package TowerFSM;

import java.util.Observable;
import java.util.Observer;

import Model.Attacker;
import Model.Map;
import Model.Structure;

/**
 * Controls tower behavior when tower is idle.
 * @author Team Something
 *
 */
public class TowerWaiting implements TowerState, Observer {
	Structure tower;
	int HP;
	int range;
	int x, y;
	boolean upgrade;
	boolean signal;
	Object attacker;
	/**
	 * 
	 * @param theTower
	 * @param hp
	 */
	public TowerWaiting(Structure theTower, int hp) {
		tower = theTower;
		HP = hp;
		range = tower.getRange();
		x = tower.getX();
		y = tower.getY();
		upgrade = false;
		signal = false;
		//waiting();
	}
	
	/**
	 * Changes state according to attackers entering range, HP reaching
	 * zero or less, or upgrade being initiated.
	 * @param obs
	 * @param atk
	 */
	public void waiting() {
		//TODO: make thread safe
		boolean stateChange = false;
		while(!stateChange) {
			if(HP <= 0) {
				tower.changeTo(TowerStates.EXPLODE, attacker, HP);
				stateChange = true;
			} else if(upgrade) {
				//TODO (Iteration 2: make towers able to upgrade
			} else if(signal) {
				tower.changeTo(TowerStates.ATTACK, attacker, HP);
				stateChange = true;
			}
			// wait for predefined number of ticks
		}
	}
	
	public void update(Observable obs, Object atk) {
		attacker = atk;
		signal = true;
	}
	
	
	/**
	 * This class is attached to attackers and defenders to determine if a
	 * hostile unit is in range and to initiate hostile activity against them.
	 * 
	 * @author Team Something
	 *
	 */
	private class Detector {
		int x, y, range; // class attributes
		Map map;

		public Detector(int locX, int locY, Map theMap, int theRange) {
			x = locX;
			y = locY;
			map = theMap;
			range = theRange;

		}

		/**
		 * Checks if any objects that can be attacked is within range.
		 * 
		 * @return if something is in range and can be attacked
		 */
		public boolean scan() {
			/*
			 * for(int a = 0; a < MasterList.unitList.toArray().length; a++){
			 * 
			 * if(Math.abs((x - MasterList.unitList[a].toArray().length) <=
			 * range) && (Math.abs(y - MasterList.unitList[a].toArray().length)
			 * <= range) && MasterList.unitList[a].hostile()) <= range {
			 * 
			 * } }
			 */
			return false;
		}

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
