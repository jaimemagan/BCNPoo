package org.example.pafpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.example.pafpoo.Clases.Cliente;
import org.example.pafpoo.ClasesCrud.ClienteCrud;
import org.example.pafpoo.ClasesCrud.CompraCrud;
import org.example.pafpoo.ClasesCrud.TarjetaCrud;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class HelloController {

    @FXML
    private ComboBox<Cliente> clienteComboBoxA;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField clienteIdFieldB;

    @FXML
    private TextField monthField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField clienteIdFieldC;

    @FXML
    private TextField facilitadorField;

    private final ClienteCrud clienteCrud = new ClienteCrud();
    private final CompraCrud compraCrud = new CompraCrud();
    private final TarjetaCrud tarjetaCrud = new TarjetaCrud();

    @FXML
    public void initialize() {
        try {
            List<Cliente> clientes = clienteCrud.obtenerTodosLosClientes();
            clienteComboBoxA.getItems().addAll(clientes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

            ReportGenerator.generateReportA(clientId, Date.valueOf(startDate), Date.valueOf(endDate));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReportB(ActionEvent actionEvent) {
        try {
            int clientId = Integer.parseInt(clienteIdFieldB.getText());
            int month = Integer.parseInt(monthField.getText());
            int year = Integer.parseInt(yearField.getText());

            ReportGenerator.generateReportB(clientId, month, year);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReportC(ActionEvent actionEvent) {
        try {
            int clientId = Integer.parseInt(clienteIdFieldC.getText());

            ReportGenerator.generateReportC(clientId);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReportD(ActionEvent actionEvent) {
        try {
            String facilitator = facilitadorField.getText();

            ReportGenerator.generateReportD(facilitator);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
