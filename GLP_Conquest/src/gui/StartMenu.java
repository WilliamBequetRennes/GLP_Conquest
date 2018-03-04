package gui;

import gui_datas.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class StartMenu extends VBox {
	
	private BlockSize screenSize;

	private ImageView logo;
	private Button newGame;
	private Button loadGame;
	
	public StartMenu(BlockSize screenSize, MenusBlock menusBlock) {
		super();
		setScreenSize(screenSize);
		setPrefSize(getScreenSize().getWidth(), getScreenSize().getHeight());

		initializeLogo();
		initializeButtons();
		initializeNewGameClick(menusBlock);
		initializeLoadGameClick();
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
		displayContent();
	}
	
	public void initializeNewGameClick(MenusBlock menusBlock){
		getNewGame().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				menusBlock.initializeGameOptions();
			}
		});
	}
	
	public void initializeLoadGameClick() {
		
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
