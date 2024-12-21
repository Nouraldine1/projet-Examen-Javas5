package com.ism.repository.bd.impl;

import com.ism.models.entities.Client;
import com.ism.repository.bd.RepositoryBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientBDRepositoryImpl extends RepositoryBD<Client> {

    @Override
    public Client findById(long id) { 
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID doit être valide");
        }
        String sql = "SELECT * FROM clients WHERE id = ?";
        try (ResultSet rs = executePreparedQuery(sql, id)) {
            if (rs.next()) {
                return mapResultSetToClient(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche du client", e);
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (ResultSet rs = executeQuery(sql)) {
            while (rs.next()) {
                clients.add(mapResultSetToClient(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des clients", e);
        }
        return clients;
    }

    @Override
    public void save(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Le client ne peut pas être null");
        }
        String sql = "INSERT INTO clients (nom, telephone, adresse, montant_total_du) VALUES (?, ?, ?, ?)";
        int rows = executePreparedUpdate(sql, client.getNom(), client.getTelephone(), client.getAdresse(), client.getMontantTotalDu());
        if (rows > 0) {
            try (ResultSet rs = executeQuery("SELECT LAST_INSERT_ID()")) {
                if (rs.next()) {
                    client.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la récupération de l'ID généré", e);
            }
        }
    }

    @Override
    public void update(Client client) {
        if (client == null || client.getId() == 0) {
            throw new IllegalArgumentException("Le client ou son ID ne peut pas être null");
        }
        String sql = "UPDATE clients SET nom = ?, telephone = ?, adresse = ?, montant_total_du = ? WHERE id = ?";
        executePreparedUpdate(sql, client.getNom(), client.getTelephone(), client.getAdresse(), client.getMontantTotalDu(), client.getId());
    }

    @Override
    public void delete(Long id) { 
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'ID doit être valide");
        }
        String sql = "DELETE FROM clients WHERE id = ?";
        executePreparedUpdate(sql, id);
    }

    private Client mapResultSetToClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("id"));
        client.setNom(rs.getString("nom"));
        client.setTelephone(rs.getString("telephone"));
        client.setAdresse(rs.getString("adresse"));
        client.setMontantTotalDu(rs.getDouble("montant_total_du"));
        return client;
    }
}