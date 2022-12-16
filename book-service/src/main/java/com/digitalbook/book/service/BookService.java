package com.digitalbook.book.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.digitalbook.book.exception.RunTimeExceptionMessage;
import com.digitalbook.book.modal.Book;
import com.digitalbook.book.response.AuthorBook;
import com.digitalbook.book.response.ReaderBookResponse;
import com.digitalbook.book.response.SearchResponse;

@Component
public interface BookService {

	int createBookByAuthor(Book book) throws SQLException, RunTimeExceptionMessage;

	int updateBlockBookByAuthor(Book book, int id, String updateOrBlock) throws SQLException;

	List<ReaderBookResponse> searchBook(Map<String, String> searchKeys, String subscribeBooks, int userId)
			throws SQLException;

	int subscriptionBook(int id, int userid, String subscribe) throws SQLException;

	int exitsByAuthorBooktitle(Book book) throws SQLException;

	List<AuthorBook> authorBook(int userId) throws SQLException;

	Book getBookById(int bookid) throws SQLException;

	List<SearchResponse> searchBooks(String date, String booktitle, String publisher, int userid) throws SQLException;

}
