package gui;

import gui_datas.ScreenSize;
import javafx.scene.layout.HBox;

public class GameBlock extends HBox{
	
	private LeftMenu leftMenu;
	private RightMenu rightMenu;
	private CentralMenu centralMenu;
	private ScreenSize screenSize;
	private int mapSize;
	
	public GameBlock(ScreenSize screenSize, int mapSize) {
		super();
		setLeftMenu(new LeftMenu());
		setCentralMenu(new CentralMenu(screenSize));
		setRightMenu(new RightMenu());
		setScreenSize(screenSize);
		setMapSize(mapSize);
		
		getChildren().add(getLeftMenu());
		getChildren().add(getCentralMenu());
		getChildren().add(getRightMenu());
	}

	public LeftMenu getLeftMenu() {
		return leftMenu;
	}

	public void setLeftMenu(LeftMenu leftMenu) {
		this.leftMenu = leftMenu;
	}

	public RightMenu getRightMenu() {
		return rightMenu;
	}

	public void setRightMenu(RightMenu rightMenu) {
		this.rightMenu = rightMenu;
	}

	public CentralMenu getCentralMenu() {
		return centralMenu;
	}

	public void setCentralMenu(CentralMenu centralMenu) {
		this.centralMenu = centralMenu;
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
