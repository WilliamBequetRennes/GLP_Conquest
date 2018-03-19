package gui;

import game.Game;
import gui_datas.BlockSize;
import javafx.scene.layout.StackPane;

public class RightMenu extends StackPane{
	
	private BlockSize blockSize;
	private UsualRightMenu usualRightMenu;
	private UnitCreationMenu unitCreationMenu;
	private UnitCreator unitCreator;
	
	public RightMenu(BlockSize blockSize, Game game, GameBlock gameBlock) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		setUnitCreator(new UnitCreator(game, getBlockSize(), this));
		setUnitCreationMenu(new UnitCreationMenu(game, getBlockSize(), this));
		setUsualRightMenu(new UsualRightMenu(game, gameBlock, this));
		displayContent();
		
		setId("padding");
	}
	public void displayContent() {
		getChildren().add(getUnitCreator());
		getChildren().add(getUnitCreationMenu());
		getChildren().add(getUsualRightMenu());
		getUnitCreator().setVisible(false);
		getUnitCreationMenu().setVisible(false);
		getUsualRightMenu().setVisible(true);
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

	public UnitCreationMenu getUnitCreationMenu() {
		return unitCreationMenu;
	}

	public void setUnitCreationMenu(UnitCreationMenu unitCreationMenu) {
		this.unitCreationMenu = unitCreationMenu;
	}
	public UnitCreator getUnitCreator() {
		return unitCreator;
	}
	public void setUnitCreator(UnitCreator unitCreator) {
		this.unitCreator = unitCreator;
	}
}
