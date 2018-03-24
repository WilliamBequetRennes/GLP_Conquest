package game;

import java.util.ArrayList;


import data.Resources;
import gui.MenuBar;
import squares.Square;
import units.Unit;

public class Turn {
	
	private static final float COEF_HP_RECOVER = 0.2f;
	public Turn() {
	}
	
	public void startTurns(Game game) {
		updateGains(game);
		updateSpents(game);
		buildingGains(game);
		unitsCosts(game);
	}
	
	public void nextTurn(Game game, MenuBar menuBar) {
		game.setCurrentPlayer(game.getCurrentPlayer()+1);
		if(game.getCurrentPlayer()>game.getPlayersNumber()) {
			game.setCurrentPlayer(1);
			game.setCurrentTurn(game.getCurrentTurn()+1);
			menuBar.getTurnNumber().setText("turn : "+game.getCurrentTurn());
		}
		if(game.getCurrentTurn() > game.getTurnsNumber()) {
			//End game
		}
		else {
			updateGains(game);
			updateSpents(game);
			buildingGains(game);
			unitsCosts(game);
			unitsRest(game);
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
		
		//Function not over yet, it has to take in count that the country can don't have the resources to upkeep its units
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
				spents.setMoney(spents.getMoney()+current.getCost().getMoney());
				spents.setFood(spents.getFood()+current.getCost().getFood());
				spents.setOil(spents.getOil()+current.getCost().getOil());
				spents.setElectricity(spents.getElectricity()+current.getCost().getElectricity());
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
}
