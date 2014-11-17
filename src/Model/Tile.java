package Model;


import java.util.Vector;
import java.util.Observable;


public class Tile extends Observable {


	private boolean spawnable;
	private boolean buildable;
	private boolean movable;
	private Vector<Attacker> attackers;
	private Structure tileStructure;
	private Dir nextTile;

	public Tile() {

		spawnable = false;
		buildable = true;
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

	public Dir getDirection() {
		if (movable) {
			return nextTile;
		}
		return null;
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

	public void setDirection(Dir direction) {
		nextTile = direction;
	}

	public void addAttacker(Attacker a) {
		if(spawnable)
		attackers.add(a);
	}

	public boolean addStructure(Structure s) {
		if (buildable && tileStructure == null) {
			tileStructure = s;
			return true;
		} else{
			System.out.println("Cannot build here");
			return false;
		}	

	}
	
	public Vector<Attacker> getAttackers(){
		return attackers;
	}
	
	public Structure getStructure(){
		return tileStructure;
	}

}
