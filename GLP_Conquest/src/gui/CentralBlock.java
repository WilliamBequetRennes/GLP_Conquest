package gui;

import gui_datas.PositionDouble;
import game.Game;
import gui_datas.BlockSize;
import javafx.scene.layout.VBox;

public class CentralBlock extends VBox{
	private static final double MENU_BAR_HEIGHT = 0.08;
	private static final double GAME_BLOCK_HEIGHT = 0.92;
	
	private MenuBar menuBar;
	private GameBlock gameBlock;

	private BlockSize blockSize;

	public CentralBlock(BlockSize blockSize, Game game, PositionDouble tracking, MenusBlock menusBlock) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		BlockSize menuBarSize = new BlockSize(getBlockSize().getWidth(), getBlockSize().getHeight()*MENU_BAR_HEIGHT);
		BlockSize gameBlockSize = new BlockSize(getBlockSize().getWidth(), getBlockSize().getHeight()*GAME_BLOCK_HEIGHT);

		setGameBlock(new GameBlock(gameBlockSize, game, tracking, menusBlock));
		setMenuBar(new MenuBar(menuBarSize, game, this));
		
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

}
