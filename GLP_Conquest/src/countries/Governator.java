package countries;

import countries.Leader;

public class Governator extends Leader{
	
	private final static int NUMBER=4;
	private static final int RANGE[]= {0,-1,0,-1,0,0,-1,-1};
	private static final int MOVEMENT[]= {0,0,0,0,0,0,0,0};
	private static float attack[]= {1.2f,0f,1.2f,0f,1.2f,1.2f,0f,0f};
	private static float defense[]= {1.2f,0f,1.2f,0f,1.2f,1.2f,0f,0f};
	private static float cost[]= {1f,1f,1f,1f,1f,1f,1f,1f};
	private static final float FOOD=1f;
	private static final float OIL=1f;
	private static final float ELECTRICITY=1f;
	
	public Governator() {
		super(NUMBER, RANGE, MOVEMENT, attack, defense, cost, FOOD, OIL, ELECTRICITY);
	}
	
}