package com.writerpad.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.writerpad.exception.ArticleNotFoundException;
import com.writerpad.exception.DuplicateArticleException;
import com.writerpad.model.Article;
import com.writerpad.repository.ArticleRepository;
import com.writerpad.response.ReadingResponse;
import com.writerpad.response.TimeToRead;
import com.writerpad.service.ArticleService;

import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;

/**
 * The Class ArticleServiceImpl.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	/** The similarity factor. */
	@Value("${similarity.factor}")
	double similarityFactor;

	/** The avg reading speed. */
	@Value("${speed.avg.words.perminute}")
	int avgReadingSpeed;

	/** The article repository. */
	@Autowired
	private ArticleRepository articleRepository;

	/* (non-Javadoc)
	 * @see com.writerpad.service.ArticleService#save(com.writerpad.model.Article)
	 */
	@Override
	public Article save(Article article) {
		List<String> savedArticles = articleRepository.findAll().parallelStream().map(Article::getBody)
				.collect(Collectors.toList());
		if (isSimilar(article.getBody(), savedArticles)) {
			throw new DuplicateArticleException("Article already exists");
		}
		return this.articleRepository.save(article);
	}

	/**
	 * Checks if is similar.
	 *
	 * @param body the body
	 * @param savedArticles the saved articles
	 * @return true, if is similar
	 */
	private boolean isSimilar(String body, List<String> savedArticles) {
		SimilarityStrategy strategy = new JaroWinklerStrategy();
		StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
		return savedArticles.parallelStream().anyMatch(e -> service.score(body, e) > similarityFactor);
	}

	/* (non-Javadoc)
	 * @see com.writerpad.service.ArticleService#updateArticle(com.writerpad.model.Article, java.lang.String)
	 */
	@Override
	public Article updateArticle(Article newArticle, String slugID) {
		Article oldArticle = articleRepository.findById(slugID).orElseThrow(ArticleNotFoundException::new);
		List<String> savedArticles = articleRepository.findAll().parallelStream().map(Article::getBody)
				.collect(Collectors.toList());
		if (null != newArticle.getBody() && isSimilar(newArticle.getBody(), savedArticles)) {
			throw new DuplicateArticleException("Article already exists");
		}
		Article updateArticle = oldArticle.update(newArticle);
		return this.articleRepository.save(updateArticle);
	}

	/* (non-Javadoc)
	 * @see com.writerpad.service.ArticleService#getReadingTime(com.writerpad.model.Article)
	 */
	@Override
	public ReadingResponse getReadingTime(Article article) {
		TimeToRead timeToRead = readingTime(article.getBody(), avgReadingSpeed);
		String articleId = article.getId();
		return new ReadingResponse(articleId, timeToRead);
	}

	/**
	 * Reading time.
	 *
	 * @param content the content
	 * @param avgReadingSpeed the avg reading speed
	 * @return the time to read
	 */
	private TimeToRead readingTime(String content, int avgReadingSpeed) {
		TimeToRead timeToread = new TimeToRead();
		int totalWords = content.split("\\s+").length;
		timeToread.setMins(totalWords / avgReadingSpeed);
		double timeInSeconds = (double) (totalWords % avgReadingSpeed) / avgReadingSpeed * 60;
		timeToread.setSec((int) timeInSeconds);
		return timeToread;
	}

	/* (non-Javadoc)
	 * @see com.writerpad.service.ArticleService#getAllTagMetric()
	 */
	@Override
	public Map<String, Long> getAllTagMetric() {
		Stream<String> tags = articleRepository.findTags();
		return tags.collect(Collectors.groupingBy(e -> e, Collectors.counting()));
	}

}
