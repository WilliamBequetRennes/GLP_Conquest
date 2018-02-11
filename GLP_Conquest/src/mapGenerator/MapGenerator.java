package mapGenerator;

import datas.Position;
import exceptions.InvalidBiomeNumberException;
import exceptions.InvalidSizeMapNumberException;
import exceptions.InvalidSquareNumberException;
import map.Biome;
import map.Map;
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

public class MapGenerator {
	
	private final static int LITTLE_MAP_DIMENSIONS = 27;
	private final static int MEDIUM_MAP_DIMENSIONS = 45;
	private final static int WIDE_MAP_DIMENSIONS = 63;
	private final static int BIOME_NUMBER_LITTLE_MAP = 3;
	private final static int BIOME_NUMBER_MEDIUM_MAP = 5;
	private final static int BIOME_NUMBER_WIDE_MAP = 7;
	private String biomeStatsFile;
	private String blockStatsFile;
	private BiomeGenerator biomeGenerator;
	private RandomNumberGenerator rng;
	
	public MapGenerator() {
		setBiomeStatsFile("probabilities_blocks.txt");
		setBlockStatsFile("probabilities_squares.txt");
		setRng(new RandomNumberGenerator());
	}
	
	public Map generate(int sizeMap) throws InvalidSizeMapNumberException{
		Map map = null;
		BiomeGenerator biomeGenerator = new BiomeGenerator(getBiomeStatsFile(), getBlockStatsFile());
		int numberOfBiomes = 0;
		Biome[][] biomes = null;
		//define the size of the map
		switch(sizeMap) {
			case(0):numberOfBiomes=BIOME_NUMBER_LITTLE_MAP;
			break;
			case(1):numberOfBiomes=BIOME_NUMBER_MEDIUM_MAP;
			break;
			case(2):numberOfBiomes=BIOME_NUMBER_WIDE_MAP;
			break;
			default:throw new InvalidSizeMapNumberException(sizeMap);
		}
		biomes = new Biome[numberOfBiomes][numberOfBiomes];
		//generate randomly the biomes
		try {
			for(int i = 0; i<numberOfBiomes; i++) {
				for(int j = 0; j<numberOfBiomes; j++) {
					int biomeType = getRng().generate(5, 0);
					if(sizeMap==0) {
						if ((i==0 || i==2) && (j==0 || j==2)) {
							biomes[i][j] = biomeGenerator.generate(biomeType, true);
						}
						else {
							biomes[i][j] = biomeGenerator.generate(biomeType, false);	
						}
					}
					else if(sizeMap==1) {
						if ((i==1 || i==3) && (j==1 || j==3)) {
							biomes[i][j] = biomeGenerator.generate(biomeType, true);
						}
						else {
							biomes[i][j] = biomeGenerator.generate(biomeType, false);	
						}
					}
					else if(sizeMap==2) {
						if ((i==1 || i==5) && (j==1 || j==5)) {
							biomes[i][j] = biomeGenerator.generate(biomeType, true);
						}
						else {
							biomes[i][j] = biomeGenerator.generate(biomeType, false);	
						}
					}
				}
			}
		}catch(InvalidSquareNumberException error) {
			System.err.println(error);
		}catch(InvalidBiomeNumberException error) {
			System.err.println(error);
		}
		//transform the biomes into a square array
		Square[][] squares = translateIntoSquares(biomes, numberOfBiomes);
		switch(sizeMap) {
		case(0):map = new Map(LITTLE_MAP_DIMENSIONS, squares);
		break;
		case(1):map = new Map(MEDIUM_MAP_DIMENSIONS, squares);
		break;
		case(2):map = new Map(WIDE_MAP_DIMENSIONS, squares);
		break;
		default:throw new InvalidSizeMapNumberException(sizeMap);
		}
		return map;
	}
	
	public Square[][] translateIntoSquares(Biome[][] biomes, int dimensions){
		Square[][] squares = new Square[dimensions*9][dimensions*9];
		for(int i = 0; i<dimensions; i++) {
			for(int j = 0; j<dimensions; j++) {
				for(int x = 0; x<9; x++) {
					for(int y = 0; y<9; y++) {
						//collect the type of the square
						int squareType = biomes[i][j].getBlocks()[x/3][y/3].getSquares()[x%3][y%3];
						try {
							//create the square using its type and its position
							squares[i*9+x][j*9+y] = createSquare(squareType, i*9+x, j*9+y);
						} catch (InvalidSquareNumberException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return squares;
	}
		
	public Square createSquare(int type, int i, int j) throws InvalidSquareNumberException{
		Square square = null;
		Position position = new Position(i, j);
		switch(type) {
		case(0):square = new Water(position);
		break;
		case(1):square = new Land(position);
		break;
		case(2):square = new Desert(position);
		break;
		case(3):square = new Forest(position);
		break;
		case(4):square = new Mont(position);
		break;
		case(5):square = new Mine(position);
		break;
		case(6):square = new Farm(position);
		break;
		case(7):square = new OilWell(position);
		break;
		case(8):square = new NuclearPlant(position);
		break;
		case(9):square = new City(position);
		break;
		default:throw new InvalidSquareNumberException(type);
		}
		return square;
	}

	public String getBiomeStatsFile() {
		return biomeStatsFile;
	}

	public void setBiomeStatsFile(String biomeStatsFile) {
		this.biomeStatsFile = biomeStatsFile;
	}

	public String getBlockStatsFile() {
		return blockStatsFile;
	}

	public void setBlockStatsFile(String blockStatsFile) {
		this.blockStatsFile = blockStatsFile;
	}

	public BiomeGenerator getBiomeGenerator() {
		return biomeGenerator;
	}

	public void setBiomeGenerator(BiomeGenerator biomeGenerator) {
		this.biomeGenerator = biomeGenerator;
	}

	public RandomNumberGenerator getRng() {
		return rng;
	}

	public void setRng(RandomNumberGenerator rng) {
		this.rng = rng;
	}
	
}
