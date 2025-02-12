package com.example.tidsrejseagenturet.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimePeriodsDatabaseHandler {

    public TimePeriods createTimePeriodInDB(String name, String description) {
        String sql = "INSERT INTO time_periods (name, description) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, name);
            stmt.setString(2, description);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating time period failed");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new TimePeriods(id, name, description);
                } else {
                    throw new SQLException("Creating time period failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteTimePeriodFromDB(int id) {
        String sql = "DELETE FROM time_periods WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TimePeriods> getAllTimePeriodsFromDB() {
        List<TimePeriods> timePeriods = new ArrayList<>();
        String sql = "SELECT * FROM time_periods";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                timePeriods.add(new TimePeriods(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timePeriods;
    }

    public boolean editTimePeriodInDB(int id, String name, String description) {
        String sql = "UPDATE time_periods SET name = ?, description = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, description);
            statement.setInt(3, id);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}