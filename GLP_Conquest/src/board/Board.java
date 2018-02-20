package board;

import squares.Square;

public class Board {
	private Square[][] squares;
	private int dimensions;
	
	public Board(int dimensions) {
		setDimensions(dimensions);
		squares = new Square[getDimensions()][getDimensions()];
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
	
	public Square getSquareType(Position position) {
		int jPosition = position.getJPosition();
		int iPosition = position.getIPosition();
		return squares[jPosition][iPosition];
	}
	
}
