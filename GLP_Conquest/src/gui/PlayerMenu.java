package gui;

import countries.Country;
import gui_datas.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import squares.Square;

public class PlayerMenu extends VBox{
	
	private BlockSize blockSize;
	
	private Label player;
	private Image[] leaderPortraits;
	private ImageView portrait;
	private Label money;
	private Label food;
	private Label oil;
	private Label electricity;
	private Label numberOfSquares;
	private Label numberOfCities;
	private Label numberOfMines;
	private Label numberOfFarms;
	private Label numberOfOilWells;
	private Label numberOfNuclearPlants;
	private Label numberOfUnits;
	private Button getBack;
	
	private int countryLeader;
	
	private GridPane dataGrid;
	private VBox resources;
	
	public PlayerMenu(BlockSize blockSize, CentralMenu centralMenu) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		setLeaderPortraits(initializeLeaderPortraits());
		initializePortrait(0);
		initializePortraitClick(centralMenu);
		initializeContent();
		initializeGetBackButton(centralMenu);

		displayContent();
		setAlignment(Pos.TOP_CENTER);
	}
	
	public void update(Country country) {
		float money = country.getGains().getMoney()-country.getSpents().getMoney();
		float food = country.getGains().getFood()-country.getSpents().getFood();
		float oil = country.getGains().getOil()-country.getSpents().getOil();
		float electricity = country.getGains().getElectricity()-country.getSpents().getElectricity();
		getPlayer().setText("Player "+country.getPlayer());
		getMoney().setText("Money : "+country.getResources().getMoney()+" (+"+money+")");
		getFood().setText("Food : "+country.getResources().getFood()+" (+"+food+")");
		getOil().setText("Oil : "+country.getResources().getOil()+" (+"+oil+")");
		getElectricity().setText("Electricity : "+country.getResources().getElectricity()+" (+"+electricity+")");
		
		int mines = 0;
		int farms = 0;
		int oilwells = 0;
		int nuclearplants = 0;
		int cities = 0;
		for(Square current : country.getBuildings().values()) {
			switch(current.getType()){
			case(5):mines++;
			break;
			case(6):farms++;
			break;
			case(7):oilwells++;
			break;
			case(8):nuclearplants++;
			break;
			case(9):cities++;
			break;
			}
		}
		getNumberOfMines().setText("Mines = "+mines);
		getNumberOfFarms().setText("Farms = "+farms);
		getNumberOfOilWells().setText("Oil wells = "+oilwells);
		getNumberOfNuclearPlants().setText("Nuclear plants = "+nuclearplants);
		getNumberOfCities().setText("Cities = "+cities);

		getNumberOfSquares().setText("Squares ="+country.getSquareNumber());
		
		getNumberOfUnits().setText("Number of units = "+country.getUnits().size());
		initializePortrait(country.getLeader().getNumber());
	}
	public void initializePortrait(int leader) {
		setPortrait(new ImageView(getLeaderPortraits()[leader]));
		setCountryLeader(leader);
	}
	public void initializePortraitClick(CentralMenu centralMenu) {
		getPortrait().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				centralMenu.getLeaderMenu().update(getCountryLeader());
				centralMenu.getLeaderMenu().setVisible(true);
				centralMenu.getLeaderMenu().toFront();
				centralMenu.getPlayerMenu().setVisible(false);
			}
		});
	}
	public void initializeContent() {
		setPlayer(new Label());
		setMoney(new Label());
		setFood(new Label());
		setOil(new Label());
		setElectricity(new Label());
		setNumberOfSquares(new Label());
		setNumberOfCities(new Label());
		setNumberOfMines(new Label());
		setNumberOfFarms(new Label());
		setNumberOfOilWells(new Label());
		setNumberOfNuclearPlants(new Label());
		setNumberOfUnits(new Label());
		setDataGrid(new GridPane());
		setResources(new VBox());
		
		getDataGrid().setAlignment(Pos.CENTER);
		getResources().setAlignment(Pos.CENTER);
	}
	public void initializeGetBackButton(CentralMenu centralMenu){
		setGetBack(new Button());
		getGetBack().setText("Back");
		getGetBack().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				centralMenu.getMapCanvas().setVisible(true);
				centralMenu.getMapCanvas().toFront();
				centralMenu.getPlayerMenu().setVisible(false);
			}
		});
	}
	public Image[] initializeLeaderPortraits() {
		Image[] sprites = new Image[7];
		sprites[0] = new Image(getClass().getResource("\\sprites\\CaptainIgloo.png").toString());
		sprites[1] = new Image(getClass().getResource("\\sprites\\Trump.png").toString());
		sprites[2] = new Image(getClass().getResource("\\sprites\\Hollande.png").toString());
		sprites[3] = new Image(getClass().getResource("\\sprites\\Governator.png").toString());
		sprites[4] = new Image(getClass().getResource("\\sprites\\Jesus.png").toString());
		sprites[5] = new Image(getClass().getResource("\\sprites\\Moses.png").toString());
		sprites[6] = new Image(getClass().getResource("\\sprites\\Poutine.png").toString());
		return sprites;
	}
	public void displayContent() {
		getResources().getChildren().add(getMoney());
		getResources().getChildren().add(getFood());
		getResources().getChildren().add(getOil());
		getResources().getChildren().add(getElectricity());
		
		getDataGrid().add(getResources(), 0, 0);
		getDataGrid().add(getNumberOfSquares(), 0, 1);
		getDataGrid().add(getNumberOfCities(), 0, 2);
		getDataGrid().add(getNumberOfMines(), 0, 3);
		getDataGrid().add(getPortrait(), 1, 0);
		getDataGrid().add(getNumberOfFarms(), 1, 1);
		getDataGrid().add(getNumberOfOilWells(), 1, 2);
		getDataGrid().add(getNumberOfNuclearPlants(), 1, 3);
		
		getChildren().add(getPlayer());
		getChildren().add(getDataGrid());
		getChildren().add(getNumberOfUnits());
		getChildren().add(getGetBack());
	}

	public Label getPlayer() {
		return player;
	}


	public void setPlayer(Label player) {
		this.player = player;
	}


	public Image[] getLeaderPortraits() {
		return leaderPortraits;
	}


	public void setLeaderPortraits(Image[] leaderPortraits) {
		this.leaderPortraits = leaderPortraits;
	}


	public ImageView getPortrait() {
		return portrait;
	}


	public void setPortrait(ImageView portrait) {
		this.portrait = portrait;
	}


	public Label getMoney() {
		return money;
	}


	public void setMoney(Label money) {
		this.money = money;
	}


	public Label getFood() {
		return food;
	}


	public void setFood(Label food) {
		this.food = food;
	}


	public Label getOil() {
		return oil;
	}


	public void setOil(Label oil) {
		this.oil = oil;
	}


	public Label getElectricity() {
		return electricity;
	}


	public void setElectricity(Label electricity) {
		this.electricity = electricity;
	}


	public Label getNumberOfSquares() {
		return numberOfSquares;
	}


	public void setNumberOfSquares(Label numberOfSquares) {
		this.numberOfSquares = numberOfSquares;
	}


	public Label getNumberOfCities() {
		return numberOfCities;
	}


	public void setNumberOfCities(Label numberOfCities) {
		this.numberOfCities = numberOfCities;
	}


	public Label getNumberOfMines() {
		return numberOfMines;
	}


	public void setNumberOfMines(Label numberOfMines) {
		this.numberOfMines = numberOfMines;
	}


	public Label getNumberOfFarms() {
		return numberOfFarms;
	}


	public void setNumberOfFarms(Label numberOfFarms) {
		this.numberOfFarms = numberOfFarms;
	}


	public Label getNumberOfOilWells() {
		return numberOfOilWells;
	}


	public void setNumberOfOilWells(Label numberOfOilWells) {
		this.numberOfOilWells = numberOfOilWells;
	}


	public Label getNumberOfNuclearPlants() {
		return numberOfNuclearPlants;
	}


	public void setNumberOfNuclearPlants(Label numberOfNuclearPlants) {
		this.numberOfNuclearPlants = numberOfNuclearPlants;
	}


	public Label getNumberOfUnits() {
		return numberOfUnits;
	}


	public void setNumberOfUnits(Label numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}


	public Button getGetBack() {
		return getBack;
	}


	public void setGetBack(Button getBack) {
		this.getBack = getBack;
	}

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}


	public GridPane getDataGrid() {
		return dataGrid;
	}


	public void setDataGrid(GridPane dataGrid) {
		this.dataGrid = dataGrid;
	}


	public VBox getResources() {
		return resources;
	}


	public void setResources(VBox resources) {
		this.resources = resources;
	}

	public int getCountryLeader() {
		return countryLeader;
	}

	public void setCountryLeader(int countryLeader) {
		this.countryLeader = countryLeader;
	}

}
