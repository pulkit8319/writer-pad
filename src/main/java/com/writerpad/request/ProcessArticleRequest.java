package com.writerpad.request;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.writerpad.model.Article;

/**
 * The Class ProcessArticleRequest.
 */
public class ProcessArticleRequest {

	/** The title. */
	@NotNull
	@NotEmpty
	private String title;
	
	/** The description. */
	@NotNull
	@NotEmpty
	private String description;
	
	/** The body. */
	@NotNull
	@NotEmpty
	private String body;
	
	/** The tags. */
	private List<String> tags;
	
	 /**
 	 * Instantiates a new process article request.
 	 */
 	public ProcessArticleRequest() {

	    }

	/**
	 * Instantiates a new process article request.
	 *
	 * @param builder the builder
	 */
	public ProcessArticleRequest(Builder builder) {
		title = builder.title;
		description = builder.description;
		body = builder.body;
		tags = builder.tags;
	}

	/**
	 * Gets the article.
	 *
	 * @return the article
	 */
	public Article getArticle() {
		return new Article.Builder().withBody(this.body).withTitle(this.title).withDescription(this.description)
				.withTags(this.tags == null ? new ArrayList<String>()
						: this.tags.stream().map(e -> e.replace(" ", "-").toLowerCase()).collect(Collectors.toList()))
				.build();
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
		this.tags = tags == null ? new ArrayList<String>() : tags;
	}

	/**
	 * The Class Builder.
	 */
	public static final class Builder {
		
		/** The title. */
		private String title;
		
		/** The description. */
		private String description;
		
		/** The body. */
		private String body;
		
		/** The tags. */
		private List<String> tags;

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
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
			this.tags = tags;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the process article request
		 */
		public ProcessArticleRequest build() {
			return new ProcessArticleRequest(this);
		}

	}
}
