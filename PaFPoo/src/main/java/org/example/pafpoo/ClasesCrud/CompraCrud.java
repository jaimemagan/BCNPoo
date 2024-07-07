package org.example.pafpoo.ClasesCrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.pafpoo.Clases.Compra;

public class CompraCrud {
    private final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC"; // URL de la base de datos
    private final String user = "root"; // Usuario de la base de datos
    private final String password = "madafakA23"; // Contraseña de la base de datos

    // Método para crear una nueva compra
    public void crearCompra(Compra compra) throws SQLException {
        String sql = "INSERT INTO compra (fechaCompra, montoTotal, descripcion, idTarjeta) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(compra.getFechaCompra())); // Asignar fecha de compra
            stmt.setFloat(2, compra.getMontoTotal()); // Asignar monto total
            stmt.setString(3, compra.getDescripcion()); // Asignar descripción
            stmt.setInt(4, compra.getIdTarjeta()); // Asignar ID de la tarjeta
            stmt.executeUpdate(); // Ejecutar la actualización
        }
    }

    // Método para obtener una compra por su ID
    public Compra obtenerCompra(int idCompra) throws SQLException {
        String sql = "SELECT * FROM compra WHERE idCompra = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCompra); // Asignar ID de la compra
            try (ResultSet rs = stmt.executeQuery()) { // Ejecutar consulta
                if (rs.next()) { // Sí hay resultado
                    Compra compra = new Compra();
                    compra.setIdCompra(rs.getInt("idCompra")); // Asignar ID de la compra
                    compra.setFechaCompra(rs.getTimestamp("fechaCompra").toLocalDateTime()); // Asignar fecha de compra
                    compra.setMontoTotal(rs.getFloat("montoTotal")); // Asignar monto total
                    compra.setDescripcion(rs.getString("descripcion")); // Asignar descripción
                    compra.setIdTarjeta(rs.getInt("idTarjeta")); // Asignar ID de la tarjeta
                    return compra; // Devolver compra
                }
            }
        }
        return null; // Devolver null si no se encuentra
    }

    // Método para obtener todas las compras
    public List<Compra> obtenerTodasLasCompras() throws SQLException {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) { // Ejecutar consulta
            while (rs.next()) { // Mientras haya resultados
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("idCompra")); // Asignar ID de la compra
                compra.setFechaCompra(rs.getTimestamp("fechaCompra").toLocalDateTime()); // Asignar fecha de compra
                compra.setMontoTotal(rs.getFloat("montoTotal")); // Asignar monto total
                compra.setDescripcion(rs.getString("descripcion")); // Asignar descripción
                compra.setIdTarjeta(rs.getInt("idTarjeta")); // Asignar ID de la tarjeta
                compras.add(compra); // Añadir compra a la lista
            }
        }
        return compras; // Devolver lista de compras
    }

    // Método para actualizar una compra
    public void actualizarCompra(Compra compra) throws SQLException {
        String sql = "UPDATE compra SET fechaCompra = ?, montoTotal = ?, descripcion = ?, idTarjeta = ? WHERE idCompra = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(compra.getFechaCompra())); // Asignar fecha de compra
            stmt.setFloat(2, compra.getMontoTotal()); // Asignar monto total
            stmt.setString(3, compra.getDescripcion()); // Asignar descripción
            stmt.setInt(4, compra.getIdTarjeta()); // Asignar ID de la tarjeta
            stmt.setInt(5, compra.getIdCompra()); // Asignar ID de la compra
            stmt.executeUpdate(); // Ejecutar actualización
        }
    }

    // Método para eliminar una compra
    public void eliminarCompra(int idCompra) throws SQLException {
        String sql = "DELETE FROM compra WHERE idCompra = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCompra); // Asignar ID de la compra
            stmt.executeUpdate(); // Ejecutar eliminación
        }
    }
}
