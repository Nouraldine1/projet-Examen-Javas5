package com.ism.repository.bd.impl;

import com.ism.models.entities.User;
import com.ism.models.entities.Client;
import com.ism.repository.bd.RepositoryBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBDRepositoryImpl extends RepositoryBD<User> {

    @Override
    public User findById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID doit être valide");
        }
        String sql = "SELECT * FROM users WHERE id = ?";
        try (ResultSet rs = executePreparedQuery(sql, id)) {
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche de l'utilisateur", e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (ResultSet rs = executeQuery(sql)) {
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des utilisateurs", e);
        }
        return users;
    }

    @Override
    public void save(User user) {
        if (user == null || user.getClient() == null) {
            throw new IllegalArgumentException("L'utilisateur ou son client ne peut pas être null");
        }
        String sql = "INSERT INTO users (email, identifiant, password, role, etat, client_id) VALUES (?, ?, ?, ?, ?, ?)";
        int rows = executePreparedUpdate(sql, 
            user.getEmail(), 
            user.getIdentifiant(), 
            user.getPassword(), 
            user.getRole().name(), 
            user.getEtat().name(), 
            user.getClient().getId()
        );
        if (rows > 0) {
            try (ResultSet rs = executeQuery("SELECT LAST_INSERT_ID()")) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la récupération de l'ID généré", e);
            }
        }
    }

    @Override
    public void update(User user) {
        if (user == null || user.getId() == 0 || user.getClient() == null) {
            throw new IllegalArgumentException("L'utilisateur, son ID ou son client ne peut pas être null");
        }
        String sql = "UPDATE users SET email = ?, identifiant = ?, password = ?, role = ?, etat = ?, client_id = ? WHERE id = ?";
        executePreparedUpdate(sql, 
            user.getEmail(), 
            user.getIdentifiant(), 
            user.getPassword(), 
            user.getRole().name(), 
            user.getEtat().name(), 
            user.getClient().getId(), 
            user.getId()
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'ID doit être valide");
        }
        String sql = "DELETE FROM users WHERE id = ?";
        executePreparedUpdate(sql, id);
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setIdentifiant(rs.getString("identifiant"));
        user.setPassword(rs.getString("password"));
        user.setRole(com.ism.models.enums.Role.valueOf(rs.getString("role")));
        user.setEtat(com.ism.models.enums.EtatCompte.valueOf(rs.getString("etat")));
        Client client = new Client();
        client.setId(rs.getInt("client_id")); 
        user.setClient(client);
        return user;
    }
}