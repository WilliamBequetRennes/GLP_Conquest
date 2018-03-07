package gui;

import gui_datas.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameOptions extends VBox{
	
	private final static int MAX_MAP_NUMBER = 999;
	private final static double OPTIONS_HEIGHT = 0.9;
	private final static double MENU_MOVES_HEIGHT = 0.1;
	private final static double SIDE_MENU_MOVES_WIDTH = 0.5;
	
	private BlockSize screenSize;
	private ImageView logo;
	private Label gameOptions;
	private Label playersNumber;
	private Label turnsNumber;
	private Label mapSize;
	private Label mapCode;
	
	private ToggleGroup players;
	private ToggleGroup turns;
	private ToggleGroup map;
	private HBox playersBox;
	private HBox turnsBox;
	private HBox mapBox;
	private HBox codeBox;
	
	private VBox optionsBox;
	private HBox menuMoves;
	private HBox leftMenuMoves;
	private HBox rightMenuMoves;
	
	private RadioButton[] playersButtons;
	private RadioButton[] turnsButtons;
	private RadioButton[] mapSizeButtons;
	
	private TextField code;
	
	private Button getBack;
	private Button next;
	
	public GameOptions(BlockSize screenSize, MenusBlock menusBlock) {
		super();
		setScreenSize(screenSize);
		setPrefSize(getScreenSize().getWidth(), getScreenSize().getHeight());
		
		initializeLogo();
		initializeLabels();
		initializeRadioButtons();
		initializeToggleGroups();
		initializeTextField();
		initializeButtons();
		initializeBoxes();
		
		initializeNextClick(menusBlock);
		initializeGetBackClick(menusBlock);
		
		displayContent();
		setAlignment(Pos.TOP_CENTER);
	}
	
	public void initializeLogo(){
		setLogo(new ImageView(new Image(getClass().getResource("\\sprites\\AMW_logo.png").toString())));
	}
	
	public void initializeTextField() {
		setCode(new TextField());
		getCode().setPromptText("from 0 to "+MAX_MAP_NUMBER);
		getCode().setAlignment(Pos.CENTER);
	}
	
	public void initializeLabels() {
		setGameOptions(new Label());
		setPlayersNumber(new Label());
		setTurnsNumber(new Label());
		setMapSize(new Label());
		setMapCode(new Label());
		
		getGameOptions().setText("Games Options");
		getPlayersNumber().setText("Players Number :");
		getTurnsNumber().setText("Turns Number :");
		getMapSize().setText("Map Size :");
		getMapCode().setText("Use a map code :");
	}
	public void initializeToggleGroups() {
		setPlayers(new ToggleGroup());
		setTurns(new ToggleGroup());
		setMap(new ToggleGroup());
		
		for(int i = 0; i<3; i++) {
			getPlayersButtons()[i].setToggleGroup(getPlayers());
			getTurnsButtons()[i].setToggleGroup(getTurns());
			getMapSizeButtons()[i].setToggleGroup(getMap());
		}

		getPlayersButtons()[0].setSelected(true);
		getTurnsButtons()[0].setSelected(true);
		getMapSizeButtons()[0].setSelected(true);
	}
	
	public void initializeButtons() {
		setGetBack(new Button());
		setNext(new Button());
		
		getGetBack().setText("Back");
		getNext().setText("Next");
	}
	
	public void initializeGetBackClick(MenusBlock menusBlock) {
		getGetBack().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				menusBlock.comeBackToStartMenu();
			}
		});
	}
	public void initializeNextClick(MenusBlock menusBlock) {
		getNext().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				int numberOfPlayers=0;
				int numberOfTurns=0;
				int sizeOfMap=0;
				
				for(int i = 0; i<3; i++) {
					if(getPlayersButtons()[i].isSelected()) {
						numberOfPlayers = i+2;
					}
					if(getTurnsButtons()[i].isSelected()) {
						numberOfTurns = (i+1)*30;
					}
					if(getMapSizeButtons()[i].isSelected()) {
						sizeOfMap = i;
					}
				}
				menusBlock.setPlayersNumber(numberOfPlayers);
				menusBlock.setTurnsNumber(numberOfTurns);
				menusBlock.setMapSize(sizeOfMap);
				if(isInteger(getCode().getText())){
					menusBlock.setMapNumber(Integer.valueOf(getCode().getText()).intValue());
				}
				else {
					menusBlock.setMapNumber(0);
				}
				menusBlock.initializeLeaderSelection();
			}
		});
		
	}
	
	public boolean isInteger(String string) {
		try {
			Integer.valueOf(string).intValue();
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
		
	}

	public void initializeRadioButtons() {
		setPlayersButtons(new RadioButton[3]);
		setTurnsButtons(new RadioButton[3]);
		setMapSizeButtons(new RadioButton[3]);
		for(int i = 0; i<3; i++) {
			getPlayersButtons()[i] = new RadioButton();
			getTurnsButtons()[i] = new RadioButton();
			getMapSizeButtons()[i] = new RadioButton();
		}
		
		getPlayersButtons()[0].setText("2");
		getPlayersButtons()[1].setText("3");
		getPlayersButtons()[2].setText("4");
		
		getTurnsButtons()[0].setText("30");
		getTurnsButtons()[1].setText("60");
		getTurnsButtons()[2].setText("90");
		
		getMapSizeButtons()[0].setText("Little");
		getMapSizeButtons()[1].setText("Medium");
		getMapSizeButtons()[2].setText("Wide");
	}
	
	public void initializeBoxes() {
		setPlayersBox(new HBox());
		setTurnsBox(new HBox());
		setMapBox(new HBox());
		setCodeBox(new HBox());
		
		setOptionsBox(new VBox());
		setMenuMoves(new HBox());
		setLeftMenuMoves(new HBox());
		setRightMenuMoves(new HBox());
		
		getOptionsBox().setPrefSize(getScreenSize().getWidth(), getScreenSize().getHeight()*OPTIONS_HEIGHT);
		getMenuMoves().setPrefSize(getScreenSize().getWidth(), getScreenSize().getHeight()*MENU_MOVES_HEIGHT);
		getLeftMenuMoves().setPrefWidth(getScreenSize().getWidth()*SIDE_MENU_MOVES_WIDTH);
		getRightMenuMoves().setPrefWidth(getScreenSize().getWidth()*SIDE_MENU_MOVES_WIDTH);
		
		getPlayersBox().setAlignment(Pos.CENTER);
		getTurnsBox().setAlignment(Pos.CENTER);
		getMapBox().setAlignment(Pos.CENTER);
		getCodeBox().setAlignment(Pos.CENTER);
		getOptionsBox().setAlignment(Pos.TOP_CENTER);
		getMenuMoves().setAlignment(Pos.BOTTOM_CENTER);
		getLeftMenuMoves().setAlignment(Pos.BOTTOM_LEFT);
		getRightMenuMoves().setAlignment(Pos.BOTTOM_RIGHT);
	}
	
	public void displayContent() {
		getPlayersBox().getChildren().add(getPlayersNumber());
		getTurnsBox().getChildren().add(getTurnsNumber());
		getMapBox().getChildren().add(getMapSize());
		for(int i = 0; i<3; i++) {
			getPlayersBox().getChildren().add(getPlayersButtons()[i]);
			getTurnsBox().getChildren().add(getTurnsButtons()[i]);
			getMapBox().getChildren().add(getMapSizeButtons()[i]);
		}
		getCodeBox().getChildren().add(getMapCode());
		getCodeBox().getChildren().add(getCode());
		
		getLeftMenuMoves().getChildren().add(getGetBack());
		getRightMenuMoves().getChildren().add(getNext());
		
		getOptionsBox().getChildren().add(getLogo());
		getOptionsBox().getChildren().add(getGameOptions());
		getOptionsBox().getChildren().add(getPlayersBox());
		getOptionsBox().getChildren().add(getTurnsBox());
		getOptionsBox().getChildren().add(getMapBox());
		getOptionsBox().getChildren().add(getCodeBox());
		
		getMenuMoves().getChildren().add(getLeftMenuMoves());
		getMenuMoves().getChildren().add(getRightMenuMoves());
		
		getChildren().add(getOptionsBox());
		getChildren().add(getMenuMoves());
	}
	
	public HBox getPlayersBox() {
		return playersBox;
	}

	public void setPlayersBox(HBox playersBox) {
		this.playersBox = playersBox;
	}

	public HBox getTurnsBox() {
		return turnsBox;
	}

	public void setTurnsBox(HBox turnsBox) {
		this.turnsBox = turnsBox;
	}

	public HBox getMapBox() {
		return mapBox;
	}

	public void setMapBox(HBox mapBox) {
		this.mapBox = mapBox;
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

	public Label getGameOptions() {
		return gameOptions;
	}

	public void setGameOptions(Label gameOptions) {
		this.gameOptions = gameOptions;
	}

	public Label getPlayersNumber() {
		return playersNumber;
	}

	public void setPlayersNumber(Label playersNumber) {
		this.playersNumber = playersNumber;
	}

	public Label getTurnsNumber() {
		return turnsNumber;
	}

	public void setTurnsNumber(Label turnsNumber) {
		this.turnsNumber = turnsNumber;
	}

	public Label getMapSize() {
		return mapSize;
	}

	public void setMapSize(Label mapSize) {
		this.mapSize = mapSize;
	}

	public Label getMapCode() {
		return mapCode;
	}

	public void setMapCode(Label mapCode) {
		this.mapCode = mapCode;
	}

	public ToggleGroup getPlayers() {
		return players;
	}

	public void setPlayers(ToggleGroup players) {
		this.players = players;
	}

	public ToggleGroup getTurns() {
		return turns;
	}

	public void setTurns(ToggleGroup turns) {
		this.turns = turns;
	}

	public ToggleGroup getMap() {
		return map;
	}

	public void setMap(ToggleGroup map) {
		this.map = map;
	}

	public RadioButton[] getPlayersButtons() {
		return playersButtons;
	}

	public void setPlayersButtons(RadioButton[] playersButtons) {
		this.playersButtons = playersButtons;
	}

	public RadioButton[] getTurnsButtons() {
		return turnsButtons;
	}

	public void setTurnsButtons(RadioButton[] turnsButtons) {
		this.turnsButtons = turnsButtons;
	}

	public RadioButton[] getMapSizeButtons() {
		return mapSizeButtons;
	}

	public void setMapSizeButtons(RadioButton[] mapSizeButtons) {
		this.mapSizeButtons = mapSizeButtons;
	}

	public TextField getCode() {
		return code;
	}

	public void setCode(TextField code) {
		this.code = code;
	}

	public Button getGetBack() {
		return getBack;
	}

	public void setGetBack(Button getBack) {
		this.getBack = getBack;
	}

	public Button getNext() {
		return next;
	}

	public void setNext(Button next) {
		this.next = next;
	}

	public HBox getCodeBox() {
		return codeBox;
	}

	public void setCodeBox(HBox codeBox) {
		this.codeBox = codeBox;
	}

	public VBox getOptionsBox() {
		return optionsBox;
	}

	public void setOptionsBox(VBox optionsBox) {
		this.optionsBox = optionsBox;
	}

	public HBox getMenuMoves() {
		return menuMoves;
	}

	public void setMenuMoves(HBox menuMoves) {
		this.menuMoves = menuMoves;
	}

	public HBox getLeftMenuMoves() {
		return leftMenuMoves;
	}

	public void setLeftMenuMoves(HBox leftMenuMoves) {
		this.leftMenuMoves = leftMenuMoves;
	}

	public HBox getRightMenuMoves() {
		return rightMenuMoves;
	}

	public void setRightMenuMoves(HBox rightMenuMoves) {
		this.rightMenuMoves = rightMenuMoves;
	}
}
