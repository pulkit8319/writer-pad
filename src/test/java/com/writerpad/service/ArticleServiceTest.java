/*package com.writerpad.service;

import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.writerpad.model.Article;
import com.writerpad.repository.ArticleRepository;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

	@InjectMocks
	private ArticleService articleService;

	@Mock
	private ArticleRepository articleRepository;

	@Test
	void testSave() {
		articleService.save(new Article());
		Mockito.verify(articleRepository).save(ArgumentMatchers.any());
	}

	@Test
	public void testUpdateArticle() {
		Article article = new Article.Builder().withBody("body").withDescription("desc").withTitle("title").build();
		Mockito.when(articleRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(article));
		articleService.updateArticle(article,"1");
		Mockito.verify(articleRepository).findById(ArgumentMatchers.any());
		Mockito.verify(articleRepository).save(ArgumentMatchers.any());
		Mockito.verify(articleRepository).findAll();
		Mockito.verifyNoMoreInteractions(articleRepository);
	}
	
}
*/