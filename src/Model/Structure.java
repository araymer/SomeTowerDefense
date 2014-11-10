package Model;

public abstract class Structure {

	protected int hitpoints;
	private int production;
	private int range; // Range of tower (in tiles, manhattan distance)
	private int damage;
	private int splashRadius; // tile radius (tiles in manhattan distance)
	private int rateOfFire; // in milliseconds
	private int buildCost;
	private SpecialAttack special;

	public Structure(int hp, int prod, int rng, int dmg, int splash, int rate,
			int cost, SpecialAttack sp) {

		hitpoints = hp;
		production = prod;
		range = rng;
		damage = dmg;
		splashRadius = splash;
		rateOfFire = rate;
		special = sp;
		buildCost = cost;

	}

	public abstract void shoot(Attacker a);

	public abstract void takeDamage(int dmg);

	public abstract void explode();

	public int getHP() {
		return hitpoints;
	}

	public int getProd() {
		return production;
	}

	public int getRange() {
		return range;
	}

	public int getDamage() {
		return damage;
	}

	public int getSplash() {
		return splashRadius;
	}

	public int getROF() {
		return rateOfFire;
	}

	public SpecialAttack getSpecial() {
		return special;
	}


	/**
	 * This class is attached to attackers and defenders to determine if a hostile
	 * unit is in range and to initiate hostile activity against them.
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
			for(int a = 0; a < MasterList.unitList.toArray().length; a++){
			
				if(Math.abs((x - MasterList.unitList[a].toArray().length) <= range) && 
					(Math.abs(y - MasterList.unitList[a].toArray().length) <= range) 
					&& MasterList.unitList[a].hostile()) <= range {
				
				}
			}
			return false;
		}

		public void shoot(Object O) {

		}
	}
}