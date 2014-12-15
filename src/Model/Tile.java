package Model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Observable;
import java.util.Vector;

import View.GameGUI;
/**
 * 
 * @author Team Something
 * 
 *
 */
@SuppressWarnings("serial")
public class Tile extends Observable implements Serializable {

	private boolean spawnable;
	private boolean buildable;
	private boolean movable;
	private Vector<Attacker> attackers;
	private Structure tileStructure;
	public Tile nextTile;
	private Point coordinate;
	/**
	 * 
	 * @param x - position x coordinate
	 * @param y - position y coordinate
	 */
	public Tile(int x, int y) {

		spawnable = false;
		buildable = true;
		movable = false;
		tileStructure = null;
		attackers = new Vector<Attacker>();
		coordinate = new Point(x, y);

	}
	/**
	 * 
	 * @return spawnable - boolean value to denote whether a tile can be spawned on.
	 */
	public boolean getSpawn() {
		return spawnable;

	}
	/**
	 * 
	 * @return buildable - boolean value to determine if a tile can be built on.
	 * 
	 */
	public boolean getBuild() {
		return buildable;

	}
	/**
	 * 
	 * @return movable - boolean value to determine if a tile can be walked through
	 */
	public boolean getMove() {
		return movable;

	}

	/**
	 * 
	 * @return nextTile - A reference to the tile that should be moved to from the current tile.
	 */
	public Tile getNextTile() {
		return nextTile;
	}
	/**
	 * 
	 * @param b - boolean to set spawnable value
	 */
	public void setSpawn(boolean b) {
		spawnable = b;

	}
	/**
	 * 
	 * @param b - boolean to set buildable value
	 */
	public void setBuild(boolean b) {
		buildable = b;

	}
	/**
	 * 
	 * @param b - boolean to set movable value
	 * @param next - reference to next Tile object
	 */
	public void setMove(boolean b, Tile next) {
		movable = b;
		nextTile = next;
	}

	/**
	 * 
	 * @param a - Attacker object to be added to the Tile.
	 */

	public void addAttacker(Attacker a) {
		if (spawnable)
			attackers.add(a);
	}
	
	/**
	 * 
	 * @param s - structure to be built on Tile
	 * @return boolean indicating success/failure
	 */
	public boolean addStructure(Structure s) {
		if (buildable && tileStructure == null) {
			if (!Player.getInstance().subtractMoney(s.getPrice())) {
				System.out.println("Insufficient Funds!");
				return false;
			}
			tileStructure = s;
		//	GameGUI.getInstance().saveData();
			return true;
		} else {
			System.out.println("Cannot build here");
			return false;
		}

	}
	/**
	 * Removes any structure currently on Tile
	 */
	public void removeStructure() {
		tileStructure = null;
	}
	/**
	 * 
	 * @return attackers - vector of attackers on Tile
	 */
	public Vector<Attacker> getAttackers() {
		return attackers;
	}
	/**
	 * 
	 * @return tileStructure - Structure on tile
	 */
	public Structure getStructure() {
		return tileStructure;
	}
	/**
	 * 
	 * @return coordinate - returns a Point object with Tile coordinates
	 */
	public Point getCoordinates() {
		return coordinate;
	}
	/**
	 * Deprecated
	 */
	public void getOccupied() {
		if (!getAttackers().isEmpty())
			System.out.println("Attacker Spawned");
	}
}
