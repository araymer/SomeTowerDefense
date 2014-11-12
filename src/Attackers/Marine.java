package Attackers;

import Model.Attacker;
import Model.Structure;

public class Marine extends Attacker {

	public Marine() {
		super(50, 10, 50, 2, 500);
	}

	@Override
	public void move() {
		// Will need a reference to the tile it is on. These are in the super
		// class
		// as x and y ints. Needs a reference to the instance of the map to move
		// TODO move in the specified direction
		/*
		 * switch (tile that it's on .getDirection())
		 * case LEFT:
		 *    move to the tile on the left (map coordinate x - 1)
		 *    set new coordinates
		 *    break;
		 * case RIGHT:
		 *    move to the tile on the right (map coordinate x + 1)
		 *    set new coordinates
		 *    break;
		 * case UP:
		 *    move to the tile on top (map coordinate y + 1)
		 *    set new coordinates
		 *    break;
		 * case DOWN:
		 *    move to the tile on bottom (map coordinate y - 1)
		 *    set new coordinates
		 *    break;
		 */

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
