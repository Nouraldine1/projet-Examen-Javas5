package com.ism.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseConnexionInt {

    Connection connect();

    void closeConnection();

    ResultSet executeQuery(String query) throws SQLException;

    int executeUpdate(String query) throws SQLException;
  //méthode execute permet d'exécuter une requête SQL sans attendre de résultat spécifique(CREATE, DROP, ou ALTER) 

    void execute(String query) throws SQLException;
  //Cette méthode démarre une transaction SQL
  //exécuter plusieurs requêtes.

    void beginTransaction() throws SQLException;
  //Cette méthode valide une transaction

    void commitTransaction() throws SQLException;

  //Cette méthode annule toutes les modifications effectuées dans une transaction en cours.
  //Elle est utilisée lorsque quelque chose tourne mal pendant une transaction.

    void rollbackTransaction() throws SQLException;
}
