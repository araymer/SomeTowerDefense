package Structures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Base;
import Model.Structure;
import TowerFSM.TowerStates;

@SuppressWarnings("serial")
public class BaseBrokenPlainsPatrol extends Base {

	protected static BufferedImage waitImage;
	protected static BufferedImage attackImage;
	protected static BufferedImage upgradeImage;
	protected static BufferedImage explodeImage;

	public BaseBrokenPlainsPatrol(int x, int y) {
		super(500, 0, 0, 0, 0, 0, 0, x, y, null);
		name = "Broken Plains Patrol Base";
		price = 0;
	}

	@Override
	public void setImages() {
		if (waitImage == null) {
			File imageFile = new File(Structure.baseDir + "BrokenPlainsBase.png");
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
