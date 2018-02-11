package datas;

public class Bonus {
	//stats multipliers
	private float attack;
	private float defense;
	
	public Bonus(float attack, float defense) {
		setAttack(attack);
		setDefense(defense);
	}
	
	public Bonus() {
		this(1,1);
	}
	
	public float getAttack() {
		return attack;
	}
	public void setAttack(float attack) {
		this.attack = attack;
	}
	public float getDefense() {
		return defense;
	}
	public void setDefense(float defense) {
		this.defense = defense;
	}
	
	
}
