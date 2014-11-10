package Structures;
/*
 * The go-to weapon for the Corps Of Planetary Acquisition (COPA - space marines). This machine-gun
 * turret-style sentry is cheap. What it lacks in damage, it makes up for with deployability and speed.
 * 
 */


import Model.Attacker;
import Model.SpecialAttack;
import Model.Structure;

public class MarineSentryGun extends Structure {

	public MarineSentryGun() {
		super(120, 0, 5, 11, 0, 200, 1500, null);
	}

	@Override
	public void shoot(Attacker a) {
		a.takeDamage(this.getDamage());
		
	}

	@Override
	public void takeDamage(int dmg) {
		hitpoints -= dmg;		
	}

	@Override
	public void explode() {
		// TODO Auto-generated method stub
		
	}

	

}
