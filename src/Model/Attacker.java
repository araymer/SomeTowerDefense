package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import View.TilePanel;

public abstract class Attacker extends Drawable implements Serializable {

	protected String name;
	private int hitpoints;
	private int attackRating;
	protected int value;

	// The larger the speed, the slower
	private int speed; // The number of milliseconds it takes to cross a full
						// tile
	private int defenseRating;
	private int range;
	public Tile location; // attacker keeps track of own location
	protected int x;
	protected int y;

	protected static final int HEIGHT = 40;
	protected static final int WIDTH = 40;
	// Variables for drawing
	protected int xIncrement;
	protected int yIncrement;
	protected transient BufferedImage bImage;
	protected String imageFileName = "error.png";
	protected static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "imageFiles"
			+ System.getProperty("file.separator");

	protected boolean isDead = false;
	public boolean needsToMove = false;

	private int tick;

	Map map;
	
	protected SpecialAttack effect;

	public Attacker(int hp, int def, int ar, int range, int spd,
			Tile startingLocation) {

		hitpoints = hp;
		attackRating = ar;
		this.range = range;
		speed = spd;
		defenseRating = def;
		effect = SpecialAttack.NONE;
		
		xIncrement = 0;
		yIncrement = 0;

		location = startingLocation;
		setX();
		setY();
		

	}

	public void setY() {
		x = (int) location.getCoordinates().getX();

	}

	public void setX() {
		y = (int) location.getCoordinates().getY();

	}

	public void move() {
		// Wont work because you can't modify a list while it's being iterated
		// over somewhere else
		if (location.getNextTile() != null) {
			needsToMove = true;
		}
	}

	public abstract void draw(Graphics2D g2);

	public abstract void attack(Structure s);

	public abstract void die();

	public void takeDamage(int dmg, SpecialAttack special) {
		System.out.println("ouch!");
		switch(special) {
			case NONE: // do nothing
			case TWO_TARGETS:
				break;
			case FREEZE:
				effect = SpecialAttack.FREEZE;
				speed = 0;
				break;
			case SLOW:
				if(effect == SpecialAttack.NONE || effect == SpecialAttack.BURN) {
					speed *= 2;
					effect = SpecialAttack.SLOW;
				}
				break;
			case BURN:
				effect = SpecialAttack.BURN;
				break;
			default:
				System.out.println("error in attacker.takeDamage method");
		}
		hitpoints -= dmg;

		if (hitpoints <= 0) {
			die();
		}
	}

	// getters
	public String getName() {
		return name;
	}

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
		setX();
		setY();
	}

	/**
	 * Returns x coordinate
	 * 
	 * @return int
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns y coordinate.
	 * 
	 * @return int
	 */
	public int getY() {
		return y;
	}

	public void update() {
		if (tick == 1000) {
			tick = 1;
		} else {
			tick++;
		}
		int framedif = 0;
		// Moves every speed amount of ticks
		if(speed != 0) {
			if (tick % speed == 0) {
				move();
			}
		}
		// Shoots if at base
		if (tick % attackRating == 0) {
			if (location.getNextTile() == null) {
				attack(TilePanel.getInstance().tileMap.getBase());
			}
		}
		// takes damage if effected by BURN
		if(effect == SpecialAttack.BURN)
			takeDamage(10, SpecialAttack.NONE);
	}

	public boolean isFinished() {
		return isDead;
	}

}
