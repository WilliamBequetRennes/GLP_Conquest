package movement;

import java.util.ArrayList;
import java.util.Iterator;

import game.Game;
import map.Map;
import units.Unit;

public class Graph {
	private ArrayList<IndexPosition> graph;
	private Map map;
	private Game game;
	
	public Graph(IndexPosition position, Map map, Game game) {
		if (parity(position.getIPosition())){
			this.graph = evenYGraph(position);
		}
		else {
			this.graph = unevenYGraph(position);
		}
		setMap(map);
		setGame(game);
	}
	
	public boolean parity(int YPosition) {
		boolean test;
		if (YPosition % 2 == 0) {
			test =  true;
		}
		else {
			test = false;
		}
		return test;	
	}
	/*
	 * Possible de fusionner les deux cas et de faire la boucle correctement
	 */
	public ArrayList<IndexPosition> evenYGraph(IndexPosition position){
		int xPosition = position.getJPosition();
		int yPosition = position.getIPosition();
		ArrayList<IndexPosition> graph = new ArrayList<IndexPosition>();
		graph.add(new IndexPosition(xPosition-3, yPosition));
		graph.add(new IndexPosition(xPosition-3, yPosition-1));
		graph.add(new IndexPosition(xPosition-3, yPosition+1));
		graph.add(new IndexPosition(xPosition-2, yPosition-3));
		graph.add(new IndexPosition(xPosition-2, yPosition-2));
		graph.add(new IndexPosition(xPosition-2, yPosition-1));
		graph.add(new IndexPosition(xPosition-2, yPosition));
		graph.add(new IndexPosition(xPosition-2, yPosition+1));
		graph.add(new IndexPosition(xPosition-2, yPosition+2));
		graph.add(new IndexPosition(xPosition-2, yPosition+3));
		graph.add(new IndexPosition(xPosition-1, yPosition-3));
		graph.add(new IndexPosition(xPosition-1, yPosition-2));
		graph.add(new IndexPosition(xPosition-1, yPosition-1));
		graph.add(new IndexPosition(xPosition-1, yPosition));
		graph.add(new IndexPosition(xPosition-1, yPosition+1));
		graph.add(new IndexPosition(xPosition-1, yPosition+2));
		graph.add(new IndexPosition(xPosition-1, yPosition+3));
		graph.add(new IndexPosition(xPosition, yPosition-3));
		graph.add(new IndexPosition(xPosition, yPosition-2));
		graph.add(new IndexPosition(xPosition, yPosition-1));
		graph.add(new IndexPosition(xPosition, yPosition+1));
		graph.add(new IndexPosition(xPosition, yPosition+2));
		graph.add(new IndexPosition(xPosition, yPosition+3));
		graph.add(new IndexPosition(xPosition+1, yPosition-3));
		graph.add(new IndexPosition(xPosition+1, yPosition-2));
		graph.add(new IndexPosition(xPosition+1, yPosition-1));
		graph.add(new IndexPosition(xPosition+1, yPosition));
		graph.add(new IndexPosition(xPosition+1, yPosition+1));
		graph.add(new IndexPosition(xPosition+1, yPosition+2));
		graph.add(new IndexPosition(xPosition+1, yPosition+3));
		graph.add(new IndexPosition(xPosition+2, yPosition-2));
		graph.add(new IndexPosition(xPosition+2, yPosition-1));
		graph.add(new IndexPosition(xPosition+2, yPosition));
		graph.add(new IndexPosition(xPosition+2, yPosition+1));
		graph.add(new IndexPosition(xPosition+2, yPosition+2));
		graph.add(new IndexPosition(xPosition+3, yPosition));

		return graph;	
	}
	public ArrayList<IndexPosition> unevenYGraph(IndexPosition position){
		int xPosition = position.getJPosition();
		int yPosition = position.getIPosition();
		ArrayList<IndexPosition> graph = new ArrayList<IndexPosition>();
		graph.add(new IndexPosition(xPosition+3, yPosition));
		graph.add(new IndexPosition(xPosition+3, yPosition-1));
		graph.add(new IndexPosition(xPosition+3, yPosition+1));
		graph.add(new IndexPosition(xPosition+2, yPosition-3));
		graph.add(new IndexPosition(xPosition+2, yPosition-2));
		graph.add(new IndexPosition(xPosition+2, yPosition-1));
		graph.add(new IndexPosition(xPosition+2, yPosition));
		graph.add(new IndexPosition(xPosition+2, yPosition+1));
		graph.add(new IndexPosition(xPosition+2, yPosition+2));
		graph.add(new IndexPosition(xPosition+2, yPosition+3));
		graph.add(new IndexPosition(xPosition+1, yPosition-3));
		graph.add(new IndexPosition(xPosition+1, yPosition-2));
		graph.add(new IndexPosition(xPosition+1, yPosition-1));
		graph.add(new IndexPosition(xPosition+1, yPosition));
		graph.add(new IndexPosition(xPosition+1, yPosition+1));
		graph.add(new IndexPosition(xPosition+1, yPosition+2));
		graph.add(new IndexPosition(xPosition+1, yPosition+3));
		graph.add(new IndexPosition(xPosition, yPosition-3));
		graph.add(new IndexPosition(xPosition, yPosition-2));
		graph.add(new IndexPosition(xPosition, yPosition-1));
		graph.add(new IndexPosition(xPosition, yPosition+1));
		graph.add(new IndexPosition(xPosition, yPosition+2));
		graph.add(new IndexPosition(xPosition, yPosition+3));
		graph.add(new IndexPosition(xPosition-1, yPosition-3));
		graph.add(new IndexPosition(xPosition-1, yPosition-2));
		graph.add(new IndexPosition(xPosition-1, yPosition-1));
		graph.add(new IndexPosition(xPosition-1, yPosition));
		graph.add(new IndexPosition(xPosition-1, yPosition+1));
		graph.add(new IndexPosition(xPosition-1, yPosition+2));
		graph.add(new IndexPosition(xPosition-1, yPosition+3));
		graph.add(new IndexPosition(xPosition-2, yPosition-2));
		graph.add(new IndexPosition(xPosition-2, yPosition-1));
		graph.add(new IndexPosition(xPosition-2, yPosition));
		graph.add(new IndexPosition(xPosition-2, yPosition+1));
		graph.add(new IndexPosition(xPosition-2, yPosition+2));
		graph.add(new IndexPosition(xPosition-3, yPosition));

		return graph;	
	}
	
	public ArrayList<IndexPosition> getGraph(){
		return this.graph;
	}
	
//	public int containsBuilding() {
//		/*0 = none
//		 *1 = one defended
//		 *2 = one free
//		 *3 = at least one defended and only one free
//		 *4 = at least one defended and some free
//		 *5 = all defended
//		 *6 = all free
//		 */
//		int status = 0;
//		Iterator<IndexPosition> graphIterator = graph.iterator();
//		IndexPosition position;
//		while(graphIterator.hasNext()) {
//			position = graphIterator.next();
//			if (map.getSquareType(position).getType() > 4) {
//				if (status == 0) {
//					if (map.getSquareType(position).getUnit()) {
//						status = 1;
//					}
//					else {
//						status = 2;
//					}
//				}
//				else if (status == 1) {
//					if (map.getSquareType(position).getUnit()) {
//						status = 5;
//					}
//					else {
//						status = 3;
//					}
//				}
//				else if (status == 2) {
//					if (map.getSquareType(position).getUnit()) {
//						status = 3;
//					}
//					else {
//						status = 6;
//					}
//				}
//				else if (status == 3) {
//					if (!map.getSquareType(position).getUnit()) {
//						status = 4;
//					}
//				}
//				else if (status == 5) {
//					if (!map.getSquareType(position).getUnit()) {
//						status = 3;
//					}
//				}
//				else if (status == 6) {
//					if (map.getSquareType(position).getUnit()) {
//						status = 4;
//					}
//				}
//			}
//		}
//		return status;
//	}
	
	public ArrayList<IndexPosition> containsBuilding() {
		ArrayList<IndexPosition> building = new ArrayList<IndexPosition>();
		Iterator<IndexPosition> graphIterator = graph.iterator();
		IndexPosition position;
		while(graphIterator.hasNext()) {
			position = graphIterator.next();
			if (map.getSquareType(position).getType()>4) {
				building.add(position);
			}
		}
		return building;
	}
	
	public ArrayList<Unit> containsUnit() {
		ArrayList<Unit> unit = new ArrayList<Unit>();
		Iterator<IndexPosition> graphIterator = graph.iterator();
		IndexPosition position;
		while(graphIterator.hasNext()) {
			position = graphIterator.next();
			if (map.getSquareType(position).getUnit()) {
				unit.add(game.getUnits().get(position));
			}
		}
		return unit;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public Map getMap() {
		return this.map;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
}
