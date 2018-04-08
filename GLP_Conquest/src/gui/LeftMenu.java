package gui;

import game.Game;
import gui_data.BlockSize;
import javafx.scene.layout.StackPane;

public class LeftMenu extends StackPane{
	
	private BlockSize blockSize;
	private GameMenu gameMenu;
	private FightForsights fightForsights;
	private UsualLeftMenu usualLeftMenu;
	
	public LeftMenu(BlockSize blockSize, Game game, GameBlock gameBlock, MenusBlock menusBlock){
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		setGameMenu(new GameMenu(game, getBlockSize(), gameBlock, menusBlock));
		setFightForsights(new FightForsights(game, blockSize, gameBlock));
		setUsualLeftMenu(new UsualLeftMenu(game, gameBlock));
		displayContent();
		
		setId("padding");
		
	}
	
	public void displayContent() {
		getChildren().add(getGameMenu());
		getChildren().add(getFightForsights());
		getChildren().add(getUsualLeftMenu());
		getGameMenu().setVisible(false);
		getFightForsights().setVisible(false);
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

	public FightForsights getFightForsights() {
		return fightForsights;
	}

	public void setFightForsights(FightForsights fightForsights) {
		this.fightForsights = fightForsights;
	}

}
