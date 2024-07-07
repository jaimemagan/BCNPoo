package org.example.pafpoo.ClasesCrud;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.example.pafpoo.Clases.Tarjeta;

public class TarjetaCrud {
    private final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC"; // URL de la base de datos
    private final String user = "tu_usuario"; // Usuario de la base de datos
    private final String password = "tu_contraseña"; // Contraseña de la base de datos

    // Método para crear una nueva tarjeta
    public void crearTarjeta(Tarjeta tarjeta) throws SQLException {
        String sql = "INSERT INTO tarjeta (numeroTarjeta, fechaExpiracion, tipoTarjeta, facilitador, idCliente) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarjeta.getNumeroTarjeta()); // Asignar número de tarjeta
            stmt.setDate(2, Date.valueOf(tarjeta.getFechaExpiracion())); // Asignar fecha de expiración
            stmt.setString(3, tarjeta.getTipoTarjeta()); // Asignar tipo de tarjeta
            stmt.setString(4, tarjeta.getFacilitador()); // Asignar facilitador
            stmt.setInt(5, tarjeta.getIdCliente()); // Asignar ID del cliente
            stmt.executeUpdate(); // Ejecutar la actualización
        }
    }

    // Método para obtener una tarjeta por su ID
    public Tarjeta obtenerTarjeta(int idTarjeta) throws SQLException {
        String sql = "SELECT * FROM tarjeta WHERE idTarjeta = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTarjeta); // Asignar ID de la tarjeta
            try (ResultSet rs = stmt.executeQuery()) { // Ejecutar consulta
                if (rs.next()) { // Sí hay resultado
                    Tarjeta tarjeta = new Tarjeta();
                    tarjeta.setIdTarjeta(rs.getInt("idTarjeta")); // Asignar ID de la tarjeta
                    tarjeta.setNumeroTarjeta(rs.getString("numeroTarjeta")); // Asignar número de tarjeta
                    tarjeta.setFechaExpiracion(rs.getDate("fechaExpiracion").toLocalDate()); // Asignar fecha de expiración
                    tarjeta.setTipoTarjeta(rs.getString("tipoTarjeta")); // Asignar tipo de tarjeta
                    tarjeta.setFacilitador(rs.getString("facilitador")); // Asignar facilitador
                    tarjeta.setIdCliente(rs.getInt("idCliente")); // Asignar ID del cliente
                    return tarjeta; // Devolver tarjeta
                }
            }
        }
        return null; // Devolver null si no se encuentra
    }

    // Método para obtener todas las tarjetas
    public List<Tarjeta> obtenerTodasLasTarjetas() throws SQLException {
        List<Tarjeta> tarjetas = new ArrayList<>();
        String sql = "SELECT * FROM tarjeta";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) { // Ejecutar consulta
            while (rs.next()) { // Mientras haya resultados
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setIdTarjeta(rs.getInt("idTarjeta")); // Asignar ID de la tarjeta
                tarjeta.setNumeroTarjeta(rs.getString("numeroTarjeta")); // Asignar número de tarjeta
                tarjeta.setFechaExpiracion(rs.getDate("fechaExpiracion").toLocalDate()); // Asignar fecha de expiración
                tarjeta.setTipoTarjeta(rs.getString("tipoTarjeta")); // Asignar tipo de tarjeta
                tarjeta.setFacilitador(rs.getString("facilitador")); // Asignar facilitador
                tarjeta.setIdCliente(rs.getInt("idCliente")); // Asignar ID del cliente
                tarjetas.add(tarjeta); // Añadir tarjeta a la lista
            }
        }
        return tarjetas; // Devolver lista de tarjetas
    }

    // Método para actualizar una tarjeta
    public void actualizarTarjeta(Tarjeta tarjeta) throws SQLException {
        String sql = "UPDATE tarjeta SET numeroTarjeta = ?, fechaExpiracion = ?, tipoTarjeta = ?, facilitador = ?, idCliente = ? WHERE idTarjeta = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarjeta.getNumeroTarjeta()); // Asignar número de tarjeta
            stmt.setDate(2, Date.valueOf(tarjeta.getFechaExpiracion())); // Asignar fecha de expiración
            stmt.setString(3, tarjeta.getTipoTarjeta()); // Asignar tipo de tarjeta
            stmt.setString(4, tarjeta.getFacilitador()); // Asignar facilitador
            stmt.setInt(5, tarjeta.getIdCliente()); // Asignar ID del cliente
            stmt.setInt(6, tarjeta.getIdTarjeta()); // Asignar ID de la tarjeta
            stmt.executeUpdate(); // Ejecutar actualización
        }
    }

    // Método para eliminar una tarjeta
    public void eliminarTarjeta(int idTarjeta) throws SQLException {
        String sql = "DELETE FROM tarjeta WHERE idTarjeta = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTarjeta); // Asignar ID de la tarjeta
            stmt.executeUpdate(); // Ejecutar eliminación
        }
    }
}
