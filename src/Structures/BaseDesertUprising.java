package Structures;

import java.awt.Image;

import Model.Attacker;
import Model.Structure;

public class BaseDesertUprising extends Structure {
	
	
	
	public BaseDesertUprising(int x, int y) {
		super(500, 0, 0, 0, 0, 0, 0, x, y, null);
		imageFileName = "baseDesertUprising.png";
	}

	@Override
	public void shoot(Attacker a) {
		// TODO: shoot an enemy

	}

	@Override
	public void takeDamage(int dmg) {
		hitpoints -= dmg;

	}

	@Override
	public void explode() {
		// TODO This means game was lost

	}

}
