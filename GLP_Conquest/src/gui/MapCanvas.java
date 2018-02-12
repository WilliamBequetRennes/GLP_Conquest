package gui;

import java.net.URISyntaxException;

import exceptions.InvalidMapSizeNumberException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import map.Map;
import mapGenerator.MapGenerator;

public class MapCanvas extends Canvas{

	private static final int WIDTH_SQUARE = 60;
	private static final int HEIGHT_SQUARE = 52;
	
	private MapGenerator mapGenerator;
	private GraphicsContext board;
	private Map map;
	private int mapSize;
	private int mapDimensions;

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
		setMap(mapGenerator.generate(mapSize));
			
		setBoard(getGraphicsContext2D());
		
		
	}

	public void generateMap() throws InvalidMapSizeNumberException{
		int squareType = 0;
		int squareOwner = 0;
	
		Image[] squaresSprites = initializeSquareSprites();
		Image[] frontierSprites = initializeFrontierSprites();
		for(int i=0; i<mapDimensions; i++) {
			for (int j=0; j<mapDimensions; j++) {
				squareType = map.getSquares()[i][j].getType();
				squareOwner = map.getSquares()[i][j].getFaction();
				if(i%2==0) {
					board.drawImage(squaresSprites[squareType], i*WIDTH_SQUARE*3/4, j*HEIGHT_SQUARE);
					board.drawImage(frontierSprites[squareOwner], i*WIDTH_SQUARE*3/4, j*HEIGHT_SQUARE);
				}
				else {
					board.drawImage(squaresSprites[squareType], i*WIDTH_SQUARE*3/4, HEIGHT_SQUARE*(j*2+1)/2);
					board.drawImage(frontierSprites[squareOwner], i*WIDTH_SQUARE*3/4, HEIGHT_SQUARE*(j*2+1)/2);
				}
			}
		}
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

	
}
