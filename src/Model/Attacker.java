package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
	private int x;
	private int y;
	
	protected static final int HEIGHT = 40;
	protected static final int WIDTH = 40;
	//Variables for drawing
	protected int xIncrement;
	protected int yIncrement;
	protected BufferedImage bImage;
	protected String imageFileName = "error.png";
	protected static String baseDir = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "imageFiles"
				+ System.getProperty("file.separator");

	// private int cost; //This is for itr2, possibly
	Map map;
	public Attacker(int hp, int def, int ar, int range, int spd) {

		hitpoints = hp;
		attackRating = ar;
		this.range = range;
		speed = spd;
		defenseRating = def;
		
		xIncrement = 0;
		yIncrement = 0;
		//map = m;
		// TODO: set location: tile.getCoordinates

	}

	public void move(Dir direction) {
		switch (direction) {
		case UP:

		case DOWN:

		case LEFT:

		case RIGHT:

		default:

		}
	}

	// TODO: implement movement based off the direction passed from the tile

	public abstract void draw(Graphics2D g2);

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
	
	/**
	 * Returns x coordinate
	 * @return int
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns y coordinate.
	 * @return int
	 */
	public int getY() {
		return y;
	}
}
