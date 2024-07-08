package org.example.pafpoo.ClasesCrud;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.example.pafpoo.Clases.Compra;
import java.math.BigDecimal;


public class CompraCrud {
    private final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC";
    private final String user = "root";
    private final String password = "Poo.012024";

    public void crearCompra(Compra compra) throws SQLException {
        String sql = "INSERT INTO compra (fecha_compra, monto_gastado, descripcion, id_tarjeta) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(compra.getFechaCompra()));
            stmt.setBigDecimal(2, compra.getMontoGastado());
            stmt.setString(3, compra.getDescripcion());
            stmt.setInt(4, compra.getIdTarjeta());
            stmt.executeUpdate();
        }
    }

    public List<Compra> obtenerComprasPorClienteYPeriodo(int idCliente, Date fechaInicio, Date fechaFin) throws SQLException {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT c.* FROM compra c JOIN tarjeta t ON c.id_tarjeta = t.id_tarjeta WHERE t.id_cliente = ? AND c.fecha_compra BETWEEN ? AND ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setDate(2, fechaInicio);
            stmt.setDate(3, fechaFin);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Compra compra = new Compra();
                    compra.setIdCompra(rs.getInt("id_compra"));
                    compra.setFechaCompra(rs.getTimestamp("fecha_compra").toLocalDateTime());
                    compra.setMontoGastado(rs.getBigDecimal("monto_gastado"));
                    compra.setDescripcion(rs.getString("descripcion"));
                    compra.setIdTarjeta(rs.getInt("id_tarjeta"));
                    compras.add(compra);
                }
            }
        }
        return compras;
    }

    public BigDecimal obtenerTotalGastadoPorClienteYMes(int idCliente, int mes, int año) throws SQLException {
        String sql = "SELECT SUM(c.monto_gastado) AS total FROM compra c JOIN tarjeta t ON c.id_tarjeta = t.id_tarjeta WHERE t.id_cliente = ? AND MONTH(c.fecha_compra) = ? AND YEAR(c.fecha_compra) = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, mes);
            stmt.setInt(3, año);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("total");
                }
            }
        }
        return BigDecimal.ZERO;
    }
}

