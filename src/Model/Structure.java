
package Model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

/**
 * 
 * @author Team Something
 *
 */
public abstract class Structure implements Observer {

	protected int hitpoints;
	private int production;
	private int range; // Range of tower (in tiles, manhattan distance)
	private int damage;
	private int splashRadius; // tile radius (tiles in manhattan distance)
	private int rateOfFire; // in milliseconds
	private int buildCost;
	private SpecialAttack special;
	private int x;
	private int y;
	private Image image;
	protected String imageFileName = "error.png";
	private static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "imageFiles"
			+ System.getProperty("file.separator");

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

	public abstract void shoot(Attacker a);

	public abstract void takeDamage(int dmg);

	public abstract void explode();
	
	public void draw(Graphics2D g2){
		if(image == null){
			File imageFile = new File(baseDir + imageFileName);
			try {
				image = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int width = 20;
		int height = 20;
		g2.drawImage(image, x, y, width, height, null);
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

	public void update(Observable obs, Object atk) {
		shoot((Attacker) atk);
	}

	/**
	 * This class is attached to attackers and defenders to determine if a
	 * hostile unit is in range and to initiate hostile activity against them.
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
			/*
			 * for(int a = 0; a < MasterList.unitList.toArray().length; a++){
			 * 
			 * if(Math.abs((x - MasterList.unitList[a].toArray().length) <=
			 * range) && (Math.abs(y - MasterList.unitList[a].toArray().length)
			 * <= range) && MasterList.unitList[a].hostile()) <= range {
			 * 
			 * } }
			 */
			return false;
		}

	}
}

