package datas;

public class Position {
	private int jPosition;
	private int iPosition;
	private Position[] localPath;
	private float localCost;
	
	public Position(int jPosition, int iPosition) {
		setJPosition(jPosition);
		setIPosition(iPosition);
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
	
	public void setLocalPath(Position[] path) {
		this.localPath = path;
	}
	
	public void addLocalPath(Position position) {
		this.
	}

}
