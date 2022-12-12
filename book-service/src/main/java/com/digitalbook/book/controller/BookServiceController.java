package com.digitalbook.book.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.websocket.server.PathParam;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbook.book.exception.RunTimeExceptionMessage;
import com.digitalbook.book.modal.Book;
import com.digitalbook.book.modal.ExceptionError;
import com.digitalbook.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RefreshScope
@RestController
@Validated
@RequestMapping("/book")
public class BookServiceController {

	@Autowired
	BookService bookService;

	@PostMapping(value = "/createBookByAuthor")
	public ResponseEntity<?> createBookByAuthor(@RequestParam("book") String book,
			@RequestParam("bookcode") String document) throws SQLException, RunTimeExceptionMessage {

		try {
			ObjectMapper ob = new ObjectMapper();
			Book books = new Book();
			books = ob.readValue(book, Book.class);
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

			Validator validator = factory.getValidator();
			Set<ConstraintViolation<Book>> violations = validator.validate(books);

			if (!violations.isEmpty()) {
				List<String> message = new ArrayList<>();
				for (ConstraintViolation<?> violation : violations) {
					message.add(violation.getPropertyPath() + ":" + violation.getMessage());
				}
				return ResponseEntity.badRequest().body(message);
			} else {
				books.setBookcode(document);

				if (bookService.exitsByAuthorBooktitle(books) == 1) {
					return ResponseEntity.badRequest().body("Error: You have already uploaded the same book!");
				}

				bookService.createBookByAuthor(books);
				return ResponseEntity.ok("Book Created Successfully");

			}

		} catch (IOException e1) {
			ExceptionError error = new ExceptionError();
			error.setCode(HttpStatus.SC_BAD_REQUEST);
			error.setMessage(e1.getMessage().toString());
			return ResponseEntity.badRequest().body(error);
		}

	}

	@PutMapping(value = "/updateBookByAuthor/{id}")
	public ResponseEntity<?> updateBookByAuthor(@RequestParam("bookcode") String document,
			@RequestParam("blockorupdate") String blockorupdate, @PathParam("id") int id)
			throws SQLException, RunTimeExceptionMessage {

		try {

			Book books = new Book();
			books.setBookcode(document.toString());

			if (bookService.exitsByAuthorBooktitle(books) == 1) {
				return ResponseEntity.badRequest()
						.body(new ExceptionError("Error: You have already uploaded the same book!"));
			}

			if (blockorupdate.equals("update")) {
				bookService.updateBlockBookByAuthor(books, id, blockorupdate);
				return ResponseEntity.ok("Book Updated Successfully");
			} else if (blockorupdate.equals("block")) {
				bookService.updateBlockBookByAuthor(books, id, blockorupdate);
				return ResponseEntity.ok("Book Block Successfully");
			} else if (blockorupdate.equals("unblock")) {
				bookService.updateBlockBookByAuthor(books, id, blockorupdate);
				return ResponseEntity.ok("Book Unblocked Successfully");
			}
			return ResponseEntity.ok("Success");
		} catch (Exception e1) {
			ExceptionError error = new ExceptionError();
			error.setCode(HttpStatus.SC_BAD_REQUEST);
			error.setMessage("Server Side Issue");
			return ResponseEntity.badRequest().body(error);
		}

	}

	@PostMapping(value = "/blockUnBlockBook/{id}")
	public ResponseEntity<?> blockUnBlockBook(@RequestParam("blockorupdate") String blockorupdate,
			@PathVariable("id") int id) throws SQLException, RunTimeExceptionMessage {

		try {
			Book books = new Book();
			if (blockorupdate.equals("block")) {
				bookService.updateBlockBookByAuthor(books, id, blockorupdate);
				return ResponseEntity.ok("Book Blocked Successfully");
			} else if (blockorupdate.equals("unblock")) {
				bookService.updateBlockBookByAuthor(books, id, blockorupdate);
				return ResponseEntity.ok("Book Unblocked Successfully");
			}
			return ResponseEntity.badRequest().body("Book Not Found");
		} catch (Exception e1) {
			ExceptionError error = new ExceptionError();
			error.setCode(HttpStatus.SC_BAD_REQUEST);
			error.setMessage("Server Side Issue");
			return ResponseEntity.badRequest().body(error);
		}

	}

	@GetMapping(value = "/BookSubscription/{userid}/{bookid}")
	public ResponseEntity<?> BookSubscription(@PathVariable("bookid") int bookid, @PathVariable("userid") int userid)
			throws SQLException, RunTimeExceptionMessage {

		try {
			bookService.subscriptionBook(bookid, userid);
			return ResponseEntity.ok("Book Subscribed Successfully");

		} catch (Exception e1) {
			ExceptionError error = new ExceptionError();
			error.setCode(HttpStatus.SC_BAD_REQUEST);
			error.setMessage("Server Side Issue");
			return ResponseEntity.badRequest().body(error);
		}

	}

	@GetMapping(value = "/SubscribedBooks/{userid}")
	public List<Book> BookSubscription(@PathVariable("userid") int userid) throws SQLException {

		List<Book> book = bookService.searchBook(null, null, userid);
		return book;

	}

	@GetMapping(value = "/AuthorBook/{userid}")
	public List<Book> AuthorBook(@PathVariable("userid") int userid) throws SQLException {

		List<Book> book = bookService.authorBook(userid);
		return book;

	}

	@GetMapping(value = "/getbookById/{bookid}")
	public Book GetbookById(@PathVariable("bookid") int bookid) throws SQLException, RunTimeExceptionMessage {

		return bookService.getBookById(bookid);

	}
}
