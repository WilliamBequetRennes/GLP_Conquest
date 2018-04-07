package gui;

import java.io.IOException;

import game.Game;
import gui_data.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import save.Save;

public class GameMenu extends VBox{
	
	private final static double MENU_BOX = 0.80 ;
	private final static double BACK_BOX = 0.20 ;
	private final static String SAVEFILE = "save";
	private BlockSize blockSize;
	private Button saveButton;
	private Button loadButton;
	private Button menu;
	private Button getBack;
	private VBox menuBox;
	private VBox backBox;
	
	private Save save;
	
	public GameMenu(Game game, BlockSize blockSize, GameBlock gameBlock, MenusBlock menusBlock) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		initializeButtons();
		initializeGetBackClick(gameBlock, menusBlock);
		initializeMenuClick(menusBlock);
		initializeSaveClick(game);
		displayContent();
		setAlignment(Pos.CENTER);
	}
	public void initializeGetBackClick(GameBlock gameBlock, MenusBlock menusBlock) {
		getGetBack().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				gameBlock.getLeftMenu().getUsualLeftMenu().setVisible(true);
				gameBlock.getLeftMenu().getUsualLeftMenu().toFront();
				gameBlock.getLeftMenu().getGameMenu().setVisible(false);
				menusBlock.getPlayableBlock().getCentralBlock().getMenuBar().setMenuClicked(false);
			}
		});
	}
	
	public void initializeButtons() {
		setSaveButton(new Button());
		setLoadButton(new Button());
		setMenu(new Button());
		setGetBack(new Button());
		
		getSaveButton().setText("Save game");
		getLoadButton().setText("Load game");
		getMenu().setText("Back to menu");
		getGetBack().setText("Back to game");
		
		setMenuBox(new VBox());
		setBackBox(new VBox());
		getMenuBox().setPrefHeight(getBlockSize().getHeight()*MENU_BOX);
		getBackBox().setPrefHeight(getBlockSize().getHeight()*BACK_BOX);
		getMenuBox().setAlignment(Pos.TOP_CENTER);
		getBackBox().setAlignment(Pos.BOTTOM_CENTER);

		getSaveButton().setId("menu_bar_button");
		getLoadButton().setId("menu_bar_button");
		getMenu().setId("menu_bar_button");
		getGetBack().setId("menu_bar_button");
		
		getBackBox().setId("spacing");
		getMenuBox().setId("spacing");
	}

	public void initializeSaveClick(Game game) {
		setSave(new Save(SAVEFILE));
		getSaveButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				try {
					getSave().saveGame(game, game.getMap());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	public void initializeMenuClick(MenusBlock menusBlock) {
		getMenu().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				menusBlock.quitGame();
			}
		});
	}
	
	public void displayContent() {
		getMenuBox().getChildren().add(getSaveButton());
		getMenuBox().getChildren().add(getLoadButton());
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

	public Button getSaveButton() {
		return saveButton;
	}
	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}
	public Button getLoadButton() {
		return loadButton;
	}
	public void setLoadButton(Button loadButton) {
		this.loadButton = loadButton;
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
	public Save getSave() {
		return save;
	}
	public void setSave(Save save) {
		this.save = save;
	}
}
