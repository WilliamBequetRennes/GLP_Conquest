package gui;

import game.Game;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UsualLeftMenu extends VBox{

	private GridPane playerArray;
	private Button[] playerList;
	private Label squareType;
	private Label attackBoost;
	private Label defenseBoost;
	private Label squareLevel;
	private Button levelUp;
	
	public UsualLeftMenu(Game game, GameBlock gameBlock) {
		super();
		initializePlayerArray(game, gameBlock);
		initializeCurrentSquare(game);
		initializeLevelUpButton(game);

		setAlignment(Pos.TOP_CENTER);
		displayContent();
	}
	
	public void initializePlayerArray(Game game, GameBlock gameBlock){
		setPlayerArray(new GridPane());
		setPlayerList(new Button[game.getPlayersNumber()]);
		for (int i = 1; i<=getPlayerList().length; i++) {
			int country = i-1;
			getPlayerList()[i-1] = new Button();
			getPlayerList()[i-1].setText("Player "+i);
			getPlayerList()[i-1].setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					gameBlock.getCentralMenu().getPlayerMenu().update(game.getPlayers()[country]);
					gameBlock.getCentralMenu().getPlayerMenu().setVisible(true);
					gameBlock.getCentralMenu().getPlayerMenu().toFront();
					gameBlock.getCentralMenu().getMapCanvas().setVisible(false);
					gameBlock.getCentralMenu().getLeaderMenu().setVisible(false);
				}
			});
			getPlayerList()[i-1].setId("player_button"+i);
		}
		switch(game.getPlayersNumber()) {
			case(4):getPlayerArray().add(getPlayerList()[3], 1, 1);
			case(3):getPlayerArray().add(getPlayerList()[2], 0, 1);
			case(2):getPlayerArray().add(getPlayerList()[1], 1, 0);
			case(1):getPlayerArray().add(getPlayerList()[0], 0, 0);
			break;
		}	
		getPlayerArray().setAlignment(Pos.CENTER);
	}
	public void initializeCurrentSquare(Game game){
		setSquareType(new Label());
		setAttackBoost(new Label());
		setDefenseBoost(new Label());
		setSquareLevel(new Label());
		
		getSquareType().setVisible(false);
		getAttackBoost().setVisible(false);
		getDefenseBoost().setVisible(false);
		getSquareLevel().setVisible(false);
		
		getSquareType().setId("type");
	}
	public void initializeLevelUpButton(Game game) {
		setLevelUp(new Button());
		getLevelUp().setText("Level Up");
		getLevelUp().setVisible(false);
		getLevelUp().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				game.getCurrentSquare().setLevel(game.getCurrentSquare().getLevel()+1);
				getSquareLevel().setText("Level : "+game.getCurrentSquare().getLevel());
				if(game.getCurrentSquare().getLevel()==3) {
					getLevelUp().setVisible(false);
				}
			}
		});
		getLevelUp().setId("menu_bar_button");
	}

	public void displayContent() {
		getChildren().add(getPlayerArray());
		getChildren().add(getSquareType());
		getChildren().add(getAttackBoost());
		getChildren().add(getDefenseBoost());
		getChildren().add(getSquareLevel());	
		getChildren().add(getLevelUp());	
	}
	
	public GridPane getPlayerArray() {
		return playerArray;
	}

	public void setPlayerArray(GridPane playerArray) {
		this.playerArray = playerArray;
	}

	public Button[] getPlayerList() {
		return playerList;
	}

	public void setPlayerList(Button[] playerList) {
		this.playerList = playerList;
	}

	public Label getSquareType() {
		return squareType;
	}

	public void setSquareType(Label squareType) {
		this.squareType = squareType;
	}

	public Label getAttackBoost() {
		return attackBoost;
	}

	public void setAttackBoost(Label attackBoost) {
		this.attackBoost = attackBoost;
	}

	public Label getDefenseBoost() {
		return defenseBoost;
	}

	public void setDefenseBoost(Label defenseBoost) {
		this.defenseBoost = defenseBoost;
	}

	public Label getSquareLevel() {
		return squareLevel;
	}

	public void setSquareLevel(Label squareLevel) {
		this.squareLevel = squareLevel;
	}

	public Button getLevelUp() {
		return levelUp;
	}

	public void setLevelUp(Button levelUp) {
		this.levelUp = levelUp;
	}

}
