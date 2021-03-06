package gui;

import game.Game;
import game.SquaresScanner;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class UsualRightMenu extends VBox{

	private final static int NUMBER_OF_LEADERS = 6;
	private Button currentPlayer;
	private Image[] leaderPortraits;
	private int leader;
	private ImageView portrait;
	private Label numberOfSquares;
	private Label money;
	private Label food;
	private Label oil;
	private Label electricity;
	private Button createUnit;
	private SquaresScanner squaresScanner;
	
	public UsualRightMenu(Game game, GameBlock gameBlock, RightMenu rightMenu) {
		super();
		initializeCurrentPlayer(game, gameBlock);
		setSquaresScanner(new SquaresScanner(game));
		setLeaderPortraits(initializeLeaderPortraits());
		initializePortrait(game.getPlayers()[0].getLeader().getNumber());
		initializePortraitClick(gameBlock.getCentralMenu(), game);
		initializeNumberOfSquares(game);
		initializeResources(game);
		initializeCreateUnitButton(game, rightMenu);
		
		displayContent();
		setAlignment(Pos.TOP_CENTER);
	}
	
	public void initializeCurrentPlayer(Game game, GameBlock gameBlock) {
		setCurrentPlayer(new Button());
		getCurrentPlayer().setText("Player "+game.getCurrentPlayer());
		getCurrentPlayer().setId("player_button"+(game.getCurrentPlayer()));
		getCurrentPlayer().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				gameBlock.getCentralMenu().getPlayerMenu().update(game.getPlayers()[game.getCurrentPlayer()-1]);
				gameBlock.getCentralMenu().getPlayerMenu().setVisible(true);
				gameBlock.getCentralMenu().getPlayerMenu().toFront();
				gameBlock.getCentralMenu().getMapCanvas().setVisible(false);
				gameBlock.getCentralMenu().getLeaderMenu().setVisible(false);
			}
		});
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
	public void initializePortrait(int leader) {
		setLeader(leader);
		setPortrait(new ImageView(getLeaderPortraits()[getLeader()]));
	}
	public void updatePortrait(int leader) {
		setLeader(leader);
		getPortrait().setImage(getLeaderPortraits()[getLeader()]);
	}
	public void initializePortraitClick(CentralMenu centralMenu, Game game) {
		getPortrait().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				centralMenu.getLeaderMenu().update(getLeader());
				centralMenu.getLeaderMenu().setVisible(true);
				centralMenu.getLeaderMenu().toFront();
				centralMenu.getPlayerMenu().setVisible(false);
				centralMenu.getMapCanvas().setVisible(false);
			}
		});
	}
	public void initializeNumberOfSquares(Game game) {
		setNumberOfSquares(new Label());
		getNumberOfSquares().setText("Number of squares : "+game.getPlayers()[game.getCurrentPlayer()-1].getSquareNumber());
		getNumberOfSquares().setWrapText(true);
	}
	
	public void initializeResources(Game game) {
		setMoney(new Label());
		setFood(new Label());
		setOil(new Label());
		setElectricity(new Label());
		
		getMoney().setText("Money : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getMoney());
		getFood().setText("Food : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getFood());
		getOil().setText("Oil : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getOil());
		getElectricity().setText("Electricity : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getElectricity());
		
		getMoney().setWrapText(true);
		getFood().setWrapText(true);
		getOil().setWrapText(true);
		getElectricity().setWrapText(true);
	}
	
	public void initializeCreateUnitButton(Game game, RightMenu rightMenu) {
		setCreateUnit(new Button());
		getCreateUnit().setText("Create Unit");
		getCreateUnit().setVisible(false);
		getCreateUnit().setId("menu_bar_button");
		
		getCreateUnit().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				rightMenu.getUnitCreationMenu().setVisible(true);
				rightMenu.getUnitCreationMenu().toFront();
				rightMenu.getUsualRightMenu().setVisible(false);
				if(getSquaresScanner().scan(0)) {
					rightMenu.getUnitCreationMenu().getShip().setVisible(true);
				}
				else {
					rightMenu.getUnitCreationMenu().getShip().setVisible(false);
				}
			}
		});
		
	}
	
	public void update(Game game) {
		getCurrentPlayer().setText("Player "+game.getCurrentPlayer());
		updatePortrait(game.getPlayers()[game.getCurrentPlayer()-1].getLeader().getNumber());
		getNumberOfSquares().setText("Number of squares : "+game.getPlayers()[game.getCurrentPlayer()-1].getSquareNumber());
		getMoney().setText("Money : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getMoney());
		getFood().setText("Food : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getFood());
		getOil().setText("Oil : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getOil());
		getElectricity().setText("Electricity : "+game.getPlayers()[game.getCurrentPlayer()-1].getResources().getElectricity());
		getCurrentPlayer().setId("player_button"+game.getCurrentPlayer());
	}
	public void displayContent() {
		getChildren().add(getCurrentPlayer());
		getChildren().add(getPortrait());
		getChildren().add(getNumberOfSquares());
		getChildren().add(getMoney());
		getChildren().add(getFood());
		getChildren().add(getOil());
		getChildren().add(getElectricity());
		getChildren().add(getCreateUnit());
	}
	public Button getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Button currentPlayer) {
		this.currentPlayer = currentPlayer;
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

	public Label getNumberOfSquares() {
		return numberOfSquares;
	}

	public void setNumberOfSquares(Label numberOfSquares) {
		this.numberOfSquares = numberOfSquares;
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

	public Button getCreateUnit() {
		return createUnit;
	}

	public void setCreateUnit(Button createUnit) {
		this.createUnit = createUnit;
	}

	public int getLeader() {
		return leader;
	}

	public void setLeader(int leader) {
		this.leader = leader;
	}

	public SquaresScanner getSquaresScanner() {
		return squaresScanner;
	}

	public void setSquaresScanner(SquaresScanner squaresScanner) {
		this.squaresScanner = squaresScanner;
	}
	
}
