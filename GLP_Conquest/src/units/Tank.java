package units;

import datas.Resources;
import datas.Position;

public class Tank extends Unit{
	/*
	* Stats definition
	*/
	private static final int MAX_HEALTH = 100;
	private static final float MAX_MOVEMENT = 2.5f;
	private static final int RANGE = 1;
	private static final int ATTACK = 100;
	private static final int DEFENSE = 65;
	private static final float VISION = 4;
	private static final int ATTRIBUTE = 3;
	private static final int[] CROSSABLE = {1,2,3,5,6,7,8,9};
	private static final int TYPE = 4;
	/*
	* Cost definition
	*/
	private static final int MONEY_UPKEEP = 0;
	private static final int FOOD_UPKEEP = 0;
	private static final int OIL_UPKEEP = 0;
	private static final int ELECTRICTY_UPKEEP = 0;
	
	/*
	* Default constructor
	*/
	public Tank(Position position, int id, int faction) {
		super(MAX_HEALTH, position, id, faction, MAX_MOVEMENT, RANGE, ATTACK, DEFENSE, VISION, ATTRIBUTE, CROSSABLE,
				new Resources(MONEY_UPKEEP, FOOD_UPKEEP, OIL_UPKEEP, ELECTRICTY_UPKEEP), TYPE);
	}
}