package com.ism.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionImpl implements DatabaseConnexionInt {
    private Connection connection;
    private final DatabaseConfig dbConfig;

    public DatabaseConnectionImpl() {
        this.dbConfig = new DatabaseConfig();
    }

    @Override
    public Connection getConnection() {
        if (connection == null) {
            try {
                String url = dbConfig.getUrl();
                String username = dbConfig.getUsername();
                String password = dbConfig.getPassword();
                String driver = dbConfig.getDriver();

                System.out.println("Default Database: " + dbConfig.getDefaultDatabase());
                System.out.println("Connexion à la base de données avec URL : " + url);
                System.out.println("Utilisateur : " + username);

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connexion à la base de données réussie !");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Driver JDBC introuvable : " + e.getMessage(), e);
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la connexion à la base de données : " + e.getMessage(), e);
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
                throw new RuntimeException("Erreur lors de la fermeture de la connexion : " + e.getMessage(), e);
            }
        }
    }

    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        if (connection == null) {
            throw new SQLException("Aucune connexion ouverte pour exécuter la requête.");
        }
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public int executeUpdate(String query) throws SQLException {
        if (connection == null) {
            throw new SQLException("Aucune connexion ouverte pour exécuter la mise à jour.");
        }
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }
}