package gui;

import exceptions.InvalidMapSizeNumberException;
import game.Game;
import gui_datas.PositionDouble;
import gui_datas.BlockSize;
import javafx.scene.layout.StackPane;

public class CentralMenu extends StackPane{

	private BlockSize blockSize;
	private MapCanvas mapCanvas;
	private PlayerMenu playerMenu;
	
	public CentralMenu(BlockSize blockSize, Game game, PositionDouble tracking, GameBlock gameBlock) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		setPlayerMenu(new PlayerMenu(blockSize, this));
		try {
			setMapCanvas(new MapCanvas(getBlockSize().getWidth(), getBlockSize().getHeight(), game));
			getMapCanvas().animatedMap(tracking, game, gameBlock);
		} catch (InvalidMapSizeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getChildren().add(getPlayerMenu());
		getChildren().add(getMapCanvas());
		getPlayerMenu().setVisible(false);
		getMapCanvas().setVisible(true);
		
	}

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}

	public MapCanvas getMapCanvas() {
		return mapCanvas;
	}

	public void setMapCanvas(MapCanvas mapCanvas) {
		this.mapCanvas = mapCanvas;
	}

	public PlayerMenu getPlayerMenu() {
		return playerMenu;
	}

	public void setPlayerMenu(PlayerMenu playerMenu) {
		this.playerMenu = playerMenu;
	}
	
}
