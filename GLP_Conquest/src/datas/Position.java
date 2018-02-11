package datas;

public class Position {
	private int xPosition;
	private int yPosition;
	
	public Position(int xPosition, int yPosition) {
		setXPosition(xPosition);
		setYPosition(yPosition);
	}
	public Position() {
		this(0,0);
	}

	public int getXPosition() {
		return xPosition;
	}

	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}

}
