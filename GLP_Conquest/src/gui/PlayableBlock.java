package gui;

import countries.Country;
import countries.Leader;
import data.Position;
import exceptions.InvalidMapSizeNumberException;
import game.Game;
import game.Turn;
import gui_data.BlockSize;
import gui_data.PositionDouble;
import javafx.scene.layout.GridPane;
import map.Map;
import map.MapLoader;
import map.MapSaver;
import mapGenerator.MapGenerator;

public class PlayableBlock extends GridPane{
	
	private static final double CORNER_WIDTH = 4;
	private static final double CORNER_HEIGHT = 20;
	
	private static final int LITTLE_DIMENSIONS = 27;
	private static final int MEDIUM_DIMENSIONS = 45;
	private static final int WIDE_DIMENSIONS = 63;
	
	private static final int LITTLE_STARTING_CITY_MIN = 4;
	private static final int LITTLE_STARTING_CITY_MAX = 22;
	private static final int MEDIUM_STARTING_CITY_MIN = 13;
	private static final int MEDIUM_STARTING_CITY_MAX = 31;
	private static final int WIDE_STARTING_CITY_MIN = 13;
	private static final int WIDE_STARTING_CITY_MAX = 49;

	private TrackingCamera northWestTracking;
	private TrackingCamera northTracking;
	private TrackingCamera northEastTracking;
	private TrackingCamera eastTracking;
	private TrackingCamera southEastTracking;
	private TrackingCamera southTracking;
	private TrackingCamera southWestTracking;
	private TrackingCamera westTracking;
	
	private CentralBlock centralBlock;

	private BlockSize screenSize;
	private int mapSize;
	private int playersNumber;
	private int turnsNumber;
	private PositionDouble tracking;
	
	private Game game;
	private MapGenerator mapGenerator;
	private MapSaver mapSaver;
	private MapLoader mapLoader;
	
	public PlayableBlock(BlockSize screenSize, int playersNumber, int turnsNumber, int mapSize, int[] leaders, int mapNumber, MenusBlock menusBlock) {
		super();
		setMapSize(mapSize);
		setPlayersNumber(playersNumber);
		setTurnsNumber(turnsNumber);
		setScreenSize(screenSize);
		setTracking(new PositionDouble());
		setMapGenerator(new MapGenerator());
		setMapSaver(new MapSaver());
		setMapLoader(new MapLoader());
		initializeTracking();
		initializeGame(leaders, mapNumber);
		
		BlockSize centerSize = new BlockSize(getScreenSize().getWidth()-(CORNER_WIDTH*2), getScreenSize().getHeight()-(CORNER_HEIGHT*2));
		setCentralBlock(new CentralBlock(centerSize, getGame(), getTracking(), menusBlock));
		add(centralBlock, 1, 1);
		
	}
	public void initializeGame(int[] leaders, int mapNumber) {
		try {
			Map map = null;
			if(mapNumber > 0) {
				map = getMapLoader().load(mapNumber);
				if(map != null) {
					switch(map.getDimensions()) {
					case(LITTLE_DIMENSIONS):setMapSize(0);
					break;
					case(MEDIUM_DIMENSIONS):setMapSize(1);
					break;
					case(WIDE_DIMENSIONS):setMapSize(2);
					break;
					}
				}
			}
			if(map == null) {
				map = getMapGenerator().generate(getMapSize());
				getMapSaver().saveMap(map);
				switch(map.getDimensions()) {
				case(LITTLE_DIMENSIONS):setMapSize(0);
				break;
				case(MEDIUM_DIMENSIONS):setMapSize(1);
				break;
				case(WIDE_DIMENSIONS):setMapSize(2);
				break;
				}
			}
			setGame(new Game(getPlayersNumber(), getTurnsNumber(), getMapSize(), map));
			getGame().setPlayers(initializePlayers(leaders));
			Turn turn = new Turn();
			turn.startTurns(getGame());
		} catch (InvalidMapSizeNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	public void initializeTracking() {
		BlockSize cornerSize = new BlockSize(CORNER_WIDTH,CORNER_HEIGHT);
		setNorthWestTracking(new TrackingCamera(-1, -1, getTracking(), cornerSize));
		setNorthTracking(new TrackingCamera(-1, 0, getTracking()));
		setNorthEastTracking(new TrackingCamera(-1, 1, getTracking()));
		setEastTracking(new TrackingCamera(0, 1, getTracking()));
		setSouthEastTracking(new TrackingCamera(1, 1, getTracking(), cornerSize));
		setSouthTracking(new TrackingCamera(1, 0, getTracking()));
		setSouthWestTracking(new TrackingCamera(1, -1, getTracking()));
		setWestTracking(new TrackingCamera(0, -1, getTracking()));
		
		add(getNorthWestTracking(), 0, 0);
		add(getNorthTracking(), 1, 0);
		add(getNorthEastTracking(), 2, 0);
		add(getEastTracking(), 2, 1);
		add(getSouthEastTracking(), 2, 2);
		add(getSouthTracking(), 1, 2);
		add(getSouthWestTracking(), 0, 2);
		add(getWestTracking(), 0, 1);
	}
	
	public Country[] initializePlayers(int[] number) throws InvalidMapSizeNumberException{

		Country[] players = new Country[getGame().getPlayersNumber()];
		for(int i = 0; i < players.length; i++) {
			players[i] = new Country(new Leader("name", "ability", number[i]), i+1);
		}
		if(getMapSize()==0) {
			switch(getGame().getPlayersNumber()) {
			case(4):giveSquareToPlayer(LITTLE_STARTING_CITY_MIN, LITTLE_STARTING_CITY_MAX, 4, players);
			case(3):giveSquareToPlayer(LITTLE_STARTING_CITY_MAX, LITTLE_STARTING_CITY_MIN, 3, players);
			case(2):giveSquareToPlayer(LITTLE_STARTING_CITY_MAX, LITTLE_STARTING_CITY_MAX, 2, players);
			case(1):giveSquareToPlayer(LITTLE_STARTING_CITY_MIN, LITTLE_STARTING_CITY_MIN, 1, players);
			break;
			default:throw new InvalidMapSizeNumberException(getMapSize());
			}
		}
		else if (getMapSize()==1) {
			switch(getGame().getPlayersNumber()) {
			case(4):giveSquareToPlayer(MEDIUM_STARTING_CITY_MIN, MEDIUM_STARTING_CITY_MAX, 4, players);
			case(3):giveSquareToPlayer(MEDIUM_STARTING_CITY_MAX, MEDIUM_STARTING_CITY_MIN, 3, players);
			case(2):giveSquareToPlayer(MEDIUM_STARTING_CITY_MAX, MEDIUM_STARTING_CITY_MAX, 2, players);
			case(1):giveSquareToPlayer(MEDIUM_STARTING_CITY_MIN, MEDIUM_STARTING_CITY_MIN, 1, players);
			break;
			default:throw new InvalidMapSizeNumberException(getMapSize());
			}
		}
		else if (getMapSize()==2) {
			switch(getGame().getPlayersNumber()) {
			case(4):giveSquareToPlayer(WIDE_STARTING_CITY_MIN, WIDE_STARTING_CITY_MAX, 4, players);
			case(3):giveSquareToPlayer(WIDE_STARTING_CITY_MAX, WIDE_STARTING_CITY_MIN, 3, players);
			case(2):giveSquareToPlayer(WIDE_STARTING_CITY_MAX, WIDE_STARTING_CITY_MAX, 2, players);
			case(1):giveSquareToPlayer(WIDE_STARTING_CITY_MIN, WIDE_STARTING_CITY_MIN, 1, players);
			break;
			default:throw new InvalidMapSizeNumberException(getMapSize());
			}
		}
		else {
			throw new InvalidMapSizeNumberException(getMapSize());
		}
		return players;
	}
	
	public void giveSquareToPlayer(int j, int i, int player, Country[] players) {
		//give a city to the player as starting square
		getGame().getMap().getSquares()[i][j].setFaction(player);
		
		players[player-1].getBuildings().put(new Position(j, i), getGame().getMap().getSquares()[i][j]);
		//give also the squares rounding the city
		if(j%2!=0) {
			getGame().getMap().getSquares()[i-1][j].setFaction(player);
			getGame().getMap().getSquares()[i][j+1].setFaction(player);
			getGame().getMap().getSquares()[i+1][j+1].setFaction(player);
			getGame().getMap().getSquares()[i+1][j].setFaction(player);
			getGame().getMap().getSquares()[i+1][j-1].setFaction(player);
			getGame().getMap().getSquares()[i][j-1].setFaction(player);
		}
		else {
			getGame().getMap().getSquares()[i-1][j].setFaction(player);
			getGame().getMap().getSquares()[i-1][j+1].setFaction(player);
			getGame().getMap().getSquares()[i][j+1].setFaction(player);
			getGame().getMap().getSquares()[i+1][j].setFaction(player);
			getGame().getMap().getSquares()[i][j-1].setFaction(player);
			getGame().getMap().getSquares()[i-1][j-1].setFaction(player);
		}
		players[player-1].setSquareNumber(7);
	}
	
	public BlockSize getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(BlockSize screenSize) {
		this.screenSize = screenSize;
	}
	public TrackingCamera getNorthWestTracking() {
		return northWestTracking;
	}
	public void setNorthWestTracking(TrackingCamera northWestTracking) {
		this.northWestTracking = northWestTracking;
	}
	public TrackingCamera getNorthTracking() {
		return northTracking;
	}
	public void setNorthTracking(TrackingCamera northTracking) {
		this.northTracking = northTracking;
	}
	public TrackingCamera getNorthEastTracking() {
		return northEastTracking;
	}
	public void setNorthEastTracking(TrackingCamera northEastTracking) {
		this.northEastTracking = northEastTracking;
	}
	public TrackingCamera getEastTracking() {
		return eastTracking;
	}
	public void setEastTracking(TrackingCamera eastTracking) {
		this.eastTracking = eastTracking;
	}
	public TrackingCamera getSouthEastTracking() {
		return southEastTracking;
	}
	public void setSouthEastTracking(TrackingCamera southEastTracking) {
		this.southEastTracking = southEastTracking;
	}
	public TrackingCamera getSouthTracking() {
		return southTracking;
	}
	public void setSouthTracking(TrackingCamera southTracking) {
		this.southTracking = southTracking;
	}
	public TrackingCamera getSouthWestTracking() {
		return southWestTracking;
	}
	public void setSouthWestTracking(TrackingCamera southWestTracking) {
		this.southWestTracking = southWestTracking;
	}
	public TrackingCamera getWestTracking() {
		return westTracking;
	}
	public void setWestTracking(TrackingCamera westTracking) {
		this.westTracking = westTracking;
	}
	public CentralBlock getCentralBlock() {
		return centralBlock;
	}
	public void setCentralBlock(CentralBlock centralBlock) {
		this.centralBlock = centralBlock;
	}
	public int getMapSize() {
		return mapSize;
	}
	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}
	public PositionDouble getTracking() {
		return tracking;
	}
	public void setTracking(PositionDouble tracking) {
		this.tracking = tracking;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public MapSaver getMapSaver() {
		return mapSaver;
	}
	public void setMapSaver(MapSaver mapSaver) {
		this.mapSaver = mapSaver;
	}
	public MapLoader getMapLoader() {
		return mapLoader;
	}
	public void setMapLoader(MapLoader mapLoader) {
		this.mapLoader = mapLoader;
	}
	public MapGenerator getMapGenerator() {
		return mapGenerator;
	}
	public void setMapGenerator(MapGenerator mapGenerator) {
		this.mapGenerator = mapGenerator;
	}
	public int getPlayersNumber() {
		return playersNumber;
	}
	public void setPlayersNumber(int playersNumber) {
		this.playersNumber = playersNumber;
	}
	public int getTurnsNumber() {
		return turnsNumber;
	}
	public void setTurnsNumber(int turnsNumber) {
		this.turnsNumber = turnsNumber;
	}
	
}

