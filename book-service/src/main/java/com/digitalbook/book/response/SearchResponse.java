package com.digitalbook.book.response;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SearchResponse {

	private Long id;

	private String booktitle;

	private String category;

	private String publisher;

	private String logo;

	private float price;

	private String audiourl;

	private String content;

	private String updatedon;

	private int subscribeid;

	private String publishdate;

	private int publishdatevalid;

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

	public String getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(String updatedon) {
		this.updatedon = updatedon;
	}

	public int getSubscribeid() {
		return subscribeid;
	}

	public void setSubscribeid(int subscribeid) {
		this.subscribeid = subscribeid;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		if(null != publishdate || !publishdate.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = sdf.format(new Date());
			
			if(formattedDate.equals(publishdate)) {
				setPublishdatevalid(1);
			}
		}
		this.publishdate = publishdate;
	}

	public int getPublishdatevalid() {
		return publishdatevalid;
	}

	public void setPublishdatevalid(int publishdatevalid) {
		this.publishdatevalid = publishdatevalid;
	}

	public SearchResponse(Long id, String booktitle, String category, String publisher, String logo, float price,
			String audiourl, String content, String updatedon, String publishdate) {
		super();
		this.id = id;
		this.booktitle = booktitle;
		this.category = category;
		this.publisher = publisher;
		this.logo = logo;
		this.price = price;
		this.audiourl = audiourl;
		this.content = content;
		this.updatedon = updatedon;
		this.publishdate = publishdate;
	}

	
	public SearchResponse() {
		
	}
}
