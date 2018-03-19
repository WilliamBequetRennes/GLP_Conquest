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
	private LeaderMenu leaderMenu;
	
	public CentralMenu(BlockSize blockSize, Game game, PositionDouble tracking, GameBlock gameBlock, MenusBlock menusBlock) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		setLeaderMenu(new LeaderMenu(blockSize, this));
		setPlayerMenu(new PlayerMenu(blockSize, this));
		try {
			setMapCanvas(new MapCanvas(getBlockSize().getWidth(), getBlockSize().getHeight(), game));
			getMapCanvas().animatedMap(tracking, game, gameBlock, menusBlock);
		} catch (InvalidMapSizeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getChildren().add(getLeaderMenu());
		getChildren().add(getPlayerMenu());
		getChildren().add(getMapCanvas());
		getLeaderMenu().setVisible(false);
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

	public LeaderMenu getLeaderMenu() {
		return leaderMenu;
	}

	public void setLeaderMenu(LeaderMenu leaderMenu) {
		this.leaderMenu = leaderMenu;
	}
	
}
