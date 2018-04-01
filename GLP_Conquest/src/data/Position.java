package data;

import movement.IndexPosition;

public class Position {
	private int jPosition;
	private int iPosition;
	
	public Position(int iPosition, int jPosition) {
		setIPosition(iPosition);
		setJPosition(jPosition);
	}
	public Position() {
		this(-1,-1);
	}
	public Position(int number) {
		this(number, number);
	}

	public int getJPosition() {
		return jPosition;
	}

	public void setJPosition(int jPosition) {
		this.jPosition = jPosition;
	}

	public int getIPosition() {
		return iPosition;
	}

	public void setIPosition(int iPosition) {
		this.iPosition = iPosition;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iPosition;
		result = prime * result + jPosition;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (iPosition != other.iPosition)
			return false;
		if (jPosition != other.jPosition)
			return false;
		return true;
	}
	
	public IndexPosition toIndexPosition() {
		IndexPosition index = new IndexPosition(iPosition,jPosition);
		return index;
	}
	
}
