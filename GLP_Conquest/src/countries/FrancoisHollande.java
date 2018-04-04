package countries;

import countries.Leader;

public class FrancoisHollande extends Leader{
	
	private final static int NUMBER=2;
	private final static int RANGE[]= {0,0,0,0,0,0,0,0};
	private final static int MOVEMENT[]= {0,0,0,0,0,0,0,0};
	private static float attack[]= {0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};
	private static float defense[]= {0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};
	private static float cost[]= {1.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f};
	private static final float FOOD=0.9f;
	private static final float OIL=0.9f;
	private static final float ELECTRICITY=0.9f;
	
	public FrancoisHollande() {
		super(NUMBER, RANGE, MOVEMENT, attack, defense, cost, FOOD, OIL, ELECTRICITY);
	}

}