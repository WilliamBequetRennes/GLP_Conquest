package gui;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import gui_datas.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class LeaderMenu extends VBox{
	
	private BlockSize blockSize;
	
	private Image[] leaderPortraits;
	private ImageView portrait;
	private Label name;
	private Label power;
	private Label description;
	private Button getBack;

	public LeaderMenu(BlockSize blockSize, CentralMenu centralMenu) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		setLeaderPortraits(initializeLeaderPortraits());
		initializePortrait(0);
		initializeContent();
		initializeGetBackButton(centralMenu);

		displayContent();
		setAlignment(Pos.TOP_CENTER);
	}
	
	public void update(int leader) {
		initializePortrait(leader);
	}
	public void initializeGetBackButton(CentralMenu centralMenu){
		setGetBack(new Button());
		getGetBack().setText("Get Back");
		getGetBack().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				centralMenu.getMapCanvas().setVisible(true);
				centralMenu.getMapCanvas().toFront();
				centralMenu.getLeaderMenu().setVisible(false);
			}
		});
	}
	
	public void initializeContent() {
		setName(new Label());
		setPower(new Label());
		setDescription(new Label());
	}
	
	public void displayContent() {
		getChildren().add(getPortrait());
		getChildren().add(getName());
		getChildren().add(getPower());
		getChildren().add(getDescription());
		getChildren().add(getGetBack());
	}
	
	public void initializePortrait(int leader) {
		setPortrait(new ImageView(getLeaderPortraits()[leader]));
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

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
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

	public Label getName() {
		return name;
	}

	public void setName(Label name) {
		this.name = name;
	}

	public Label getPower() {
		return power;
	}

	public void setPower(Label power) {
		this.power = power;
	}

	public Label getDescription() {
		return description;
	}

	public void setDescription(Label description) {
		this.description = description;
	}

	public Button getGetBack() {
		return getBack;
	}

	public void setGetBack(Button getBack) {
		this.getBack = getBack;
	}
}
