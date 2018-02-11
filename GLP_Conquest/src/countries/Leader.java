package countries;

public class Leader {

	private String name;
	private String ability;
	
	public Leader(String name, String ability) {
		super();
		this.name = name;
		this.ability = ability;
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
	
}
