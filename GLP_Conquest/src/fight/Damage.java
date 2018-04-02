package fight;

public class Damage {
	private float minAttackDamage;
	private float maxAttackDamage;
	private float minRevengeDamage;
	private float maxRevengeDamage;
	private float attackerMinRemainingHealth;
	private float attackerMaxRemainingHealth;
	private float defenderMinRemainingHealth;
	private float defenderMaxRemainingHealth;
	
	public Damage(float minAttackDamage, float maxAttackDamage,  float minRevengeDamage,float maxRevengeDamage, 
			float attackerMinRemainingHealth, float attackerMaxRemainingHealth, float defenderMinRemainingHealth, float defenderMaxRemainingHealth ) {
		setMinAttackDamage(minAttackDamage);
		setMaxAttackDamage(maxAttackDamage);
		setMinRevengeDamage(minRevengeDamage);
		setMaxRevengeDamage(maxRevengeDamage);
		setAttackerMinRemainingHealth(attackerMinRemainingHealth);
		setAttackerMaxRemainingHealth(attackerMaxRemainingHealth);
		setDefenderMinRemainingHealth(defenderMinRemainingHealth);
		setDefenderMaxRemainingHealth(defenderMaxRemainingHealth);
	}
	public Damage() {
		this(0,0,0,0,0,0,0,0);
	}
	
	public void setMinAttackDamage(float minAttackDamage) {
		this.minAttackDamage = minAttackDamage;
	}
	
	public float getMinAttackDamage() {
		return this.minAttackDamage;
	}
	public void setMaxAttackDamage(float maxAttackDamage) {
		this.maxAttackDamage = maxAttackDamage;
	}
	
	public float getMaxAttackDamage() {
		return this.maxAttackDamage;
	}
	public void setMinRevengeDamage(float minRevengeDamage) {
		this.minRevengeDamage = minRevengeDamage;
	}
	
	public float getMinRevengeDamage() {
		return this.minRevengeDamage;
	}
	public void setMaxRevengeDamage(float maxRevengeDamage) {
		this.maxRevengeDamage = maxRevengeDamage;
	}
	
	public float getMaxRevengeDamage() {
		return this.maxRevengeDamage;
	}
	public void setAttackerMinRemainingHealth(float attackerMinRemainingHealth) {
		this.attackerMinRemainingHealth = attackerMinRemainingHealth;
	}
	
	public float getAttackerMinRemainingHealth() {
		return this.attackerMinRemainingHealth;
	}
	public void setAttackerMaxRemainingHealth(float attackerMaxRemainingHealth) {
		this.attackerMaxRemainingHealth = attackerMaxRemainingHealth;
	}
	
	public float getAttackerMaxRemainingHealth() {
		return this.attackerMaxRemainingHealth;
	}
	public void setDefenderMinRemainingHealth(float defenderMinRemainingHealth) {
		this.defenderMinRemainingHealth = defenderMinRemainingHealth;
	}
	
	public float getDefenderMinRemainingHealth() {
		return this.defenderMinRemainingHealth;
	}
	public void setDefenderMaxRemainingHealth(float defenderMaxRemainingHealth) {
		this.defenderMaxRemainingHealth = defenderMaxRemainingHealth;
	}
	
	public float getDefenderMaxRemainingHealth() {
		return this.defenderMaxRemainingHealth;
	}
	
	
}
