package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;


public class LoginController {

    @FXML
    private Button btnLogin;
    @FXML
    private Label lblErrorMsg;


    /**
     * This function handles the login process upon pressing the login button.
     *
     * @param actionEvent
     */
    public void handleBtnLogin(ActionEvent actionEvent) {
        lblErrorMsg.setText("Invalid login. Please try again.");
    }
}
