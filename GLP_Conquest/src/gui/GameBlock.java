package gui;

import gui_datas.PositionDouble;
import game.Game;
import gui_datas.BlockSize;
import javafx.scene.layout.HBox;

public class GameBlock extends HBox{
	
	private static final double SIDE_BLOCK_WIDTH = 0.20;
	private static final double MAP_CANVAS_WIDTH = 0.60;
	
	private LeftMenu leftMenu;
	private RightMenu rightMenu;
	private CentralMenu centralMenu;
	private BlockSize blockSize;
	
	public GameBlock(BlockSize blockSize, Game game, PositionDouble tracking, MenusBlock menusBlock) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		BlockSize sideBlockSize = new BlockSize(getBlockSize().getWidth()*SIDE_BLOCK_WIDTH, getBlockSize().getHeight());
		BlockSize centralBlockSize = new BlockSize(getBlockSize().getWidth()*MAP_CANVAS_WIDTH-1, getBlockSize().getHeight());
		
		setLeftMenu(new LeftMenu(sideBlockSize, game, this, menusBlock));
		setCentralMenu(new CentralMenu(centralBlockSize, game, tracking, this));
		setRightMenu(new RightMenu(sideBlockSize, game, this));
		
		getChildren().add(getLeftMenu());
		getChildren().add(getCentralMenu());
		getChildren().add(getRightMenu());
	}

	public LeftMenu getLeftMenu() {
		return leftMenu;
	}

	public void setLeftMenu(LeftMenu leftMenu) {
		this.leftMenu = leftMenu;
	}

	public RightMenu getRightMenu() {
		return rightMenu;
	}

	public void setRightMenu(RightMenu rightMenu) {
		this.rightMenu = rightMenu;
	}

	public CentralMenu getCentralMenu() {
		return centralMenu;
	}

	public void setCentralMenu(CentralMenu centralMenu) {
		this.centralMenu = centralMenu;
	}

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}

}
