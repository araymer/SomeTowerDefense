/*
 * @author Team Something
 * Map hierarchy
 */

package Model;

import java.util.Vector;

public abstract class Map {
	
	protected Vector<Vector<Tile>> gameBoard;
	private int height, width;
	protected Vector<Attacker> currentAttackers;
	
	public Map(int h, int w) { //Default constructor sets empty board
		height = h;
		width = w;
		
		for(int i = 0; i<width; i++) {
			gameBoard.add(new Vector<Tile>());
			for(int k = 0; k<height; k++) {
				gameBoard.get(i).add(new Tile());
			}
		}
	}
	
	public abstract void setBase();
	public abstract void setSpawnPoints();
	public abstract void setPath();
	public abstract void setStartingResources();
	
	
	
	
	
	
	

}
