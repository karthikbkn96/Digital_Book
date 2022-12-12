package com.digitalbook.book.repository;

public class SqlQueries {

	public final static String createBookByAuthor = "INSERT INTO BOOK (BOOKTITLE,BOOKCODE,AUTHORID,PRICE,CATEGORY,PUBLISHER,LOGO,AUDIOURL,CONTENT,ISACTIVE,UPDATEDON,CREATEDBY,UPDATEDBY,CREATEDON) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public final static String exitsByAuthorBooktitle = "SELECT COUNT(*) FROM BOOK WHERE AUTHORID =? AND BOOKTITLE=?";
	
	public final static String updateBook = "UPDATE BOOK SET BOOKCODE= ? , UPDATEDON=? WHERE ID=?";
	
	public final static String blockBook = "UPDATE BOOK SET ISACTIVE=?, UPDATEDON=? WHERE ID=?";
	
	public final static String unBlockBook = "UPDATE BOOK SET ISACTIVE= ? , UPDATEDON=? WHERE ID=?";
	
	public final static String searchBook = "SELECT * FROM BOOK B, BOOKSUBSCRIPTION BS WHERE BS.USERID=? AND BS.BOOKID=B.ID AND B.ISACTIVE=1";
	
	public final static String subscriptionBook = "INSERT INTO BOOKSUBSCRIPTION (USERID, ISACTIVE, BOOKID)  VALUES(?,?,?)";
	
	public final static String authorBooks = "SELECT * FROM BOOK WHERE Authorid=? ";
	
	public final static String getBookById = "SELECT * FROM BOOK WHERE id=?";
}
