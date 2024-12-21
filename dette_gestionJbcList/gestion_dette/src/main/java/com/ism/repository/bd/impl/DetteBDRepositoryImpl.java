package com.ism.repository.bd.impl;

import com.ism.core.factory.RepositoryFactory;
import com.ism.models.entities.Dette;
import com.ism.models.entities.Client;
import com.ism.models.entities.Article;
import com.ism.models.enums.DetteEtat;
import com.ism.repository.bd.RepositoryBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetteBDRepositoryImpl extends RepositoryBD<Dette> {


    @Override
    public Dette findById(long userId) {
        String sql = "SELECT * FROM dettes WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Dette dette = new Dette();
                dette.setId(rs.getInt("id"));
                dette.setDate(rs.getDate("date").toLocalDate());
                dette.setMontantTotal(rs.getDouble("montant_total"));
                dette.setMontantVerse(rs.getDouble("montant_verse"));
                dette.setMontantRestant(rs.getDouble("montant_restant"));
                dette.setEtat(DetteEtat.valueOf(rs.getString("etat")));
                Client client = RepositoryFactory.getRepository(Client.class).findById((long) rs.getInt("client_id"));
                dette.setClient(client);
                dette.setArticles(getArticlesForDette(userId));
                return dette;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche de la dette", e);
        }
    }

    

    @Override
    public void save(Dette dette) {
        String sql = "INSERT INTO dettes (date, montant_total, montant_verse, montant_restant, etat, client_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, java.sql.Date.valueOf(dette.getDate()));
            stmt.setDouble(2, dette.getMontantTotal());
            stmt.setDouble(3, dette.getMontantVerse());
            stmt.setDouble(4, dette.getMontantRestant());
            stmt.setString(5, dette.getEtat().name());
            stmt.setLong(6, (long) dette.getClient().getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                dette.setId(rs.getInt(1));
            }
            saveDetteArticles(dette);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de la dette", e);
        }
    }

    @Override
    public void update(Dette dette) {
        String sql = "UPDATE dettes SET date = ?, montant_total = ?, montant_verse = ?, montant_restant = ?, etat = ?, client_id = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(dette.getDate()));
            stmt.setDouble(2, dette.getMontantTotal());
            stmt.setDouble(3, dette.getMontantVerse());
            stmt.setDouble(4, dette.getMontantRestant());
            stmt.setString(5, dette.getEtat().name());
            stmt.setLong(6, (long) dette.getClient().getId());
            stmt.setLong(7, (long) dette.getId());
            stmt.executeUpdate();
            saveDetteArticles(dette);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la dette", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM dettes WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de la dette", e);
        }
    }

    private void saveDetteArticles(Dette dette) {
        String deleteSql = "DELETE FROM dette_articles WHERE dette_id = ?";
        String insertSql = "INSERT INTO dette_articles (dette_id, article_id) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            deleteStmt.setLong(1, (long) dette.getId());
            deleteStmt.executeUpdate();
            for (Article article : dette.getArticles()) {
                insertStmt.setLong(1, (long) dette.getId());
                insertStmt.setLong(2, (long) article.getId());
                insertStmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde des articles de la dette", e);
        }
    }

    private List<Article> getArticlesForDette(Long detteId) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT a.* FROM articles a JOIN dette_articles da ON a.id = da.article_id WHERE da.dette_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, detteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setLibelle(rs.getString("libelle"));
                article.setPrix(rs.getDouble("prix"));
                article.setQteStocks(rs.getInt("qte_stocks"));
                articles.add(article);
            }
            return articles;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des articles de la dette", e);
        }
    }



    @Override
    public List<Dette> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }


}