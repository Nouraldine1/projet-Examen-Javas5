package com.example.core.repositories.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.core.data.entities.Dette;
import com.example.core.data.entities.Paiement;
import com.example.core.repositories.list.interfaces.IDetteRepository;
import com.example.core.repository.impl.RepositoryDb;

public class DetteRepositoryDb extends RepositoryDb<Dette> implements IDetteRepository {

    public DetteRepositoryDb() {
        super("dette", Dette.class);
    }

    // Récupérer une dette par ID
    @Override
    public Dette detteById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        Dette dette = null;

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dette = convertToObject(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return dette;
    }

    // Insérer une nouvelle dette
    @Override
    public void insert(Dette data) {
        String sql = "INSERT INTO " + tableName + " (montant_total, montant_verser, montant_restant, etat, date, client_id) VALUES (?, ?, ?, ?, ?, ?)";

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, data.getMontantTotal());
            ps.setDouble(2, data.getMontantVerser());
            ps.setDouble(3, data.getMontantRestant());
            ps.setString(4, data.getEtat().toString());  // Convertir l'énumération en chaîne
            ps.setDate(5, java.sql.Date.valueOf(data.getDate()));
            ps.setInt(6, data.getClient().getId());  // Récupérer l'ID du client

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

    // Sélectionner toutes les dettes
    @Override
    public List<Dette> select() {
        List<Dette> dettes = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Dette dette = convertToObject(rs);
                dettes.add(dette);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return dettes;
    }

    // Convertir un ResultSet en objet Dette
    @Override
    public Dette convertToObject(ResultSet rs) {
        Dette dette = new Dette();
        try {
            dette.setId(rs.getInt("id"));
            dette.setMontantTotal(rs.getDouble("montantTotal"));
            dette.setMontantVerser(rs.getDouble("montantVerser"));
            dette.setMontantRestant(rs.getDouble("montantRestant"));
            // dette.setEtat(EtatDette.valueOf(rs.getString("etat")));  // Convertir la chaîne en énumération
            dette.setDate(rs.getDate("date").toLocalDate());

            // Récupérer le client associé à la dette
            // dette.setClient(clientRepository.findById(rs.getInt("client_id")));

            // Récupérer les paiements associés à la dette
            // dette.setPaiements(findPaiementsByDetteId(dette.getId()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dette;
    }

    // Implémentation de la méthode detteById

    // Enregistrer un paiement pour une dette
    // @Override
    // public Paiement insertPaiement(Paiement paiement) {
    //     String sql = "INSERT INTO paiement (montant, date_paiement, dette_id) VALUES (?, ?, ?)";
        
    //     this.getConnection();  // Obtenir la connexion
    //     try (PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
    //         ps.setDouble(1, paiement.getMontant());
    //         ps.setDate(2, java.sql.Date.valueOf(paiement.getDate()));
    //         ps.setInt(3, paiement.getDette().getId());

    //         ps.executeUpdate();
    //         try (ResultSet rs = ps.getGeneratedKeys()) {
    //             if (rs.next()) {
    //                 paiement.setId(rs.getInt(1));  // Mettre à jour l'ID généré
    //             }
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     } finally {
    //         this.closeConnection();  // Fermer la connexion
    //     }
    //     return paiement;
    // }

    // Récupérer les paiements liés à une dette
    // @Override
    // public List<Paiement> enregistrePaiements(Paiement paiement) {
    //     List<Paiement> paiements = new ArrayList<>();
    //     String sql = "SELECT * FROM paiement WHERE dette_id = ?";
        
    //     this.getConnection();  // Obtenir la connexion
    //     try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
    //         ps.setInt(1, paiement.getDette().getId());
    //         try (ResultSet rs = ps.executeQuery()) {
    //             while (rs.next()) {
    //                 Paiement p = new Paiement();
    //                 p.setId(rs.getInt("id"));
    //                 p.setMontant(rs.getDouble("montant"));
    //                 p.setDate(rs.getDate("date_paiement").toLocalDate());
    //                 // Associer le paiement à la dette
    //                 p.setDette(findById(rs.getInt("dette_id")));
    //                 paiements.add(p);
    //             }
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     } finally {
    //         this.closeConnection();  // Fermer la connexion
    //     }
    //     return paiements;
    // }

    // Récupérer les dettes non soldées
    @Override
    public List<Dette> detteNonSolder() {
        List<Dette> dettes = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE montant_restant > 0";

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Dette dette = convertToObject(rs);
                dettes.add(dette);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return dettes;
    }

    @Override
    public List<Paiement> enregistrePaiements(Paiement paiement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enregistrePaiements'");
    }

    @Override
    public Paiement insertPaiement(Paiement paiement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertPaiement'");
    }

    // Méthode privée pour trouver les paiements d'une dette
    // private List<Paiement> findPaiementsByDetteId(int detteId) {
    //     List<Paiement> paiements = new ArrayList<>();
    //     String sql = "SELECT * FROM paiement WHERE dette_id = ?";
        
    //     this.getConnection();  // Obtenir la connexion
    //     try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
    //         ps.setInt(1, detteId);
    //         try (ResultSet rs = ps.executeQuery()) {
    //             while (rs.next()) {
    //                 Paiement paiement = new Paiement();
    //                 paiement.setId(rs.getInt("id"));
    //                 paiement.setMontant(rs.getDouble("montant"));
    //                 paiement.setDate(rs.getDate("date_paiement").toLocalDate());
    //                 paiements.add(paiement);
    //             }
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     } finally {
    //         this.closeConnection();  // Fermer la connexion
    //     }
    //     return paiements;
    // }
}
