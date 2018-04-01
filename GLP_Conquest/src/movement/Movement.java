package movement;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import countries.Country;
import data.Position;
import exceptions.AttributeException;
import exceptions.OutOfRangeException;
import exceptions.UnitException;
import game.Game;
import map.Map;
import units.Transform;
import units.Unit;
import fight.AreaScanner;


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
		
		AreaScanner scanner = new AreaScanner();
		ArrayList<IndexPosition> scan = new ArrayList<IndexPosition>();
		for(Position convert : scanner.searchTargets(unit,map)) {
			scan.add(convert.toIndexPosition());
		}
		//Iterator on the whole graph linked to the position 
		
		Iterator<IndexPosition> positionIterator0 = scan.iterator();
		
		//save the cost of the previous square
		
		float previousCost = 0;
		
		//save the previous path to the current position
		
		ArrayList<Position> previousPath = new ArrayList<Position>();
		
		//position of the selected unit
		
		IndexPosition position = new IndexPosition(unit.getPosition());
		
		//remaining movement of the selected unit
		
		int movement = (int) unit.getMovement();
		
		//the starting position costs 0 to go to
		
		position.setLocalCost(0);		
		
		//For each position of the whole graph
		
		while (positionIterator0.hasNext()) {
			
			//we test the position
			
			IndexPosition testedPosition0 = positionIterator0.next();
			
			//set the movement cost to reach the selected position
			
			previousCost += map.getSquareType(testedPosition0).getMoveCost();

			//select the adjacent positions			
			
			ArrayList<Position> adjacent1 = adjacentSquare(position, map);
			
			//if it is next to the unit's position
			
			if (adjacent1.contains(testedPosition0)) {
			
				//set data
				
				testedPosition0.setLocalCost(previousCost);
				
				testedPosition0.addLocalPath(testedPosition0);
			
			}
			
			//if it is not next to the unit's position
			
			else {
				
				//the previous position is part of the path
				
				previousPath.add(testedPosition0);
				
				//we test all of the adjacent position of the tested position

				Iterator<Position>positionIterator1 = adjacent1.iterator();
				
				// For each adjacent position
				
				while (positionIterator1.hasNext()) {
					
					//test if the current position is next to the position we aim
					
					Position testedPosition1 = positionIterator1.next();
					
					//refresh the movement cost to the aimed square
					
					previousCost += map.getSquareType(testedPosition1).getMoveCost();
					
					//select the adjacent position
					
					ArrayList<Position> adjacent2 = adjacentSquare(testedPosition1, map);
					
					//if the adjacent position contains the aimed position
					
					if (adjacent2.contains(testedPosition0)) {
						
						//if the movement cost is cheaper than thep revious one(default : 100)
					
						if(map.getSquareType(testedPosition0).getMoveCost()+previousCost<testedPosition0.getLocalCost()) {
						
							//set datas
							
							testedPosition0.setLocalCost(map.getSquareType(testedPosition0).getMoveCost()+previousCost);
							
							testedPosition0.setLocalPath(previousPath);
							
							testedPosition0.addLocalPath(testedPosition0);
						}
					}
					
					//last iteration
					
					else {
						
						//the previous position is part of the path
						
						previousPath.add(testedPosition1);
						
						//try last iteration
						
						Iterator<Position> positionIterator2 = adjacent2.iterator();
						
						// For each adjacent position
						
						while (positionIterator2.hasNext()) {
							
							//save the last position in range and check
							
							Position testedPosition2 = positionIterator2.next();
							
							//update movement cost
							
							previousCost += map.getSquareType(testedPosition2).getMoveCost();
							
							//check one last time
							
							ArrayList<Position> adjacent3 = adjacentSquare(testedPosition0, map);
							
							//and if you finally find it
						
							if (adjacent3.contains(testedPosition0)) {
								
								//verify it is the quickest
								
								if(map.getSquareType(testedPosition0).getMoveCost()+previousCost<testedPosition0.getLocalCost()) {
									
									//and set datas if it is
									
									testedPosition0.setLocalCost(map.getSquareType(testedPosition0).getMoveCost()+previousCost);
									testedPosition0.setLocalPath(previousPath);
									testedPosition0.addLocalPath(testedPosition0);
									
								}
							}
							previousCost -= map.getSquareType(testedPosition2).getMoveCost();
						}
						previousPath.remove(testedPosition1);
					}
					previousCost -= map.getSquareType(testedPosition1).getMoveCost();
				}
				previousPath.remove(testedPosition0);
			}
			previousCost -= map.getSquareType(testedPosition0).getMoveCost();
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
						
						if (map.getSquareType(testedPosition).getFaction() != map.getSquareType(getUnit().getPosition()).getFaction()
								&& !map.getSquareType(testedPosition).getUnit()) {
							int enemyFaction = map.getSquareType(testedPosition).getFaction();
							map.getSquareType(testedPosition).setFaction(getUnit().getFaction());
							game.getPlayers()[game.getCurrentPlayer()].setSquareNumber(game.getPlayers()[game.getCurrentPlayer()].getSquareNumber()+1);
							game.getPlayers()[enemyFaction-1].setSquareNumber(game.getPlayers()[enemyFaction-1].getSquareNumber()-1);
						}
					}
				}
				float movement = getUnit().getMovement()-position.getLocalCost();
				getUnit().setMovement(movement);
				unit.setPosition(position);
				
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
	
	public Damage calculate(Unit attackUnit, Unit defenseUnit, Map map) throws AttributeException {
		double attributeBonus = 1;
				
//		Country[] countries = game.getPlayers();
//		Country attackerCountry = countries[game.getCurrentPlayer()];
		
		int attackerAttack = attackUnit.getAttack();
		int attackerDefense = attackUnit.getDefense();
		float attackerHealth = attackUnit.getCurrentHealth();
		float attackerMinHealth = attackUnit.getCurrentHealth();
		float attackerMaxHealth = attackUnit.getCurrentHealth();
		int attackerRange = attackUnit.getRange();
		int attackerAttribute = attackUnit.getAttribute();
//		String attackerBoost = attackUnit.getFaction().getLeader();
		float attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getAttack(); 
		
		int defenderAttack = defenseUnit.getAttack();
		int defenderDefense = defenseUnit.getDefense();
		float defenderHealth = defenseUnit.getCurrentHealth();
		float defenderMinHealth = defenseUnit.getCurrentHealth();
		float defenderMaxHealth = defenseUnit.getCurrentHealth();
		int defenderRange = defenseUnit.getRange();
		int defenderAttribute = defenseUnit.getAttribute();
//		String defenderBoost = defenseUnit.getFaction().getLeader();
		float defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getDefense();
//		Position defenderPosition = defenseUnit.getPosition();
		
		double minAttackDamage=0;
		double maxAttackDamage=0;
		float defenderMinDamage=0;
		float defenderMaxDamage=0;
		double minRevengeDamage=0;
		double maxRevengeDamage=0;
		
		if(attackUnit.getType()==3 || attackUnit.getType()==2) {
		
		minAttackDamage = attackerAttack*/*attackerBoost**/attackerBonus*attackerHealth/100*1.8 - defenderDefense*/*defenderBoost**/defenderBonus;
		maxAttackDamage = attackerAttack*/*attackerBoost**/attackerBonus*attackerHealth/100*2.2 - defenderDefense*/*defenderBoost**/defenderBonus;

		attributeBonus = attributeBonus(attackerAttribute, defenderAttribute);
		minAttackDamage = minAttackDamage*attributeBonus;
		maxAttackDamage = maxAttackDamage*attributeBonus;

		defenderMinDamage = (float) minAttackDamage;
		defenderMaxDamage = (float) maxAttackDamage;
		defenderMinHealth = (float) (defenderHealth-maxAttackDamage);
		defenderMaxHealth = (float) (defenderHealth-minAttackDamage);
		
		}
		else {
			minAttackDamage = attackerAttack*/*attackerBoost**/attackerBonus*attackerHealth/100*1.9 - defenderDefense*/*defenderBoost**/defenderBonus;
			maxAttackDamage = attackerAttack*/*attackerBoost**/attackerBonus*attackerHealth/100*2.1 - defenderDefense*/*defenderBoost**/defenderBonus;

			attributeBonus = attributeBonus(attackerAttribute, defenderAttribute);
			minAttackDamage = minAttackDamage*attributeBonus;
			maxAttackDamage = maxAttackDamage*attributeBonus;

			defenderMinDamage = (float) minAttackDamage;
			defenderMaxDamage = (float) maxAttackDamage;
			defenderMinHealth = (float) (defenderHealth-maxAttackDamage);
			defenderMaxHealth = (float) (defenderHealth-minAttackDamage);
		}
		if (defenderMaxHealth > 0) {
			if(defenderRange == attackerRange) {
				if(defenseUnit.getType()==3 || defenseUnit.getType()==2) {
					attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getDefense(); 
					defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getAttack();
					minRevengeDamage = defenderAttack*/*defenderBoost**/defenderBonus*defenderHealth/100*1.8- attackerDefense*/*attackerBoost**/attackerBonus;
					maxRevengeDamage = defenderAttack*/*defenderBoost**/defenderBonus*defenderHealth/100*2.2 - attackerDefense*/*attackerBoost**/attackerBonus;

					minRevengeDamage = minRevengeDamage*1/attributeBonus;
					maxRevengeDamage = maxRevengeDamage*1/attributeBonus;
					
					attackerMaxHealth = (float) (attackerHealth - minRevengeDamage);
					attackerMinHealth = (float) (attackerHealth - maxRevengeDamage);
				}
				else {
					attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getDefense(); 
					defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getAttack();
					 minRevengeDamage = defenderAttack*/*defenderBoost**/defenderBonus*defenderHealth/100*1.9 - attackerDefense*/*attackerBoost**/attackerBonus;
					maxRevengeDamage = defenderAttack*/*defenderBoost**/defenderBonus*defenderHealth/100*2.1 - attackerDefense*/*attackerBoost**/attackerBonus;

					minRevengeDamage = minRevengeDamage*1/attributeBonus;
					maxRevengeDamage = maxRevengeDamage*1/attributeBonus;
					
					attackerMaxHealth = (float) (attackerHealth - minRevengeDamage);
					attackerMinHealth = (float) (attackerHealth - maxRevengeDamage);
				}
				attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getDefense(); 
				defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getAttack();
				minRevengeDamage = defenderAttack*/*defenderBoost**/defenderBonus*defenderHealth/100*1.9 - attackerDefense*/*attackerBoost**/attackerBonus;
				maxRevengeDamage = defenderAttack*/*defenderBoost**/defenderBonus*defenderHealth/100*2.1 - attackerDefense*/*attackerBoost**/attackerBonus;

				minRevengeDamage = minRevengeDamage*1/attributeBonus;
				maxRevengeDamage = maxRevengeDamage*1/attributeBonus;
				
				attackerMaxHealth = (float) (attackerHealth - minRevengeDamage);
				attackerMinHealth = (float) (attackerHealth - maxRevengeDamage);
			}
		}
		else {
			maxRevengeDamage = 0;
			minRevengeDamage = 0;
		}		
	Damage damages = new Damage(defenderMinDamage, defenderMaxDamage, (float)minRevengeDamage, (float)maxRevengeDamage,
			(float)attackerMinHealth, (float)attackerMaxHealth, (float)defenderMinHealth, (float)defenderMaxHealth);
		
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
		
		Random r = new Random();

		double random = 1;
		
		if(attackUnit.getType()==3 || attackUnit.getType()==2) {
			random = 1.8 + r.nextDouble() * (2.2 - 1.8);
		}
		else {
			random = 1.9 + r.nextDouble() * (2.1 - 1.9);
		}
		
		double damage = attackerAttack*/*attackerBoost**/attackerBonus*attackerHealth/100*random - defenderDefense*/*defenderBoost**/defenderBonus;
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

	public void tranformUnit(Unit unit, Map map, Game game) throws UnitException {
		if (unit.getType()<6) {
			if(map.getSquareType(unit.getPosition()).getType() == 0) {
				Transform transform = new Transform(unit.getPosition(),unit.getFaction(),unit);
				game.getUnits().remove(unit.getPosition(),unit);
				game.getUnits().put(transform.getPosition(),transform);
				game.getPlayers()[unit.getFaction()].getUnits().remove(unit.getPosition(),unit);
				game.getPlayers()[unit.getFaction()].getUnits().put(transform.getPosition(),transform);
			}
		}
		else if (unit.getType()==9) {
			if(map.getSquareType(unit.getPosition()).getType() != 0) {
				Transform transform = (Transform) unit;
				game.getUnits().remove(unit.getPosition(),unit);
				game.getUnits().put(transform.getPosition(),transform.getUnit());
				game.getPlayers()[unit.getFaction()].getUnits().remove(unit.getPosition(),unit);
				game.getPlayers()[unit.getFaction()].getUnits().put(transform.getPosition(),transform.getUnit());
				
			}	
		}
		else {
			if(map.getSquareType(unit.getPosition()).getType() != 0) {
				UnitException except = new UnitException();
				throw except;
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
	
	public void conquest(Position position, Map map, Game game) {
		ArrayList<Position> adjacent = adjacentSquare(position,map);
		for(Position check : adjacent) {
			if(map.getSquareType(check).getFaction()== 0) {
				game.getPlayers()[game.getCurrentPlayer()].setSquareNumber(game.getPlayers()[game.getCurrentPlayer()].getSquareNumber()+1);
				map.getSquareType(check).setFaction(game.getCurrentPlayer()+1);
			}
			if(map.getSquareType(check).getFaction()!=game.getCurrentPlayer()) {
				if(map.getSquareType(check).getType()<5 && !map.getSquareType(check).getUnit()) {
					game.getPlayers()[map.getSquareType(check).getFaction()-1].setSquareNumber(
							game.getPlayers()[map.getSquareType(check).getFaction()-1].getSquareNumber()-1);
					game.getPlayers()[game.getCurrentPlayer()].setSquareNumber(game.getPlayers()[game.getCurrentPlayer()].getSquareNumber()+1);
					
				}
			}
		}
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
	
	public class Damage {
		private float minAttackDamage;
		private float maxAttackDamage;
		private float minRevengeDamage;
		private float maxRevengeDamage;
		private float attackerMinRemainingHealth;
		private float attackerMaxRemainingHealth;
		private float defenderMinRemainingHealth;
		private float defenderMaxRemainingHealth;
		
		public Damage(float minAttackDamage, float maxAttackDamage,  float minRevengeDamage,float maxRevengeDamage, 
				float attackerMinRemainingHealth, float attackerMaxRemainingHealth, float defenderMinRemainingHealth, float defenderMaxRemainingHealth ) {
			setMinAttackDamage(minAttackDamage);
			setMaxAttackDamage(maxAttackDamage);
			setMinRevengeDamage(minRevengeDamage);
			setMaxRevengeDamage(maxRevengeDamage);
			setAttackerMinRemainingHealth(attackerMinRemainingHealth);
			setAttackerMaxRemainingHealth(attackerMaxRemainingHealth);
			setDefenderMinRemainingHealth(defenderMinRemainingHealth);
			setDefenderMaxRemainingHealth(defenderMaxRemainingHealth);
		}
		public Damage() {
			this(0,0,0,0,0,0,0,0);
		}
		
		public void setMinAttackDamage(float minAttackDamage) {
			this.minAttackDamage = minAttackDamage;
		}
		
		public float getMinAttackDamage() {
			return this.minAttackDamage;
		}
		public void setMaxAttackDamage(float maxAttackDamage) {
			this.maxAttackDamage = maxAttackDamage;
		}
		
		public float getMaxAttackDamage() {
			return this.maxAttackDamage;
		}
		public void setMinRevengeDamage(float minRevengeDamage) {
			this.minRevengeDamage = minRevengeDamage;
		}
		
		public float getMinRevengeDamage() {
			return this.minRevengeDamage;
		}
		public void setMaxRevengeDamage(float maxRevengeDamage) {
			this.maxRevengeDamage = maxRevengeDamage;
		}
		
		public float getMaxRevengeDamage() {
			return this.maxRevengeDamage;
		}
		public void setAttackerMinRemainingHealth(float attackerMinRemainingHealth) {
			this.attackerMinRemainingHealth = attackerMinRemainingHealth;
		}
		
		public float getAttackerMinRemainingHealth() {
			return this.attackerMinRemainingHealth;
		}
		public void setAttackerMaxRemainingHealth(float attackerMaxRemainingHealth) {
			this.attackerMaxRemainingHealth = attackerMaxRemainingHealth;
		}
		
		public float getAttackerMaxRemainingHealth() {
			return this.attackerMaxRemainingHealth;
		}
		public void setDefenderMinRemainingHealth(float defenderMinRemainingHealth) {
			this.defenderMinRemainingHealth = defenderMinRemainingHealth;
		}
		
		public float getDefenderMinRemainingHealth() {
			return this.defenderMinRemainingHealth;
		}
		public void setDefenderMaxRemainingHealth(float defenderMaxRemainingHealth) {
			this.defenderMaxRemainingHealth = defenderMaxRemainingHealth;
		}
		
		public float getDefenderMaxRemainingHealth() {
			return this.defenderMaxRemainingHealth;
		}
		
		
	}
}
