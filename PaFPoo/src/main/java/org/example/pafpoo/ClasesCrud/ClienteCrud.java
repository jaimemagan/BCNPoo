package org.example.pafpoo.ClasesCrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.pafpoo.Clases.Cliente;

public class ClienteCrud {
    private final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC"; // URL de la base de datos
    private final String user = "root"; // Usuario de la base de datos
    private final String password = "madafakA23"; // Contraseña de la base de datos

    // Método para crear un nuevo cliente
    public void crearCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (nombreCompleto, direccion, telefono) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombreCompleto()); // Asignar nombre completo
            stmt.setString(2, cliente.getDireccion()); // Asignar dirección
            stmt.setString(3, cliente.getTelefono()); // Asignar teléfono
            stmt.executeUpdate(); // Ejecutar la actualización
        }
    }

    // Método para obtener un cliente por su ID
    public Cliente obtenerCliente(Long idCliente) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE idCliente = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idCliente); // Asignar ID del cliente
            try (ResultSet rs = stmt.executeQuery()) { // Ejecutar consulta
                if (rs.next()) { // Sí hay resultado
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getLong("idCliente")); // Asignar ID del cliente
                    cliente.setNombreCompleto(rs.getString("nombreCompleto")); // Asignar nombre completo
                    cliente.setDireccion(rs.getString("direccion")); // Asignar dirección
                    cliente.setTelefono(rs.getString("telefono")); // Asignar teléfono
                    return cliente; // Devolver cliente
                }
            }
        }
        return null; // Devolver null si no se encuentra
    }

    // Método para obtener todos los clientes
    public List<Cliente> obtenerTodosLosClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) { // Ejecutar consulta
            while (rs.next()) { // Mientras haya resultados
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getLong("idCliente")); // Asignar ID del cliente
                cliente.setNombreCompleto(rs.getString("nombreCompleto")); // Asignar nombre completo
                cliente.setDireccion(rs.getString("direccion")); // Asignar dirección
                cliente.setTelefono(rs.getString("telefono")); // Asignar teléfono
                clientes.add(cliente); // Añadir cliente a la lista
            }
        }
        return clientes; // Devolver lista de clientes
    }

    // Método para actualizar un cliente
    public void actualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET nombreCompleto = ?, direccion = ?, telefono = ? WHERE idCliente = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombreCompleto()); // Asignar nombre completo
            stmt.setString(2, cliente.getDireccion()); // Asignar dirección
            stmt.setString(3, cliente.getTelefono()); // Asignar teléfono
            stmt.setLong(4, cliente.getIdCliente()); // Asignar ID del cliente
            stmt.executeUpdate(); // Ejecutar actualización
        }
    }

    // Método para eliminar un cliente
    public void eliminarCliente(Long idCliente) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE idCliente = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idCliente); // Asignar ID del cliente
            stmt.executeUpdate(); // Ejecutar eliminación
        }
    }
}
