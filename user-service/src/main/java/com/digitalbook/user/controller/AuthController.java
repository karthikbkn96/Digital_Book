package com.digitalbook.user.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digitalbook.user.UserServiceApplication;
import com.digitalbook.user.jwt.JwtUtils;
import com.digitalbook.user.modal.ERole;
import com.digitalbook.user.modal.ExceptionError;
import com.digitalbook.user.modal.Role;
import com.digitalbook.user.modal.User;
import com.digitalbook.user.request.LoginRequest;
import com.digitalbook.user.request.SignupRequest;
import com.digitalbook.user.response.JwtResponse;
import com.digitalbook.user.response.MessageResponse;
import com.digitalbook.user.service.RoleRepository;
import com.digitalbook.user.service.UserRepository;
import com.digitalbook.user.serviceimpl.UserDetailsImpl;

@RefreshScope
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;

	@Value("${bookservice.url}")
	private String bookservice;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			List<String> message = new ArrayList<>();

			for (FieldError e : errors) {
				message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
			}
			ExceptionError error = new ExceptionError();
			error.setCode(500);
			error.setMessage("Please Enter Valid Details");
			error.setCause(message.toString());
			return ResponseEntity.badRequest().body(error);
		}

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhonenumber(),
				signUpRequest.getIsactive());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_QUEST)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "reader":
					Role adminRole = roleRepository.findByName(ERole.ROLE_READER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "author":
					Role modRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_QUEST)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		user.setIsactive("1");
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@GetMapping("/SubscribedBooks")
	@PreAuthorize("hasRole('READER') || hasRole('AUTHOR')")
	public String SubscribedBooks() throws Exception {
		User users = CurrentUser();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return UserServiceApplication.restTemplate()
				.exchange(bookservice + "SubscribedBooks/" + users.getId(), HttpMethod.GET, entity, String.class)
				.getBody();

	}

	@GetMapping("/AuthorBooks")
	@PreAuthorize("hasRole('AUTHOR')")
	public String AuthorBooks() throws Exception {
		User users = CurrentUser();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return UserServiceApplication.restTemplate()
				.exchange(bookservice + "AuthorBook/" + users.getId(), HttpMethod.GET, entity, String.class).getBody();

	}

	@PreAuthorize("hasRole('AUTHOR')")
	@PostMapping(value = "/blockUnBlockBook/{id}")
	public ResponseEntity<?> blockUnBlockBook(@RequestBody String blockorupdate, @PathVariable("id") int id)
			throws Exception {

		String url = bookservice + "blockUnBlockBook/" + id;

		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("blockorupdate", new JSONObject(blockorupdate).get("blockorunblock"));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
		ResponseEntity<?> response = UserServiceApplication.restTemplate().exchange(url, HttpMethod.POST, requestEntity,
				String.class);
		return response;
	}

	@PreAuthorize("hasRole('READER') || hasRole('AUTHOR')")
	@PostMapping(value = "/BookSubscription/{bookid}")
	public String BookSubscription(@PathVariable("bookid") int bookid, @RequestBody String subscribe) throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		StringBuilder sb = new StringBuilder();
		sb.append("BookSubscription/");
		sb.append(CurrentUser().getId());
		sb.append("/");
		sb.append(bookid);
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("subscribe", subscribe);
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(bodyMap, headers);
		return UserServiceApplication.restTemplate()
				.exchange(bookservice + sb.toString(), HttpMethod.POST, entity, String.class).getBody();
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@PostMapping(value = "/createBookByAuthor", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> createBookByAuthor(@RequestPart("book") String book,
			@RequestPart("bookcode") MultipartFile document, @RequestPart("logo") MultipartFile logo)
			throws IOException, Exception {

		LocalDateTime dateTime = LocalDateTime.now();
		String currentDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss"));
		String url = bookservice + "createBookByAuthor/";
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();

		JSONObject bookObject = new JSONObject(book);

		User users = CurrentUser();
		bookObject.put("authorid", users.getId());
		bookObject.put("publisher", users.getUsername());
		bookObject.put("createdby", users.getId());
		bookObject.put("updatedby", users.getId());
		bookObject.put("updatedon", currentDateTime);
		bookObject.put("createdon", currentDateTime);
		bookObject.put("isactive", 1);

		bodyMap.add("book", bookObject.toString());

		if (null != document.getOriginalFilename()) {
			if (StringUtils.endsWithIgnoreCase(document.getOriginalFilename(), "pdf")
					|| StringUtils.endsWithIgnoreCase(document.getOriginalFilename(), "docx")) {
				JSONObject obj = new JSONObject();
				obj.put("name", document.getOriginalFilename());
				obj.put("type", document.getContentType());
				obj.put("data", document.getBytes());
				bodyMap.add("bookcode", obj.toString());
			} else {
				return ResponseEntity.badRequest().body("Please Upload PDF/DOCX files");
			}
		} else {
			return ResponseEntity.badRequest().body("Please upload pdf/docx file");
		}
		if (null != logo.getOriginalFilename()) {
			JSONObject obj = new JSONObject();
			obj.put("name", logo.getOriginalFilename());
			obj.put("type", logo.getContentType());
			obj.put("data", logo.getBytes());
			bodyMap.add("logo", obj.toString());
		} else {
			return ResponseEntity.badRequest().body("Please Upload PDF/DOCX files");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
		ResponseEntity<?> response = UserServiceApplication.restTemplate().exchange(url, HttpMethod.POST, requestEntity,
				String.class);
		return response;

	}

	@PreAuthorize("hasRole('AUTHOR')")
	@PostMapping(value = "/updateBookByAuthor/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> updateBookByAuthor(@RequestPart("bookcode") MultipartFile document,
			@PathVariable("id") int id) throws IOException, Exception {

		String url = bookservice + "updateBookByAuthor/" + id;
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("blockorupdate", "update");
		if (null != document.getOriginalFilename()) {
			if (StringUtils.endsWithIgnoreCase(document.getOriginalFilename(), "pdf")
					|| StringUtils.endsWithIgnoreCase(document.getOriginalFilename(), "docx")) {
				JSONObject obj = new JSONObject();
				obj.put("name", document.getOriginalFilename());
				obj.put("type", document.getContentType());
				obj.put("data", document.getBytes());
				bodyMap.add("bookcode", obj.toString());
			} else {
				return ResponseEntity.badRequest().body("Please Upload PDF/DOCX files");
			}
		} else {
			return ResponseEntity.badRequest().body("Please upload pdf/docx file");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
		ResponseEntity<?> response = UserServiceApplication.restTemplate().exchange(url, HttpMethod.POST, requestEntity,
				String.class);
		return response;

	}

	@GetMapping(value = "/getbookById/{bookid}")
	public String BookSubscription(@PathVariable("bookid") String bookid) throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String sb = bookservice + "getbookById/" + bookid;
		return UserServiceApplication.restTemplate().exchange(sb, HttpMethod.GET, entity, String.class).getBody();
	}

	private User CurrentUser() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> user = userRepository.findByUsername(auth.getName());
		if (user.isPresent()) {
			return user.get();
		} else {
			return new User();
		}

	}

	@PostMapping(value = "/searchBook")
	public String searchBooks(@RequestBody String search) throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		User users = CurrentUser();
		String sb = bookservice + "searchBook";
		JSONObject obj = new JSONObject(search);
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("userid", users.getId());
		bodyMap.add("date", obj.get("date"));
		bodyMap.add("booktitle", obj.get("booktitle"));
		bodyMap.add("publisher", obj.get("publisher"));
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(bodyMap, headers);
		return UserServiceApplication.restTemplate().exchange(sb.toString(), HttpMethod.POST, entity, String.class)
				.getBody();

	}

}
