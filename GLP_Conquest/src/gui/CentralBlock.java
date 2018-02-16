package gui;

import gui_datas.PositionDouble;
import exceptions.InvalidMapSizeNumberException;
import game.Game;
import gui_datas.BlockSize;
import javafx.scene.layout.VBox;
import map.Map;
import mapGenerator.MapGenerator;

public class CentralBlock extends VBox{
	private static final double MENU_BAR_HEIGHT = 0.08;
	private static final double GAME_BLOCK_HEIGHT = 0.92;
	private static final int PLAYERS_NUMBER = 4;
	
	private MenuBar menuBar;
	private GameBlock gameBlock;

	private BlockSize blockSize;
	private Game game;

	public CentralBlock(BlockSize blockSize, int mapSize, PositionDouble tracking) {
		super();
		setStyle("-fx-background-color: blue");
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		BlockSize menuBarSize = new BlockSize(getBlockSize().getWidth(), getBlockSize().getHeight()*MENU_BAR_HEIGHT);
		BlockSize gameBlockSize = new BlockSize(getBlockSize().getWidth(), getBlockSize().getHeight()*GAME_BLOCK_HEIGHT);

		try {
			MapGenerator mapGenerator = new MapGenerator();
			Map map = mapGenerator.generate(mapSize);
		
			setGame(new Game(PLAYERS_NUMBER, mapSize, map));
			setMenuBar(new MenuBar(menuBarSize));
			setGameBlock(new GameBlock(gameBlockSize, getGame(), tracking));
		} catch (InvalidMapSizeNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
