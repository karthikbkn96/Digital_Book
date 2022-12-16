package com.digitalbook.book.repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.digitalbook.book.exception.RunTimeExceptionMessage;
import com.digitalbook.book.modal.Book;
import com.digitalbook.book.response.AuthorBook;
import com.digitalbook.book.response.ReaderBookResponse;
import com.digitalbook.book.response.SearchResponse;

@Repository
public class BookServiceRepository {

	@Autowired
	JdbcTemplate jdbctemplate;

	public int createBookByAuthor(Book book) throws SQLException, RunTimeExceptionMessage {
		LocalDateTime dateTime = LocalDateTime.now();
		String createdon = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss"));

		int result = jdbctemplate.update(SqlQueries.CREATEBOOKBYAUTHOR, book.getBooktitle(), book.getBookcode(),
				book.getAuthorid(), book.getPrice(), book.getCategory(), book.getPublisher(), book.getLogo(),
				book.getAudiourl(), book.getContent(), book.getIsactive(), book.getUpdatedon(), book.getCreatedby(),
				book.getUpdatedby(), createdon, book.getPublishdate());
		return result;
	}

	public int exitsByAuthorBooktitle(Book book) throws SQLException {
		int ExistBookCount = jdbctemplate.queryForObject(SqlQueries.EXITSBYAUTHORBOOKTITLE,
				new Object[] { book.getAuthorid(), book.getBooktitle() }, Integer.class);
		return ExistBookCount;
	}

	public int updateBlockBookByAuthor(Book book, int id, String updateOrBlock) throws SQLException {
		if (updateOrBlock.equals("update")) {
			int result = jdbctemplate.update(SqlQueries.UPDATEBOOK, book.getBookcode(), book.getUpdatedon(), id);
			return result;
		} else if (updateOrBlock.equals("block")) {
			int result = jdbctemplate.update(SqlQueries.BLOCKBOOK, 0, book.getUpdatedon(), id);
			return result;
		} else if (updateOrBlock.equals("unblock")) {
			int result = jdbctemplate.update(SqlQueries.UNBLOCKBOOK, 1, book.getUpdatedon(), id);
			return result;
		}

		return 0;
	}

	public List<ReaderBookResponse> searchBook(Map<String, String> searchKeys, String subscribeBooks, int userId)
			throws SQLException {
		List<ReaderBookResponse> book = jdbctemplate.query(SqlQueries.SEARCHBOOK, new Object[] { userId },
				new BeanPropertyRowMapper(ReaderBookResponse.class));
		return book;
	}

	public List<AuthorBook> authorBook(int userId) throws SQLException {
		List<AuthorBook> book = jdbctemplate.query(SqlQueries.AUTHORBOOKS, new Object[] { userId },
				new BeanPropertyRowMapper(AuthorBook.class));
		return book;
	}

	public int subscriptionBook(int bookid, int userid, String subscribe) throws SQLException {
		if (subscribe.equals("subscribe")) {
			int result = jdbctemplate.update(SqlQueries.SUBSCRIPTIONBOOK, userid, 1, bookid);
			return result;
		} else {
			int result = jdbctemplate.update(SqlQueries.UNSUBSCRIPTIONBOOK, bookid);
			return result;
		}

	}

	public Book getBookById(int bookid) {
		return (Book) jdbctemplate.queryForObject(SqlQueries.GETBOOKBYID, new BeanPropertyRowMapper(Book.class),
				bookid);

	}

	public List<SearchResponse> searchBooks(String date, String booktitle, String publisher, int userid)
			throws SQLException {

		String exitquery = "SELECT BOOKID FROM BOOKSUBSCRIPTION WHERE USERID =" + userid + " AND ISACTIVE=1";
		List<String> list = jdbctemplate.queryForList(exitquery, String.class);

		if (list.size() == 0) {
			String query = "SELECT * FROM BOOK WHERE PUBLISHDATE LIKE '%" + date + "%' AND BOOKTITLE LIKE '%"
					+ booktitle + "%' AND PUBLISHER LIKE '%" + publisher + "%' AND ISACTIVE=1";
			System.out.println(query);
			return jdbctemplate.query(query, new Object[] {}, new BeanPropertyRowMapper(SearchResponse.class));
		} else {
			List<Integer> list1 = list.stream().map(Integer::parseInt).collect(Collectors.toList());
			String query1 = "SELECT * FROM BOOK WHERE PUBLISHDATE LIKE '%" + date + "%' AND BOOKTITLE LIKE '%"
					+ booktitle + "%' AND PUBLISHER LIKE '%" + publisher + "%' AND ISACTIVE=1 AND ID NOT IN (?)";
			return jdbctemplate.query(query1, list1.toArray(),
					(rs, rowNum) -> new SearchResponse(rs.getLong("id"), rs.getString("booktitle"),
							rs.getString("category"), rs.getString("publisher"), rs.getString("logo"),
							rs.getFloat("price"), rs.getString("audiourl"), rs.getString("content"),
							rs.getString("updatedon"), rs.getString("publishdate")));

		}

	}
}
