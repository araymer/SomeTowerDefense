package Structures;

/*
 * The go-to weapon for the Corps Of Planetary Acquisition (COPA - space marines). This machine-gun
 * turret-style sentry is cheap. What it lacks in damage, it makes up for with deployability and speed.
 * 
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.SpecialAttack;
import Model.Structure;
import Model.StructureType;
import TowerFSM.TowerStates;
import TowerFSM.TowerWaiting;

@SuppressWarnings("serial")
public class MarineSentryGun extends Structure {

	protected static BufferedImage waitImage;
	protected static BufferedImage attackImage;
	protected static BufferedImage upgradeImage;
	protected static BufferedImage explodeImage;

	private static final int HITPOINTS = 120;
	private static final int PRODUCTION = 0;
	private static final int RANGE = 3;
	private static final int DAMAGE = 7;
	private static final int SPLASH = 0;
	private static final int RATEOFFIRE = 5;// The smaller, the faster
	private static final int COST = 1500;

	/**
	 * The constructor, arguments are the x and y coordinates of the tower.
	 * @param x
	 * @param y
	 */
	public MarineSentryGun(int x, int y) {
		super(HITPOINTS, PRODUCTION, RANGE, DAMAGE, SPLASH, RATEOFFIRE, COST,
				x, y, SpecialAttack.NONE);
		setImages();
		upgradeTo = StructureType.SENTRYGUN2;
		upgradeCost = 3000;
		tower = new TowerWaiting(this);
		name = "Sentry Gun";
		price = 50;
	}
	
	/**
	 * Establishes the images for the various states.
	 */
	@Override
	public void setImages() {

		if (waitImage == null) {
			File imageFile = new File(Structure.baseDir + "topdownturret40.png");
			try {
				waitImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (attackImage == null) {
			File imageFile = new File(Structure.baseDir + "turretFire.png");
			try {
				attackImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (upgradeImage == null) {
			File imageFile = new File(Structure.baseDir + "topdownTurret40.png");
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
