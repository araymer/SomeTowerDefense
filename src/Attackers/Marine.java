package Attackers;

import Model.Attacker;
import Model.Structure;

public class Marine extends Attacker {



	public Marine() {
		super(50, 10, 50, 2, 500);
	}



	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(Structure s) {
		s.takeDamage(this.getAttack());
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
