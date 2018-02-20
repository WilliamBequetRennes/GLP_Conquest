package datas;

public class Position {
	private int jPosition;
	private int iPosition;
	private Position[] localPath;
	private float localCost;
	
	public Position(int jPosition, int iPosition) {
		setJPosition(jPosition);
		setIPosition(iPosition);
		setDefaultLocalPath();
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
	
	public void setDefaultLocalPath() {
		this.localPath = {};
	}
	
	public void setLocalPath(Position[] path) {
		this.localPath = path;
	}
	
	public void addLocalPath(Position position) {
		this.localPath += position;
	}

	public Position[] getLocalPath() {
		return this.localPath;
	}
	
	public void setDefaultLocalCost() {
		this.localCost = 100;
	}
	
	public void setLocalCost(float cost) {
		this.localCost = cost;
	}
	
	public float getLocalCost() {
		return this.localCost;
	}
}
