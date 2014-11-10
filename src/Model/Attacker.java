package Model;

//Test, let's see if git is working.
public abstract class Attacker {

	private int hitpoints;
	private int attackRating;
	private int range;
	private int speed; // The number of milliseconds it takes to cross a full
						// tile
	private int defenseRating;

	// private int cost; //This is for itr2, possibly

	public Attacker(int hp, int def, int ar, int range, int spd) {

		hitpoints = hp;
		attackRating = ar;
		this.range = range;
		speed = spd;
		defenseRating = def;

	}

	public abstract void move();

	public abstract void attack(Structure s);

	public abstract void die();

	public void takeDamage(int dmg) {
		hitpoints -= dmg;
	}

	// getters
	public int getHP() {
		return hitpoints;
	}

	public int getAttack() {
		return attackRating;
	}

	public int getRange() {
		return range;
	}

	public int getSpeed() {
		return speed;
	}

	public int getAC() {
		return defenseRating;
	}

}
