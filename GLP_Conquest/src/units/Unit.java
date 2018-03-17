package units;

import data.Position;
import data.Resources;

public abstract class Unit{	

	private float maxHealth;
	private float currentHealth;
	private Position position;
	private int id;
	private int faction;
	private float maxMovement;
	private float movement;
	public static int range;
	private int attack;
	private int defense;
	private float vision;
	private int attribute;
	private int[] crossable;
	private Resources cost;
	private int type;
	
	public Unit(float maxHealth, float currentHealth, Position position, int id, int faction, float maxMovement,
			float movement, int range, int attack, int defense, float vision, int attribute, int[] crossable,
			Resources cost, int type) {
		setMaxHealth(maxHealth);
		setCurrentHealth(currentHealth);
		setPosition(position);
		setId(id);
		setFaction(faction);
		setMaxMovement(maxMovement);
		setMovement(movement);
		setRange(range);
		setAttack(attack);
		setDefense(defense);
		setVision(vision);
		setAttribute(attribute);
		setCrossable(crossable);
		setCost(cost);
		setType(type);
	}
	public Unit(float maxHealth, Position position, int id, int faction, float maxMovement, int range,
			int attack, int defense, float vision, int attribute, int[] crossable,
			Resources cost, int type) {
		this(maxHealth, maxHealth, position, id, faction, maxMovement, maxMovement, range, attack, defense, 
				vision, attribute, crossable, cost, type);
	}

	public float getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFaction() {
		return faction;
	}

	public void setFaction(int faction) {
		this.faction = faction;
	}

	public float getMaxMovement() {
		return maxMovement;
	}

	public void setMaxMovement(float maxMovement) {
		this.maxMovement = maxMovement;
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

	public Resources getCost() {
		return cost;
	}

	public void setCost(Resources cost) {
		this.cost = cost;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}