package movement;

import java.util.ArrayList;
import java.util.Iterator;
import data.Position;
import exceptions.UnitException;
import game.Game;
import map.Map;
import units.Transform;
import units.Unit;
import fight.AreaScanner;


public class Movement {
	private Unit unit;
	private Graph graph;
	
	public Movement(Unit unit) {
		setUnit(unit);
		IndexPosition position = new IndexPosition(unit.getPosition());
		setGraph(new Graph(position));
	}
	
	public Movement(Unit unit, Graph graph) {
		setUnit(unit);
		setGraph(graph);
	}
	
	public Graph scanArea(Map map){
		availableMovement(map);
		return getGraph();
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
	
	public ArrayList<Position> adjacentSquare(Position interPosition, Map map){
		ArrayList<Position> adjacent = new ArrayList<Position>();
		int jPosition = interPosition.getJPosition();
		int iPosition = interPosition.getIPosition();
		if (parity(interPosition.getJPosition())){
			if(jPosition > 0) {
				adjacent.add(new IndexPosition(iPosition-1,jPosition));
				if (iPosition > 0) {
					adjacent.add(new IndexPosition(iPosition-1, jPosition-1));
				}
				if (iPosition < map.getDimensions()) {
					adjacent.add(new IndexPosition(iPosition-1,jPosition+1));
				}
			}
			if (iPosition > 0) {
				adjacent.add(new IndexPosition(iPosition, jPosition-1));
			}
			if (iPosition < map.getDimensions()) {
				adjacent.add(new IndexPosition(iPosition,jPosition+1));
			}
			if (jPosition < map.getDimensions()) {
				adjacent.add(new IndexPosition(iPosition+1,jPosition));
			}
		}
		else {
			if(jPosition > 0) {
				adjacent.add(new IndexPosition(iPosition-1,jPosition));
			}
			if (iPosition > 0) {
				adjacent.add(new IndexPosition(iPosition, jPosition-1));
			}
			if (iPosition < map.getDimensions()) {
				adjacent.add(new IndexPosition(iPosition,jPosition+1));
			}
			if (jPosition < map.getDimensions()) {
				adjacent.add(new IndexPosition(iPosition+1,jPosition));
				if (iPosition > 0) {
					adjacent.add(new IndexPosition(iPosition+1, jPosition-1));
				}
				if (iPosition < map.getDimensions()) {
					adjacent.add(new IndexPosition(iPosition+1,jPosition+1));
				}
			}
		}
		return adjacent; 
	}
	
public ArrayList<IndexPosition> availableMovement(Map map){
		
		ArrayList<IndexPosition> available = new ArrayList<IndexPosition>();
		
		IndexPosition position = new IndexPosition(getUnit().getPosition());
		
		float costPreview = 0;
		
		ArrayList<Position> adjacent1 = new ArrayList<Position>();
		ArrayList<Position> adjacent2 = new ArrayList<Position>();
		ArrayList<Position> adjacent3 = new ArrayList<Position>();
		ArrayList<Position> adjacent4 = new ArrayList<Position>();
		ArrayList<Position> adjacent5 = new ArrayList<Position>();
		
		AreaScanner scanner = new AreaScanner();
		ArrayList<IndexPosition> scan = new ArrayList<IndexPosition>();
		int movement = (int) getUnit().getMovement();
		ArrayList<Position> reachable = scanner.aroundPositions(position,movement,map);
		for(Position convert : reachable) {
			scan.add(convert.toIndexPosition());
		}
		//Iterator on the whole graph linked to the position 
		
		Iterator<IndexPosition> positionIterator0 = scan.iterator();
//		Iterator<IndexPosition> positionIterator0 = getGraph().getGraph().iterator();
		
		//save the cost of the previous square
		
		float previousCost = 0;
		
		//save the previous path to the current position
		
		ArrayList<IndexPosition> previousPath = new ArrayList<IndexPosition>();		
		
		//the starting position costs 0 to go to
		
		position.setLocalCost(0);		
		
		//For each position of the whole graph
		
		while (positionIterator0.hasNext()) {

			IndexPosition testedPosition0 = positionIterator0.next();

			if(isCrossable(map.getSquareType(testedPosition0).getType(),getUnit())) {				
				
				//set the movement cost to reach the selected position
				
				previousCost += map.getSquareType(testedPosition0).getMoveCost();
	
				//select the adjacent positions			
				
				adjacent1 = adjacentSquare(position, map);
				
				//if it is next to the unit's position
				
				if (adjacent1.contains(testedPosition0)) {
						
					//set data
										
					testedPosition0.addLocalPath(testedPosition0);
					
					testedPosition0.calculateLocalCost(map);
					
					available.add(testedPosition0);
		
				}
			
				//if it is not next to the unit's position
				
				else {
					if(isCrossable(map.getSquareType(testedPosition0).getType(),getUnit())){
						
						//the previous position is part of the path
						
						previousPath.add(testedPosition0);
						
						//we test all of the adjacent position of the tested position
		
						Iterator<Position>positionIterator1 = adjacent1.iterator();
						
						// For each adjacent position
						
						while (positionIterator1.hasNext()) {
							
							//test if the current position is next to the position we aim
							
							Position testedPosition1 = positionIterator1.next();
							
							if(isCrossable(map.getSquareType(testedPosition1).getType(),getUnit())){
							
								//refresh the movement cost to the aimed square
								
								previousCost += map.getSquareType(testedPosition1).getMoveCost();
								
								//select the adjacent position
								
								adjacent2 = adjacentSquare(testedPosition1, map);
								
								//if the adjacent position contains the aimed position
								
								if (adjacent2.contains(testedPosition0)) {
									
									//if the movement cost is cheaper than thep revious one(default : 100)
									costPreview = calculateTestLocalCost(map,previousPath)+map.getSquareType(testedPosition0).getMoveCost();
									if(costPreview<testedPosition0.getLocalCost()) {
									
										//set datas
																	
										testedPosition0.setLocalPath(previousPath);
										
										testedPosition0.addLocalPath(testedPosition0);
										
										testedPosition0.calculateLocalCost(map);
										
										available.add(testedPosition0);
			
									}
								}
								
								//last iteration
								
								else {
									
									if(isCrossable(map.getSquareType(testedPosition1).getType(),getUnit())){
									
										//the previous position is part of the path
										
										previousPath.add(testedPosition1.toIndexPosition());
										
										//try last iteration
										
										Iterator<Position> positionIterator2 = adjacent2.iterator();
										
										// For each adjacent position
										
										while (positionIterator2.hasNext()) {
											
											//save the last position in range and check
											
											Position testedPosition2 = positionIterator2.next();
											
											if(isCrossable(map.getSquareType(testedPosition2).getType(),getUnit())){
											
												//update movement cost
											
												previousCost += map.getSquareType(testedPosition2).getMoveCost();
												
												//check one last time
												
												adjacent3 = adjacentSquare(testedPosition0, map);
												
												//and if you finally find it
											
												if (adjacent3.contains(testedPosition0)) {
												
													//verify it is the quickest
												
													costPreview = calculateTestLocalCost(map,previousPath)+map.getSquareType(testedPosition0).getMoveCost();
													if(costPreview<testedPosition0.getLocalCost()) {
														
														//and set datas if it is
														
														testedPosition0.setLocalPath(previousPath);
														testedPosition0.addLocalPath(testedPosition0);
														testedPosition0.calculateLocalCost(map);
														available.add(testedPosition0);
				
													}
												}
											
												else {
													
													if(isCrossable(map.getSquareType(testedPosition2).getType(),getUnit())){
													
														//the previous position is part of the path
													
														previousPath.add(testedPosition2.toIndexPosition());
														
														//try last iteration
														
														Iterator<Position> positionIterator3 = adjacent3.iterator();
														
														// For each adjacent position
														
														while (positionIterator3.hasNext()) {
															
															//save the last position in range and check
															
															Position testedPosition3 = positionIterator3.next();
															
															if(isCrossable(map.getSquareType(testedPosition3).getType(),getUnit())){
															
																//update movement cost
																
																previousCost += map.getSquareType(testedPosition3).getMoveCost();
																
																//check one last time
																
																adjacent4 = adjacentSquare(testedPosition0, map);
																
																//and if you finally find it
															
																if (adjacent4.contains(testedPosition0)) {
																	
																	//verify it is the quickest
																	
																	costPreview = calculateTestLocalCost(map,previousPath)+map.getSquareType(testedPosition0).getMoveCost();
																	if(costPreview<testedPosition0.getLocalCost()) {
																		
																		//and set datas if it is
																		
																		testedPosition0.setLocalPath(previousPath);
																		testedPosition0.addLocalPath(testedPosition0);
																		testedPosition0.calculateLocalCost(map);
																		available.add(testedPosition0);
							
																		
																	}
																
																}
																else {
																	
																	if(isCrossable(map.getSquareType(testedPosition3).getType(),getUnit())){
																	
																		//the previous position is part of the path
																	
																		previousPath.add(testedPosition3.toIndexPosition());
																		
																		//try last iteration
																		
																		Iterator<Position> positionIterator4 = adjacent4.iterator();
																		
																		// For each adjacent position
																		
																		while (positionIterator4.hasNext()) {
																			
																			//save the last position in range and check
																			
																			Position testedPosition4 = positionIterator4.next();
																			
																			if(isCrossable(map.getSquareType(testedPosition4).getType(),getUnit())){
																			
																				//update movement cost
																				
																				previousCost += map.getSquareType(testedPosition4).getMoveCost();
																				
																				//check one last time
																				
																				adjacent5 = adjacentSquare(testedPosition0, map);
																				
																				//and if you finally find it
																			
																				if (adjacent5.contains(testedPosition0)) {
																					
																					//verify it is the quickest
																					
																					costPreview = calculateTestLocalCost(map,previousPath)+map.getSquareType(testedPosition0).getMoveCost();
																					if(costPreview<testedPosition0.getLocalCost()) {
																						
																						//and set datas if it is
																						
																						testedPosition0.setLocalPath(previousPath);
																						testedPosition0.addLocalPath(testedPosition0);
																						testedPosition0.calculateLocalCost(map);
																						available.add(testedPosition0);
											
																						
																					}
																				
																				}
																				else {
																					
																					if(isCrossable(map.getSquareType(testedPosition4).getType(),getUnit())){
																					
																						//the previous position is part of the path
																					
																						previousPath.add(testedPosition4.toIndexPosition());
																						
																						//try last iteration
																						
																						Iterator<Position> positionIterator5 = adjacent5.iterator();
																						
																						// For each adjacent position
																						
																						while (positionIterator5.hasNext()) {
																							
																							//save the last position in range and check
																							
																							Position testedPosition5 = positionIterator5.next();
																							
																							if(isCrossable(map.getSquareType(testedPosition5).getType(),getUnit())){
																							
																								//update movement cost
																								
																								previousCost += map.getSquareType(testedPosition5).getMoveCost();
																								
																								//check one last time
																								
																								ArrayList<Position> adjacent6 = adjacentSquare(testedPosition0, map);
																								
																								//and if you finally find it
																							
																								if (adjacent6.contains(testedPosition0)) {
																									
																									//verify it is the quickest
																									
																									costPreview = calculateTestLocalCost(map,previousPath)+map.getSquareType(testedPosition0).getMoveCost();
																									if(costPreview<testedPosition0.getLocalCost()) {
																										
																										//and set datas if it is
																										
																										testedPosition0.setLocalPath(previousPath);
																										testedPosition0.addLocalPath(testedPosition0);
																										testedPosition0.calculateLocalCost(map);
																										available.add(testedPosition0);
															
																										
																									}
																								
																								}
																								previousCost -= map.getSquareType(testedPosition5).getMoveCost();
																							}
																						}
																						previousPath.remove(testedPosition4);
																					}
																				}
																				previousCost -= map.getSquareType(testedPosition4).getMoveCost();
																			}
																		}
																		previousPath.remove(testedPosition3);
																	}
																}
																previousCost -= map.getSquareType(testedPosition3).getMoveCost();
															}
														}
														previousPath.remove(testedPosition2);
													}
												}
												previousCost -= map.getSquareType(testedPosition2).getMoveCost();
											}
										}
										previousPath.remove(testedPosition1);
									}	
								}
								previousCost -= map.getSquareType(testedPosition1).getMoveCost();
							}
						}
						previousPath.remove(testedPosition0);
					}
				}
				
			}
			previousCost -= map.getSquareType(testedPosition0).getMoveCost();
		}
		ArrayList<IndexPosition> copy = new ArrayList<IndexPosition>();
		copy.addAll(available);
		for(IndexPosition current : copy) {
			if (current.getLocalCost() > movement) {
				available.remove(current);
			}
		}
		return available;
	}
	public IndexPosition findIndexPosition(ArrayList<IndexPosition> positions, Position objective) {
		IndexPosition result = new IndexPosition(objective);
		for(IndexPosition current : positions) {
			if(current.getIPosition() == objective.getIPosition() 
					&& current.getJPosition() == objective.getJPosition()) {
				result = current;
			}
		}
		return result;
	}
	public void goTo(Position finalPosition, ArrayList<IndexPosition> moveList, Map map, Game game) {
		//get the IndexPosition from moveList that correspond to the finalPosition
		IndexPosition objective = findIndexPosition(moveList, finalPosition);
		
		if(moveList.contains(objective)) {
			Unit movingUnit = game.getPlayers()[game.getCurrentPlayer()-1].getUnits().get(getUnit().getPosition());
			Position currentPosition = getUnit().getPosition();
			int moveCost = 0;
			for(Position current : objective.getLocalPath()) {
				conquest(current, map, game);
				moveCost += map.getSquares()[current.getIPosition()][current.getJPosition()].getMoveCost();
			}
			//conquest(finalPosition, map, game);
			//moveCost += map.getSquares()[finalPosition.getIPosition()][finalPosition.getJPosition()].getMoveCost();
			//Reduce the move points of the unit
			movingUnit.setMovement(movingUnit.getMovement()-moveCost);
			
			//Move the unit
			map.getSquares()[currentPosition.getIPosition()][currentPosition.getJPosition()].setUnit(false);
			map.getSquares()[finalPosition.getIPosition()][finalPosition.getJPosition()].setUnit(true);
			game.getPlayers()[game.getCurrentPlayer()-1].getUnits().remove(movingUnit.getPosition());
			movingUnit.setPosition(finalPosition);
			game.getPlayers()[game.getCurrentPlayer()-1].getUnits().put(finalPosition, movingUnit);
		}
	}

	public void tranformUnit(Unit unit, Map map, Game game) throws UnitException {
		if (unit.getType()<6) {
			if(map.getSquareType(unit.getPosition()).getType() == 0) {
				Transform transform = new Transform(unit.getPosition(),unit.getFaction(),unit);
				game.getPlayers()[unit.getFaction()].getUnits().remove(unit.getPosition(),unit);
				game.getPlayers()[unit.getFaction()].getUnits().put(transform.getPosition(),transform);
				transform.setMovement(0);
			}
		}
		else if (unit.getType()==9) {
			if(map.getSquareType(unit.getPosition()).getType() != 0) {
				Transform transform = (Transform) unit;
				game.getPlayers()[unit.getFaction()].getUnits().remove(unit.getPosition(),unit);
				game.getPlayers()[unit.getFaction()].getUnits().put(transform.getPosition(),transform.getUnit());
				unit.setMovement(0);
			}	
		}
		else {
			if(map.getSquareType(unit.getPosition()).getType() != 0) {
				UnitException except = new UnitException(unit.getType());
				throw except;
			}
		}
	}
	
	
	
	public boolean isCrossable(int type, Unit unit) {
		boolean isCrossable = false;
		int[] crossable = unit.getCrossable();
		for(int i=0;i<crossable.length;i++) {
			if(crossable[i]==type) {
				isCrossable = true;
			}
		}
		return isCrossable;
	}
	
	public void conquest(Position position, Map map, Game game) {
		AreaScanner areaScanner = new AreaScanner();
		ArrayList<Position> adjacent = areaScanner.aroundPositions(position, 1, map);
		
		for(Position current : adjacent) {
			int faction = map.getSquares()[current.getIPosition()][current.getJPosition()].getFaction();
			
			//if the square doesn't already belong to the player, if the square isn't a building and
			//if there is no enemy unit on it
			if(faction != game.getCurrentPlayer() && 
					map.getSquares()[current.getIPosition()][current.getJPosition()].getType() <= 4 && 
					!map.getSquares()[current.getIPosition()][current.getJPosition()].getUnit()) {
					
					//if the square was owned by an other player
					if(faction > 0) {
						int squaresOfEnemy = game.getPlayers()[faction-1].getSquareNumber();
						game.getPlayers()[faction-1].setSquareNumber(squaresOfEnemy-1);
					}
					map.getSquares()[current.getIPosition()][current.getJPosition()].setFaction(game.getCurrentPlayer());
					int numberOfSquares = game.getPlayers()[game.getCurrentPlayer()-1].getSquareNumber();
					game.getPlayers()[game.getCurrentPlayer()-1].setSquareNumber(numberOfSquares+1);
			}
		}
		int faction = map.getSquares()[position.getIPosition()][position.getJPosition()].getFaction();
		
		//if the square was owned by an other player
		if(faction>0) {
			int squaresOfEnemy = game.getPlayers()[faction-1].getSquareNumber();
			game.getPlayers()[faction-1].setSquareNumber(squaresOfEnemy-1);
			if (map.getSquares()[position.getIPosition()][position.getJPosition()].getType()>4) {
				game.getPlayers()[faction-1].getBuildings().remove(position);
			}
		}
		
		map.getSquares()[position.getIPosition()][position.getJPosition()].setFaction(game.getCurrentPlayer());
		int numberOfSquares = game.getPlayers()[game.getCurrentPlayer()-1].getSquareNumber();
		game.getPlayers()[game.getCurrentPlayer()-1].setSquareNumber(numberOfSquares+1);
		if (map.getSquares()[position.getIPosition()][position.getJPosition()].getType()>4) {
			game.getPlayers()[game.getCurrentPlayer()-1].getBuildings().put(position,map.getSquares()[position.getIPosition()][position.getJPosition()]);
		}
	}
	
	public float calculateTestLocalCost(Map map, ArrayList<IndexPosition> path) {
		float movementCost = 0;
		for(IndexPosition current : path) {
			movementCost += map.getSquareType(current.toPosition()).getMoveCost();
		}
		return(movementCost);
	}
	
	public void setUnit(Unit unit) {
		this.unit= unit;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	public Graph getGraph() {
		return graph;
	}
	
}
