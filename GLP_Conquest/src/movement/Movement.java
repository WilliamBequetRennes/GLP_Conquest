package movement;

import java.util.ArrayList;
import java.util.Iterator;

import countries.Country;
import datas.Bonus;
import datas.Position;
import exceptions.OutOfRangeException;
import game.Game;
import map.Map;
import squares.Square;
import units.Unit;

public class Movement {
	private Unit unit;
	private Graph graph;
	private Map map;
	
	public Movement(Unit unit) {
		setUnit(unit);
		IndexPosition position = (IndexPosition) this.unit.getPosition();
		setGraph(position);
		setMap(map);
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
	
	public ArrayList<IndexPosition> adjacentSquare(IndexPosition position){
		ArrayList<IndexPosition> adjacent = new ArrayList<IndexPosition>();
		int jPosition = position.getJPosition();
		int iPosition = position.getIPosition();
		if (parity(position.getIPosition())){
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
		
		//Contains the Position the Unit can go through
		ArrayList<IndexPosition> movementRange = new ArrayList<IndexPosition>();
		Iterator<IndexPosition> positionIterator0 = this.graph.getGraph().iterator();
		float previousCost = 0;
		ArrayList<Position> previousPath = new ArrayList<Position>();
		IndexPosition position = (IndexPosition) this.unit.getPosition();
		int movement = (int) this.unit.getMovement();
		position.setLocalCost(0);
		
		//For each position
		while (positionIterator0.hasNext()) {
			IndexPosition testedPosition0 = positionIterator0.next();
			ArrayList<IndexPosition> adjacent1 = adjacentSquare(position);
			//if it is next to the unit's position
			if (adjacent1.contains(testedPosition0)) {
				//set datas
				testedPosition0.setLocalCost(map.getSquareType(testedPosition0).getMoveCost());
				testedPosition0.addLocalPath(testedPosition0);
			}
			else {
				
				Iterator<IndexPosition>positionIterator1 = adjacent1.iterator();
				
				// For each adjacent position
				while (positionIterator1.hasNext()) {
					IndexPosition testedPosition1 = positionIterator1.next();
					previousCost += map.getSquareType(testedPosition1).getMoveCost();
					ArrayList<IndexPosition> adjacent2 = adjacentSquare(testedPosition0);
					if (adjacent2.contains(testedPosition0)) {
						if(map.getSquareType(testedPosition0).getMoveCost()+previousCost<testedPosition0.getLocalCost()) {
							testedPosition0.setLocalCost(map.getSquareType(testedPosition0).getMoveCost()+previousCost);
							testedPosition0.setLocalPath(previousPath);
							testedPosition0.addLocalPath(testedPosition0);
						}
					}
					else {
						Iterator<IndexPosition>positionIterator2 = adjacent2.iterator();
						
						// For each adjacent position
						while (positionIterator2.hasNext()) {
							IndexPosition testedPosition2 = positionIterator2.next();
							previousCost += map.getSquareType(testedPosition2).getMoveCost();
							ArrayList<IndexPosition> adjacent3 = adjacentSquare(testedPosition0);
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
		Iterator<IndexPosition> sortIterator = this.graph.getGraph().iterator();
		while (sortIterator.hasNext()) {
			IndexPosition sortPosition = sortIterator.next();
			if (sortPosition.getLocalCost() > movement) {
				graph.getGraph().remove(sortPosition);
			}
		}
	}
	
	public int goTo(IndexPosition position) throws OutOfRangeException {
		// Event : 
		//0 : movement
		//1 : battle
		//2 : conquest
		int event = 0;
		if (this.graph.getGraph().contains(position)) {
			if (map.getSquareType(position).getFaction() != map.getSquareType(this.unit.getPosition()).getFaction()) {
				if (map.getSquareType(position).getUnit()) {
					event = 1;
//					Country defender = ;
					
//					fight(this.unit, );
					return event;
				}
				else {
					event  = 2;
					this.map.getSquareType(position).setFaction(this.unit.getFaction());
					float movement = this.unit.getMovement()-position.getLocalCost();
					this.unit.setMovement(movement);
					return event;
				}
			}
			else {
				this.unit.setPosition(position);
				float movement = this.unit.getMovement()-position.getLocalCost();
				this.unit.setMovement(movement);
				return event;
			}
		}
		else {
			OutOfRangeException except = new OutOfRangeException();
			throw except; 
		}
	}
	
	public void fight(Unit attackUnit, Unit defenseUnit) {

		float attributeBonus = 1;
		
		int attackerAttack = attackUnit.getAttack();
		int attackerDefense = attackUnit.getDefense();
		float attackerHealth = attackUnit.getCurrentHealth();
		int attackerRange = attackUnit.getRange();
		int attackerAttribute = attackUnit.getAttribute();
//		String attackerBoost = attackUnit.getFaction().getLeader();
		Bonus attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus(); 
		int attackerFaction = attackUnit.getFaction();
		
		int defenderAttack = defenseUnit.getAttack();
		int defenderDefense = defenseUnit.getDefense();
		float defenderHealth = defenseUnit.getCurrentHealth();
		int defenderRange = defenseUnit.getRange();
		int defenderAttribute = defenseUnit.getAttribute();
//		String defenderBoost = defenseUnit.getFaction().getLeader();
		Bonus defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus();
		int defenderFaction = defenseUnit.getFaction();
		
		float damage = attackerAttack*/*attackerBoost*attackerBonus**/attackerHealth/100 - defenderDefense/* *defenderBoost*defenderBonus*/;
		
		/*if (attackerAttribute != defenderAttribute) {
			if (attributeBonus()) {
				attributeBonus = 3/2;
				damage = damage*attributeBonus;
			}
			else {
				attributeBonus = 2/3
				damage = damage*attributeBonus;
			}
		}*/
		
		defenderHealth = defenderHealth-damage;
		defenseUnit.setCurrentHealth(defenderHealth);
		
		if (defenderHealth > 0) {
			if(defenderRange == attackerRange) {
				damage = defenderAttack*/*defenderBoost*defenderBonus**/defenderHealth/100 - attackerDefense/* *attackerBoost*attackerBonus*/;
				damage = damage*1/attributeBonus;
				
				attackerHealth = attackerHealth - damage;
				attackUnit.setCurrentHealth(attackerHealth);
				
				if(attackerHealth < 0) {
					
					//Remove attacker Unit
					//Conquest square
				}
			}
		}
		else {
//			Country[] players  = Game.getPlayers();
//			Country player = players[defenderFaction-1];
//			player.getUnits().remove(defenderUnit);
			//Remove defender Unit
			//Conquest Square
			//Move attacker unit
		}
		
	}
	
	public void setUnit(Unit unit) {
		this.unit= unit;
	}
	
	public Unit getUnit() {
		return this.unit;
	}
	
	public void setGraph(IndexPosition position) {
		this.graph = graph;
	}
	
	public Graph getGraph() {
		return this.graph;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public Map getMap() {
		return this.map;
	}
}
