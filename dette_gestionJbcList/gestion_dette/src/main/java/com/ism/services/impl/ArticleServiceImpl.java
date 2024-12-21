package com.ism.services.impl;

import com.ism.core.factory.RepositoryFactory;
import com.ism.models.entities.Article;
import com.ism.repository.IRepository;
import com.ism.services.ArticleService;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleServiceImpl implements ArticleService {
    private IRepository<Article> articleRepository = RepositoryFactory.getRepository(Article.class);

    @Override
    public void createArticle(String libelle, double prix, int qteStocks) {
        Article article = new Article();
        article.setLibelle(libelle);
        article.setPrix(prix);
        article.setQteStocks(qteStocks);
        articleRepository.save(article);
    }

    @Override
    public List<Article> listArticles() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> listAvailableArticles() {
        return articleRepository.findAll().stream()
                .filter(article -> article.getQteStocks() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStock(int articleId, int newQuantity) {
        Article article = articleRepository.findById((long) articleId);
        if (article != null) {
            article.setQteStocks(newQuantity);
            articleRepository.update(article);
        }
    }
}