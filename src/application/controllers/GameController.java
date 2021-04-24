package application.controllers;
import application.models.Matrix;
import application.models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameController {
	private Scene loginScene;
	private Scene leaderboardScene;
	
	private Matrix matrix;
	
	private User user;
	
	@FXML
	private Text currentScore;
	
	@FXML
	private Text topScore;
	
	@FXML
	private Text helloLabel;
	
	@FXML
	private Text gameResult;
	
	@FXML
	private Button restartGame;
	
	@FXML
	private GridPane gridPane;
	
	@FXML
	private AnchorPane rightPane;
	
	//injection
	public void setLoginScene(Scene loginScene) {
		this.loginScene = loginScene;
	}
	
	public void setLeaderboardScene(Scene leaderboardScene) {
		this.leaderboardScene = leaderboardScene;
	}
	
	public void setUser(User user) {
		this.user = user;
		helloLabel.setText("Hello, " + user.getName());
	}
	
	public void startNewGame(int difficulty) {
		matrix = new Matrix(difficulty, this);
		setGridPane();
		gameResult.setText("");
		currentScore.setText("Current Score: " + matrix.getCurrentScore());
	}
	
	//pre-condition: called only after a game has ended
	//adds score of game to leaderboards if in top 5
	private void updateTopScores() {
		int currentScore = matrix.getCurrentScore();
		int[] top5scores = user.getTopScores();
		int i;
		for(i = 0; i < top5scores.length; i++) {
			if(currentScore <= top5scores[i]) {
				if(i != 0) {
					top5scores[i-1] = currentScore;
				}
				return;
			}
			if(i != 0) {
				top5scores[i - 1] = top5scores[i];
			}
		}
		top5scores[i-1] = currentScore;
	}
	
	public void setTopScoreLabel() {
		topScore.setText("Top Score: " + user.getTopScores()[user.getTopScores().length - 1]);
	}
	
	public void setCurrentScoreLabel() {
		currentScore.setText("Current Score: " + matrix.getCurrentScore());
	}
	
	//sets the size of the image and puts them into the grid pane
	public void setGridPane() {
		int numRow = (int)Math.sqrt(matrix.getDifficultyLevel());
		int numCol = numRow;
		gridPane.getChildren().clear();
		
		double imageSize = (rightPane.getPrefWidth() - gridPane.getHgap() * (numCol + 1)) / numCol;
		
		int count = 0;
		for (int i = 0; i < numCol; i++) {
			for (int j = 0; j < numRow; j++) {
				ImageView imageView = matrix.getImageViews().get(count);
				imageView.setFitHeight(imageSize);
				imageView.setFitWidth(imageSize);
				GridPane.setConstraints(imageView, i, j);
				gridPane.getChildren().add(imageView);
				count++;
			}
		}
	}
	
	public void handleStartNewGame(ActionEvent actionEvent) {
		startNewGame(matrix.getDifficulty());
	}
	
	//go back to login page, sign out
	public void handleLogout(ActionEvent actionEvent) {
		gridPane.getChildren().clear();
		topScore.setText("Top Score:");
		currentScore.setText("Current Score:");
		helloLabel.setText("Hello,");
		gameResult.setText("");
		matrix = null;
		DbController.getSingleDBInstance().updateScoresIntoUsersTable(user, user.getTopScores());
		Stage primaryStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
	}
	
	public void handleMouseClicked(MouseEvent event) {
		//event clicked here
		ImageView source = (ImageView)event.getSource();
		if (matrix.addClickedImage(source)) {
			if (matrix.haveIWon()) {
				gameResult.setText("YOU WIN!");
				setCurrentScoreLabel();
				updateTopScores();
				setTopScoreLabel();
				//display incremented score after winning click
				matrix.removeImageHandlers();
			} else {
				matrix.shuffleImageViews();
				setGridPane();
				setCurrentScoreLabel();
				//after the image has been clicked and the game is not won or lost, display the incremented score
			}
		} else {
			gameResult.setText("YOU LOST!");
			updateTopScores();
			setTopScoreLabel();
			matrix.removeImageHandlers();
		}
	}
	
	public void handleChangeDifficulty(ActionEvent event) {
		String[] difficulty = {"Easy", "Medium", "Hard"};
		ChoiceDialog<String> choices = new ChoiceDialog<String>(difficulty[0], difficulty);
		choices.setTitle("Difficulty Level");
		choices.setHeaderText("Select a difficulty level.");
		choices.showAndWait();
		switch(choices.getSelectedItem()) {
		case "Easy" :
			startNewGame(9);
			break;
		case "Medium" :
			startNewGame(16);
			break;
		case "Hard" :
			startNewGame(25);
			break;
		}
		
	}
	
	// Method to change the scene to leaderboard
	public void viewLeaderboard(ActionEvent event) {
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(leaderboardScene);
	}
}
