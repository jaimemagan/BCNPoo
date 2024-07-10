package org.example.pafpoo.ClasesCrud; // Define el paquete al que pertenece esta clase

import org.example.pafpoo.Clases.Cliente; // Importa la clase Cliente
import org.example.pafpoo.DatabaseConnection; // Importa la clase DatabaseConnection

import java.sql.*; // Importa todas las clases del paquete java.sql
import java.util.ArrayList; // Importa la clase ArrayList
import java.util.List; // Importa la interfaz List

public class ClienteCrud { // Define la clase ClienteCrud

    // Método para agregar un cliente a la base de datos
    public void agregarCliente(Cliente cliente) throws SQLException {
        // Define la consulta SQL para insertar un nuevo cliente con parámetros
        String sql = "INSERT INTO cliente (nombre_Completo, direccion, telefono) VALUES (?, ?, ?)";
        // Usa un bloque try-with-resources para asegurar que los recursos se cierren correctamente
        try (Connection conn = DatabaseConnection.getConnection(); // Obtiene una conexión a la base de datos
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara la consulta SQL
            // Establece los valores de los parámetros en la consulta SQL
            pstmt.setString(1, cliente.getNombreCompleto()); // Asigna el nombre completo del cliente al primer parámetro
            pstmt.setString(2, cliente.getDireccion()); // Asigna la dirección del cliente al segundo parámetro
            pstmt.setString(3, cliente.getTelefono()); // Asigna el teléfono del cliente al tercer parámetro
            // Ejecuta la consulta de inserción
            pstmt.executeUpdate(); // Ejecuta la actualización en la base de datos
        }
    }

    // Método para eliminar un cliente de la base de datos basado en su ID
    public void eliminarCliente(Long idCliente) throws SQLException {
        // Define la consulta SQL para eliminar un cliente basado en su ID
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
        // Usa un bloque try-with-resources para asegurar que los recursos se cierren correctamente
        try (Connection conn = DatabaseConnection.getConnection(); // Obtiene una conexión a la base de datos
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara la consulta SQL
            // Establece el valor del parámetro en la consulta SQL
            pstmt.setLong(1, idCliente); // Asigna el ID del cliente al primer parámetro
            // Ejecuta la consulta de eliminación
            pstmt.executeUpdate(); // Ejecuta la actualización en la base de datos
        }
    }

    // Método para actualizar los datos de un cliente en la base de datos
    public void actualizarCliente(Cliente cliente) throws SQLException {
        // Define la consulta SQL para actualizar un cliente basado en su ID
        String sql = "UPDATE cliente SET nombre_Completo = ?, direccion = ?, telefono = ? WHERE idCliente = ?";
        // Usa un bloque try-with-resources para asegurar que los recursos se cierren correctamente
        try (Connection conn = DatabaseConnection.getConnection(); // Obtiene una conexión a la base de datos
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepara la consulta SQL
            // Establece los valores de los parámetros en la consulta SQL
            pstmt.setString(1, cliente.getNombreCompleto()); // Asigna el nombre completo del cliente al primer parámetro
            pstmt.setString(2, cliente.getDireccion()); // Asigna la dirección del cliente al segundo parámetro
            pstmt.setString(3, cliente.getTelefono()); // Asigna el teléfono del cliente al tercer parámetro
            pstmt.setLong(4, cliente.getIdCliente()); // Asigna el ID del cliente al cuarto parámetro
            // Ejecuta la consulta de actualización
            pstmt.executeUpdate(); // Ejecuta la actualización en la base de datos
        }
    }

    // Método para obtener todos los clientes de la base de datos
    public List<Cliente> obtenerTodosLosClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>(); // Crea una lista para almacenar los clientes
        // Define la consulta SQL para obtener todos los clientes
        String sql = "SELECT * FROM cliente";
        // Usa un bloque try-with-resources para asegurar que los recursos se cierren correctamente
        try (Connection conn = DatabaseConnection.getConnection(); // Obtiene una conexión a la base de datos
             Statement stmt = conn.createStatement(); // Crea una declaración para ejecutar la consulta SQL
             ResultSet rs = stmt.executeQuery(sql)) { // Ejecuta la consulta SQL y obtiene los resultados
            while (rs.next()) { // Itera sobre los resultados
                Cliente cliente = new Cliente(); // Crea un nuevo objeto Cliente
                // Asigna los valores obtenidos de la base de datos al objeto Cliente
                cliente.setIdCliente(rs.getLong("idCliente")); // Asigna el ID del cliente
                cliente.setNombreCompleto(rs.getString("nombre_Completo")); // Asigna el nombre completo del cliente
                cliente.setDireccion(rs.getString("direccion")); // Asigna la dirección del cliente
                cliente.setTelefono(rs.getString("telefono")); // Asigna el teléfono del cliente
                clientes.add(cliente); // Añade el cliente a la lista
            }
        }
        return clientes; // Devuelve la lista de clientes
    }
}
