package com.example.core.services.interfaces;

import java.util.List;

import com.example.core.data.entities.Article;

public interface IArticleService {
    List<Article> getAll();
    void store(Article article);
    List<Article> ListfindArticleById(int id);
    Article findArticleById(int id);
    
}
