package gui;

import game.Game;
import game.Turn;
import gui_data.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class MenuBar extends HBox{

	private static final double LEFT_SIDE = 0.25;
	private static final double RIGHT_SIDE = 0.75;
	
	private BlockSize blockSize;
	private HBox leftSide;
	private HBox rightSide;
	private Button menu;
	private Label mapNumber;
	private Label turnNumber;
	private Button endTurn;
	
	Turn turn;
	private boolean menuClicked;
	
	public MenuBar(BlockSize blockSize, Game game, CentralBlock centralBlock) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		initializeSides();
		initializeMenuButton(game);
		initializeMapNumber(game);
		initializeTurnNumber(game);
		initializeEndTurnButton(game, centralBlock);
		initializeMenuClick(centralBlock.getGameBlock());
	}
	
	public void initializeMenuClick(GameBlock gameBlock) {
		getMenu().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if(!isMenuClicked()) {
					gameBlock.getLeftMenu().getGameMenu().setVisible(true);
					gameBlock.getLeftMenu().getGameMenu().toFront();
					gameBlock.getLeftMenu().getUsualLeftMenu().setVisible(false);
					setMenuClicked(true);
				}
				else {
					gameBlock.getLeftMenu().getUsualLeftMenu().setVisible(true);
					gameBlock.getLeftMenu().getUsualLeftMenu().toFront();
					gameBlock.getLeftMenu().getGameMenu().setVisible(false);
					setMenuClicked(false);
				}
			}
		});
	}
	
	public void initializeSides() {
		setLeftSide(new HBox());
		setRightSide(new HBox());
		getLeftSide().setPrefSize(getBlockSize().getWidth()*LEFT_SIDE, getBlockSize().getHeight());
		getRightSide().setPrefSize(getBlockSize().getWidth()*RIGHT_SIDE, getBlockSize().getHeight());
		getLeftSide().setAlignment(Pos.CENTER_LEFT);
		getRightSide().setAlignment(Pos.CENTER_RIGHT);
		getLeftSide().setId("separed");
		getRightSide().setId("separed");
		
		getChildren().add(getLeftSide());
		getChildren().add(getRightSide());
	}
	public void initializeMenuButton(Game game) {
		setMenu(new Button());
		getMenu().setText("Menu");
		getLeftSide().getChildren().add(getMenu());
		getMenu().setId("switch_button");
		setMenuClicked(false);
	}
	public void initializeMapNumber(Game game) {
		setTurnNumber(new Label());
		getTurnNumber().setText("map : "+game.getMap().getNumber());
		getRightSide().getChildren().add(getTurnNumber());
	}
	public void initializeTurnNumber(Game game) {
		setTurnNumber(new Label());
		getTurnNumber().setText("turn : "+game.getCurrentTurn());
		getRightSide().getChildren().add(getTurnNumber());
	}
	public void initializeEndTurnButton(Game game, CentralBlock centralBlock) {
		setEndTurn(new Button());
		getEndTurn().setText("End turn");
		turn = new Turn();
		MenuBar thisMenuBar = this;
		getEndTurn().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				setMenuClicked(false);
				turn.nextTurn(game, thisMenuBar);
				clearMenus(centralBlock.getGameBlock());
				refreshUsualRightMenu(centralBlock.getGameBlock().getRightMenu().getUsualRightMenu(), game);
				int playerNumber = centralBlock.getGameBlock().getCentralMenu().getPlayerMenu().getPlayerNumber();
				if(playerNumber==0) {
					playerNumber++;
				}
				centralBlock.getGameBlock().getCentralMenu().getPlayerMenu().update(game.getPlayers()[playerNumber-1]);
				reinitializeUsualLeftMenu(centralBlock.getGameBlock().getLeftMenu().getUsualLeftMenu(), game);
			}
		});
		getEndTurn().setId("switch_button");
		
		getRightSide().getChildren().add(getEndTurn());
	}
	
	public void reinitializeUsualLeftMenu(UsualLeftMenu menu, Game game) {
		menu.getSquareType().setVisible(false);
		menu.getAttackBoost().setVisible(false);
		menu.getDefenseBoost().setVisible(false);
		menu.getSquareLevel().setVisible(false);
		menu.getLevelUp().setVisible(false);
		menu.getUnitData().setVisible(false);
		menu.getUnitType().setVisible(false);
		menu.getAttack().setVisible(false);
		menu.getDefense().setVisible(false);
		menu.getHealthPoints().setVisible(false);
		menu.getMovePoints().setVisible(false);
		menu.getRange().setVisible(false);
		menu.getUpkeep().setVisible(false);
	}
	
	public void refreshUsualRightMenu(UsualRightMenu menu, Game game) {
		menu.getCurrentPlayer().setText("Player "+game.getCurrentPlayer());
		menu.updatePortrait(game.getPlayers()[game.getCurrentPlayer()-1].getLeader().getNumber());
		menu.getNumberOfSquares().setText("Number of squares : "+game.getPlayers()[game.getCurrentPlayer()-1].getSquareNumber());
		menu.getMoney().setText("Money : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getMoney());
		menu.getFood().setText("Food : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getFood());
		menu.getOil().setText("Oil : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getOil());
		menu.getElectricity().setText("Electricity : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getElectricity());
		menu.getCurrentPlayer().setId("player_button"+game.getCurrentPlayer());
		menu.getCreateUnit().setVisible(false);
		
	}
	
	public void clearMenus(GameBlock gameBlock) {
		//reset left menu
		gameBlock.getLeftMenu().getUsualLeftMenu().setVisible(true);
		gameBlock.getLeftMenu().getUsualLeftMenu().toFront();
		gameBlock.getLeftMenu().getGameMenu().setVisible(false);
		setMenuClicked(false);

		//reset left menu
		gameBlock.getRightMenu().getUsualRightMenu().setVisible(true);
		gameBlock.getRightMenu().getUsualRightMenu().toFront();
		gameBlock.getRightMenu().getUnitCreationMenu().setVisible(false);
		gameBlock.getRightMenu().getUnitCreator().setVisible(false);
	}
	public HBox getLeftSide() {
		return leftSide;
	}

	public void setLeftSide(HBox leftSide) {
		this.leftSide = leftSide;
	}

	public HBox getRightSide() {
		return rightSide;
	}

	public void setRightSide(HBox rightSide) {
		this.rightSide = rightSide;
	}

	public Button getMenu() {
		return menu;
	}

	public void setMenu(Button menu) {
		this.menu = menu;
	}

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}

	public Label getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(Label turnNumber) {
		this.turnNumber = turnNumber;
	}

	public Button getEndTurn() {
		return endTurn;
	}

	public void setEndTurn(Button endTurn) {
		this.endTurn = endTurn;
	}

	public Label getMapNumber() {
		return mapNumber;
	}

	public void setMapNumber(Label mapNumber) {
		this.mapNumber = mapNumber;
	}

	public boolean isMenuClicked() {
		return menuClicked;
	}

	public void setMenuClicked(boolean menuClicked) {
		this.menuClicked = menuClicked;
	}
}
