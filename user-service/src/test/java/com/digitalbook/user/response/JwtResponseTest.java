package com.digitalbook.user.response;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class JwtResponseTest {

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
		JwtResponse JwtResponse = new JwtResponse(null, null, null, null, null);
		JwtResponse.setAccessToken("accessToken");
		JwtResponse.setUsername("username");
		JwtResponse.setEmail("email");
		JwtResponse.setTokenType("tokenType");
		Assert.assertEquals("accessToken", JwtResponse.getAccessToken());
		Assert.assertEquals("username", JwtResponse.getUsername());
		Assert.assertEquals("email", JwtResponse.getEmail());
		Assert.assertEquals("tokenType", JwtResponse.getTokenType());
		JwtResponse JwtResponse1 = new JwtResponse(null, null, null, null, null);
		JwtResponse JwtResponse2 = new JwtResponse(null, null, null, null, null);
		Assert.assertEquals(JwtResponse2, JwtResponse1);
	}

}
