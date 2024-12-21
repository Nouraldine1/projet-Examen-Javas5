package com.example.core.repositories.list;

import java.util.ArrayList;
import java.util.List;

import com.example.core.data.entities.Article;
import com.example.core.repositories.list.interfaces.IArticleRepository;
import com.example.core.repository.impl.RepositoryList;

public class ArticleRepository extends RepositoryList<Article> implements IArticleRepository {
    public List<Article> listeArticle = new ArrayList<>();
    @Override
    public List<Article> ListArticleById(int id) {
        for (Article article : list) {
            if (article.getId()==id) {
                listeArticle.add(article);
                return listeArticle;
            }
        }
        return listeArticle;
    }
   @Override

   public Article ArticleById(int id){
    for (Article article : list) {
        if (article.getId()==id) {
            return article;
        }
    }
    return null;
   }
}
