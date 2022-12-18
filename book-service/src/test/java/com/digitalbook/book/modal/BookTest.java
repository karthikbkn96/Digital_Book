package com.digitalbook.book.modal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class BookTest {

	private Logger log = LogManager.getLogger(this.getClass());

	@BeforeAll
	static void initAll() {
	}

	@BeforeEach
	void init() {
	}

	@Test
	@DisplayName("get Id")
	public void getId() {
		try {
			log.info("Starting execution of getId");
			Long expectedValue = null;

			Book book = new Book();
			Long actualValue = book.getId();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Id")
	public void setId() {
		try {
			log.info("Starting execution of setId");
			Long id = 0L;

			Book book = new Book();
			book.setId(id);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetId-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Category")
	public void getCategory() {
		try {
			log.info("Starting execution of getCategory");
			String expectedValue = null;

			Book book = new Book();
			String actualValue = book.getCategory();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Category")
	public void setCategory() {
		try {
			log.info("Starting execution of setCategory");
			String category = "";

			Book book = new Book();
			book.setCategory(category);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetCategory-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Publisher")
	public void getPublisher() {
		try {
			log.info("Starting execution of getPublisher");
			String expectedValue = null;

			Book book = new Book();
			String actualValue = book.getPublisher();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Publisher")
	public void setPublisher() {
		try {
			log.info("Starting execution of setPublisher");
			String publisher = "";

			Book book = new Book();
			book.setPublisher(publisher);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetPublisher-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Logo")
	public void getLogo() {
		try {
			log.info("Starting execution of getLogo");
			String expectedValue = null;

			Book book = new Book();
			String actualValue = book.getLogo();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Logo")
	public void setLogo() {
		try {
			log.info("Starting execution of setLogo");
			String logo = "";

			Book book = new Book();
			book.setLogo(logo);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetLogo-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Audiourl")
	public void getAudiourl() {
		try {
			log.info("Starting execution of getAudiourl");
			String expectedValue = null;

			Book book = new Book();
			String actualValue = book.getAudiourl();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Audiourl")
	public void setAudiourl() {
		try {
			log.info("Starting execution of setAudiourl");
			String audiourl = "";

			Book book = new Book();
			book.setAudiourl(audiourl);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetAudiourl-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Content")
	public void getContent() {
		try {
			log.info("Starting execution of getContent");
			String expectedValue = null;

			Book book = new Book();
			String actualValue = book.getContent();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Content")
	public void setContent() {
		try {
			log.info("Starting execution of setContent");
			String content = "";

			Book book = new Book();
			book.setContent(content);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetContent-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Booktitle")
	public void getBooktitle() {
		try {
			log.info("Starting execution of getBooktitle");
			String expectedValue = null;

			Book book = new Book();
			String actualValue = book.getBooktitle();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Isactive")
	public void getIsactive() {
		try {
			log.info("Starting execution of getIsactive");
			int expectedValue = 0;

			Book book = new Book();
			int actualValue = book.getIsactive();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Isactive")
	public void setIsactive() {
		try {
			log.info("Starting execution of setIsactive");
			int isactive = 0;

			Book book = new Book();
			book.setIsactive(isactive);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetIsactive-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Updatedon")
	public void getUpdatedon() {
		try {
			log.info("Starting execution of getUpdatedon");
			String expectedValue = null;

			Book book = new Book();
			String actualValue = book.getUpdatedon();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertNotEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Updatedon")
	public void setUpdatedon() {
		try {
			log.info("Starting execution of setUpdatedon");
			String updatedon = "";

			Book book = new Book();
			book.setUpdatedon(updatedon);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetUpdatedon-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Createdby")
	public void getCreatedby() {
		try {
			log.info("Starting execution of getCreatedby");
			int expectedValue = 0;

			Book book = new Book();
			int actualValue = book.getCreatedby();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Createdby")
	public void setCreatedby() {
		try {
			log.info("Starting execution of setCreatedby");
			int createdby = 0;

			Book book = new Book();
			book.setCreatedby(createdby);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetCreatedby-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Updatedby")
	public void getUpdatedby() {
		try {
			log.info("Starting execution of getUpdatedby");
			int expectedValue = 0;

			Book book = new Book();
			int actualValue = book.getUpdatedby();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Updatedby")
	public void setUpdatedby() {
		try {
			log.info("Starting execution of setUpdatedby");
			int updatedby = 0;

			Book book = new Book();
			book.setUpdatedby(updatedby);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetUpdatedby-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Createdon")
	public void getCreatedon() {
		try {
			log.info("Starting execution of getCreatedon");
			String expectedValue = null;

			Book book = new Book();
			String actualValue = book.getCreatedon();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Createdon")
	public void setCreatedon() {
		try {
			log.info("Starting execution of setCreatedon");
			String createdon = "";

			Book book = new Book();
			book.setCreatedon(createdon);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetCreatedon-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Booktitle")
	public void setBooktitle() {
		try {
			log.info("Starting execution of setBooktitle");
			String booktitle = "";

			Book book = new Book();
			book.setBooktitle(booktitle);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetBooktitle-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Publishdate")
	public void getPublishdate() {
		try {
			log.info("Starting execution of getPublishdate");
			String expectedValue = null;

			Book book = new Book();
			String actualValue = book.getPublishdate();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Publishdate")
	public void setPublishdate() {
		try {
			log.info("Starting execution of setPublishdate");
			String publishdate = "";

			Book book = new Book();
			book.setPublishdate(publishdate);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetPublishdate-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Bookcode")
	public void getBookcode() {
		try {
			log.info("Starting execution of getBookcode");
			String expectedValue = null;

			Book book = new Book();
			String actualValue = book.getBookcode();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Bookcode")
	public void setBookcode() {
		try {
			log.info("Starting execution of setBookcode");
			String bookcode = "";

			Book book = new Book();
			book.setBookcode(bookcode);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetBookcode-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Authorid")
	public void getAuthorid() {
		try {
			log.info("Starting execution of getAuthorid");
			int expectedValue = 0;

			Book book = new Book();
			int actualValue = book.getAuthorid();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Authorid")
	public void setAuthorid() {
		try {
			log.info("Starting execution of setAuthorid");
			int authorid = 0;

			Book book = new Book();
			book.setAuthorid(authorid);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetAuthorid-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("get Price")
	public void getPrice() {
		try {
			log.info("Starting execution of getPrice");
			float expectedValue = 0;

			Book book = new Book();
			float actualValue = book.getPrice();
			log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
			Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@Test
	@DisplayName("set Price")
	public void setPrice() {
		try {
			log.info("Starting execution of setPrice");
			float price = 0;

			Book book = new Book();
			book.setPrice(price);
			Assertions.assertTrue(true);
		} catch (Exception exception) {
			log.error("Exception in execution ofsetPrice-" + exception, exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}
	
	@Test
	public void book() {
		Book book = new Book("", "", 0, 0F, "", "","","", "", 0, "", 0, 0,"","");
		Book book2 = new Book("", "", 0, 0F, "", "","","", "", 0, "", 0, 0,"","");
		Assertions.assertEquals(book,book2);
	}
}
