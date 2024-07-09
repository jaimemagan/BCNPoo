package org.example.pafpoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/BCN";  // Cambia el puerto y el nombre de la base de datos según sea necesario
    private static final String USER = "root";  // Reemplaza con tu usuario de MySQL
    private static final String PASSWORD = "Poo.012024";  // Reemplaza con tu contraseña de MySQL

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a MySQL!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fallo en la conexión a MySQL: " + e.getMessage());
        }
        return connection;
    }
}
