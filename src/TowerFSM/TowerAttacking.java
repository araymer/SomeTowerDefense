package TowerFSM;

import java.util.Observable;
import java.util.Observer;

import Model.Structure;

/**
 * Handles the attacking behavior of the structure.
 * @author Team Something
 *
 */
public class TowerAttacking implements TowerState, Observer {

	public TowerAttacking(Structure structure, int hp) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCurrentHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void takeDamage(int dmg) {
		// TODO Auto-generated method stub
		
	}

}
