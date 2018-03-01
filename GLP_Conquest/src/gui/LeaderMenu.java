package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	
	private static final int MAX_LINE_SIZE = 5;
	
	private BlockSize blockSize;
	
	private Image[] leaderPortraits;
	private ImageView portrait;
	private Label name;
	private Label power;
	private Label boost1;
	private Label boost2;
	private Button getBack;
	
	private String leadersFile;

	public LeaderMenu(BlockSize blockSize, CentralMenu centralMenu) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		setLeadersFile("leaders.txt");
		setLeaderPortraits(initializeLeaderPortraits());
		initializePortrait(0);
		initializeContent();
		initializeGetBackButton(centralMenu);

		displayContent();
		setAlignment(Pos.TOP_CENTER);
	}
	
	public void update(int leader) {
		initializePortrait(leader);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(getLeadersFile()));
			String line = "";
			while (!line.startsWith(""+leader)) {
					line = reader.readLine();
			}
			String[] current = line.split("#");
			getName().setText(current[1]);
			getPower().setText(current[2]);
			getBoost1().setText("- "+current[3]);
			if(current.length==MAX_LINE_SIZE) {
				getBoost2().setText("- "+current[4]);
			}
			else {
				getBoost2().setText("");
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void initializeGetBackButton(CentralMenu centralMenu){
		setGetBack(new Button());
		getGetBack().setText("Back");
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
		setBoost1(new Label());
		setBoost2(new Label());
	}
	
	public void displayContent() {
		getChildren().add(getPortrait());
		getChildren().add(getName());
		getChildren().add(getPower());
		getChildren().add(getBoost1());
		getChildren().add(getBoost2());
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

	public Label getBoost1() {
		return boost1;
	}

	public void setBoost1(Label boost1) {
		this.boost1 = boost1;
	}

	public Label getBoost2() {
		return boost2;
	}

	public void setBoost2(Label boost2) {
		this.boost2 = boost2;
	}

	public Button getGetBack() {
		return getBack;
	}

	public void setGetBack(Button getBack) {
		this.getBack = getBack;
	}

	public String getLeadersFile() {
		return leadersFile;
	}

	public void setLeadersFile(String leadersFile) {
		this.leadersFile = leadersFile;
	}
}
