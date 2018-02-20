package gui;
	
import java.awt.Dimension;

import gui_datas.BlockSize;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import map.Map;
import mapGenerator.MapGenerator;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
	private static final int PLAYERS_NUMBER = 4;
	
	private BlockSize screenSize;
	private int mapSize;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Rectangle2D screen = Screen.getPrimary().getVisualBounds();
			Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			setScreenSize(new BlockSize(dimension.getWidth(), dimension.getHeight()));
			
			Scene scene = new Scene(root, getScreenSize().getWidth(), getScreenSize().getHeight(), Color.LIGHTSTEELBLUE);
			//Scene scene = new Scene(root, 300, 300);
			
			scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
			primaryStage.setTitle("Project Conquest : Another Modern Wargame");

			primaryStage.setScene(scene);
			
			setMapSize(LITTLE_MAP);
			GlobalBlock globalBlock = new GlobalBlock(getScreenSize(), PLAYERS_NUMBER, getMapSize());
			root.getChildren().add(globalBlock);
			
			primaryStage.show();
			primaryStage.setFullScreen(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public BlockSize getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(BlockSize screenSize) {
		this.screenSize = screenSize;
	}

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}
}
