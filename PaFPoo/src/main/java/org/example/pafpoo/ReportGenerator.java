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
import java.util.List;

public class ReportGenerator {
    private static final ClienteCrud clienteCrud = new ClienteCrud();
    private static final CompraCrud compraCrud = new CompraCrud();
    private static final TarjetaCrud tarjetaCrud = new TarjetaCrud();

    private static final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "Poo.012024";

    public static void generateReportA(int clientId, Date startDate, Date endDate) throws SQLException, IOException {
        List<Compra> compras = compraCrud.obtenerComprasPorClienteYPeriodo(clientId, startDate, endDate);
        String fileName = "Reportes/ReporteA_" + LocalDateTime.now() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Reporte A: Listar compras de un cliente en un periodo\n");
            writer.write("Cliente ID: " + clientId + "\n");
            writer.write("Periodo: " + startDate + " - " + endDate + "\n");
            writer.write("-----------------------------------------------------\n");
            for (Compra compra : compras) {
                writer.write(compra.toString() + "\n");
            }
        }
    }

    public static void generateReportB(int clientId, int month, int year) throws SQLException, IOException {
        BigDecimal totalGastado = compraCrud.obtenerTotalGastadoPorClienteYMes(clientId, month, year);
        String fileName = "Reportes/ReporteB_" + LocalDateTime.now() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Reporte B: Total gastado por un cliente en un mes específico\n");
            writer.write("Cliente ID: " + clientId + "\n");
            writer.write("Mes: " + month + ", Año: " + year + "\n");
            writer.write("Total gastado: " + totalGastado + "\n");
        }
    }

    public static void generateReportC(int clientId) throws SQLException, IOException {
        List<Tarjeta> tarjetas = tarjetaCrud.obtenerTarjetasPorCliente(clientId);
        String fileName = "Reportes/ReporteC_" + LocalDateTime.now() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Reporte C: Listar todas las tarjetas de un cliente\n");
            writer.write("Cliente ID: " + clientId + "\n");
            writer.write("-----------------------------------------------------\n");
            for (Tarjeta tarjeta : tarjetas) {
                writer.write(tarjeta.toString() + "\n");
            }
        }
    }

    public static void generateReportD(String facilitator) throws SQLException, IOException {
        int facilitatorId = getFacilitatorIdByName(facilitator);
        List<Cliente> clientes = tarjetaCrud.obtenerClientesPorFacilitador(facilitatorId);
        String fileName = "Reportes/ReporteD_" + LocalDateTime.now() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Reporte D: Clientes por facilitador de tarjeta\n");
            writer.write("Facilitador: " + facilitator + "\n");
            writer.write("-----------------------------------------------------\n");
            for (Cliente cliente : clientes) {
                writer.write(cliente.toString() + "\n");
            }
        }
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
