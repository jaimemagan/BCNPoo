package org.example.pafpoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/BCN";
    private static final String USER = "root"; // Cambia esto por tu usuario de MySQL
    private static final String PASSWORD = "Poo.012024"; // Cambia esto por tu contraseña de MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos BCN.");
            } else {
                System.out.println("Fallo en la conexión a la base de datos BCN.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
