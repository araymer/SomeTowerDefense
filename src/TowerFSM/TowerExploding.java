package TowerFSM;

import java.awt.Graphics2D;

import Model.Structure;

/**
 * Handles the destruction of the Tower.
 * @author Team Something
 * 
 */
public class TowerExploding extends TowerState {

	public TowerExploding (Structure tower) {
		super(tower);
//		int x = tower.getX();
//		int y = tower.getY();
		
		
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

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
