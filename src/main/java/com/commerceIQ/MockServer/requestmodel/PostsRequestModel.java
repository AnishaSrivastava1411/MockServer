package com.commerceIQ.MockServer.requestmodel;

public class PostsRequestModel {
	private Long id;
	private String title;
	private String author;
	private Long views;
	private Long reviews;

	public PostsRequestModel(Long id, String title, String author, Long views, Long reviews) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.views = views;
		this.reviews = reviews;
	}

	public PostsRequestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public Long getReviews() {
		return reviews;
	}

	public void setReviews(Long reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "PostsRequestModel [id=" + id + ", title=" + title + ", author=" + author + ", views=" + views
				+ ", reviews=" + reviews + "]";
	}

}