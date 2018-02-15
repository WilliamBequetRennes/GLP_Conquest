package gui;

import gui_datas.PositionDouble;
import gui_datas.BlockSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CentralBlock extends VBox{
	private static final double MENU_BAR_HEIGHT = 0.08;
	private static final double GAME_BLOCK_HEIGHT = 0.92;
	
	private MenuBar menuBar;
	private GameBlock gameBlock;

	private BlockSize blockSize;
	private int mapSize;

	public CentralBlock(BlockSize blockSize, int mapSize, PositionDouble tracking) {
		super();
		setStyle("-fx-background-color: blue");
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		BlockSize menuBarSize = new BlockSize(getBlockSize().getWidth(), getBlockSize().getHeight()*MENU_BAR_HEIGHT);
		BlockSize gameBlockSize = new BlockSize(getBlockSize().getWidth(), getBlockSize().getHeight()*GAME_BLOCK_HEIGHT);
		
		setMapSize(mapSize);
		setMenuBar(new MenuBar(menuBarSize));
		setGameBlock(new GameBlock(gameBlockSize, getMapSize(), tracking));
		getChildren().add(getMenuBar());
		getChildren().add(getGameBlock());
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public GameBlock getGameBlock() {
		return gameBlock;
	}

	public void setGameBlock(GameBlock gameBlock) {
		this.gameBlock = gameBlock;
	}

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}

}
