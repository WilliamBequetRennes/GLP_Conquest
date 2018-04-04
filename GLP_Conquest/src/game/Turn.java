package game;

import java.util.ArrayList;


import data.Resources;
import data.Results;
import gui.MenuBar;
import squares.Square;
import units.Unit;

public class Turn {
	
	private static final float COEF_HP_RECOVER = 0.2f;
	private static final int TERRAIN_POINT = 1;
	private static final int BUILDING_POINT = 3;
	private static final int CITY_POINT = 5;
	
	private Results results;
	
	public Turn() {
	}
	
	public void startTurns(Game game) {
		updateGains(game);
		updateSpents(game);
		buildingGains(game);
		unitsCosts(game);
	}
	
	public int nextTurn(Game game, MenuBar menuBar) {
		game.setCurrentPlayer(game.getCurrentPlayer()+1);
		if(game.getCurrentPlayer()>game.getPlayersNumber()) {
			game.setCurrentPlayer(1);
			game.setCurrentTurn(game.getCurrentTurn()+1);
		}
		if(game.getCurrentTurn() > game.getTurnsNumber()) {
			gameOver(game);
			return 2;
		}
		else {
			updateGains(game);
			updateSpents(game);
			buildingGains(game);
			unitsCosts(game);
			unitsRest(game);
		}
		if(game.getCurrentPlayer()==1) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public void buildingGains(Game game) {
		//Add resources produced by country's buildings to its resources
		Resources resources = game.getPlayers()[game.getCurrentPlayer()-1].getResources();
		Resources gains = game.getPlayers()[game.getCurrentPlayer()-1].getGains();
		
		resources.setMoney(resources.getMoney()+gains.getMoney());
		resources.setFood(resources.getFood()+gains.getFood());
		resources.setOil(resources.getOil()+gains.getOil());
		resources.setElectricity(resources.getElectricity()+gains.getElectricity());
		
		game.getPlayers()[game.getCurrentPlayer()-1].setResources(resources);
	}
	
	public void unitsCosts(Game game) {
		//Retire resources produced by country's units to its resources
		Resources resources = game.getPlayers()[game.getCurrentPlayer()-1].getResources();
		Resources spents = game.getPlayers()[game.getCurrentPlayer()-1].getSpents();
		
		resources.setMoney(resources.getMoney()-spents.getMoney());
		resources.setFood(resources.getFood()-spents.getFood());
		resources.setOil(resources.getOil()-spents.getOil());
		resources.setElectricity(resources.getElectricity()-spents.getElectricity());
		if(resources.getMoney() < 0) {
			resources.setMoney(0);
		}
		if(resources.getFood() < 0) {
			resources.setFood(0);
		}
		if(resources.getOil() < 0) {
			resources.setOil(0);
		}
		if(resources.getElectricity() < 0) {
			resources.setElectricity(0);
		}
		game.getPlayers()[game.getCurrentPlayer()-1].setResources(resources);
		
	}
	
	public void updateGains(Game game) {
		//update the gains of each country
		for(int i = 0; i<game.getPlayersNumber(); i++) {
			ArrayList<Square> buildings = new ArrayList<Square>();
			buildings.addAll(game.getPlayers()[i].getBuildings().values());
			
			Resources gains = new Resources();
			for(Square current : buildings) {
				gains.setMoney(gains.getMoney()+current.getResources().getMoney());
				gains.setFood(gains.getFood()+current.getResources().getFood());
				gains.setOil(gains.getOil()+current.getResources().getOil());
				gains.setElectricity(gains.getElectricity()+current.getResources().getElectricity());
			}
			gains.setFood(gains.getFood()*game.getPlayers()[i].getLeader().getFoodCoeff());
			gains.setOil(gains.getOil()*game.getPlayers()[i].getLeader().getOilCoeff());
			gains.setElectricity(gains.getElectricity()*game.getPlayers()[i].getLeader().getElectricityCoeff());
			game.getPlayers()[i].setGains(gains);	
		}
	}
	
	public void updateSpents(Game game) {
		//update the spents of each country
		for(int i = 0; i<game.getPlayersNumber(); i++) {
			ArrayList<Unit> units = new ArrayList<Unit>();
			units.addAll(game.getPlayers()[game.getCurrentPlayer()-1].getUnits().values());

			Resources spents = new Resources();
			for(Unit current : units) {
				spents.setMoney(spents.getMoney()+current.getUpkeep().getMoney());
				spents.setFood(spents.getFood()+current.getUpkeep().getFood());
				spents.setOil(spents.getOil()+current.getUpkeep().getOil());
				spents.setElectricity(spents.getElectricity()+current.getUpkeep().getElectricity());
			}
			game.getPlayers()[i].setSpents(spents);
		}
	}
	
	public void unitsRest(Game game) {
		for(Unit current : game.getPlayers()[game.getCurrentPlayer()-1].getUnits().values()) {
			current.setMovement(current.getMaxMovement());
			//if the unit is on a building it will recover some HP
			if(game.getPlayers()[game.getCurrentPlayer()-1].getBuildings().containsKey(current.getPosition())) {
				current.setCurrentHealth(current.getCurrentHealth()+current.getMaxHealth()*COEF_HP_RECOVER);
				if(current.getCurrentHealth()>current.getMaxHealth()) {
					current.setCurrentHealth(current.getMaxHealth());
				}
			}
		}
	}
	
	public void gameOver(Game game) {
		int playersNumber = game.getPlayersNumber();
		int[] leaders = new int[playersNumber];
		int[] ranks = new int[playersNumber];
		int[] totalPoints = new int[playersNumber];
		int[] numberOfTerrains = new int[playersNumber];
		int[] numberOfBuildings = new int[playersNumber];
		int[] numberOfCities = new int[playersNumber];
		int[] terrainPoints = new int[playersNumber];
		int[] buildingPoints = new int[playersNumber];
		int[] cityPoints = new int[playersNumber];
		
		for(int i = 0; i<playersNumber; i++) {
			leaders[i] = 0;
			ranks[i] = 0;
			totalPoints[i] = 0;
			numberOfTerrains[i] = 0;
			numberOfBuildings[i] = 0;
			numberOfCities[i] = 0;
			terrainPoints[i] = 0;
			buildingPoints[i] = 0;
			cityPoints[i] = 0;
			
			leaders[i] = game.getPlayers()[i].getLeader().getNumber();
			for(Square current : game.getPlayers()[i].getBuildings().values()) {
				if(current.getType() == 9) {
					numberOfCities[i]++;
				}
				else {
					numberOfBuildings[i]++;
				}
			}
			numberOfTerrains[i] = game.getPlayers()[i].getSquareNumber();
			numberOfTerrains[i] -= numberOfCities[i];
			numberOfTerrains[i] -= numberOfBuildings[i];
			
			terrainPoints[i] = numberOfTerrains[i]*TERRAIN_POINT;
			buildingPoints[i] = numberOfBuildings[i]*BUILDING_POINT;
			cityPoints[i] = numberOfCities[i]*CITY_POINT;
			
			totalPoints[i] = terrainPoints[i]+buildingPoints[i]+cityPoints[i];
		}
		
		for(int i = 0; i<playersNumber; i++) {
			ranks[i] = 1;
			for(int j = 0; j<playersNumber; j++) {
				if(j!=i) {
					if(totalPoints[i]<totalPoints[j]) {
						ranks[i]++;
					}
				}
			}
		}
		
		setResults(new Results(playersNumber, ranks, totalPoints, terrainPoints, 
				buildingPoints, cityPoints, numberOfTerrains, numberOfBuildings, 
				numberOfCities, leaders));
	}

	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}
}
