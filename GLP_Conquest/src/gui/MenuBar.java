package gui;

import gui_datas.BlockSize;
import javafx.scene.layout.HBox;

public class MenuBar extends HBox{
	
	private BlockSize blockSize;
	
	public MenuBar(BlockSize blockSize) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
	}

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}
}
