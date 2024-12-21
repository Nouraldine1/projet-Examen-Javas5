package com.ism.repository.list.impl;

import com.ism.models.entities.Article;
import com.ism.repository.IRepository;
import java.util.ArrayList;
import java.util.List;

public class ArticleListRepository implements IRepository<Article> {
    private List<Article> articles = new ArrayList<>();
    private static long idCounter = 1L;

    @Override
    public Article findById(long userId) {
        return articles.stream().filter(a -> a.getId() == userId).findFirst().orElse(null);
    }
    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articles);
    }

    @Override
    public void save(Article article) {
        if (article.getId() == 0) {
            article.setId((int) idCounter++);
            articles.add(article);
        }
    }

    @Override
    public void update(Article article) {
        articles.removeIf(a -> a.getId() == article.getId());
        articles.add(article);
    }

    @Override
    public void delete(Long id) {
        articles.removeIf(a -> a.getId() == id);
    }

}