package datas;

public class Resources {
	private float money;
	private float food;
	private float oil;
	private float electricity;
	
	public Resources(float money, float food, float oil, float electricity) {
		setMoney(money);
		setFood(food);
		setOil(oil);
		setElectricity(electricity);
	}
	
	public Resources() {
		this(0,0,0,0);
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public float getFood() {
		return food;
	}

	public void setFood(float food) {
		this.food = food;
	}

	public float getOil() {
		return oil;
	}

	public void setOil(float oil) {
		this.oil = oil;
	}

	public float getElectricity() {
		return electricity;
	}

	public void setElectricity(float electricity) {
		this.electricity = electricity;
	}
}
