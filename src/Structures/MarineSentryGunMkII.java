package Structures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.SpecialAttack;
import Model.Structure;
import TowerFSM.TowerStates;
import TowerFSM.TowerWaiting;

/**
 * The upgraded version of the Sentry Gun. It can track and attack two separate targets simultaneously.
 * Should not be buildable on it's own, must be an upgrade to the sentry gun.
 * @author Team Something
 *
 */
public class MarineSentryGunMkII extends Structure {
	protected static BufferedImage waitImage;
	protected static BufferedImage attackImage;
	protected static BufferedImage upgradeImage;
	protected static BufferedImage explodeImage;
	
	private static final int HITPOINTS = 200;
	private static final int PRODUCTION = 0;
	private static final int RANGE = 3;
	private static final int DAMAGE = 7;
	private static final int SPLASH = 0;
	private static final int RATEOFFIRE = 5;//The smaller, the faster
	private static final int COST = 3000;

	public MarineSentryGunMkII(int x, int y) {
		super(HITPOINTS, PRODUCTION, RANGE, DAMAGE, SPLASH, RATEOFFIRE, COST, x, y, SpecialAttack.TWO_TARGETS);
		setImages();
		upgradeTo = null;
		upgradeCost = 0;
		tower = new TowerWaiting(this);
	}

	@Override
	protected void setImages() {
		if (waitImage == null) {
			File imageFile = new File(Structure.baseDir + "MarineSentryGunMkII.png");
			try {
				waitImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (attackImage == null) {
			File imageFile = new File(Structure.baseDir + "error.png");
			try {
				attackImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (upgradeImage == null) {
			File imageFile = new File(Structure.baseDir + "error.png");
			try {
				upgradeImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (explodeImage == null) {
			File imageFile = new File(Structure.baseDir + "explosion-sprite40.png");
			try {
				explodeImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

		


	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

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
}

