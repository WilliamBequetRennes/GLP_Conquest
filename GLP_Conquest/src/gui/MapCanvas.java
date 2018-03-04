package gui;

import java.util.HashMap;

import exceptions.InvalidMapSizeNumberException;
import game.Game;
import gui_datas.BlockSize;
import gui_datas.PositionDouble;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import mapGenerator.MapGenerator;
import squares.Square;

public class MapCanvas extends Canvas{

	private static final int WIDTH_SQUARE = 60;
	private static final int HEIGHT_SQUARE = 52;
	private static final Color BACKGROUND = Color.BLACK;
	private static final double VIEWABLE_VOID = 150;
	
	private MapGenerator mapGenerator;
	private GraphicsContext board;
	private int numberOfSquares;
	private BlockSize mapDimensions;
	
	private Image[] squaresSprites;
	private Image[] frontierSprites;
	private double cameraPositionX;
	private double cameraPositionY;

	public MapCanvas (double blockWidth, double blockHeight, Game game) throws InvalidMapSizeNumberException {
		super();
		initializeMapDimensions(blockWidth, blockHeight, game.getMapSize());
		setSquaresSprites(initializeSquareSprites());
		setFrontierSprites(initializeFrontierSprites());
		setCameraPositionX(0);
		setCameraPositionY(0);
		
		setBoard(getGraphicsContext2D());
		
	}
	
	public void initializeMapDimensions(double blockWidth, double blockHeight, int mapSize) throws InvalidMapSizeNumberException {
		setWidth(blockWidth);
		setHeight(blockHeight);
		switch(mapSize) {
			case(0):setNumberOfSquares(27);
			break;
			case(1):setNumberOfSquares(45);
			break;
			case(2):setNumberOfSquares(63);
			break;
			default: throw new InvalidMapSizeNumberException(mapSize);
		}
		double mapWidth = WIDTH_SQUARE*(3*getNumberOfSquares()+2)/4-getNumberOfSquares()/2;
		double mapHeight = HEIGHT_SQUARE*(getNumberOfSquares()+0.5);
		setMapDimensions(new BlockSize(mapWidth, mapHeight));
	
	}
	
	public void animatedMap(PositionDouble tracking, Game game, GameBlock gameBlock) {
		new AnimationTimer() {
			public void handle(long now) {
				board.setFill(BACKGROUND);
				board.fillRect(0, 0, getWidth(), getHeight());
				int squareType = 0;
				int squareOwner = 0;
				if((tracking.getX()<=0 && getCameraPositionX()>-VIEWABLE_VOID) || (tracking.getX()>=0 && getCameraPositionX()<getMapDimensions().getWidth()-getWidth()+VIEWABLE_VOID)) {
					setCameraPositionX(getCameraPositionX()+tracking.getX());
				}
				if((tracking.getY()<=0 && getCameraPositionY()>-VIEWABLE_VOID) || (tracking.getY()>=0 && getCameraPositionY()<getMapDimensions().getHeight()-getHeight()+VIEWABLE_VOID)) {
					setCameraPositionY(getCameraPositionY()+tracking.getY());
				}
				HashMap<PositionDouble,Square> displayedSquares = new HashMap<>();
				for(int i=0; i<getNumberOfSquares(); i++) {
					for (int j=0; j<getNumberOfSquares(); j++) {
						squareType = game.getMap().getSquares()[i][j].getType();
						squareOwner = game.getMap().getSquares()[i][j].getFaction();
						
						double x = j*WIDTH_SQUARE*3/4-getCameraPositionX();
						double y = 0;
						if(x > -WIDTH_SQUARE && x < getWidth()+WIDTH_SQUARE) {
							if(j%2==0) {
								y = HEIGHT_SQUARE*i-getCameraPositionY();
								if(y > -HEIGHT_SQUARE && y < getHeight()+HEIGHT_SQUARE) {
									board.drawImage(getSquaresSprites()[squareType], x, y);
									board.drawImage(getFrontierSprites()[squareOwner], x, y);
									displayedSquares.put(new PositionDouble(x, y), game.getMap().getSquares()[i][j]);
								}
							}
							else {
								y = HEIGHT_SQUARE*(i+0.5)-getCameraPositionY();
								if(y > -HEIGHT_SQUARE && y < getHeight()+HEIGHT_SQUARE) {
									board.drawImage(getSquaresSprites()[squareType], x, y);
									board.drawImage(getFrontierSprites()[squareOwner], x, y);
									displayedSquares.put(new PositionDouble(x, y), game.getMap().getSquares()[i][j]);
								}
							}
						}
					}
				}
				initializeSquareClicks(game, displayedSquares, gameBlock);
			}
		}.start();
	}
	
	public void initializeSquareClicks(Game game, HashMap<PositionDouble,Square> squares, GameBlock gameBlock) {
		double xCenter = (WIDTH_SQUARE/2);
		double yCenter = (HEIGHT_SQUARE/2);
		double radius = HEIGHT_SQUARE/2;
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				double mouseX = mouseEvent.getX();
				double mouseY = mouseEvent.getY();
				for(PositionDouble current : squares.keySet()) {
					double xPosition = Math.abs(current.getX()+xCenter-mouseX);
					double yPosition = Math.abs(current.getY()+yCenter-mouseY);
					if(Math.pow(xPosition, 2) + Math.pow(yPosition, 2) <= Math.pow(radius, 2)){
						game.setCurrentSquare(squares.get(current));
						String type = "";
						switch(game.getCurrentSquare().getType()) {
							case(0):type="Water";
							break;
							case(1):type="Land";
							break;
							case(2):type="Desert";
							break;
							case(3):type="Forest";
							break;
							case(4):type="Mont";
							break;
							case(5):type="Mine";
							break;
							case(6):type="Farm";
							break;
							case(7):type="Oil well";
							break;
							case(8):type="Nuclear plant";
							break;
							case(9):type="City";
							break;
						}
						String attackBoost = "Attack x"+game.getCurrentSquare().getBonus().getAttack();	
						String defenseBoost = "Defense x"+game.getCurrentSquare().getBonus().getDefense();
						String level = "Level : "+game.getCurrentSquare().getLevel();
						
						gameBlock.getLeftMenu().getUsualLeftMenu().getSquareType().setText(type);
						gameBlock.getLeftMenu().getUsualLeftMenu().getAttackBoost().setText(attackBoost);
						gameBlock.getLeftMenu().getUsualLeftMenu().getDefenseBoost().setText(defenseBoost);
						gameBlock.getLeftMenu().getUsualLeftMenu().getSquareLevel().setText(level);
						
						//Unit creation part in the right menu
						if(game.getCurrentSquare().getType()==9) {
							gameBlock.getRightMenu().getUsualRightMenu().getCreateUnit().setVisible(true);
						}
						else {
							gameBlock.getRightMenu().getUsualRightMenu().getCreateUnit().setVisible(false);
						}
						
						//Square level part in the left menu
						if(game.getCurrentSquare().getType()>4) {
							gameBlock.getLeftMenu().getUsualLeftMenu().getSquareLevel().setVisible(true);
							if(game.getCurrentSquare().getLevel()<3) {
								gameBlock.getLeftMenu().getUsualLeftMenu().getLevelUp().setVisible(true);
							}
							else {
								gameBlock.getLeftMenu().getUsualLeftMenu().getLevelUp().setVisible(false);
							}
						}
						else {
							gameBlock.getLeftMenu().getUsualLeftMenu().getSquareLevel().setVisible(false);
							gameBlock.getLeftMenu().getUsualLeftMenu().getLevelUp().setVisible(false);
						}
					}
				}
			}
		});
	}
	
	public Image[] initializeSquareSprites() {
		Image[] sprites = new Image[10];
		sprites[0] = new Image(getClass().getResource("\\sprites\\Water.png").toString());
		sprites[1] = new Image(getClass().getResource("\\sprites\\Land.png").toString());
		sprites[2] = new Image(getClass().getResource("\\sprites\\Desert.png").toString());
		sprites[3] = new Image(getClass().getResource("\\sprites\\Forest.png").toString());
		sprites[4] = new Image(getClass().getResource("\\sprites\\Mont.png").toString());
		sprites[5] = new Image(getClass().getResource("\\sprites\\Mine.png").toString());
		sprites[6] = new Image(getClass().getResource("\\sprites\\Farm.png").toString());
		sprites[7] = new Image(getClass().getResource("\\sprites\\OilWell.png").toString());
		sprites[8] = new Image(getClass().getResource("\\sprites\\NuclearPlant.png").toString());
		sprites[9] = new Image(getClass().getResource("\\sprites\\City.png").toString());
		return sprites;
	}
	
	public Image[] initializeFrontierSprites() {
		Image[] sprites = new Image[5];
		sprites[0] = new Image(getClass().getResource("\\sprites\\Neutral_Frontier.png").toString());
		sprites[1] = new Image(getClass().getResource("\\sprites\\Player1_Frontier.png").toString());
		sprites[2] = new Image(getClass().getResource("\\sprites\\Player2_Frontier.png").toString());
		sprites[3] = new Image(getClass().getResource("\\sprites\\Player3_Frontier.png").toString());
		sprites[4] = new Image(getClass().getResource("\\sprites\\Player4_Frontier.png").toString());
		return sprites;
	}

	public MapGenerator getMapGenerator() {
		return mapGenerator;
	}

	public void setMapGenerator(MapGenerator mapGenerator) {
		this.mapGenerator = mapGenerator;
	}

	public GraphicsContext getBoard() {
		return board;
	}

	public int getNumberOfSquares() {
		return numberOfSquares;
	}

	public void setNumberOfSquares(int numberOfSquares) {
		this.numberOfSquares = numberOfSquares;
	}

	public void setBoard(GraphicsContext board) {
		this.board = board;
	}

	public Image[] getSquaresSprites() {
		return squaresSprites;
	}

	public void setSquaresSprites(Image[] squaresSprites) {
		this.squaresSprites = squaresSprites;
	}

	public Image[] getFrontierSprites() {
		return frontierSprites;
	}

	public void setFrontierSprites(Image[] frontierSprites) {
		this.frontierSprites = frontierSprites;
	}

	public double getCameraPositionX() {
		return cameraPositionX;
	}

	public void setCameraPositionX(double cameraPositionX) {
		this.cameraPositionX = cameraPositionX;
	}

	public double getCameraPositionY() {
		return cameraPositionY;
	}

	public void setCameraPositionY(double cameraPositionY) {
		this.cameraPositionY = cameraPositionY;
	}

	public BlockSize getMapDimensions() {
		return mapDimensions;
	}

	public void setMapDimensions(BlockSize mapDimensions) {
		this.mapDimensions = mapDimensions;
	}

	
}
