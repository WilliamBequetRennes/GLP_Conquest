package gui;

import datas.Position;
import exceptions.InvalidMapSizeNumberException;
import game.Game;
import gui_datas.PositionDouble;
import gui_datas.BlockSize;
import javafx.scene.layout.StackPane;

public class CentralMenu extends StackPane{

	private BlockSize blockSize;
	private MapCanvas mapCanvas;
	
	public CentralMenu(BlockSize blockSize, Game game, PositionDouble tracking, Position currentSelection) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());

		try {
			setMapCanvas(new MapCanvas(getBlockSize().getWidth(), getBlockSize().getHeight(), game));
			getMapCanvas().animatedMap(tracking, currentSelection, game);
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

	public MapCanvas getMapCanvas() {
		return mapCanvas;
	}

	public void setMapCanvas(MapCanvas mapCanvas) {
		this.mapCanvas = mapCanvas;
	}
	
}
