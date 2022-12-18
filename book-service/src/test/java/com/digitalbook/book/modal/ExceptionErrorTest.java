package com.digitalbook.book.modal;

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
		ExceptionError.setCode(500);
		ExceptionError.setMessage("Error");

		Assert.assertSame(500, ExceptionError.getCode());
		Assert.assertSame(500, ExceptionError.getCode());
		Assert.assertSame("Error", ExceptionError.getMessage());
		ExceptionError ExceptionError1 = new ExceptionError("Exception");
		ExceptionError ExceptionError2 = new ExceptionError("Exception");
		Assert.assertEquals(ExceptionError1, ExceptionError2);
	}

}
