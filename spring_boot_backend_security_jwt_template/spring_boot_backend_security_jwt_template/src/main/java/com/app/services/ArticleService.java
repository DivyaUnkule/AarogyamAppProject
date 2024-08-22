package com.app.services;

import java.util.List;

import com.app.dto.ArticleDTO;
import com.app.entities.Article;

public interface ArticleService {
	 Article createArticle(ArticleDTO articleDTO);
	    Article updateArticle(ArticleDTO articleDTO);
	    void deleteArticle(Long id);
	    List<Article> getAllArticles();
	    

}
