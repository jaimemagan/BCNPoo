package org.example.pafpoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/pafpoo/hello-view.fxml"));

        // Obtener las dimensiones de la pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Cargar el FXML y ajustar la escena a las dimensiones de la pantalla
        Scene scene = new Scene(fxmlLoader.load(), screenBounds.getWidth(), screenBounds.getHeight());

        // Ajustar el tamaño del Stage a las dimensiones de la pantalla
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());

        stage.setTitle("Sistema de Reportes");
        stage.setScene(scene);
        stage.show();

        // Verificar la conexión a la base de datos
        verificarConexion();

        // Generar y guardar los reportes
        try {
            Date startDate = Date.valueOf(LocalDate.of(2023, 1, 1));
            Date endDate = Date.valueOf(LocalDate.of(2023, 12, 31));
            int clientId = 1; // Reemplazar con el ID del cliente adecuado

            // Generar y guardar el Reporte A
            List<String> reportA = ReportGenerator.generateReportA(clientId, startDate, endDate);
            ReportGenerator.saveReportToFile(reportA, "reporte_A.txt");

            // Generar y guardar el Reporte B
            int month = 6; // Junio
            int year = 2023;
            List<String> reportB = ReportGenerator.generateReportB(clientId, month, year);
            ReportGenerator.saveReportToFile(reportB, "reporte_B.txt");

            // Generar y guardar el Reporte C
            List<String> reportC = ReportGenerator.generateReportC(clientId);
            ReportGenerator.saveReportToFile(reportC, "reporte_C.txt");

            // Generar y guardar el Reporte D
            String facilitator = "Facilitador1"; // Reemplazar con el nombre adecuado del facilitador
            List<String> reportD = ReportGenerator.generateReportD(facilitator);
            ReportGenerator.saveReportToFile(reportD, "reporte_D.txt");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void verificarConexion() {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Conexión exitosa a MySQL!");
        } else {
            System.out.println("Fallo en la conexión a MySQL.");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

