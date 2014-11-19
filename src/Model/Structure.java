
package Model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import TowerFSM.*;

/**
 * Super-class of all structures. The behavior of the structures are controlled by
 * the classes in the TowerFSM package. Please note that the hitpoints represented
 * below is the maximum and the getHP method will return the maximum, all current
 * state information is passed between the state classes using the changeState()
 * method and can be called using the getCurrentHP method from the TowerState
 * interface. 
 * @author Team Something
 *
 */
public abstract class Structure {

	protected int hitpoints;
	private int production;
	private int range; // Range of tower (in tiles, Manhattan distance)
	private int damage;
	private int splashRadius; // tile radius (tiles in Manhattan distance)
	private int rateOfFire; // in milliseconds
	private int buildCost;
	private SpecialAttack special;
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
	protected TowerState tower;

	public Structure(int hp, int prod, int rng, int dmg, int splash, int rate,
			int cost, int x, int y, SpecialAttack sp) {
		tower = new TowerWaiting(this, hp);
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
		 * for (int i = 0; i < rng; i++) {
		 *  if (does not fall off the map and is a path) 
		 *  tile.addObserver(this);
		 *  }
		 */

	}
	
	public abstract void draw(Graphics2D g2);

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
	
	/**
	 * Changes to a different state, called from TowersFSM classes.
	 * @param TowerStates
	 * @param int
	 */
	public void changeTo(TowerStates newState, Object atk, int hp) {
		switch(newState) {
		case ATTACK:
			tower = new TowerAttacking(this, hp);
			break;
		case WAIT:
			tower = new TowerWaiting(this, hp);
			break;
		case EXPLODE:
			tower = new TowerExploding(this);
			break;
		case UPGRADE:
			tower = new TowerUpgrading(this, hp);
			break;
		default:
			System.out.println("Problem Encountered while changing states.")
		}
		
	}
}

