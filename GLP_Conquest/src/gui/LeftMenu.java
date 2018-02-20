package gui;

import game.Game;
import gui_datas.BlockSize;
import javafx.scene.layout.StackPane;

public class LeftMenu extends StackPane{
	
	private BlockSize blockSize;
	private UsualLeftMenu usualLeftMenu;
	
	public LeftMenu(BlockSize blockSize, Game game){
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		setUsualLeftMenu(new UsualLeftMenu(game));
		getChildren().add(getUsualLeftMenu());
		
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

}
