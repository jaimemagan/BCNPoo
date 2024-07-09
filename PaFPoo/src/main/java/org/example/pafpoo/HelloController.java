package org.example.pafpoo; // 00090322 Definición del paquete para la clase

import javafx.event.ActionEvent; // 00090322 Importación de la clase ActionEvent para manejar eventos de acción
import javafx.fxml.FXML; // 00090322 Importación de la anotación FXML para enlazar con elementos definidos en el archivo FXML
import javafx.scene.control.ComboBox; // 00090322 Importación de la clase ComboBox para listas desplegables
import javafx.scene.control.DatePicker; // 00090322 Importación de la clase DatePicker para seleccionar fechas
import javafx.scene.control.ListView; // 00090322 Importación de la clase ListView para listas de elementos
import javafx.scene.control.TextField; // 00090322 Importación de la clase TextField para campos de texto
import javafx.scene.layout.AnchorPane; // 00090322 Importación de la clase AnchorPane para layout de anclaje
import javafx.scene.control.Alert; // 00090322 Importación de la clase Alert para mostrar cuadros de diálogo de alerta
import javafx.scene.control.Alert.AlertType; // 00090322 Importación de la clase AlertType para definir tipos de alertas
import org.example.pafpoo.Clases.Cliente; // 00090322 Importación de la clase Cliente
import org.example.pafpoo.ClasesCrud.ClienteCrud; // 00090322 Importación de la clase ClienteCrud para operaciones CRUD de Cliente
import org.example.pafpoo.ClasesCrud.CompraCrud; // 00090322 Importación de la clase CompraCrud para operaciones CRUD de Compra
import org.example.pafpoo.ClasesCrud.TarjetaCrud; // 00090322 Importación de la clase TarjetaCrud para operaciones CRUD de Tarjeta

import java.awt.Desktop; // 00090322 Importación de la clase Desktop para abrir archivos con la aplicación predeterminada
import java.io.File; // 00090322 Importación de la clase File para manejar archivos
import java.io.IOException; // 00090322 Importación de la clase IOException para manejar excepciones de entrada/salida
import java.sql.Date; // 00090322 Importación de la clase Date para manejar fechas SQL
import java.sql.SQLException; // 00090322 Importación de la clase SQLException para manejar excepciones SQL
import java.time.LocalDate; // 00090322 Importación de la clase LocalDate para manejar fechas locales
import java.util.List; // 00090322 Importación de la clase List para manejar listas
import java.util.stream.Stream; // 00090322 Importación de la clase Stream para operaciones con streams

public class HelloController { // 00090322 Definición de la clase HelloController

    @FXML // 00090322 Anotación FXML para enlazar con el elemento welcomeScreen del archivo FXML
    private AnchorPane welcomeScreen;

    @FXML // 00090322 Anotación FXML para enlazar con el elemento reportScreen del archivo FXML
    private AnchorPane reportScreen;

    @FXML // 00090322 Anotación FXML para enlazar con el elemento reportSelectionScreen del archivo FXML
    private AnchorPane reportSelectionScreen;

    @FXML // 00090322 Anotación FXML para enlazar con el elemento clienteComboBoxA del archivo FXML
    private ComboBox<Cliente> clienteComboBoxA;

    @FXML // 00090322 Anotación FXML para enlazar con el elemento startDatePicker del archivo FXML
    private DatePicker startDatePicker;

    @FXML // 00090322 Anotación FXML para enlazar con el elemento endDatePicker del archivo FXML
    private DatePicker endDatePicker;

    @FXML // 00090322 Anotación FXML para enlazar con el elemento clienteComboBoxB del archivo FXML
    private ComboBox<Cliente> clienteComboBoxB;

    @FXML // 00090322 Anotación FXML para enlazar con el elemento monthField del archivo FXML
    private TextField monthField;

    @FXML // 00090322 Anotación FXML para enlazar con el elemento yearField del archivo FXML
    private TextField yearField;

    @FXML // 00090322 Anotación FXML para enlazar con el elemento clienteComboBoxC del archivo FXML
    private ComboBox<Cliente> clienteComboBoxC;

    @FXML // 00090322 Anotación FXML para enlazar con el elemento facilitadorField del archivo FXML
    private TextField facilitadorField;

    @FXML // 00090322 Anotación FXML para enlazar con el elemento listView del archivo FXML
    private ListView<String> listView;

    private final ClienteCrud clienteCrud = new ClienteCrud(); // 00090322 Creación de una instancia de ClienteCrud para operaciones CRUD
    private final CompraCrud compraCrud = new CompraCrud(); // 00090322 Creación de una instancia de CompraCrud para operaciones CRUD
    private final TarjetaCrud tarjetaCrud = new TarjetaCrud(); // 00090322 Creación de una instancia de TarjetaCrud para operaciones CRUD

    @FXML // 00090322 Anotación FXML para indicar que este método se llama al inicializar la interfaz
    public void initialize() {
        try {
            List<Cliente> clientes = clienteCrud.obtenerTodosLosClientes(); // 00090322 Obtener todos los clientes de la base de datos
            clienteComboBoxA.getItems().addAll(clientes); // 00090322 Añadir los clientes al ComboBox A
            clienteComboBoxB.getItems().addAll(clientes); // 00090322 Añadir los clientes al ComboBox B
            clienteComboBoxC.getItems().addAll(clientes); // 00090322 Añadir los clientes al ComboBox C
        } catch (SQLException e) { // 00090322 Captura de la excepción en caso de error SQL
            e.printStackTrace(); // 00090322 Imprimir el stack trace del error
        }
    }

    @FXML // 00090322 Anotación FXML para indicar que este método es llamado desde la interfaz
    private void showReportScreen() {
        welcomeScreen.setVisible(false); // 00090322 Ocultar la pantalla de bienvenida
        reportScreen.setVisible(true); // 00090322 Mostrar la pantalla de reportes
        reportSelectionScreen.setVisible(false); // 00090322 Ocultar la pantalla de selección de reportes
    }

    @FXML // 00090322 Anotación FXML para indicar que este método es llamado desde la interfaz
    private void showReportSelectionScreen() {
        welcomeScreen.setVisible(false); // 00090322 Ocultar la pantalla de bienvenida
        reportScreen.setVisible(false); // 00090322 Ocultar la pantalla de reportes
        reportSelectionScreen.setVisible(true); // 00090322 Mostrar la pantalla de selección de reportes
    }

    @FXML // 00090322 Anotación FXML para indicar que este método es llamado desde la interfaz
    private void showWelcomeScreen() {
        reportScreen.setVisible(false); // 00090322 Ocultar la pantalla de reportes
        welcomeScreen.setVisible(true); // 00090322 Mostrar la pantalla de bienvenida
        reportSelectionScreen.setVisible(false); // 00090322 Ocultar la pantalla de selección de reportes
    }

    public void generateReportA(ActionEvent actionEvent) { // 00090322 Método para generar el Reporte A al recibir un evento de acción
        try {
            Cliente cliente = clienteComboBoxA.getValue(); // 00090322 Obtener el cliente seleccionado del ComboBox A
            if (cliente == null) { // 00090322 Validar si el cliente es nulo
                showAlert("Error", "Por favor, seleccione un cliente."); // 00090322 Mostrar alerta si no se seleccionó un cliente
                return; // 00090322 Salir del método si no se seleccionó un cliente
            }
            int clientId = cliente.getIdCliente().intValue(); // 00090322 Obtener el ID del cliente seleccionado
            LocalDate startDate = startDatePicker.getValue(); // 00090322 Obtener la fecha de inicio seleccionada
            LocalDate endDate = endDatePicker.getValue(); // 00090322 Obtener la fecha de fin seleccionada

            if (startDate == null || endDate == null) { // 00090322 Validar si las fechas de inicio o fin son nulas
                showAlert("Error", "Por favor, seleccione las fechas de inicio y fin."); // 00090322 Mostrar alerta si las fechas no están seleccionadas
                return; // 00090322 Salir del método si las fechas no están seleccionadas
            }

            List<String> reportAResults = ReportGenerator.generateReportA(clientId, Date.valueOf(startDate), Date.valueOf(endDate)); // 00090322 Generar el Reporte A y obtener los resultados
            listView.getItems().setAll(reportAResults); // 00090322 Añadir los resultados del reporte A al ListView

            ReportGenerator.saveReportToFile(reportAResults, "A"); // 00090322 Guardar el reporte A en un archivo

        } catch (SQLException | IOException e) { // 00090322 Captura de las excepciones SQL y IO
            e.printStackTrace(); // 00090322 Imprimir el stack trace del error
            showAlert("Error", "Ocurrió un error al generar el reporte."); // 00090322 Mostrar alerta de error al generar el reporte
        }
    }

    public void generateReportB(ActionEvent actionEvent) { // 00090322 Método para generar el Reporte B al recibir un evento de acción
        try {
            Cliente cliente = clienteComboBoxB.getValue(); // 00090322 Obtener el cliente seleccionado del ComboBox B
            if (cliente == null) { // 00090322 Validar si el cliente es nulo
                showAlert("Error", "Por favor, seleccione un cliente."); // 00090322 Mostrar alerta si no se seleccionó un cliente
                return; // 00090322 Salir del método si no se seleccionó un cliente
            }
            int clientId = cliente.getIdCliente().intValue(); // 00090322 Obtener el ID del cliente seleccionado
            int month = Integer.parseInt(monthField.getText()); // 00090322 Obtener el mes ingresado en el campo de texto
            int year = Integer.parseInt(yearField.getText()); // 00090322 Obtener el año ingresado en el campo de texto

            List<String> reportBResults = ReportGenerator.generateReportB(clientId, month, year); // 00090322 Generar el Reporte B y obtener los resultados
            listView.getItems().setAll(reportBResults); // 00090322 Añadir los resultados del reporte B al ListView

            ReportGenerator.saveReportToFile(reportBResults, "B"); // 00090322 Guardar el reporte B en un archivo

        } catch (SQLException | IOException e) { // 00090322 Captura de las excepciones SQL y IO
            e.printStackTrace(); // 00090322 Imprimir el stack trace del error
            showAlert("Error", "Ocurrió un error al generar el reporte."); // 00090322 Mostrar alerta de error al generar el reporte
        } catch (NumberFormatException e) { // 00090322 Captura de la excepción de formato de número
            showAlert("Error", "Por favor, ingrese valores válidos para mes y año."); // 00090322 Mostrar alerta de error si el formato del mes o año no es válido
        }
    }

    public void generateReportC(ActionEvent actionEvent) { // 00090322 Método para generar el Reporte C al recibir un evento de acción
        try {
            Cliente cliente = clienteComboBoxC.getValue(); // 00090322 Obtener el cliente seleccionado del ComboBox C
            if (cliente == null) { // 00090322 Validar si el cliente es nulo
                showAlert("Error", "Por favor, seleccione un cliente."); // 00090322 Mostrar alerta si no se seleccionó un cliente
                return; // 00090322 Salir del método si no se seleccionó un cliente
            }
            int clientId = cliente.getIdCliente().intValue(); // 00090322 Obtener el ID del cliente seleccionado

            List<String> reportCResults = ReportGenerator.generateReportC(clientId); // 00090322 Generar el Reporte C y obtener los resultados
            listView.getItems().setAll(reportCResults); // 00090322 Añadir los resultados del reporte C al ListView

            ReportGenerator.saveReportToFile(reportCResults, "C"); // 00090322 Guardar el reporte C en un archivo

        } catch (SQLException | IOException e) { // 00090322 Captura de las excepciones SQL y IO
            e.printStackTrace(); // 00090322 Imprimir el stack trace del error
            showAlert("Error", "Ocurrió un error al generar el reporte."); // 00090322 Mostrar alerta de error al generar el reporte
        }
    }

    public void generateReportD(ActionEvent actionEvent) { // 00090322 Método para generar el Reporte D al recibir un evento de acción
        try {
            String facilitator = facilitadorField.getText(); // 00090322 Obtener el texto ingresado en el campo de facilitador

            if (facilitator == null || facilitator.trim().isEmpty()) { // 00090322 Validar si el facilitador es nulo o vacío
                showAlert("Error", "Por favor, ingrese el nombre del facilitador."); // 00090322 Mostrar alerta si el facilitador no está ingresado
                return; // 00090322 Salir del método si el facilitador no está ingresado
            }

            List<String> reportDResults = ReportGenerator.generateReportD(facilitator); // 00090322 Generar el Reporte D y obtener los resultados
            listView.getItems().setAll(reportDResults); // 00090322 Añadir los resultados del reporte D al ListView

            ReportGenerator.saveReportToFile(reportDResults, "D"); // 00090322 Guardar el reporte D en un archivo

        } catch (SQLException | IOException e) { // 00090322 Captura de las excepciones SQL y IO
            e.printStackTrace(); // 00090322 Imprimir el stack trace del error
            showAlert("Error", "Ocurrió un error al generar el reporte."); // 00090322 Mostrar alerta de error al generar el reporte
        }
    }

    @FXML // 00090322 Anotación FXML para indicar que este método es llamado desde la interfaz
    private void openReportA() {
        openLatestReportFile("A"); // 00090322 Llamada al método para abrir el último archivo del Reporte A
    }

    @FXML // 00090322 Anotación FXML para indicar que este método es llamado desde la interfaz
    private void openReportB() {
        openLatestReportFile("B"); // 00090322 Llamada al método para abrir el último archivo del Reporte B
    }

    @FXML // 00090322 Anotación FXML para indicar que este método es llamado desde la interfaz
    private void openReportC() {
        openLatestReportFile("C"); // 00090322 Llamada al método para abrir el último archivo del Reporte C
    }

    @FXML // 00090322 Anotación FXML para indicar que este método es llamado desde la interfaz
    private void openReportD() {
        openLatestReportFile("D"); // 00090322 Llamada al método para abrir el último archivo del Reporte D
    }

    private void openLatestReportFile(String reportType) { // 00090322 Método para abrir el último archivo de reporte dado su tipo
        File dir = new File("reportes/"); // 00090322 Crear una instancia de File con el directorio de reportes
        File[] matchingFiles = dir.listFiles((d, name) -> name.startsWith("Reporte_" + reportType + "_") && name.endsWith(".txt")); // 00090322 Obtener los archivos que coinciden con el tipo de reporte
        if (matchingFiles != null && matchingFiles.length > 0) { // 00090322 Comprobar si hay archivos que coinciden
            File latestFile = Stream.of(matchingFiles).max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified())).orElse(null); // 00090322 Obtener el archivo más reciente
            if (latestFile != null) { // 00090322 Validar si el archivo más reciente no es nulo
                try {
                    Desktop.getDesktop().open(latestFile); // 00090322 Intentar abrir el archivo con la aplicación predeterminada
                } catch (IOException e) { // 00090322 Captura de la excepción en caso de error de entrada/salida
                    showAlert("Error", "No se pudo abrir el archivo: " + latestFile.getAbsolutePath()); // 00090322 Mostrar una alerta de error si no se pudo abrir el archivo
                    e.printStackTrace(); // 00090322 Imprimir el stack trace del error
                }
            }
        } else {
            showAlert("Error", "No se encontró ningún archivo para el reporte: " + reportType); // 00090322 Mostrar una alerta de error si no se encontraron archivos para el reporte
        }
    }

    private void showAlert(String title, String message) { // 00090322 Método para mostrar una alerta con un título y un mensaje
        Alert alert = new Alert(Alert.AlertType.ERROR); // 00090322 Crear una instancia de Alert de tipo ERROR
        alert.setTitle(title); // 00090322 Establecer el título de la alerta
        alert.setHeaderText(null); // 00090322 Establecer el texto de encabezado como null
        alert.setContentText(message); // 00090322 Establecer el contenido de la alerta con el mensaje
        alert.showAndWait(); // 00090322 Mostrar la alerta y esperar a que el usuario la cierre
    }
}
