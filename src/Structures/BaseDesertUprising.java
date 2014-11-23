package Structures;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Structure;
import TowerFSM.TowerStates;

public class BaseDesertUprising extends Structure {
	
	protected static BufferedImage waitImage;
	protected static BufferedImage attackImage;
	protected static BufferedImage upgradeImage;
	protected static BufferedImage explodeImage;

	public BaseDesertUprising(int x, int y) {
		super(500, 0, 0, 0, 0, 0, 0, x, y, null);
	}

//	@Override
//	public void draw(Graphics2D g2) {
//		if (bImage == null) {
//			File imageFile = new File(baseDir + imageFileName);
//			try {
//				bImage = ImageIO.read(imageFile);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		if ((xIncrement * WIDTH) + WIDTH > bImage.getWidth()) {
//			yIncrement++;
//			xIncrement = 0;
//		}
//		if ((yIncrement * HEIGHT) + HEIGHT > bImage.getHeight()) {
//			// Start from beginning again
//			yIncrement = 0;
//		}
//		BufferedImage tempSubImage = bImage.getSubimage(xIncrement * WIDTH,
//				yIncrement * HEIGHT, WIDTH, HEIGHT);
//		xIncrement++;
//		g2.drawImage(tempSubImage, getX() * WIDTH, getY() * HEIGHT, WIDTH,
//				HEIGHT, null);
//
//	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setImageNames() {
		if (waitImage == null) {
			File imageFile = new File(Structure.baseDir + "base40.png");
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

}
