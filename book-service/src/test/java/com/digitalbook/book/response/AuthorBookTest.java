package com.digitalbook.book.response;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class AuthorBookTest {

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
		AuthorBook AuthorBook = new AuthorBook();
		AuthorBook.setId(1L);
		AuthorBook.setAuthorid(1);
		AuthorBook.setBookcode("bookcode");
		AuthorBook.setBooktitle("booktitle");
		AuthorBook.setCategory("category");
		AuthorBook.setContent("content");
		AuthorBook.setIsactive(1);
		AuthorBook.setLogo("logo");
		AuthorBook.setPrice(11.22F);
		AuthorBook.setPublisher("publisher");
		AuthorBook.setSubscribedcount(1);
		
		Assert.assertSame(1L, AuthorBook.getId());
		Assert.assertSame(1, AuthorBook.getAuthorid());
		Assert.assertSame("bookcode", AuthorBook.getBookcode());
		Assert.assertSame("booktitle", AuthorBook.getBooktitle());
		Assert.assertSame("category", AuthorBook.getCategory());
		Assert.assertSame("content", AuthorBook.getContent());
		Assert.assertSame(1, AuthorBook.getIsactive());
		Assert.assertSame("logo", AuthorBook.getLogo());
		Assert.assertSame(11.22F, AuthorBook.getPrice());
		Assert.assertSame("publisher", AuthorBook.getPublisher());
		Assert.assertSame(1, AuthorBook.getSubscribedcount());
	}
}
