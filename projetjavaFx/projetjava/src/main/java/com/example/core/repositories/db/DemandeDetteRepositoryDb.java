package com.example.core.repositories.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.core.data.entities.DemandeDette;
import com.example.core.data.Enum.EtatDemandeDette;
import com.example.core.repositories.list.interfaces.IDemandeDetteRepository;
import com.example.core.repository.impl.RepositoryDb;

public class DemandeDetteRepositoryDb extends RepositoryDb<DemandeDette> implements IDemandeDetteRepository {

    public DemandeDetteRepositoryDb() {
        super("demande_dette", DemandeDette.class);
    }

    // Récupérer une demande de dette par ID
    @Override
    public DemandeDette DemandeById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        DemandeDette demandeDette = null;

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    demandeDette = convertToObject(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return demandeDette;
    }

    // Insérer une nouvelle demande de dette
    @Override
    public void insert(DemandeDette data) {
        String sql = "INSERT INTO " + tableName + " (date, montant_total, etat) VALUES (?, ?, ?)";

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, java.sql.Date.valueOf(data.getDate()));
            ps.setDouble(2, data.getMontantTotal());
            ps.setString(3, data.getEtat().name());  // Stocker l'état sous forme de chaîne de caractères

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    data.setId(rs.getInt(1));  // Mettre à jour l'ID de la demande généré
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
    }

    // Sélectionner toutes les demandes de dette
    @Override
    public List<DemandeDette> select() {
        List<DemandeDette> demandesDettes = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DemandeDette demandeDette = convertToObject(rs);
                demandesDettes.add(demandeDette);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return demandesDettes;
    }

    // Convertir un ResultSet en objet DemandeDette
    @Override
    public DemandeDette convertToObject(ResultSet rs) {
        DemandeDette demandeDette = new DemandeDette();
        try {
            demandeDette.setId(rs.getInt("id"));
            demandeDette.setDate(rs.getDate("date").toLocalDate());
            demandeDette.setMontantTotal(rs.getInt("montant_total"));
            demandeDette.setEtat(EtatDemandeDette.valueOf(rs.getString("etat")));  // Convertir la chaîne en enum
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandeDette;
    }
}
