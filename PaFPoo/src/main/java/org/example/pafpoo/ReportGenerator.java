package org.example.pafpoo;

import org.example.pafpoo.Clases.Cliente; //00090322 Importación de la clase Cliente.
import org.example.pafpoo.Clases.Compra; //00090322 Importación de la clase Compra.
import org.example.pafpoo.Clases.Tarjeta; //00090322 Importación de la clase Tarjeta.
import org.example.pafpoo.ClasesCrud.ClienteCrud; //00090322 Importación de la clase ClienteCrud para operaciones CRUD con Cliente.
import org.example.pafpoo.ClasesCrud.CompraCrud; //00090322 Importación de la clase CompraCrud para operaciones CRUD con Compra.
import org.example.pafpoo.ClasesCrud.TarjetaCrud; //00090322 Importación de la clase TarjetaCrud para operaciones CRUD con Tarjeta.

import java.io.BufferedWriter; //00090322 Importación de BufferedWriter para escribir archivos.
import java.io.FileWriter; //00090322 Importación de FileWriter para escribir archivos.
import java.io.IOException; //00090322 Importación de IOException para manejar excepciones de entrada/salida.
import java.math.BigDecimal; //00090322 Importación de BigDecimal para manejar valores decimales grandes y precisos.
import java.sql.Connection; //00090322 Importación de Connection para manejar conexiones a la base de datos.
import java.sql.Date; //00090322 Importación de Date para manejar fechas.
import java.sql.DriverManager; //00090322 Importación de DriverManager para manejar controladores de bases de datos.
import java.sql.PreparedStatement; //00090322 Importación de PreparedStatement para ejecutar sentencias SQL precompiladas.
import java.sql.ResultSet; //00090322 Importación de ResultSet para manejar los resultados de consultas SQL.
import java.sql.SQLException; //00090322 Importación de SQLException para manejar excepciones de SQL.
import java.time.LocalDateTime; //00090322 Importación de LocalDateTime para manejar fechas y horas.
import java.time.format.DateTimeFormatter; //00090322 Importación de DateTimeFormatter para formatear fechas y horas.
import java.util.ArrayList; //00090322 Importación de ArrayList para manejar listas dinámicas.
import java.util.List; //00090322 Importación de List para manejar listas.

public class ReportGenerator { //00090322 Clase principal para la generación de reportes.
    private static final ClienteCrud clienteCrud = new ClienteCrud(); //00090322 Inicialización de ClienteCrud para operaciones CRUD con Cliente.
    private static final CompraCrud compraCrud = new CompraCrud(); //00090322 Inicialización de CompraCrud para operaciones CRUD con Compra.
    private static final TarjetaCrud tarjetaCrud = new TarjetaCrud(); //00090322 Inicialización de TarjetaCrud para operaciones CRUD con Tarjeta.

    private static final String url = "jdbc:mysql://localhost:3306/bcn?serverTimezone=UTC"; //00090322 URL de conexión a la base de datos.
    private static final String user = "root"; //00090322 Usuario de la base de datos.
    private static final String password = "Poo.012024"; //00090322 Contraseña de la base de datos.
    private static final String reportDir = "reportes/"; //00090322 Directorio donde se guardarán los reportes.

    public static List<String> generateReportA(int clientId, Date startDate, Date endDate) throws SQLException, IOException { //00090322 Método para generar el reporte A.
        List<Compra> compras = compraCrud.obtenerComprasPorClienteYPeriodo(clientId, startDate, endDate); //00090322 Obtiene las compras de un cliente en un periodo.
        List<String> reportLines = new ArrayList<>(); //00090322 Lista para almacenar las líneas del reporte.
        reportLines.add("Reporte A: Listar compras de un cliente en un periodo"); //00090322 Agrega el título del reporte.
        reportLines.add("Cliente ID: " + clientId); //00090322 Agrega el ID del cliente al reporte.
        reportLines.add("Periodo: " + startDate + " - " + endDate); //00090322 Agrega el periodo del reporte.
        reportLines.add("-----------------------------------------------------"); //00090322 Agrega una línea divisoria al reporte.
        for (Compra compra : compras) { //00090322 Itera sobre las compras del cliente.
            reportLines.add(compra.toString()); //00090322 Agrega cada compra al reporte.
        }
        return reportLines; //00090322 Retorna las líneas del reporte.
    }

    public static List<String> generateReportB(int clientId, int month, int year) throws SQLException, IOException { //00090322 Método para generar el reporte B.
        BigDecimal totalGastado = compraCrud.obtenerTotalGastadoPorClienteYMes(clientId, month, year); //00090322 Obtiene el total gastado por un cliente en un mes específico.
        List<String> reportLines = new ArrayList<>(); //00090322 Lista para almacenar las líneas del reporte.
        reportLines.add("Reporte B: Total gastado por un cliente en un mes específico"); //00090322 Agrega el título del reporte.
        reportLines.add("Cliente ID: " + clientId); //00090322 Agrega el ID del cliente al reporte.
        reportLines.add("Mes: " + month + ", Año: " + year); //00090322 Agrega el mes y el año al reporte.
        reportLines.add("Total gastado: " + totalGastado); //00090322 Agrega el total gastado al reporte.
        return reportLines; //00090322 Retorna las líneas del reporte.
    }

    public static List<String> generateReportC(int clientId) throws SQLException, IOException { //00090322 Método para generar el reporte C.
        List<Tarjeta> tarjetas = tarjetaCrud.obtenerTarjetasPorCliente(clientId); //00090322 Obtiene las tarjetas de un cliente.
        List<String> reportLines = new ArrayList<>(); //00090322 Lista para almacenar las líneas del reporte.
        reportLines.add("Reporte C: Listar todas las tarjetas de un cliente"); //00090322 Agrega el título del reporte.
        reportLines.add("Cliente ID: " + clientId); //00090322 Agrega el ID del cliente al reporte.
        reportLines.add("-----------------------------------------------------"); //00090322 Agrega una línea divisoria al reporte.

        List<String> tarjetasCredito = new ArrayList<>(); //00090322 Lista para almacenar las tarjetas de crédito.
        List<String> tarjetasDebito = new ArrayList<>(); //00090322 Lista para almacenar las tarjetas de débito.

        for (Tarjeta tarjeta : tarjetas) { //00090322 Itera sobre las tarjetas del cliente.
            String tarjetaFormateada = "XXXX XXXX XXXX " + tarjeta.getNumero().substring(tarjeta.getNumero().length() - 4); //00090322 Formatea el número de la tarjeta.
            if (tarjeta.getTipo().equalsIgnoreCase("credito")) { //00090322 Verifica si la tarjeta es de crédito.
                tarjetasCredito.add(tarjetaFormateada); //00090322 Agrega la tarjeta de crédito a la lista.
            } else if (tarjeta.getTipo().equalsIgnoreCase("debito")) { //00090322 Verifica si la tarjeta es de débito.
                tarjetasDebito.add(tarjetaFormateada); //00090322 Agrega la tarjeta de débito a la lista.
            }
        }

        reportLines.add("Tarjetas de crédito:"); //00090322 Agrega el título de las tarjetas de crédito al reporte.
        if (tarjetasCredito.isEmpty()) { //00090322 Verifica si no hay tarjetas de crédito.
            reportLines.add("N/A (No cuenta con tarjetas de este tipo)"); //00090322 Agrega un mensaje indicando que no hay tarjetas de crédito.
        } else {
            reportLines.addAll(tarjetasCredito); //00090322 Agrega las tarjetas de crédito al reporte.
        }

        reportLines.add("Tarjetas de Débito:"); //00090322 Agrega el título de las tarjetas de débito al reporte.
        if (tarjetasDebito.isEmpty()) { //00090322 Verifica si no hay tarjetas de débito.
            reportLines.add("N/A (No cuenta con tarjetas de este tipo)"); //00090322 Agrega un mensaje indicando que no hay tarjetas de débito.
        } else {
            reportLines.addAll(tarjetasDebito); //00090322 Agrega las tarjetas de débito al reporte.
        }

        return reportLines; //00090322 Retorna las líneas del reporte.
    }

    public static List<String> generateReportD(String facilitator) throws SQLException, IOException { //00090322 Método para generar el reporte D.
        int facilitatorId = getFacilitatorIdByName(facilitator); //00090322 Obtiene el ID del facilitador por su nombre.
        List<Cliente> clientes = tarjetaCrud.obtenerClientesPorFacilitador(facilitatorId); //00090322 Obtiene los clientes asociados a un facilitador.
        List<String> reportLines = new ArrayList<>(); //00090322 Lista para almacenar las líneas del reporte.
        reportLines.add("Reporte D: Clientes por facilitador de tarjeta"); //00090322 Agrega el título del reporte.
        reportLines.add("Facilitador: " + facilitator); //00090322 Agrega el nombre del facilitador al reporte.
        reportLines.add("-----------------------------------------------------"); //00090322 Agrega una línea divisoria al reporte.
        for (Cliente cliente : clientes) { //00090322 Itera sobre los clientes asociados al facilitador.
            reportLines.add(cliente.toString()); //00090322 Agrega cada cliente al reporte.
        }
        return reportLines; //00090322 Retorna las líneas del reporte.
    }

    private static int getFacilitatorIdByName(String name) throws SQLException { //00090322 Método para obtener el ID del facilitador por su nombre.
        String sql = "SELECT id_facilitador FROM facilitador WHERE nombre = ?"; //00090322 Consulta SQL para obtener el ID del facilitador.
        try (Connection conn = DriverManager.getConnection(url, user, password); //00090322 Establece la conexión con la base de datos.
             PreparedStatement stmt = conn.prepareStatement(sql)) { //00090322 Prepara la consulta SQL.
            stmt.setString(1, name); //00090322 Establece el nombre del facilitador en la consulta.
            try (ResultSet rs = stmt.executeQuery()) { //00090322 Ejecuta la consulta y obtiene el resultado.
                if (rs.next()) { //00090322 Verifica si hay un resultado.
                    return rs.getInt("id_facilitador"); //00090322 Retorna el ID del facilitador.
                }
            }
        }
        throw new SQLException("Facilitator not found: " + name); //00090322 Lanza una excepción si no se encuentra el facilitador.
    }

    public static void saveReportToFile(List<String> reportLines, String reportType) throws IOException { //00090322 Método para guardar el reporte en un archivo.
        LocalDateTime now = LocalDateTime.now(); //00090322 Obtiene la fecha y hora actuales.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"); //00090322 Formatea la fecha y hora.
        String formattedDateTime = now.format(formatter); //00090322 Formatea la fecha y hora actuales.
        String fileName = reportDir + "Reporte_" + reportType + "_" + formattedDateTime + ".txt"; //00090322 Genera el nombre del archivo del reporte.

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) { //00090322 Abre un BufferedWriter para escribir el archivo.
            for (String line : reportLines) { //00090322 Itera sobre las líneas del reporte.
                writer.write(line); //00090322 Escribe cada línea en el archivo.
                writer.newLine(); //00090322 Escribe una nueva línea en el archivo.
            }
        }
    }
}


