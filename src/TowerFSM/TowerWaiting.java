package TowerFSM;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
@SuppressWarnings("serial")
public class TowerWaiting extends TowerState implements Serializable{
	
	boolean stateChange = false;
	
	/**
	 * 
	 * @param theTower
	 */
	public TowerWaiting(Structure theTower) {
		super(theTower);
		tower = theTower;
		
		upgrade = false;
		signal = false;
		

	}

	/**
	 * Changes state according to attackers entering range, HP reaching zero or
	 * less, or upgrade being initiated.
	 */
	private void waiting() {
				if (!stateChange) {
					if (tower.hitpoints <= 0) {
						tower.changeTo(TowerStates.EXPLODE, null);
						stateChange = true;
					} else if (upgrade) {
						tower.changeTo(TowerStates.UPGRADE, null);
						stateChange = true;
					} else if (signal) {
						tower.changeTo(TowerStates.ATTACK, attacker);
						stateChange = true;
					}
				}	
	}

	

	public void draw(Graphics2D g2) {
		if (tower.bImage == null) {
			tower.setImages();
			tower.refreshBImage(TowerStates.WAIT);
		}

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
