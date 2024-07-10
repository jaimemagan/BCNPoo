package org.example.pafpoo; // Definición del paquete para la clase

import javafx.event.ActionEvent; // Importación de la clase ActionEvent para manejar eventos de acción
import javafx.fxml.FXML; // Importación de la anotación FXML para enlazar con elementos definidos en el archivo FXML
import javafx.scene.control.ComboBox; // Importación de la clase ComboBox para listas desplegables
import javafx.scene.control.DatePicker; // Importación de la clase DatePicker para seleccionar fechas
import javafx.scene.control.ListView; // Importación de la clase ListView para listas de elementos
import javafx.scene.control.TextField; // Importación de la clase TextField para campos de texto
import javafx.scene.layout.AnchorPane; // Importación de la clase AnchorPane para layout de anclaje
import javafx.scene.control.Alert; // Importación de la clase Alert para mostrar cuadros de diálogo de alerta
import javafx.scene.control.Alert.AlertType; // Importación de la clase AlertType para definir tipos de alertas
import org.example.pafpoo.Clases.Cliente; // Importación de la clase Cliente
import org.example.pafpoo.Clases.Compra; // Importación de la clase Compra
import org.example.pafpoo.Clases.Tarjeta; // Importación de la clase Tarjeta
import org.example.pafpoo.ClasesCrud.ClienteCrud; // Importación de la clase ClienteCrud para operaciones CRUD de Cliente
import org.example.pafpoo.ClasesCrud.CompraCrud; // Importación de la clase CompraCrud para operaciones CRUD de Compra
import org.example.pafpoo.ClasesCrud.TarjetaCrud; // Importación de la clase TarjetaCrud para operaciones CRUD de Tarjeta

import java.awt.Desktop; // Importación de la clase Desktop para abrir archivos con la aplicación predeterminada
import java.io.File; // Importación de la clase File para manejar archivos
import java.io.IOException; // Importación de la clase IOException para manejar excepciones de entrada/salida
import java.math.BigDecimal; // Importación de la clase BigDecimal para manejar números decimales
import java.sql.Date; // Importación de la clase Date para manejar fechas SQL
import java.sql.SQLException; // Importación de la clase SQLException para manejar excepciones SQL
import java.time.LocalDate; // Importación de la clase LocalDate para manejar fechas locales
import java.util.List; // Importación de la clase List para manejar listas
import java.util.stream.Stream; // Importación de la clase Stream para operaciones con streams

public class HelloController { // Definición de la clase HelloController

    @FXML private AnchorPane welcomeScreen; // Anotación FXML para enlazar con el elemento welcomeScreen del archivo FXML
    @FXML private AnchorPane reportScreen; // Anotación FXML para enlazar con el elemento reportScreen del archivo FXML
    @FXML private AnchorPane reportSelectionScreen; // Anotación FXML para enlazar con el elemento reportSelectionScreen del archivo FXML
    @FXML private AnchorPane modificationsScreen; // Anotación FXML para enlazar con el elemento modificationsScreen del archivo FXML

    @FXML private ComboBox<Cliente> clienteComboBoxA; // Anotación FXML para enlazar con el elemento clienteComboBoxA del archivo FXML
    @FXML private DatePicker startDatePicker; // Anotación FXML para enlazar con el elemento startDatePicker del archivo FXML
    @FXML private DatePicker endDatePicker; // Anotación FXML para enlazar con el elemento endDatePicker del archivo FXML
    @FXML private ComboBox<Cliente> clienteComboBoxB; // Anotación FXML para enlazar con el elemento clienteComboBoxB del archivo FXML
    @FXML private TextField monthField; // Anotación FXML para enlazar con el elemento monthField del archivo FXML
    @FXML private TextField yearField; // Anotación FXML para enlazar con el elemento yearField del archivo FXML
    @FXML private ComboBox<Cliente> clienteComboBoxC; // Anotación FXML para enlazar con el elemento clienteComboBoxC del archivo FXML
    @FXML private TextField facilitadorField; // Anotación FXML para enlazar con el elemento facilitadorField del archivo FXML
    @FXML private ListView<String> listView; // Anotación FXML para enlazar con el elemento listView del archivo FXML

    @FXML private TextField nombreClienteField; // Anotación FXML para enlazar con el elemento nombreClienteField del archivo FXML
    @FXML private TextField direccionClienteField; // Anotación FXML para enlazar con el elemento direccionClienteField del archivo FXML
    @FXML private TextField telefonoClienteField; // Anotación FXML para enlazar con el elemento telefonoClienteField del archivo FXML

    @FXML private TextField numeroTarjetaField; // Anotación FXML para enlazar con el elemento numeroTarjetaField del archivo FXML
    @FXML private DatePicker fechaExpiracionTarjetaPicker; // Anotación FXML para enlazar con el elemento fechaExpiracionTarjetaPicker del archivo FXML
    @FXML private ComboBox<Integer> tipoTarjetaComboBox; // Anotación FXML para enlazar con el elemento tipoTarjetaComboBox del archivo FXML
    @FXML private ComboBox<Integer> facilitadorComboBox; // Anotación FXML para enlazar con el elemento facilitadorComboBox del archivo FXML
    @FXML private ComboBox<Cliente> clienteComboBoxD; // Anotación FXML para enlazar con el elemento clienteComboBoxD del archivo FXML

    @FXML private DatePicker fechaCompraPicker; // Anotación FXML para enlazar con el elemento fechaCompraPicker del archivo FXML
    @FXML private TextField montoGastadoField; // Anotación FXML para enlazar con el elemento montoGastadoField del archivo FXML
    @FXML private TextField descripcionCompraField; // Anotación FXML para enlazar con el elemento descripcionCompraField del archivo FXML
    @FXML private ComboBox<Integer> tarjetaCompraComboBox; // Anotación FXML para enlazar con el elemento tarjetaCompraComboBox del archivo FXML
    @FXML private ListView<Compra> compraListView; // Anotación FXML para enlazar con el elemento compraListView del archivo FXML
    @FXML private ComboBox<Tarjeta> tarjetaComboBox; // Anotación FXML para enlazar con el elemento tarjetaComboBox del archivo FXML

    // Creación de instancias de las clases CRUD para operaciones de cliente, compra y tarjeta
    private final ClienteCrud clienteCrud = new ClienteCrud();
    private final CompraCrud compraCrud = new CompraCrud();
    private final TarjetaCrud tarjetaCrud = new TarjetaCrud();

    @FXML
    public void initialize() {
        try {
            List<Cliente> clientes = clienteCrud.obtenerTodosLosClientes();
            System.out.println("Clientes obtenidos: " + clientes.size());

            clienteComboBoxA.getItems().addAll(clientes);
            clienteComboBoxB.getItems().addAll(clientes);
            clienteComboBoxC.getItems().addAll(clientes);
            clienteComboBoxD.getItems().addAll(clientes); // Verifica si esto causa el error

            if (!clientes.isEmpty()) {
                List<Tarjeta> tarjetas = tarjetaCrud.obtenerTarjetasPorCliente(clientes.get(0).getIdCliente().intValue());
                tarjetaComboBox.getItems().addAll(tarjetas);

                List<Compra> compras = compraCrud.obtenerComprasPorClienteYPeriodo(clientes.get(0).getIdCliente().intValue(), Date.valueOf(LocalDate.now().minusYears(1)), Date.valueOf(LocalDate.now()));
                compraListView.getItems().addAll(compras);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showReportScreen() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        hideAllScreens(); // Ocultar todas las pantallas
        reportScreen.setVisible(true); // Mostrar la pantalla de reportes
    }

    @FXML
    private void showReportSelectionScreen() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        hideAllScreens(); // Ocultar todas las pantallas
        reportSelectionScreen.setVisible(true); // Mostrar la pantalla de selección de reportes
    }

    @FXML
    private void showWelcomeScreen() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        hideAllScreens(); // Ocultar todas las pantallas
        welcomeScreen.setVisible(true); // Mostrar la pantalla de bienvenida
    }

    @FXML
    private void showModificationsScreen() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        hideAllScreens(); // Ocultar todas las pantallas
        modificationsScreen.setVisible(true); // Mostrar la pantalla de modificaciones
    }

    private void hideAllScreens() { // Método para ocultar todas las pantallas
        welcomeScreen.setVisible(false); // Ocultar la pantalla de bienvenida
        reportScreen.setVisible(false); // Ocultar la pantalla de reportes
        reportSelectionScreen.setVisible(false); // Ocultar la pantalla de selección de reportes
        modificationsScreen.setVisible(false); // Ocultar la pantalla de modificaciones
    }

    public void generateReportA(ActionEvent actionEvent) { // Método para generar el Reporte A al recibir un evento de acción
        try {
            Cliente cliente = clienteComboBoxA.getValue(); // Obtener el cliente seleccionado del ComboBox A
            if (cliente == null) { // Validar si el cliente es nulo
                showAlert("Error", "Por favor, seleccione un cliente."); // Mostrar alerta si no se seleccionó un cliente
                return; // Salir del método si no se seleccionó un cliente
            }
            int clientId = cliente.getIdCliente().intValue(); // Obtener el ID del cliente seleccionado
            LocalDate startDate = startDatePicker.getValue(); // Obtener la fecha de inicio seleccionada
            LocalDate endDate = endDatePicker.getValue(); // Obtener la fecha de fin seleccionada

            if (startDate == null || endDate == null) { // Validar si las fechas de inicio o fin son nulas
                showAlert("Error", "Por favor, seleccione las fechas de inicio y fin."); // Mostrar alerta si las fechas no están seleccionadas
                return; // Salir del método si las fechas no están seleccionadas
            }

            // Generar el Reporte A y obtener los resultados
            List<String> reportAResults = ReportGenerator.generateReportA(clientId, Date.valueOf(startDate), Date.valueOf(endDate));
            listView.getItems().setAll(reportAResults); // Añadir los resultados del reporte A al ListView

            ReportGenerator.saveReportToFile(reportAResults, "A"); // Guardar el reporte A en un archivo

        } catch (SQLException | IOException e) { // Captura de las excepciones SQL y IO
            e.printStackTrace(); // Imprimir el stack trace del error
            showAlert("Error", "Ocurrió un error al generar el reporte."); // Mostrar alerta de error al generar el reporte
        }
    }

    public void generateReportB(ActionEvent actionEvent) { // Método para generar el Reporte B al recibir un evento de acción
        try {
            Cliente cliente = clienteComboBoxB.getValue(); // Obtener el cliente seleccionado del ComboBox B
            if (cliente == null) { // Validar si el cliente es nulo
                showAlert("Error", "Por favor, seleccione un cliente."); // Mostrar alerta si no se seleccionó un cliente
                return; // Salir del método si no se seleccionó un cliente
            }
            int clientId = cliente.getIdCliente().intValue(); // Obtener el ID del cliente seleccionado
            int month = Integer.parseInt(monthField.getText()); // Obtener el mes ingresado en el campo de texto
            int year = Integer.parseInt(yearField.getText()); // Obtener el año ingresado en el campo de texto

            // Generar el Reporte B y obtener los resultados
            List<String> reportBResults = ReportGenerator.generateReportB(clientId, month, year);
            listView.getItems().setAll(reportBResults); // Añadir los resultados del reporte B al ListView

            ReportGenerator.saveReportToFile(reportBResults, "B"); // Guardar el reporte B en un archivo

        } catch (SQLException | IOException e) { // Captura de las excepciones SQL y IO
            e.printStackTrace(); // Imprimir el stack trace del error
            showAlert("Error", "Ocurrió un error al generar el reporte."); // Mostrar alerta de error al generar el reporte
        } catch (NumberFormatException e) { // Captura de la excepción de formato de número
            showAlert("Error", "Por favor, ingrese valores válidos para mes y año."); // Mostrar alerta de error si el formato del mes o año no es válido
        }
    }

    public void generateReportC(ActionEvent actionEvent) { // Método para generar el Reporte C al recibir un evento de acción
        try {
            Cliente cliente = clienteComboBoxC.getValue(); // Obtener el cliente seleccionado del ComboBox C
            if (cliente == null) { // Validar si el cliente es nulo
                showAlert("Error", "Por favor, seleccione un cliente."); // Mostrar alerta si no se seleccionó un cliente
                return; // Salir del método si no se seleccionó un cliente
            }
            int clientId = cliente.getIdCliente().intValue(); // Obtener el ID del cliente seleccionado

            // Generar el Reporte C y obtener los resultados
            List<String> reportCResults = ReportGenerator.generateReportC(clientId);
            listView.getItems().setAll(reportCResults); // Añadir los resultados del reporte C al ListView

            ReportGenerator.saveReportToFile(reportCResults, "C"); // Guardar el reporte C en un archivo

        } catch (SQLException | IOException e) { // Captura de las excepciones SQL y IO
            e.printStackTrace(); // Imprimir el stack trace del error
            showAlert("Error", "Ocurrió un error al generar el reporte."); // Mostrar alerta de error al generar el reporte
        }
    }

    public void generateReportD(ActionEvent actionEvent) { // Método para generar el Reporte D al recibir un evento de acción
        try {
            String facilitator = facilitadorField.getText(); // Obtener el texto ingresado en el campo de facilitador

            if (facilitator == null || facilitator.trim().isEmpty()) { // Validar si el facilitador es nulo o vacío
                showAlert("Error", "Por favor, ingrese el nombre del facilitador."); // Mostrar alerta si el facilitador no está ingresado
                return; // Salir del método si el facilitador no está ingresado
            }

            // Generar el Reporte D y obtener los resultados
            List<String> reportDResults = ReportGenerator.generateReportD(facilitator);
            listView.getItems().setAll(reportDResults); // Añadir los resultados del reporte D al ListView

            ReportGenerator.saveReportToFile(reportDResults, "D"); // Guardar el reporte D en un archivo

        } catch (SQLException | IOException e) { // Captura de las excepciones SQL y IO
            e.printStackTrace(); // Imprimir el stack trace del error
            showAlert("Error", "Ocurrió un error al generar el reporte."); // Mostrar alerta de error al generar el reporte
        }
    }

    @FXML
    private void openReportA() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        openLatestReportFile("A"); // Llamada al método para abrir el último archivo del Reporte A
    }

    @FXML
    private void openReportB() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        openLatestReportFile("B"); // Llamada al método para abrir el último archivo del Reporte B
    }

    @FXML
    private void openReportC() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        openLatestReportFile("C"); // Llamada al método para abrir el último archivo del Reporte C
    }

    @FXML
    private void openReportD() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        openLatestReportFile("D"); // Llamada al método para abrir el último archivo del Reporte D
    }

    private void openLatestReportFile(String reportType) { // Método para abrir el último archivo de reporte dado su tipo
        File dir = new File("reportes/"); // Crear una instancia de File con el directorio de reportes
        // Obtener los archivos que coinciden con el tipo de reporte
        File[] matchingFiles = dir.listFiles((d, name) -> name.startsWith("Reporte_" + reportType + "_") && name.endsWith(".txt"));
        if (matchingFiles != null && matchingFiles.length > 0) { // Comprobar si hay archivos que coinciden
            // Obtener el archivo más reciente
            File latestFile = Stream.of(matchingFiles).max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified())).orElse(null);
            if (latestFile != null) { // Validar si el archivo más reciente no es nulo
                try {
                    Desktop.getDesktop().open(latestFile); // Intentar abrir el archivo con la aplicación predeterminada
                } catch (IOException e) { // Captura de la excepción en caso de error de entrada/salida
                    // Mostrar una alerta de error si no se pudo abrir el archivo
                    showAlert("Error", "No se pudo abrir el archivo: " + latestFile.getAbsolutePath());
                    e.printStackTrace(); // Imprimir el stack trace del error
                }
            }
        } else {
            // Mostrar una alerta de error si no se encontraron archivos para el reporte
            showAlert("Error", "No se encontró ningún archivo para el reporte: " + reportType);
        }
    }

    private void showAlert(String title, String message) { // Método para mostrar una alerta con un título y un mensaje
        Alert alert = new Alert(Alert.AlertType.ERROR); // Crear una instancia de Alert de tipo ERROR
        alert.setTitle(title); // Establecer el título de la alerta
        alert.setHeaderText(null); // Establecer el texto de encabezado como null
        alert.setContentText(message); // Establecer el contenido de la alerta con el mensaje
        alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
    }

    @FXML
    private void addClient() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        try {
            // Recoger datos del formulario
            String nombreCompleto = nombreClienteField.getText();
            String direccion = direccionClienteField.getText();
            String telefono = telefonoClienteField.getText();

            // Crear un objeto Cliente con los datos recogidos
            Cliente cliente = new Cliente();
            cliente.setNombreCompleto(nombreCompleto);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);

            // Llamar al método crearCliente de ClienteCrud para añadir el cliente a la base de datos
            clienteCrud.agregarCliente(cliente);

            // Actualizar la lista de clientes en los ComboBox
            List<Cliente> clientes = clienteCrud.obtenerTodosLosClientes();
            clienteComboBoxA.getItems().setAll(clientes);
            clienteComboBoxB.getItems().setAll(clientes);
            clienteComboBoxC.getItems().setAll(clientes);
            clienteComboBoxD.getItems().setAll(clientes);

            // Mostrar alerta de éxito
            showAlert("Success", "Cliente added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add client.");
        }
    }

    @FXML
    private void deleteClient() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        try {
            // Obtener el cliente seleccionado del ComboBox
            Cliente cliente = clienteComboBoxA.getValue();
            if (cliente != null) {
                // Llamar al método eliminarCliente de ClienteCrud para eliminar el cliente de la base de datos
                clienteCrud.eliminarCliente(cliente.getIdCliente());

                // Actualizar la lista de clientes en los ComboBox
                List<Cliente> clientes = clienteCrud.obtenerTodosLosClientes();
                clienteComboBoxA.getItems().setAll(clientes);
                clienteComboBoxB.getItems().setAll(clientes);
                clienteComboBoxC.getItems().setAll(clientes);
                clienteComboBoxD.getItems().setAll(clientes);

                // Mostrar alerta de éxito
                showAlert("Success", "Cliente deleted successfully.");
            } else {
                showAlert("Error", "No client selected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to delete client.");
        }
    }

    @FXML
    private void updateClient() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        try {
            // Obtener el cliente seleccionado del ComboBox
            Cliente cliente = clienteComboBoxA.getValue();
            if (cliente != null) {
                // Recoger datos actualizados del formulario
                String nombreCompleto = nombreClienteField.getText();
                String direccion = direccionClienteField.getText();
                String telefono = telefonoClienteField.getText();

                // Actualizar los datos del cliente
                cliente.setNombreCompleto(nombreCompleto);
                cliente.setDireccion(direccion);
                cliente.setTelefono(telefono);

                // Llamar al método actualizarCliente de ClienteCrud para actualizar el cliente en la base de datos
                clienteCrud.actualizarCliente(cliente);

                // Actualizar la lista de clientes en los ComboBox
                List<Cliente> clientes = clienteCrud.obtenerTodosLosClientes();
                clienteComboBoxA.getItems().setAll(clientes);
                clienteComboBoxB.getItems().setAll(clientes);
                clienteComboBoxC.getItems().setAll(clientes);
                clienteComboBoxD.getItems().setAll(clientes);

                // Mostrar alerta de éxito
                showAlert("Success", "Cliente updated successfully.");
            } else {
                showAlert("Error", "No client selected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update client.");
        }
    }

    @FXML
    private void addPurchase() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        try {
            // Recoger datos del formulario
            LocalDate fechaCompra = fechaCompraPicker.getValue();
            BigDecimal montoGastado = new BigDecimal(montoGastadoField.getText());
            String descripcion = descripcionCompraField.getText();
            int idTarjeta = tarjetaCompraComboBox.getValue();

            // Crear un objeto Compra con los datos recogidos
            Compra compra = new Compra();
            compra.setFechaCompra(fechaCompra.atStartOfDay());
            compra.setMontoGastado(montoGastado);
            compra.setDescripcion(descripcion);
            compra.setIdTarjeta(idTarjeta);

            // Llamar al método crearCompra de CompraCrud para añadir la compra a la base de datos
            compraCrud.crearCompra(compra);

            // Actualizar la lista de compras en el ListView
            List<Compra> compras = compraCrud.obtenerComprasPorClienteYPeriodo(clienteComboBoxD.getValue().getIdCliente().intValue(), Date.valueOf(LocalDate.now().minusYears(1)), Date.valueOf(LocalDate.now()));
            compraListView.getItems().setAll(compras);

            // Mostrar alerta de éxito
            showAlert("Success", "Compra added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add purchase.");
        }
    }

    @FXML
    private void deletePurchase() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        try {
            // Obtener la compra seleccionada del ListView
            Compra compra = compraListView.getSelectionModel().getSelectedItem();
            if (compra != null) {
                // Llamar al método eliminarCompra de CompraCrud para eliminar la compra de la base de datos
                compraCrud.eliminarCompra(compra.getIdCompra());

                // Actualizar la lista de compras en el ListView
                List<Compra> compras = compraCrud.obtenerComprasPorClienteYPeriodo(clienteComboBoxD.getValue().getIdCliente().intValue(), Date.valueOf(LocalDate.now().minusYears(1)), Date.valueOf(LocalDate.now()));
                compraListView.getItems().setAll(compras);

                // Mostrar alerta de éxito
                showAlert("Success", "Compra deleted successfully.");
            } else {
                showAlert("Error", "No purchase selected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to delete purchase.");
        }
    }

    @FXML
    private void updatePurchase() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        try {
            // Obtener la compra seleccionada del ListView
            Compra compra = compraListView.getSelectionModel().getSelectedItem();
            if (compra != null) {
                // Recoger datos actualizados del formulario
                LocalDate fechaCompra = fechaCompraPicker.getValue();
                BigDecimal montoGastado = new BigDecimal(montoGastadoField.getText());
                String descripcion = descripcionCompraField.getText();
                int idTarjeta = tarjetaCompraComboBox.getValue();

                // Actualizar los datos de la compra
                compra.setFechaCompra(fechaCompra.atStartOfDay());
                compra.setMontoGastado(montoGastado);
                compra.setDescripcion(descripcion);
                compra.setIdTarjeta(idTarjeta);

                // Llamar al método actualizarCompra de CompraCrud para actualizar la compra en la base de datos
                compraCrud.actualizarCompra(compra);

                // Actualizar la lista de compras en el ListView
                List<Compra> compras = compraCrud.obtenerComprasPorClienteYPeriodo(clienteComboBoxD.getValue().getIdCliente().intValue(), Date.valueOf(LocalDate.now().minusYears(1)), Date.valueOf(LocalDate.now()));
                compraListView.getItems().setAll(compras);

                // Mostrar alerta de éxito
                showAlert("Success", "Compra updated successfully.");
            } else {
                showAlert("Error", "No purchase selected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update purchase.");
        }
    }

    @FXML
    private void addCard() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        try {
            // Recoger datos del formulario
            String numeroTarjeta = numeroTarjetaField.getText();
            LocalDate fechaExpiracion = fechaExpiracionTarjetaPicker.getValue();
            int idTipoTarjeta = tipoTarjetaComboBox.getValue();
            int idFacilitador = facilitadorComboBox.getValue();
            int idCliente = clienteComboBoxD.getValue().getIdCliente().intValue();

            // Crear un objeto Tarjeta con los datos recogidos
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setNumeroTarjeta(numeroTarjeta);
            tarjeta.setFechaExpiracion(fechaExpiracion);
            tarjeta.setIdTipoTarjeta(idTipoTarjeta);
            tarjeta.setIdFacilitador(idFacilitador);
            tarjeta.setIdCliente(idCliente);

            // Llamar al método crearTarjeta de TarjetaCrud para añadir la tarjeta a la base de datos
            tarjetaCrud.crearTarjeta(tarjeta);

            // Actualizar la lista de tarjetas en el ComboBox
            List<Tarjeta> tarjetas = tarjetaCrud.obtenerTarjetasPorCliente(clienteComboBoxD.getValue().getIdCliente().intValue());
            tarjetaComboBox.getItems().setAll(tarjetas);

            // Mostrar alerta de éxito
            showAlert("Success", "Tarjeta added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add card.");
        }
    }

    @FXML
    private void deleteCard() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        try {
            // Obtener la tarjeta seleccionada del ComboBox
            Tarjeta tarjeta = tarjetaComboBox.getValue();
            if (tarjeta != null) {
                // Llamar al método eliminarTarjeta de TarjetaCrud para eliminar la tarjeta de la base de datos
                tarjetaCrud.eliminarTarjeta(tarjeta.getIdTarjeta());

                // Actualizar la lista de tarjetas en el ComboBox
                List<Tarjeta> tarjetas = tarjetaCrud.obtenerTarjetasPorCliente(clienteComboBoxD.getValue().getIdCliente().intValue());
                tarjetaComboBox.getItems().setAll(tarjetas);

                // Mostrar alerta de éxito
                showAlert("Success", "Tarjeta deleted successfully.");
            } else {
                showAlert("Error", "No card selected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to delete card.");
        }
    }

    @FXML
    private void updateCard() { // Anotación FXML para indicar que este método es llamado desde la interfaz
        try {
            // Obtener la tarjeta seleccionada del ComboBox
            Tarjeta tarjeta = tarjetaComboBox.getValue();
            if (tarjeta != null) {
                // Recoger datos actualizados del formulario
                String numeroTarjeta = numeroTarjetaField.getText();
                LocalDate fechaExpiracion = fechaExpiracionTarjetaPicker.getValue();
                int idTipoTarjeta = tipoTarjetaComboBox.getValue();
                int idFacilitador = facilitadorComboBox.getValue();
                int idCliente = clienteComboBoxD.getValue().getIdCliente().intValue();

                // Actualizar los datos de la tarjeta
                tarjeta.setNumeroTarjeta(numeroTarjeta);
                tarjeta.setFechaExpiracion(fechaExpiracion);
                tarjeta.setIdTipoTarjeta(idTipoTarjeta);
                tarjeta.setIdFacilitador(idFacilitador);
                tarjeta.setIdCliente(idCliente);

                // Llamar al método actualizarTarjeta de TarjetaCrud para actualizar la tarjeta en la base de datos
                tarjetaCrud.actualizarTarjeta(tarjeta);

                // Actualizar la lista de tarjetas en el ComboBox
                List<Tarjeta> tarjetas = tarjetaCrud.obtenerTarjetasPorCliente(clienteComboBoxD.getValue().getIdCliente().intValue());
                tarjetaComboBox.getItems().setAll(tarjetas);

                // Mostrar alerta de éxito
                showAlert("Success", "Tarjeta updated successfully.");
            } else {
                showAlert("Error", "No card selected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update card.");
        }
    }
}
