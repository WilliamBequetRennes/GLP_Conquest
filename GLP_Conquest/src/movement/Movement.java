package movement;

import java.util.ArrayList;
import java.util.Iterator;

import datas.Position;
import exceptions.OutOfRangeException;
import map.Map;
import units.Unit;

public class Movement {
	private Unit unit;
	private Graph graph;
	private Map map;
	
	public Movement(Unit unit) {
		setUnit(unit);
		Position position = this.unit.getPosition();
		//setGraph(position);
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
		int xPosition = position.getJPosition();
		int yPosition = position.getIPosition();
		if (parity(position.getIPosition())){
			/*if(xPosition > 0) {
				adjacent.add(new Position(xPosition-1,yPosition));
				if (yPosition > 0) {
					adjacent.add(new Position(xPosition-1, yPosition-1));
				}
				if (yPosition < mapSize) {
					adjacent.add(new Position(xPosition-1,yPosition+1));
				}
			}
			if (yPosition > 0) {
				adjacent.add(new Position(xPosition, yPosition-1));
			}
			if (yPosition < mapSize) {
				adjacent.add(new Position(xPosition,yPosition+1));
			}
			if (xPosition < mapSize) {
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
			if (yPosition < mapSize) {
				adjacent.add(new Position(xPosition,yPosition+1));
			}
			if (xPosition < mapSize) {
				adjacent.add(new Position(xPosition+1,yPosition));
				if (yPosition > 0) {
					adjacent.add(new Position(xPosition+1, yPosition-1));
				}
				if (yPosition < mapSize) {
					adjacent.add(new Position(xPosition+1,yPosition+1));
				}
			}*/
		}
		return adjacent; 
	}
	
	public ArrayList<Position> availableMovement(){
		
		//Contains the Position the Unit can go through
		ArrayList<Position> movementRange = new ArrayList<Position>();
		Iterator<Position> positionIterator0 = movementRange.iterator();
		
		Position position = this.unit.getPosition();
		int movement = (int) this.unit.getMovement();
		//For each position
		while (positionIterator0.hasNext()) {
			Position testedPosition0 = positionIterator0.next();
			ArrayList<Position> adjacent1 = adjacentSquare(position);
			//if it is next to the unit's position
			/*if (adjacent1.contains(testedPosition0)) {
				testedPosition0.setCost(board.getSquare());
			}*/
		}
		
		
		
		
		return movementRange;
	}
	
	public ArrayList<Position> shortestWay(Position position) throws OutOfRangeException{
		ArrayList<Position> available = availableMovement();
		if (available.contains(position)) {
			ArrayList<Position> way = new ArrayList<Position>();
//			int deltaX = this.unit.getPosition().getXPosition() - position.getXPosition();
//			int deltaY = this.unit.getPosition().getYPosition() - position.getYPosition();
			
			return way;
		}
		else {
			throw new OutOfRangeException();
		}
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
