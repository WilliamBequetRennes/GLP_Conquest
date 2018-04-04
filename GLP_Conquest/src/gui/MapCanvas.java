package gui;

import java.util.ArrayList;
import java.util.HashMap;

import data.Position;
import exceptions.AttributeException;
import exceptions.InvalidMapSizeNumberException;
import exceptions.OutOfRangeException;
import fight.AreaScanner;
import game.Game;
import gui_data.BlockSize;
import gui_data.PositionDouble;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import mapGenerator.MapGenerator;
import movement.Graph;
import movement.IndexPosition;
import movement.Movement;
import squares.Square;
import units.Unit;

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
	private Image selectedSquareSprite;
	private Position selectedSquare;
	private double cameraPositionX;
	private double cameraPositionY;
	
	private Image[] unitsSprites;
	private Image[] unitBackgroundsSprites;
	
	private Image possibleMoveSprite;
	private ArrayList<Position> possibleMoves;
	private ArrayList<IndexPosition> detectedMoves;
	private Graph movesGraph;
	private boolean moveAvailable;
	private Unit movingUnit;
	
	private Image possibleAttackSprite;
	private ArrayList<Position> possibleAttacks;
	private AreaScanner areaScanner;

	public MapCanvas (double blockWidth, double blockHeight, Game game) throws InvalidMapSizeNumberException {
		super();
		initializeMapDimensions(blockWidth, blockHeight, game.getMapSize());
		
		setSquaresSprites(initializeSquareSprites());
		setFrontierSprites(initializeFrontierSprites());
		setUnitsSprites(initializeUnitsSprites());
		setUnitBackgroundsSprites(initializeUnitBackgroundsSprites());
		initializeFeedbackSprites();
		
		setCameraPositionX(0);
		setCameraPositionY(0);
		
		initializeMoves();
		
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
	
	public void initializeMoves() {
		setPossibleMoves(new ArrayList<Position>());
		setMoveAvailable(false);
		setMovingUnit(null);
		
		setPossibleAttacks(new ArrayList<Position>());
		setAreaScanner(new AreaScanner());
	}
	
	public void createAnimatedMap(PositionDouble tracking, Game game, GameBlock gameBlock, MenusBlock menusBlock) {
		new AnimationTimer() {
			public void handle(long now) {
				getBoard().setFill(BACKGROUND);
				getBoard().fillRect(0, 0, getWidth(), getHeight());
				int squareType = 0;
				int squareOwner = 0;
				boolean squareUnit;
				//set the limits of the camera's tracking
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
						squareUnit = game.getMap().getSquares()[i][j].getUnit();
						Position position = new Position(i,j);
						
						double x = j*WIDTH_SQUARE*3/4-getCameraPositionX();
						double y = 0;
						if(x > -WIDTH_SQUARE && x < getWidth()+WIDTH_SQUARE) {
							if(j%2==0) {
								y = HEIGHT_SQUARE*i-getCameraPositionY();
							}
							else {
								y = HEIGHT_SQUARE*(i+0.5)-getCameraPositionY();
							}
							if(y > -HEIGHT_SQUARE && y < getHeight()+HEIGHT_SQUARE) {
								getBoard().drawImage(getSquaresSprites()[squareType], x, y);
								getBoard().drawImage(getFrontierSprites()[squareOwner], x, y);
								if(squareUnit) {
									getBoard().drawImage(getUnitBackgroundsSprites()[squareOwner-1], x, y);
									int unit = game.getPlayers()[squareOwner-1].getUnits().get(position).getType();
									getBoard().drawImage(getUnitsSprites()[unit], x, y);
								}
								displayedSquares.put(new PositionDouble(x, y, i, j), game.getMap().getSquares()[i][j]);
								if(getSelectedSquare().equals(position)) {
									getBoard().drawImage(getSelectedSquareSprite(), x, y);
								}
								if(getPossibleMoves().contains(position)) {
									getBoard().drawImage(getPossibleMoveSprite(), x, y);
								}
								if(getPossibleAttacks().contains(position)) {
									getBoard().drawImage(getPossibleAttackSprite(), x, y);
								}
							}
						}
					}
				}
				initializeSquareClicks(game, displayedSquares, gameBlock, menusBlock);
			}
		}.start();
	}
	
	public void initializeSquareClicks(Game game, HashMap<PositionDouble,Square> squares, GameBlock gameBlock, MenusBlock menusBlock) {
		double xCenter = (WIDTH_SQUARE/2);
		double yCenter = (HEIGHT_SQUARE/2);
		double radius = HEIGHT_SQUARE/2;
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				clearMenus(gameBlock, menusBlock); //Clear other menus
				
				double mouseX = mouseEvent.getX();
				double mouseY = mouseEvent.getY();
				for(PositionDouble current : squares.keySet()) {
					double xPosition = Math.abs(current.getX()+xCenter-mouseX);
					double yPosition = Math.abs(current.getY()+yCenter-mouseY);
					if(Math.pow(xPosition, 2) + Math.pow(yPosition, 2) <= Math.pow(radius, 2)){
						Square clicked = game.getMap().getSquares()[current.getI()][current.getJ()];
						game.setCurrentSquare(clicked);
						setSelectedSquare(clicked.getPosition());
						movement(game);
						attacks(game);
						gameBlock.getLeftMenu().getUsualLeftMenu().update(game);
						changeVisibility(game, gameBlock);
					}
				}
			}
		});
	}
	
	public void attacks(Game game) {
		//If the unit is controled by the player and have all its move points, it can attack
		if (game.getCurrentSquare().getFaction() == game.getCurrentPlayer()
				&& game.getCurrentSquare().getUnit()) {
			if(getMovingUnit().getMovement()==getMovingUnit().getMaxMovement()) {
				setPossibleAttacks(getAreaScanner().searchTargets(getMovingUnit(), game.getMap(), game));
				for(Position current : getPossibleAttacks()) {
					if (getPossibleMoves().contains(current)) {
						getPossibleMoves().remove(current);
					}
				}
			}
			else {
				getPossibleAttacks().clear();
			}
		}
		else {
			getPossibleAttacks().clear();
		}
	}
	
	public void movement(Game game) {
		//If the unit is controled by the player it can be moved
		if (game.getCurrentSquare().getFaction() == game.getCurrentPlayer()
				&& game.getCurrentSquare().getUnit()) {
			setMovingUnit(game.getPlayers()[game.getCurrentPlayer()-1].getUnits().get(getSelectedSquare()));
			Movement move = new Movement(getMovingUnit());
			setDetectedMoves(move.availableMovement(game.getMap()));
			
			getPossibleMoves().clear();
			for(IndexPosition current : getDetectedMoves()) {
				getPossibleMoves().add(new Position(current.getJPosition(), current.getIPosition()));
			}
			setMoveAvailable(true);
		}
		//if a unit is moving and the square is empty, the unit can move on it
		else if (isMoveAvailable() && !game.getCurrentSquare().getUnit() &&
				getPossibleMoves().contains(getSelectedSquare())) {
				Movement move = new Movement(getMovingUnit());
				
				move.goTo(getSelectedSquare(), getDetectedMoves(), game.getMap(), game);
				
				//update movingUnit with the new position of the unit
				setMovingUnit(game.getPlayers()[game.getCurrentPlayer()-1].getUnits().get(getSelectedSquare()));
				move = new Movement(getMovingUnit());
				setDetectedMoves(move.availableMovement(game.getMap()));
				
				//update possibleMoves
				getPossibleMoves().clear();
				for(IndexPosition current : getDetectedMoves()) {
					getPossibleMoves().add(new Position(current.getJPosition(), current.getIPosition()));
				}
				setMoveAvailable(true);
			
		}
		//if a unit is moving and the square is occupied by an ennemy, the fight menu is opened
		else if (isMoveAvailable() && game.getCurrentSquare().getUnit()) {
			//afficher l'écran de prévisualisation de combat avec un boutton Attack
			//pour lancer le combat
		}
		//if the square isn't occupied by a player's unit and the moving unit is to far
		//or if there is no moving unit, there is no possible action
		else {
			getPossibleMoves().clear();
			setMoveAvailable(false);
		}
	}
	public void changeVisibility(Game game, GameBlock gameBlock) {
		//Unit creation part in the right menu
		if(game.getCurrentSquare().getType()==9 && game.getCurrentSquare().getFaction()==game.getCurrentPlayer() && !game.getCurrentSquare().getUnit()) {
			gameBlock.getRightMenu().getUsualRightMenu().getCreateUnit().setVisible(true);
		}
		else {
			gameBlock.getRightMenu().getUsualRightMenu().getCreateUnit().setVisible(false);
		}
	}
	
	public void clearMenus(GameBlock gameBlock, MenusBlock menusBlock) {
		//reset left menu
		gameBlock.getLeftMenu().getUsualLeftMenu().setVisible(true);
		gameBlock.getLeftMenu().getUsualLeftMenu().toFront();
		gameBlock.getLeftMenu().getGameMenu().setVisible(false);
		menusBlock.getPlayableBlock().getCentralBlock().getMenuBar().setMenuClicked(false);

		//reset left menu
		gameBlock.getRightMenu().getUsualRightMenu().setVisible(true);
		gameBlock.getRightMenu().getUsualRightMenu().toFront();
		gameBlock.getRightMenu().getUnitCreationMenu().setVisible(false);
		gameBlock.getRightMenu().getUnitCreator().setVisible(false);
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
	
	public void initializeFeedbackSprites() {
		setSelectedSquare(new Position(getNumberOfSquares(), getNumberOfSquares()));
		setSelectedSquareSprite(new Image(getClass().getResource("\\sprites\\Selection.png").toString()));
		setPossibleMoveSprite(new Image(getClass().getResource("\\sprites\\PossibleMove.png").toString()));
		setPossibleAttackSprite(new Image(getClass().getResource("\\sprites\\PossibleAttack.png").toString()));
	}
	
	public Image[] initializeUnitsSprites() {
		Image[] sprites = new Image[8];
		sprites[0] = new Image(getClass().getResource("\\sprites\\assault.png").toString());
		sprites[1] = new Image(getClass().getResource("\\sprites\\sniper.png").toString());
		sprites[2] = new Image(getClass().getResource("\\sprites\\OB42.png").toString());
		sprites[3] = new Image(getClass().getResource("\\sprites\\BFG9000.png").toString());
		sprites[4] = new Image(getClass().getResource("\\sprites\\tank.png").toString());
		sprites[5] = new Image(getClass().getResource("\\sprites\\turret.png").toString());
		sprites[6] = new Image(getClass().getResource("\\sprites\\destroyer.png").toString());
		sprites[7] = new Image(getClass().getResource("\\sprites\\battleship.png").toString());
		return sprites;
	}
	public Image[] initializeUnitBackgroundsSprites() {
		Image[] sprites = new Image[4];
		sprites[0] = new Image(getClass().getResource("\\sprites\\Player1_unit.png").toString());
		sprites[1] = new Image(getClass().getResource("\\sprites\\Player2_unit.png").toString());
		sprites[2] = new Image(getClass().getResource("\\sprites\\Player3_unit.png").toString());
		sprites[3] = new Image(getClass().getResource("\\sprites\\Player4_unit.png").toString());
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

	public Image getSelectedSquareSprite() {
		return selectedSquareSprite;
	}

	public void setSelectedSquareSprite(Image selectedSquareSprite) {
		this.selectedSquareSprite = selectedSquareSprite;
	}

	public Position getSelectedSquare() {
		return selectedSquare;
	}

	public void setSelectedSquare(Position selectedSquare) {
		this.selectedSquare = selectedSquare;
	}

	public Image[] getUnitsSprites() {
		return unitsSprites;
	}

	public void setUnitsSprites(Image[] unitsSprites) {
		this.unitsSprites = unitsSprites;
	}

	public Image[] getUnitBackgroundsSprites() {
		return unitBackgroundsSprites;
	}

	public void setUnitBackgroundsSprites(Image[] unitBackgroundsSprites) {
		this.unitBackgroundsSprites = unitBackgroundsSprites;
	}

	public ArrayList<Position> getPossibleMoves() {
		return possibleMoves;
	}

	public void setPossibleMoves(ArrayList<Position> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

	public boolean isMoveAvailable() {
		return moveAvailable;
	}

	public void setMoveAvailable(boolean moveAvailable) {
		this.moveAvailable = moveAvailable;
	}

	public Graph getMovesGraph() {
		return movesGraph;
	}

	public void setMovesGraph(Graph movesGraph) {
		this.movesGraph = movesGraph;
	}

	public Unit getMovingUnit() {
		return movingUnit;
	}

	public void setMovingUnit(Unit movingUnit) {
		this.movingUnit = movingUnit;
	}

	public Image getPossibleMoveSprite() {
		return possibleMoveSprite;
	}

	public void setPossibleMoveSprite(Image possibleMoveSprite) {
		this.possibleMoveSprite = possibleMoveSprite;
	}

	public ArrayList<Position> getPossibleAttacks() {
		return possibleAttacks;
	}

	public void setPossibleAttacks(ArrayList<Position> possibleAttacks) {
		this.possibleAttacks = possibleAttacks;
	}

	public AreaScanner getAreaScanner() {
		return areaScanner;
	}

	public void setAreaScanner(AreaScanner areaScanner) {
		this.areaScanner = areaScanner;
	}

	public Image getPossibleAttackSprite() {
		return possibleAttackSprite;
	}

	public void setPossibleAttackSprite(Image possibleAttackSprite) {
		this.possibleAttackSprite = possibleAttackSprite;
	}

	public ArrayList<IndexPosition> getDetectedMoves() {
		return detectedMoves;
	}

	public void setDetectedMoves(ArrayList<IndexPosition> detectedMoves) {
		this.detectedMoves = detectedMoves;
	}
	
}
