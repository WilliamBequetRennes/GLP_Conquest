package game;

import java.util.ArrayList;

import data.Resources;
import squares.Square;

public class LevelUp {
	
	private static final int LEVEL_MAX = 3;
	private static final int[] COST_LEVEL = {1000,1000,1000};
	private static final int[] MAIN_INCREASE = {200,200,200};
	private static final int[] SECONDARY_INCREASE = {100,100,100};
	
	public LevelUp(){
	}
	
	public void up(Game game) {
		if(game.getCurrentSquare().getType()>4 && game.getCurrentSquare().getLevel()<LEVEL_MAX){
			int level = game.getCurrentSquare().getLevel();
			int player = game.getCurrentPlayer()-1;
			if(game.getPlayers()[player].getResources().getMoney()>=COST_LEVEL[level]) {
				float money = game.getPlayers()[player].getResources().getMoney();
				money-=COST_LEVEL[level];
				game.getPlayers()[player].getResources().setMoney(money);
				game.getCurrentSquare().setLevel(level+1);
				
				float moneyGains = game.getCurrentSquare().getResources().getMoney();
				float foodGains = game.getCurrentSquare().getResources().getFood();
				float oilGains = game.getCurrentSquare().getResources().getOil();
				float electricityGains = game.getCurrentSquare().getResources().getElectricity();
				switch(game.getCurrentSquare().getType()) {
				case(5):moneyGains+=MAIN_INCREASE[level];
				break;
				case(6):moneyGains+=SECONDARY_INCREASE[level];
				foodGains+=MAIN_INCREASE[level];
				break;
				case(7):moneyGains+=SECONDARY_INCREASE[level];
				oilGains+=MAIN_INCREASE[level];
				break;
				case(8):moneyGains+=SECONDARY_INCREASE[level];
				electricityGains+=MAIN_INCREASE[level];
				break;
				case(9):moneyGains+=SECONDARY_INCREASE[level];
				foodGains+=SECONDARY_INCREASE[level];
				oilGains+=SECONDARY_INCREASE[level];
				electricityGains+=SECONDARY_INCREASE[level];
				break;
				}
				Resources gains = new Resources(moneyGains, foodGains, oilGains, electricityGains);
				game.getCurrentSquare().setResources(gains);
				updateGains(game, player);
			}
		}
	}
	public void updateGains(Game game, int player){
		ArrayList<Square> buildings = new ArrayList<Square>();
		buildings.addAll(game.getPlayers()[player].getBuildings().values());
		Resources gains = new Resources();
		for(Square current : buildings) {
			gains.setMoney(gains.getMoney()+current.getResources().getMoney());
			gains.setFood(gains.getFood()+current.getResources().getFood());
			gains.setOil(gains.getOil()+current.getResources().getOil());
			gains.setElectricity(gains.getElectricity()+current.getResources().getElectricity());
		}
		game.getPlayers()[player].setGains(gains);
	}
}
