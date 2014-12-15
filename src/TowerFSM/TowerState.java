package TowerFSM;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import Model.Attacker;
import Model.SpecialAttack;
import Model.Structure;

/**
 * This is the parent class for all tower state classes.
 * @author Team Something
 *
 */
@SuppressWarnings("serial")
public abstract class TowerState implements Serializable{
	boolean upgrade;
	boolean signal;
	Attacker attacker;
	
	Structure tower;
	
	/**
	 * Constructor, all state changes call this.
	 * @param struct - the Structure
	 */
	public TowerState(Structure struct){
		
		
		this.tower = struct;
	}

	/**
	 * Gets the current hit points.
	 * @return int - Current hit points
	 */
	public abstract int getCurrentHP();
	
	/**
	 * The structure looses hit points
	 * @param dmg - amount of damage
	 */
	public abstract void takeDamage(int dmg);
	
	/**
	 * Draws the structure.
	 * @param g2 - Graphics2D object
	 */
	public abstract void draw(Graphics2D g2);
	
	/**
	 * Updates the status of the structure.
	 */
	public abstract void update();
}
