package com.digitalbook.book.response;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class ReaderBookResponseTest {



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
		ReaderBookResponse ReaderBook = new ReaderBookResponse();
		ReaderBook.setId(1L);
		ReaderBook.setSubscribeid(1);
		ReaderBook.setBookcode("bookcode");
		ReaderBook.setBooktitle("booktitle");
		ReaderBook.setCategory("category");
		ReaderBook.setContent("content");
		ReaderBook.setLogo("logo");
		ReaderBook.setPrice(11.22F);
		ReaderBook.setPublisher("publisher");
		
		Assert.assertSame(1L, ReaderBook.getId());
		Assert.assertSame("bookcode", ReaderBook.getBookcode());
		Assert.assertSame("booktitle", ReaderBook.getBooktitle());
		Assert.assertSame("category", ReaderBook.getCategory());
		Assert.assertSame("content", ReaderBook.getContent());
		Assert.assertSame("logo", ReaderBook.getLogo());
		Assert.assertSame(11.22F, ReaderBook.getPrice());
		Assert.assertSame("publisher", ReaderBook.getPublisher());
		Assert.assertSame(1, ReaderBook.getSubscribeid());
	}

}
