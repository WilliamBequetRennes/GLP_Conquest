package movement;

public class IndexPosition {
	int xPosition;
	int yPosition;
	int cost;
	
	public IndexPosition(int xPosition, int yPosition) {
		setXPosition(xPosition);
		setYPosition(yPosition);
	}
	
	public void setXPosition(int xPosition){
		this.xPosition = xPosition;
	}
	
	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getXPosition(){
		return this.xPosition;
	}
	
	public int getYPosition() {
		return this.yPosition;
	}
	
	public int getCost() {
		return this.cost;
	}
}
