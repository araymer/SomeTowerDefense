package TowerFSM;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.SpecialAttack;
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
	//int HP;
	Structure tower;
	Attacker target;
	boolean upgrade;
	boolean noTarget;
	private int tick;

	/**
	 * 
	 * @param structure
	 * @param atk
	 * @param hp
	 */
	public TowerAttacking(Structure structure, Attacker atk) {
		super(structure);
		tower = structure;
		target = atk;
	}

	/**
	 * The tower attacks the targeted hostile unit. Upon the elimination or
	 * departure from range of the targeted unit, the tower will change to WAIT
	 * state.
	 */
	private void shoot() {
		//System.out.println("I seen him");
		if(target.isFinished()){
			System.out.println("TowerAttacking: Enemy died, mission complete");
			tower.changeTo(TowerStates.WAIT, null);
		}else if (inRange()) {
			if(tick % tower.rateOfFire == 0){
				target.takeDamage(tower.getDamage());
			}
			
		} else {
			System.out.println("TowerAttacking: Enemy went out of range, switching to wait");
			tower.changeTo(TowerStates.WAIT, null);
		}
		
		// if the tower has TWO_TARGETS special attack, it see if there is more than one
		// enemy on the same tile, and attacks the second one
		if(tower.getSpecial() == SpecialAttack.TWO_TARGETS) {
			target.location.getAttackers().trimToSize();
			if(target.location.getAttackers().size() > 1) {
				target.location.getAttackers().get(1).takeDamage(tower.getDamage());
			}
		}
	}

	/**
	 * Determines if a target is in range of the tower.
	 * 
	 * @return
	 */
	private boolean inRange() {
		// if range to target => tower.getRange(), then damage
		// SQRT((X2-X1)^2+(Y2-Y1)^2)
		double x = Math.pow((tower.x - target.getX()), 2);
		double y = Math.pow((tower.y - target.getY()), 2);
		double dist = Math.sqrt(x+y);
		double absdist = Math.abs(dist);
		if(absdist <= tower.range){
			return true;
		}else{
			return false;
		}
		
	}

	/**
	 * Gets the current hit points of the structure.
	 * 
	 * @return int
	 */
	@Override
	public int getCurrentHP() {
		return tower.hitpoints;
	}

	@Override
	public void takeDamage(int dmg) {
		tower.hitpoints -= dmg;

	}

	
	private void attacking() {
				boolean stateChange = false;
				if (!stateChange) {
					shoot();
					if (tower.hitpoints <= 0) {
						System.out.println("TowerAttacking: hp less than 0");
						tower.changeTo(TowerStates.EXPLODE, null);
						stateChange = true;
					} else if (upgrade) {
						tower.changeTo(TowerStates.UPGRADE, null);
						stateChange = true;
					} else if (noTarget) {
						tower.changeTo(TowerStates.WAIT, null);
						stateChange = true;
					}
				}
	}
	
	//TODO Will work on spites next iteration
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
		if(tick == 1000){
			tick = 1;
		}else{
			tick++;
		}
		attacking();

	}
}
