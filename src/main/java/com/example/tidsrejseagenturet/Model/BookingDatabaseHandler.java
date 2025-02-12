package com.example.tidsrejseagenturet.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDatabaseHandler {

    public Booking createBooking(int customerId, int timeMachineId, int timePeriodId, int guideId) {
        String sql = "INSERT INTO bookings (customer_id, time_machine_id, time_period_id, guide_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, customerId);
            stmt.setInt(2, timeMachineId);
            stmt.setInt(3, timePeriodId);
            stmt.setInt(4, guideId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating booking failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Booking(
                            generatedKeys.getInt(1),
                            customerId,
                            timeMachineId,
                            timePeriodId,
                            guideId
                    );
                } else {
                    throw new SQLException("Creating booking failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Booking> getAllBookings() {
        ObservableList<Booking> bookings = FXCollections.observableArrayList();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("time_machine_id"),
                        rs.getInt("time_period_id"),
                        rs.getInt("guide_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public ObservableList<Booking> getBookingsByCustomerId(int customerId) {
        ObservableList<Booking> bookings = FXCollections.observableArrayList();
        String sql = "SELECT * FROM bookings WHERE customer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("time_machine_id"),
                        rs.getInt("time_period_id"),
                        rs.getInt("guide_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public boolean deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE id = ?";

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

    public boolean updateBooking(int id, int customerId, int timeMachineId, int timePeriodId, int guideId) {
        String sql = "UPDATE bookings SET customer_id = ?, time_machine_id = ?, time_period_id = ?, guide_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            stmt.setInt(2, timeMachineId);
            stmt.setInt(3, timePeriodId);
            stmt.setInt(4, guideId);
            stmt.setInt(5, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}