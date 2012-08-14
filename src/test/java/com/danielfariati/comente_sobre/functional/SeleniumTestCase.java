package com.danielfariati.comente_sobre.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleniumException;

public class SeleniumTestCase {

	private static DefaultSelenium selenium;

	private static final String BASE_URL = "http://localhost:8080/comente-sobre/";

	@BeforeClass
	public static void setupClass() throws Exception {
		SeleniumHelper.startSeleniumServer();

        selenium = new DefaultSelenium("localhost", 4444, "*firefox", BASE_URL);
        selenium.start();
    }

	@AfterClass
	public static void tearDownClass() throws Exception {
        SeleniumHelper.stopSeleniumServer(selenium);
    }

	@Test
	public void runTestsInSequence() {
		testTopicList();
		testSaveTopicWithoutRequiredFields();
		testSuggestSubjectURL();
		testRedirectAfterSaveTopic();
		testSubmitButtonShouldNotBeClickableUntilAllRequiredFieldsAreFilled();
		testUpdateCharCountWhenTypingMessage();
		testCssColorChangeOn10CharLeft();
		testDisableSubmitButtonIfCharLeftIsNegative();
		testSaveCommentWithoutRequiredFields();
		testSaveInvalidEmailComment();
		testSaveValidComment();
		testTopicListAfterSavingTopic();
	}

	private void testTopicList() {
		selenium.open(BASE_URL);
		selenium.waitForPageToLoad("2000");

		selenium.isElementPresent("table");

		selenium.click("submit");
		selenium.waitForPageToLoad("2000");

		assertEquals(BASE_URL + "topic/new", selenium.getLocation());
	}

	private void testSaveTopicWithoutRequiredFields() {
		selenium.open(BASE_URL + "topic/new");
		selenium.waitForPageToLoad("2000");

		selenium.click("id=create");
		selenium.waitForPageToLoad("2000");

		assertEquals(BASE_URL + "topic/new", selenium.getLocation());
		assertTrue(selenium.isTextPresent("O campo assunto deve ser preenchido!"));
		assertTrue(selenium.isTextPresent("O campo URL deve ser preenchido!"));
	}

	private void testSuggestSubjectURL() {
		selenium.open(BASE_URL + "topic/new");
		selenium.waitForPageToLoad("2000");

		selenium.type("id=subject-input", "Metodologias Ágeis");
		selenium.typeKeys("id=subject-input", "Metodologias Ágeis");

		assertEquals("metodologias-ageis", selenium.getValue("id=subject-url-input"));
	}

	private void testRedirectAfterSaveTopic() {
		selenium.open(BASE_URL + "topic/new");
		selenium.waitForPageToLoad("2000");

		selenium.type("id=subject-input", "Metodologias Ágeis");
		selenium.typeKeys("id=subject-input", "Metodologias Ágeis");

		selenium.type("id=subject-url-input", "metodologias-ageis");
		selenium.typeKeys("id=subject-url-input", "metodologias-ageis");

		selenium.click("id=create");
		selenium.waitForPageToLoad("2000");

		assertEquals(BASE_URL + "metodologias-ageis", selenium.getLocation());
	}

	private void testSubmitButtonShouldNotBeClickableUntilAllRequiredFieldsAreFilled() {
		selenium.open(BASE_URL + "metodologias-ageis");
		selenium.waitForPageToLoad("2000");

		assertFalse(selenium.isVisible("new-comment-wrapper"));

		selenium.click("id=add-comment-btn");
		assertTrue(selenium.isVisible("new-comment-wrapper"));

		assertEquals("true", selenium.getAttribute("id=submit@disabled"));

		selenium.type("id=email-input", "email@mail.com");
		selenium.typeKeys("id=email-input", "email@mail.com");

		assertEquals("true", selenium.getAttribute("css=input[id='submit']@disabled"));

		selenium.type("id=message-area", "message");
		selenium.typeKeys("id=message-area", "message");

		try {
			selenium.getAttribute("css=input[id='submit']@disabled");
			fail("exception wanted but not throwed");
		} catch(SeleniumException e) {
			// do nothing
		}
	}

	private void testUpdateCharCountWhenTypingMessage() {
		selenium.open(BASE_URL + "metodologias-ageis");
		selenium.waitForPageToLoad("2000");

		assertEquals("2000", selenium.getText("id=char-left"));

		selenium.type("id=message-area", "12345");
		selenium.typeKeys("id=message-area", "12345");

		assertEquals("1995", selenium.getText("id=char-left"));
	}

	private void testCssColorChangeOn10CharLeft() {
		selenium.open(BASE_URL + "metodologias-ageis");
		selenium.waitForPageToLoad("2000");

		assertEquals("2000", selenium.getText("id=char-left"));
		assertEquals("color: black;", selenium.getAttribute("css=span[id='char-left']@style"));

		selenium.type("id=message-area", "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
		selenium.typeKeys("id=message-area", "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");

		assertEquals("10", selenium.getText("id=char-left"));
		assertEquals("color: red;", selenium.getAttribute("css=span[id='char-left']@style"));
	}

	private void testDisableSubmitButtonIfCharLeftIsNegative() {
		selenium.open(BASE_URL + "metodologias-ageis");
		selenium.waitForPageToLoad("2000");

		selenium.type("id=email-input", "mail@mail.com");
		selenium.typeKeys("id=email-input", "mail@mail.com");

		selenium.type("id=message-area", "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		selenium.typeKeys("id=message-area", "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

		assertEquals("-1", selenium.getText("id=char-left"));
		assertEquals("true", selenium.getAttribute("css=input[id='submit']@disabled"));
	}

	private void testSaveCommentWithoutRequiredFields() {
		selenium.open(BASE_URL + "metodologias-ageis");
		selenium.waitForPageToLoad("2000");

		selenium.runScript("document.getElementById('submit').disabled = false;");
		selenium.click("id=submit");
		selenium.waitForPageToLoad("2000");

		assertTrue(selenium.isTextPresent("O campo e-mail deve ser preenchido!"));
		assertTrue(selenium.isTextPresent("O campo mensagem deve ser preenchido!"));
		assertTrue(selenium.isTextPresent("Nenhum comentário encontrado!"));
	}

	private void testSaveInvalidEmailComment() {
		selenium.open(BASE_URL + "metodologias-ageis");
		selenium.waitForPageToLoad("2000");

		selenium.type("id=email-input", "mail");
		selenium.typeKeys("id=email-input", "mail");

		selenium.type("id=message-area", "message");
		selenium.typeKeys("id=message-area", "message");

		selenium.click("id=submit");
		selenium.waitForPageToLoad("2000");

		assertTrue(selenium.isTextPresent("Por favor, informe um e-mail válido."));
		assertTrue(selenium.isTextPresent("Nenhum comentário encontrado!"));
	}

	private void testSaveValidComment() {
		selenium.open(BASE_URL + "metodologias-ageis");
		selenium.waitForPageToLoad("2000");

		selenium.type("id=email-input", "mail@mail.com");
		selenium.typeKeys("id=email-input", "mail@mail.com");

		selenium.type("id=message-area", "message");
		selenium.typeKeys("id=message-area", "message");

		selenium.click("id=submit");
		selenium.waitForPageToLoad("2000");

		assertFalse(selenium.isTextPresent("Nenhum comentário encontrado!"));
		assertTrue(selenium.isTextPresent("mail@mail.com"));
		assertTrue(selenium.isTextPresent("message"));
	}

	private void testTopicListAfterSavingTopic() {
		selenium.open(BASE_URL);
		selenium.waitForPageToLoad("2000");

		assertFalse(selenium.isTextPresent("Nenhum tópico encontrado!"));
		assertEquals("Metodologias Ágeis", selenium.getText("class=subject"));
		assertEquals("metodologias-ageis", selenium.getText("class=url"));
		assertEquals("/comente-sobre/metodologias-ageis", selenium.getAttribute("class=link-inner@href"));
	}

}
