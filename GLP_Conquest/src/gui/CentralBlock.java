package gui;

import gui_datas.PositionDouble;
import gui_datas.ScreenSize;
import javafx.scene.layout.VBox;

public class CentralBlock extends VBox{
	
	private MenuBar menuBar;
	private GameBlock gameBlock;

	private ScreenSize screenSize;
	private int mapSize;

	public CentralBlock(ScreenSize screenSize, int mapSize, PositionDouble tracking) {
		super();
		setScreenSize(screenSize);
		setMapSize(mapSize);
		setMenuBar(new MenuBar());
		setGameBlock(new GameBlock(getScreenSize(), getMapSize(), tracking));
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

	public ScreenSize getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(ScreenSize screenSize) {
		this.screenSize = screenSize;
	}

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}

}
