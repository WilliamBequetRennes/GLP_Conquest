package gui;

import exceptions.InvalidUnitNumberException;
import game.Game;
import game.UnitPurchase;
import gui_datas.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import units.Assault;
import units.Battleship;
import units.Bfgninethousand;
import units.Destroyer;
import units.Obfourtytwo;
import units.Sniper;
import units.Tank;
import units.Turret;
import units.Unit;

public class UnitCreator extends VBox{

	private final static double CREATION_BOX = 0.80 ;
	private final static double BACK_BOX = 0.20 ;
	private final static int UNITS_NUMBER = 2;
	private BlockSize blockSize;

	private int[] types;
	private Label unitType;
	private int unitsNumber;
	private Button[] create;
	private Label[] name;
	private Label[] atk;
	private Label[] def;
	private Label[] mov;
	private Label[] range;
	private Label[] cost;
	private Label[] upkeep;
	private GridPane[] grid;
	private Button getBack;
	private UnitPurchase unitPurchase;
	
	private VBox creationBox;
	private VBox backBox;
	
	public UnitCreator(Game game, BlockSize blockSize, GameBlock gameBlock) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		initializeContent(UNITS_NUMBER);
		initializeBoxes();
		initializeGetBackClick(gameBlock);
		initializeCreationClicks(game, gameBlock);
		
		displayContent();
		setAlignment(Pos.TOP_CENTER);
	}
	
	public void initializeContent(int unitsNumber) {
		setUnitType(new Label());
		getUnitType().setId("creation_menu");
		
		setGetBack(new Button());
		getGetBack().setText("Back");
		getGetBack().setId("menu_bar_button");
		
		setUnitsNumber(unitsNumber);
		setCreate(new Button[getUnitsNumber()]);
		setName(new Label[getUnitsNumber()]);
		setAtk(new Label[getUnitsNumber()]);
		setDef(new Label[getUnitsNumber()]);
		setMov(new Label[getUnitsNumber()]);
		setRange(new Label[getUnitsNumber()]);
		setCost(new Label[getUnitsNumber()]);
		setUpkeep(new Label[getUnitsNumber()]);
		setGrid(new GridPane[getUnitsNumber()]);
		
		for(int i = 0; i<getUnitsNumber(); i++) {
			getCreate()[i] = new Button();
			getName()[i] = new Label();
			getAtk()[i] = new Label();
			getDef()[i] = new Label();
			getMov()[i] = new Label();
			getRange()[i] = new Label();
			getCost()[i] = new Label();
			getUpkeep()[i] = new Label();
			getGrid()[i] = new GridPane();
			
			getName()[i].setId("type");
			getCreate()[i].setText("Create");
			getCreate()[i].setId("menu_bar_button");
			
			getGrid()[i].add(getAtk()[i], 0, 0);
			getGrid()[i].add(getDef()[i], 1, 0);
			getGrid()[i].add(getCost()[i], 0, 1);
			getGrid()[i].add(getMov()[i], 1, 1);
			getGrid()[i].add(getUpkeep()[i], 0, 2);
			getGrid()[i].add(getRange()[i], 1, 2);
			getGrid()[i].setAlignment(Pos.CENTER);
		}
	}
	
	public void initializeBoxes() {
		setCreationBox(new VBox());
		setBackBox(new VBox());
		
		getCreationBox().setPrefHeight(getBlockSize().getHeight()*CREATION_BOX);
		getBackBox().setPrefHeight(getBlockSize().getHeight()*BACK_BOX);
		getCreationBox().setAlignment(Pos.TOP_CENTER);
		getBackBox().setAlignment(Pos.BOTTOM_CENTER);

		getCreationBox().setId("spacing");
		getBackBox().setId("spacing");
	}
	public void displayContent() {
		for(int i = 0; i<getUnitsNumber(); i++) {
			getCreationBox().getChildren().add(getName()[i]);
			getCreationBox().getChildren().add(getGrid()[i]);
			getCreationBox().getChildren().add(getCreate()[i]);
		}
		getBackBox().getChildren().add(getGetBack());

		getChildren().add(getUnitType());
		getChildren().add(getCreationBox());
		getChildren().add(getBackBox());
	}
	public void initializeGetBackClick(GameBlock gameBlock) {
		getGetBack().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				gameBlock.getRightMenu().getUnitCreationMenu().setVisible(true);
				gameBlock.getRightMenu().getUnitCreationMenu().toFront();
				gameBlock.getRightMenu().getUnitCreator().setVisible(false);
			}
		});
	}
	public void update(int[] types, String unitType) throws InvalidUnitNumberException{
		setTypes(types);
		getUnitType().setText(unitType);
		Unit[] units = new Unit[getUnitsNumber()];
		for(int i = 0; i<units.length; i++) {
			switch(getTypes()[i]) {
			case(0):units[i] = new Assault(null, 0, 0);
			break;
			case(1):units[i] = new Sniper(null, 0, 0);
			break;
			case(2):units[i] = new Obfourtytwo(null, 0, 0);
			break;
			case(3):units[i] = new Bfgninethousand(null, 0, 0);
			break;
			case(4):units[i] = new Tank(null, 0, 0);
			break;
			case(5):units[i] = new Turret(null, 0, 0);
			break;
			case(6):units[i] = new Destroyer(null, 0, 0);
			break;
			case(7):units[i] = new Battleship(null, 0, 0);
			break;
			default:throw new InvalidUnitNumberException(types[i]);
			}
			getName()[i].setText(units[i].getName());
			getAtk()[i].setText("ATK : "+units[i].getAttack());
			getDef()[i].setText("DEF : "+units[i].getDefense());
			getMov()[i].setText("MOV : "+units[i].getMovement());
			getRange()[i].setText("Range : "+units[i].getRange());
			getCost()[i].setText("Cost : "+units[i].getCost().getMoney()+" money");
			if(units[i].getType()==0 || units[i].getType()==1) {
				getUpkeep()[i].setText("Upkeep : "+units[i].getCost().getFood()+" food");
			}
			if(units[i].getType()==2 || units[i].getType()==3) {
				getUpkeep()[i].setText("Upkeep : "+units[i].getCost().getElectricity()+" elec");
			}
			if(units[i].getType()==4 || units[i].getType()==5) {
				getUpkeep()[i].setText("Upkeep : "+units[i].getCost().getOil()+" oil");
			}
			if(units[i].getType()==6 || units[i].getType()==7) {
				getUpkeep()[i].setText("Upkeep : "+units[i].getCost().getOil()+" oil");
			}
		}
	}
	public void initializeCreationClicks(Game game, GameBlock gameBlock) {
		setTypes(new int[getUnitsNumber()]);
		setUnitPurchase(new UnitPurchase());
		for(int i = 0; i<getCreate().length; i++) {
			int type = i;
			getCreate()[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					try {
						boolean purchase = getUnitPurchase().purchase(game, getTypes()[type]);
						if(purchase) {
							int playerNumber = gameBlock.getCentralMenu().getPlayerMenu().getPlayerNumber();
							if(playerNumber == 0) {
								playerNumber++;
							}
							gameBlock.getCentralMenu().getPlayerMenu().update(game.getPlayers()[playerNumber-1]);
							gameBlock.getRightMenu().getUsualRightMenu().setVisible(true);
							gameBlock.getRightMenu().getUsualRightMenu().toFront();
							gameBlock.getRightMenu().getUsualRightMenu().getCreateUnit().setVisible(false);
							gameBlock.getRightMenu().getUnitCreator().setVisible(false);
						}
					} catch (InvalidUnitNumberException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

	public int[] getTypes() {
		return types;
	}

	public void setTypes(int[] types) {
		this.types = types;
	}

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}

	public VBox getCreationBox() {
		return creationBox;
	}

	public void setCreationBox(VBox creationBox) {
		this.creationBox = creationBox;
	}

	public VBox getBackBox() {
		return backBox;
	}

	public void setBackBox(VBox backBox) {
		this.backBox = backBox;
	}

	public int getUnitsNumber() {
		return unitsNumber;
	}

	public void setUnitsNumber(int unitsNumber) {
		this.unitsNumber = unitsNumber;
	}

	public Button[] getCreate() {
		return create;
	}

	public void setCreate(Button[] create) {
		this.create = create;
	}

	public Label[] getName() {
		return name;
	}

	public void setName(Label[] name) {
		this.name = name;
	}

	public Label[] getAtk() {
		return atk;
	}

	public void setAtk(Label[] atk) {
		this.atk = atk;
	}

	public Label[] getDef() {
		return def;
	}

	public void setDef(Label[] def) {
		this.def = def;
	}

	public Label[] getMov() {
		return mov;
	}

	public void setMov(Label[] mov) {
		this.mov = mov;
	}

	public Label[] getRange() {
		return range;
	}

	public void setRange(Label[] range) {
		this.range = range;
	}

	public Label[] getCost() {
		return cost;
	}

	public void setCost(Label[] cost) {
		this.cost = cost;
	}

	public Label[] getUpkeep() {
		return upkeep;
	}

	public void setUpkeep(Label[] upkeep) {
		this.upkeep = upkeep;
	}

	public GridPane[] getGrid() {
		return grid;
	}

	public void setGrid(GridPane[] grid) {
		this.grid = grid;
	}

	public Button getGetBack() {
		return getBack;
	}

	public void setGetBack(Button getBack) {
		this.getBack = getBack;
	}

	public Label getUnitType() {
		return unitType;
	}

	public void setUnitType(Label unitType) {
		this.unitType = unitType;
	}

	public UnitPurchase getUnitPurchase() {
		return unitPurchase;
	}

	public void setUnitPurchase(UnitPurchase unitPurchase) {
		this.unitPurchase = unitPurchase;
	}
	
}
