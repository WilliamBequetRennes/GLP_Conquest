package gui;

import game.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class UsualRightMenu extends VBox{

	private Label currentPlayer;
	private Image[] leaderPortraits;
	private ImageView portrait;
	private Label numberOfSquares;
	private Label money;
	private Label food;
	private Label oil;
	private Label electricity;
	private Button createUnit;
	
	public UsualRightMenu(Game game) {
		super();
		initializeCurrentPlayer(game);
		setLeaderPortraits(initializeLeaderPortraits());
		initializePortrait(game.getPlayers()[0].getLeader().getNumber());
		initializeNumberOfSquares(game);
		initializeResources(game);
		initializeCreateUnitButton(game);
		
		displayContent();
		setAlignment(Pos.TOP_CENTER);
	}
	
	public void initializeCurrentPlayer(Game game) {
		setCurrentPlayer(new Label());
		getCurrentPlayer().setText("Player : "+game.getCurrentPlayer());
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
	public void initializePortrait(int leader) {
		setPortrait(new ImageView(getLeaderPortraits()[leader]));
	}
	
	public void initializeNumberOfSquares(Game game) {
		setNumberOfSquares(new Label());
		getNumberOfSquares().setText("Number of squares : "+game.getPlayers()[game.getCurrentPlayer()-1].getSquareNumber());
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
	}
	
	public void initializeCreateUnitButton(Game game) {
		setCreateUnit(new Button());
		getCreateUnit().setText("Create Unit");
		getCreateUnit().setVisible(false);
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
	public Label getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Label currentPlayer) {
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
	
}
