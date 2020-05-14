package com.writerpad.util;

/**
 * The Class CustomStringUtils.
 */
public class CustomStringUtils {
	
	/**
	 * Slugify.
	 *
	 * @param input the input
	 * @return the string
	 */
	public static String slugify(String input) {

        if (input == null) {
            throw new IllegalArgumentException("input can't be null");
        }
        input = input.trim().toLowerCase()
                .replaceAll("\\s\\s+", " ");
        return input.replaceAll("\\s", "-");
    }

}
