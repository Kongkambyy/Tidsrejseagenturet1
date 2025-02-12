package com.example.tidsrejseagenturet.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuideDatabaseHandler {

    // Hent alle guides fra databasen
    public ObservableList<Guide> getAllGuides() {
        ObservableList<Guide> guides = FXCollections.observableArrayList();
        String sql = "SELECT * FROM guides";  // Added missing *

        try (Connection conn = DatabaseConnection.getConnection();  // Changed to match your other handlers
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                guides.add(new Guide(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("specialty")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Consistent with other handlers
        }
        return guides;
    }

    // Opret en ny guide
    public Guide createGuide(String name, String specialty) {
        String sql = "INSERT INTO guides (name, specialty) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, name);
            stmt.setString(2, specialty);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating guide failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Guide(generatedKeys.getInt(1), name, specialty);
                } else {
                    throw new SQLException("Creating guide failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Opdater en eksisterende guide
    public boolean updateGuide(int id, String newName, String newSpecialty) {
        String sql = "UPDATE guides SET name = ?, specialty = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setString(2, newSpecialty);
            stmt.setInt(3, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Slet en guide
    public boolean deleteGuide(int id) {
        String sql = "DELETE FROM guides WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}