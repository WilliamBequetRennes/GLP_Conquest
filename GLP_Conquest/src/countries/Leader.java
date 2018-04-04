package countries;

public abstract class Leader {

	private int number;
	private float attackCoeff[];
	private float defenseCoeff[];
	private float costCoeff[];
	private float resourcesCoeff;
	private float foodCoeff;
	private float oilCoeff;
	private float electricityCoeff;
	private int rangeChanger[];
	private int movementChanger[];

	public Leader(int NUMBER, int RANGE[], int MOVEMENT[], float[] attack, float[] defense, float[] cost, float FOOD, float OIL, float ELECTRICITY) {
		setNumber(NUMBER);
		setRangeChanger(RANGE);
		setMovementChanger(MOVEMENT);
		setAttackCoeff(attack);
		setDefenseCoeff(defense);
		setCostCoeff(cost);
		setFoodCoeff(FOOD);
		setOilCoeff(OIL);
		setElectricityCoeff(ELECTRICITY);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float[] getAttackCoeff() {
		return attackCoeff;
	}

	public void setAttackCoeff(float[] attackCoeff) {
		this.attackCoeff = attackCoeff;
	}

	public float[] getDefenseCoeff() {
		return defenseCoeff;
	}

	public void setDefenseCoeff(float[] defenseCoeff) {
		this.defenseCoeff = defenseCoeff;
	}

	public float[] getCostCoeff() {
		return costCoeff;
	}

	public void setCostCoeff(float[] costCoeff) {
		this.costCoeff = costCoeff;
	}

	public float getResourcesCoeff() {
		return resourcesCoeff;
	}

	public void setResourcesCoeff(float resourcesCoeff) {
		this.resourcesCoeff = resourcesCoeff;
	}

	public float getFoodCoeff() {
		return foodCoeff;
	}

	public void setFoodCoeff(float foodCoeff) {
		this.foodCoeff = foodCoeff;
	}

	public float getOilCoeff() {
		return oilCoeff;
	}

	public void setOilCoeff(float oilCoeff) {
		this.oilCoeff = oilCoeff;
	}

	public float getElectricityCoeff() {
		return electricityCoeff;
	}

	public void setElectricityCoeff(float nuclearPlantCoeff) {
		this.electricityCoeff = nuclearPlantCoeff;
	}

	public int[] getRangeChanger() {
		return rangeChanger;
	}

	public void setRangeChanger(int[] rangeChanger) {
		this.rangeChanger = rangeChanger;
	}

	public int[] getMovementChanger() {
		return movementChanger;
	}

	public void setMovementChanger(int[] movementChanger) {
		this.movementChanger = movementChanger;
	}
	
}