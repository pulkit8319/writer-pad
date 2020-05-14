package com.writerpad.repository;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.writerpad.model.Article;

/**
 * The Interface ArticleRepository.
 */
public interface ArticleRepository extends MongoRepository<Article, String> {

	/**
	 * Find tags.
	 *
	 * @return the stream
	 */
	@Query(value="{}",fields = "{tags:1,_id:0}")
	Stream<String> findTags();

}
