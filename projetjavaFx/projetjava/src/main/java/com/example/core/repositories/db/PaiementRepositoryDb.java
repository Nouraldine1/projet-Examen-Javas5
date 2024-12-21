package com.example.core.repositories.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.core.data.entities.Paiement;
import com.example.core.repositories.list.interfaces.IPaiementRepository;
import com.example.core.repository.impl.RepositoryDb;

public class PaiementRepositoryDb extends RepositoryDb<Paiement> implements IPaiementRepository {

    public PaiementRepositoryDb() {
        super("paiement", Paiement.class);
    }

    // Insérer un nouveau paiement
    @Override
    public void insert(Paiement paiement) {
        String sql = "INSERT INTO " + tableName + " (date_paiement, montant, dette_id) VALUES (?, ?, ?)";
        
        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, java.sql.Date.valueOf(paiement.getDate()));
            ps.setInt(2, paiement.getMontantPayer());
            ps.setInt(3, paiement.getDette().getId());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    paiement.setId(rs.getInt(1));  // Mettre à jour l'ID généré
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
    }

    // Récupérer un paiement par son ID
    @Override
    public Paiement findById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        Paiement paiement = null;

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    paiement = convertToObject(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return paiement;
    }

    // Récupérer tous les paiements associés à une dette spécifique
    public List<Paiement> findByDetteId(int detteId) {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE dette_id = ?";

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, detteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Paiement paiement = convertToObject(rs);
                    paiements.add(paiement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return paiements;
    }

    // Convertir un ResultSet en objet Paiement
    @Override
    public Paiement convertToObject(ResultSet rs) {
        Paiement paiement = new Paiement();
        try {
            paiement.setId(rs.getInt("id"));
            paiement.setDate(rs.getDate("date_paiement").toLocalDate());
            paiement.setMontantPayer(rs.getInt("montant"));

            // Vous devrez peut-être ajouter une logique ici pour récupérer l'entité Dette associée
            // paiement.setDette(detteRepository.findById(rs.getInt("dette_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiement;
    }

    // Sélectionner tous les paiements
    @Override
    public List<Paiement> select() {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paiement paiement = convertToObject(rs);
                paiements.add(paiement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return paiements;
    }

    @Override
    public List<Paiement> ListPaiementById(int detteId) {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE dette_id = ?";

        this.getConnection();  // Obtenir la connexion à la base de données
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, detteId);  // Assigner l'ID de la dette

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Paiement paiement = convertToObject(rs);  // Convertir chaque ligne de résultat en objet Paiement
                    paiements.add(paiement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion après l'opération
        }
        return paiements;
    }
}
