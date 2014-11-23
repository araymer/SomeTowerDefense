package Model;

import java.awt.Point;
import java.util.Observable;
import java.util.Vector;

public class Tile extends Observable {

	private boolean spawnable;
	private boolean buildable;
	private boolean movable;
	private Vector<Attacker> attackers;
	private Structure tileStructure;
	private Tile nextTile;
	private Point coordinate;

	public Tile(int x, int y) {

		spawnable = false;
		buildable = true;
		movable = false;
		tileStructure = null;
		attackers = new Vector<Attacker>();
		coordinate = new Point(x, y);

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

	/*
	 * public Dir getDirection() { if (movable) { return nextTile; } return
	 * null; }
	 */

	public Tile getNextTile() {
		return nextTile;
	}

	public void setSpawn(boolean b) {
		spawnable = b;

	}

	public void setBuild(boolean b) {
		buildable = b;

	}

	public void setMove(boolean b, Tile next) {
		movable = b;
		nextTile = next;
	}

	/*
	 * public void setDirection(Dir direction) { nextTile = direction; }
	 */

	public void addAttacker(Attacker a) {
		if (spawnable)
			attackers.add(a);
	}

	public boolean addStructure(Structure s) {
		if (buildable && tileStructure == null) {
			tileStructure = s;
			return true;
		} else {
			System.out.println("Cannot build here");
			return false;
		}

	}
	
	public void removeStructure(){
		tileStructure = null;
	}

	public Vector<Attacker> getAttackers() {
		return attackers;
	}

	public Structure getStructure() {
		return tileStructure;
	}

	public Point getCoordinates() {
		return coordinate;
	}

	public void getOccupied() {
		if (!getAttackers().isEmpty())
			System.out.println("Attacker Spawned");
	}
}
