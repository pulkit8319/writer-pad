package com.writerpad.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.writerpad.exception.ArticleNotFoundException;
import com.writerpad.model.Article;
import com.writerpad.repository.ArticleRepository;
import com.writerpad.request.ProcessArticleRequest;
import com.writerpad.response.ReadingResponse;
import com.writerpad.response.TagResponse;
import com.writerpad.service.ArticleService;
import com.writerpad.service.impl.NextSequenceService;

/**
 * The Class ArticleController.
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
	
	/** The next sequence service. */
	@Autowired
	private NextSequenceService nextSequenceService;

	/** The article service. */
	@Autowired
	private ArticleService articleService;

	/** The article repository. */
	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * Gets the all.
	 *
	 * @param pageable the pageable
	 * @return the all
	 */
	@GetMapping
	public ResponseEntity<List<Article>> getAll(Pageable pageable) {
		Page<Article> articles = articleRepository.findAll(pageable);
		if (!articles.hasContent()) {
			ResponseEntity.noContent().build();
		}
		return new ResponseEntity<>(articles.getContent(), HttpStatus.OK);
	}

	/**
	 * Gets the article by id.
	 *
	 * @param slugID the slug ID
	 * @return the article by id
	 */
	@GetMapping(path = "/{slug_uuid}")
	public ResponseEntity<Article> getArticleById(@PathVariable(value = "slug_uuid") String slugID) {
		Article article = articleRepository.findById(slugID).orElseThrow(ArticleNotFoundException::new);
		return new ResponseEntity<>(article, HttpStatus.OK);
	}

	/**
	 * Creates the article.
	 *
	 * @param articleRequest the article request
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<Article> createArticle(@Valid @RequestBody ProcessArticleRequest articleRequest) {
		Article article = articleRequest.getArticle();
		article.setId(String.valueOf(nextSequenceService.getNextSequenceNum("customSequence")));
		Article savedArticle = articleService.save(article);
		return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
	}

	/**
	 * Update article.
	 *
	 * @param slugID the slug ID
	 * @param articleRequest the article request
	 * @return the response entity
	 */
	@PatchMapping(path = "/{slug_uuid}")
	public ResponseEntity<Article> updateArticle(@PathVariable(value = "slug_uuid") String slugID,
			@RequestBody ProcessArticleRequest articleRequest) {
		Article article = articleRequest.getArticle();
		Article newArticle = articleService.updateArticle(article, slugID);
		return new ResponseEntity<>(newArticle, HttpStatus.OK);
	}

	/**
	 * Delete article.
	 *
	 * @param slugID the slug ID
	 * @return the response entity
	 */
	@DeleteMapping(path = "/{slug_uuid}")
	public ResponseEntity<Void> deleteArticle(@PathVariable(value = "slug_uuid") String slugID) {
		articleRepository.deleteById(slugID);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Time to read article.
	 *
	 * @param slugID the slug ID
	 * @return the response entity
	 */
	@GetMapping(path = "/{slug_uuid}/timetoread")
	public ResponseEntity<ReadingResponse> timeToReadArticle(@PathVariable(value = "slug_uuid") String slugID) {
		Article article = articleRepository.findById(slugID).orElseThrow(ArticleNotFoundException::new);
		ReadingResponse resdingResponse = articleService.getReadingTime(article);
		return new ResponseEntity<>(resdingResponse, HttpStatus.OK);
	}

	/**
	 * Gets the tag metrics.
	 *
	 * @return the tag metrics
	 */
	@GetMapping(path = "/tags")
	public ResponseEntity<List<TagResponse>> getTagMetrics() {
		 Map<String, Long> tagMap = articleService.getAllTagMetric();
		 if(tagMap.isEmpty()){
			 ResponseEntity.noContent().build();
		 }
		 List<TagResponse> listOfTagResponse = tagMap.entrySet().stream()
	                .map(e -> new TagResponse(e.getKey(), e.getValue()))
	                .collect(Collectors.toList());
		 return new ResponseEntity<>(listOfTagResponse,HttpStatus.OK);
	}
}
