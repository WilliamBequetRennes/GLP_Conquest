package gui;

import game.Game;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import units.Unit;

public class UsualLeftMenu extends VBox{

	private GridPane playerArray;
	private Button[] playerList;
	
	private Label squareType;
	private Label attackBoost;
	private Label defenseBoost;
	private Label squareLevel;
	private Button levelUp;
	
	private GridPane unitData;
	private Label unitType;
	private Label healthPoints;
	private Label movePoints;
	private Label attack;
	private Label defense;
	private Label range;
	private Label upkeep;
	
	public UsualLeftMenu(Game game, GameBlock gameBlock) {
		super();
		initializePlayerArray(game, gameBlock);
		initializeCurrentSquare();
		initializeLevelUpButton(game);
		initializeUnit();

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
	public void initializeCurrentSquare(){
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
	public void initializeUnit(){
		setUnitData(new GridPane());
		setUnitType(new Label());
		setAttack(new Label());
		setDefense(new Label());
		setHealthPoints(new Label());
		setMovePoints(new Label());
		setRange(new Label());
		setUpkeep(new Label());
		
		getUnitData().setVisible(false);
		getUnitType().setVisible(false);
		getAttack().setVisible(false);
		getDefense().setVisible(false);
		getHealthPoints().setVisible(false);
		getMovePoints().setVisible(false);
		getRange().setVisible(false);
		getUpkeep().setVisible(false);
		
		getUnitType().setId("type");
		getUnitData().setAlignment(Pos.CENTER);
	}
	
	public void update(Game game) {
		String type = "";
		switch(game.getCurrentSquare().getType()) {
			case(0):type="Water";
			break;
			case(1):type="Land";
			break;
			case(2):type="Desert";
			break;
			case(3):type="Forest";
			break;
			case(4):type="Mont";
			break;
			case(5):type="Mine";
			break;
			case(6):type="Farm";
			break;
			case(7):type="Oil well";
			break;
			case(8):type="Nuclear plant";
			break;
			case(9):type="City";
			break;
		}
		String attackBoost = "Attack x"+game.getCurrentSquare().getBonus().getAttack();	
		String defenseBoost = "Defense x"+game.getCurrentSquare().getBonus().getDefense();
		String level = "Level : "+game.getCurrentSquare().getLevel();
		
		getSquareType().setText(type);
		getAttackBoost().setText(attackBoost);
		getDefenseBoost().setText(defenseBoost);
		getSquareLevel().setText(level);
		
		if(game.getCurrentSquare().getUnit()){
			int faction = game.getCurrentSquare().getFaction();
			Unit unit = game.getPlayers()[faction-1].getUnits().get(game.getCurrentSquare().getPosition());
			
			getUnitType().setText(unit.getName());
			getHealthPoints().setText("HP : "+unit.getCurrentHealth()+"/"+unit.getMaxHealth());
			getMovePoints().setText("MP : "+unit.getMovement()+"/"+unit.getMaxMovement());
			getAttack().setText("ATK : "+unit.getAttack());
			getDefense().setText("DEF : "+unit.getDefense());
			getRange().setText("Range : "+unit.getRange());
			switch(unit.getType()) {
			case(0):
			case(1):getUpkeep().setText("Upkeep : "+unit.getUpkeep().getFood()+" Food");
			break;
			case(2):
			case(3):getUpkeep().setText("Upkeep : "+unit.getUpkeep().getElectricity()+" Elec");
			break;
			case(4):
			case(5):
			case(6):
			case(7):getUpkeep().setText("Upkeep : "+unit.getUpkeep().getOil()+" Oil");
			break;
			}
			
			getUnitData().setVisible(true);
			getUnitType().setVisible(true);
			getAttack().setVisible(true);
			getDefense().setVisible(true);
			getHealthPoints().setVisible(true);
			getMovePoints().setVisible(true);
			getRange().setVisible(true);
			getUpkeep().setVisible(true);
		}
		else {
			getUnitData().setVisible(false);
			getUnitType().setVisible(false);
			getAttack().setVisible(false);
			getDefense().setVisible(false);
			getHealthPoints().setVisible(false);
			getMovePoints().setVisible(false);
			getRange().setVisible(false);
			getUpkeep().setVisible(false);
		}
		
	}

	public void displayContent() {
		getChildren().add(getPlayerArray());
		getChildren().add(getSquareType());
		getChildren().add(getAttackBoost());
		getChildren().add(getDefenseBoost());
		getChildren().add(getSquareLevel());	
		getChildren().add(getLevelUp());	

		getUnitData().add(getMovePoints(), 0, 0);
		getUnitData().add(getRange(), 1, 0);
		getUnitData().add(getAttack(), 0, 1);
		getUnitData().add(getDefense(), 1, 1);

		getChildren().add(getUnitType());
		getChildren().add(getHealthPoints());
		getChildren().add(getUnitData());
		getChildren().add(getUpkeep());
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

	public Label getUnitType() {
		return unitType;
	}

	public void setUnitType(Label unitType) {
		this.unitType = unitType;
	}

	public Label getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(Label healthPoints) {
		this.healthPoints = healthPoints;
	}

	public Label getMovePoints() {
		return movePoints;
	}

	public void setMovePoints(Label movePoints) {
		this.movePoints = movePoints;
	}

	public Label getRange() {
		return range;
	}

	public void setRange(Label range) {
		this.range = range;
	}

	public Label getUpkeep() {
		return upkeep;
	}

	public void setUpkeep(Label upkeep) {
		this.upkeep = upkeep;
	}

	public Label getAttack() {
		return attack;
	}

	public void setAttack(Label attack) {
		this.attack = attack;
	}

	public Label getDefense() {
		return defense;
	}

	public void setDefense(Label defense) {
		this.defense = defense;
	}

	public GridPane getUnitData() {
		return unitData;
	}

	public void setUnitData(GridPane unitData) {
		this.unitData = unitData;
	}

}
