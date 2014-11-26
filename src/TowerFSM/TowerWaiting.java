package TowerFSM;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.Structure;
import View.TilePanel;

/**
 * Controls tower behavior when tower is idle.
 * 
 * @author Team Something
 *
 */
public class TowerWaiting extends TowerState {
	// Structure tower;
	// int HP;
	// int range;
	// int x, y;
	// boolean upgrade;
	// boolean signal;
	// Attacker attacker;

	// static final int HEIGHT = 40;
	// static final int WIDTH = 40;
	// //Variables for drawing
	// int xIncrement = 0;
	// int yIncrement = 0;
	// BufferedImage bImage = null;
	// String imageFileName;
	// static String baseDir = System.getProperty("user.dir")
	// + System.getProperty("file.separator") + "imageFiles"
	// + System.getProperty("file.separator");
	Thread th;
	boolean stateChange = false;
	
	/**
	 * 
	 * @param theTower
	 */
	public TowerWaiting(Structure theTower) {
		super(theTower);
		tower = theTower;
		// HP = hp;
		// range = tower.getRange();
		// x = tower.getX();
		// y = tower.getY();
		upgrade = false;
		signal = false;
		// imageFileName = tower.imageFileName;
		//waiting();

	}

	/**
	 * Changes state according to attackers entering range, HP reaching zero or
	 * less, or upgrade being initiated.
	 */
//	private void waiting() {
//		th = new Thread(new Runnable() {
//			public void run() {
//				while (!stateChange) {
//					if (tower.hitpoints <= 0) {
//						tower.changeTo(TowerStates.EXPLODE, null);
//						stateChange = true;
//					} else if (upgrade) {
//						// TODO (Iteration 2: make towers able to upgrade
//					} else if (signal) {
//						tower.changeTo(TowerStates.ATTACK, attacker);
//						stateChange = true;
//					}
//					 //wait for predefined number of ticks
//					try {
//						Thread.sleep(10); // or it will cause massive CPU usage
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
//		th.start();
//	}
	
	private void waiting() {
				if (!stateChange) {
					if (tower.hitpoints <= 0) {
						tower.changeTo(TowerStates.EXPLODE, null);
						stateChange = true;
					} else if (upgrade) {
						// TODO (Iteration 2: make towers able to upgrade
					} else if (signal) {
						tower.changeTo(TowerStates.ATTACK, attacker);
						stateChange = true;
					}
				}	
	}

	// public void update(Attacker atk) {
	// attacker = atk;
	// signal = true;
	// }

	public void draw(Graphics2D g2) {
//		if (tower.bImage == null) {
//			File imageFile = new File(Structure.baseDir + tower.imageFileName);
//			try {
//				tower.bImage = ImageIO.read(imageFile);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		if ((tower.xIncrement * tower.WIDTH) + tower.WIDTH > tower.bImage
				.getWidth()) {
			tower.yIncrement++;
			tower.xIncrement = 0;
		}
		if ((tower.yIncrement * tower.HEIGHT) + tower.HEIGHT > tower.bImage
				.getHeight()) {
			// Start from beginning again
			tower.yIncrement = 0;
		}
		BufferedImage tempSubImage = tower.bImage.getSubimage(tower.xIncrement
				* tower.WIDTH, tower.yIncrement * tower.HEIGHT, tower.WIDTH,
				tower.HEIGHT);
		tower.xIncrement++;
		g2.drawImage(tempSubImage, tower.getX() * tower.WIDTH, tower.getY()
				* tower.HEIGHT, tower.WIDTH, tower.HEIGHT, null);

	}

	/**
	 * This class is attached to attackers and defenders to determine if a
	 * hostile unit is in range and to initiate hostile activity against them.
	 * 
	 * @author Team Something
	 *
	 */
	// private class Detector {
	// int x, y, range; // class attributes
	// Map map;
	//
	// public Detector(int locX, int locY, Map theMap, int theRange) {
	// x = locX;
	// y = locY;
	// map = theMap;
	// range = theRange;
	//
	// }
	//
	// /**
	// * Checks if any objects that can be attacked is within range.
	// *
	// * @return if something is in range and can be attacked
	// */
	// public boolean scan() {
	// /*
	// * for(int a = 0; a < MasterList.unitList.toArray().length; a++){
	// *
	// * if(Math.abs((x - MasterList.unitList[a].toArray().length) <=
	// * range) && (Math.abs(y - MasterList.unitList[a].toArray().length)
	// * <= range) && MasterList.unitList[a].hostile()) <= range {
	// *
	// * } }
	// */
	// return false;
	// }
	//
	// }

	@Override
	public int getCurrentHP() {
		return tower.hitpoints;
	}

	@Override
	public void takeDamage(int dmg) {
		tower.hitpoints -= dmg;
	}
	
	private void scanForAttackers(){
		// Check for attackers
				for (int x = tower.x - tower.range; x <= tower.x + tower.range; x++) {
					for (int y = tower.y - tower.range; y <= tower.y + tower.range; y++) {
						// TODO Check to make sure x and y are within the map boundaries
						if (x < TilePanel.getInstance().tileMap.getGameBoard().size()
								&& x >= 0
								&& y < TilePanel.getInstance().tileMap.getGameBoard()
										.get(x).size() && y >= 0) {
								Vector<Attacker> atkrList = TilePanel.getInstance().tileMap
										.getGameBoard().get(x).get(y).getAttackers();
								if (atkrList != null && atkrList.size() > 0) {
									tower.changeTo(TowerStates.ATTACK, atkrList.get(0));
									stateChange = true;
									return;
								}
						}
					}
				}
	}

	@Override
	public void update() {
		scanForAttackers();
		waiting();

	}

}
