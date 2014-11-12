package Model;

<<<<<<< HEAD
import java.util.Vector;

public class Tile {
=======
import java.util.Observable;

public class Tile extends Observable {
>>>>>>> FETCH_HEAD

	private boolean spawnable;
	private boolean buildable;
	private boolean movable;
<<<<<<< HEAD
	private Vector<Attacker> attackers;
=======
>>>>>>> FETCH_HEAD
	private Structure tileStructure;
	private Dir nextTile;

	public Tile() {

		spawnable = false;
		buildable = false;
		movable = false;
		tileStructure = null;
<<<<<<< HEAD
		attackers = new Vector<Attacker>();
=======
>>>>>>> FETCH_HEAD

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

<<<<<<< HEAD
	public void addAttacker(Attacker a) {
		attackers.add(a);
	}

=======
>>>>>>> FETCH_HEAD
	public boolean build(Structure s) {
		if (buildable && tileStructure == null) {
			tileStructure = s;
			return true;
		} else
			return false;

	}

}
