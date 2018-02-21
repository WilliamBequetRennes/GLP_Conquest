package gui;

import game.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UsualLeftMenu extends VBox{

	private GridPane playerArray;
	private Label[] playerList;
	private Label squareType;
	
	public UsualLeftMenu(Game game) {
		super();
		initializePlayerArray(game);
		initializeCurrentSquare(game);
	}
	
	public void initializePlayerArray(Game game){
		setPlayerArray(new GridPane());
		setPlayerList(new Label[game.getPlayersNumber()]);
		for (int i = 1; i<=getPlayerList().length; i++) {
			getPlayerList()[i-1] = new Label();
			getPlayerList()[i-1].setText("player"+i);
		}
		switch(game.getPlayersNumber()) {
			case(4):getPlayerArray().add(getPlayerList()[3], 1, 1);
			case(3):getPlayerArray().add(getPlayerList()[2], 0, 1);
			case(2):getPlayerArray().add(getPlayerList()[1], 1, 0);
			case(1):getPlayerArray().add(getPlayerList()[0], 0, 0);
			break;
		}	
		getChildren().add(getPlayerArray());
	}
	public void initializeCurrentSquare(Game game){
		setSquareType(new Label());
		getChildren().add(getSquareType());
		
	}

	public GridPane getPlayerArray() {
		return playerArray;
	}

	public void setPlayerArray(GridPane playerArray) {
		this.playerArray = playerArray;
	}

	public Label[] getPlayerList() {
		return playerList;
	}

	public void setPlayerList(Label[] playerList) {
		this.playerList = playerList;
	}

	public Label getSquareType() {
		return squareType;
	}

	public void setSquareType(Label squareType) {
		this.squareType = squareType;
	}

}
