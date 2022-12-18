package com.digitalbook.book.exception;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class RunTimeExceptionMessageTest{


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
		RunTimeExceptionMessage ReaderBook = new RunTimeExceptionMessage("errorMessage");
		ReaderBook.setErrorCode(500);
		ReaderBook.setErrorMessage("errorMessage");
		
		Assert.assertNotSame(500, ReaderBook.getErrorCode());
		Assert.assertSame("errorMessage", ReaderBook.getErrorMessage());
		ReaderBook.toString();
		ExceptionMessage ReaderBook1 = new ExceptionMessage("errorMessage");
		Assert.assertNotSame(ReaderBook1, ReaderBook);
		ExceptionMessage ReaderBook12 = new ExceptionMessage("errorMessage",444);
		ExceptionMessage ReaderBook13 = new ExceptionMessage("errorMessage",444);
		Assert.assertNotSame(ReaderBook12, ReaderBook13);
	}

}
