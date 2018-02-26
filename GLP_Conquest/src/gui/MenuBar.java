package gui;

import game.Game;
import gui_datas.BlockSize;
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
	private Label turnNumber;
	private Button endTurn;
	
	public MenuBar(BlockSize blockSize, Game game, CentralBlock centralBlock) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		initializeSides();
		initializeMenuButton(game);
		initializeTurnNumber(game);
		initializeEndTurnButton(game, centralBlock);
	}
	
	public void initializeSides() {
		setLeftSide(new HBox());
		setRightSide(new HBox());
		getLeftSide().setPrefSize(getBlockSize().getWidth()*LEFT_SIDE, getBlockSize().getHeight());
		getRightSide().setPrefSize(getBlockSize().getWidth()*RIGHT_SIDE, getBlockSize().getHeight());
		getLeftSide().setAlignment(Pos.CENTER_LEFT);
		getRightSide().setAlignment(Pos.CENTER_RIGHT);
		getChildren().add(getLeftSide());
		getChildren().add(getRightSide());
	}
	public void initializeMenuButton(Game game) {
		setMenu(new Button());
		getMenu().setText("Menu");
		getLeftSide().getChildren().add(getMenu());
	}
	public void initializeTurnNumber(Game game) {
		setTurnNumber(new Label());
		getTurnNumber().setText("turn : "+game.getTurnNumber());
		getRightSide().getChildren().add(getTurnNumber());
	}
	public void initializeEndTurnButton(Game game, CentralBlock centralBlock) {
		setEndTurn(new Button());
		getEndTurn().setText("End turn");
		getEndTurn().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				game.setCurrentPlayer(game.getCurrentPlayer()+1);
				if(game.getCurrentPlayer()>game.getPlayersNumber()) {
					game.setCurrentPlayer(1);
					game.setTurnNumber(game.getTurnNumber()+1);
					getTurnNumber().setText("turn : "+game.getTurnNumber());
				}
				centralBlock.getGameBlock().getRightMenu().getUsualRightMenu().getCurrentPlayer().setText("Player : "+game.getCurrentPlayer());
				centralBlock.getGameBlock().getRightMenu().getUsualRightMenu().initializePortrait(game.getPlayers()[game.getCurrentPlayer()].getLeader().getNumber());
			}
		});
		getRightSide().getChildren().add(getEndTurn());
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
}
