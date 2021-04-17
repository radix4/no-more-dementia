package application.controllers;

import application.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private Text txtErrorMsg;
    @FXML
    private Text txtSuccessMsg;
    @FXML
    private TextField txtFdName;
    @FXML
    private TextField txtFdEmail;
    @FXML
    private TextField pwFdPassword;
    @FXML
    private TextField pwFdConfirmPassword;


    private Scene loginScene;

    public void setLoginScene(Scene scene) {
        this.loginScene = scene;
    }

    /**
     * This function handles the registration process upon pressing the registration button.
     * @param actionEvent
     */
    public void handleBtnRegister(ActionEvent actionEvent) {
        DbController dbInstance = DbController.getSingleDBInstance();

        String name = txtFdName.getText();
        String email = txtFdEmail.getText();
        String password = pwFdPassword.getText();
        String confirmPassword = pwFdConfirmPassword.getText();

        /* empty fields = error */
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            txtErrorMsg.setText("Error! Please fill out all fields.");
            return;
        }

        /* passwords don't match */
        if (!password.equals(confirmPassword)) {
            txtErrorMsg.setText("Error! Passwords don't match.");
            return;
        }

        User newUser = new User(name, email, password);

        /* insert new user into the database */
        if(dbInstance.insertIntoUsersTable(newUser))
            txtSuccessMsg.setText("Create account success!\nGo to login screen to login.");
        else
            txtErrorMsg.setText("Email already exists!");
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
