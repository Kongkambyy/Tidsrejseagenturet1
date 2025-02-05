package com.example.tidsrejseagenturet.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeMachineDatabaseHandler {

    public TimeMachine createTimeMachineInDB(String name, int capacity, boolean available) {
        String sql = "INSERT INTO time_machine (name, capacity, available) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, name);
            stmt.setInt(2, capacity);
            stmt.setBoolean(3, available);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating time machine failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new TimeMachine(id, name, capacity, available);
                } else {
                    throw new SQLException("Creating time machine failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateTimeMachineInDB(int id, String name, int capacity, boolean available) {
        String sql = "UPDATE time_machine SET name = ?, capacity = ?, available = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt(2, capacity);
            stmt.setBoolean(3, available);
            stmt.setInt(4, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TimeMachine> getAllTimeMachinesFromDB() {
        List<TimeMachine> machines = new ArrayList<>();
        String sql = "SELECT * FROM time_machine";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                machines.add(new TimeMachine(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("capacity"),
                        rs.getBoolean("available")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return machines;
    }

    public TimeMachine getTimeMachineFromDB(int id) {
        String sql = "SELECT * FROM time_machine WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TimeMachine(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("capacity"),
                            rs.getBoolean("available")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAvailabilityInDB(int id, boolean available) {
        String sql = "UPDATE time_machine SET available = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, available);
            stmt.setInt(2, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}