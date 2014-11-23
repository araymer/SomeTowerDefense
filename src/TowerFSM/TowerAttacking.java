package TowerFSM;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.Structure;

/**
 * Handles the attacking behavior of the structure. In this state the tower will
 * attack the Attacker that was passed when the state changed and continue to do
 * so until the target is destroyed, moves beyond the tower's range or the tower
 * itself is either destroyed or begins upgrading.
 * 
 * @author Team Something
 *
 */
public class TowerAttacking extends TowerState {
	int HP;
	Structure tower;
	Attacker target;
	boolean upgrade;
	boolean noTarget;

	/**
	 * 
	 * @param structure
	 * @param atk
	 * @param hp
	 */
	public TowerAttacking(Structure structure, Attacker atk) {
		super(structure);
		tower = structure;
		// HP = hp;
		target = atk;
		//attacking();
	}

	/**
	 * The tower attacks the targeted hostile unit. Upon the elimination or
	 * departure from range of the targeted unit, the tower will change to WAIT
	 * state.
	 */
	private void shoot() {
		//System.out.println("I seen tim");
		if (inRange()) {
			target.takeDamage(tower.getDamage());
		} else {
			tower.changeTo(TowerStates.WAIT, null);
		}
	}

	/**
	 * Determines if a target is in range of the tower.
	 * 
	 * @return
	 */
	private boolean inRange() {
		// if range to target => tower.getRange(), then damage
		return false;
	}

	/**
	 * Gets the current hit points of the structure.
	 * 
	 * @return int
	 */
	@Override
	public int getCurrentHP() {
		return HP;
	}

	@Override
	public void takeDamage(int dmg) {
		HP -= dmg;

	}

//	private void attacking() {
//		Thread th = new Thread(new Runnable() {
//			public void run() {
//				boolean stateChange = false;
//				while (!stateChange) {
//					//shoot();
//					if (HP <= 0) {
//						tower.changeTo(TowerStates.EXPLODE, null);
//						stateChange = true;
//					} else if (upgrade) {
//						// TODO (Iteration 2: make towers able to upgrade
//					} else if (noTarget) {
//						tower.changeTo(TowerStates.WAIT, null);
//						stateChange = true;
//					}
//					// wait for predefined number of ticks
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
	
	private void attacking() {
				boolean stateChange = false;
				if (!stateChange) {
					//shoot();
					if (HP <= 0) {
						tower.changeTo(TowerStates.EXPLODE, null);
						stateChange = true;
					} else if (upgrade) {
						// TODO (Iteration 2: make towers able to upgrade
					} else if (noTarget) {
						tower.changeTo(TowerStates.WAIT, null);
						stateChange = true;
					}
				}
	}
	
	//TODO Will work on sprites next iteration
	@Override
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

	@Override
	public void update() {
		attacking();
		shoot();

	}
}
