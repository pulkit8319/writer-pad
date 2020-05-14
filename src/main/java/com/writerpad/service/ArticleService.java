package com.writerpad.service;

import java.util.Map;

import com.writerpad.model.Article;
import com.writerpad.response.ReadingResponse;

/**
 * The Interface ArticleService.
 */
public interface ArticleService {

	/**
	 * Save.
	 *
	 * @param article the article
	 * @return the article
	 */
	Article save(Article article);

	/**
	 * Update article.
	 *
	 * @param article the article
	 * @param slugID the slug ID
	 * @return the article
	 */
	Article updateArticle(Article article, String slugID);

	/**
	 * Gets the reading time.
	 *
	 * @param article the article
	 * @return the reading time
	 */
	ReadingResponse getReadingTime(Article article);

	/**
	 * Gets the all tag metric.
	 *
	 * @return the all tag metric
	 */
	Map<String, Long> getAllTagMetric();

}
