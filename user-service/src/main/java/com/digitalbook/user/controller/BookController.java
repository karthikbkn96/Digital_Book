package com.digitalbook.user.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digitalbook.user.UserServiceApplication;
import com.digitalbook.user.modal.User;
import com.digitalbook.user.service.UserRepository;

@RefreshScope
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/digitalbook")
public class BookController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/SubscribedBooks")
	@PreAuthorize("hasRole('READER')")
	public String SubscribedBooks() {
		User users = CurrentUser();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return UserServiceApplication.restTemplate()
				.exchange("http://localhost:8089/book/SubscribedBooks/" + users.getId(), HttpMethod.GET, entity,
						String.class)
				.getBody();

	}

	@GetMapping("/AuthorBooks")
	@PreAuthorize("hasRole('AUTHOR')")
	public String AuthorBooks() {
		User users = CurrentUser();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return UserServiceApplication.restTemplate().exchange("http://localhost:8089/book/AuthorBook/" + users.getId(),
				HttpMethod.GET, entity, String.class).getBody();

	}

	@PreAuthorize("hasRole('AUTHOR')")
	@PostMapping(value = "/blockUnBlockBook/{id}")
	public ResponseEntity<?> blockUnBlockBook(@RequestBody String blockorupdate, @PathVariable("id") int id) {

		String url = "http://localhost:8089/book/blockUnBlockBook/" + id;

		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("blockorupdate", new JSONObject(blockorupdate).get("blockorunblock"));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
		ResponseEntity<?> response = UserServiceApplication.restTemplate().exchange(url, HttpMethod.POST, requestEntity,
				String.class);
		return response;
	}

	@PreAuthorize("hasRole('READER')")
	@PostMapping(value = "/BookSubscription/{bookid}")
	public String BookSubscription(@PathVariable("bookid") int bookid, @RequestBody String subscribe) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		StringBuilder sb = new StringBuilder();
		sb.append("http://localhost:8089/book/BookSubscription/");
		sb.append(CurrentUser().getId());
		sb.append("/");
		sb.append(bookid);
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("subscribe", subscribe);
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(bodyMap, headers);
		return UserServiceApplication.restTemplate().exchange(sb.toString(), HttpMethod.POST, entity, String.class)
				.getBody();
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@PostMapping(value = "/createBookByAuthor", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> createBookByAuthor(@RequestPart("book") String book,
			@RequestPart("bookcode") MultipartFile document, @RequestPart("logo") MultipartFile logo)
			throws IOException {

		LocalDateTime dateTime = LocalDateTime.now();
		String currentDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss"));
		String url = "http://localhost:8089/book/createBookByAuthor/";
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

	@PutMapping(value = "/updateBookByAuthor/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> updateBookByAuthor(@RequestPart("book") String book,
			@RequestPart("bookcode") MultipartFile document, @PathVariable("id") int id, @RequestPart("logo") MultipartFile logo)
			throws IOException {

		String url = "http://localhost:8089/book/updateBookByAuthor/" + id;
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

	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping(value = "/getbookById/{bookid}")
	public String BookSubscription(@PathVariable("bookid") String bookid) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String sb = "http://localhost:8089/book/getbookById/" + bookid;
		return UserServiceApplication.restTemplate().exchange(sb, HttpMethod.GET, entity, String.class).getBody();
	}

	private User CurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> user = userRepository.findByUsername(auth.getName());
		return user.get();
	}

	@PostMapping(value = "/searchBook")
	public String searchBooks(@RequestBody String search) throws SQLException {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		User users = CurrentUser();
		String sb = "http://localhost:8089/book/searchBook";
		JSONObject obj = new JSONObject(search);
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("userid",users.getId());
		bodyMap.add("date", obj.get("date"));
		bodyMap.add("booktitle", obj.get("booktitle"));
		bodyMap.add("publisher", obj.get("publisher"));
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(bodyMap, headers);
		return UserServiceApplication.restTemplate().exchange(sb.toString(), HttpMethod.POST, entity, String.class)
				.getBody();

	}
}
