package unit;

import datas.Resources;
import datas.Position;

public interface Unit{	
	
	public Unit();
	
	public Unit(float currentHealth, Position position, int iD, int faction, float remainingMovement, int maxHealth,
			float movement, int range, int attack, int defense, float vision, int attribute, int[] crossable,
			int moneyUpkeep, int foodUpkeep, int oilUpkeep, int electricityUpkeep);
	public float getCurrentHealth();
	public void setCurrentHealth(float currentHealth);
	public Position getPosition();
	public void setPosition(Position position);
	public int getID();
	public void setID(int iD);
	public int getFaction();
	public void setFaction(int faction);
	public float getRemainingMovement();
	public void setRemainingMovement(float remainingMovement);
	public int getMaxHealth();
	public void setMaxHealth(int maxHealth);
	public float getMovement();
	public void setMovement(float movement);
	public int getRange();
	public void setRange(int range);
	public int getAttack();
	public void setAttack(int attack);
	public int getDefense();
	public void setDefense(int defense);
	public float getVision();
	public void setVision(float vision);
	public int getAttribute();
	public void setAttribute(int attribute);
	public int[] getCrossable();
	public void setCrossable(int[] crossable);
	public int getMoneyUpkeep();
	public void setMoneyUpkeep(int moneyUpkeep);
	public int getFoodUpkeep();
	public void setFoodUpkeep(int foodUpkeep);
	public int getOilUpkeep();
	public void setOilUpkeep(int oilUpkeep);
	public int getElectricityUpkeep();
	public void setElectricityUpkeep(int electricityUpkeep);	
}