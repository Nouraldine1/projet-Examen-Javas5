package com.ism.services;

import com.ism.models.entities.Article;
import java.util.List;

public interface ArticleService {
    void createArticle(String libelle, double prix, int qteStocks);
    List<Article> listArticles();
    List<Article> listAvailableArticles();
    void updateStock(int articleId, int newQuantity);
}