package org.example.pafpoo.ClasesCrud;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.pafpoo.Clases.Cliente;

public class ClienteCrud {
    private final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC";
    private final String user = "root";
    private final String password = "Poo.012024";

    public List<Cliente> obtenerTodosLosClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getLong("ID_CLIENTE"));
                cliente.setNombreCompleto(rs.getString("NOMBRE_COMPLETO"));
                cliente.setDireccion(rs.getString("DIRECCION"));
                cliente.setTelefono(rs.getString("TELEFONO"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
