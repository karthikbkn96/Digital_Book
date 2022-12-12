package com.digitalbook.user.request;

import org.springframework.web.multipart.MultipartFile;

public class createBookRequest {

	String book;
	
	MultipartFile  multipartFile;

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
	
}
