package save;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;

import com.sun.javafx.sg.prism.GrowableDataBuffer;

import countries.CaptainIgloo;
import countries.Country;
import countries.DonaldTrump;
import countries.FrancoisHollande;
import countries.GordonRamsay;
import countries.Governator;
import countries.Leader;
import countries.VladimirPutin;
import data.Position;
import data.Resources;
import exceptions.LeaderException;
import exceptions.UnitException;
import game.Game;
import map.Map;
import map.MapLoader;
import squares.Square;
import units.Assault;
import units.Battleship;
import units.Bfgninethousand;
import units.Destroyer;
import units.Obfourtytwo;
import units.Sniper;
import units.Tank;
import units.Transport;
import units.Turret;
import units.Unit;

public class Save {
	private File save;
	private Game game;
	
	public Save(String string) {
		if(!string.endsWith(".txt")) {
			string += ".txt";
		}
		this.save = new File(string);
		setGame(new Game());
	}
	
	public Save(File file) {
		setSave(file);
		setGame(new Game());
	}
	
	public void saveGame(Game game) throws IOException {
		setGame(game);
		BufferedWriter writer = new BufferedWriter(new FileWriter(this.save));
		int currentPlayer = getGame().getCurrentPlayer();
		int playersNumber = getGame().getPlayersNumber();
		writer.write("<");
		writer.write(getGame().getMap().getNumber()+"");
		writer.write("#");
		writer.write(getGame().getCurrentTurn()+"");
		writer.write("#");
		writer.write(getGame().getTurnsNumber()+"");
		writer.write("#");
		writer.write(playersNumber+"");
		writer.write("#");
		//Get Map Code
		writer.write(getGame().getCurrentSquare().getPosition().getIPosition()+"");
		writer.write("#");
		writer.write(getGame().getCurrentSquare().getPosition().getJPosition()+"");
		writer.write("#");
		writer.write(currentPlayer+"");
		writer.write(">");

		for (int i=0; i<playersNumber; i++) {
			writer.write("<");
			Country country = getGame().getPlayers()[i];
			writer.write(i+"");
			writer.write("#");
			writer.write(country.getLeader().getNumber()+"");
			writer.write("#");
			writer.write(Float.toString(country.getResources().getMoney()));
			writer.write("#");
			writer.write(Float.toString(country.getResources().getFood()));
			writer.write("#");
			writer.write(Float.toString(country.getResources().getOil()));
			writer.write("#");
			writer.write(Float.toString(country.getResources().getElectricity()));
			writer.write("#");
			writer.write(Float.toString(country.getGains().getMoney()));
			writer.write("#");
			writer.write(Float.toString(country.getGains().getFood()));
			writer.write("#");
			writer.write(Float.toString(country.getGains().getOil()));
			writer.write("#");
			writer.write(Float.toString(country.getGains().getElectricity()));
			writer.write("#");
			writer.write(Float.toString(country.getSpents().getMoney()));
			writer.write("#");
			writer.write(Float.toString(country.getSpents().getFood()));
			writer.write("#");
			writer.write(Float.toString(country.getSpents().getOil()));
			writer.write("#");
			writer.write(Float.toString(country.getSpents().getElectricity()));
			writer.write("#");
			writer.write(country.getSquareNumber()+"");
			
			HashMap<Position, Square> buildings = country.getBuildings();
			Set<Entry<Position, Square>> buildingsSet = buildings.entrySet();
			Iterator<Entry<Position, Square>> iteratorSquare = buildingsSet.iterator();
			while(iteratorSquare.hasNext()) {
				writer.write("#");
				Entry<Position, Square> building = (Entry<Position, Square>) iteratorSquare.next();
				Position position = (Position) building.getKey();
				writer.write(position.getIPosition()+"");
				writer.write("#");
				writer.write(position.getJPosition()+"");
			}
			writer.write("&");
			
			HashMap<Position, Unit> units = country.getUnits();
			Set<Entry<Position, Unit>> unitsSet = units.entrySet();
			Iterator<Entry<Position, Unit>> iteratorUnit = unitsSet.iterator();
			while(iteratorUnit.hasNext()) {
				Entry<Position, Unit> unit = (Entry<Position, Unit>) iteratorUnit.next();
				Position position = (Position) unit.getKey();
				Unit warResource = (Unit) unit.getValue();
				writer.write("#");
				writer.write(position.getIPosition()+"");
				writer.write("#");
				writer.write(position.getJPosition()+"");
				writer.write("#");
				writer.write(warResource.getType()+"");
				writer.write("#");
				writer.write(Float.toString(warResource.getCurrentHealth()));
				writer.write("#");
				writer.write(Float.toString(warResource.getMovement()));
			}
			writer.write(">");
		}
		writer.write("<");
		Map map = getGame().getMap();
		for(int i = 0; i < getGame().getMapSize(); i++) {
			for (int j = 0; j < getGame().getMapSize(); j++) {
				writer.write(map.getSquares()[i][j].getFaction()+"");
			}
		}
		writer.newLine();
		writer.write(">");
		writer.close();
	}
	
	public Game loadGame() throws IOException,LeaderException,UnitException {
		FileReader reader = new FileReader(getSave());
		char lastChar = (char) reader.read();
		String storage = "";
		while(lastChar != '#'){
			if (lastChar<='9' &&  lastChar>='0') {
				storage += lastChar;
			}
			lastChar = (char) reader.read();
		}
		MapLoader mapLoader = new MapLoader();
		getGame().setMap(mapLoader.load((Integer.parseInt(storage))));
		storage="";
		lastChar = (char)reader.read();
		while(lastChar != '#'){
			if (lastChar<='9' &&  lastChar>='0') {
				storage += lastChar;
			}
			lastChar = (char) reader.read();
		}
		getGame().setCurrentTurn(Integer.parseInt(storage));
		storage="";
		lastChar = (char)reader.read();
		while(lastChar != '#'){
			if (lastChar<='9' &&  lastChar>='0') {
				storage += lastChar;
			}
			lastChar = (char) reader.read();
		}
		getGame().setTurnsNumber(Integer.parseInt(storage));
		storage="";
		lastChar = (char)reader.read();
		while(lastChar != '#'){
			if (lastChar<='9' &&  lastChar>='0') {
				storage += lastChar;
			}
			lastChar = (char) reader.read();
		}
		getGame().setPlayersNumber(Integer.parseInt(storage));
		storage="";
		lastChar = (char)reader.read();
		while(lastChar != '#'){
			if (lastChar<='9' &&  lastChar>='0') {
				storage += lastChar;
			}
			lastChar = (char) reader.read();
		}
		Position currentSquare = new Position(0,0);
		currentSquare.setIPosition(Integer.parseInt(storage));
		storage="";
		lastChar = (char)reader.read();
		while(lastChar != '#'){
			if (lastChar<='9' &&  lastChar>='0') {
				storage += lastChar;
			}
			lastChar = (char) reader.read();
		}
		currentSquare.setJPosition(Integer.parseInt(storage));
		storage="";
		lastChar = (char)reader.read();
		while(lastChar!='#' && lastChar!='>' && lastChar!='<') {
			if (lastChar<='9' &&  lastChar>='0') {
				storage += lastChar;
			}
			lastChar = (char) reader.read();
			lastChar = (char) reader.read();
		}
		getGame().setCurrentPlayer(Integer.parseInt(storage));
		Country country1 = new Country(null, 1, null, null, null, 0, new HashMap<Position, Square>(), new HashMap<Position, Unit>());
		Country country2 = new Country(null, 2, null, null, null, 0, new HashMap<Position, Square>(), new HashMap<Position, Unit>());
		Country country3 = new Country(null, 3, null, null, null, 0, new HashMap<Position, Square>(), new HashMap<Position, Unit>());
		Country country4 = new Country(null, 4, null, null, null, 0, new HashMap<Position, Square>(), new HashMap<Position, Unit>());
		Country[] countries = {country1, country2, country3, country4};
		Country currentCountry;
		for (int i = 0; i < getGame().getPlayersNumber(); i++) {
			currentCountry = countries[i];
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.setPlayer(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			lastChar = (char) reader.read();
			int leadersNumber = Integer.parseInt(storage);
			switch(leadersNumber) {
			case(0) : currentCountry.setLeader(new CaptainIgloo());
			break;
			case(1) : currentCountry.setLeader(new DonaldTrump());
			break;
			case(2) : currentCountry.setLeader(new FrancoisHollande());
			break;
			case(3) : currentCountry.setLeader(new GordonRamsay());
			break;
			case(4) : currentCountry.setLeader(new Governator());
			break;
			case(5) : currentCountry.setLeader(new VladimirPutin());
			break;
			default : 
				LeaderException except = new LeaderException(leadersNumber);
				throw except;
			}
			currentCountry.setResources(new Resources(0,0,0,0));
			currentCountry.setGains(new Resources(0,0,0,0));
			currentCountry.setSpents(new Resources(0,0,0,0));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getResources().setMoney(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getResources().setFood(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getResources().setOil(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getResources().setElectricity(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getGains().setMoney(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getGains().setFood(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getGains().setOil(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getGains().setElectricity(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getSpents().setMoney(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getSpents().setFood(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getSpents().setOil(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.getSpents().setElectricity(Integer.parseInt(storage));
			lastChar = (char) reader.read();
			storage="";
			while(lastChar != '#'){
				if (lastChar<='9' &&  lastChar>='0') {
					storage += lastChar;
				}
				lastChar = (char) reader.read();
			}
			currentCountry.setSquareNumber(Integer.parseInt(storage));
			currentCountry.setBuildings(new HashMap<Position, Square>());
			while(lastChar != '&') {
				lastChar = (char) reader.read();
				storage="";
				while(lastChar != '#'){
					if (lastChar<='9' &&  lastChar>='0') {
						storage += lastChar;
					}
					lastChar = (char) reader.read();
				}
				int iPosition = Integer.parseInt(storage);
				lastChar = (char) reader.read();
				storage="";
				while(lastChar != '&'){
					if (lastChar<='9' &&  lastChar>='0') {
						storage += lastChar;
					}
					lastChar = (char) reader.read();
				}
				int jPosition = Integer.parseInt(storage);
				Position position = new Position(iPosition, jPosition);
				System.out.println(getGame().toString());
				System.out.println(iPosition+" : "+jPosition);
				Square square = getGame().getMap().getSquares()[position.getIPosition()][position.getJPosition()];
				currentCountry.getBuildings().put(position,  square);
			}
			currentCountry.setUnits(new HashMap<Position, Unit>());
			lastChar = (char) reader.read();
			while(lastChar != '>') {
				lastChar = (char) reader.read();
				storage="";
				while(lastChar != '#'){
					if (lastChar<='9' &&  lastChar>='0') {
						storage += lastChar;
					}
					lastChar = (char) reader.read();
				}
				int iPosition = Integer.parseInt(storage);
				lastChar = (char) reader.read();
				storage="";
				while(lastChar != '#'){
					if (lastChar<='9' &&  lastChar>='0') {
						storage += lastChar;
					}
					lastChar = (char) reader.read();
				}
				int jPosition = Integer.parseInt(storage);
				Position position = new Position(iPosition, jPosition);
				Unit currentUnit;
				lastChar = (char) reader.read();
				storage="";
				while(lastChar != '#'){
					if (lastChar<='9' &&  lastChar>='0') {
						storage += lastChar;
					}
					lastChar = (char) reader.read();
				}
				int unitsType = Integer.parseInt(storage);
				switch(unitsType) {
				case 0 : currentUnit = new Assault(position, i);
				break;
				case 1 : currentUnit = new Sniper(position, i);
				break;
				case 2 : currentUnit = new Obfourtytwo(position, i);
				break;
				case 3 : currentUnit = new Bfgninethousand(position, i);
				break;
				case 4 : currentUnit = new Tank(position, i);
				break;
				case 5 : currentUnit = new Turret(position, i);
				break;
				case 6 : currentUnit = new Destroyer(position, i);
				break;
				case 7 : currentUnit = new Battleship(position, i);
				break;
				case 8 : currentUnit = new Transport(position, i);
				break;
				default : 
					UnitException except = new UnitException(unitsType);
					throw except;
				}
				lastChar = (char) reader.read();
				storage="";
				while(lastChar != '#'){
					if (lastChar<='9' &&  lastChar>='0') {
						storage += lastChar;
					}
					lastChar = (char) reader.read();
				}
				currentUnit.setCurrentHealth(Integer.parseInt(storage));
				lastChar = (char) reader.read();
				storage="";
				while(lastChar != '#'){
					if (lastChar<='9' &&  lastChar>='0') {
						storage += lastChar;
					}
					lastChar = (char) reader.read();
				}
				currentUnit.setMovement(Integer.parseInt(storage));
				currentCountry.getUnits().put(position,  currentUnit);
			}
		}
		lastChar = (char) reader.read();
		Map map = getGame().getMap();
		Square[][] squares = map.getSquares();
		lastChar = (char) reader.read();
		for (int i = 0; i<getGame().getMapSize();i++) {
			for (int j = 0; j<getGame().getMapSize();j++) {
				squares[i][j].setFaction(reader.read());
			}
		}
		
		reader.close();
		
		return getGame();
	}
	
	
	public void setGame(Game game) {
		this.game = game;
	}
	public void setSave(File file) {
		this.save = file;
	}

	public File getSave() {
		return save;
	}

	public Game getGame() {
		return game;
	}
}
