package TowerFSM;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Model.Attacker;
import Model.SpecialAttack;
import Model.Structure;

/**
 * This interface is for all tower state classes.
 * @author Team Something
 *
 */
public abstract class TowerState{
	boolean upgrade;
	boolean signal;
	Attacker attacker;
	
	Structure tower;
	
	public TowerState(Structure struct){
		
		
		this.tower = struct;
	}

	public abstract int getCurrentHP();
	
	public abstract void takeDamage(int dmg);
	
	public abstract void draw(Graphics2D g2);
	
	public abstract void update();
}
