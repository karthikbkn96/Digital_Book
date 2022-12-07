package com.digitalbook.user.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.digitalbook.user.modal.ExceptionError;

@RestController
@RequestMapping("/digitalbook")
public class BookController {

	@GetMapping("/SubscribedBooks/{id}")
	@PreAuthorize("hasRole('AUTHOR') || hasRole('READER')")
	public String SubscribedBooks(@PathVariable int id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate
				.exchange("http://localhost:8089/book/SubscribedBooks/" + id, HttpMethod.GET, entity, String.class)
				.getBody();

	}

	@PostMapping(value = "/blockUnBlockBook/{id}")
	public ResponseEntity<?> blockUnBlockBook(@RequestParam("blockorupdate") String blockorupdate,
			@PathVariable("id") int id) {

		String url = "http://localhost:8089/book/blockUnBlockBook/" + id;

		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("blockorupdate", blockorupdate);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		return response;
	}

	@GetMapping(value = "/BookSubscription/{userid}/{bookid}")
	public String BookSubscription(@PathVariable("bookid") int bookid, @PathVariable("userid") int userid) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder sb = new StringBuilder();
		sb.append("http://localhost:8089/book/BookSubscription/");
		sb.append(userid);
		sb.append("/");
		sb.append(bookid);
		return restTemplate.exchange(sb.toString(), HttpMethod.GET, entity, String.class).getBody();
	}
}
