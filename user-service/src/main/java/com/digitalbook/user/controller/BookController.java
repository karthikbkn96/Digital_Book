package com.digitalbook.user.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.websocket.server.PathParam;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digitalbook.user.UserServiceApplication;

@RestController
@RequestMapping("/digitalbook")
public class BookController {

	@GetMapping("/SubscribedBooks/{id}")
	@PreAuthorize("hasRole('AUTHOR') || hasRole('READER')")
	public String SubscribedBooks(@PathVariable int id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return UserServiceApplication.restTemplate()
				.exchange("http://localhost:8089/book/SubscribedBooks/" + id, HttpMethod.GET, entity, String.class)
				.getBody();

	}

	@PreAuthorize("hasRole('AUTHOR')")
	@PostMapping(value = "/blockUnBlockBook/{id}")
	public ResponseEntity<?> blockUnBlockBook(@RequestParam("blockorupdate") String blockorupdate,
			@PathVariable("id") int id) {

		String url = "http://localhost:8089/book/blockUnBlockBook/" + id;
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("blockorupdate", blockorupdate);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
		ResponseEntity<?> response = UserServiceApplication.restTemplate().exchange(url, HttpMethod.POST, requestEntity,
				String.class);
		return response;
	}

	@PreAuthorize("hasRole('AUTHOR') || hasRole('READER')")
	@GetMapping(value = "/BookSubscription/{userid}/{bookid}")
	public String BookSubscription(@PathVariable("bookid") int bookid, @PathVariable("userid") int userid) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		StringBuilder sb = new StringBuilder();
		sb.append("http://localhost:8089/book/BookSubscription/");
		sb.append(userid);
		sb.append("/");
		sb.append(bookid);
		return UserServiceApplication.restTemplate().exchange(sb.toString(), HttpMethod.GET, entity, String.class)
				.getBody();
	}

	// @PreAuthorize("hasRole('AUTHOR')")
	@PostMapping(value = "/createBookByAuthor", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createBookByAuthor(@RequestParam("book") String book,
			@RequestParam("bookcode") MultipartFile document) throws IOException {

		String url = "http://localhost:8089/book/createBookByAuthor/";
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("book", book);
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

	@PutMapping(value = "/updateBookByAuthor/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> updateBookByAuthor(@RequestParam("bookcode") MultipartFile document,
			@RequestParam("blockorupdate") String blockorupdate, @PathParam("id") int id)
			throws JSONException, IOException {

		String url = "http://localhost:8089/book/updateBookByAuthor/"+id;
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("blockorupdate", blockorupdate);
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
}
