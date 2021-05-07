package application.controllers;

import application.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LeaderboardController {
	private User user;
	
	private Scene loginScene;
	
	private Scene gameScene;
	
	GameController gameController = new GameController();
	
	@FXML
	private Text helloLabelTwo;
	
	@FXML
	private Text scoreLbl0;
	
	@FXML
	private Text scoreLbl1;
	
	@FXML
	private Text scoreLbl2;
	
	@FXML
	private Text scoreLbl3;
	
	@FXML
	private Text scoreLbl4;
	
	public void setUser(User user) {
		this.user = user;
		helloLabelTwo.setText("Hello, " + user.getName());
	}
	
	public void setLoginScene(Scene loginScene) {
		this.loginScene = loginScene;
	}
	
	public void setGameScene(Scene gameScene) {
		this.gameScene = gameScene;
	}
	
	public void displayTopFiveScores() {
		
		int[] topFiveScores = user.getTopScores();
		Text[] labels = {scoreLbl0, scoreLbl1, scoreLbl2, scoreLbl3, scoreLbl4};
		
		int len = topFiveScores.length;
		
		for(int i = 0; i < len; i++) {
			labels[i].setText("Score " + (len-i) + ": " + topFiveScores[i]);
		}
		
	}
	
	// Action handler to go back to the Game scene from the leaderboard scene
	public void backToPlay(ActionEvent event) {
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(gameScene);
	}
	
	public void logout(ActionEvent event) {
		DbController.getSingleDBInstance().updateScoresIntoUsersTable(user, user.getTopScores());
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
	}
	

	

}
