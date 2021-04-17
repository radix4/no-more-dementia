package application;
	
import application.controllers.DbController;
import application.controllers.LoginController;
import application.controllers.RegistrationController;
import application.models.User;
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
		/* instantiate db instance*/
		DbController dbInstance = DbController.getSingleDBInstance();

		User user = new User("name", "email", "address");
		User user1 = new User("name1", "email1", "address1");
		User user2 = new User("name1", "email1", "address1");


		dbInstance.insertIntoUsersTable(user);
		dbInstance.insertIntoUsersTable(user1);
		dbInstance.insertIntoUsersTable(user2); /* expect error: duplicate email */


		/* launch GUI */
		launch(args);


		/* close DB */
		dbInstance.closeDB();
	}
}
