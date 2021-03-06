package units;

import data.Position;
import data.Resources;

public class Destroyer extends Unit{
	/*
	* Stats definition
	*/
	private static final int MAX_HEALTH = 100;
	private static final float MAX_MOVEMENT = 3;
	private static final int RANGE = 3;
	private static final int ATTACK = 100;
	private static final int DEFENSE = 75;
	private static final float VISION = 4;
	private static final int ATTRIBUTE = 3;
	private static final int[] CROSSABLE = {0,9};
	private static final int TYPE = 6;
	private static final String NAME = "Destroyer";
	/*
	* Cost definition
	*/
	private static final int MONEY_COST = 1500;
	private static final int FOOD_COST = 0;
	private static final int OIL_COST = 1500;
	private static final int ELECTRICTY_COST = 0;
	/*
	* Upkeep definition
	*/
	private static final int MONEY_UPKEEP = 250;
	private static final int FOOD_UPKEEP = 0;
	private static final int OIL_UPKEEP = 400;
	private static final int ELECTRICTY_UPKEEP = 0;

	/*
	* Default constructor
	*/
	public Destroyer(Position position, int faction) {
		super(MAX_HEALTH, position, faction, MAX_MOVEMENT, RANGE, ATTACK, DEFENSE, VISION, ATTRIBUTE,
				CROSSABLE, new Resources(MONEY_COST, FOOD_COST, OIL_COST, ELECTRICTY_COST),
				new Resources(MONEY_UPKEEP, FOOD_UPKEEP, OIL_UPKEEP, ELECTRICTY_UPKEEP), TYPE, NAME);
	}
}