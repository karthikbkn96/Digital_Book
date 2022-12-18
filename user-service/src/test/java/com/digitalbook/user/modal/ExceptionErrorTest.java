package com.digitalbook.user.modal;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class ExceptionErrorTest {

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
		ExceptionError ExceptionError = new ExceptionError();
		ExceptionError.setCode(500);
		ExceptionError.setMessage("errorMessage");
		ExceptionError.setCause("cause");

		Assert.assertNotSame(500, ExceptionError.getCode());
		Assert.assertSame("errorMessage", ExceptionError.getMessage());
		Assert.assertSame("cause", ExceptionError.getCause());
	}
}
