package gui;

import game.Game;
import gui_datas.BlockSize;
import javafx.scene.layout.StackPane;

public class LeftMenu extends StackPane{
	
	private BlockSize blockSize;
	private GameMenu gameMenu;
	private UsualLeftMenu usualLeftMenu;
	
	public LeftMenu(BlockSize blockSize, Game game, GameBlock gameBlock){
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		setGameMenu(new GameMenu(game, getBlockSize(), gameBlock));
		setUsualLeftMenu(new UsualLeftMenu(game, gameBlock));
		displayContent();
		
	}
	
	public void displayContent() {
		getChildren().add(getGameMenu());
		getChildren().add(getUsualLeftMenu());
		getGameMenu().setVisible(false);
		getUsualLeftMenu().setVisible(true);
	}
	
	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}


	public UsualLeftMenu getUsualLeftMenu() {
		return usualLeftMenu;
	}


	public void setUsualLeftMenu(UsualLeftMenu usualLeftMenu) {
		this.usualLeftMenu = usualLeftMenu;
	}

	public GameMenu getGameMenu() {
		return gameMenu;
	}

	public void setGameMenu(GameMenu gameMenu) {
		this.gameMenu = gameMenu;
	}

}
