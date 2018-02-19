import java.util.ArrayList;

public class Graph {
	private ArrayList<Position> graph;
	
	public Graph(Position position) {
		if (parity(position.getYPosition())){
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
	public ArrayList<Position> evenYGraph(Position Position){
		int xPosition = Position.getXPosition();
		int yPosition = Position.getYPosition();
		ArrayList<Position> graph = new ArrayList<Position>();
		graph.add(new Position(xPosition-3, yPosition));
		graph.add(new Position(xPosition-3, yPosition-1));
		graph.add(new Position(xPosition-3, yPosition+1));
		graph.add(new Position(xPosition-2, yPosition-3));
		graph.add(new Position(xPosition-2, yPosition-2));
		graph.add(new Position(xPosition-2, yPosition-1));
		graph.add(new Position(xPosition-2, yPosition));
		graph.add(new Position(xPosition-2, yPosition+1));
		graph.add(new Position(xPosition-2, yPosition+2));
		graph.add(new Position(xPosition-2, yPosition+3));
		graph.add(new Position(xPosition-1, yPosition-3));
		graph.add(new Position(xPosition-1, yPosition-2));
		graph.add(new Position(xPosition-1, yPosition-1));
		graph.add(new Position(xPosition-1, yPosition));
		graph.add(new Position(xPosition-1, yPosition+1));
		graph.add(new Position(xPosition-1, yPosition+2));
		graph.add(new Position(xPosition-1, yPosition+3));
		graph.add(new Position(xPosition, yPosition-3));
		graph.add(new Position(xPosition, yPosition-2));
		graph.add(new Position(xPosition, yPosition-1));
		graph.add(new Position(xPosition, yPosition+1));
		graph.add(new Position(xPosition, yPosition+2));
		graph.add(new Position(xPosition, yPosition+3));
		graph.add(new Position(xPosition+1, yPosition-3));
		graph.add(new Position(xPosition+1, yPosition-2));
		graph.add(new Position(xPosition+1, yPosition-1));
		graph.add(new Position(xPosition+1, yPosition));
		graph.add(new Position(xPosition+1, yPosition+1));
		graph.add(new Position(xPosition+1, yPosition+2));
		graph.add(new Position(xPosition+1, yPosition+3));
		graph.add(new Position(xPosition+2, yPosition-2));
		graph.add(new Position(xPosition+2, yPosition-1));
		graph.add(new Position(xPosition+2, yPosition));
		graph.add(new Position(xPosition+2, yPosition+1));
		graph.add(new Position(xPosition+2, yPosition+2));
		graph.add(new Position(xPosition+3, yPosition));

		return graph;	
	}
	public ArrayList<Position> unevenYGraph(Position Position){
		int xPosition = Position.getXPosition();
		int yPosition = Position.getYPosition();
		ArrayList<Position> graph = new ArrayList<Position>();
		graph.add(new Position(xPosition+3, yPosition));
		graph.add(new Position(xPosition+3, yPosition-1));
		graph.add(new Position(xPosition+3, yPosition+1));
		graph.add(new Position(xPosition+2, yPosition-3));
		graph.add(new Position(xPosition+2, yPosition-2));
		graph.add(new Position(xPosition+2, yPosition-1));
		graph.add(new Position(xPosition+2, yPosition));
		graph.add(new Position(xPosition+2, yPosition+1));
		graph.add(new Position(xPosition+2, yPosition+2));
		graph.add(new Position(xPosition+2, yPosition+3));
		graph.add(new Position(xPosition+1, yPosition-3));
		graph.add(new Position(xPosition+1, yPosition-2));
		graph.add(new Position(xPosition+1, yPosition-1));
		graph.add(new Position(xPosition+1, yPosition));
		graph.add(new Position(xPosition+1, yPosition+1));
		graph.add(new Position(xPosition+1, yPosition+2));
		graph.add(new Position(xPosition+1, yPosition+3));
		graph.add(new Position(xPosition, yPosition-3));
		graph.add(new Position(xPosition, yPosition-2));
		graph.add(new Position(xPosition, yPosition-1));
		graph.add(new Position(xPosition, yPosition+1));
		graph.add(new Position(xPosition, yPosition+2));
		graph.add(new Position(xPosition, yPosition+3));
		graph.add(new Position(xPosition-1, yPosition-3));
		graph.add(new Position(xPosition-1, yPosition-2));
		graph.add(new Position(xPosition-1, yPosition-1));
		graph.add(new Position(xPosition-1, yPosition));
		graph.add(new Position(xPosition-1, yPosition+1));
		graph.add(new Position(xPosition-1, yPosition+2));
		graph.add(new Position(xPosition-1, yPosition+3));
		graph.add(new Position(xPosition-2, yPosition-2));
		graph.add(new Position(xPosition-2, yPosition-1));
		graph.add(new Position(xPosition-2, yPosition));
		graph.add(new Position(xPosition-2, yPosition+1));
		graph.add(new Position(xPosition-2, yPosition+2));
		graph.add(new Position(xPosition-3, yPosition));

		return graph;	
	}
	
	public ArrayList<Position> getGraph(){
		return this.graph;
	}
	
}
