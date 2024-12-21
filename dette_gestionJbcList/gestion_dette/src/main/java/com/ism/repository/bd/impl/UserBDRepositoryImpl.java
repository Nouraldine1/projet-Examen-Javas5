package com.ism.repository.bd.impl;

import com.ism.models.entities.User;
import com.ism.models.enums.EtatCompte;
import com.ism.repository.bd.UserBDRepository;
import com.ism.repository.bd.RepositoryBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBDRepositoryImpl extends RepositoryBD<User> implements UserBDRepository {

    @Override
    protected String getTableName() {
        return "User";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (email, password, identifiant, role, etat) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setString(1, entity.getEmail());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getIdentifiant());
        preparedStatement.setString(4, entity.getRole());
        preparedStatement.setString(5, entity.getEtat().name());
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET email = ?, password = ?, identifiant = ?, role = ?, etat = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setString(1, entity.getEmail());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getIdentifiant());
        preparedStatement.setString(4, entity.getRole());
        preparedStatement.setString(5, entity.getEtat().name());
        preparedStatement.setLong(6, entity.getId());
    }

    @Override
    protected User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setIdentifiant(resultSet.getString("identifiant"));
        user.setRole(resultSet.getString("role"));
        user.setEtat(EtatCompte.valueOf(resultSet.getString("etat")));
        return user;
    }

    @Override
    public User login(String email, String password) {
        String query = "SELECT * FROM " + getTableName() + " WHERE email = ? AND password = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = executeQuery(preparedStatement);

            if (resultSet.next()) {
                return mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la connexion de l'utilisateur : " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User selectByLogin(String login) {
        String query = "SELECT * FROM " + getTableName() + " WHERE identifiant = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, login);
            ResultSet resultSet = executeQuery(preparedStatement);

            if (resultSet.next()) {
                return mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de l'utilisateur par login : " + e.getMessage(), e);
        }
        return null;
    }

    private ResultSet executeQuery(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

    private int executeUpdate(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeUpdate();
    }
}
