package fight;

import java.util.ArrayList;

import data.Position;
import map.Map;
import squares.Square;
import units.Unit;

public class AreaScanner {

	private ArrayList<Position> targets;
	private ArrayList<Position> squares;
	
	public AreaScanner() {
		setTargets(new ArrayList<Position>());
		setSquares(new ArrayList<Position>());
	}
	
	public ArrayList<Position> searchTargets(Unit unit, Map map) {
		getTargets().clear();
		int range = unit.getRange();
		if(range<=1) {
			return getTargets();
		}
		ArrayList<Position> reachableSquares = aroundPositions(unit.getPosition(), range, map);
		for(Position current : reachableSquares) {
			Square square = map.getSquares()[current.getIPosition()][current.getJPosition()];
			if(square.getFaction()!=unit.getFaction() && square.getUnit()) {
				getTargets().add(current);
			}
		}
		return getTargets();
	}
	
	public ArrayList<Position> aroundPositions(Position position, int distance, Map map){
		getSquares().clear();
		int jPosition = position.getJPosition();
		int iPosition = position.getIPosition();
		for(int j = -distance; j<=distance; j++) {
			//If we are in the map dimensions
			if(jPosition + j >= 0 && jPosition + j < map.getDimensions()) {
				if(j%2==0) {
					int d = distance - (j/2);
					for(int i = -d; i <= d; i++) {
						//If we are in the map dimensions
						if(iPosition + i >= 0 && iPosition + i < map.getDimensions() && (j!=0 || i!=0)) {
							getSquares().add(new Position(i+iPosition,j+jPosition));
						}
					}
				}
				else {
					int dMax = distance - ((Math.abs(j)-1)/2);
					int dMin = distance - ((Math.abs(j)+1)/2);
					if(jPosition%2!=0) {
						for(int i = -dMin; i <= dMax; i++) {
							//If we are in the map dimensions
							if(iPosition + i >= 0 && iPosition + i < map.getDimensions() && (j!=0 || i!=0)) {
								getSquares().add(new Position(i+iPosition,j+jPosition));
							}
						}
					}
					else {
						for(int i = -dMax; i <= dMin; i++) {
							//If we are in the map dimensions
							if(iPosition + i >= 0 && iPosition + i < map.getDimensions() && (j!=0 || i!=0)) {
								getSquares().add(new Position(i+iPosition,j+jPosition));
							}
						}
					}
				}
			}
		}
		return getSquares();
	}


	public ArrayList<Position> getTargets() {
		return targets;
	}

	public void setTargets(ArrayList<Position> targets) {
		this.targets = targets;
	}

	public ArrayList<Position> getSquares() {
		return squares;
	}

	public void setSquares(ArrayList<Position> squares) {
		this.squares = squares;
	}

}
