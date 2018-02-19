package unit;

import datas.Resources;
import datas.Position;

public abstract class Unit{
	private float currentHealth;
	private Position position;
	private int ID;
	private int faction;
	private float remainingMovement;

	/*
	* Stats definition
	*/
	private int maxHealth;
	private float movement;
	private int range;
	private int attack;
	private int defense;
	private float vision;
	private int attribute;
	private int[] crossable;
	/*
	* Cost definition
	*/
	private int moneyUpkeep;
	private int foodUpkeep;
	private int oilUpkeep;
	private int electricityUpkeep;	
	
	public Unit() {
		
	}
	
	public Unit(float currentHealth, Position position, int iD, int faction, float remainingMovement, int maxHealth,
			float movement, int range, int attack, int defense, float vision, int attribute, int[] crossable,
			int moneyUpkeep, int foodUpkeep, int oilUpkeep, int electricityUpkeep) {
		super();
		this.currentHealth = currentHealth;
		this.position = position;
		ID = iD;
		this.faction = faction;
		this.remainingMovement = remainingMovement;
		this.maxHealth = maxHealth;
		this.movement = movement;
		this.range = range;
		this.attack = attack;
		this.defense = defense;
		this.vision = vision;
		this.attribute = attribute;
		this.crossable = crossable;
		this.moneyUpkeep = moneyUpkeep;
		this.foodUpkeep = foodUpkeep;
		this.oilUpkeep = oilUpkeep;
		this.electricityUpkeep = electricityUpkeep;
	}
	public float getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(float currentHealth) {
		this.currentHealth = currentHealth;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getFaction() {
		return faction;
	}
	public void setFaction(int faction) {
		this.faction = faction;
	}
	public float getRemainingMovement() {
		return remainingMovement;
	}
	public void setRemainingMovement(float remainingMovement) {
		this.remainingMovement = remainingMovement;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public float getMovement() {
		return movement;
	}
	public void setMovement(float movement) {
		this.movement = movement;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public float getVision() {
		return vision;
	}
	public void setVision(float vision) {
		this.vision = vision;
	}
	public int getAttribute() {
		return attribute;
	}
	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}
	public int[] getCrossable() {
		return crossable;
	}
	public void setCrossable(int[] crossable) {
		this.crossable = crossable;
	}
	public int getMoneyUpkeep() {
		return moneyUpkeep;
	}
	public void setMoneyUpkeep(int moneyUpkeep) {
		this.moneyUpkeep = moneyUpkeep;
	}
	public int getFoodUpkeep() {
		return foodUpkeep;
	}
	public void setFoodUpkeep(int foodUpkeep) {
		this.foodUpkeep = foodUpkeep;
	}
	public int getOilUpkeep() {
		return oilUpkeep;
	}
	public void setOilUpkeep(int oilUpkeep) {
		this.oilUpkeep = oilUpkeep;
	}
	public int getElectricityUpkeep() {
		return electricityUpkeep;
	}
	public void setElectricityUpkeep(int electricityUpkeep) {
		this.electricityUpkeep = electricityUpkeep;
	}
	
	
}