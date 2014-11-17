package Model;

import java.awt.Point;
import java.util.Vector;

public abstract class Map {

	protected Vector<Vector<Tile>> gameBoard;
	private int height, width;
	protected Vector<Attacker> attackers;
	protected Vector<Structure> structures;

	// TODO: Should we have a master list of spawnable tiles? Would make
	// randomizing wave spawn points easier

	public Map(int h, int w) { // Default constructor sets empty board
		height = h;
		width = w;
			
		gameBoard = new Vector<Vector<Tile>>();
		
		for (int i = 0; i < width; i++) {
			gameBoard.add(new Vector<Tile>());
			for (int k = 0; k < height; k++) {
				gameBoard.get(i).add(new Tile());
			}
		}
		
		structures = new Vector<Structure>();
		attackers = new Vector<Attacker>();
	}

	public abstract void setBase();

	public abstract void setSpawnPoints();

	public abstract void setPath();

	public abstract void setStartingResources();
	
	public abstract void createStructure(StructureType selectedStructure, Point point);

}
