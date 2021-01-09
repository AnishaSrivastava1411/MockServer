package com.commerceIQ.MockServer.requestmodel;

public class AuthorRequestModel {

	private Long id;
	private String first_Name;
	private String last_Name;
	private Long posts;

	public AuthorRequestModel() {
		super();

	}

	public AuthorRequestModel(Long id, String first_Name, String last_Name, Long posts) {
		super();
		this.id = id;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.posts = posts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_Name() {
		return first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public Long getPosts() {
		return posts;
	}

	public void setPosts(Long posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "AuthorRequestModel [id=" + id + ", first_Name=" + first_Name + ", last_Name=" + last_Name + ", posts="
				+ posts + "]";
	}

}
