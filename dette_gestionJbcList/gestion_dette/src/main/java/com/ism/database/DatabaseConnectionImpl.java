package com.ism.database;

import java.sql.*;

public class DatabaseConnectionImpl implements DatabaseConnexionInt {

    private Connection connection;

    @Override
    public Connection connect() {
        if (connection == null) {
            try {
                DatabaseConfig dbConfig = new DatabaseConfig();

                // Load configuration from DatabaseConfig
                String url = dbConfig.getUrl();
                String username = dbConfig.getUsername();
                String password = dbConfig.getPassword();
                String driver = dbConfig.getDriver();

                // Display default database
                System.out.println("Default Database: " + dbConfig.getDefaultDatabase());
                System.out.println("Connexion à la base de données avec URL : " + url);
                System.out.println("Utilisateur : " + username);

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connexion à la base de données réussie !");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            }
        }
        return connection;
    }

    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Connexion à la base de données fermée.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public int executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    @Override
    public void execute(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    @Override
    public void beginTransaction() throws SQLException {
        if (connection != null) {
            connection.setAutoCommit(false);
        } else {
            throw new SQLException("Connexion non établie pour démarrer une transaction.");
        }
    }

    @Override
    public void commitTransaction() throws SQLException {
        if (connection != null) {
            connection.commit();
            connection.setAutoCommit(true);
        } else {
            throw new SQLException("Connexion non établie pour valider une transaction.");
        }
    }

    @Override
    public void rollbackTransaction() throws SQLException {
        if (connection != null) {
            connection.rollback();
            connection.setAutoCommit(true);
        } else {
            throw new SQLException("Connexion non établie pour annuler une transaction.");
        }
    }
}
