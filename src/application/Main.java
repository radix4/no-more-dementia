package application;
	
import application.controllers.LoginController;
import application.controllers.RegistrationController;
import application.controllers.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

// getting loader and a pane for the first scene.
// loader will then give a possibility to get related controller
//              FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("firstLayout.fxml"));
// 				Parent firstPane = firstPaneLoader.load();
// 				Scene firstScene = new Scene(firstPane, 300, 275);
//
// 				// getting loader and a pane for the second scene
// 				FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("secondLayout.fxml"));
// 				Parent secondPane = secondPageLoader.load();
// 				Scene secondScene = new Scene(secondPane, 300, 275);
//
// 				// injecting second scene into the controller of the first scene
// 				FirstController firstPaneController = (FirstController) firstPaneLoader.getController();
// 				firstPaneController.setSecondScene(secondScene);
//
// 				// injecting first scene into the controller of the second scene
// 				SecondController secondPaneController = (SecondController) secondPageLoader.getController();
// 				secondPaneController.setFirstScene(firstScene);
//
// 				primaryStage.setTitle("Switching scenes");
// 				primaryStage.setScene(firstScene);
// 				primaryStage.show();


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
