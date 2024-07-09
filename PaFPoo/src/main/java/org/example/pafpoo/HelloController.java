package org.example.pafpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.pafpoo.Clases.Cliente;
import org.example.pafpoo.ClasesCrud.ClienteCrud;
import org.example.pafpoo.ClasesCrud.CompraCrud;
import org.example.pafpoo.ClasesCrud.TarjetaCrud;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class HelloController {

    @FXML
    private AnchorPane welcomeScreen;

    @FXML
    private AnchorPane reportScreen;

    @FXML
    private ComboBox<Cliente> clienteComboBoxA;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<Cliente> clienteComboBoxB;

    @FXML
    private TextField monthField;

    @FXML
    private TextField yearField;

    @FXML
    private ComboBox<Cliente> clienteComboBoxC;

    @FXML
    private TextField facilitadorField;

    @FXML
    private ListView<String> listView;

    private final ClienteCrud clienteCrud = new ClienteCrud();
    private final CompraCrud compraCrud = new CompraCrud();
    private final TarjetaCrud tarjetaCrud = new TarjetaCrud();

    @FXML
    public void initialize() {
        try {
            List<Cliente> clientes = clienteCrud.obtenerTodosLosClientes();
            clienteComboBoxA.getItems().addAll(clientes);
            clienteComboBoxB.getItems().addAll(clientes);
            clienteComboBoxC.getItems().addAll(clientes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showReportScreen() {
        welcomeScreen.setVisible(false);
        reportScreen.setVisible(true);
    }

    @FXML
    private void showServiceScreen() {
        // Implementa la lógica para mostrar la pantalla de servicio si tienes una
    }

    @FXML
    private void showWelcomeScreen() {
        reportScreen.setVisible(false);
        welcomeScreen.setVisible(true);
    }

    public void generateReportA(ActionEvent actionEvent) {
        try {
            Cliente cliente = clienteComboBoxA.getValue();
            if (cliente == null) {
                System.out.println("Por favor, seleccione un cliente.");
                return;
            }
            int clientId = cliente.getIdCliente().intValue();
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();

            // Genera el reporte A y añade el resultado al ListView
            List<String> reportAResults = ReportGenerator.generateReportA(clientId, Date.valueOf(startDate), Date.valueOf(endDate));
            listView.getItems().setAll(reportAResults);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReportB(ActionEvent actionEvent) {
        try {
            Cliente cliente = clienteComboBoxB.getValue();
            if (cliente == null) {
                System.out.println("Por favor, seleccione un cliente.");
                return;
            }
            int clientId = cliente.getIdCliente().intValue();
            int month = Integer.parseInt(monthField.getText());
            int year = Integer.parseInt(yearField.getText());

            // Genera el reporte B y añade el resultado al ListView
            List<String> reportBResults = ReportGenerator.generateReportB(clientId, month, year);
            listView.getItems().setAll(reportBResults);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReportC(ActionEvent actionEvent) {
        try {
            Cliente cliente = clienteComboBoxC.getValue();
            if (cliente == null) {
                System.out.println("Por favor, seleccione un cliente.");
                return;
            }
            int clientId = cliente.getIdCliente().intValue();

            // Genera el reporte C y añade el resultado al ListView
            List<String> reportCResults = ReportGenerator.generateReportC(clientId);
            listView.getItems().setAll(reportCResults);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReportD(ActionEvent actionEvent) {
        try {
            String facilitator = facilitadorField.getText();

            // Genera el reporte D y añade el resultado al ListView
            List<String> reportDResults = ReportGenerator.generateReportD(facilitator);
            listView.getItems().setAll(reportDResults);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
