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
public abstract class Structure {

	public int hitpoints;
	public int production;
	public int range; // Range of tower (in tiles, Manhattan distance)
	public int damage;
	public int splashRadius; // tile radius (tiles in Manhattan distance)
	public int rateOfFire; // in milliseconds
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
	public String imageFileName = "error.png";
	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "imageFiles"
			+ System.getProperty("file.separator");
	protected TowerState tower = new TowerWaiting(this);

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
		// TODO: set as observer to the tiles in its range
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

	public abstract void update();

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
			tower = new TowerAttacking(this, atkr);
			break;
		case WAIT:
			tower = new TowerWaiting(this);
			break;
		case EXPLODE:
			tower = new TowerExploding(this);
			break;
		case UPGRADE:
			tower = new TowerUpgrading(this);
			break;
		default:
			System.out.println("Problem Encountered while changing states.");
		}

	}
}
