package gui;

import exceptions.InvalidMapSizeNumberException;
import gui_datas.ScreenSize;
import javafx.scene.layout.StackPane;

public class CentralMenu extends StackPane{

	private ScreenSize screenSize;
	private int mapSize;
	
	private MapCanvas mapCanvas;
	
	public CentralMenu(ScreenSize screenSize) {
		super();
		setScreenSize(screenSize);
		setMapSize(mapSize);

		try {
			setMapCanvas(new MapCanvas(getScreenSize().getWidth(), getScreenSize().getHeight(), getMapSize()));
			getMapCanvas().generateMap();
		} catch (InvalidMapSizeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getChildren().add(mapCanvas);
		mapCanvas.setVisible(true);
		
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

	public MapCanvas getMapCanvas() {
		return mapCanvas;
	}

	public void setMapCanvas(MapCanvas mapCanvas) {
		this.mapCanvas = mapCanvas;
	}
	
}
