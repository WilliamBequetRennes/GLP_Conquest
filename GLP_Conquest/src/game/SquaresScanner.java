package game;

import data.Position;
import map.Map;
import squares.Square;

public class SquaresScanner {
	
	Game game;
	Map map;
	Square[][] squares;
	int dimensions;
	
	public SquaresScanner(Game game) {
		setGame(game);
		setMap(getGame().getMap());
		setSquares(getMap().getSquares());
		setDimensions(getMap().getDimensions());
	}
	

	public boolean scan(int searchedType, Position square) {
		/*if(square.getJPosition()==0) {
			if(square.getIPosition()==0) {
				if(square.getJPosition()%2==0) {
					
				}
				else {
					
				}
			}
			else if(square.getIPosition()==getDimensions()-1) {
				if(square.getJPosition()%2==0) {
					
				}
				else {
					
				}
			}
			else {
				if(square.getJPosition()%2==0) {
					
				}
				else {
					
				}
			}
		}
		else if(square.getJPosition()==getDimensions()-1) {
			if(square.getIPosition()==0) {
				if(square.getJPosition()%2==0) {
					
				}
				else {
					
				}
			}
			else if(square.getIPosition()==getDimensions()-1) {
				if(square.getJPosition()%2==0) {
					
				}
				else {
					
				}
			}
			else {
				if(square.getJPosition()%2==0) {
					
				}
				else {
					
				}
			}
		}
		else {
			if(square.getIPosition()==0) {
				if(square.getJPosition()%2==0) {
					
				}
				else {
					
				}
			}
			else if(square.getIPosition()==getDimensions()-1) {
				if(square.getJPosition()%2==0) {
					
				}
				else {
					
				}
			}
			else {
				if(square.getJPosition()%2==0) {
					
				}
				else {
					
				}
			}
		}*/
		boolean result = false;
		int i = square.getIPosition();
		int j = square.getJPosition();
		if(j%2==0) {
			if(j>0 && i>0) {
				if(getSquares()[i-1][j-1].getType()==searchedType) {
					result = true;
				}
			}
			if(i>0) {
				if(getSquares()[i-1][j].getType()==searchedType) {
					result = true;
				}
			}
			if(i>0 && j<getDimensions()-1) {
				if(getSquares()[i-1][j+1].getType()==searchedType) {
					result = true;
				}
			}
			if(j<getDimensions()-1) {
				if(getSquares()[i][j+1].getType()==searchedType) {
					result = true;
				}
			}
			if(i<getDimensions()-1) {
				if(getSquares()[i+1][j].getType()==searchedType) {
					result = true;
				}
			}
			if(j>0) {
				if(getSquares()[i][j-1].getType()==searchedType) {
					result = true;
				}
			}
		}
		else {
			if(j>0) {
				if(getSquares()[i][j-1].getType()==searchedType) {
					result = true;
				}
			}
			if(i>0) {
				if(getSquares()[i-1][j].getType()==searchedType) {
					result = true;
				}
			}
			if(j<getDimensions()-1) {
				if(getSquares()[i][j+1].getType()==searchedType) {
					result = true;
				}
			}
			if(i<getDimensions()-1 && j<getDimensions()-1) {
				if(getSquares()[i+1][j+1].getType()==searchedType) {
					result = true;
				}
			}
			if(i<getDimensions()-1) {
				if(getSquares()[i+1][j].getType()==searchedType) {
					result = true;
				}
			}
			if(i<getDimensions()-1 && j>0) {
				if(getSquares()[i+1][j-1].getType()==searchedType) {
					result = true;
				}
			}
		}
		return result;
	}
	public boolean scan(int searchedType) {
		return scan(searchedType, getGame().getCurrentSquare().getPosition());
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
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


	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}
	
}
