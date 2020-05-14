package com.writerpad.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.writerpad.util.CustomStringUtils;

/**
 * The Class Article.
 */
@Document(collection = "articles")
public class Article {

	/** The id. */
	@Id
	private String id;
	
	/** The slug. */
	private String slug;
	
	/** The title. */
	private String title;
	
	/** The description. */
	private String description;
	
	/** The body. */
	private String body;
	
	/** The tags. */
	private List<String> tags;
	
	/** The created at. */
	@CreatedDate
	private Date createdAt;
	
	/** The updated at. */
	@LastModifiedDate
	private Date updatedAt;
	
	/** The favourited. */
	private boolean favourited;
	
	/** The favourite count. */
	private int favouriteCount;

	/**
	 * Instantiates a new article.
	 */
	public Article() {
	}

	/**
	 * Instantiates a new article.
	 *
	 * @param builder the builder
	 */
	public Article(Builder builder) {
		id = builder.id;
		if (null != this.title) {
			slug = CustomStringUtils.slugify(this.title);
		}
		body = builder.body;
		title = builder.title;
		description = builder.description;
		tags = builder.tags;
		favourited = builder.favourited;
		favouriteCount = builder.favouriteCount;
		createdAt = new Date();
		updatedAt = new Date();
	}

	/**
	 * Update.
	 *
	 * @param newArticle the new article
	 * @return the article
	 */
	public Article update(Article newArticle) {

		if (Objects.nonNull(newArticle.getTitle())) {
			this.title = newArticle.getTitle();
		}
		if (Objects.nonNull(newArticle.getBody())) {
			this.body = newArticle.getBody();
		}
		if (Objects.nonNull(newArticle.getDescription())) {
			this.description = newArticle.getDescription();
		}
		if (Objects.nonNull(newArticle.getTags()) && newArticle.getTags().size() > 0) {
			this.tags = newArticle.getTags();
		}
		this.updatedAt = new Date();
		return this;
	}

	/**
	 * The Class Builder.
	 */
	public static final class Builder {
		
		/** The id. */
		private String id;
		
		/** The title. */
		private String title;
		
		/** The description. */
		private String description;
		
		/** The body. */
		private String body;
		
		/** The tags. */
		private List<String> tags;
		
		/** The favourited. */
		private boolean favourited;
		
		/** The favourite count. */
		private int favouriteCount;

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
		}

		/**
		 * With id.
		 *
		 * @param id the id
		 * @return the builder
		 */
		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		/**
		 * With title.
		 *
		 * @param title the title
		 * @return the builder
		 */
		public Builder withTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 * With description.
		 *
		 * @param description the description
		 * @return the builder
		 */
		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		/**
		 * With body.
		 *
		 * @param body the body
		 * @return the builder
		 */
		public Builder withBody(String body) {
			this.body = body;
			return this;
		}

		/**
		 * With tags.
		 *
		 * @param tags the tags
		 * @return the builder
		 */
		public Builder withTags(List<String> tags) {
			this.tags = tags == null ? new ArrayList<String>()
					: tags.stream().map(CustomStringUtils::slugify).collect(Collectors.toList());
			return this;
		}

		/**
		 * With favourited.
		 *
		 * @param favourited the favourited
		 * @return the builder
		 */
		public Builder withFavourited(boolean favourited) {
			this.favourited = favourited;
			return this;
		}

		/**
		 * With favourite count.
		 *
		 * @param favouriteCount the favourite count
		 * @return the builder
		 */
		public Builder withFavouriteCount(int favouriteCount) {
			this.favouriteCount = favouriteCount;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the article
		 */
		public Article build() {
			return new Article(this);
		}
	}

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
	 * Gets the slug.
	 *
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * Sets the slug.
	 *
	 * @param slug the new slug
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Sets the body.
	 *
	 * @param body the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * Sets the tags.
	 *
	 * @param tags the new tags
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the updated at.
	 *
	 * @return the updated at
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Sets the updated at.
	 *
	 * @param updatedAt the new updated at
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * Checks if is favourited.
	 *
	 * @return true, if is favourited
	 */
	public boolean isFavourited() {
		return favourited;
	}

	/**
	 * Sets the favourited.
	 *
	 * @param favourited the new favourited
	 */
	public void setFavourited(boolean favourited) {
		this.favourited = favourited;
	}

	/**
	 * Gets the favourite count.
	 *
	 * @return the favourite count
	 */
	public int getFavouriteCount() {
		return favouriteCount;
	}

	/**
	 * Sets the favourite count.
	 *
	 * @param favouriteCount the new favourite count
	 */
	public void setFavouriteCount(int favouriteCount) {
		this.favouriteCount = favouriteCount;
	}

}
