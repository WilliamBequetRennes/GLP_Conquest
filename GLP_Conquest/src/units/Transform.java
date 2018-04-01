package units;

import data.Position;

public class Transform extends Transport{
	
	private Unit unit;

	public Transform(Position position, int faction, Unit unit) {
		super(position, faction);
		setUnit(unit);
		setType(9);
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public Unit getUnit() {
		return this.unit;
	}

}
