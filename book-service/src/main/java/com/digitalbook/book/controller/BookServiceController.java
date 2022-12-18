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

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbook.book.exception.RunTimeExceptionMessage;
import com.digitalbook.book.modal.Book;
import com.digitalbook.book.response.AuthorBook;
import com.digitalbook.book.response.ReaderBookResponse;
import com.digitalbook.book.response.SearchResponse;
import com.digitalbook.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Validated
@RequestMapping("/book")
public class BookServiceController {

	@Autowired
	BookService bookService;

	@PostMapping(value = "/createBookByAuthor")
	public ResponseEntity<?> createBookByAuthor(@RequestParam("book") String book,
			@RequestParam("bookcode") String document, @RequestParam("logo") String logo)
			throws SQLException, RunTimeExceptionMessage, Exception {

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
				books.setLogo(logo);
				if (bookService.exitsByAuthorBooktitle(books) == 1) {
					return ResponseEntity.badRequest().body("Error: You have already uploaded the same book!");
				}

				bookService.createBookByAuthor(books);
				return ResponseEntity.ok("Book Created Successfully");

			}

		} catch (IOException e1) {
			return ResponseEntity.badRequest().body("Server Side Issue");
		}

	}

	@PostMapping(value = "/updateBookByAuthor/{id}")
	public ResponseEntity<?> updateBookByAuthor(@RequestParam("bookcode") String document,
			@RequestParam("blockorupdate") String blockorupdate, @PathVariable("id") int id)
			throws SQLException, RunTimeExceptionMessage, Exception {

		try {

			Book books = new Book();
			books.setBookcode(document);

			if (blockorupdate.equals("update")) {
				bookService.updateBlockBookByAuthor(books, id, blockorupdate);
				return ResponseEntity.ok("Book Updated Successfully");
			}
			return ResponseEntity.ok("Success");
		} catch (Exception e1) {
			return ResponseEntity.badRequest().body("Server Side Issue");
		}

	}

	@PostMapping(value = "/blockUnBlockBook/{id}")
	public ResponseEntity<?> blockUnBlockBook(@RequestParam("blockorupdate") String blockorupdate,
			@PathVariable("id") int id) throws SQLException, RunTimeExceptionMessage, Exception {

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
			return ResponseEntity.badRequest().body("Server Side Issue");
		}

	}

	@PostMapping(value = "/BookSubscription/{userid}/{bookid}")
	public ResponseEntity<?> BookSubscription(@PathVariable("bookid") int bookid,
			@RequestParam("subscribe") String subscribe, @PathVariable("userid") int userid)
			throws SQLException, RunTimeExceptionMessage, Exception {

		try {
			String subcscribe = new JSONObject(subscribe).getString("subscribe");
			bookService.subscriptionBook(bookid, userid, subcscribe);
			if (subscribe.equals("subscribe")) {
				return ResponseEntity.ok("Book Subscribed Successfully");
			} else {
				return ResponseEntity.ok("Book Unsubscribed");
			}

		} catch (Exception e1) {
			return ResponseEntity.badRequest().body("Server Side Issue");
		}

	}

	@GetMapping(value = "/SubscribedBooks/{userid}")
	public List<ReaderBookResponse> BookSubscription(@PathVariable("userid") int userid)
			throws SQLException, Exception {

		List<ReaderBookResponse> book = bookService.searchBook(null, null, userid);
		return book;

	}

	@GetMapping(value = "/AuthorBook/{userid}")
	public List<AuthorBook> AuthorBook(@PathVariable("userid") int userid) throws SQLException, Exception {

		List<AuthorBook> book = bookService.authorBook(userid);
		System.out.println(book);
		return book;

	}

	@GetMapping(value = "/getbookById/{bookid}")
	public Book GetbookById(@PathVariable("bookid") int bookid)
			throws SQLException, RunTimeExceptionMessage, Exception {

		return bookService.getBookById(bookid);

	}

	@PostMapping(value = "/searchBook")
	public List<SearchResponse> searchBooks(@RequestParam("date") String date,
			@RequestParam("booktitle") String booktitle, @RequestParam("userid") String userid,
			@RequestParam("publisher") String publisher) throws SQLException, RunTimeExceptionMessage, Exception {

		if (userid.equals("")) {
			return bookService.searchBooks(date, booktitle, publisher, 0);
		}
		return bookService.searchBooks(date, booktitle, publisher, Integer.parseInt(userid));

	}

}
