package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class RegistrationController {

    @FXML
    private Text txtErrorMsg;

    public void handleBtnRegister(ActionEvent actionEvent) {
        txtErrorMsg.setText("Error! Please fill out all fields.");
    }
}
