package TowerFSM;

import Model.Structure;

/**
 * Handles the destruction of the Tower.
 * @author Team Something
 *
 */
public class TowerExploding implements TowerState {

	public TowerExploding (Structure tower) {
		// tower explosion animation
	}
	
	@Override
	public int getCurrentHP() {
		// You are exploding, so the highest it could possibly be is:
		return 0;
	}

	@Override
	public void takeDamage(int dmg) {
		// Already being scattered across the landscape in small pieces!
		// What more do you want?
	}

}
