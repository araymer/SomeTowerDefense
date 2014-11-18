package Structures;

/*
 * The go-to weapon for the Corps Of Planetary Acquisition (COPA - space marines). This machine-gun
 * turret-style sentry is cheap. What it lacks in damage, it makes up for with deployability and speed.
 * 
 */

import java.util.Observable;

import Model.Attacker;
import Model.Structure;

public class MarineSentryGun extends Structure {

	public MarineSentryGun(int x, int y) {
		super(120, 0, 5, 11, 0, 200, 1500, x, y, null);
		imageFileName = "topdownturret.png";
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
		// TODO play explode animation and remove sentry gun

	}

	@Override
	public void update(Observable obs, Object atk) {
		shoot((Attacker) atk);
	}

}
