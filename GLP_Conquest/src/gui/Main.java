package gui;
	
import java.net.URISyntaxException;

import exceptions.InvalidSizeMapNumberException;
import javafx.application.Application;
import javafx.stage.Stage;
import map.Map;
import mapGenerator.MapGenerator;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {

	private static final int WIDTH_SQUARE = 60;
	private static final int HEIGHT_SQUARE = 52;
	private static final int LITTLE_MAP = 0;
	private static final int MEDIUM_MAP = 1;
	private static final int WIDE_MAP = 2;
	private static final int LITTLE_DIMENSIONS = 27;
	private static final int MEDIUM_DIMENSIONS = 45;
	private static final int WIDE_DIMENSIONS = 63;
	private static final double WIDTH = 600;
	private static final double HEIGHT = 520;

	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, WIDTH, HEIGHT, Color.LIGHTSTEELBLUE);
			scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
			primaryStage.setTitle("Project Conquest : Another Modern Wargame");

			primaryStage.setScene(scene);
			
			MapGenerator mapGenerator = new MapGenerator();

			Canvas canvas = new Canvas(WIDTH, HEIGHT);
			GraphicsContext board = canvas.getGraphicsContext2D();
			
			try {
				Map map = mapGenerator.generate(LITTLE_MAP);
				int squareType = 0;
				int squareOwner = 0;
			
				Image[] squaresSprites = initializeSquareSprites();
				Image[] frontierSprites = initializeFrontierSprites();
				for(int i=0; i<LITTLE_DIMENSIONS; i++) {
					for (int j=0; j<LITTLE_DIMENSIONS; j++) {
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
			} catch (InvalidSizeMapNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			root.getChildren().add(canvas);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
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
}
