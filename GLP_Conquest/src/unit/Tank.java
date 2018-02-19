package unit;

import datas.Resources;
import datas.Position;

public class Tank extends Unit{
	private float currentHealth;
	private Position position;
	private int ID;
	private int faction;
	private float remainingMovement;

	/*
	* Stats definition
	*/
	private static final int MAX_HEALTH_POINTS = 100;
	private static final float MOVEMENT = (float) 2.5;
	private static final int RANGE = 1;
	private static final int ATTACK = 100;
	private static final int DEFENSE = 65;
	private static final float VISION = 4;
	private static final int ATTRIBUTE = 3;
	private static final int[] CROSSABLE = {1,2,3,5,6,7,8,9};
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
	public Tank(Position position, int faction) {
		setID();
		setCurrentHealth(MAX_HEALTH_POINTS);
		setPosition(position);
		setFaction(faction);
		setRemainingMovement(MOVEMENT);	
	}
	
	public void setID() {
		//this.ID = getLastID()+1;
		//setID(this.ID);
	}
	
	public void setCurrentHealth(float currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setFaction(int faction) {
		this.faction = faction;
	}
	
	public void setRemainingMovement(float remainingMovement) {
		this.remainingMovement = remainingMovement;
	}

	public float getCurrentHealth() {
		return currentHealth;
	}

	public Position getPosition() {
		return position;
	}

	public int getID() {
		return ID;
	}

	public int getFaction() {
		return faction;
	}

	public float getRemainingMovement() {
		return remainingMovement;
	}

	public int getMaxHealthPoints() {
		return MAX_HEALTH_POINTS;
	}

	public float getMovement() {
		return MOVEMENT;
	}

	public int getRange() {
		return RANGE;
	}

	public int getAttack() {
		return ATTACK;
	}

	public int getDefense() {
		return DEFENSE;
	}

	public float getVision() {
		return VISION;
	}

	public int getAttribute() {
		return ATTRIBUTE;
	}

	public int[] getCrossable() {
		return CROSSABLE;
	}

	public int getMoneyUpkeep() {
		return MONEY_UPKEEP;
	}

	public int getFoodUpkeep() {
		return FOOD_UPKEEP;
	}

	public int getOilUpkeep() {
		return OIL_UPKEEP;
	}

	public int getElectrictyUpkeep() {
		return ELECTRICTY_UPKEEP;
	}
}