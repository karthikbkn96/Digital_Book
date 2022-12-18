package com.digitalbook.book.request;

import java.sql.Date;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.digitalbook.book.modal.ExceptionError;

import junit.framework.Assert;

public class BookRequestTest {

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}

	@BeforeAll
	static void initAll() {
	}

	@BeforeEach
	void init() {
	}

	@Test
	public void test() {
		Date LocalDate = new Date(11, 11, 11);
		BookRequest BookRequest = new BookRequest();
		BookRequest.setAudiourl("www.audio.com");
		BookRequest.setAuthorid("1");
		BookRequest.setBookcode("bookcode");
		BookRequest.setBooktitle("setBookTitle");
		BookRequest.setCategory("category");
		BookRequest.setContent("content");
		BookRequest.setCreatedby(1);
		BookRequest.setIsactive(1);
		BookRequest.setLogo("logo");
		BookRequest.setPrice(11.00);
		BookRequest.setPublisher("publisher");
		BookRequest.setUpdateBy(LocalDate);
		BookRequest.setUpdatedon(LocalDate);

		Assert.assertSame("www.audio.com", BookRequest.getAudiourl());
		Assert.assertSame("1", BookRequest.getAuthorid());
		Assert.assertSame("bookcode", BookRequest.getBookcode());
		Assert.assertSame("setBookTitle", BookRequest.getBooktitle());
		Assert.assertSame("category", BookRequest.getCategory());
		Assert.assertSame("content", BookRequest.getContent());
		Assert.assertSame(1, BookRequest.getCreatedby());
		Assert.assertSame(1, BookRequest.getIsactive());

		Assert.assertSame("logo", BookRequest.getLogo());
		Assert.assertSame(11.00, BookRequest.getPrice());
		Assert.assertSame("publisher", BookRequest.getPublisher());
		Assert.assertSame(LocalDate, BookRequest.getUpdateBy());
		Assert.assertSame(LocalDate, BookRequest.getUpdatedon());

	}
}
