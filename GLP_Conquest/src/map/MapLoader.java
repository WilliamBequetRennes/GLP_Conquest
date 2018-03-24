package map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import data.Position;
import squares.City;
import squares.Desert;
import squares.Farm;
import squares.Forest;
import squares.Land;
import squares.Mine;
import squares.Mont;
import squares.NuclearPlant;
import squares.OilWell;
import squares.Square;
import squares.Water;

public class MapLoader {

	private static final int MAX_MAPS = 1000;
	private final static int DIMENSIONS_POS = 1;
	private final static int SQUARES_POS = 2;
	
	private String loadFile;
	
	public MapLoader() {
		setLoadFile("savedMaps.txt");
	}
	
	public Map load(int number){
		if(number < MAX_MAPS) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(getLoadFile()));
				String current = "";
				while (current!=null && !current.startsWith(number+"#")) {
					current = reader.readLine();
				}
				reader.close();
				if(current == null) {
					return null;
				}
				else {
					String[] splittedLine = current.split("#");
					int dimensions = Integer.valueOf(splittedLine[DIMENSIONS_POS]).intValue();
					Square[][] squares = new Square[dimensions][dimensions];
					String[] squaresList = splittedLine[SQUARES_POS].split(",");
					int currentSquare = 0;
					int squareType;
					for(int i = 0; i < dimensions; i++) {
						for(int j = 0; j < dimensions; j++) {
							squareType = Integer.valueOf(squaresList[currentSquare]).intValue();
							switch(squareType) {
							case(0):squares[i][j] = new Water(new Position(i,j));
							break;
							case(1):squares[i][j] = new Land(new Position(i,j));
							break;
							case(2):squares[i][j] = new Desert(new Position(i,j));
							break;
							case(3):squares[i][j] = new Forest(new Position(i,j));
							break;
							case(4):squares[i][j] = new Mont(new Position(i,j));
							break;
							case(5):squares[i][j] = new Mine(new Position(i,j));
							break;
							case(6):squares[i][j] = new Farm(new Position(i,j));
							break;
							case(7):squares[i][j] = new OilWell(new Position(i,j));
							break;
							case(8):squares[i][j] = new NuclearPlant(new Position(i,j));
							break;
							case(9):squares[i][j] = new City(new Position(i,j));
							break;
							}
							currentSquare++;
						}
					}
					return new Map(dimensions, squares, number);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public String getLoadFile() {
		return loadFile;
	}
	public void setLoadFile(String loadFile) {
		this.loadFile = loadFile;
	}
}
