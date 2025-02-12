package com.example.tidsrejseagenturet.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseConnection {
    // Konstant, der indeholder URL'en til databasen.
    // JDBC URL format: jdbc:mysql://[host]:[port]/[database]
    private static final String URL = "jdbc:mysql://lasseblunckshillerx.tplinkdns.com:3306/tidsrejse";

    // MySQL-brugernavn. Skal matche brugernavnet på din database.
    private static final String USER = "ZealandGruppen";

    // MySQL-adgangskode. Sørg for at holde denne sikker og ikke dele den offentligt.
    private static final String PASSWORD = "KunForZealand";

    // Denne metode opretter forbindelse til databasen og returnerer et Connection-objekt.
    public static Connection getConnection() {
        try {
            // DriverManager håndterer forbindelsen til databasen.
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Hvis noget går galt, udskrives fejlen i konsollen.
            e.printStackTrace();
            return null; // Returner null, hvis forbindelsen mislykkes.
        }
    }
}