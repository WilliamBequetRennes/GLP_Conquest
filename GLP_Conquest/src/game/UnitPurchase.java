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
	
	private Game game;
	public UnitPurchase(Game game) {
		setGame(game);
	}
	public boolean purchase(int type) throws InvalidUnitNumberException{
		Unit unit = null;
		float money = getGame().getPlayers()[getGame().getCurrentPlayer()].getResources().getMoney();
		switch(type) {
		case(0):unit = new Assault(getGame().getCurrentSquare().getPosition(), 0, getGame().getCurrentPlayer());
		break;
		case(1):unit = new Sniper(getGame().getCurrentSquare().getPosition(), 0, getGame().getCurrentPlayer());
		break;
		case(2):unit = new Obfourtytwo(getGame().getCurrentSquare().getPosition(), 0, getGame().getCurrentPlayer());
		break;
		case(3):unit = new Bfgninethousand(getGame().getCurrentSquare().getPosition(), 0, getGame().getCurrentPlayer());
		break;
		case(4):unit = new Tank(getGame().getCurrentSquare().getPosition(), 0, getGame().getCurrentPlayer());
		break;
		case(5):unit = new Turret(getGame().getCurrentSquare().getPosition(), 0, getGame().getCurrentPlayer());
		break;
		case(6):unit = new Destroyer(getGame().getCurrentSquare().getPosition(), 0, getGame().getCurrentPlayer());
		break;
		case(7):unit = new Battleship(getGame().getCurrentSquare().getPosition(), 0, getGame().getCurrentPlayer());
		break;
		case(8):unit = new Transport(getGame().getCurrentSquare().getPosition(), 0, getGame().getCurrentPlayer());
		break;
		default:throw new InvalidUnitNumberException(type);
		}
		if(money >= unit.getCost().getMoney()) {
			//if the country has enough money to buy the unit
			money-=unit.getCost().getMoney();
			getGame().getPlayers()[getGame().getCurrentPlayer()].getResources().setMoney(money);
			getGame().getPlayers()[getGame().getCurrentPlayer()].getUnits().put(unit.getPosition(), unit);
			
			float moneySpents = getGame().getPlayers()[getGame().getCurrentPlayer()].getSpents().getMoney();
			float food = getGame().getPlayers()[getGame().getCurrentPlayer()].getSpents().getFood();
			float oil = getGame().getPlayers()[getGame().getCurrentPlayer()].getSpents().getOil();
			float electricity = getGame().getPlayers()[getGame().getCurrentPlayer()].getSpents().getElectricity();
			
			food += unit.getCost().getFood();
			oil += unit.getCost().getOil();
			electricity += unit.getCost().getElectricity();
			
			Resources spents = new Resources(moneySpents, food, oil, electricity);
			getGame().getPlayers()[getGame().getCurrentPlayer()].setSpents(spents);
			
			return true;
		}
		else {
			//if the country doesn't have enough money to buy the unit
			return false;
		}
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
