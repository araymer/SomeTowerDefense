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

import Model.Structure;
import TowerFSM.TowerAttacking;
import TowerFSM.TowerExploding;
import TowerFSM.TowerStates;
import TowerFSM.TowerUpgrading;
import TowerFSM.TowerWaiting;

public class MarineSentryGun extends Structure {
	
	protected static BufferedImage waitImage;
	protected static BufferedImage attackImage;
	protected static BufferedImage upgradeImage;
	protected static BufferedImage explodeImage;

	public MarineSentryGun(int x, int y) {
		super(120, 0, 5, 11, 0, 200, 1500, x, y, null);
		setImages();
		tower = new TowerWaiting(this);
	}
	
	protected void setImages(){
//		waitImage = "topdownturret40.png";
//		attackImage = "turretFire.png";
		//Set rest when available
		
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
			File imageFile = new File(Structure.baseDir + "error.png");
			try {
				upgradeImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (explodeImage == null) {
			File imageFile = new File(Structure.baseDir + "error.png");
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

	// @Override
	// public void draw(Graphics2D g2) {
	// if(bImage == null){
	// File imageFile = new File(baseDir + imageFileName);
	// try {
	// bImage = ImageIO.read(imageFile);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// if((xIncrement * WIDTH) + WIDTH > bImage.getWidth()){
	// yIncrement ++;
	// xIncrement = 0;
	// }
	// if((yIncrement * HEIGHT) + HEIGHT > bImage.getHeight()){
	// //Start from beginning again
	// yIncrement = 0;
	// }
	// BufferedImage tempSubImage = bImage.getSubimage(xIncrement * WIDTH,
	// yIncrement * HEIGHT, WIDTH, HEIGHT);
	// xIncrement ++;
	// g2.drawImage(tempSubImage, getX() * WIDTH, getY() * HEIGHT, WIDTH,
	// HEIGHT, null);
	//
	// }

}
