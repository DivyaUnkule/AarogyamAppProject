package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ArticleDTO;
import com.app.entities.Article;
import com.app.services.ArticleService;


@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@PostMapping("/admin/insertarticles")
	public ResponseEntity<Article> insertArticle(@RequestBody ArticleDTO articleDTO) {
		Article createdArticle = articleService.createArticle(articleDTO);
		return ResponseEntity.status(200).body(createdArticle);
	}

	@PutMapping("/admin/updatearticles")
	public ResponseEntity<Article> updateArticle(@RequestBody ArticleDTO articleDTO) {
		Article updatedArticle = articleService.updateArticle(articleDTO);
		return ResponseEntity.status(200).body(updatedArticle);
	}

	@DeleteMapping("/admin/deletearticle/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
		articleService.deleteArticle(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/admin/getallarticles")
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> articles = articleService.getAllArticles();
		return ResponseEntity.status(200).body(articles);
	}

	@GetMapping("/user/getALLArticles")
	public ResponseEntity<List<Article>> getALLArticlesForUser() {
		List<Article> articles = articleService.getAllArticles();
		return ResponseEntity.ok(articles);
	}

}
