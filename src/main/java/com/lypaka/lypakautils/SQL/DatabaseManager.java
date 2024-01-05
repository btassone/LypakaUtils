package com.lypaka.lypakautils.SQL;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private final ConnectionManager mysqlConnectionManager;

    public DatabaseManager(ConnectionManager connectionManager) {
        this.mysqlConnectionManager = connectionManager;
    }

    public void saveData(String tableName, Map<String, Object> data) {
        try (Connection connection = mysqlConnectionManager.getDataSource().getConnection()) {
            // Build the SQL INSERT statement dynamically based on the provided data
            StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ")
                    .append(tableName)
                    .append(" (");

            StringBuilder placeholders = new StringBuilder();
            for (String fieldName : data.keySet()) {
                sqlBuilder.append(fieldName).append(",");
                placeholders.append("?,");
            }
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1); // Remove the trailing comma
            placeholders.deleteCharAt(placeholders.length() - 1); // Remove the trailing comma

            sqlBuilder.append(") VALUES (").append(placeholders).append(")");

            // Prepare and execute the SQL statement with parameters
            try (PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString())) {
                int parameterIndex = 1;
                for (Object value : data.values()) {
                    statement.setObject(parameterIndex++, value);
                }
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> loadData(String tableName, String[] fields, String condition) {
        Map<String, Object> data = new HashMap<>();
        String selectQuery = "SELECT " + String.join(", ", fields) + " FROM " + tableName + " WHERE " + condition;

        try (Connection connection = mysqlConnectionManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    for (String field : fields) {
                        Object value = resultSet.getObject(field);
                        data.put(field, value);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public void deleteData(String tableName, String condition) {
        String deleteQuery = "DELETE FROM " + tableName + " WHERE " + condition;

        try (Connection connection = mysqlConnectionManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean dataExists(String tableName, String condition) {
        String selectQuery = "SELECT EXISTS(SELECT 1 FROM " + tableName + " WHERE " + condition + ")";

        try (Connection connection = mysqlConnectionManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void createTable(String tableName, String tableDefinition) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + tableDefinition + ")";
        try (Connection connection = ConnectionManager.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it, throw it, etc.)
        }
    }
}
