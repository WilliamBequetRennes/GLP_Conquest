package units;

import data.Position;
import data.Resources;

public class Sniper extends Unit{

	/*
	* Stats definition
	*/
	private static final int MAX_HEALTH = 100;
	private static final float MAX_MOVEMENT = 3;
	private static final int RANGE = 4;
	private static final int ATTACK = 95;
	private static final int DEFENSE = 25;
	private static final float VISION = 4;
	private static final int ATTRIBUTE = 1;
	private static final int[] CROSSABLE = {0,1,2,3,4,5,6,7,8,9};
	private static final int TYPE = 1;
	private static final String NAME = "Sniper";
	/*
	* Cost definition
	*/
	private static final int MONEY_COST = 900;
	private static final int FOOD_COST = 900;
	private static final int OIL_COST = 0;
	private static final int ELECTRICTY_COST = 0;
	/*
	* Upkeep definition
	*/
	private static final int MONEY_UPKEEP = 0;
	private static final int FOOD_UPKEEP = 200;
	private static final int OIL_UPKEEP = 0;
	private static final int ELECTRICTY_UPKEEP = 0;

	/*
	* Default constructor
	*/
	public Sniper(Position position, int faction) {
		super(MAX_HEALTH, position, faction, MAX_MOVEMENT, RANGE, ATTACK, DEFENSE, VISION, ATTRIBUTE,
				CROSSABLE, new Resources(MONEY_COST, FOOD_COST, OIL_COST, ELECTRICTY_COST),
				new Resources(MONEY_UPKEEP, FOOD_UPKEEP, OIL_UPKEEP, ELECTRICTY_UPKEEP), TYPE, NAME);
	}
}