package com.app.dto;



public class ArticleDTO {
	private Long id;
	private String title;
	private String content;
	 // Name of the role associated with the article

	// Getters and setters
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArticleDTO( String title, String content) {
		
		this.title = title;
		this.content = content;
		
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArticleDTO() {
		super();
	}

	@Override
	public String toString() {
		return "ArticleDTO [title=" + title + ", content=" + content + "]";
	}
	
	
	
}
