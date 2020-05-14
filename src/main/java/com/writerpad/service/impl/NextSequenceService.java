package com.writerpad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.writerpad.model.IdSequencer;

/**
 * The Class NextSequenceService.
 */
@Service
public class NextSequenceService {

	/** The mongo operations. */
	@Autowired
	private MongoOperations mongoOperations;

	/**
	 * Gets the next sequence num.
	 *
	 * @param seqName the seq name
	 * @return the next sequence num
	 */
	public int getNextSequenceNum(String seqName) {
		IdSequencer counter = mongoOperations.findAndModify(Query.query(Criteria.where("_id").is(seqName)),
				new Update().inc("sequenceNum", 1), FindAndModifyOptions.options().returnNew(true).upsert(true),
				IdSequencer.class);
		return counter.getSequenceNum();
	}

}
