package gui;

import gui_datas.PositionDouble;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class TrackingCamera extends Pane{

	private int COEFFICIENT = 5;
	private double hTracking;
	private double vTracking;
	
	public TrackingCamera( double vTracking, double hTracking, PositionDouble positionDouble) {
		super();
		setVTracking(vTracking);
		setHTracking(hTracking);
		initializeTracking(positionDouble);
	}
	
	public void initializeTracking(PositionDouble positionDouble) {
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				double x = positionDouble.getX()+getHTracking()*COEFFICIENT;
				double y = positionDouble.getY()+getVTracking()*COEFFICIENT;
				positionDouble.setX(x);
				positionDouble.setY(y);
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				positionDouble.setX(0);
				positionDouble.setY(0);
			}
		});
	}

	public double getHTracking() {
		return hTracking;
	}

	public void setHTracking(double hTracking) {
		this.hTracking = hTracking;
	}

	public double getVTracking() {
		return vTracking;
	}

	public void setVTracking(double vTracking) {
		this.vTracking = vTracking;
	}
	
}