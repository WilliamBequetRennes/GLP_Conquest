package gui_data;

public class PositionDouble {
	private double x;
	private double y;
	private int i;
	private int j;
	
	public PositionDouble() {
		setX(0);
		setY(0);
		setI(0);
		setJ(0);
		
	}
	public PositionDouble(double x, double y, int i, int j) {
		setX(x);
		setY(y);
		setI(i);
		setJ(j);
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
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
}
