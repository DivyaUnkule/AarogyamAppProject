package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Article;

public interface ArticleRepo extends JpaRepository<Article, Long> {

}
