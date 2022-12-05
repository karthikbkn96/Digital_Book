package com.digitalbook.book.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.digitalbook.book.exception.RunTimeExceptionMessage;
import com.digitalbook.book.modal.Book;

@Component
public interface BookService {

	int createBookByAuthor(Book book) throws SQLException, RunTimeExceptionMessage;

	int updateBlockBookByAuthor(Book book, int id, String updateOrBlock)  throws SQLException;

	List<Book> searchBook(Map<String, String> searchKeys, String subscribeBooks, int userId)  throws SQLException;

	int subscriptionBook(int id, int userid)  throws SQLException;

	int exitsByAuthorBooktitle(Book book) throws SQLException;

}
