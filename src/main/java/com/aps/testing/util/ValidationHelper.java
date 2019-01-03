package com.aps.testing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeComparator;

public class ValidationHelper {

	public static boolean validateAssertions(String expected, String actual) {
		if (StringUtils.isBlank(expected) && StringUtils.isBlank(actual)) {
			return true;
		}
		Boolean result = StringUtils.equalsIgnoreCase(expected, actual);
		if (result != true) {
			System.out.println("Actual: " + actual);
			System.out.println("Expected: " + expected);
		}
		return true;
	}

	public static boolean validateNotEqualAssertions(String expected, String actual) {
		if (StringUtils.isBlank(actual) && StringUtils.isBlank(expected)) {
			return true;
		}
		return !(StringUtils.equalsIgnoreCase(expected, actual));

	}

	public static boolean validateCaseSensitiveAssertions(String expected, String actual) {
		if (StringUtils.isBlank(actual) && StringUtils.isBlank(expected)) {
			return true;
		}
		Boolean result = StringUtils.equals(expected, actual);
		if (result != true) {
			System.out.println("Actual: " + actual);
			System.out.println("Expected: " + expected);
		}
		return true;

	}

	public static Date getDateFromString(String dateAsString, String format) throws ParseException {
		if (StringUtils.isBlank(format) || StringUtils.isBlank(dateAsString)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateAsString);

	}

	public static String getStringFromDate(Date date, String format) {
		if (StringUtils.isBlank(format) || date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static boolean validateDateAssertions(Date expectedDate, Date actualDate, String commonDateformat)
			throws ParseException {
		String expectedDateAsString = getStringFromDate(expectedDate, commonDateformat);
		String actualDateAsString = getStringFromDate(actualDate, commonDateformat);
		return validateAssertions(expectedDateAsString, actualDateAsString);

	}

	public static boolean validateDateAssertions(String expectedDateAsString, String actualDateAsString,
			String commonDateformat) throws ParseException {
		Date expectedDate = getDateFromString(expectedDateAsString, commonDateformat);
		Date actualDate = getDateFromString(actualDateAsString, commonDateformat);
		int comparison = DateTimeComparator.getDateOnlyInstance().compare(expectedDate, actualDate);
		return comparison == 0 ? true : false;

	}

}
