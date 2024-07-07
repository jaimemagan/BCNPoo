package org.example.pafpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }






    public void generateReportA(ActionEvent actionEvent) {
    }

    public void generateReportB(ActionEvent actionEvent) {
    }

    public void generateReportC(ActionEvent actionEvent) {
    }

    public void generateReportD(ActionEvent actionEvent) {
    }
}