package Model;

import java.util.Observable;

public class Tile extends Observable {

	private boolean spawnable;
	private boolean buildable;
	private boolean movable;
	private Structure tileStructure;
	private Dir nextTile;

	public Tile() {

		spawnable = false;
		buildable = false;
		movable = false;
		tileStructure = null;

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

	public boolean build(Structure s) {
		if (buildable && tileStructure == null) {
			tileStructure = s;
			return true;
		} else
			return false;

	}

}
