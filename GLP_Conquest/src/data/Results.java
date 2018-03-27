package data;

public class Results {
	private int playersNumber;
	private int[] ranks;
	private int[] totalPoints;
	private int[] terrainPoints;
	private int[] buildingPoints;
	private int[] cityPoints;
	private int[] numberOfTerrains;
	private int[] numberOfBuildings;
	private int[] numberOfCities;
	private int[] leaders;
	
	public Results(int playersNumber, int[]ranks, int[] totalPoints, int[] terrainPoints, 
			int[] buildingPoints, int[] cityPoints, int[] numberOfTerrains, int[] numberOfBuildings, 
			int[] numberOfCities, int[] leaders) {
		
		setPlayersNumber(playersNumber);
		setRanks(ranks);
		setTotalPoints(totalPoints);
		setTerrainPoints(terrainPoints);
		setBuildingPoints(buildingPoints);
		setCityPoints(cityPoints);
		setNumberOfTerrains(numberOfTerrains);
		setNumberOfBuildings(numberOfBuildings);
		setNumberOfCities(numberOfCities);
		setLeaders(leaders);
	}

	public int getPlayersNumber() {
		return playersNumber;
	}

	public void setPlayersNumber(int playersNumber) {
		this.playersNumber = playersNumber;
	}

	public int[] getRanks() {
		return ranks;
	}

	public void setRanks(int[] ranks) {
		this.ranks = ranks;
	}

	public int[] getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int[] totalPoints) {
		this.totalPoints = totalPoints;
	}

	public int[] getTerrainPoints() {
		return terrainPoints;
	}

	public void setTerrainPoints(int[] terrainPoints) {
		this.terrainPoints = terrainPoints;
	}

	public int[] getBuildingPoints() {
		return buildingPoints;
	}

	public void setBuildingPoints(int[] buildingPoints) {
		this.buildingPoints = buildingPoints;
	}

	public int[] getCityPoints() {
		return cityPoints;
	}

	public void setCityPoints(int[] cityPoints) {
		this.cityPoints = cityPoints;
	}

	public int[] getNumberOfTerrains() {
		return numberOfTerrains;
	}

	public void setNumberOfTerrains(int[] numberOfTerrains) {
		this.numberOfTerrains = numberOfTerrains;
	}

	public int[] getNumberOfBuildings() {
		return numberOfBuildings;
	}

	public void setNumberOfBuildings(int[] numberOfBuildings) {
		this.numberOfBuildings = numberOfBuildings;
	}

	public int[] getNumberOfCities() {
		return numberOfCities;
	}

	public void setNumberOfCities(int[] numberOfCities) {
		this.numberOfCities = numberOfCities;
	}

	public int[] getLeaders() {
		return leaders;
	}

	public void setLeaders(int[] leaders) {
		this.leaders = leaders;
	}
}
