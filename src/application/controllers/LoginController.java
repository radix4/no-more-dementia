package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    private Button btnLogin;
    @FXML
    private Label lblErrorMsg;

    private Scene registrationScene;

    public void setRegistrationScene(Scene scene) {
        this.registrationScene = scene;
    }


    /**
     * This function handles the login process upon pressing the login button.
     * @param actionEvent
     */
    public void handleBtnLogin(ActionEvent actionEvent) {
        lblErrorMsg.setText("Invalid login. Please try again.");
    }

    /**
     * This function switches to registration scene upon click the "Register now" link text.
     * @param mouseEvent
     */
    public void handleTxtRegisterLink(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(registrationScene);
    }
}
