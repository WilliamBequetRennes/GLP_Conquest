package fight;

import java.util.ArrayList;

import data.Position;
import map.Map;
import units.Unit;

public class AreaScanner {

	private ArrayList<Position> squares;
	
	public AreaScanner() {
		setSquares(new ArrayList<Position>());
	}
	
	public ArrayList<Position> SearchTargets(Unit unit, Map map) {
		getSquares().clear();
		if(unit.getRange()<=1) {
			return getSquares();
		}
		int range = unit.getRange();
		int jPosition = unit.getPosition().getJPosition();
		int iPosition = unit.getPosition().getIPosition();
		for(int j = -range; j<=range; j++) {
			//If we are in the map dimensions
			if(jPosition + j >= 0 && jPosition + j < map.getDimensions()) {
				//Soit [x] la partie entière de x
				//Soit s le signe de jPosition tel que s = j/|j|
				//Soit d le maximum de i et -d le minimum
				//Alors, si jPosition % 2 == 0, d = range - [|j|/2]
				//Sinon, d = range - [|j+1|/2]
				//Donc, quelque soit la parité de jPosition on a :
				//d = R - [ |j+(jPosition % 2)|/2 ]
				//d = R - [ s*(j+(jPosition % 2)) / 2 ]
				int s = j/Math.abs(j);
				int d = range - (int)(s*(j+(jPosition%2))/2);
				for(int i = -d; i <= d; i++) {
					//If we are in the map dimensions
					if(iPosition + i >= 0 && iPosition + i < map.getDimensions()) {
						if(map.getSquares()[i][j].getUnit() && 
								map.getSquares()[i][j].getFaction()!=unit.getFaction()) {
							getSquares().add(new Position(i,j));
						}
					}
				}
			}
		}
		return getSquares();
	}

	public ArrayList<Position> getSquares() {
		return squares;
	}

	public void setSquares(ArrayList<Position> squares) {
		this.squares = squares;
	}
}
