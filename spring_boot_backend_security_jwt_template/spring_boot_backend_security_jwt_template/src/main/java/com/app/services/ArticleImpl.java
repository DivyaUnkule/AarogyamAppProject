package com.app.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ArticleDTO;
import com.app.entities.Article;

import com.app.repositories.ArticleRepo;


@Service
@Transactional
public class ArticleImpl implements ArticleService {

	@Autowired
	private ArticleRepo articleRepository;

	
	@Override
	public Article createArticle(ArticleDTO articleDTO) {
		Article article = new Article();
		article.setTitle(articleDTO.getTitle());
		article.setContent(articleDTO.getContent());

		return articleRepository.save(article);

	}

	@Override
	public Article updateArticle(ArticleDTO articleDTO) {
		Optional<Article> articleOptional = articleRepository.findById(articleDTO.getId());
		if (!articleOptional.isPresent()) {
			throw new RuntimeException("Article not found");
		}

		Article article = articleOptional.get();
		article.setTitle(articleDTO.getTitle());
		article.setContent(articleDTO.getContent());

		

		return articleRepository.save(article);

	}

	@Override
	public void deleteArticle(Long id) {
		articleRepository.deleteById(id);

	}

	@Override
	public List<Article> getAllArticles() {

		return articleRepository.findAll();
	}

	

}
