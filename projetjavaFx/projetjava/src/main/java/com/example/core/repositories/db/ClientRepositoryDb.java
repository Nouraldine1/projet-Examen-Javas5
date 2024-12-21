package com.example.core.repositories.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.core.data.entities.Client;
import com.example.core.repositories.list.interfaces.IClientRepository;
import com.example.core.repository.impl.RepositoryDb;

public class ClientRepositoryDb extends RepositoryDb<Client> implements IClientRepository {

    public ClientRepositoryDb() {
        super("clients", Client.class);
    }

    @Override
    public Client findClientByPhone(String telephone) {
        String sql = "SELECT * FROM " + tableName + " WHERE telephone = ?";
        Client client = null;

        this.getConnection(); // Obtenir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, telephone);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = convertToObject(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(); // Fermer la connexion après utilisation
        }
        return client;
    }

    @Override
    public Client findClientBySurname(String surname) {
        String sql = "SELECT * FROM " + tableName + " WHERE surname = ?";
        Client client = null;

        this.getConnection(); // Obtenir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, surname);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = convertToObject(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(); // Fermer la connexion après utilisation
        }
        return client;
    }

    @Override
    public Client ClientById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        Client client = null;

        this.getConnection(); // Obtenir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = convertToObject(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(); // Fermer la connexion après utilisation
        }
        return client;
    }

    @Override
    public void insert(Client data) {
        System.out.println("Tentative d'insertion du client : " + data);
        String sql = "INSERT INTO " + tableName + " (surname, telephone, address) VALUES (?, ?, ?)";
        this.getConnection();

        try (PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, data.getSurname());
            ps.setString(2, data.getTelephone());
            ps.setString(3, data.getAddress());

            int rowsAffected = ps.executeUpdate();
            System.out.println("Nombre de lignes insérées : " + rowsAffected);

            if (rowsAffected == 0) {
                System.out.println("L'insertion du client a échoué, aucune ligne insérée.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    data.setId(rs.getInt(1));
                    System.out.println("ID généré pour le client : " + data.getId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public List<Client> select() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        this.getConnection(); // Obtenir la connexion
        try (PreparedStatement ps = this.connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clients.add(convertToObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(); // Fermer la connexion après utilisation
        }
        return clients;
    }

    @Override
    public Client convertToObject(ResultSet rs) { // Changer private en public
        Client client = new Client();
        try {
            client.setId(rs.getInt("id"));
            client.setSurname(rs.getString("surname"));
            client.setTelephone(rs.getString("telephone"));
            client.setAddress(rs.getString("address")); // Assurez-vous que l'entité Client a ce champ
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client findClientByUserId(int userId) {
        String sql = "SELECT * FROM " + tableName + " WHERE user_account_id = ?";
        Client client = null;

        this.getConnection(); // Obtenir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = convertToObject(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(); // Fermer la connexion après utilisation
        }
        return client;
    }

}
