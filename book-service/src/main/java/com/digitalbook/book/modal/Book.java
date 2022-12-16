package com.digitalbook.book.modal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Title must not be null")
	@Size(max = 250)
	private String booktitle;

	@Size(max = 50)
	private String bookcode;

	private int authorid;

	@Min(value = 0L, message = "Negative price is not accaptable")
	private float price;

	@NotBlank(message = "Category must not be null")
	@Size(max = 200)
	private String category;

	@NotBlank(message = "Publisher must not be null")
	@Size(max = 200)
	private String publisher;

	private String logo;

	private String audiourl;

	private String content;

	@Min(1)
	private int isactive;

	private String updatedon;

	private int createdby;

	private int updatedby;

	private String createdon;

	private String publishdate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String getBookcode() {
		return bookcode;
	}

	public void setBookcode(String bookcode) {
		this.bookcode = bookcode;
	}

	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAudiourl() {
		return audiourl;
	}

	public void setAudiourl(String audiourl) {
		this.audiourl = audiourl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public String getUpdatedon() {
		LocalDateTime dateTime = LocalDateTime.now();
		updatedon = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss"));
		return updatedon;
	}

	public void setUpdatedon(String updatedon) {
		this.updatedon = updatedon;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public int getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	
	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	public Book(String booktitle, String bookcode, int authorid, float price, String category, String publisher,
			String logo, String audiourl, String content, int isactive, String updatedon, int createdby, int updatedby,
			String createdon, String publishdate) {
		super();
		this.booktitle = booktitle;
		this.bookcode = bookcode;
		this.authorid = authorid;
		this.price = price;
		this.category = category;
		this.publisher = publisher;
		this.logo = logo;
		this.audiourl = audiourl;
		this.content = content;
		this.isactive = isactive;
		this.updatedon = updatedon;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.createdon = createdon;
		this.publishdate = publishdate;
	}

	public Book() {

	}
}
