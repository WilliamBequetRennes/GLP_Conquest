package gui;

import gui_datas.PositionDouble;
import gui_datas.BlockSize;
import javafx.scene.layout.HBox;

public class GameBlock extends HBox{
	
	private static final double SIDE_BLOCK_WIDTH = 0.20;
	private static final double MAP_CANVAS_WIDTH = 0.60;
	
	private LeftMenu leftMenu;
	private RightMenu rightMenu;
	private CentralMenu centralMenu;
	private BlockSize blockSize;
	private int mapSize;
	
	public GameBlock(BlockSize blockSize, int mapSize, PositionDouble tracking) {
		super();
		setBlockSize(blockSize);
		setMapSize(mapSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		BlockSize sideBlockSize = new BlockSize(getBlockSize().getWidth()*SIDE_BLOCK_WIDTH, getBlockSize().getHeight());
		BlockSize centralBlockSize = new BlockSize(getBlockSize().getWidth()*MAP_CANVAS_WIDTH-1, getBlockSize().getHeight());
		
		System.out.println(sideBlockSize.getWidth());
		System.out.println(centralBlockSize.getWidth());
		System.out.println(sideBlockSize.getWidth()*2);
		System.out.println(sideBlockSize.getWidth()*2+centralBlockSize.getWidth());
		setLeftMenu(new LeftMenu(sideBlockSize));
		setCentralMenu(new CentralMenu(centralBlockSize, getMapSize(), tracking));
		setRightMenu(new RightMenu(sideBlockSize));
		
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

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}
}
