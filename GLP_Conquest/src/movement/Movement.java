package movement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import countries.Country;
import data.Position;
import exceptions.AttributeException;
import exceptions.OutOfRangeException;
import game.Game;
import map.Map;
import units.Unit;

public class Movement {
	private Unit unit;
	private Graph graph;
	
	public Movement(Unit unit) {
		setUnit(unit);
		IndexPosition position = new IndexPosition(unit.getPosition());
		setGraph(new Graph(position));
	}
	
	public Movement(Unit unit, Graph graph) {
		setUnit(unit);
		setGraph(graph);
	}
	
	public Graph scanArea(Map map){
		availableMovement(map);
		return getGraph();
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
	
	public ArrayList<Position> adjacentSquare(Position interPosition, Map map){
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
	
	public void availableMovement(Map map){
		
		Iterator<IndexPosition> positionIterator0 = getGraph().getGraph().iterator();
		float previousCost = 0;
		ArrayList<Position> previousPath = new ArrayList<Position>();
		IndexPosition position = new IndexPosition(getUnit().getPosition());
		int movement = (int) getUnit().getMovement();
		position.setLocalCost(0);
		
		//For each position
		while (positionIterator0.hasNext()) {
			IndexPosition testedPosition0 = positionIterator0.next();
			ArrayList<Position> adjacent1 = adjacentSquare(position, map);
			//if it is next to the unit's position
			if (adjacent1.contains(testedPosition0)) {
				//set data
				testedPosition0.setLocalCost(map.getSquareType(testedPosition0).getMoveCost());
				testedPosition0.addLocalPath(testedPosition0);
			}
			else {
				
				Iterator<Position>positionIterator1 = adjacent1.iterator();
				
				// For each adjacent position
				while (positionIterator1.hasNext()) {
					Position testedPosition1 = positionIterator1.next();
					previousCost += map.getSquareType(testedPosition1).getMoveCost();
					ArrayList<Position> adjacent2 = adjacentSquare(testedPosition0, map);
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
							ArrayList<Position> adjacent3 = adjacentSquare(testedPosition0, map);
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

		ArrayList<IndexPosition> graphResult = new ArrayList<IndexPosition>();
		for(IndexPosition current : getGraph().getGraph()) {
			if (current.getLocalCost() <= movement) {
				graphResult.add(current);
			}
		}
		getGraph().setGraph(graphResult);
	}
	
	public int goTo(IndexPosition position, Map map, Game game) throws OutOfRangeException, AttributeException {
		// Event : 
		//0 : movement
		//1 : battle
		//2 : conquest
		int event = 0;
		Position interPosition;
		Position testedPosition;
		if (getGraph().getGraph().contains(position)) {
			if (map.getSquareType(position).getFaction() != map.getSquareType(getUnit().getPosition()).getFaction()) {
				ArrayList<Position> localPath = position.getLocalPath();
				Iterator<Position> iterator = localPath.iterator();
				while (iterator.hasNext()) {
					interPosition = iterator.next();
					ArrayList<Position> adjacent = adjacentSquare(interPosition, map);
					Iterator<Position> interIterator = adjacent.iterator();
					while (interIterator.hasNext()) {
						testedPosition = interIterator.next();
						if (map.getSquareType(testedPosition).getFaction() != map.getSquareType(getUnit().getPosition()).getFaction() && !map.getSquareType(testedPosition).getUnit()) {
							map.getSquareType(testedPosition).setFaction(getUnit().getFaction());
						}
					}
				}
				float movement = getUnit().getMovement()-position.getLocalCost();
				getUnit().setMovement(movement);
				if (map.getSquareType(position).getUnit()) {
					event = 1;
					Unit defenderUnit = game.getUnits().get(position);
					fight(getUnit(), defenderUnit, game, map);
					return event;
				}
				else {
					event  = 2;
					return event;
				}
			}
			else {
				getUnit().setPosition(position);
				float movement = getUnit().getMovement()-position.getLocalCost();
				getUnit().setMovement(movement);
				return event;
			}
		}
		else {
			OutOfRangeException except = new OutOfRangeException();
			throw except; 
		}
	}
	
	public float[] calculate(Unit attackUnit, Unit defenseUnit, Map map) throws AttributeException {
		double attributeBonus = 1;
				
//		Country[] countries = game.getPlayers();
//		Country attackerCountry = countries[game.getCurrentPlayer()];
		
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
//		Position defenderPosition = defenseUnit.getPosition();
		
		double damage = attackerAttack*/*attackerBoost**/attackerBonus*attackerHealth/100 - defenderDefense*/*defenderBoost**/defenderBonus;
		
		attributeBonus = attributeBonus(attackerAttribute, defenderAttribute);
		damage = damage*attributeBonus;
		
		float defenderDamage = (float) damage;
		defenderHealth = (float) (defenderHealth-damage);
				
		if (defenderHealth > 0) {
			if(defenderRange == attackerRange) {
				attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getDefense(); 
				defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getAttack();
				damage = defenderAttack*/*defenderBoost**/defenderBonus*defenderHealth/100 - attackerDefense*/*attackerBoost**/attackerBonus;
				damage = damage*1/attributeBonus;
				
				attackerHealth = (float) (attackerHealth - damage);
				attackUnit.setCurrentHealth(attackerHealth);
			}
		}
		else {
			damage = 0;
		}		
	float[] damages = {defenderDamage, defenderHealth, (float)damage, attackerHealth};
		
	return damages;
	}
	
	public void fight(Unit attackUnit, Unit defenseUnit, Game game, Map map) throws AttributeException {

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
					Position position = getUnit().getPosition();
					units.remove(position,getUnit());
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
	
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	public Graph getGraph() {
		return graph;
	}
}
