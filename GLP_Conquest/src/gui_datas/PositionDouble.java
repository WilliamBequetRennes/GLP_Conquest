package gui_datas;

public class PositionDouble {
	private double x;
	private double y;
	
	public PositionDouble() {
		setX(0);
		setY(0);
	}
	public PositionDouble(double x, double y) {
		setX(x);
		setY(y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
