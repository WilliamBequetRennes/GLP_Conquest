package gui;

import exceptions.InvalidMapSizeNumberException;
import gui_datas.PositionDouble;
import gui_datas.BlockSize;
import javafx.scene.layout.StackPane;

public class CentralMenu extends StackPane{

	private BlockSize blockSize;
	private int mapSize;
	
	private MapCanvas mapCanvas;
	
	public CentralMenu(BlockSize blockSize, int mapSize, PositionDouble tracking) {
		super();
		setBlockSize(blockSize);
		setMapSize(mapSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());

		try {
			setMapCanvas(new MapCanvas(getBlockSize().getWidth(), getBlockSize().getHeight(), getMapSize()));
			getMapCanvas().animatedMap(tracking);
		} catch (InvalidMapSizeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getChildren().add(mapCanvas);
		mapCanvas.setVisible(true);
		
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

	public MapCanvas getMapCanvas() {
		return mapCanvas;
	}

	public void setMapCanvas(MapCanvas mapCanvas) {
		this.mapCanvas = mapCanvas;
	}
	
}
