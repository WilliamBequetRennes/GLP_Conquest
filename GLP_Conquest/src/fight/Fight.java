package fight;

import java.util.HashMap;
import java.util.Random;

import countries.Country;
import data.Position;
import exceptions.AttributeException;
import game.Game;
import map.Map;
import units.Unit;

public class Fight {
	
	public Fight() {
		
	}
	
	public Damage calculate(Unit attackUnit, Unit defenseUnit, Map map) throws AttributeException {
		double attributeBonus = 1;
		
		int attackerAttack = attackUnit.getAttack();
		int attackerDefense = attackUnit.getDefense();
		float attackerHealth = attackUnit.getCurrentHealth();
		float attackerMinHealth = attackUnit.getCurrentHealth();
		float attackerMaxHealth = attackUnit.getCurrentHealth();
		int attackerRange = attackUnit.getRange();
		int attackerAttribute = attackUnit.getAttribute();
		float attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getAttack(); 
		
		int defenderAttack = defenseUnit.getAttack();
		int defenderDefense = defenseUnit.getDefense();
		float defenderHealth = defenseUnit.getCurrentHealth();
		float defenderMinHealth = defenseUnit.getCurrentHealth();
		float defenderMaxHealth = defenseUnit.getCurrentHealth();
		int defenderRange = defenseUnit.getRange();
		int defenderAttribute = defenseUnit.getAttribute();
		float defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getDefense();
		
		double minAttackDamage=0;
		double maxAttackDamage=0;
		float defenderMinDamage=0;
		float defenderMaxDamage=0;
		double minRevengeDamage=0;
		double maxRevengeDamage=0;
		
		if(attackUnit.getType()==3 || attackUnit.getType()==2) {
		
		minAttackDamage = attackerAttack*attackerBonus*attackerHealth/100*1.8 - defenderDefense*defenderBonus;
		maxAttackDamage = attackerAttack*attackerBonus*attackerHealth/100*2.2 - defenderDefense*defenderBonus;

		attributeBonus = attributeBonus(attackerAttribute, defenderAttribute);
		minAttackDamage = minAttackDamage*attributeBonus;
		maxAttackDamage = maxAttackDamage*attributeBonus;

		defenderMinDamage = (float) minAttackDamage;
		defenderMaxDamage = (float) maxAttackDamage;
		defenderMinHealth = (float) (defenderHealth-maxAttackDamage);
		defenderMaxHealth = (float) (defenderHealth-minAttackDamage);
		
		}
		else {
			minAttackDamage = attackerAttack*attackerBonus*attackerHealth/100*1.9 - defenderDefense*defenderBonus;
			maxAttackDamage = attackerAttack*attackerBonus*attackerHealth/100*2.1 - defenderDefense*defenderBonus;

			attributeBonus = attributeBonus(attackerAttribute, defenderAttribute);
			minAttackDamage = minAttackDamage*attributeBonus;
			maxAttackDamage = maxAttackDamage*attributeBonus;

			defenderMinDamage = (float) minAttackDamage;
			defenderMaxDamage = (float) maxAttackDamage;
			defenderMinHealth = (float) (defenderHealth-maxAttackDamage);
			defenderMaxHealth = (float) (defenderHealth-minAttackDamage);
		}
		if (defenderMaxHealth > 0) {
			if(defenderRange == attackerRange) {
				if(defenseUnit.getType()==3 || defenseUnit.getType()==2) {
					attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getDefense(); 
					defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getAttack();
					minRevengeDamage = defenderAttack*defenderBonus*defenderMinHealth/100*1.8- attackerDefense*attackerBonus;
					maxRevengeDamage = defenderAttack*defenderBonus*defenderMaxHealth/100*2.2 - attackerDefense*attackerBonus;

					minRevengeDamage = minRevengeDamage*1/attributeBonus;
					maxRevengeDamage = maxRevengeDamage*1/attributeBonus;
					
					attackerMaxHealth = (float) (attackerHealth - minRevengeDamage);
					attackerMinHealth = (float) (attackerHealth - maxRevengeDamage);
				}
				else {
					attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getDefense(); 
					defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getAttack();
					 minRevengeDamage = defenderAttack*defenderBonus*defenderMinHealth/100*1.9 - attackerDefense*attackerBonus;
					maxRevengeDamage = defenderAttack*defenderBonus*defenderMaxHealth/100*2.1 - attackerDefense*attackerBonus;

					minRevengeDamage = minRevengeDamage*1/attributeBonus;
					maxRevengeDamage = maxRevengeDamage*1/attributeBonus;
					
					attackerMaxHealth = (float) (attackerHealth - minRevengeDamage);
					attackerMinHealth = (float) (attackerHealth - maxRevengeDamage);
				}
				attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getDefense(); 
				defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getAttack();
				minRevengeDamage = defenderAttack*defenderBonus*defenderHealth/100*1.9 - attackerDefense*attackerBonus;
				maxRevengeDamage = defenderAttack*defenderBonus*defenderHealth/100*2.1 - attackerDefense*attackerBonus;

				minRevengeDamage = minRevengeDamage*1/attributeBonus;
				maxRevengeDamage = maxRevengeDamage*1/attributeBonus;
				
				attackerMaxHealth = (float) (attackerHealth - minRevengeDamage);
				attackerMinHealth = (float) (attackerHealth - maxRevengeDamage);
			}
		}
		else {
			maxRevengeDamage = 0;
			minRevengeDamage = 0;
		}		
		Damage damages = new Damage(defenderMinDamage, defenderMaxDamage, (float)minRevengeDamage, (float)maxRevengeDamage,
				(float)attackerMinHealth, (float)attackerMaxHealth, (float)defenderMinHealth, (float)defenderMaxHealth);
			
		return damages;
	}
	public void fight(Unit attackUnit, Unit defenseUnit, Game game, Map map) throws AttributeException {

		double attributeBonus = 1;
		
		Country[] countries = game.getPlayers();
		Country attackerCountry = countries[game.getCurrentPlayer()];
		
		int attackerAttack = attackUnit.getAttack();
		int attackerDefense = attackUnit.getDefense();
		float attackerHealth = attackUnit.getCurrentHealth();
		int attackerRange = attackUnit.getRange();
		int attackerAttribute = attackUnit.getAttribute();
		float attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getAttack(); 
		
		int defenderAttack = defenseUnit.getAttack();
		int defenderDefense = defenseUnit.getDefense();
		float defenderHealth = defenseUnit.getCurrentHealth();
		int defenderRange = defenseUnit.getRange();
		int defenderAttribute = defenseUnit.getAttribute();
		float defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getDefense();
		Position defenderPosition = defenseUnit.getPosition();
		
		Random r = new Random();

		double random = 1;
		
		if(attackUnit.getType()==3 || attackUnit.getType()==2) {
			random = 1.8 + r.nextDouble() * (2.2 - 1.8);
		}
		else {
			random = 1.9 + r.nextDouble() * (2.1 - 1.9);
		}
		
		double damage = attackerAttack*attackerBonus*attackerHealth/100*random - defenderDefense*defenderBonus;
		attributeBonus = attributeBonus(attackerAttribute, defenderAttribute);
		damage = damage*attributeBonus;
		
		defenderHealth = (float) (defenderHealth-damage);
		defenseUnit.setCurrentHealth(defenderHealth);
		
		if (defenderHealth > 0) {
			if(defenderRange == attackerRange) {
				attackerBonus = map.getSquareType(attackUnit.getPosition()).getBonus().getDefense(); 
				defenderBonus = map.getSquareType(defenseUnit.getPosition()).getBonus().getAttack();
				damage = defenderAttack*defenderBonus*defenderHealth/100 - attackerDefense*attackerBonus;
				damage = damage*1/attributeBonus;
				
				attackerHealth = (float) (attackerHealth - damage);
				attackUnit.setCurrentHealth(attackerHealth);
				
				if(attackerHealth < 0) {
					
					HashMap<Position, Unit> units = attackerCountry.getUnits();
					Position position = attackUnit.getPosition();
					units.remove(position,attackUnit);
					attackerCountry.setUnits(units);
				}
			}
		}
		else {
			boolean test = false;
			int i = 0;
			while(test == false) {
				Country country = countries[i] ;
				if(country.getUnits().containsKey(defenderPosition)) {
					HashMap<Position, Unit> units = country.getUnits();
					units.remove(defenderPosition, defenseUnit);
					country.setUnits(units);
					test=true;
				}
				i++;
			}
		}		
	}
	
	public double attributeBonus(int attribute1, int attribute2) throws AttributeException{
		double bonus = 1;
		if (attribute1 == 1) {
			if (attribute2 == 2) {
				bonus = 1.5;
			}
			else if (attribute2 == 3) {
				bonus = 0.75;
			}
			else {
				AttributeException except = new AttributeException();
				throw except;
			}
		}
		else if (attribute1 == 2) {
			if (attribute2 == 3) {
				bonus = 1.5;
			}
			else if (attribute2 == 1) {
				bonus = 0.75;
			}
			else {
				AttributeException except = new AttributeException();
				throw except;
			}
		}
		else if (attribute1 == 3) {
			if (attribute2 == 1) {
				bonus = 1.5;
			}
			else if (attribute2 == 2) {
				bonus = 0.75;
			}
			else {
				AttributeException except = new AttributeException();
				throw except;
			}
		}
		else {
			AttributeException except = new AttributeException();
			throw except;
		}
		return bonus;
	}
}
