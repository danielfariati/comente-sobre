package com.danielfariati.comente_sobre.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void shouldReturnFalseIfEmailDoesntHaveAt() {
		String email = "mail.com";

		Boolean isValid = Utils.validateEmail(email);

		assertFalse(isValid);
	}

	@Test
	public void shouldReturnFalseIfEmailStartsWithDot() {
		String email = ".mail@mail.com";

		Boolean isValid = Utils.validateEmail(email);

		assertFalse(isValid);
	}

	@Test
	public void shouldReturnFalseIfEmailHaveSpecialChar() {
		String email = "mail()@mail.com";

		Boolean isValid = Utils.validateEmail(email);

		assertFalse(isValid);
	}

	@Test
	public void shouldReturnFalseIfEmailTldStartsWithDot() {
		String email = "mail@.com";

		Boolean isValid = Utils.validateEmail(email);

		assertFalse(isValid);
	}

	@Test
	public void shouldReturnFalseIfEmailHaveDoubleDots() {
		String email = "m..ail@mail.com";

		Boolean isValid = Utils.validateEmail(email);

		assertFalse(isValid);
	}

	@Test
	public void shouldReturnFalseIfEmailTldHaveDoubleDots() {
		String email = "mail@mail..com";

		Boolean isValid = Utils.validateEmail(email);

		assertFalse(isValid);
	}

	@Test
	public void shouldReturnFalseIfEmailEndsWithDot() {
		String email = "mail.@mail.com";

		Boolean isValid = Utils.validateEmail(email);

		assertFalse(isValid);
	}

	@Test
	public void shouldReturnFalseIfEmailHaveDoubleAt() {
		String email = "mail@mail@mail.com";

		Boolean isValid = Utils.validateEmail(email);

		assertFalse(isValid);
	}

	@Test
	public void shouldReturnFalseIfEmailTldDoesntHaveTwoCharacters() {
		String email = "mail@mail.c";

		Boolean isValid = Utils.validateEmail(email);

		assertFalse(isValid);
	}

	@Test
	public void shouldReturnTrueIfEmailIsValid() {
		String email = "mail@mail.com";

		Boolean isValid = Utils.validateEmail(email);

		assertTrue(isValid);
	}

}
