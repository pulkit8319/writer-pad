package com.writerpad.response;

/**
 * The Class TagResponse.
 */
public class TagResponse {

	/** The tag. */
	private String tag;
	
	/** The occurrence. */
	private Long occurrence;

	/**
	 * Instantiates a new tag response.
	 *
	 * @param tag the tag
	 * @param occurence the occurence
	 */
	public TagResponse(String tag, Long occurence) {
		this.tag = tag;
		this.occurrence = occurence;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Gets the occurence.
	 *
	 * @return the occurence
	 */
	public Long getOccurence() {
		return occurrence;
	}

}
