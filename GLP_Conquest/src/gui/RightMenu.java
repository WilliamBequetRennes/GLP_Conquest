package gui;

import game.Game;
import gui_datas.BlockSize;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class RightMenu extends StackPane{
	
	private BlockSize blockSize;
	private UsualRightMenu usualRightMenu;
	
	public RightMenu(BlockSize blockSize, Game game) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		setUsualRightMenu(new UsualRightMenu(game));
		getChildren().add(getUsualRightMenu());
	}

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}

	public UsualRightMenu getUsualRightMenu() {
		return usualRightMenu;
	}

	public void setUsualRightMenu(UsualRightMenu usualRightMenu) {
		this.usualRightMenu = usualRightMenu;
	}
}
