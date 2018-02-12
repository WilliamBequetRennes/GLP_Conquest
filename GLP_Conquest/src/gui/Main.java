package gui;
	
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
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
	
	private double screenWidth;
	private double screenHeight;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Rectangle2D screen = Screen.getPrimary().getVisualBounds();
			setScreenWidth(screen.getWidth());
			setScreenHeight(screen.getHeight());
			
			Scene scene = new Scene(root, getScreenWidth(), getScreenHeight(), Color.LIGHTSTEELBLUE);
			scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
			primaryStage.setTitle("Project Conquest : Another Modern Wargame");

			primaryStage.setScene(scene);
			
			GlobalBlock globalBlock = new GlobalBlock(getScreenWidth(), getScreenHeight());
			root.getChildren().add(globalBlock);
			primaryStage.show();
			//primaryStage.setFullScreen(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(double screenWidth) {
		this.screenWidth = screenWidth;
	}

	public double getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(double screenHeight) {
		this.screenHeight = screenHeight;
	}
}
