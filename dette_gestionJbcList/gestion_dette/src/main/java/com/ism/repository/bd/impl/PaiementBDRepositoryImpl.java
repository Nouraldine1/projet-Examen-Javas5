package com.ism.repository.bd.impl;

import com.ism.models.entities.Paiement;
import com.ism.models.enums.PaiementEtat;
import com.ism.models.entities.Dette;
import com.ism.repository.bd.RepositoryBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaiementBDRepositoryImpl extends RepositoryBD<Paiement> {

    @Override
    public Paiement findById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID doit être valide");
        }
        String sql = "SELECT * FROM paiements WHERE id_paiement = ?";
        try (ResultSet rs = executePreparedQuery(sql, id)) {
            if (rs.next()) {
                return mapResultSetToPaiement(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche du paiement", e);
        }
    }

    @Override
    public List<Paiement> findAll() {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiements";
        try (ResultSet rs = executeQuery(sql)) {
            while (rs.next()) {
                paiements.add(mapResultSetToPaiement(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des paiements", e);
        }
        return paiements;
    }

    @Override
    public void save(Paiement paiement) {
        if (paiement == null || paiement.getDette() == null) {
            throw new IllegalArgumentException("Le paiement ou la dette associée ne peut pas être null");
        }
        String sql = "INSERT INTO paiements (montant, date, etat, dette_id) VALUES (?, ?, ?, ?)";
        int rows = executePreparedUpdate(sql, 
            paiement.getMontant(), 
            java.sql.Date.valueOf(paiement.getDate()), 
            paiement.getEtat().name(), 
            paiement.getDette().getId()
        );
        if (rows > 0) {
            try (ResultSet rs = executeQuery("SELECT LAST_INSERT_ID()")) {
                if (rs.next()) {
                    paiement.setIdPaiement(rs.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la récupération de l'ID généré", e);
            }
        }
    }

    @Override
    public void update(Paiement paiement) {
        if (paiement == null || paiement.getIdPaiement() == 0 || paiement.getDette() == null) {
            throw new IllegalArgumentException("Le paiement, son ID ou la dette associée ne peut pas être null");
        }
        String sql = "UPDATE paiements SET montant = ?, date = ?, etat = ?, dette_id = ? WHERE id_paiement = ?";
        executePreparedUpdate(sql, 
            paiement.getMontant(), 
            java.sql.Date.valueOf(paiement.getDate()), 
            paiement.getEtat().name(), 
            paiement.getDette().getId(), 
            paiement.getIdPaiement()
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'ID doit être valide");
        }
        String sql = "DELETE FROM paiements WHERE id_paiement = ?";
        executePreparedUpdate(sql, id);
    }

    private Paiement mapResultSetToPaiement(ResultSet rs) throws SQLException {
        Paiement paiement = new Paiement();
        paiement.setIdPaiement(rs.getInt("id_paiement"));
        paiement.setMontant(rs.getDouble("montant"));
        paiement.setDate(rs.getDate("date").toLocalDate());
        paiement.setEtat(PaiementEtat.valueOf(rs.getString("etat")));
        Dette dette = new Dette();
        dette.setId(rs.getInt("dette_id")); 
        paiement.setDette(dette);
        return paiement;
    }
}