package Attackers;

import Model.Attacker;
import Model.Structure;

public class Marine extends Attacker {

	public Marine() {
		super(50, 10, 50, 2, 500);
		//imageFileName = topdownmarines
	}

	@Override
	public void attack(Structure s) {
		s.takeDamage(this.getAttack());
	}

	@Override
	public void die() {
		// play dying animation and remove the attacker

	}


}
