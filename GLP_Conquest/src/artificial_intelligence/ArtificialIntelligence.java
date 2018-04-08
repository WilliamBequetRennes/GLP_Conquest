package artificial_intelligence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import countries.Country;
import data.Position;
import exceptions.AttributeException;
import exceptions.InvalidUnitNumberException;
import exceptions.OutOfRangeException;
import fight.Damage;
import fight.Fight;
import game.Game;
import game.LevelUp;
import game.UnitPurchase;
import movement.Graph;
import movement.IndexPosition;
import movement.Movement;
import squares.Square;
import units.Unit;

public class ArtificialIntelligence {
	private Game game;
	private int playersTurn;
	private Country country;
	private HashMap<Position, Unit> waiting;
	
	public ArtificialIntelligence(int turnNumber, Game game) {
		setGame(game);
		setPlayersTurn(turnNumber);
		setCountry(game.getPlayers()[turnNumber]);
		setWaiting(new HashMap<Position,Unit>());
	}
	
	//Mettre à jour avec les modifications du package movement
	public void unitsManagement() throws AttributeException, OutOfRangeException {
		HashMap<Position,Unit> units = this.country.getUnits();
		Set<Entry<Position,Unit>> unitsSet = units.entrySet();
		Iterator<Entry<Position,Unit>> unitsIterator = unitsSet.iterator();
		Unit unit;
		Unit enemyUnit;
		Movement movement;
		Graph graph;
		IndexPosition position;
		Damage preview;
		HashMap<IndexPosition, Integer> priority = new HashMap<IndexPosition, Integer>();
		HashMap<Position,Unit> alone = new HashMap<Position, Unit>();
		while(unitsIterator.hasNext()) {
			unit = (Unit) unitsIterator.next();
			movement = new Movement(unit);
			graph = movement.getGraph();
			ArrayList<IndexPosition> buildings = graph.containsBuilding(getGame().getMap());
			Iterator<IndexPosition> buildingsIterator = buildings.iterator();
			int weight = 0;
			while(buildingsIterator.hasNext()) {
				position = buildingsIterator.next();
				if(!game.getMap().getSquareType(position).getUnit()) {
					weight = 10;
					if(game.getMap().getSquareType(position).getType() == 9) {
						weight = 20;
					}
				}
				else {
					int faction = game.getMap().getSquares()[position.getIPosition()][position.getJPosition()].getFaction();
					Fight fight = new Fight();
					preview = fight.calculate(unit, game.getPlayers()[faction-1].getUnits().get(position),getGame().getMap());
					if(preview.getDefenderMaxRemainingHealth() == 0) {
						weight = 11;
					}
					if (preview.getMinRevengeDamage() > 0) {
						weight = 9;
					}
					else {
						weight = 2;
					}
				}
				priority.put(position, weight);
			}
			ArrayList<Unit> enemyUnits = graph.containsUnit(getGame().getMap(),getGame());
			Iterator<Unit> enemyUnitsIterator = enemyUnits.iterator();
			while(enemyUnitsIterator.hasNext()) {
				enemyUnit = enemyUnitsIterator.next();
				if(!priority.containsKey(enemyUnit.getPosition())) {
					Fight fight = new Fight();
					preview = fight.calculate(unit, enemyUnit,getGame().getMap());
					if(preview.getDefenderMaxRemainingHealth() == 0) {
						weight = 8;
					}
					if (preview.getMinRevengeDamage() > 0) {
						weight = 5;
					}
					else {
						weight = 1;
					}
					priority.put(new IndexPosition(enemyUnit.getPosition().getIPosition(),enemyUnit.getPosition().getJPosition()), weight);
				}
			}
			if (priority.isEmpty()) {
				alone.put(unit.getPosition(), unit);
			}
			else {
				IndexPosition[] choice = max3(priority);
				Movement move = new Movement(unit);
				int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
				if (randomNum<75) {
					
					move.goTo(choice[0],choice[0].getLocalPath(),getGame().getMap(),getGame());
				}
				else if(randomNum>=75 && randomNum<95) {
					move.goTo(choice[1],choice[1].getLocalPath(),getGame().getMap(),getGame());
				}
				else {
					move.goTo(choice[2],choice[2].getLocalPath(),getGame().getMap(),getGame());
				}
			}
		}
		Set<Entry<Position,Unit>> aloneSet = alone.entrySet();
		Iterator<Entry<Position,Unit>> aloneIterator = aloneSet.iterator();
		while(aloneIterator.hasNext()) {
			Map.Entry<Position, Unit> lostUnits = aloneIterator.next();
			Unit lostUnit = lostUnits.getValue();
			Graph looking4Aim = new Graph(new IndexPosition(lostUnit.getPosition().getIPosition(),lostUnit.getPosition().getJPosition()));
			ArrayList<IndexPosition> aim = looking4Aim.getGraph();
			Iterator<IndexPosition> lostIterator = aim.iterator();
			boolean foundAim = false;
			IndexPosition lostPosition = null;
			while(lostIterator.hasNext() && !foundAim) {
				lostPosition = lostIterator.next();
				if (game.getMap().getSquareType(lostPosition).getFaction() != lostUnit.getFaction()) {
					foundAim = true;
					Movement move = new Movement(lostUnit);
					move.goTo(lostPosition,lostPosition.getLocalPath(),getGame().getMap(),getGame());
				}
			}
			if (foundAim == false) {
				Movement move = new Movement(lostUnit);
				move.goTo(lostPosition,lostPosition.getLocalPath(),getGame().getMap(),getGame());
			}
		}
	}
	
	public IndexPosition[] max3(HashMap<IndexPosition,Integer> priority) {
		IndexPosition[] optimize = {};
		Set<Entry<IndexPosition,Integer>> optiSet = priority.entrySet();
		Iterator<Entry<IndexPosition,Integer>> iterator = optiSet.iterator();
		int weight1 = 0;
		IndexPosition position1 = null;
		int weight2 = 0;
		IndexPosition position2 = null;
		int weight3 = 0;
		IndexPosition position3 = null;
		while(iterator.hasNext()) {
			Map.Entry<IndexPosition,Integer> iteration = iterator.next();
			
			if(iteration.getValue()>Math.min(weight1, Math.min(weight2, weight3))) {
				if(weight1<weight2 && weight1<weight3) {
					weight1 = iteration.getValue();
					position1 = iteration.getKey();
				}
				else if(weight2<weight1 && weight2<weight3) {
					weight2 = iteration.getValue();
					position2 = iteration.getKey();
				}
				else {
					weight3 = iteration.getValue();
					position3 = iteration.getKey();
				}
			}
		}
		if(weight3 == 0) {
			if(weight2 == 0) {
				weight3 = weight1;
				weight2 = weight1;
				position3 = position1;
				position2 = position1;
			}
			else {
				weight3 = weight2;
				position3 = position2;
			}
		}
		if(weight1<weight2 && weight1<weight3) {
			optimize[2] = position1;
			if(weight2<weight3) {
				optimize[1] = position2;
				optimize[0] = position3;
			}
			else {
				optimize[1] = position3;
				optimize[0] = position2;
			}
		}
		else if(weight2<weight1 && weight2<weight3) {
			optimize[2] = position2;
			if(weight1<weight3) {
				optimize[1] = position1;
				optimize[0] = position3;
			}
			else {
				optimize[1] = position3;
				optimize[0] = position1;
			}
		}
		else {
			optimize[2] = position3;
			if(weight2<weight1) {
				optimize[1] = position2;
				optimize[0] = position1;
			}
			else {
				optimize[1] = position1;
				optimize[0] = position2;
			}
		}
		return optimize;
	}
	
	public void buildingManagement(Game game) {
		HashMap<Position,Square> buildings = getCountry().getBuildings();
		Set<Entry<Position,Square>> buildingsSet = buildings.entrySet();
		LevelUp up = new LevelUp();
		for(Entry<Position,Square> building : buildingsSet) {
			if(building.getValue().getLevel()<up.getMaxLevel()) {
				float money = getCountry().getResources().getMoney();
				float neededMoney = up.getCostLevel()[building.getValue().getLevel()];
				if(neededMoney<money) {
					game.setCurrentSquare(building.getValue());
					up.up(game);
				}
			}
		}	
	}
	
	public void unitsCreator(Game game) throws InvalidUnitNumberException {
		HashMap<Position,Square> buildings = getCountry().getBuildings();
		Set<Entry<Position,Square>> buildingsSet = buildings.entrySet();
		ArrayList<Square> cities = new ArrayList<Square>();
		UnitPurchase purchase = new UnitPurchase();
		for(Entry<Position,Square> building : buildingsSet) {
			if(building.getValue().getType() == 9) {
				cities.add(building.getValue());
			}
		}
		for(Square city : cities) {
			game.setCurrentSquare(city);
			int randomNum = ThreadLocalRandom.current().nextInt(0, 6);
			purchase.purchase(game, randomNum);			
		}
	}
	
	public void ecoTurnOrder(Game game) throws AttributeException, OutOfRangeException, InvalidUnitNumberException {
		unitsManagement();
		buildingManagement(game);
		unitsCreator(game);
	}
	public void aggroTurnOrder(Game game) throws AttributeException, OutOfRangeException, InvalidUnitNumberException {
		unitsManagement();
		unitsCreator(game);
		buildingManagement(game);
	}
	public void unknownTurnOrder(Game game) throws AttributeException, OutOfRangeException, InvalidUnitNumberException {
		unitsManagement();
		int randomNum = ThreadLocalRandom.current().nextInt(1, 2);
		if(randomNum==1) {
			buildingManagement(game);
			unitsCreator(game);
		}
		else {
			unitsCreator(game);
			buildingManagement(game);
	
		}
	}
	public void setWaiting(HashMap<Position, Unit> hashMap) {
		this.waiting = hashMap;
	}
	
	public HashMap<Position,Unit> getWaiting(){
		return this.waiting;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Country getCountry() {
		return this.country;
	}

	public void setPlayersTurn(int turnNumber) {
		this.playersTurn = turnNumber;
	}
	
	public int getPlayersTurn() {
		return this.playersTurn;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return this.game;
	}
	
}
