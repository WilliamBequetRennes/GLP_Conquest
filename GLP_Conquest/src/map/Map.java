package map;

import datas.Position;
import squares.Square;

public class Map {
	
	private Square[][] squares;
	private int dimensions;
	
	public Map(int dimensions, Square[][] squares) {
		setDimensions(dimensions);
		setSquares(squares);
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public int getDimensions() {
		return dimensions;
	}

	public void setDimensions(int dimensions) {
		this.dimensions = dimensions;
	}

	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i<dimensions; i++) {
			for(int j = 0; j<dimensions; j++) {
				result+=getSquares()[i][j].getType();
			}
			result+="\n";
		}
		return result;
	}

	public Square getSquareType(Position position) {
		int jPosition = position.getJPosition();
		int iPosition = position.getIPosition();
		return squares[jPosition][iPosition];
	}
}
