package application.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import application.controllers.GameController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Matrix {
	private int difficultyLevel;
	
	private ArrayList<ImageView> imageViews;
	
	private boolean[] imageWasClicked; //index i will store whether image i has been clicked before
	
	private int currentScore;
	
	private GameController gameController;
	
	private EventHandler<MouseEvent> imageViewHandler; //reference used to remove event handlers from all images at the end of the game
	
	//creates each ImageViewer and connects each to an event handler for mouse click
	public Matrix(int diffLevel, GameController gameController) {
		difficultyLevel = diffLevel;
		currentScore = 0;
		imageWasClicked = new boolean[diffLevel];
		this.gameController = gameController;
		
		imageViews = new ArrayList<ImageView>();
		imageViewHandler = e -> gameController.handleMouseClicked(e);
		for (int i = 0; i < diffLevel; i++) {
			ImageView imageView = createImage(i);
			imageView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, imageViewHandler);
			getImageViews().add(imageView);
		}
		shuffleImageViews();
	}
	
	//shuffles images
	public void shuffleImageViews() {
		Collections.shuffle(getImageViews());
	}

	
	//returns an ImageView object with image of current count (ex. count 0 will create an ImageView using image1.png)
	//and will set ImageView ID to current count.
	public ImageView createImage(int count) {
		System.out.println("Create Image");
		try {
			Image image = new Image(new FileInputStream("C:\\Users\\kevtr\\CS151\\no-more-dementia\\src\\application\\images\\image" + count + ".png"));
			
			//Creating the image view
		    ImageView imageView = new ImageView();
		    //Setting image to the image view
		    imageView.setImage(image);
		    imageView.setId("" + count);
		    //imageView.setFitHeight(imageHeight);
		    //imageView.setFitWidth(imageWidth);
		    return imageView;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//returns true if we added the clicked image (could be winning condition or not)
	//returns false otherwise, means that the game is lost
	public boolean addClickedImage(ImageView imageView) {
		String imageViewId = imageView.getId();
		int imageId = Integer.parseInt(imageViewId);
		if(imageWasClicked[imageId]) {
			return false;
		}
		imageWasClicked[imageId] = true;
		currentScore++;
		return true;
	}
	
	//win condition
	public boolean haveIWon() {
		return currentScore == getDifficultyLevel();
	}
	
	//remove mouse click handler from matrix's images
	public void removeImageHandlers() {
		for(ImageView image : getImageViews()) {
			image.removeEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, imageViewHandler);
		}
	}
	
	//gets difficulty
	public int getDifficulty() {
		return getDifficultyLevel();
	}
	
	//get current score
	public int getCurrentScore() {
		return currentScore;
	}

	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public ArrayList<ImageView> getImageViews() {
		return imageViews;
	}
}
