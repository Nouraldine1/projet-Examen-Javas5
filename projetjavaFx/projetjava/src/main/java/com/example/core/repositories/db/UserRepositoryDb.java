package com.example.core.repositories.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.core.data.entities.User;
import com.example.core.repositories.list.interfaces.IUserRepository;
import com.example.core.repository.impl.RepositoryDb;

public class UserRepositoryDb extends RepositoryDb<User> implements IUserRepository {

    public UserRepositoryDb() {
        super("user", User.class);
    }

    @Override
    public User findByLogin(String login) {
        String sql = "SELECT * FROM " + tableName + " WHERE login = ?";
        User user = null;

        this.getConnection(); // Ouvrir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    try {
                        user = convertToObject(rs); // Convertir les données en un objet User
                    } catch (IllegalAccessException e) {
                        e.printStackTrace(); // Gérer l'exception IllegalAccessException ici
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(); // Fermer la connexion après utilisation
        }
        return user;
    }

}
