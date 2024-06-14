package com.biblioteca.converter;

public class MathConverter {

	// Will return 0 if the string given isn't numeric instead of throwing an error;
	public static Long convertStringToLong(String string) {
		if(isLong(string)) {
			return Long.parseLong(string);
		} else {
			return 0L;
		}
	}
	
	
	public static Boolean isLong(String string) {
		try {
			Long.parseLong(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
}
