package TowerFSM;

import java.awt.Graphics2D;
import java.io.Serializable;

import Model.Structure;
import Structures.*;

/**
 * This class controls the behavior of the tower while it is in the process of
 * upgrading. Note: towers take X2 damage while upgrading.
 * 
 * NOTE: UNUSED, UPGRADES HANDLED IN: Map class. 
 * 
 * @author Team Something
 *
 */
@SuppressWarnings("serial")
public class TowerUpgrading extends TowerState implements Serializable{
	Structure tower;
	

	public TowerUpgrading(Structure structure) {
		super(structure);
		tower = structure;
		update();
	}

	/**
	 * Returns the current hit points that the tower has.
	 * 
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
		tower.hitpoints -= (dmg * 2);
	}

	/**
	 * Draws the graphics.
	 */
	@Override
	public void draw(Graphics2D g2) {
		if (tower.bImage == null) {
			tower.setImages();
			tower.refreshBImage(TowerStates.UPGRADE);
		}

	}

	/**
	 * Updates the upgrade for a set number of ticks.
	 */
	@Override
	public void update() {
		if (tower.getUpgradeTo() == null) {
			System.out.println("no upgrades available");
		} else {
			switch (tower.getUpgradeTo()) {
			case SENTRYGUN:
				System.out.println("attempting to upgrade");
				
				tower = new MarineSentryGunMkII(tower.x, tower.y);
				tower.changeTo(TowerStates.WAIT, null);
				break;
			case PLASMACANNON:
				tower = new HellfireCannon(tower.x, tower.y);
				tower.changeTo(TowerStates.WAIT, null);
				break;
			case CHRONOTOWER:
				tower = new StasisTower(tower.x, tower.y);
				tower.changeTo(TowerStates.WAIT, null);
				break;
			default:
				System.out.println("ERROR in update method TowerUpgrading");
			}
		}
		

	}

}
