package gui;

import game.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class UsualRightMenu extends VBox{

	public Label currentPlayer;
	private Image[] leaderPortraits;
	private ImageView portrait;
	
	public UsualRightMenu(Game game) {
		super();
		setCurrentPlayer(new Label());
		getCurrentPlayer().setText("Player : "+game.getCurrentPlayer());
		setLeaderPortraits(initializeLeaderPortraits());
		initializePortrait(game.getPlayers()[0].getLeader().getNumber());
		getChildren().add(getCurrentPlayer());
		getChildren().add(getPortrait());
		setAlignment(Pos.TOP_CENTER);
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
	
}
