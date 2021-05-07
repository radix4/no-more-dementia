package application.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InstructionsController {
	private Scene gameScene;

	public void setGameScene(Scene gameScene) {
		this.gameScene = gameScene;
	}
	
	public void backToPlay(ActionEvent event) {
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(gameScene);
	}

}
