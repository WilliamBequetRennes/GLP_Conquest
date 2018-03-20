package game;

import data.Resources;
import exceptions.InvalidUnitNumberException;
import units.Assault;
import units.Battleship;
import units.Bfgninethousand;
import units.Destroyer;
import units.Obfourtytwo;
import units.Sniper;
import units.Tank;
import units.Transport;
import units.Turret;
import units.Unit;

public class UnitPurchase {
	
	public UnitPurchase() {
	}
	public boolean purchase(Game game, int type) throws InvalidUnitNumberException{
		Unit unit = null;
		float money = game.getPlayers()[game.getCurrentPlayer()-1].getResources().getMoney();
		switch(type) {
		case(0):unit = new Assault(game.getCurrentSquare().getPosition(), 0, game.getCurrentPlayer());
		break;
		case(1):unit = new Sniper(game.getCurrentSquare().getPosition(), 0, game.getCurrentPlayer());
		break;
		case(2):unit = new Obfourtytwo(game.getCurrentSquare().getPosition(), 0, game.getCurrentPlayer());
		break;
		case(3):unit = new Bfgninethousand(game.getCurrentSquare().getPosition(), 0, game.getCurrentPlayer());
		break;
		case(4):unit = new Tank(game.getCurrentSquare().getPosition(), 0, game.getCurrentPlayer());
		break;
		case(5):unit = new Turret(game.getCurrentSquare().getPosition(), 0, game.getCurrentPlayer());
		break;
		case(6):unit = new Destroyer(game.getCurrentSquare().getPosition(), 0, game.getCurrentPlayer());
		break;
		case(7):unit = new Battleship(game.getCurrentSquare().getPosition(), 0, game.getCurrentPlayer());
		break;
		case(8):unit = new Transport(game.getCurrentSquare().getPosition(), 0, game.getCurrentPlayer());
		break;
		default:throw new InvalidUnitNumberException(type);
		}
		if(money >= unit.getCost().getMoney()) {
			//if the country has enough money to buy the unit
			money-=unit.getCost().getMoney();
			game.getPlayers()[game.getCurrentPlayer()-1].getResources().setMoney(money);
			game.getPlayers()[game.getCurrentPlayer()-1].getUnits().put(unit.getPosition(), unit);
			
			float moneySpents = game.getPlayers()[game.getCurrentPlayer()-1].getSpents().getMoney();
			float food = game.getPlayers()[game.getCurrentPlayer()-1].getSpents().getFood();
			float oil = game.getPlayers()[game.getCurrentPlayer()-1].getSpents().getOil();
			float electricity = game.getPlayers()[game.getCurrentPlayer()-1].getSpents().getElectricity();
			
			food += unit.getCost().getFood();
			oil += unit.getCost().getOil();
			electricity += unit.getCost().getElectricity();
			
			Resources spents = new Resources(moneySpents, food, oil, electricity);
			game.getPlayers()[game.getCurrentPlayer()-1].setSpents(spents);
			game.getCurrentSquare().setUnit(true);
			return true;
		}
		else {
			//if the country doesn't have enough money to buy the unit
			return false;
		}
	}
}
