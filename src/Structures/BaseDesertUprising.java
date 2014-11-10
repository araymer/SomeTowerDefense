package Structures;

import Model.Attacker;
import Model.SpecialAttack;
import Model.Structure;

public class BaseDesertUprising extends Structure {
	
	public BaseDesertUprising() {
		super(500, 0, 0, 0, 0, 0, 0, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shoot(Attacker a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeDamage(int dmg) {
		hitpoints -= dmg;
		
	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub. This means game was lost
		
	}

}
