package Structures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.Base;
import Model.Structure;
import Model.Ticker;
import TowerFSM.TowerStates;
import View.GameGUI;

public class BaseDesertUprising extends Base {

	protected static BufferedImage waitImage;
	protected static BufferedImage attackImage;
	protected static BufferedImage upgradeImage;
	protected static BufferedImage explodeImage;

	public BaseDesertUprising(int x, int y) {
		super(500, 0, 0, 0, 0, 0, 0, x, y, null);
		name = "Desert Uprising Base";
	}

	// @Override
	// public void draw(Graphics2D g2) {
	// if (bImage == null) {
	// File imageFile = new File(baseDir + imageFileName);
	// try {
	// bImage = ImageIO.read(imageFile);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// if ((xIncrement * WIDTH) + WIDTH > bImage.getWidth()) {
	// yIncrement++;
	// xIncrement = 0;
	// }
	// if ((yIncrement * HEIGHT) + HEIGHT > bImage.getHeight()) {
	// // Start from beginning again
	// yIncrement = 0;
	// }
	// BufferedImage tempSubImage = bImage.getSubimage(xIncrement * WIDTH,
	// yIncrement * HEIGHT, WIDTH, HEIGHT);
	// xIncrement++;
	// g2.drawImage(tempSubImage, getX() * WIDTH, getY() * HEIGHT, WIDTH,
	// HEIGHT, null);
	//
	// }

	@Override
	protected void setImages() {
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
			File imageFile = new File(Structure.baseDir
					+ "explosion-sprite40.png");
			try {
				explodeImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// @Override
	// public void takeDamage(int dmg) {
	// System.out.println("WARNING: base under attack!!");
	// hitpoints -= dmg;
	// System.out.println("Base health: " + hitpoints);
	//
	// }

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

	@Override
	public void die() {
		System.out.println("\n\n\n\nBASE WAS DESTROYED. GAME OVER");
		Ticker.getInstance().loopStop();
		JFrame gameOver = new JFrame();
		gameOver.setSize(200, 100);
		gameOver.add(new JLabel("BASE WAS DESTROYED. GAME OVER."));
		gameOver.setVisible(true);
		GameGUI.getInstance().returnMenu();
	}

}
