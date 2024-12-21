package com.example.core.repositories.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.core.data.entities.Article;
import com.example.core.repositories.list.interfaces.IArticleRepository;
import com.example.core.repository.impl.RepositoryDb;

public class ArticleRepositoryDb extends RepositoryDb<Article> implements IArticleRepository {

    public ArticleRepositoryDb() {
        super("articles", Article.class);  // Assurez-vous que la table s'appelle bien "articles"
    }

    @Override
    public List<Article> ListArticleById(int id) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    articles.add(convertToObject(rs));  // Conversion de chaque ligne en objet Article
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return articles;
    }

    @Override
    public Article ArticleById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        Article article = null;

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    article = convertToObject(rs);  // Convertir la première ligne en Article
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return article;
    }

    @Override
    public void insert(Article data) {
        String sql = "INSERT INTO " + tableName + " (libelle, prix, qteStock) VALUES (?, ?, ?)";
        this.getConnection();  // Obtenir la connexion

        try (PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, data.getLibelle());  // Assurez-vous que le champ "name" existe dans l'entité Article
            ps.setDouble(2, data.getPrix());  // Assurez-vous que le champ "price" existe
            ps.setInt(3, data.getQteStock());  // Assurez-vous que le champ "quantity" existe
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    data.setId(rs.getInt(1));  // Met à jour l'ID généré dans l'entité Article
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
    }

    @Override
    public List<Article> select() {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                articles.add(convertToObject(rs));  // Ajouter chaque article récupéré à la liste
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return articles;
    }

    @Override
    public Article convertToObject(ResultSet rs) {
        Article article = new Article();
        try {
            article.setId(rs.getInt("id"));  // Assurez-vous que "id" est correct
            article.setLibelle(rs.getString("libelle"));  // Assurez-vous que le champ "name" existe
            article.setPrix(rs.getDouble("prix"));  // Assurez-vous que le champ "price" existe
            article.setQteStock(rs.getInt("qteStock"));  // Assurez-vous que le champ "quantity" existe
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }
}
