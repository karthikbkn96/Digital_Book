package com.digitalbook.book.modal;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class BookSubscriptionTest {

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
		BookSubscription BookSubscription = new BookSubscription();
		BookSubscription.setId(1L);
		BookSubscription.setUserid(1);
		BookSubscription.setIsactive(1);
		BookSubscription.setBookid(1);

		Assert.assertSame(1L, BookSubscription.getId());
		Assert.assertSame(1, BookSubscription.getUserid());
		Assert.assertSame(1, BookSubscription.getIsactive());
		Assert.assertSame(1, BookSubscription.getBookid());
		BookSubscription BookSubscription1 = new BookSubscription(1, 1, 1);
		BookSubscription BookSubscription2 = new BookSubscription(1, 1, 1);
		Assert.assertEquals(BookSubscription1, BookSubscription2);
	}

}
