package squares;

import data.Bonus;
import data.Position;
import data.Resources;

public class Desert extends Square{

	private final static int TYPE = 2;
	private final static float MOVEMENT_COST = 2;
	private final static float ATTACK_BONUS = 1;
	private final static float DEFENSE_BONUS = 0.9f;
	private final static int MAX_LEVEL = 0;
	private static final float MONEY = 0;
	private static final float FOOD = 0;
	private static final float OIL = 0;
	private static final float ELECTRICITY = 0;
	
	public Desert(Position position, int level, int faction, boolean unit) {
		super(TYPE, MOVEMENT_COST, new Bonus(ATTACK_BONUS,DEFENSE_BONUS),
				new Resources(MONEY, FOOD, OIL, ELECTRICITY), level, MAX_LEVEL,
				position, faction, unit);
	}
	public Desert(Position position) {
		this(position, 0, 0, false);
	}

}
