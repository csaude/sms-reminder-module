package org.openmrs.module.smsreminder.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public static boolean cellNumberValidator(String cellNumber) {
		return isValid(cellNumber);
	}

	public static boolean isValid(String s) {

		Pattern p = Pattern.compile("^\\d{12}$");
		Matcher m = p.matcher(s);
		return (m.matches());
	}

}
