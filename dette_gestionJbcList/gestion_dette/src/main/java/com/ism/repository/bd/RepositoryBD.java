package com.ism.repository.bd;

import com.ism.database.DatabaseConnectionImpl;
import com.ism.database.DatabaseConnexionInt;
import com.ism.repository.IRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class RepositoryBD<T> implements IRepository<T> {
    protected DatabaseConnexionInt dbConnection;

    public RepositoryBD() {
        this.dbConnection = new DatabaseConnectionImpl();
    }

    protected Connection getConnection() {
        return dbConnection.getConnection();
    }

    protected void closeConnection() {
        dbConnection.closeConnection();
    }

    protected ResultSet executeQuery(String query) {
        try {
            return dbConnection.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'exécution de la requête : " + query, e);
        }
    }

    protected int executeUpdate(String query) {
        try {
            return dbConnection.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour : " + query, e);
        }
    }

    protected ResultSet executePreparedQuery(String query, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            setParameters(stmt, params);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'exécution de la requête préparée : " + query, e);
        }
    }

    protected int executePreparedUpdate(String query, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            setParameters(stmt, params);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour préparée : " + query, e);
        }
    }

    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }
}