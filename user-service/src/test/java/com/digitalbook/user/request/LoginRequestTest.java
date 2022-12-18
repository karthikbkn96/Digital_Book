package com.digitalbook.user.request;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class LoginRequestTest {
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
		LoginRequest LoginRequest = new LoginRequest();
		LoginRequest.setPassword("password");
		LoginRequest.setUsername("username");
		Assert.assertEquals("password", LoginRequest.getPassword());
		Assert.assertEquals("username", LoginRequest.getUsername());
	}
}
