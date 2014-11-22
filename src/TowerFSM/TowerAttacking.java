package TowerFSM;

import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import Model.Attacker;
import Model.Structure;

/**
 * Handles the attacking behavior of the structure. In this state the
 * tower will attack the Attacker that was passed when the state changed
 * and continue to do so until the target is destroyed, moves beyond
 * the tower's range or the tower itself is either destroyed or begins
 * upgrading.
 * @author Team Something
 *
 */
public class TowerAttacking extends TowerState {
	int HP;
	Structure tower;
	Attacker target;
	boolean upgrade;
	boolean noTarget;
	
	/**
	 * 
	 * @param structure
	 * @param atk
	 * @param hp
	 */
	public TowerAttacking(Structure structure, Attacker atk) {
		super(structure);
		System.out.println("Tower changed to attacking state");
		tower = structure;
		//HP = hp;
		target = atk;
		attacking();
	}
	
	/**
	 * The tower attacks the targeted hostile unit. Upon the elimination or 
	 * departure from range of the targeted unit, the tower will change 
	 * to WAIT state.  
	 */
	private void shoot() {
		if(inRange()) {
			// tell Attacker how much damage they have taken
		} else {
			tower.changeTo(TowerStates.WAIT, null);
		}
	}
	
	/**
	 * Determines if a target is in range of the tower.
	 * @return
	 */
	private boolean inRange() {
		//if range to target => tower.getRange(), then damage
		return false;
	}
	
	/**
	 * Gets the current hit points of the structure.
	 * @return int
	 */
	@Override
	public int getCurrentHP() {
		return HP;
	}

	@Override
	public void takeDamage(int dmg) {
		HP -= dmg;
		
	}
	
	private void attacking() {
		Thread th = new Thread(new Runnable() {
			public void run() {
				boolean stateChange = false;
				while(!stateChange) {
					shoot();
					if(HP <= 0) {
						tower.changeTo(TowerStates.EXPLODE, null);
						stateChange = true;
					} else if(upgrade) {
						//TODO (Iteration 2: make towers able to upgrade
					} else if(noTarget) {
						tower.changeTo(TowerStates.WAIT, null);
						stateChange = true;
					}
					// wait for predefined number of ticks
					try {
						Thread.sleep(10); //or it will cause massive CPU usage
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
			}
		});
		th.start();
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
