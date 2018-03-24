package gui;

import gui_data.BlockSize;
import javafx.scene.layout.StackPane;

public class MenusBlock extends StackPane {
	
	private BlockSize screenSize;
	private StartMenu startMenu;
	private GameOptions gameOptions;
	private LeaderSelection leaderSelection;
	private PlayableBlock playableBlock;
	
	private int playersNumber;
	private int turnsNumber;
	private int mapSize;
	private int[] leaders;
	private int mapNumber;
	
	public MenusBlock(BlockSize screenSize) {
		super();
		setScreenSize(screenSize);
		setPrefSize(getScreenSize().getWidth(), getScreenSize().getHeight());
		
		initializeStartMenu();
	}
	
	public void initializeStartMenu() {
		setStartMenu(new StartMenu(getScreenSize(), this));
		getChildren().add(getStartMenu());
		getStartMenu().toFront();
		getStartMenu().setVisible(true);
	}
	public void initializeGameOptions() {
		setGameOptions(new GameOptions(getScreenSize(), this));
		getChildren().add(getGameOptions());
		getGameOptions().toFront();
		getGameOptions().setVisible(true);
		getStartMenu().setVisible(false);
	}
	public void initializeLeaderSelection() {
		setLeaderSelection(new LeaderSelection(getScreenSize(), getPlayersNumber(), this));
		getChildren().add(getLeaderSelection());
		getLeaderSelection().toFront();
		getLeaderSelection().setVisible(true);
		getGameOptions().setVisible(false);
	}
	public void initializePlayableBlock() {
		setPlayableBlock(new PlayableBlock(getScreenSize(), getPlayersNumber(), getTurnsNumber(), getMapSize(), getLeaders(), getMapNumber(), this));
		getChildren().add(getPlayableBlock());
		getPlayableBlock().toFront();
		getPlayableBlock().setVisible(true);
		getLeaderSelection().setVisible(false);
	}
	public void comeBackToStartMenu() {
		getGameOptions().setVisible(false);
		getStartMenu().toFront();
		getStartMenu().setVisible(true);
	}
	public void comeBackToGameOptions() {
		getLeaderSelection().setVisible(false);
		getGameOptions().toFront();
		getGameOptions().setVisible(true);
	}
	public void quitGame() {
		getPlayableBlock().setVisible(false);
		getStartMenu().toFront();
		getStartMenu().setVisible(true);
	}
	public BlockSize getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(BlockSize screenSize) {
		this.screenSize = screenSize;
	}

	public PlayableBlock getPlayableBlock() {
		return playableBlock;
	}

	public void setPlayableBlock(PlayableBlock playableBlock) {
		this.playableBlock = playableBlock;
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

	public StartMenu getStartMenu() {
		return startMenu;
	}

	public void setStartMenu(StartMenu startMenu) {
		this.startMenu = startMenu;
	}

	public int getMapNumber() {
		return mapNumber;
	}

	public void setMapNumber(int mapNumber) {
		this.mapNumber = mapNumber;
	}

}
