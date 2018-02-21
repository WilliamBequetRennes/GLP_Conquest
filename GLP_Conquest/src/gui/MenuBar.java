package gui;

import game.Game;
import gui_datas.BlockSize;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class MenuBar extends HBox{
	
	private BlockSize blockSize;
	private Label turnNumber;
	private Button endTurn;
	
	public MenuBar(BlockSize blockSize, Game game) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		initializeTurnNumber(game);
		initializeEndTurnButton(game);
		getChildren().add(getTurnNumber());
		getChildren().add(getEndTurn());
	}
	
	public void initializeTurnNumber(Game game) {
		setTurnNumber(new Label());
		getTurnNumber().setText("turn : "+game.getTurnNumber());
	}
	public void initializeEndTurnButton(Game game) {
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
			}
		});
		
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
