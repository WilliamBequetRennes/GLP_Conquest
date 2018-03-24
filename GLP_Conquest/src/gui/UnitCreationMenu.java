package gui;

import exceptions.InvalidUnitNumberException;
import game.Game;
import gui_data.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class UnitCreationMenu extends VBox{

	private final static double CREATION_BOX = 0.80 ;
	private final static double BACK_BOX = 0.20 ;
	private final static int[] INFANTRY = {0,1};
	private final static int[] ARMORED = {4,5};
	private final static int[] ROBOT = {2,3};
	private final static int[] SHIP = {6,7};
	private BlockSize blockSize;
	
	private Label unitsCreation;
	private Button infantry;
	private Button armored;
	private Button robot;
	private Button ship;
	private Button getBack;
	
	private VBox creationBox;
	private VBox backBox;
	
	public UnitCreationMenu(Game game, BlockSize blockSize, RightMenu rightMenu) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		initializeContent();
		initializeBoxes();
		
		initializeGetBackClick(rightMenu);
		initializeCreationClick(rightMenu);
		
		displayContent();
		setAlignment(Pos.TOP_CENTER);
	}
	
	public void initializeContent() {
		setUnitsCreation(new Label());
		getUnitsCreation().setText("Unit Creation");
		getUnitsCreation().setId("creation_menu");
		
		setInfantry(new Button());
		setArmored(new Button());
		setRobot(new Button());
		setShip(new Button());
		setGetBack(new Button());

		getInfantry().setText("Infantry");
		getArmored().setText("Armored");
		getRobot().setText("Robot");
		getShip().setText("Ship");
		getGetBack().setText("Back");
		
		getInfantry().setId("menu_bar_button");
		getArmored().setId("menu_bar_button");
		getRobot().setId("menu_bar_button");
		getShip().setId("menu_bar_button");
		getGetBack().setId("menu_bar_button");
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
		getCreationBox().getChildren().add(getUnitsCreation());
		getCreationBox().getChildren().add(getInfantry());
		getCreationBox().getChildren().add(getArmored());
		getCreationBox().getChildren().add(getRobot());
		getCreationBox().getChildren().add(getShip());
		
		getBackBox().getChildren().add(getGetBack());
		
		getChildren().add(getCreationBox());
		getChildren().add(getBackBox());
	}
	
	public void initializeCreationClick(RightMenu rightMenu) {
		getInfantry().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				try {
					rightMenu.getUnitCreator().update(INFANTRY, "Infantry");
				} catch (InvalidUnitNumberException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rightMenu.getUnitCreator().setVisible(true);
				rightMenu.getUnitCreator().toFront();
				rightMenu.getUnitCreationMenu().setVisible(false);
			}
		});
		getArmored().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				try {
					rightMenu.getUnitCreator().update(ARMORED, "Armored");
				} catch (InvalidUnitNumberException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rightMenu.getUnitCreator().setVisible(true);
				rightMenu.getUnitCreator().toFront();
				rightMenu.getUnitCreationMenu().setVisible(false);
			}
		});
		getRobot().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				try {
					rightMenu.getUnitCreator().update(ROBOT, "Robot");
				} catch (InvalidUnitNumberException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rightMenu.getUnitCreator().setVisible(true);
				rightMenu.getUnitCreator().toFront();
				rightMenu.getUnitCreationMenu().setVisible(false);
			}
		});
		getShip().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				try {
					rightMenu.getUnitCreator().update(SHIP, "Ship");
				} catch (InvalidUnitNumberException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rightMenu.getUnitCreator().setVisible(true);
				rightMenu.getUnitCreator().toFront();
				rightMenu.getUnitCreationMenu().setVisible(false);
			}
		});
	}
	
	public void initializeGetBackClick(RightMenu rightMenu) {
		getGetBack().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				rightMenu.getUsualRightMenu().setVisible(true);
				rightMenu.getUsualRightMenu().toFront();
				rightMenu.getUnitCreationMenu().setVisible(false);
			}
		});
	}


	public Label getUnitsCreation() {
		return unitsCreation;
	}


	public void setUnitsCreation(Label unitsCreation) {
		this.unitsCreation = unitsCreation;
	}


	public Button getInfantry() {
		return infantry;
	}


	public void setInfantry(Button infantry) {
		this.infantry = infantry;
	}


	public Button getArmored() {
		return armored;
	}


	public void setArmored(Button armored) {
		this.armored = armored;
	}


	public Button getRobot() {
		return robot;
	}


	public void setRobot(Button robot) {
		this.robot = robot;
	}


	public Button getShip() {
		return ship;
	}


	public void setShip(Button ship) {
		this.ship = ship;
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
}
