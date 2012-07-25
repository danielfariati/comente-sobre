package com.danielfariati.comente_sobre.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import com.danielfariati.comente_sobre.utils.Utils;

public class UserTest {

	@Test
	public void shouldEncryptPassword() {
		String unencryptedPassword = "password";

		User user = new User();
		user.setPassword(unencryptedPassword);

		String encryptedPassword = user.getPassword();

		assertNotSame(encryptedPassword, unencryptedPassword);
	}

	@Test
	public void shouldAddSaltToPassword() {
		String salt = "comente-sobre";
		String unencryptedPassword = "password";

		User user = new User();
		user.setPassword(unencryptedPassword);

		String encryptedPassword = user.getPassword();

		assertNotSame(Utils.encrypt(unencryptedPassword), encryptedPassword);
		assertEquals(Utils.encrypt(unencryptedPassword + salt), encryptedPassword);
	}

}
