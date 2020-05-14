package com.writerpad.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class IdSequencer.
 */
@Document(collection = "customsequences")
public class IdSequencer {

	/** The id. */
	@Id
	private String id;
	
	/** The sequence num. */
	private int sequenceNum;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the sequence num.
	 *
	 * @return the sequence num
	 */
	public int getSequenceNum() {
		return sequenceNum;
	}

	/**
	 * Sets the sequence num.
	 *
	 * @param sequenceNum the new sequence num
	 */
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

}
