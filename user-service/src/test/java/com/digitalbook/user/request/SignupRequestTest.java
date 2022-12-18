package com.digitalbook.user.request;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class SignupRequestTest {

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
		SignupRequest SignupRequest = new SignupRequest();
		SignupRequest.setPassword("password");
		SignupRequest.setUsername("username");
		SignupRequest.setEmail("email");
		SignupRequest.setIsactive("isactive");
		SignupRequest.setPhonenumber("phonenumber");
		Assert.assertEquals("password", SignupRequest.getPassword());
		Assert.assertEquals("username", SignupRequest.getUsername());
		Assert.assertEquals("email", SignupRequest.getEmail());
		Assert.assertEquals("isactive", SignupRequest.getIsactive());
		Assert.assertEquals("phonenumber", SignupRequest.getPhonenumber());
	}

}
