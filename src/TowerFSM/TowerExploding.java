package TowerFSM;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import Model.Structure;

/**
 * Handles the destruction of the Tower.
 * 
 * @author Team Something
 * 
 */
@SuppressWarnings("serial")
public class TowerExploding extends TowerState implements Serializable{

	public TowerExploding(Structure tower) {
		super(tower);
		// int x = tower.getX();
		// int y = tower.getY();

		// tower explosion animation
	}

	@Override
	public int getCurrentHP() {
		// You are exploding, so the highest it could possibly be is:
		return 0;
	}

	@Override
	public void takeDamage(int dmg) {
		// Already being scattered across the landscape in small pieces!
		// What more do you want?
	}

	@Override
	public void draw(Graphics2D g2) {
		if (tower.bImage == null) {
			tower.setImages();
			tower.refreshBImage(TowerStates.EXPLODE);
		}
		
		if ((tower.xIncrement * tower.WIDTH) + tower.WIDTH > tower.bImage
				.getWidth()) {
			tower.yIncrement++;
			tower.xIncrement = 0;
		}
		if ((tower.yIncrement * tower.HEIGHT) + tower.HEIGHT > tower.bImage
				.getHeight()) {
			// Start from beginning again
			tower.die();
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
	public void update() {
		// TODO update

	}

}
