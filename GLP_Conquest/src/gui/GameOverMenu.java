package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import game.Results;
import gui_data.BlockSize;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameOverMenu extends HBox{

	private final static int NUMBER_OF_LEADERS = 6;
	private final static double PLAYER_BOXES_WIDTH = 0.24;
	private final static double PLAYER_BOXES_HEIGHT = 0.80;
	
	private BlockSize screenSize;
	private String leadersFile;
	private Results results;
	
	private Label[] players;
	private Image[] portraits;
	private ImageView[] leaders;
	private Label[] names;
	
	private VBox[] ladder;
	private GridPane[] grids;
	
	private Label[] ranks;
	private Label[] numberOfTerrains;
	private Label[] numberOfCities;
	private Label[] numberOfBuildings;
	private Label[] terrainPoints;
	private Label[] cityPoints;
	private Label[] buildingPoints;
	private Label[] totalPoints;
	
	public GameOverMenu(BlockSize screenSize, Results results, MenusBlock menusBlock) {
		super();
		setScreenSize(screenSize);
		setPrefSize(getScreenSize().getWidth(), getScreenSize().getHeight());
		setId("gradient");
		setLeadersFile("leaders.txt");
		setResults(results);
		setPortraits(initializeLeaderPortraits());
		
		initializeLadder();
		initializeLabels();
		initializeLeaders();
		initializeGrids();
		
		displayContent();
		setAlignment(Pos.CENTER);
		setMaxHeight(getScreenSize().getHeight()*PLAYER_BOXES_HEIGHT);
	}
	
	public void initializeLadder() {
		setLadder(new VBox[getResults().getPlayersNumber()]);
		for(int i = 0; i<getResults().getPlayersNumber(); i++) {
			getLadder()[i] = new VBox();

			getLadder()[i].setAlignment(Pos.CENTER);
			getLadder()[i].setPrefHeight(getScreenSize().getHeight()*PLAYER_BOXES_HEIGHT);
			getLadder()[i].setPrefWidth(getScreenSize().getWidth()*PLAYER_BOXES_WIDTH);
			getLadder()[i].setId("leader_selection"+(i+1));
			getLadder()[i].setAlignment(Pos.CENTER);
		}
	}
	
	public void initializeLabels() {
		setPlayers(new Label[getResults().getPlayersNumber()]);
		setRanks(new Label[getResults().getPlayersNumber()]);
		
		setNumberOfTerrains(new Label[getResults().getPlayersNumber()]);
		setNumberOfBuildings(new Label[getResults().getPlayersNumber()]);
		setNumberOfCities(new Label[getResults().getPlayersNumber()]);
		
		setTerrainPoints(new Label[getResults().getPlayersNumber()]);
		setBuildingPoints(new Label[getResults().getPlayersNumber()]);
		setCityPoints(new Label[getResults().getPlayersNumber()]);
		setTotalPoints(new Label[getResults().getPlayersNumber()]);
		
		for(int i = 0; i<getResults().getPlayersNumber(); i++) {
			getPlayers()[i] = new Label();
			getRanks()[i] = new Label();
			getNumberOfTerrains()[i] = new Label();
			getNumberOfBuildings()[i] = new Label();
			getNumberOfCities()[i] = new Label();
			getTerrainPoints()[i] = new Label();
			getBuildingPoints()[i] = new Label();
			getCityPoints()[i] = new Label();
			getTotalPoints()[i] = new Label();
			
			getPlayers()[i].setText("Player "+(i+1));
			getRanks()[i].setText("Rank "+getResults().getRanks()[i]);
			getNumberOfTerrains()[i].setText("Terrains : "+getResults().getNumberOfTerrains()[i]);
			getNumberOfBuildings()[i].setText("Buildings : "+getResults().getNumberOfBuildings()[i]);
			getNumberOfCities()[i].setText("Cities : "+getResults().getNumberOfCities()[i]);
			getTerrainPoints()[i].setText("Points : "+getResults().getTerrainPoints()[i]);
			getBuildingPoints()[i].setText("Points : "+getResults().getBuildingPoints()[i]);
			getCityPoints()[i].setText("Points : "+getResults().getCityPoints()[i]);
			getTotalPoints()[i].setText("Total points : "+getResults().getTotalPoints()[i]);
			
			getPlayers()[i].setId("player"+(i+1));
			getRanks()[i].setId("rank");
			getTotalPoints()[i].setId("leader_name");
		}
	}
	
	public void initializeGrids() {
		setGrids(new GridPane[getResults().getPlayersNumber()]);
		for(int i = 0; i<getResults().getPlayersNumber(); i++) {
			getGrids()[i] = new GridPane();
			getGrids()[i].add(getNumberOfTerrains()[i], 0, 0);
			getGrids()[i].add(getNumberOfBuildings()[i], 0, 1);
			getGrids()[i].add(getNumberOfCities()[i], 0, 2);
			getGrids()[i].add(getTerrainPoints()[i], 1, 0);
			getGrids()[i].add(getBuildingPoints()[i], 1, 1);
			getGrids()[i].add(getCityPoints()[i], 1, 2);
			getGrids()[i].setAlignment(Pos.CENTER);
		}
	}
	
	public void initializeLeaders() {
		setLeaders(new ImageView[getResults().getPlayersNumber()]);
		setNames(new Label[getResults().getPlayersNumber()]);

		for(int i = 0; i<getResults().getPlayersNumber(); i++) {
			int leader = getResults().getLeaders()[i];
			getLeaders()[i] = new ImageView(getPortraits()[leader]);
			getNames()[i] = new Label();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(getLeadersFile()));
				String line = "";
				while (!line.startsWith(""+leader)) {
					line = reader.readLine();
				}
				String[] current = line.split("#");
				getNames()[i].setText(current[1]);
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getNames()[i].setId("leader_name");
		}
	}

	public Image[] initializeLeaderPortraits() {
		Image[] sprites = new Image[NUMBER_OF_LEADERS];
		sprites[0] = new Image(getClass().getResource("\\sprites\\CaptainIgloo.png").toString());
		sprites[1] = new Image(getClass().getResource("\\sprites\\Trump.png").toString());
		sprites[2] = new Image(getClass().getResource("\\sprites\\Hollande.png").toString());
		sprites[3] = new Image(getClass().getResource("\\sprites\\GordonRamsay.png").toString());
		sprites[4] = new Image(getClass().getResource("\\sprites\\Governator.png").toString());
		sprites[5] = new Image(getClass().getResource("\\sprites\\Poutine.png").toString());
		return sprites;
	}
	
	public void displayContent() {
		for(int i = 0; i<getResults().getPlayersNumber(); i++) {
			getLadder()[i].getChildren().add(getRanks()[i]);
			getLadder()[i].getChildren().add(getPlayers()[i]);
			getLadder()[i].getChildren().add(getTotalPoints()[i]);
			getLadder()[i].getChildren().add(getGrids()[i]);
			getLadder()[i].getChildren().add(getNames()[i]);
			getLadder()[i].getChildren().add(getLeaders()[i]);
			getChildren().add(getLadder()[i]);
		}
	}
	
	public BlockSize getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(BlockSize screenSize) {
		this.screenSize = screenSize;
	}

	public Label[] getPlayers() {
		return players;
	}

	public void setPlayers(Label[] players) {
		this.players = players;
	}

	public Image[] getPortraits() {
		return portraits;
	}

	public void setPortraits(Image[] portraits) {
		this.portraits = portraits;
	}

	public ImageView[] getLeaders() {
		return leaders;
	}

	public void setLeaders(ImageView[] leaders) {
		this.leaders = leaders;
	}

	public Label[] getNames() {
		return names;
	}

	public void setNames(Label[] names) {
		this.names = names;
	}

	public VBox[] getLadder() {
		return ladder;
	}

	public void setLadder(VBox[] ladder) {
		this.ladder = ladder;
	}

	public Label[] getRanks() {
		return ranks;
	}

	public void setRanks(Label[] ranks) {
		this.ranks = ranks;
	}

	public Label[] getNumberOfTerrains() {
		return numberOfTerrains;
	}

	public void setNumberOfTerrains(Label[] numberOfTerrains) {
		this.numberOfTerrains = numberOfTerrains;
	}

	public Label[] getNumberOfCities() {
		return numberOfCities;
	}

	public void setNumberOfCities(Label[] numberOfCities) {
		this.numberOfCities = numberOfCities;
	}


	public Label[] getNumberOfBuildings() {
		return numberOfBuildings;
	}

	public void setNumberOfBuildings(Label[] numberOfBuildings) {
		this.numberOfBuildings = numberOfBuildings;
	}

	public Label[] getCityPoints() {
		return cityPoints;
	}

	public void setCityPoints(Label[] cityPoints) {
		this.cityPoints = cityPoints;
	}

	public Label[] getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Label[] totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Label[] getBuildingPoints() {
		return buildingPoints;
	}

	public void setBuildingPoints(Label[] buildingPoints) {
		this.buildingPoints = buildingPoints;
	}

	public Label[] getTerrainPoints() {
		return terrainPoints;
	}

	public void setTerrainPoints(Label[] terrainPoints) {
		this.terrainPoints = terrainPoints;
	}

	public String getLeadersFile() {
		return leadersFile;
	}

	public void setLeadersFile(String leadersFile) {
		this.leadersFile = leadersFile;
	}

	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}

	public GridPane[] getGrids() {
		return grids;
	}

	public void setGrids(GridPane[] grids) {
		this.grids = grids;
	}
}
