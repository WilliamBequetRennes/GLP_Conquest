package gui;

import game.Game;
import gui_datas.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class UnitCreationMenu extends VBox{

	private final static double CREATION_BOX = 0.80 ;
	private final static double BACK_BOX = 0.20 ;
	private BlockSize blockSize;
	
	private Label unitsCreation;
	private Button infants;
	private Button armored;
	private Button robots;
	private Button ships;
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
		
		displayContent();
		setAlignment(Pos.TOP_CENTER);
	}
	
	public void initializeContent() {
		setUnitsCreation(new Label());
		getUnitsCreation().setText("Unit Creation");
		
		setInfants(new Button());
		setArmored(new Button());
		setRobots(new Button());
		setShips(new Button());
		setGetBack(new Button());

		getInfants().setText("Infants");
		getArmored().setText("Armored");
		getRobots().setText("Robots");
		getShips().setText("Ships");
		getGetBack().setText("Back");
		
		getInfants().setId("menu_bar_button");
		getArmored().setId("menu_bar_button");
		getRobots().setId("menu_bar_button");
		getShips().setId("menu_bar_button");
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
		getCreationBox().getChildren().add(getInfants());
		getCreationBox().getChildren().add(getArmored());
		getCreationBox().getChildren().add(getRobots());
		getCreationBox().getChildren().add(getShips());
		
		getBackBox().getChildren().add(getGetBack());
		
		getChildren().add(getCreationBox());
		getChildren().add(getBackBox());
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


	public Button getInfants() {
		return infants;
	}


	public void setInfants(Button infants) {
		this.infants = infants;
	}


	public Button getArmored() {
		return armored;
	}


	public void setArmored(Button armored) {
		this.armored = armored;
	}


	public Button getRobots() {
		return robots;
	}


	public void setRobots(Button robots) {
		this.robots = robots;
	}


	public Button getShips() {
		return ships;
	}


	public void setShips(Button ships) {
		this.ships = ships;
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
