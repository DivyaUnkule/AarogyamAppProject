package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
public class Article extends BaseEntity{
	
	@Column(length = 255, nullable = false)
    private String title;

    @Column(nullable = false , columnDefinition = "TEXT")
    private String content;

   
	public String getTitle(){
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

	

	public Article(String title, String content) {
		
		this.title = title;
		this.content = content;
		
	}

	public Article() {
		super();
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", content=" + content + "]";
	}

	
	

}
