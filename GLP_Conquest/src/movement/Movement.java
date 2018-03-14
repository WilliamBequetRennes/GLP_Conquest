package movement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import countries.Country;
import datas.Position;
import exceptions.AttributeException;
import exceptions.OutOfRangeException;
import game.Game;
import map.Map;
import units.Unit;

public class Movement {
	private Unit unit;
	private Graph graph;
	private Map map;
	private Game game;
	
	public Movement(Unit unit, Game game) {
		setUnit(unit);
		IndexPosition position = (IndexPosition) unit.getPosition();
		setGraph(position);
		setMap(map);
		setGame(game);
	}
	
	public boolean parity(int YPosition) {
		boolean test;
		if (YPosition % 2 == 0) {
			test =  true;
		}
		else {
			test = false;
		}
		return test;	
	}
	
	public ArrayList<Position> adjacentSquare(Position interPosition){
		ArrayList<Position> adjacent = new ArrayList<Position>();
		int jPosition = interPosition.getJPosition();
		int iPosition = interPosition.getIPosition();
		if (parity(interPosition.getIPosition())){
			if(jPosition > 0) {
				adjacent.add(new IndexPosition(jPosition-1,iPosition));
				if (iPosition > 0) {
					adjacent.add(new IndexPosition(jPosition-1, iPosition-1));
				}
				if (iPosition < map.getDimensions()) {
					adjacent.add(new IndexPosition(jPosition-1,iPosition+1));
				}
			}
			if (iPosition > 0) {
				adjacent.add(new IndexPosition(jPosition, iPosition-1));
			}
			if (iPosition < map.getDimensions()) {
				adjacent.add(new IndexPosition(jPosition,iPosition+1));
			}
			if (jPosition < map.getDimensions()) {
				adjacent.add(new IndexPosition(jPosition+1,iPosition));
			}
		}
		else {
			if(jPosition > 0) {
				adjacent.add(new IndexPosition(jPosition-1,iPosition));
			}
			if (iPosition > 0) {
				adjacent.add(new IndexPosition(jPosition, iPosition-1));
			}
			if (iPosition < map.getDimensions()) {
				adjacent.add(new IndexPosition(jPosition,iPosition+1));
			}
			if (jPosition < map.getDimensions()) {
				adjacent.add(new IndexPosition(jPosition+1,iPosition));
				if (iPosition > 0) {
					adjacent.add(new IndexPosition(jPosition+1, iPosition-1));
				}
				if (iPosition < map.getDimensions()) {
					adjacent.add(new IndexPosition(jPosition+1,iPosition+1));
				}
			}
		}
		return adjacent; 
	}
	
	public void availableMovement(){
		
		Iterator<IndexPosition> positionIterator0 = graph.getGraph().iterator();
		float previousCost = 0;
		ArrayList<Position> previousPath = new ArrayList<Position>();
		IndexPosition position = (IndexPosition) unit.getPosition();
		int movement = (int) unit.getMovement();
		position.setLocalCost(0);
		
		//For each position
		while (positionIterator0.hasNext()) {
			IndexPosition testedPosition0 = positionIterator0.next();
			ArrayList<Position> adjacent1 = adjacentSquare(position);
			//if it is next to the unit's position
			if (adjacent1.contains(testedPosition0)) {
				//set datas
				testedPosition0.setLocalCost(map.getSquareType(testedPosition0).getMoveCost());
				testedPosition0.addLocalPath(testedPosition0);
			}
			else {
				
				Iterator<Position>positionIterator1 = adjacent1.iterator();
				
				// For each adjacent position
				while (positionIterator1.hasNext()) {
					Position testedPosition1 = positionIterator1.next();
					previousCost += map.getSquareType(testedPosition1).getMoveCost();
					ArrayList<Position> adjacent2 = adjacentSquare(testedPosition0);
					if (adjacent2.contains(testedPosition0)) {
						if(map.getSquareType(testedPosition0).getMoveCost()+previousCost<testedPosition0.getLocalCost()) {
							testedPosition0.setLocalCost(map.getSquareType(testedPosition0).getMoveCost()+previousCost);
							testedPosition0.setLocalPath(previousPath);
							testedPosition0.addLocalPath(testedPosition0);
						}
					}
					else {
						Iterator<Position>positionIterator2 = adjacent2.iterator();
						
						// For each adjacent position
						while (positionIterator2.hasNext()) {
							Position testedPosition2 = positionIterator2.next();
							previousCost += map.getSquareType(testedPosition2).getMoveCost();
							ArrayList<Position> adjacent3 = adjacentSquare(testedPosition0);
							if (adjacent3.contains(testedPosition0)) {
								if(map.getSquareType(testedPosition0).getMoveCost()+previousCost<testedPosition0.getLocalCost()) {
									testedPosition0.setLocalCost(map.getSquareType(testedPosition0).getMoveCost()+previousCost);
									testedPosition0.setLocalPath(previousPath);
									testedPosition0.addLocalPath(testedPosition0);
								}
							}
						}
					}
				}
			}
		}
		Iterator<IndexPosition> sortIterator = graph.getGraph().iterator();
		while (sortIterator.hasNext()) {
			IndexPosition sortPosition = sortIterator.next();
			if (sortPosition.getLocalCost() > movement) {
				graph.getGraph().remove(sortPosition);
			}
		}
	}
	
	public int goTo(IndexPosition position) throws OutOfRangeException, AttributeException {
		// Event : 
		//0 : movement
		//1 : battle
		//2 : conquest
		int event = 0;
		Position interPosition;
		Position testedPosition;
		if (graph.getGraph().contains(position)) {
			if (map.getSquareType(position).getFaction() != map.getSquareType(unit.getPosition()).getFaction()) {
				ArrayList<Position> localPath = position.getLocalPath();
				Iterator<Position> iterator = localPath.iterator();
				while (iterator.hasNext()) {
					interPosition = iterator.next();
					ArrayList<Position> adjacent = adjacentSquare(interPosition);
					Iterator<Position> interIterator = adjacent.iterator();
					while (interIterator.hasNext()) {
						testedPosition = interIterator.next();
						if (map.getSquareType(testedPosition).getFaction() != map.getSquareType(unit.getPosition()).getFaction() && !map.getSquareType(testedPosition).getUnit()) {
							map.getSquareType(testedPosition).setFaction(unit.getFaction());
						}
					}
				}
				float movement = unit.getMovement()-position.getLocalCost();
				unit.setMovement(movement);
				if (map.getSquareType(position).getUnit()) {
					event = 1;
					Unit defenderUnit = game.getUnits().get(position);
					fight(unit, defenderUnit);
					return event;
				}
				else {
					event  = 2;
					return event;
				}
			}
			else {
				unit.setPosition(position);
				float movement = unit.getMovement()-position.getLocalCost();
				unit.setMovement(movement);
				return event;
			}
		}
		else {
			OutOfRangeException except = new OutOfRangeException();
			throw except; 
		}
	}
	
	public void fight(Unit attackUnit, Unit defenseUnit) throws AttributeException {

		double attributeBonus = 1;
		
		Country[] countries = game.getPlayers();
		Country attackerCountry = countries[game.getCurrentPlayer()];
		
		int attackerAttack = attackUnit.getAttack();
		int attackerDefense = attackUnit.getDefense();
		float attackerHealth = attackUnit.getCurrentHealth();
		int attackerRange = attackUnit.getRange();
		int attackerAttribute = attackUnit.getAttribute();
//		String attackerBoost = attackUnit.getFaction().getLeader();
		float attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getAttack(); 
		
		int defenderAttack = defenseUnit.getAttack();
		int defenderDefense = defenseUnit.getDefense();
		float defenderHealth = defenseUnit.getCurrentHealth();
		int defenderRange = defenseUnit.getRange();
		int defenderAttribute = defenseUnit.getAttribute();
//		String defenderBoost = defenseUnit.getFaction().getLeader();
		float defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getDefense();
		Position defenderPosition = defenseUnit.getPosition();
		
		double damage = attackerAttack*/*attackerBoost**/attackerBonus*attackerHealth/100 - defenderDefense*/*defenderBoost**/defenderBonus;
		
		attributeBonus = attributeBonus(attackerAttribute, defenderAttribute);
		damage = damage*attributeBonus;
		
		defenderHealth = (float) (defenderHealth-damage);
		defenseUnit.setCurrentHealth(defenderHealth);
		
		if (defenderHealth > 0) {
			if(defenderRange == attackerRange) {
				attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getDefense(); 
				defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getAttack();
				damage = defenderAttack*/*defenderBoost**/defenderBonus*defenderHealth/100 - attackerDefense*/*attackerBoost**/attackerBonus;
				damage = damage*1/attributeBonus;
				
				attackerHealth = (float) (attackerHealth - damage);
				attackUnit.setCurrentHealth(attackerHealth);
				
				if(attackerHealth < 0) {
					
					HashMap<Position, Unit> units = attackerCountry.getUnits();
					Position position = unit.getPosition();
					units.remove(position,unit);
					attackerCountry.setUnits(units);
				}
			}
		}
		else {
			boolean test = false;
			int i = 0;
			while(test == false) {
				Country country = countries[i] ;
				if(country.getUnits().containsKey(defenderPosition)) {
					HashMap<Position, Unit> units = country.getUnits();
					units.remove(defenderPosition, defenseUnit);
					country.setUnits(units);
				}
			}
		}		
	}
	
	public double attributeBonus(int attribute1, int attribute2) throws AttributeException{
		double bonus = 1;
		if (attribute1 == 1) {
			if (attribute2 == 2) {
				bonus = 1.5;
			}
			else if (attribute2 == 3) {
				bonus = 0.75;
			}
			else {
				AttributeException except = new AttributeException();
				throw except;
			}
		}
		else if (attribute1 == 2) {
			if (attribute2 == 3) {
				bonus = 1.5;
			}
			else if (attribute2 == 1) {
				bonus = 0.75;
			}
			else {
				AttributeException except = new AttributeException();
				throw except;
			}
		}
		else if (attribute1 == 3) {
			if (attribute2 == 1) {
				bonus = 1.5;
			}
			else if (attribute2 == 2) {
				bonus = 0.75;
			}
			else {
				AttributeException except = new AttributeException();
				throw except;
			}
		}
		else {
			AttributeException except = new AttributeException();
			throw except;
		}
		return bonus;
	}
	
	public void setUnit(Unit unit) {
		this.unit= unit;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public void setGraph(IndexPosition position) {
		this.graph = new Graph(position);
	}
	
	public Graph getGraph() {
		return graph;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public Map getMap() {
		return map;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
}
