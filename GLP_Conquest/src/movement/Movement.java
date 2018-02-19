import java.util.ArrayList;
import unit.Unit;

public class Movement {
	private Unit unit;
	private Graph graph;
	private int mapSize;
	
	public Movement(Unit unit) {
		this.unit = unit;
		Position position = this.unit.getPosition();
		this.graph = new Graph(position);
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
			}
		}
		return adjacent; 
	}
	
	public ArrayList<Position> availableMovement(){
		int count = 0;
		ArrayList<Position> movementRange = new ArrayList<Position>();
		ArrayList<Position> maxRange = graph.getGraph();
		Position position = this.unit.getPosition();
		int movement = (int) this.unit.getRemainingMovement();
		
		
		
		return movementRange;
	}
	
	public ArrayList<Position> shortestWay(Position position) throws outOfRangeException{
		ArrayList<Position> available = availableMovement();
		if (available.contains(position)) {
			ArrayList<Position> way = new ArrayList<Position>();
//			int deltaX = this.unit.getPosition().getXPosition() - position.getXPosition();
//			int deltaY = this.unit.getPosition().getYPosition() - position.getYPosition();
			
			return way;
		}
		else {
			outOfRangeException except = new outOfRangeException();
			throw except; 
		}
	}
}
