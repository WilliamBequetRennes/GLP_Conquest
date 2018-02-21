import java.util.ArrayList;
import java.util.Iterator;

import com.sun.xml.internal.ws.dump.LoggingDumpTube.Position;

import unit.Unit;

public class Movement {
	private Unit unit;
	private Graph graph;
	private Map map;
	
	public Movement(Unit unit) {
		setUnit(unit);
		Position position = this.unit.getPosition();
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
	
	public ArrayList<Position> adjacentSquare(Position position){
		ArrayList<Position> adjacent = new ArrayList<Position>();
		int xPosition = position.getXPosition();
		int yPosition = position.getYPosition();
		if (parity(position.getYPosition())){
			if(xPosition > 0) {
				adjacent.add(new Position(xPosition-1,yPosition));
				if (yPosition > 0) {
					adjacent.add(new Position(xPosition-1, yPosition-1));
				}
				if (yPosition < map.dimensions) {
					adjacent.add(new Position(xPosition-1,yPosition+1));
				}
			}
			if (yPosition > 0) {
				adjacent.add(new Position(xPosition, yPosition-1));
			}
			if (yPosition < map.dimensions) {
				adjacent.add(new Position(xPosition,yPosition+1));
			}
			if (xPosition < map.dimensions) {
				adjacent.add(new Position(xPosition+1,yPosition));
			}
		}
		else {
			if(xPosition > 0) {
				adjacent.add(new Position(xPosition-1,yPosition));
			}
			if (yPosition > 0) {
				adjacent.add(new Position(xPosition, yPosition-1));
			}
			if (yPosition < map.dimensions) {
				adjacent.add(new Position(xPosition,yPosition+1));
			}
			if (xPosition < map.dimensions) {
				adjacent.add(new Position(xPosition+1,yPosition));
				if (yPosition > 0) {
					adjacent.add(new Position(xPosition+1, yPosition-1));
				}
				if (yPosition < map.dimensions) {
					adjacent.add(new Position(xPosition+1,yPosition+1));
				}
			}
		}
		return adjacent; 
	}
	
	public void availableMovement(){
		
		//Contains the Position the Unit can go through
		ArrayList<Position> movementRange = new ArrayList<Position>();
		Iterator<Position> positionIterator0 = this.graph.iterator();
		float previousCost = 0;
		Position[] previousPath = {};
		
		Position position = this.unit.getPosition();
		int movement = (int) this.unit.getRemainingMovement();
		position.setLocalCost(0);
		
		//For each position
		while (positionIterator0.hasNext()) {
			Position testedPosition0 = positionIterator0.next();
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
							testedPosition0.SetLocalPath(previousPath);
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
									testedPosition0.SetLocalPath(previousPath);
									testedPosition0.addLocalPath(testedPosition0);
								}
							}
						}
					}
				}
			}
		}
		Iterator<Position> sortIterator = this.graph.iterator();
		while (sortIterator.hasNext()) {
			Position sortPosition = sortIterator.next();
			if (sortPosition.getCost > movement) {
				graph.remove(sortPosition);
			}
		}
	}
	
	public int goTo(Position position) throws outOfRangeException{
		int event = 0;
		if (this.graph.contains(position)) {
			
		}
		else {
			outOfRangeException except = new outOfRangeException();
			throw except; 
		}
	}
	
	public void setUnit(Unit unit) {
		this.unit= unit;
	}
	
	public Unit getUnit() {
		return this.unit;
	}
	
	public void setGraph(Position position) {
		this.graph = graph;
	}
	
	public Graph getGraph() {
		return this.graph;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public Map = getMap() {
		return this.map;
	}
}
