package movement;

import java.util.ArrayList;

import datas.Position;

public class IndexPosition extends Position {
	private int jPosition;
	private int iPosition;
	private float localCost;
	private ArrayList<Position> localPath;
	
	public IndexPosition(int jPosition, int iPosition) {
		setJPosition(jPosition);
		setIPosition(iPosition);
		setDefaultCost();
		setDefaultLocalPath();
	}
	public IndexPosition() {
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
	
	public void setDefaultCost() {
		this.localCost = 100;
	}
	
	public void setLocalCost(float localCost) {
		this.localCost = localCost;
	}
	
	public float getLocalCost() {
		return this.localCost;
	}
	
	public void setDefaultLocalPath() {
		this.localPath = null;
	}

	public void setLocalPath(ArrayList<Position> localPath) {
		this.localPath = localPath;
	}
	
	public void addLocalPath(Position position) {
		this.localPath.add(position);
	}
	
	public ArrayList<Position> getLocalPath() {
		return this.localPath;
	}
}
