package gui;

import gui_datas.ScreenSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class GlobalBlock extends GridPane{

	private TrackingCamera northWestTracking;
	private TrackingCamera northTracking;
	private TrackingCamera northEastTracking;
	private TrackingCamera eastTracking;
	private TrackingCamera southEastTracking;
	private TrackingCamera southTracking;
	private TrackingCamera southWestTracking;
	private TrackingCamera westTracking;
	
	private CentralBlock centralBlock;

	private ScreenSize screenSize;
	private int mapSize;
	
	public GlobalBlock(ScreenSize screenSize, int mapSize) {
		super();
		setMapSize(mapSize);
		setScreenSize(screenSize);
		initializeTracking();
		setCentralBlock(new CentralBlock(getScreenSize(), getMapSize()));
		add(centralBlock, 1, 1);
		
		getNorthWestTracking().setMinSize(10, 10);
		getCentralBlock().setMinSize(getScreenSize().getWidth()-20, getScreenSize().getHeight()-20);
		getCentralBlock().setStyle("-fx-background-color: blue");
	}
	public void initializeTracking() {
		setNorthWestTracking(new TrackingCamera(-1, -1));
		setNorthTracking(new TrackingCamera(-1, 0));
		setNorthEastTracking(new TrackingCamera(-1, 1));
		setEastTracking(new TrackingCamera(0, 1));
		setSouthEastTracking(new TrackingCamera(1, 1));
		setSouthTracking(new TrackingCamera(1, 0));
		setSouthWestTracking(new TrackingCamera(1, -1));
		setWestTracking(new TrackingCamera(0, -1));
		
		add(getNorthWestTracking(), 0, 0);
		add(getNorthTracking(), 1, 0);
		add(getNorthEastTracking(), 2, 0);
		add(getEastTracking(), 2, 1);
		add(getSouthEastTracking(), 2, 2);
		add(getSouthTracking(), 1, 2);
		add(getSouthWestTracking(), 0, 2);
		add(getWestTracking(), 0, 1);
	}
	
	
	public ScreenSize getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(ScreenSize screenSize) {
		this.screenSize = screenSize;
	}
	public TrackingCamera getNorthWestTracking() {
		return northWestTracking;
	}
	public void setNorthWestTracking(TrackingCamera northWestTracking) {
		this.northWestTracking = northWestTracking;
	}
	public TrackingCamera getNorthTracking() {
		return northTracking;
	}
	public void setNorthTracking(TrackingCamera northTracking) {
		this.northTracking = northTracking;
	}
	public TrackingCamera getNorthEastTracking() {
		return northEastTracking;
	}
	public void setNorthEastTracking(TrackingCamera northEastTracking) {
		this.northEastTracking = northEastTracking;
	}
	public TrackingCamera getEastTracking() {
		return eastTracking;
	}
	public void setEastTracking(TrackingCamera eastTracking) {
		this.eastTracking = eastTracking;
	}
	public TrackingCamera getSouthEastTracking() {
		return southEastTracking;
	}
	public void setSouthEastTracking(TrackingCamera southEastTracking) {
		this.southEastTracking = southEastTracking;
	}
	public TrackingCamera getSouthTracking() {
		return southTracking;
	}
	public void setSouthTracking(TrackingCamera southTracking) {
		this.southTracking = southTracking;
	}
	public TrackingCamera getSouthWestTracking() {
		return southWestTracking;
	}
	public void setSouthWestTracking(TrackingCamera southWestTracking) {
		this.southWestTracking = southWestTracking;
	}
	public TrackingCamera getWestTracking() {
		return westTracking;
	}
	public void setWestTracking(TrackingCamera westTracking) {
		this.westTracking = westTracking;
	}
	public CentralBlock getCentralBlock() {
		return centralBlock;
	}
	public void setCentralBlock(CentralBlock centralBlock) {
		this.centralBlock = centralBlock;
	}
	public int getMapSize() {
		return mapSize;
	}
	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}
	
}

