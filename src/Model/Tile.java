package Model;

import java.util.Vector;

public class Tile {

	private boolean spawnable;
	private boolean buildable;
	private boolean movable;
	private Vector<Attacker> attackers;
	private Structure tileStructure;
	
	public Tile() {
		
		spawnable = false;
		buildable = false;
		movable = false;
		tileStructure = null;
		attackers = new Vector<Attacker>();
		
	}
	
	
	public boolean getSpawn() {
		return spawnable;
		
	}
	public boolean getBuild() {
		return buildable;
		
	}
	public boolean getMove() {
		return movable;
		
	}
	
	public void setSpawn(boolean b) {
		spawnable = b;
		
	}
	public void setBuild(boolean b) {
		buildable = b;
		
	}
	public void setMove(boolean b) {
		movable = b;
		
	}
	
	public void addAttacker(Attacker a) {
		attackers.add(a);
	}
	public boolean build(Structure s) {
		if(buildable && tileStructure == null) {
			tileStructure = s;
		return true;
		}
		else
			return false;
		
	}
	
	
	
	
}
