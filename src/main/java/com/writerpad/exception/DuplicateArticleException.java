package com.writerpad.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class DuplicateArticleException.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateArticleException extends RuntimeException {

	/**
	 * Instantiates a new duplicate article exception.
	 *
	 * @param exception the exception
	 */
	public DuplicateArticleException(String exception) {

		super(exception);
	}

}
