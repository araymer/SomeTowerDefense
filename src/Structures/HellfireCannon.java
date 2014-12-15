package Structures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.SpecialAttack;
import Model.Structure;
import Model.StructureType;
import TowerFSM.TowerStates;
import TowerFSM.TowerWaiting;

/**
 * This is the upgraded version of the Hellfire Cannon, dealing more damage,
 * with the burning...
 * 
 * @author Team Something
 *
 */
@SuppressWarnings("serial")
public class HellfireCannon extends Structure {
	protected static BufferedImage waitImage;
	protected static BufferedImage attackImage;
	protected static BufferedImage upgradeImage;
	protected static BufferedImage explodeImage;

	private static final int HITPOINTS = 100;
	private static final int PRODUCTION = 0;
	private static final int RANGE = 4;
	private static final int DAMAGE = 20;
	private static final int SPLASH = 0;
	private static final int RATEOFFIRE = 7;// The smaller, the faster
	private static final int COST = 2500;

	/**
	 * The constructor, arguments are the x and y coordinates of the tower.
	 * @param x
	 * @param y
	 */
	public HellfireCannon(int x, int y) {
		super(HITPOINTS, PRODUCTION, RANGE, DAMAGE, SPLASH, RATEOFFIRE, COST,
				x, y, SpecialAttack.FREEZE);
		setImages();
		upgradeTo = StructureType.NONE;
		upgradeCost = 0;
		tower = new TowerWaiting(this);
		name = "Hellfire Cannon";
		price = 50;
	}

	/**
	 * Establishes the images for the various states.
	 */
	public void setImages() {

		if (waitImage == null) {
			File imageFile = new File(Structure.baseDir + "HellFireCannon.png");
			try {
				waitImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (attackImage == null) {
			File imageFile = new File(Structure.baseDir
					+ "HellFireCannonFire.png");
			try {
				attackImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (upgradeImage == null) {
			File imageFile = new File(Structure.baseDir + "HellFireCannon.png");
			try {
				upgradeImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (explodeImage == null) {
			File imageFile = new File(Structure.baseDir
					+ "explosion-sprite40.png");
			try {
				explodeImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Determines which sprite sheet to use.
	 */
	@Override
	protected BufferedImage getImage(TowerStates newState) {
		BufferedImage correctImage = null;
		switch (newState) {
		case ATTACK:
			correctImage = attackImage;
			break;
		case WAIT:
			correctImage = waitImage;
			break;
		case EXPLODE:
			correctImage = explodeImage;
			break;
		case UPGRADE:
			correctImage = upgradeImage;
			break;
		default:
			System.out.println("Problem Encountered in getImage()");
		}
		return correctImage;
	}

	/**
	 * Unused
	 */
	@Override
	public void die() {

	}


}
