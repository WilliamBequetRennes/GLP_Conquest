package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import gui_datas.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LeaderSelection extends VBox {
	
	private final static int MAX_LEADER = 6;
	private final static double PLAYER_BOXES_WIDTH = 0.24;
	private final static double LEADERS_BOX_HEIGHT = 0.9;
	private final static double MENU_MOVES_HEIGHT = 0.1;
	private final static double SIDE_MENU_MOVES_WIDTH = 0.5;

	private BlockSize screenSize;
	private String leadersFile;
	private int playersNumber;
	private ImageView logo;
	private Label leaderSelection;

	private int[] currentLeader;
	private Label[] players;
	private Image[] portraits;
	private ImageView[] leaders;
	private Label[] names;
	private Label[] powers;
	private Button[] previousLeader;
	private Button[] nextLeader;
	private HBox[] changeLeader;
	
	private HBox menu;
	private VBox[] playersLeader;

	private VBox leadersBox;
	private HBox menuMoves;
	private HBox leftMenuMoves;
	private HBox rightMenuMoves;
	
	private Button getBack;
	private Button next;
	
	public LeaderSelection (BlockSize screenSize, int playersNumber, MenusBlock menusBlock) {
		super();
		setScreenSize(screenSize);
		setPrefSize(getScreenSize().getWidth(), getScreenSize().getHeight());
		setId("gradient");
		
		setLeadersFile("leaders.txt");
		setPlayersNumber(playersNumber);
		setPortraits(initializeLeaderPortraits());
		initializeLogo();
		initializeLabels();
		initializePlayers();
		initializeMenuBoxes();
		initializeButtons(menusBlock);
		initializeMainBoxes();
		displayContent();
		setAlignment(Pos.TOP_CENTER);
	}
	
	public void update(int player) {
		int leader = getCurrentLeader()[player];
		getLeaders()[player].setImage(getPortraits()[leader]);
		getPlayers()[player].setText("Player "+(player+1));
		try {
			BufferedReader reader = new BufferedReader(new FileReader(getLeadersFile()));
			String line = "";
			while (!line.startsWith(""+leader)) {
				line = reader.readLine();
			}
			String[] current = line.split("#");
			getNames()[player].setText(current[1]);
			getPowers()[player].setText("Power : "+current[2]);
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void initializePlayers() {
		setCurrentLeader(new int[getPlayersNumber()]);
		setPlayers(new Label[getPlayersNumber()]);
		setLeaders(new ImageView[getPlayersNumber()]);
		setNames(new Label[getPlayersNumber()]);
		setPowers(new Label[getPlayersNumber()]);
		setPreviousLeader(new Button[getPlayersNumber()]);
		setNextLeader(new Button[getPlayersNumber()]);
		setChangeLeader(new HBox[getPlayersNumber()]);
		
		for(int i = 0; i<getPlayersNumber(); i++) {
			getCurrentLeader()[i]=0;
			getPlayers()[i] = new Label();
			getLeaders()[i] = new ImageView();
			getNames()[i] = new Label();
			getPowers()[i] = new Label();
			initializeChangeButton(i);
			update(i);
			
			getPlayers()[i].setId("player"+(i+1));
			getNames()[i].setId("leader_name");
			getPowers()[i].setId("menu_text");
			getPreviousLeader()[i].setId("change_leader");
			getNextLeader()[i].setId("change_leader");
		}
	}
	
	public void initializeChangeButton(int player){
		getPreviousLeader()[player] = new Button();
		getNextLeader()[player] = new Button();
		
		getPreviousLeader()[player].setText("<");
		getNextLeader()[player].setText(">");

		getPreviousLeader()[player].setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if(getCurrentLeader()[player] > 0) {
					getCurrentLeader()[player]--;
				}
				else {
					getCurrentLeader()[player] = MAX_LEADER;
				}
				update(player);
			}
		});
		getNextLeader()[player].setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if(getCurrentLeader()[player] < MAX_LEADER) {
					getCurrentLeader()[player]++;
				}
				else {
					getCurrentLeader()[player] = 0;
				}
				update(player);
			}
		});
	}
	
	public void initializeLogo(){
		setLogo(new ImageView(new Image(getClass().getResource("\\sprites\\AMW_logo.png").toString())));
		
	}
	public void initializeLabels() {
		setLeaderSelection(new Label());
		getLeaderSelection().setText("Leader Selection");
		getLeaderSelection().setId("menu_title");
	}
	
	public void initializeMainBoxes() {
		setLeadersBox(new VBox());
		setMenuMoves(new HBox());
		setLeftMenuMoves(new HBox());
		setRightMenuMoves(new HBox());
		
		getLeadersBox().setPrefSize(getScreenSize().getWidth(), getScreenSize().getHeight()*LEADERS_BOX_HEIGHT);
		getMenuMoves().setPrefSize(getScreenSize().getWidth(), getScreenSize().getHeight()*MENU_MOVES_HEIGHT);
		getLeftMenuMoves().setPrefWidth(getScreenSize().getWidth()*SIDE_MENU_MOVES_WIDTH);
		getRightMenuMoves().setPrefWidth(getScreenSize().getWidth()*SIDE_MENU_MOVES_WIDTH);
		
		getLeadersBox().setAlignment(Pos.TOP_CENTER);
		getMenuMoves().setAlignment(Pos.BOTTOM_CENTER);
		getLeftMenuMoves().setAlignment(Pos.BOTTOM_LEFT);
		getRightMenuMoves().setAlignment(Pos.BOTTOM_RIGHT);
		
		getLeadersBox().setId("spacing");
	}
	public void initializeMenuBoxes() {
		setMenu(new HBox());
		setPlayersLeader(new VBox[getPlayersNumber()]);
		for(int i = 0; i<getPlayersNumber(); i++) {
			getChangeLeader()[i] = new HBox();
			getChangeLeader()[i].getChildren().add(getPreviousLeader()[i]);
			getChangeLeader()[i].getChildren().add(getNextLeader()[i]);
			getChangeLeader()[i].setAlignment(Pos.CENTER);
			getChangeLeader()[i].setId("spacing");
			
			getPlayersLeader()[i] = new VBox();
			getPlayersLeader()[i].getChildren().add(getPlayers()[i]);
			getPlayersLeader()[i].getChildren().add(getLeaders()[i]);
			getPlayersLeader()[i].getChildren().add(getNames()[i]);
			getPlayersLeader()[i].getChildren().add(getPowers()[i]);
			getPlayersLeader()[i].getChildren().add(getChangeLeader()[i]);
			
			getPlayersLeader()[i].setAlignment(Pos.CENTER);
			getPlayersLeader()[i].setPrefWidth(getScreenSize().getWidth()*PLAYER_BOXES_WIDTH);
			getPlayersLeader()[i].setId("leader_selection"+(i+1));
			
			getMenu().getChildren().add(getPlayersLeader()[i]);
		}
		getMenu().setAlignment(Pos.CENTER);
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
	public void initializeButtons(MenusBlock menusBlock) {
		setGetBack(new Button());
		setNext(new Button());
		
		getGetBack().setText("Back");
		getNext().setText("Start");
		
		getGetBack().setId("switch_menu_button");
		getNext().setId("switch_menu_button");
		
		getGetBack().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				menusBlock.comeBackToGameOptions();
			}
		});
		getNext().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				menusBlock.setLeaders(getCurrentLeader());
				menusBlock.initializePlayableBlock();
			}
		});
	}
	
	public void displayContent() {
		getLeadersBox().getChildren().add(getLogo());
		getLeadersBox().getChildren().add(getLeaderSelection());
		getLeadersBox().getChildren().add(getMenu());
		
		getLeftMenuMoves().getChildren().add(getGetBack());
		getRightMenuMoves().getChildren().add(getNext());
		
		getMenuMoves().getChildren().add(getLeftMenuMoves());
		getMenuMoves().getChildren().add(getRightMenuMoves());
		
		getChildren().add(getLeadersBox());
		getChildren().add(getMenuMoves());
	}
	
	public String getLeadersFile() {
		return leadersFile;
	}

	public void setLeadersFile(String leadersFile) {
		this.leadersFile = leadersFile;
	}

	public int[] getCurrentLeader() {
		return currentLeader;
	}

	public void setCurrentLeader(int[] currentLeader) {
		this.currentLeader = currentLeader;
	}

	public Label[] getPlayers() {
		return players;
	}

	public void setPlayers(Label[] players) {
		this.players = players;
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

	public Label getLeaderSelection() {
		return leaderSelection;
	}

	public void setLeaderSelection(Label leaderSelection) {
		this.leaderSelection = leaderSelection;
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

	public int getPlayersNumber() {
		return playersNumber;
	}

	public void setPlayersNumber(int playersNumber) {
		this.playersNumber = playersNumber;
	}

	public HBox getMenu() {
		return menu;
	}

	public void setMenu(HBox menu) {
		this.menu = menu;
	}

	public VBox[] getPlayersLeader() {
		return playersLeader;
	}

	public void setPlayersLeader(VBox[] playersLeader) {
		this.playersLeader = playersLeader;
	}

	public Image[] getPortraits() {
		return portraits;
	}

	public void setPortraits(Image[] portraits) {
		this.portraits = portraits;
	}

	public ImageView[] getLeaders() {
		return leaders;
	}

	public void setLeaders(ImageView[] leaders) {
		this.leaders = leaders;
	}

	public Label[] getNames() {
		return names;
	}

	public void setNames(Label[] names) {
		this.names = names;
	}

	public Label[] getPowers() {
		return powers;
	}

	public void setPowers(Label[] powers) {
		this.powers = powers;
	}

	public Button[] getPreviousLeader() {
		return previousLeader;
	}

	public void setPreviousLeader(Button[] previousLeader) {
		this.previousLeader = previousLeader;
	}

	public Button[] getNextLeader() {
		return nextLeader;
	}

	public void setNextLeader(Button[] nextLeader) {
		this.nextLeader = nextLeader;
	}

	public HBox[] getChangeLeader() {
		return changeLeader;
	}

	public void setChangeLeader(HBox[] changeLeader) {
		this.changeLeader = changeLeader;
	}

	public VBox getLeadersBox() {
		return leadersBox;
	}

	public void setLeadersBox(VBox leadersBox) {
		this.leadersBox = leadersBox;
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
