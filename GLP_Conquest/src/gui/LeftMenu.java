package gui;

import gui_datas.BlockSize;
import javafx.scene.layout.StackPane;

public class LeftMenu extends StackPane{
	
	private BlockSize blockSize;
	
	public LeftMenu(BlockSize blockSize) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		setStyle("-fx-background-color: red");
	}

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}
}
