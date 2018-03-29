package gui;

import java.io.IOException;

import exceptions.LeaderException;
import exceptions.UnitException;
import game.Game;
import gui_data.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import save.Save;

public class StartMenu extends VBox {

	private final static String SAVEFILE = "save.sav";
	
	private BlockSize screenSize;

	private ImageView logo;
	private Button newGame;
	private Button loadGame;
	
	public StartMenu(BlockSize screenSize, MenusBlock menusBlock) {
		super();
		setScreenSize(screenSize);
		setPrefSize(getScreenSize().getWidth(), getScreenSize().getHeight());
		setId("gradient");

		initializeLogo();
		initializeButtons();
		initializeNewGameClick(menusBlock);
		initializeLoadGameClick(menusBlock);
		displayContent();
		setAlignment(Pos.CENTER);
	}
	
	public void initializeLogo(){
		setLogo(new ImageView(new Image(getClass().getResource("\\sprites\\AMW_logo.png").toString())));
	}
	
	public void initializeButtons() {
		setNewGame(new Button());
		setLoadGame(new Button());
		
		getNewGame().setText("New Game");
		getLoadGame().setText("Load Game");

		getNewGame().setId("menu_button");
		getLoadGame().setId("menu_button");
	}
	
	public void initializeNewGameClick(MenusBlock menusBlock){
		getNewGame().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				menusBlock.initializeGameOptions();
			}
		});
	}
	
	public void initializeLoadGameClick(MenusBlock menusBlock) {
		getLoadGame().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				Save save = new Save(SAVEFILE);
				Game game = null;
				try {
					game = save.loadGame();
				} catch (IOException | LeaderException | UnitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				menusBlock.loadGame(game);
			}
		});
	}
	public void displayContent() {
		getChildren().add(getLogo());
		getChildren().add(getNewGame());
		getChildren().add(getLoadGame());
	}
	
	public BlockSize getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(BlockSize screenSize) {
		this.screenSize = screenSize;
	}

	public ImageView getLogo() {
		return logo;
	}

	public void setLogo(ImageView logo) {
		this.logo = logo;
	}

	public Button getNewGame() {
		return newGame;
	}

	public void setNewGame(Button newGame) {
		this.newGame = newGame;
	}

	public Button getLoadGame() {
		return loadGame;
	}

	public void setLoadGame(Button loadGame) {
		this.loadGame = loadGame;
	}
}
