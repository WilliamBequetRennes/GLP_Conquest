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
		float food = game.getPlayers()[game.getCurrentPlayer()-1].getResources().getFood();
		float oil = game.getPlayers()[game.getCurrentPlayer()-1].getResources().getOil();
		float electricity = game.getPlayers()[game.getCurrentPlayer()-1].getResources().getElectricity();
		
		switch(type) {
		case(0):unit = new Assault(game.getCurrentSquare().getPosition(), game.getCurrentPlayer());
		break;
		case(1):unit = new Sniper(game.getCurrentSquare().getPosition(), game.getCurrentPlayer());
		break;
		case(2):unit = new Obfourtytwo(game.getCurrentSquare().getPosition(), game.getCurrentPlayer());
		break;
		case(3):unit = new Bfgninethousand(game.getCurrentSquare().getPosition(), game.getCurrentPlayer());
		break;
		case(4):unit = new Tank(game.getCurrentSquare().getPosition(), game.getCurrentPlayer());
		break;
		case(5):unit = new Turret(game.getCurrentSquare().getPosition(), game.getCurrentPlayer());
		break;
		case(6):unit = new Destroyer(game.getCurrentSquare().getPosition(), game.getCurrentPlayer());
		break;
		case(7):unit = new Battleship(game.getCurrentSquare().getPosition(), game.getCurrentPlayer());
		break;
		case(8):unit = new Transport(game.getCurrentSquare().getPosition(), game.getCurrentPlayer());
		break;
		default:throw new InvalidUnitNumberException(type);
		}
		
		float attackCoeff = game.getPlayers()[game.getCurrentPlayer()-1].getLeader().getAttackCoeff()[unit.getType()];
		float defenseCoeff= game.getPlayers()[game.getCurrentPlayer()-1].getLeader().getDefenseCoeff()[unit.getType()];
		float costCoeff = game.getPlayers()[game.getCurrentPlayer()-1].getLeader().getCostCoeff()[unit.getType()];
		int rangeChanger = game.getPlayers()[game.getCurrentPlayer()-1].getLeader().getRangeChanger()[unit.getType()];
		int movementChanger = game.getPlayers()[game.getCurrentPlayer()-1].getLeader().getMovementChanger()[unit.getType()];
		
		unit.setAttack((int)(unit.getAttack()*attackCoeff));
		unit.setDefense((int)(unit.getAttack()*defenseCoeff));
		unit.getCost().setMoney(unit.getCost().getMoney()*costCoeff);
		unit.getCost().setFood(unit.getCost().getFood()*costCoeff);
		unit.getCost().setOil(unit.getCost().getOil()*costCoeff);
		unit.getCost().setElectricity(unit.getCost().getElectricity()*costCoeff);
		unit.setRange(unit.getRange()+rangeChanger);
		unit.setMaxMovement(unit.getMaxMovement()+movementChanger);
		
		boolean buyable = true;
		if(money < unit.getCost().getMoney()) {
			buyable = false;
		}
		if(food < unit.getCost().getFood()) {
			buyable = false;
		}
		if(oil < unit.getCost().getOil()) {
			buyable = false;
		}
		if(electricity < unit.getCost().getElectricity()) {
			buyable = false;
		}
		//if the country has enough money to buy the unit
		if(buyable) {
			//Reduce the country's resources by the unit's cost
			money-=(unit.getCost().getMoney());
			food-=(unit.getCost().getFood());
			oil-=(unit.getCost().getOil());
			electricity-=(unit.getCost().getElectricity());
			
			Resources resources = new Resources(money, food, oil, electricity);
			game.getPlayers()[game.getCurrentPlayer()-1].setResources(resources);
			
			//Add the unit's upkeep the the country's spents
			float moneySpents = game.getPlayers()[game.getCurrentPlayer()-1].getSpents().getMoney();
			float foodSpents = game.getPlayers()[game.getCurrentPlayer()-1].getSpents().getFood();
			float oilSpents = game.getPlayers()[game.getCurrentPlayer()-1].getSpents().getOil();
			float electricitySpents = game.getPlayers()[game.getCurrentPlayer()-1].getSpents().getElectricity();
			
			moneySpents += unit.getUpkeep().getMoney();
			foodSpents += unit.getUpkeep().getFood();
			oilSpents += unit.getUpkeep().getOil();
			electricitySpents += unit.getUpkeep().getElectricity();
			
			Resources spents = new Resources(moneySpents, foodSpents, oilSpents, electricitySpents);
			game.getPlayers()[game.getCurrentPlayer()-1].setSpents(spents);

			//create the unit in the game
			game.getPlayers()[game.getCurrentPlayer()-1].getUnits().put(unit.getPosition(), unit);
			game.getCurrentSquare().setUnit(true);
			return true;
		}
		//if the country doesn't have enough money to buy the unit
		else {
			return false;
		}
	}
}
