package TowerFSM;

import java.awt.Graphics2D;

import Model.Structure;
/**
 * This class controls the behavior of the tower while it is in the 
 * process of upgrading. Note: towers take X2 damage while upgrading.
 * @author Team Something
 *
 */
public class TowerUpgrading extends TowerState {
	Structure tower;

	public TowerUpgrading(Structure structure) {
		super(structure);
		tower = structure;
		
	}

	/**
	 * Returns the current hit points that the tower has.
	 * @return int
	 */
	@Override
	public int getCurrentHP() {
		return tower.hitpoints;
	}

	/**
	 * Damages tower at +100%.
	 */
	@Override
	public void takeDamage(int dmg) {
		tower.hitpoints -= (dmg*2);
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
