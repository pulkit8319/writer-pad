package com.writerpad.response;

/**
 * The Class ReadingResponse.
 */
public class ReadingResponse {

	/** The article id. */
	private String articleId;
	
	/** The time to read. */
	private TimeToRead timeToRead;

	/**
	 * Gets the article id.
	 *
	 * @return the article id
	 */
	public String getArticleId() {
		return articleId;
	}

	/**
	 * Gets the time to read.
	 *
	 * @return the time to read
	 */
	public TimeToRead getTimeToRead() {
		return timeToRead;
	}
	
	/**
	 * Instantiates a new reading response.
	 *
	 * @param articleId the article id
	 * @param timeToRead the time to read
	 */
	public ReadingResponse(String articleId,TimeToRead timeToRead) {
		this.articleId = articleId;
		this.timeToRead = timeToRead;
	}


}
