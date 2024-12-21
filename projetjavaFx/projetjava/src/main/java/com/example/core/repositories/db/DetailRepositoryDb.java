package com.example.core.repositories.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.core.data.entities.Detail;
import com.example.core.repositories.list.interfaces.IDetailRepository;
import com.example.core.repository.impl.RepositoryDb;

public class DetailRepositoryDb extends RepositoryDb<Detail> implements IDetailRepository {

    public DetailRepositoryDb() {
        super("detail", Detail.class);
    }

    // Récupérer un détail par ID
    @Override
    public Detail findById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        Detail detail = null;

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    detail = convertToObject(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return detail;
    }

    // Insérer un nouveau détail
    @Override
    public void insert(Detail data) {
        String sql = "INSERT INTO " + tableName + " (quantite, prix_vente, article_id, dette_id) VALUES (?, ?, ?, ?)";

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, data.getQuantite());
            ps.setInt(2, data.getPrixVente());
            // ps.setInt(3, data.getArticle().getId());  // Insérer l'ID de l'article
            // ps.setInt(4, data.getDette().getId());    // Insérer l'ID de la dette

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    data.setId(rs.getInt(1));  // Mettre à jour l'ID généré
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
    }

    // Sélectionner tous les détails
    @Override
    public List<Detail> select() {
        List<Detail> details = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Detail detail = convertToObject(rs);
                details.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return details;
    }

    // Convertir un ResultSet en objet Detail
    @Override
    public Detail convertToObject(ResultSet rs) {
        Detail detail = new Detail();
        try {
            detail.setId(rs.getInt("id"));
            detail.setQuantite(rs.getInt("quantite"));
            detail.setPrixVente(rs.getInt("prix_vente"));

            // Pour l'article et la dette, tu devras probablement récupérer ces objets séparément
            // Utilise les repositories correspondants pour les récupérer
            // detail.setArticle(articleRepository.findById(rs.getInt("article_id")));
            // detail.setDette(detteRepository.findById(rs.getInt("dette_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detail;
    }
}
