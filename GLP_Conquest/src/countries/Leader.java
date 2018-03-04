package countries;

public class Leader {

	private String name;
	private String ability;
	private int number;
	
	public Leader(String name, String ability) {
		super();
		setName(name);
		setAbility(ability);
		setNumber(0);
	}
	public Leader(String name, String ability, int number) {
		super();
		setName(name);
		setAbility(ability);
		setNumber(number);;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
