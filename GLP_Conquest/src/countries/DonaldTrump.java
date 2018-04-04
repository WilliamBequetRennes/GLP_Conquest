package countries;

import countries.Leader;

public class DonaldTrump extends Leader{
	
	private final static int NUMBER=1;
	private static final int RANGE[]= {0,0,0,0,0,0,0,0};
	private static final int MOVEMENT[]= {0,0,0,0,0,0,0,0};
	private static float attack[]= {0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};
	private static float defense[]= {0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};
	private static float cost[]= {0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};
	private static final float FOOD=1f;
	private static final float OIL=1.1f;
	private static final float ELECTRICITY=1f;
	
	public DonaldTrump() {
		super(NUMBER, RANGE, MOVEMENT, attack, defense, cost, FOOD, OIL, ELECTRICITY);
	}

}