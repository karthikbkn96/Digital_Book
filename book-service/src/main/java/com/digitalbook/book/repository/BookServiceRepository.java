package com.digitalbook.book.repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.digitalbook.book.exception.RunTimeExceptionMessage;
import com.digitalbook.book.modal.Book;

@Repository
public class BookServiceRepository {

	@Autowired
	JdbcTemplate jdbctemplate;

	public int createBookByAuthor(Book book) throws SQLException, RunTimeExceptionMessage {
		LocalDateTime dateTime = LocalDateTime.now();
		String createdon = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss"));

		int result = jdbctemplate.update(SqlQueries.createBookByAuthor, book.getBooktitle(), book.getBookcode(),
				book.getAuthorid(), book.getPrice(), book.getCategory(), book.getPublisher(), book.getLogo(),
				book.getAudiourl(), book.getContent(), book.getIsactive(), book.getUpdatedon(), book.getCreatedby(),
				book.getUpdatedby(), createdon);
		return result;
	}

	public int exitsByAuthorBooktitle(Book book) throws SQLException {
		int ExistBookCount = jdbctemplate.queryForObject(SqlQueries.exitsByAuthorBooktitle,
				new Object[] { book.getAuthorid(), book.getBooktitle() }, Integer.class);
		return ExistBookCount;
	}

	public int updateBlockBookByAuthor(Book book, int id, String updateOrBlock) throws SQLException {
		if (updateOrBlock.equals("update")) {
			int result = jdbctemplate.update(SqlQueries.updateBook, book.getBookcode(), book.getUpdatedon(), id);
			return result;
		} else if (updateOrBlock.equals("block")) {
			int result = jdbctemplate.update(SqlQueries.blockBook, 0, book.getUpdatedon(), id);
			return result;
		} else if (updateOrBlock.equals("unblock")) {
			int result = jdbctemplate.update(SqlQueries.unBlockBook, 1, book.getUpdatedon(), id);
			return result;
		}

		return 0;
	}

	public List<Book> searchBook(Map<String, String> searchKeys, String subscribeBooks, int userId)
			throws SQLException {
		List<Book> book = jdbctemplate.query(SqlQueries.searchBook, new Object[] { userId },
				new BeanPropertyRowMapper(Book.class));
		return book;
	}

	public int subscriptionBook(int bookid, int userid) throws SQLException {
		int result = jdbctemplate.update(SqlQueries.subscriptionBook, userid, 1, bookid);
		return result;
	}

}
