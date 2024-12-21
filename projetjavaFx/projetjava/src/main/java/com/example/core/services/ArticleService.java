package com.example.core.services;

import java.util.List;

import com.example.core.data.entities.Article;
import com.example.core.repositories.list.interfaces.IArticleRepository;
import com.example.core.services.interfaces.IArticleService;

public class ArticleService implements IArticleService{
    
    private IArticleRepository articleRepository;

    public ArticleService(IArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    @Override
    public List<Article> ListfindArticleById(int id) {
        return articleRepository.ListArticleById(id);
    }
    @Override
    public List<Article> getAll() {
        return articleRepository.select();
    }
    @Override
    public void store(Article article) {
        articleRepository.insert(article);
    }

    @Override
    public Article findArticleById(int id){
        return articleRepository.ArticleById(id);
    }
    
}
