package countries;

import java.util.HashMap;

import datas.Position;
import datas.Resources;
import squares.Square;
import units.Unit;

public class Country {
	private final static int STARTING_MONEY = 5000;
	private final static int STARTING_FOOD = 5000;
	private final static int STARTING_OIL = 5000;
	private final static int STARTING_ELECTRICITY = 5000;

	private int player;
	private Leader leader;
	private Resources resources;
	private Resources gains;
	private Resources spents;
	private int squareNumber;
	private HashMap<Position,Square> buildings;
	private HashMap<Position,Unit> units;
	
	public Country(Leader leader, int player, Resources resources, Resources gains,
			Resources spents, int squareNumber, HashMap<Position,Square> buildings,
			HashMap<Position,Unit> units) {
		setPlayer(player);
		setLeader(leader);
		setResources(resources);
		setGains(gains);
		setSpents(spents);
		setSquareNumber(squareNumber);
		setBuildings(buildings);
		setUnits(units);
	}
	public Country(Leader leader, int player, Resources gains,
			Resources spents, int squareNumber, HashMap<Position,Square> buildings,
			HashMap<Position,Unit> units) {
			this(leader,player,new Resources(STARTING_MONEY, STARTING_FOOD, STARTING_OIL,
				STARTING_ELECTRICITY),gains,spents,squareNumber,buildings,units);
		}
	public Country(Leader leader, int player) {
		this(leader, player, new Resources(), new Resources(), 0, new HashMap<Position,Square>(), new HashMap<Position,Unit>());
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	public Resources getResources() {
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

	public Resources getGains() {
		return gains;
	}

	public void setGains(Resources gains) {
		this.gains = gains;
	}

	public Resources getSpents() {
		return spents;
	}

	public void setSpents(Resources spents) {
		this.spents = spents;
	}

	public int getSquareNumber() {
		return squareNumber;
	}

	public void setSquareNumber(int squareNumber) {
		this.squareNumber = squareNumber;
	}
	public HashMap<Position, Square> getBuildings() {
		return buildings;
	}
	public void setBuildings(HashMap<Position, Square> buildings) {
		this.buildings = buildings;
	}
	public HashMap<Position, Unit> getUnits() {
		return units;
	}
	public void setUnits(HashMap<Position, Unit> units) {
		this.units = units;
	}

}