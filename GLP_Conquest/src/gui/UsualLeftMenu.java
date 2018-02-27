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
	private Label[] playerList;
	private Label squareType;
	private Label attackBoost;
	private Label defenseBoost;
	private Label squareLevel;
	private Button levelUp;
	
	public UsualLeftMenu(Game game) {
		super();
		initializePlayerArray(game);
		initializeCurrentSquare(game);
		initializeLevelUpButton(game);

		setAlignment(Pos.TOP_CENTER);
		displayContent();
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
		getPlayerArray().setAlignment(Pos.CENTER);
	}
	public void initializeCurrentSquare(Game game){
		setSquareType(new Label());
		setAttackBoost(new Label());
		setDefenseBoost(new Label());
		setSquareLevel(new Label());
		
		/*getSquareType().setVisible(false);
		getAttackBoost().setVisible(false);
		getDefenseBoost().setVisible(false);*/
		getSquareLevel().setVisible(false);
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
