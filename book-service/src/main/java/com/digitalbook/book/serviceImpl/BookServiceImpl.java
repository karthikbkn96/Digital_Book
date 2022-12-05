package com.digitalbook.book.serviceImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.digitalbook.book.exception.RunTimeExceptionMessage;
import com.digitalbook.book.modal.Book;
import com.digitalbook.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int createBookByAuthor(Book book) throws SQLException, RunTimeExceptionMessage {
		String SqlQuery = "INSERT INTO Book (booktitle,bookcode,authorid,price,category,publisher,logo,audiourl,content,isactive,updatedon,createdby,updatedby,createdon) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		LocalDateTime dateTime = LocalDateTime.now();
		String createdon = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss"));

		int result = jdbctemplate.update(SqlQuery, book.getBooktitle(), book.getBookcode(), book.getAuthorid(),
				book.getPrice(), book.getCategory(), book.getPublisher(), book.getLogo(), book.getAudiourl(),
				book.getContent(), book.getIsactive(), book.getUpdatedon(), book.getCreatedby(), book.getUpdateby(),
				createdon);
		return result;
	}

	@Override
	public int exitsByAuthorBooktitle(Book book) throws SQLException {
		String selectQuery = "Select Count(*) from book where authorid =? and booktitle=?";
		int ExistBookCount = jdbctemplate.queryForObject(selectQuery,
				new Object[] { book.getAuthorid(), book.getBooktitle() }, Integer.class);
		return ExistBookCount;
	}

	@Override
	public int updateBlockBookByAuthor(Book book, int id, String updateOrBlock) throws SQLException {
		if (updateOrBlock.equals("update")) {
			String SqlQuery = "UPDATE Book SET bookcode= ? , updatedon=? where id=?";
			int result = jdbctemplate.update(SqlQuery, book.getBookcode(), book.getUpdatedon(), id);
			return result;
		}
		if (updateOrBlock.equals("block")) {
			String SqlQuery = "UPDATE Book SET isactive=?, updatedon=? where id=?";
			int result = jdbctemplate.update(SqlQuery, 0, book.getUpdatedon(), id);
			return result;
		}
		if (updateOrBlock.equals("unblock")) {
			String SqlQuery = "UPDATE Book SET isactive= ? , updatedon=? where id=?";
			int result = jdbctemplate.update(SqlQuery, 1, book.getUpdatedon(), id);
			return result;
		}
		return 0;
	}

	@Override
	public List<Book> searchBook(Map<String, String> searchKeys, String subscribeBooks, int userId)
			throws SQLException {

		String query = "SELECT * FROM BOOK B, BOOKSUBSCRIPTION BS WHERE BS.USERID=? AND BS.BOOKID=B.ID AND B.ISACTIVE=1";

		List<Book> book = jdbctemplate.query(query, new Object[] { userId }, new BeanPropertyRowMapper(Book.class));
		return book;
	}

	@Override
	public int subscriptionBook(int bookid, int userid) throws SQLException {
		String SqlQuery = "INSERT INTO booksubscription (userid, isactive, bookid)  values(?,?,?)";
		int result = jdbctemplate.update(SqlQuery, userid, 1, bookid);
		return result;
	}

}
