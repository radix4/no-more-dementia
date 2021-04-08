package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent loginRoot = FXMLLoader.load(getClass().getResource("layout/login.fxml"));
			Parent registrationRoot = FXMLLoader.load(getClass().getResource("layout/login.fxml"));

			Scene login = new Scene(loginRoot,1000,700);
			Scene registration = new Scene(registrationRoot,1000,700);



			primaryStage.setScene(login);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
