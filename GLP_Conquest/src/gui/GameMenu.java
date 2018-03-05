package gui;

import game.Game;
import gui_datas.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class GameMenu extends VBox{
	
	private final static double MENU_BOX = 0.75 ;
	private final static double BACK_BOX = 0.25 ;
	private BlockSize blockSize;
	private Button save;
	private Button load;
	private Button menu;
	private Button getBack;
	private VBox menuBox;
	private VBox backBox;
	
	public GameMenu(Game game, BlockSize blockSize, GameBlock gameBlock, MenusBlock menusBlock) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		initializeButtons();
		initializeGetBackClick(gameBlock);
		initializeMenuClick(menusBlock);
		displayContent();
		setAlignment(Pos.CENTER);
	}
	public void initializeGetBackClick(GameBlock gameBlock) {
		getGetBack().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				gameBlock.getLeftMenu().getUsualLeftMenu().setVisible(true);
				gameBlock.getLeftMenu().getUsualLeftMenu().toFront();
				gameBlock.getLeftMenu().getGameMenu().setVisible(false);
			}
		});
	}
	
	public void initializeButtons() {
		setSave(new Button());
		setLoad(new Button());
		setMenu(new Button());
		setGetBack(new Button());
		
		getSave().setText("Save game");
		getLoad().setText("Load game");
		getMenu().setText("Back to main menu");
		getGetBack().setText("Back to game");
		
		setMenuBox(new VBox());
		setBackBox(new VBox());
		getMenuBox().setPrefHeight(getBlockSize().getHeight()*MENU_BOX);
		getBackBox().setPrefHeight(getBlockSize().getHeight()*BACK_BOX);
		getMenuBox().setAlignment(Pos.TOP_CENTER);
		getBackBox().setAlignment(Pos.BOTTOM_CENTER);
	}
	
	public void initializeMenuClick(MenusBlock menusBlock) {
		getMenu().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				menusBlock.quitGame();
			}
		});
	}
	
	public void displayContent() {
		getMenuBox().getChildren().add(getSave());
		getMenuBox().getChildren().add(getLoad());
		getMenuBox().getChildren().add(getMenu());
		getBackBox().getChildren().add(getGetBack());
		
		getChildren().add(getMenuBox());
		getChildren().add(getBackBox());
	}

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}

	public VBox getMenuBox() {
		return menuBox;
	}

	public void setMenuBox(VBox menuBox) {
		this.menuBox = menuBox;
	}

	public VBox getBackBox() {
		return backBox;
	}

	public void setBackBox(VBox backBox) {
		this.backBox = backBox;
	}

	public Button getSave() {
		return save;
	}

	public void setSave(Button save) {
		this.save = save;
	}

	public Button getLoad() {
		return load;
	}

	public void setLoad(Button load) {
		this.load = load;
	}

	public Button getMenu() {
		return menu;
	}

	public void setMenu(Button menu) {
		this.menu = menu;
	}

	public Button getGetBack() {
		return getBack;
	}

	public void setGetBack(Button getBack) {
		this.getBack = getBack;
	}
}
