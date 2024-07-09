package org.example.pafpoo; // Define el paquete donde se encuentra esta clase

import java.sql.Connection; // Importa la clase Connection de java.sql para manejar la conexión a la base de datos
import java.sql.DriverManager; // Importa la clase DriverManager de java.sql para gestionar los controladores de la base de datos
import java.sql.SQLException; // Importa la clase SQLException de java.sql para manejar excepciones de SQL

public class DatabaseConnection { // Define la clase DatabaseConnection
    private static final String URL = "jdbc:mysql://localhost:3306/BCN";  // Declara y asigna la URL de conexión a la base de datos, incluyendo el puerto y el nombre de la base de datos
    private static final String USER = "root";  // Declara y asigna el usuario de la base de datos
    private static final String PASSWORD = "Poo.012024";  // Declara y asigna la contraseña de la base de datos

    public static Connection getConnection() { // Define un método estático para obtener la conexión a la base de datos
        Connection connection = null; // Declara una variable de tipo Connection e inicialízala en null
        try { // Inicia un bloque try para intentar establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD); // Intenta establecer la conexión con la base de datos usando DriverManager
            System.out.println("Conexión exitosa a MySQL!"); // Imprime un mensaje si la conexión es exitosa
        } catch (SQLException e) { // Captura cualquier SQLException que ocurra durante la conexión
            e.printStackTrace(); // Imprime el stack trace de la excepción
            System.out.println("Fallo en la conexión a MySQL: " + e.getMessage()); // Imprime un mensaje indicando que la conexión falló, junto con el mensaje de la excepción
        }
        return connection; // Retorna el objeto Connection (será null si la conexión falló)
    }
}
