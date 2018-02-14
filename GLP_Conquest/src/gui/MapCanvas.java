package gui;

import java.net.URISyntaxException;
import java.util.HashMap;

import exceptions.InvalidMapSizeNumberException;
import gui_datas.PositionDouble;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import map.Map;
import mapGenerator.MapGenerator;
import squares.Square;

public class MapCanvas extends Canvas{

	private static final int WIDTH_SQUARE = 60;
	private static final int HEIGHT_SQUARE = 52;
	private static final Color BACKGROUND = Color.BLACK;
	
	private MapGenerator mapGenerator;
	private GraphicsContext board;
	private Map map;
	private int mapSize;
	private int mapDimensions;
	
	private Image[] squaresSprites;
	private Image[] frontierSprites;
	private double cameraPositionX;
	private double cameraPositionY;

	public MapCanvas (double screenWidth, double screenHeight, int mapSize) throws InvalidMapSizeNumberException {
		super();
		setWidth(screenWidth/2);
		setHeight(screenHeight/2);
		setMapSize(mapSize);
		switch(getMapSize()) {
			case(0):setMapDimensions(27);
			break;
			case(1):setMapDimensions(45);
			break;
			case(2):setMapDimensions(63);
			break;
			default: throw new InvalidMapSizeNumberException(mapSize);
		}
		setSquaresSprites(initializeSquareSprites());
		setFrontierSprites(initializeFrontierSprites());
		setCameraPositionX(0);
		setCameraPositionY(0);
		
		//Generate the map
		setMapGenerator(new MapGenerator());
		setMap(getMapGenerator().generate(getMapSize()));
		setBoard(getGraphicsContext2D());
		
	}

	public void staticMap(PositionDouble tracking) throws InvalidMapSizeNumberException{
		board.setFill(BACKGROUND);
		board.fillRect(0, 0, getWidth(), getHeight());
		int squareType = 0;
		int squareOwner = 0;
		setCameraPositionX(getCameraPositionX()+tracking.getX());
		setCameraPositionY(getCameraPositionY()+tracking.getY());
		HashMap<PositionDouble,Square> displayedSquares = new HashMap<>();
		for(int i=0; i<mapDimensions; i++) {
			for (int j=0; j<mapDimensions; j++) {
				squareType = map.getSquares()[i][j].getType();
				squareOwner = map.getSquares()[i][j].getFaction();
				
				double originalX = WIDTH_SQUARE-(Math.sqrt(3)*HEIGHT_SQUARE/6);
				double x = j*originalX-getCameraPositionX();
				double y = 0;
				if(x > -WIDTH_SQUARE && x < getWidth()+WIDTH_SQUARE) {
					if(j%2==0) {
						y = HEIGHT_SQUARE*i-getCameraPositionY();
						if(y > -HEIGHT_SQUARE && y < getHeight()+HEIGHT_SQUARE) {
							board.drawImage(getSquaresSprites()[squareType], x, y);
							board.drawImage(getFrontierSprites()[squareOwner], x, y);
							displayedSquares.put(new PositionDouble(x, y), map.getSquares()[i][j]);
						}
					}
					else {
						y = HEIGHT_SQUARE*(i+0.5)-getCameraPositionY();
						if(y > -HEIGHT_SQUARE && y < getHeight()+HEIGHT_SQUARE) {
							board.drawImage(getSquaresSprites()[squareType], x, y);
							board.drawImage(getFrontierSprites()[squareOwner], x, y);
							displayedSquares.put(new PositionDouble(x, y), map.getSquares()[i][j]);
						}
					}
				}
			}
		}
		initializeSquareClicks(displayedSquares);
	}
	
	public void animatedMap(PositionDouble tracking) {
		new AnimationTimer() {
			public void handle(long now) {
				board.setFill(BACKGROUND);
				board.fillRect(0, 0, getWidth(), getHeight());
				int squareType = 0;
				int squareOwner = 0;
				setCameraPositionX(getCameraPositionX()+tracking.getX());
				setCameraPositionY(getCameraPositionY()+tracking.getY());
				HashMap<PositionDouble,Square> displayedSquares = new HashMap<>();
				for(int i=0; i<mapDimensions; i++) {
					for (int j=0; j<mapDimensions; j++) {
						squareType = map.getSquares()[i][j].getType();
						squareOwner = map.getSquares()[i][j].getFaction();
						
						double originalX = WIDTH_SQUARE-(Math.sqrt(3)*HEIGHT_SQUARE/6);
						double x = j*originalX-getCameraPositionX();
						double y = 0;
						if(x > -WIDTH_SQUARE && x < getWidth()+WIDTH_SQUARE) {
							if(j%2==0) {
								y = HEIGHT_SQUARE*i-getCameraPositionY();
								if(y > -HEIGHT_SQUARE && y < getHeight()+HEIGHT_SQUARE) {
									board.drawImage(getSquaresSprites()[squareType], x, y);
									board.drawImage(getFrontierSprites()[squareOwner], x, y);
									displayedSquares.put(new PositionDouble(x, y), map.getSquares()[i][j]);
								}
							}
							else {
								y = HEIGHT_SQUARE*(i+0.5)-getCameraPositionY();
								if(y > -HEIGHT_SQUARE && y < getHeight()+HEIGHT_SQUARE) {
									board.drawImage(getSquaresSprites()[squareType], x, y);
									board.drawImage(getFrontierSprites()[squareOwner], x, y);
									displayedSquares.put(new PositionDouble(x, y), map.getSquares()[i][j]);
								}
							}
						}
					}
				}
				initializeSquareClicks(displayedSquares);
			}
		}.start();
	}
	
	public void initializeSquareClicks(HashMap<PositionDouble,Square> squares) {
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
						System.out.println(squares.get(current).getPosition().getXPosition());
						System.out.println(squares.get(current).getPosition().getYPosition());
						System.out.println(squares.get(current).getType());
					}
				}
			}
		});
	}
	
	public Image[] initializeSquareSprites() {
		Image[] sprites = new Image[10];
		try {
			sprites[0] = new Image(getClass().getResource("\\sprites\\Water.png").toURI().toString());
			sprites[1] = new Image(getClass().getResource("\\sprites\\Land.png").toString());
			sprites[2] = new Image(getClass().getResource("\\sprites\\Desert.png").toString());
			sprites[3] = new Image(getClass().getResource("\\sprites\\Forest.png").toString());
			sprites[4] = new Image(getClass().getResource("\\sprites\\Mont.png").toString());
			sprites[5] = new Image(getClass().getResource("\\sprites\\Mine.png").toString());
			sprites[6] = new Image(getClass().getResource("\\sprites\\Farm.png").toString());
			sprites[7] = new Image(getClass().getResource("\\sprites\\OilWell.png").toString());
			sprites[8] = new Image(getClass().getResource("\\sprites\\NuclearPlant.png").toString());
			sprites[9] = new Image(getClass().getResource("\\sprites\\City.png").toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}

	public int getMapDimensions() {
		return mapDimensions;
	}

	public void setMapDimensions(int mapDimensions) {
		this.mapDimensions = mapDimensions;
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

	
}
