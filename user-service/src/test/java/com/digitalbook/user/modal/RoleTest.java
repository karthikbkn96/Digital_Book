package com.digitalbook.user.modal;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class RoleTest {

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
		Role Role = new Role();
		Role.setId(1);
		Role.setName(ERole.ROLE_AUTHOR);
		

		Assert.assertNotSame(1, Role.getId());
		Assert.assertSame(ERole.ROLE_AUTHOR, Role.getName());
	}

}