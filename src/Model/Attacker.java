package Model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	//Variables for drawing
	protected int xIncrement;
	protected int yIncrement;
	protected BufferedImage bImage;
	protected String imageFileName = "error.png";
	protected static String baseDir = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "imageFiles"
				+ System.getProperty("file.separator");

	// private int cost; //This is for itr2, possibly

	public Attacker(int hp, int def, int ar, int range, int spd) {

		hitpoints = hp;
		attackRating = ar;
		this.range = range;
		speed = spd;
		defenseRating = def;
		
		xIncrement = 0;
		yIncrement = 0;
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

}
