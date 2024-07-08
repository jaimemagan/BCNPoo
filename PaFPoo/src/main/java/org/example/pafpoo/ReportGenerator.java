package org.example.pafpoo;

import org.example.pafpoo.Clases.Cliente;
import org.example.pafpoo.Clases.Compra;
import org.example.pafpoo.Clases.Tarjeta;
import org.example.pafpoo.ClasesCrud.ClienteCrud;
import org.example.pafpoo.ClasesCrud.CompraCrud;
import org.example.pafpoo.ClasesCrud.TarjetaCrud;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {
    private static final ClienteCrud clienteCrud = new ClienteCrud();
    private static final CompraCrud compraCrud = new CompraCrud();
    private static final TarjetaCrud tarjetaCrud = new TarjetaCrud();

    private static final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "Poo.012024";

    public static List<String> generateReportA(int clientId, Date startDate, Date endDate) throws SQLException, IOException {
        List<Compra> compras = compraCrud.obtenerComprasPorClienteYPeriodo(clientId, startDate, endDate);
        List<String> reportLines = new ArrayList<>();
        reportLines.add("Reporte A: Listar compras de un cliente en un periodo");
        reportLines.add("Cliente ID: " + clientId);
        reportLines.add("Periodo: " + startDate + " - " + endDate);
        reportLines.add("-----------------------------------------------------");
        for (Compra compra : compras) {
            reportLines.add(compra.toString());
        }
        return reportLines;
    }

    public static List<String> generateReportB(int clientId, int month, int year) throws SQLException, IOException {
        BigDecimal totalGastado = compraCrud.obtenerTotalGastadoPorClienteYMes(clientId, month, year);
        List<String> reportLines = new ArrayList<>();
        reportLines.add("Reporte B: Total gastado por un cliente en un mes específico");
        reportLines.add("Cliente ID: " + clientId);
        reportLines.add("Mes: " + month + ", Año: " + year);
        reportLines.add("Total gastado: " + totalGastado);
        return reportLines;
    }

    public static List<String> generateReportC(int clientId) throws SQLException, IOException {
        List<Tarjeta> tarjetas = tarjetaCrud.obtenerTarjetasPorCliente(clientId);
        List<String> reportLines = new ArrayList<>();
        reportLines.add("Reporte C: Listar todas las tarjetas de un cliente");
        reportLines.add("Cliente ID: " + clientId);
        reportLines.add("-----------------------------------------------------");

        // Filtrar y formatear las tarjetas
        List<String> tarjetasCredito = new ArrayList<>();
        List<String> tarjetasDebito = new ArrayList<>();

        for (Tarjeta tarjeta : tarjetas) {
            String tarjetaFormateada = "XXXX XXXX XXXX " + tarjeta.getNumero().substring(tarjeta.getNumero().length() - 4);
            if (tarjeta.getTipo().equalsIgnoreCase("credito")) {
                tarjetasCredito.add(tarjetaFormateada);
            } else if (tarjeta.getTipo().equalsIgnoreCase("debito")) {
                tarjetasDebito.add(tarjetaFormateada);
            }
        }

        reportLines.add("Tarjetas de crédito:");
        if (tarjetasCredito.isEmpty()) {
            reportLines.add("N/A (No cuenta con tarjetas de este tipo)");
        } else {
            reportLines.addAll(tarjetasCredito);
        }

        reportLines.add("Tarjetas de Débito:");
        if (tarjetasDebito.isEmpty()) {
            reportLines.add("N/A (No cuenta con tarjetas de este tipo)");
        } else {
            reportLines.addAll(tarjetasDebito);
        }

        return reportLines;
    }

    public static List<String> generateReportD(String facilitator) throws SQLException, IOException {
        int facilitatorId = getFacilitatorIdByName(facilitator);
        List<Cliente> clientes = tarjetaCrud.obtenerClientesPorFacilitador(facilitatorId);
        List<String> reportLines = new ArrayList<>();
        reportLines.add("Reporte D: Clientes por facilitador de tarjeta");
        reportLines.add("Facilitador: " + facilitator);
        reportLines.add("-----------------------------------------------------");
        for (Cliente cliente : clientes) {
            reportLines.add(cliente.toString());
        }
        return reportLines;
    }

    private static int getFacilitatorIdByName(String name) throws SQLException {
        String sql = "SELECT id_facilitador FROM facilitador WHERE nombre = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_facilitador");
                }
            }
        }
        throw new SQLException("Facilitator not found: " + name);
    }
}
