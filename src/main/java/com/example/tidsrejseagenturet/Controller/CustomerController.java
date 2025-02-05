package com.example.tidsrejseagenturet.Controller;

import com.example.tidsrejseagenturet.Model.Customer;
import com.example.tidsrejseagenturet.Model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController {
    private int nextId = 1;

    // Opret en ny kunde
    public Customer createCustomer(String name, String email) {
        String sql = "INSERT INTO customer (name, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Customer creation failed");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new Customer(id, name, email);
                } else {
                    throw new SQLException("Customer creation failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer login(String email, String password) {
        String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1,email);
            statement.setString(2,password);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");

                    return new Customer(id, name, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}