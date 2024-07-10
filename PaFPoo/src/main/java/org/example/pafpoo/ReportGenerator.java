package org.example.pafpoo; // Define el paquete al que pertenece esta clase

// Importa las clases necesarias
import org.example.pafpoo.Clases.Cliente; // Importa la clase Cliente
import org.example.pafpoo.Clases.Compra; // Importa la clase Compra
import org.example.pafpoo.Clases.Tarjeta; // Importa la clase Tarjeta
import org.example.pafpoo.ClasesCrud.ClienteCrud; // Importa la clase ClienteCrud para operaciones CRUD con clientes
import org.example.pafpoo.ClasesCrud.CompraCrud; // Importa la clase CompraCrud para operaciones CRUD con compras
import org.example.pafpoo.ClasesCrud.TarjetaCrud; // Importa la clase TarjetaCrud para operaciones CRUD con tarjetas

// Importa clases necesarias para la entrada/salida y manejo de archivos
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Importa clases necesarias para operaciones aritméticas y manejo de fechas
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator { // Define la clase ReportGenerator

    // Instancias estáticas de las clases CRUD para realizar operaciones en la base de datos
    private static final ClienteCrud clienteCrud = new ClienteCrud();
    private static final CompraCrud compraCrud = new CompraCrud();
    private static final TarjetaCrud tarjetaCrud = new TarjetaCrud();

    // Variables estáticas para la conexión a la base de datos
    private static final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC"; // URL de conexión a la base de datos
    private static final String user = "root"; // Usuario de la base de datos
    private static final String password = "madafakA23"; // Contraseña de la base de datos
    private static final String reportDir = "reportes/"; // Directorio donde se guardarán los reportes

    // Genera el reporte A: Listar compras de un cliente en un periodo
    public static List<String> generateReportA(int clientId, Date startDate, Date endDate) throws SQLException, IOException {
        // Obtiene las compras del cliente en el periodo especificado
        List<Compra> compras = compraCrud.obtenerComprasPorClienteYPeriodo(clientId, startDate, endDate);
        // Lista para almacenar las líneas del reporte
        List<String> reportLines = new ArrayList<>();
        // Agrega encabezados y datos al reporte
        reportLines.add("Reporte A: Listar compras de un cliente en un periodo");
        reportLines.add("Cliente ID: " + clientId);
        reportLines.add("Periodo: " + startDate + " - " + endDate);
        reportLines.add("-----------------------------------------------------");
        // Agrega las compras al reporte
        for (Compra compra : compras) {
            reportLines.add(compra.toString());
        }
        // Retorna las líneas del reporte
        return reportLines;
    }

    // Genera el reporte B: Total gastado por un cliente en un mes específico
    public static List<String> generateReportB(int clientId, int month, int year) throws SQLException, IOException {
        // Obtiene el total gastado por el cliente en el mes y año especificados
        BigDecimal totalGastado = compraCrud.obtenerTotalGastadoPorClienteYMes(clientId, month, year);
        // Lista para almacenar las líneas del reporte
        List<String> reportLines = new ArrayList<>();
        // Agrega encabezados y datos al reporte
        reportLines.add("Reporte B: Total gastado por un cliente en un mes específico");
        reportLines.add("Cliente ID: " + clientId);
        reportLines.add("Mes: " + month + ", Año: " + year);
        reportLines.add("Total gastado: " + totalGastado);
        // Retorna las líneas del reporte
        return reportLines;
    }

    // Genera el reporte C: Listar todas las tarjetas de un cliente
    public static List<String> generateReportC(int clientId) throws SQLException, IOException {
        // Obtiene todas las tarjetas del cliente
        List<Tarjeta> tarjetas = tarjetaCrud.obtenerTarjetasPorCliente(clientId);
        // Lista para almacenar las líneas del reporte
        List<String> reportLines = new ArrayList<>();
        // Agrega encabezados y datos al reporte
        reportLines.add("Reporte C: Listar todas las tarjetas de un cliente");
        reportLines.add("Cliente ID: " + clientId);
        reportLines.add("-----------------------------------------------------");

        // Listas para tarjetas de crédito y débito
        List<String> tarjetasCredito = new ArrayList<>();
        List<String> tarjetasDebito = new ArrayList<>();

        // Itera sobre las tarjetas y las clasifica en crédito o débito
        for (Tarjeta tarjeta : tarjetas) {
            // Formatea el número de tarjeta para mostrar solo los últimos 4 dígitos
            String tarjetaFormateada = "XXXX XXXX XXXX " + tarjeta.getNumero().substring(tarjeta.getNumero().length() - 4);
            if (tarjeta.getTipo().equalsIgnoreCase("credito")) { // Si es tarjeta de crédito
                tarjetasCredito.add(tarjetaFormateada);
            } else if (tarjeta.getTipo().equalsIgnoreCase("debito")) { // Si es tarjeta de débito
                tarjetasDebito.add(tarjetaFormateada);
            }
        }

        // Agrega tarjetas de crédito al reporte
        reportLines.add("Tarjetas de crédito:");
        if (tarjetasCredito.isEmpty()) {
            reportLines.add("N/A (No cuenta con tarjetas de este tipo)"); // Si no hay tarjetas de crédito
        } else {
            reportLines.addAll(tarjetasCredito); // Agrega las tarjetas de crédito a la lista de líneas del reporte
        }

        // Agrega tarjetas de débito al reporte
        reportLines.add("Tarjetas de Débito:");
        if (tarjetasDebito.isEmpty()) {
            reportLines.add("N/A (No cuenta con tarjetas de este tipo)"); // Si no hay tarjetas de débito
        } else {
            reportLines.addAll(tarjetasDebito); // Agrega las tarjetas de débito a la lista de líneas del reporte
        }

        // Retorna las líneas del reporte
        return reportLines;
    }

    // Genera el reporte D: Clientes por facilitador de tarjeta
    public static List<String> generateReportD(String facilitator) throws SQLException, IOException {
        // Obtiene el ID del facilitador por su nombre
        int facilitatorId = getFacilitatorIdByName(facilitator);
        // Obtiene los clientes del facilitador especificado
        List<Cliente> clientes = tarjetaCrud.obtenerClientesPorFacilitador(facilitatorId);
        // Lista para almacenar las líneas del reporte
        List<String> reportLines = new ArrayList<>();
        // Agrega encabezados y datos al reporte
        reportLines.add("Reporte D: Clientes por facilitador de tarjeta");
        reportLines.add("Facilitador: " + facilitator);
        reportLines.add("-----------------------------------------------------");
        // Agrega los clientes al reporte
        for (Cliente cliente : clientes) {
            reportLines.add(cliente.toString());
        }
        // Retorna las líneas del reporte
        return reportLines;
    }

    // Método privado para obtener el ID de un facilitador por su nombre
    private static int getFacilitatorIdByName(String name) throws SQLException {
        // Define la consulta SQL para obtener el ID del facilitador
        String sql = "SELECT idfacilitador FROM facilitador WHERE nombre = ?";
        // Usa un bloque try-with-resources para asegurar que los recursos se cierren correctamente
        try (Connection conn = DriverManager.getConnection(url, user, password); // Obtiene una conexión a la base de datos
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara la consulta SQL
            stmt.setString(1, name); // Establece el nombre del facilitador en el primer parámetro
            try (ResultSet rs = stmt.executeQuery()) { // Ejecuta la consulta SQL y obtiene el resultado
                if (rs.next()) { // Si se encuentra un resultado
                    return rs.getInt("idfacilitador"); // Retorna el ID del facilitador
                }
            }
        }
        // Lanza una excepción si no se encuentra el facilitador
        throw new SQLException("Facilitator not found: " + name);
    }

    // Método para guardar el reporte en un archivo
    public static void saveReportToFile(List<String> reportLines, String reportType) throws IOException {
        // Crear el directorio si no existe
        File directory = new File(reportDir);
        if (!directory.exists()) { // Si el directorio no existe
            directory.mkdirs(); // Crear el directorio
        }

        // Obtener la fecha y hora actual y formatearla
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = now.format(formatter);
        // Definir el nombre del archivo basado en el tipo de reporte y la fecha/hora actual
        String fileName = reportDir + "Reporte_" + reportType + "_" + formattedDateTime + ".txt";

        // Usar un BufferedWriter para escribir las líneas del reporte en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : reportLines) { // Iterar sobre las líneas del reporte
                writer.write(line); // Escribir cada línea en el archivo
                writer.newLine(); // Escribir una nueva línea
            }
        }
    }
}
