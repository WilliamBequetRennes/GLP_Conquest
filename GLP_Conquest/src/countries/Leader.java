package countries;

public class Leader {

	private String name;
	private String ability;
	private int number;
	
	public Leader(String name, String ability) {
		super();
		this.name = name;
		this.ability = ability;
		this.number = 0;
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
