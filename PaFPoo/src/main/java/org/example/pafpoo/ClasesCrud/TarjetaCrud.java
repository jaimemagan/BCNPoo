package org.example.pafpoo.ClasesCrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.example.pafpoo.Clases.Cliente;
import org.example.pafpoo.Clases.Tarjeta;

public class TarjetaCrud {
    private final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC";
    private final String user = "root";
    private final String password = "Poo.012024";

    public void crearTarjeta(Tarjeta tarjeta) throws SQLException {
        String sql = "INSERT INTO tarjeta (numero_tarjeta, fecha_expiracion, id_tipo_tarjeta, id_facilitador, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarjeta.getNumeroTarjeta());
            stmt.setDate(2, Date.valueOf(tarjeta.getFechaExpiracion()));
            stmt.setInt(3, tarjeta.getIdTipoTarjeta());
            stmt.setInt(4, tarjeta.getIdFacilitador());
            stmt.setInt(5, tarjeta.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public List<Tarjeta> obtenerTarjetasPorCliente(int idCliente) throws SQLException {
        List<Tarjeta> tarjetas = new ArrayList<>();
        String sql = "SELECT * FROM tarjeta WHERE id_cliente = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarjeta tarjeta = new Tarjeta();
                    tarjeta.setIdTarjeta(rs.getInt("id_tarjeta"));
                    tarjeta.setNumeroTarjeta(rs.getString("numero_tarjeta"));
                    tarjeta.setFechaExpiracion(rs.getDate("fecha_expiracion").toLocalDate());
                    tarjeta.setIdTipoTarjeta(rs.getInt("id_tipo_tarjeta"));
                    tarjeta.setIdFacilitador(rs.getInt("id_facilitador"));
                    tarjeta.setIdCliente(rs.getInt("id_cliente"));
                    tarjetas.add(tarjeta);
                }
            }
        }
        return tarjetas;
    }

    public List<Cliente> obtenerClientesPorFacilitador(int idFacilitador) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT DISTINCT c.* FROM cliente c JOIN tarjeta t ON c.id_cliente = t.id_cliente WHERE t.id_facilitador = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFacilitador);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getLong("id_cliente"));
                    cliente.setNombreCompleto(rs.getString("nombre_completo"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setTelefono(rs.getString("telefono"));
                    clientes.add(cliente);
                }
            }
        }
        return clientes;
    }
}
