package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//Test, let's see if git is working.
//Test 2

public abstract class Attacker extends Drawable{

	private int hitpoints;
	private int attackRating;

	private int speed; // The number of milliseconds it takes to cross a full
						// tile
	private int defenseRating;
	private int range;
	protected Tile location; // attacker keeps track of own location
	protected int x;
	protected int y;

	protected static final int HEIGHT = 40;
	protected static final int WIDTH = 40;
	// Variables for drawing
	protected int xIncrement;
	protected int yIncrement;
	protected BufferedImage bImage;
	protected String imageFileName = "error.png";
	protected static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "imageFiles"
			+ System.getProperty("file.separator");
	
	protected boolean isDead = false;

	// private int cost; //This is for itr2, possibly
	Map map;

	public Attacker(int hp, int def, int ar, int range, int spd,
			Tile startingLocation) {

		hitpoints = hp;
		attackRating = ar;
		this.range = range;
		speed = spd;
		defenseRating = def;

		xIncrement = 0;
		yIncrement = 0;

		location = startingLocation;
		setX();
		setY();

	}

	private void setY() {
		x = (int) location.getCoordinates().getX();

	}

	private void setX() {
		y = (int) location.getCoordinates().getY();

	}

	public void move() {
		//Wont work because you can't modify a list while it's being iterated over somewhere else by repaint
		if (location.getNextTile() == null)
			return;
		location.getAttackers().remove(this);
		location.getNextTile().getAttackers().add(this);
		location = location.getNextTile();

		setX();
		setY();
	}

	public abstract void draw(Graphics2D g2);

	public abstract void attack(Structure s);

	public abstract void die();

	public void takeDamage(int dmg) {
		System.out.println("ouch!");
		hitpoints -= dmg;
		
		if(hitpoints <= 0){
			System.out.println("Attacker: hp reached " + hitpoints + ", I died!!");
			die();
		}
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
	
	public void update(){
		
	}
	
	public boolean isFinished(){
		return isDead;
	}
	
}
