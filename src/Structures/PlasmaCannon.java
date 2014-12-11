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
 * The class for the Plasma Cannon structure, a heavy duty turret that
 * deep-fries enemies where they stand.
 * 
 * @author Team Something
 *
 */
public class PlasmaCannon extends Structure {
	protected static BufferedImage waitImage;
	protected static BufferedImage attackImage;
	protected static BufferedImage upgradeImage;
	protected static BufferedImage explodeImage;

	private static final int HITPOINTS = 200;
	private static final int PRODUCTION = 0;
	private static final int RANGE = 4;
	private static final int DAMAGE = 14;
	private static final int SPLASH = 0;
	private static final int RATEOFFIRE = 7;// The smaller, the faster
	private static final int COST = 3000;

	public PlasmaCannon(int x, int y) {
		super(HITPOINTS, PRODUCTION, RANGE, DAMAGE, SPLASH, RATEOFFIRE, COST,
				x, y, SpecialAttack.BURN);
		setImages();
		upgradeTo = StructureType.HELLFIRECANNON;
		tower = new TowerWaiting(this);
		name = "Plasma Cannon";
	}

	@Override
	protected void setImages() {
		if (waitImage == null) {
			File imageFile = new File(Structure.baseDir + "PlasmaCannon.png");
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
			File imageFile = new File(Structure.baseDir + "PlasmaCannon.png");
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

	@Override
	public void die() {
		// TODO: implement dying

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
