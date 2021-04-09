package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private Text txtErrorMsg;

    private Scene loginScene;

    public void setLoginScene(Scene scene) {
        this.loginScene = scene;
    }

    /**
     * This function handles the registration process upon pressing the registration button.
     * @param actionEvent
     */
    public void handleBtnRegister(ActionEvent actionEvent) {
        txtErrorMsg.setText("Error! Please fill out all fields.");
    }

    /**
     * This function switches to login scene upon click the "Login here" link text.
     * @param mouseEvent
     */
    public void handleTxtLoginLink(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
    }
}
