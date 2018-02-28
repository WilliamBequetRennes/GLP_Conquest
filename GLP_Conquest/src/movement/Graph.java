package movement;

import java.util.ArrayList;

public class Graph {
	private ArrayList<IndexPosition> graph;
	
	public Graph(IndexPosition position) {
		if (parity(position.getIPosition())){
			this.graph = evenYGraph(position);
		}
		else {
			this.graph = unevenYGraph(position);
		}
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
	
}
