package gui;

import gui_datas.BlockSize;
import javafx.scene.layout.StackPane;

public class MenusBlock extends StackPane {
	
	private BlockSize screenSize;
	private GameOptions gameOptions;
	private LeaderSelection leaderSelection;
	private GlobalBlock globalBlock;
	
	private int playersNumber;
	private int turnsNumber;
	private int mapSize;
	private int[] leaders;
	
	public MenusBlock(BlockSize screenSize) {
		super();
		setScreenSize(screenSize);
		setPrefSize(getScreenSize().getWidth(), getScreenSize().getHeight());
		
		initializeGameOptions();
	}
	
	public void initializeGameOptions() {
		setGameOptions(new GameOptions(getScreenSize(), this));
		getChildren().add(getGameOptions());
		getGameOptions().toFront();
		getGameOptions().setVisible(true);
	}
	public void initializeLeaderSelection() {
		setLeaderSelection(new LeaderSelection(getScreenSize(), getPlayersNumber(), this));
		getChildren().add(getLeaderSelection());
		getLeaderSelection().toFront();
		getLeaderSelection().setVisible(true);
		getGameOptions().setVisible(false);
	}
	public void initializeGlobalBlock() {
		setGlobalBlock(new GlobalBlock(getScreenSize(), getPlayersNumber(), getTurnsNumber(), getMapSize(), getLeaders()));
		getChildren().add(getGlobalBlock());
		getGlobalBlock().toFront();
		getGlobalBlock().setVisible(true);
		getLeaderSelection().setVisible(false);
	}
	public void comeBackToGameOptions() {
		getLeaderSelection().setVisible(false);
		getGameOptions().toFront();
		getGameOptions().setVisible(true);
	}
	public BlockSize getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(BlockSize screenSize) {
		this.screenSize = screenSize;
	}

	public GlobalBlock getGlobalBlock() {
		return globalBlock;
	}

	public void setGlobalBlock(GlobalBlock globalBlock) {
		this.globalBlock = globalBlock;
	}

	public GameOptions getGameOptions() {
		return gameOptions;
	}

	public void setGameOptions(GameOptions gameOptions) {
		this.gameOptions = gameOptions;
	}

	public LeaderSelection getLeaderSelection() {
		return leaderSelection;
	}

	public void setLeaderSelection(LeaderSelection leaderSelection) {
		this.leaderSelection = leaderSelection;
	}

	public int getPlayersNumber() {
		return playersNumber;
	}

	public void setPlayersNumber(int playersNumber) {
		this.playersNumber = playersNumber;
	}

	public int getTurnsNumber() {
		return turnsNumber;
	}

	public void setTurnsNumber(int turnsNumber) {
		this.turnsNumber = turnsNumber;
	}

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}

	public int[] getLeaders() {
		return leaders;
	}

	public void setLeaders(int[] leaders) {
		this.leaders = leaders;
	}

}
