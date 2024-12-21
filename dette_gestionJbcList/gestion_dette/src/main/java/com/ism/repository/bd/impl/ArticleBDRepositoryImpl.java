package com.ism.repository.bd.impl;

import com.ism.models.entities.Article;
import com.ism.repository.bd.RepositoryBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleBDRepositoryImpl extends RepositoryBD<Article> {

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM articles";
        try (ResultSet rs = executeQuery(sql)) {
            while (rs.next()) {
                articles.add(mapResultSetToArticle(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des articles", e);
        }
        return articles;
    }

    @Override
    public void save(Article article) {
        if (article == null) {
            throw new IllegalArgumentException("L'article ne peut pas être null");
        }
        String sql = "INSERT INTO articles (libelle, prix, qte_stocks) VALUES (?, ?, ?)";
        int rows = executePreparedUpdate(sql, article.getLibelle(), article.getPrix(), article.getQteStocks());
        if (rows > 0) {
            try (ResultSet rs = executeQuery("SELECT LAST_INSERT_ID()")) {
                if (rs.next()) {
                    article.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la récupération de l'ID généré", e);
            }
        }
    }

    @Override
    public void update(Article article) {
        if (article == null || article.getId() == 0) {
            throw new IllegalArgumentException("L'article ou son ID ne peut pas être null");
        }
        String sql = "UPDATE articles SET libelle = ?, prix = ?, qte_stocks = ? WHERE id = ?";
        executePreparedUpdate(sql, article.getLibelle(), article.getPrix(), article.getQteStocks(), article.getId());
    }

    @Override
    public void delete(Long id) { 
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'ID doit être valide");
        }
        String sql = "DELETE FROM articles WHERE id = ?";
        executePreparedUpdate(sql, id);
    }

    @Override
    public Article findById(long id) { 
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID doit être valide");
        }
        String sql = "SELECT * FROM articles WHERE id = ?";
        try (ResultSet rs = executePreparedQuery(sql, id)) {
            if (rs.next()) {
                return mapResultSetToArticle(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche de l'article", e);
        }
    }

    // Méthode helper pour mapper ResultSet à Article
    private Article mapResultSetToArticle(ResultSet rs) throws SQLException {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setLibelle(rs.getString("libelle"));
        article.setPrix(rs.getDouble("prix"));
        article.setQteStocks(rs.getInt("qte_stocks"));
        return article;
    }
}