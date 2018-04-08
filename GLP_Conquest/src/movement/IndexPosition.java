package movement;

import java.util.ArrayList;

import data.Position;
import map.Map;

public class IndexPosition extends Position {
	private int iPosition;
	private int jPosition;
	private float localCost;
	private ArrayList<IndexPosition> localPath;
	
	public IndexPosition(int iPosition, int jPosition) {
		setJPosition(iPosition);
		setIPosition(jPosition);
		setDefaultCost();
		setLocalPath(new ArrayList<IndexPosition>());
	}
	public IndexPosition(Position position) {
		super(position.getIPosition(), position.getJPosition());
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
	
	public void calculateLocalCost(Map map) {
		ArrayList<IndexPosition> path = getLocalPath();
		float movementCost = 0;
		for(IndexPosition current : path) {
			movementCost += map.getSquareType(current.toPosition()).getMoveCost();
		}
		setLocalCost(movementCost);
	}
	
	public float getLocalCost() {
		return this.localCost;
	}
	
	public void setDefaultLocalPath() {
		this.localPath = null;
	}

	public void setLocalPath(ArrayList<IndexPosition> localPath) {
		this.localPath = localPath;
	}
	
	public void addLocalPath(IndexPosition position) {
		getLocalPath().add(position);
	}
	
	public ArrayList<IndexPosition> getLocalPath() {
		return this.localPath;
	}
	
	public Position toPosition() {
		Position position = new Position(iPosition,jPosition);
		return  position;
	}
	
}
