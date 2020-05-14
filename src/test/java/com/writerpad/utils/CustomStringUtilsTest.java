package com.writerpad.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.writerpad.util.CustomStringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomStringUtilsTest.
 */
public class CustomStringUtilsTest {
	
	/**
	 * Should create slug for a given title.
	 */
	@Test
    void should_create_slug_for_a_given_title() {
        String input = "test custom string util";
        String output = CustomStringUtils.slugify(input);
        Assertions.assertThat(output).isEqualTo("test-custom-string-util");
    }

}
