package countries;

import countries.Leader;


public class VladimirPutin extends Leader{
	
	private final static int NUMBER=5;
	private static final int RANGE[]= {0,0,0,0,0,0,0,0};
	private static final int MOVEMENT[]= {0,0,0,0,0,0,0,0};
	private static float attack[]= {1.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f};
	private static float defense[]= {1.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f};
	private static float cost[]= {1.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f};
	private static final float FOOD=1f;
	private static final float OIL=1f;
	private static final float ELECTRICITY=1.1f;
	
	public VladimirPutin() {
		super(NUMBER, RANGE, MOVEMENT, attack, defense, cost, FOOD, OIL, ELECTRICITY);
	}

}