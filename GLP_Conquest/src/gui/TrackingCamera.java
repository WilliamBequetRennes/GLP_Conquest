package gui;

import javafx.scene.layout.Pane;

public class TrackingCamera extends Pane{

	private int COEFFICIENT = 1;
	private int hTracking;
	private int vTracking;
	
	public TrackingCamera( int vTracking, int hTracking) {
		super();
		setVTracking(vTracking);
		setHTracking(hTracking);
	}

	public int getHTracking() {
		return hTracking;
	}

	public void setHTracking(int horizontalTracking) {
		this.hTracking = horizontalTracking;
	}

	public int getVTracking() {
		return vTracking;
	}

	public void setVTracking(int verticalTracking) {
		this.vTracking = verticalTracking;
	}
}
