package org.example.pafpoo; // 00090322 Define el paquete donde se encuentra esta clase

import javafx.application.Application; // 00090322 Importa la clase Application de JavaFX
import javafx.fxml.FXMLLoader; // 00090322 Importa la clase FXMLLoader de JavaFX
import javafx.scene.Scene; // 00090322 Importa la clase Scene de JavaFX
import javafx.stage.Screen; // 00090322 Importa la clase Screen de JavaFX
import javafx.stage.Stage; // 00090322 Importa la clase Stage de JavaFX
import javafx.geometry.Rectangle2D; // 00090322 Importa la clase Rectangle2D de JavaFX

import java.io.IOException; // 00090322 Importa la clase IOException
import java.sql.Connection; // 00090322 Importa la clase Connection de java.sql
import java.sql.Date; // 00090322 Importa la clase Date de java.sql
import java.sql.SQLException; // 00090322 Importa la clase SQLException de java.sql
import java.time.LocalDate; // 00090322 Importa la clase LocalDate
import java.util.List; // 00090322 Importa la interfaz List

public class HelloApplication extends Application { // 00090322 Define la clase HelloApplication que extiende Application
    @Override
    public void start(Stage stage) throws IOException { // 00090322 Sobrescribe el método start, que lanza IOException
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/pafpoo/hello-view.fxml")); // 00090322 Crea un FXMLLoader para cargar el archivo FXML

        // 00090322 Obtener las dimensiones de la pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds(); // 00090322 Obtiene los límites de la pantalla principal

        // 00090322 Cargar el FXML y ajustar la escena a las dimensiones de la pantalla
        Scene scene = new Scene(fxmlLoader.load(), screenBounds.getWidth(), screenBounds.getHeight()); // 00090322 Carga el FXML y ajusta la escena al tamaño de la pantalla

        // 00090322 Ajustar el tamaño del Stage a las dimensiones de la pantalla
        stage.setWidth(screenBounds.getWidth()); // 00090322 Establece el ancho del Stage al ancho de la pantalla
        stage.setHeight(screenBounds.getHeight()); // 00090322 Establece el alto del Stage al alto de la pantalla

        stage.setTitle("Sistema de Reportes"); // 00090322 Establece el título del Stage
        stage.setScene(scene); // 00090322 Establece la escena en el Stage
        stage.show(); // 00090322 Muestra el Stage

        // 00090322 Verificar la conexión a la base de datos
        verificarConexion(); // 00090322 Llama al método verificarConexion

        // 00090322 Generar y guardar los reportes
        try {
            Date startDate = Date.valueOf(LocalDate.of(2023, 1, 1)); // 00090322 Define la fecha de inicio
            Date endDate = Date.valueOf(LocalDate.of(2023, 12, 31)); // 00090322 Define la fecha de fin
            int clientId = 1; // 00090322 Reemplazar con el ID del cliente adecuado

            // 00090322 Generar y guardar el Reporte A
            List<String> reportA = ReportGenerator.generateReportA(clientId, startDate, endDate); // 00090322 Genera el Reporte A
            ReportGenerator.saveReportToFile(reportA, "reporte_A.txt"); // 00090322 Guarda el Reporte A en un archivo

            // 00090322 Generar y guardar el Reporte B
            int month = 6; // 00090322 Junio
            int year = 2023; // 00090322 Año
            List<String> reportB = ReportGenerator.generateReportB(clientId, month, year); // 00090322 Genera el Reporte B
            ReportGenerator.saveReportToFile(reportB, "reporte_B.txt"); // 00090322 Guarda el Reporte B en un archivo

            // 00090322 Generar y guardar el Reporte C
            List<String> reportC = ReportGenerator.generateReportC(clientId); // 00090322 Genera el Reporte C
            ReportGenerator.saveReportToFile(reportC, "reporte_C.txt"); // 00090322 Guarda el Reporte C en un archivo

            // 00090322 Generar y guardar el Reporte D
            String facilitator = "VISA"; // 00090322 Reemplazar con el nombre adecuado del facilitador
            List<String> reportD = ReportGenerator.generateReportD(facilitator); // 00090322 Genera el Reporte D
            ReportGenerator.saveReportToFile(reportD, "reporte_D.txt"); // 00090322 Guarda el Reporte D en un archivo
        } catch (SQLException | IOException e) { // 00090322 Captura las excepciones SQLException e IOException
            e.printStackTrace(); // 00090322 Imprime el stack trace de la excepción
        }
    }

    private void verificarConexion() { // 00090322 Define un método para verificar la conexión a la base de datos
        Connection connection = DatabaseConnection.getConnection(); // 00090322 Obtiene la conexión a la base de datos
        if (connection != null) { // 00090322 Verifica si la conexión es exitosa
            System.out.println("Conexión exitosa a MySQL!"); // 00090322 Imprime un mensaje si la conexión es exitosa
        } else { // 00090322 Si la conexión falla
            System.out.println("Fallo en la conexión a MySQL."); // 00090322 Imprime un mensaje si la conexión falla
        }
    }

    public static void main(String[] args) { // 00090322 Define el método main
        launch(); // 00090322 Llama al método launch para iniciar la aplicación JavaFX
    }
}
