package com.digitalbook.user.modal;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class UserTest {

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
		User User = new User();
		User.setEmail("email");
		User.setId(1L);
		User.setIsactive("1");

		User.setPassword("password");

		User.setPhonenumber("phonenumber");
		User.setUsername("username");

		Assert.assertSame("email", User.getEmail());
		Assert.assertSame("1", User.getIsactive());
		Assert.assertNotSame(1L, User.getId());
		Assert.assertSame("password", User.getPassword());
		Assert.assertSame("phonenumber", User.getPhonenumber());
		Assert.assertSame("username", User.getUsername());

		User User1 = new User("username", "email", "password", "phonenumber", "isactive");

		User User2 = new User("username", "email", "password", "phonenumber", "isactive");
		Assert.assertSame(User2, User1);
	}

}
