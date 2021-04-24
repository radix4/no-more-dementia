package application.controllers;

import application.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LeaderboardController {
	private Scene gameScene;
	
	public void setGameScene(Scene gameScene) {
		this.gameScene = gameScene;
	}
	
	// Action handler to go back to the Game scene from the leaderboard scene
	public void backToPlay(ActionEvent event) {
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(gameScene);
	}

	

}
