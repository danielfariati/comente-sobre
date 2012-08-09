package com.danielfariati.comente_sobre.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	private Utils() {
	}

	public static boolean validateEmail(String email) {
		String mailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern mailPattern = Pattern.compile(mailRegex);
		Matcher matcher = mailPattern.matcher(email);

		return matcher.matches();
	}

	public static boolean validateUrl(String url) {
		String urlRegex = "^([A-Za-z0-9-]+)$";
		Pattern urlPattern = Pattern.compile(urlRegex);
		Matcher matcher = urlPattern.matcher(url);

	    return matcher.matches();
	}

}
