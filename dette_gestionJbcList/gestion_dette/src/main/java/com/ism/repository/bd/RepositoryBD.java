package com.ism.repository.bd;

import com.ism.repository.IRepository;
import com.ism.database.DatabaseConnectionImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryBD<T> extends DatabaseConnectionImpl implements IRepository<T> {

    @Override
    public T findById(Long id) {
        String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération par ID : " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        List<T> results = new ArrayList<>();
        String query = "SELECT * FROM " + getTableName();
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                results.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de tous les enregistrements : " + e.getMessage(), e);
        }
        return results;
    }

    @Override
    public void save(T entity) {
        String query = getInsertQuery();
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setInsertParameters(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement : " + e.getMessage(), e);
        }
    }

    @Override
    public void update(T entity) {
        String query = getUpdateQuery();
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setUpdateParameters(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour : " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM " + getTableName() + " WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression : " + e.getMessage(), e);
        }
    }

    public int insert(String query, Object... parameters) {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion : " + e.getMessage(), e);
        }
    }

    public ResultSet select(String query, Object... parameters) {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sélection : " + e.getMessage(), e);
        }
    }

    // Méthodes abstraites à implémenter par les sous-classes
    protected abstract String getTableName();

    protected abstract String getInsertQuery();

    protected abstract void setInsertParameters(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract String getUpdateQuery();

    protected abstract void setUpdateParameters(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract T mapRow(ResultSet resultSet) throws SQLException;
}
