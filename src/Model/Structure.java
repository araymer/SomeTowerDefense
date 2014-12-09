package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import TowerFSM.TowerAttacking;
import TowerFSM.TowerExploding;
import TowerFSM.TowerState;
import TowerFSM.TowerStates;
import TowerFSM.TowerUpgrading;
import TowerFSM.TowerWaiting;

/**
 * Super-class of all structures. The behavior of the structures are controlled
 * by the classes in the TowerFSM package. Please note that the hitpoints
 * represented below is the maximum and the getHP method will return the
 * maximum, all current state information is passed between the state classes
 * using the changeState() method and can be called using the getCurrentHP
 * method from the TowerState interface.
 * 
 * @author Team Something
 *
 */
public abstract class Structure extends Drawable {

	public String name;
	public int hitpoints;
	public int production;
	public int range; // Range of tower (in tiles, Manhattan distance)
	public int damage;
	public int splashRadius; // tile radius (tiles in Manhattan distance)
	public int rateOfFire; // attack at this interval lower is faster
	public int buildCost;
	public SpecialAttack special;
	public int x;
	public int y;

	public static final int HEIGHT = 40;
	public static final int WIDTH = 40;
	// Variables for drawing
	public int xIncrement;
	public int yIncrement;
	public BufferedImage bImage;
	// public String imageFileName = "error.png";
	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "imageFiles"
			+ System.getProperty("file.separator");

	// for upgrades
	protected StructureType upgradeTo;
	protected int upgradeCost;

	// protected String waitImage = "error.png";
	// protected String attackImage = "error.png";
	// protected String explodeImage = "error.png";
	// protected String upgradeImage = "error.png";

	protected TowerState tower;

	public Structure(int hp, int prod, int rng, int dmg, int splash, int rate,
			int cost, int x, int y, SpecialAttack sp) {

		hitpoints = hp;
		production = prod;
		range = rng;
		damage = dmg;
		splashRadius = splash;
		rateOfFire = rate;
		special = sp;
		buildCost = cost;
		this.x = x; // added so the structure can know where it is
		this.y = y; // and can add itself as an observer to the tiles in its
					// range
		xIncrement = 0;
		yIncrement = 0;
		setImages();
		this.changeTo(TowerStates.WAIT, null);
		// How are we doing range in diagonal directions?
		// Will need to be able to have a reference to the map
		/*
		 * for (int i = 0; i < rng; i++) { if (does not fall off the map and is
		 * a path) tile.addObserver(this); }
		 */

	}

	public void draw(Graphics2D g2) {
		tower.draw(g2);
	}

	/**
	 * create static buffered images for extended classes
	 */
	protected abstract void setImages();

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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public SpecialAttack getSpecial() {
		return special;
	}

	public void update() {
		// System.out.println("structure update");
		tower.update();
	}

	public abstract void die();

	public void takeDamage(int dmg) {
		System.out.println("beep boop!");
		hitpoints -= dmg;
	}

	/**
	 * returns true if structure is destroyed
	 */
	public boolean isFinished() {
		// TODO Not finished, lol
		return false;
	}

	/**
	 * Gets the StructureType that this tower upgrades to.
	 * 
	 * @return StructureType
	 */
	public StructureType getUpgradeTo() {
		return upgradeTo;
	}

	/**
	 * Returns the cost to upgrade.
	 * 
	 * @return int
	 */
	public int getUpgradeCost() {
		return upgradeCost;
	}

	/**
	 * Returns appropriate image based on state
	 * 
	 * @param state
	 * @return
	 */
	protected abstract BufferedImage getImage(TowerStates state);

	/**
	 * Changes to a different state, called from TowersFSM classes.
	 * 
	 * @param TowerStates
	 * @param Attacker
	 * @param int
	 */
	public void changeTo(TowerStates newState, Attacker atkr) {
		switch (newState) {
		case ATTACK:
			bImage = getImage(newState);
			tower = new TowerAttacking(this, atkr);
			break;
		case WAIT:
			bImage = getImage(newState);
			tower = new TowerWaiting(this);
			break;
		case EXPLODE:
			System.out.println("Structure: state changed to EXPLODE");
			bImage = getImage(newState);
			tower = new TowerExploding(this);
			break;
		case UPGRADE:
			System.out.println("Structure: state changed to UPGRADE");
			bImage = getImage(newState);
			tower = new TowerUpgrading(this);
			break;
		default:
			System.out.println("Problem Encountered while changing states.");
		}

	}

	public String getName() {
		return name;
	}

	public void upgrade() {
		// TODO: set upgradability???

	}
}
