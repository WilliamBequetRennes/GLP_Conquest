package squares;

import datas.Bonus;
import datas.Position;
import datas.Resources;

public class City extends Square{

	private final static int TYPE = 9;
	private final static float MOVEMENT_COST = 1.5f;
	private final static float ATTACK_BONUS = 1;
	private final static float DEFENSE_BONUS = 1.3f;
	private final static int MAX_LEVEL = 3;
	private static final float MONEY = 500;
	private static final float FOOD = 500;
	private static final float OIL = 500;
	private static final float ELECTRICITY = 500;
	
	public City(Position position, int level, int faction, boolean unit) {
		super(TYPE, MOVEMENT_COST, new Bonus(ATTACK_BONUS,DEFENSE_BONUS),
				new Resources(MONEY, FOOD, OIL, ELECTRICITY), level, MAX_LEVEL,
				position, faction, unit);
	}
	public City(Position position) {
		this(position, 0, 0, false);
	}

}
