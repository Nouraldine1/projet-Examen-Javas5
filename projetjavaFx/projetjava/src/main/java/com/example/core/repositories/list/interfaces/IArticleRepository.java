package com.example.core.repositories.list.interfaces;

import java.util.List;

import com.example.core.data.entities.Article;

import com.example.core.repository.IRepository;

public interface IArticleRepository extends IRepository<Article>{
    List<Article> ListArticleById(int id);
    Article ArticleById(int id);
}
