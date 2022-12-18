package com.digitalbook.user.response;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class MessageResponseTest {

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
		MessageResponse MessageResponse = new MessageResponse("");
		MessageResponse.setMessage("");
		Assert.assertEquals("", MessageResponse.getMessage());
		MessageResponse MessageResponse1 = new MessageResponse("");
		MessageResponse MessageResponse2 = new MessageResponse("");
		Assert.assertEquals(MessageResponse2, MessageResponse1);
	}

}
