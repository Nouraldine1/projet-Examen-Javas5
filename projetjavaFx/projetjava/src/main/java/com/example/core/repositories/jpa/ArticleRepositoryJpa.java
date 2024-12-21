package com.example.core.repositories.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import com.example.core.data.entities.Article;
import com.example.core.repositories.list.interfaces.IArticleRepository;
import com.example.core.repository.impl.RepositoryJpa;



public class ArticleRepositoryJpa extends RepositoryJpa<Article> implements IArticleRepository {


    public ArticleRepositoryJpa() {
        super(Article.class);
    }

    @Override
    public List<Article> ListArticleById(int id) {
        String query = "SELECT a FROM Article a WHERE a.id = :id";
        TypedQuery<Article> typedQuery = entityManager.createQuery(query, Article.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getResultList();
    }

    @Override
    public Article ArticleById(int id) {
        return entityManager.find(Article.class, id); // Utilisation de find pour récupérer un article par ID
    }

    @Override
    public void insert(Article article) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(article); // Utilisation de persist pour insérer un nouvel article
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
          
        }
    }

    @Override
    public List<Article> select() {
        String query = "SELECT a FROM Article a";
        TypedQuery<Article> typedQuery = entityManager.createQuery(query, Article.class);
        return typedQuery.getResultList();
    }

}
