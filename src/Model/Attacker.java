package Model;

//Test, let's see if git is working.
//Test 2

public abstract class Attacker {

	private int hitpoints;
	private int attackRating;

	private int speed; // The number of milliseconds it takes to cross a full
						// tile
	private int defenseRating;
	private int range;
	private Tile location; // attacker keeps track of own location

	// private int cost; //This is for itr2, possibly

	public Attacker(int hp, int def, int ar, int range, int spd) {

		hitpoints = hp;
		attackRating = ar;
		this.range = range;
		speed = spd;
		defenseRating = def;

	}

	public void move(Dir direction) {
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

	// TODO: implement movement based off the direction passed from the tile

	public abstract void draw();
	
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

	public Tile getLoc() {
		return location;
	}

	public void setLoc(Tile loc) {
		location = loc;
	}

}
