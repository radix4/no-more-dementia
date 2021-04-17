package application.controllers;

import application.models.User;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginController {
    private Scene registrationScene;

    @FXML
    private Label lblErrorMsg;
    @FXML
    private TextField loginTxtFEmail;
    @FXML
    private TextField loginPassFPassword;

    public static User currentUser = null;

    public void setRegistrationScene(Scene scene) {
        this.registrationScene = scene;
    }


    /**
     * This function handles the login process upon pressing the login button.
     * @param actionEvent
     */
    public void handleBtnLogin(ActionEvent actionEvent) {
        String email = loginTxtFEmail.getText();
        String password = loginPassFPassword.getText();

        /* empty fields = error */
        if (email.isEmpty() || password.isEmpty()) {
            lblErrorMsg.setText("Error! Please fill out all fields.");
            return;
        }

        DbController dbInstance = DbController.getSingleDBInstance();

        currentUser = dbInstance.selectUserFromUsersTable(email);

        /* case where:
        * 1) email doesn't exist in db
        * 2) password doesn't match with email provided */
        if (currentUser == null || !currentUser.getPassword().equals(password)) {
            lblErrorMsg.setText("Invalid login. Please try again.");
            return;
        }

        /* ===== successfully loggin =====*/
        lblErrorMsg.setText("Login success.");
        System.out.println(currentUser);

        // TODO: switch to home/play screen

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
