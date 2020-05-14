/*package com.writerpad.controller;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writerpad.model.Article;
import com.writerpad.repository.ArticleRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ArticleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@AfterAll
	void tearDown() {
		articleRepository.deleteAll();
	}

	Function<Article, String> slugIdGenerator = (article) -> String.format("%s_%s", article.getSlug(), article.getId());

	@Test
	void testGetAllArticles() throws Exception {
		Article article1 = new Article.Builder().withDescription("description1").withBody("body1").withTitle("title1")
				.build();
		Article article2 = new Article.Builder().withDescription("description2").withBody("body2").withTitle("title2")
				.build();
		Article article3 = new Article.Builder().withDescription("description2").withBody("body2").withTitle("title2")
				.build();
		articleRepository.saveAll(Arrays.asList(article1, article2, article3));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/articles").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
	}

	@Test
	void testGetArticleByID() throws Exception {
		Article article = new Article.Builder().withDescription("description").withBody("body").withTitle("title")
				.build();
		Article saved = articleRepository.save(article);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/articles/{slug_id}", slugIdGenerator.apply(saved))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.body").value("body"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description"));
	}

	@Test
	void testAddArticleStatus201() throws Exception {

		Article article = new Article.Builder().withTitle("title").withBody("body").withDescription("description")
				.build();
		String json = objectMapper.writeValueAsString(article);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/articles").accept(MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testAddArticleStatus400() throws Exception {

		Article article = new Article.Builder().withTitle("").withDescription("description").withBody("body").build();
		String json = objectMapper.writeValueAsString(article);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/articles").accept(MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void testUpdateArticleStatus200() throws Exception {

		Article updateArticle = new Article.Builder().withBody(" body").withTitle("title")
				.withDescription("description").build();
		String json = objectMapper.writeValueAsString(updateArticle);
		Article article = new Article.Builder().withBody("b1234").withDescription("desc123").withTitle("t1234").build();
		Article saved = articleRepository.save(article);
		this.mockMvc
				.perform(MockMvcRequestBuilders.patch("/api/articles/{id}", slugIdGenerator.apply(article))
						.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt", CoreMatchers.not(saved.getUpdatedAt())));
	}

	@Test
	public void testDeleteArticle() throws Exception {
		Article article = new Article.Builder().withBody("body").withDescription("description").withTitle("title")
				.build();
		Article saved = articleRepository.save(article);
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/api/articles/{id}", slugIdGenerator.apply(saved))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	public void testTimeToReadArticle() throws Exception {
		String body = IntStream.range(1, 400).mapToObj(String::valueOf).collect(Collectors.joining(" "));
		Article article = new Article.Builder().withBody(body).withDescription("description").withTitle("title")
				.build();
		Article savedArticle = articleRepository.save(article);
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.get("/api/articles/{slug_id}/timetoread", slugIdGenerator.apply(savedArticle))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.slugId").value(slugIdGenerator.apply(savedArticle)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.readingTime").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.readingTime.mins").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.readingTime.seconds").value(46));
	}

	@Test
	void testGet() throws Exception {

		Article article1 = new Article.Builder().withBody("body1").withTitle("title1").withDescription("desc1")
				.withTags(Arrays.asList("java")).build();
		Article article2 = new Article.Builder().withBody("body2").withTitle("title2").withDescription("desc2")
				.withTags(Arrays.asList("python", "Java", "jAVa")).build();
		articleRepository.saveAll(Arrays.asList(article1, article2));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/articles/tags").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*].tag").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].tag").value("python"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].occurence").value("3"));
	}
}
*/