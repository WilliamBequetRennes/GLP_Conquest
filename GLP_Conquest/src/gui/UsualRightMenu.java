package gui;

import game.Game;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UsualRightMenu extends VBox{

	public Label currentPlayer;
	
	public UsualRightMenu(Game game) {
		super();
		setCurrentPlayer(new Label());
		getCurrentPlayer().setText("Player : "+game.getCurrentPlayer());
		getChildren().add(getCurrentPlayer());
	}

	public Label getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Label currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
}
