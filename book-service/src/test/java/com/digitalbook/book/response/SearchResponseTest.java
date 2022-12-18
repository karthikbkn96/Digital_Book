package com.digitalbook.book.response;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

public class SearchResponseTest {

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}

	@BeforeAll
	static void initAll() {
	}

	@BeforeEach
	void init() {
	}

	@Test
	public void test() {
		SearchResponse SearchResponse = new SearchResponse();
		SearchResponse.setId(1L);
		SearchResponse.setSubscribeid(1);
		SearchResponse.setBooktitle("booktitle");
		SearchResponse.setCategory("category");
		SearchResponse.setContent("content");
		SearchResponse.setLogo("logo");
		SearchResponse.setPrice(11.22F);
		SearchResponse.setPublisher("publisher");
		SearchResponse.setAudiourl("publisher");
		SearchResponse.setUpdatedon("publisher");
		SearchResponse.setPublishdate("2022-11-22");
		SearchResponse.setPublishdatevalid(1);

		Assert.assertSame(1, SearchResponse.getPublishdatevalid());
		Assert.assertSame(1L, SearchResponse.getId());
		Assert.assertNotSame("20221122", SearchResponse.getPublishdate());
		Assert.assertSame("booktitle", SearchResponse.getBooktitle());
		Assert.assertSame("publisher", SearchResponse.getUpdatedon());
		Assert.assertSame("publisher", SearchResponse.getAudiourl());
		Assert.assertSame("category", SearchResponse.getCategory());
		Assert.assertSame("content", SearchResponse.getContent());
		Assert.assertSame("logo", SearchResponse.getLogo());
		Assert.assertNotSame(11.22F, SearchResponse.getPrice());
		Assert.assertSame("publisher", SearchResponse.getPublisher());
		Assert.assertSame(1, SearchResponse.getSubscribeid());

		SearchResponse SearchResponse1 = new SearchResponse(1L, "booktitle", "ategory", "publisher", "logo", 1F,
				"audiourl", "content", "updatedon", "publishdate");
		SearchResponse SearchResponse2 = new SearchResponse(1L, "booktitle", "ategory", "publisher", "logo", 1F,
				"audiourl", "content", "updatedon", "publishdate");
		Assert.assertNotSame(SearchResponse2, SearchResponse1);
	}
}
