package game;

import java.util.HashMap;

import countries.Country;
import datas.Position;
import map.Map;
import squares.Square;
import units.Unit;

public class Game {

	private int currentTurn;
	private int turnsNumber;
	private int playersNumber;
	private Country[] players;
	private int currentPlayer;
	private int mapSize;
	private Map map;
	private Square currentSquare;
	private HashMap<Position,Unit> units;
	
	public Game(int currentTurn, int turnsNumber, int playersNumber, Country[] players, int currentPlayer, int mapSize,
			Map map, Square currentSquare, HashMap<Position,Unit> units) {
		setCurrentTurn(currentTurn);
		setTurnsNumber(turnsNumber);
		setPlayersNumber(playersNumber);
		setPlayers(players);
		setCurrentPlayer(currentPlayer);
		setMapSize(mapSize);
		setMap(map);
		setCurrentSquare(currentSquare);
		setUnits(units);
	}
	
	public Game(int playersNumber, int turnsNumber, int mapSize, Map map) {
		this(1, turnsNumber, playersNumber, new Country[playersNumber], 1, mapSize, map, map.getSquares()[0][0], new HashMap<Position,Unit>());
	}
	public int getCurrentTurn() {
		return currentTurn;
	}
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}
	public int getPlayersNumber() {
		return playersNumber;
	}

	public void setPlayersNumber(int playersNumber) {
		this.playersNumber = playersNumber;
	}

	public Country[] getPlayers() {
		return players;
	}
	public void setPlayers(Country[] players) {
		this.players = players;
	}
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	public int getMapSize() {
		return mapSize;
	}
	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public Square getCurrentSquare() {
		return currentSquare;
	}
	public void setCurrentSquare(Square currentSquare) {
		this.currentSquare = currentSquare;
	}

	public HashMap<Position, Unit> getUnits() {
		return units;
	}

	public void setUnits(HashMap<Position, Unit> units) {
		this.units = units;
	}

	public int getTurnsNumber() {
		return turnsNumber;
	}

	public void setTurnsNumber(int turnsNumber) {
		this.turnsNumber = turnsNumber;
	}
	
}
