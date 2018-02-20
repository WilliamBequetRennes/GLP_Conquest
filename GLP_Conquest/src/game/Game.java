package game;

import countries.Country;
import map.Map;
import squares.Square;

public class Game {

	private int turnNumber;
	private int playersNumber;
	private Country[] players;
	private int currentPlayer;
	private int mapSize;
	private Map map;
	private Square currentSquare;
	
	public Game(int turnNumber, int playersNumber, Country[] players, int currentPlayer, int mapSize, Map map, Square currentSquare) {
		setTurnNumber(turnNumber);
		setPlayersNumber(playersNumber);
		setPlayers(players);
		setCurrentPlayer(currentPlayer);
		setMap(map);
		setCurrentSquare(currentSquare);
	}
	
	public Game(int playersNumber, int mapSize, Map map) {
		this(0, playersNumber, new Country[playersNumber], 0, mapSize, map, map.getSquares()[0][0]);
	}
	public int getTurnNumber() {
		return turnNumber;
	}
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
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
	
	public Square getSquareType(Position position) {
		int jPosition = position.getJPosition();
		int iPosition = position.getIPosition();
		return squares[jPosition][iPosition];
	}
	
}
