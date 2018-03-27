package data;

public class FightForecasts {
	
	private double attackerCurrentHealth;
	private double defenderCurrentHealth;
	private double attackerTakenDamages;
	private double defenderTakenDamages;
	private double attackerRemainingHealth;
	private double defenderRemainingHealth;
	private Position attackerPosition;
	private Position defenderPosition;
	
	public FightForecasts(double attackerCurrentHealth,  double defenderCurrentHealth, 
			double attackerTakenDamages, double defenderTakenDamages, double attackerRemainingHealth, 
			double defenderRemainingHealth, Position attackerPosition, Position defenderPosition) {
		
		setAttackerCurrentHealth(attackerCurrentHealth);
		setAttackerTakenDamages(attackerTakenDamages);
		setAttackerRemainingHealth(attackerRemainingHealth);
		setAttackerPosition(attackerPosition);
		setDefenderCurrentHealth(defenderCurrentHealth);
		setDefenderTakenDamages(defenderTakenDamages);
		setDefenderRemainingHealth(defenderRemainingHealth);
		setDefenderPosition(defenderPosition);
	}

	public double getAttackerCurrentHealth() {
		return attackerCurrentHealth;
	}

	public void setAttackerCurrentHealth(double attackerCurrentHealth) {
		this.attackerCurrentHealth = attackerCurrentHealth;
	}

	public double getDefenderCurrentHealth() {
		return defenderCurrentHealth;
	}

	public void setDefenderCurrentHealth(double defenderCurrentHealth) {
		this.defenderCurrentHealth = defenderCurrentHealth;
	}

	public double getAttackerTakenDamages() {
		return attackerTakenDamages;
	}

	public void setAttackerTakenDamages(double attackerTakenDamages) {
		this.attackerTakenDamages = attackerTakenDamages;
	}

	public double getDefenderTakenDamages() {
		return defenderTakenDamages;
	}

	public void setDefenderTakenDamages(double defenderTakenDamages) {
		this.defenderTakenDamages = defenderTakenDamages;
	}

	public double getAttackerRemainingHealth() {
		return attackerRemainingHealth;
	}

	public void setAttackerRemainingHealth(double attackerRemainingHealth) {
		this.attackerRemainingHealth = attackerRemainingHealth;
	}

	public double getDefenderRemainingHealth() {
		return defenderRemainingHealth;
	}

	public void setDefenderRemainingHealth(double defenderRemainingHealth) {
		this.defenderRemainingHealth = defenderRemainingHealth;
	}

	public Position getAttackerPosition() {
		return attackerPosition;
	}

	public void setAttackerPosition(Position attackerPosition) {
		this.attackerPosition = attackerPosition;
	}

	public Position getDefenderPosition() {
		return defenderPosition;
	}

	public void setDefenderPosition(Position defenderPosition) {
		this.defenderPosition = defenderPosition;
	}

	
}
