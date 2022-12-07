package com.digitalbook.book.serviceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbook.book.exception.RunTimeExceptionMessage;
import com.digitalbook.book.modal.Book;
import com.digitalbook.book.repository.BookServiceRepository;
import com.digitalbook.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookServiceRepository bookServiceRepository;

	@Override
	public int createBookByAuthor(Book book) throws SQLException, RunTimeExceptionMessage {
		return bookServiceRepository.createBookByAuthor(book);
	}

	@Override
	public int exitsByAuthorBooktitle(Book book) throws SQLException {
		return bookServiceRepository.exitsByAuthorBooktitle(book);
	}

	@Override
	public int updateBlockBookByAuthor(Book book, int id, String updateOrBlock) throws SQLException {
		return bookServiceRepository.updateBlockBookByAuthor(book, id, updateOrBlock);
	}

	@Override
	public List<Book> searchBook(Map<String, String> searchKeys, String subscribeBooks, int userId)
			throws SQLException {
		return bookServiceRepository.searchBook(searchKeys, subscribeBooks, userId);
	}

	@Override
	public int subscriptionBook(int bookid, int userid) throws SQLException {
		return bookServiceRepository.subscriptionBook(bookid, userid);
	}

}
