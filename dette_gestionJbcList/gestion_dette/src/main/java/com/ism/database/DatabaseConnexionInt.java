package com.ism.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseConnexionInt {
    Connection getConnection(); 
    void closeConnection();
    ResultSet executeQuery(String query) throws SQLException;
    int executeUpdate(String query) throws SQLException;
}
