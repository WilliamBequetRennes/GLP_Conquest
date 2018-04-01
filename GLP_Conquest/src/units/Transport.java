package units;

import data.Position;
import data.Resources;

public class Transport extends Unit{

	/*
	* Stats definition
	*/
	private static final int MAX_HEALTH = 100;
	private static final float MAX_MOVEMENT = 4;
	private static final int RANGE = 0;
	private static final int ATTACK = 0;
	private static final int DEFENSE = 70;
	private static final float VISION = 4;
	private static final int ATTRIBUTE = 3;
	private static final int[] CROSSABLE = {0,1,2,3,4,5,6,7,8,9};
	private static int TYPE = 8;
	private static final String NAME = "Transport";
	/*
	* Cost definition
	*/
	private static final int MONEY_COST = 0;
	private static final int FOOD_COST = 0;
	private static final int OIL_COST = 0;
	private static final int ELECTRICTY_COST = 0;
	/*
	* Upkeep definition
	*/
	private static final int MONEY_UPKEEP = 0;
	private static final int FOOD_UPKEEP = 0;
	private static final int OIL_UPKEEP = 0;
	private static final int ELECTRICTY_UPKEEP = 0;

	/*
	* Default constructor
	*/
	public Transport(Position position, int faction) {
		super(MAX_HEALTH, position, faction, MAX_MOVEMENT, RANGE, ATTACK, DEFENSE, VISION, ATTRIBUTE,
				CROSSABLE, new Resources(MONEY_COST, FOOD_COST, OIL_COST, ELECTRICTY_COST),
				new Resources(MONEY_UPKEEP, FOOD_UPKEEP, OIL_UPKEEP, ELECTRICTY_UPKEEP), TYPE, NAME);
	}
}