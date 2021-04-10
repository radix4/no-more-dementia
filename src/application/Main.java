package application;
	
import application.controllers.LoginController;
import application.controllers.RegistrationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/* Initialize login scene */
			FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("layout/login.fxml"));
			Parent loginPane = loginPaneLoader.load();
			Scene loginScene = new Scene(loginPane,1000,700);

			/* Initialize registration scene */
			FXMLLoader registrationPaneLoader = new FXMLLoader(getClass().getResource("layout/registration.fxml"));
			Parent registrationPane = registrationPaneLoader.load();
			Scene registrationScene = new Scene(registrationPane,1000,700);

			/* Inject registration scene to login scene */
			/* Purpose is to remember states between scenes */
			LoginController loginPaneController = (LoginController)  loginPaneLoader.getController();
			loginPaneController.setRegistrationScene(registrationScene);

			/* Inject login scene to registration scene */
			RegistrationController registrationPaneController = (RegistrationController)  registrationPaneLoader.getController();
			registrationPaneController.setLoginScene(loginScene);


			/* Login scene is the initial scene */
			primaryStage.setScene(loginScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
