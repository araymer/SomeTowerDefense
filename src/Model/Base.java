package Model;

import View.GameGUI;

public abstract class Base extends Structure {

	public Base(int hp, int prod, int rng, int dmg, int splash, int rate,
			int cost, int x, int y, SpecialAttack sp) {
		super(hp, prod, rng, dmg, splash, rate, cost, x, y, sp);
		upgradeTo = StructureType.NONE;
		upgradeCost = 0;

	}

	public void setHP(int newHP) {
		hitpoints = newHP;
		System.out.println("New base hp is: " + hitpoints);
	}

	/**
	 * Special takeDamage that damages the hp on the server if on multiplayer.
	 */
	@Override
	public void takeDamage(int dmg) {
		if (GameGUI.getInstance().isMultiplayer) {
			GameGUI.getInstance().baseTakeDamage(dmg);
		} else {
			System.out.println("beep boop!");
			hitpoints -= dmg;
		}

	}

}
