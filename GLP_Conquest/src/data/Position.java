package data;

public class Position {
	private int jPosition;
	private int iPosition;
	
	public Position(int iPosition, int jPosition) {
		setIPosition(iPosition);
		setJPosition(jPosition);
	}
	public Position() {
		this(0,0);
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

}
