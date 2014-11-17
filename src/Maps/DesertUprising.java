/*
 * 
 * A desert marine outpost world. Conditions here are rough and supplies are sparse. The marines
 * want off this gods-forsaken hellhole and have taken up arms in mutiny!
 * 
 */

package Maps;

import java.awt.Point;

import Model.Dir;
import Model.Map;
import Model.StructureType;
import Model.Tile;
import Structures.BaseDesertUprising;

public class DesertUprising extends Map {

	private static int height = 30;
	private static int width = 40;
	private Tile spawnTile;

	public DesertUprising() {
		super(height, width);
		setPath();
		setBase();
		setSpawnPoints();
		setStartingResources();
		setTiles();
		
	}

	private void setTiles() {
		// PLACE ANY OTHER TILE CHANGES HERE

	}

	@Override
	public void setBase() {
		// For now we'll set the base in the left-middle tile (0,15)
		gameBoard.get(0).get(15).addStructure(new BaseDesertUprising(0, 15));

	}

	@Override
	public void setSpawnPoints() {
		// One spawn point on opposite end of the board
		gameBoard.get(width - 1).get(15).setSpawn(true);
		spawnTile = gameBoard.get(width-1).get(15);

	}

	@Override
	public void setPath() {
		for (int i = 1; i < width; i++) {
			gameBoard.get(i).get(15).setMove(true);
			gameBoard.get(i).get(15).setDirection(Dir.LEFT);
			// in this case very simple because just one direction
		}

	}

	@Override
	public void setStartingResources() {
		// TODO set starting resources for the player

	}
	
	public Tile getSpawnTile() {
		return spawnTile;
	}
	
	/**
	 * Calculates the correct tile to place structure given the mouse position on the GameGUI
	 * 
	 * @param selectedStructure   The type of structure to be created
	 * @param point   The position on the panel where the user clicked
	 */
	
	public void createStructure(StructureType selectedStructure, Point point){
		// TODO Auto-generated method stub
		//Calculate the tile that was clicked
		Tile selectedTile = gameBoard.get(0).get(0);
				
		if(selectedTile.getBuild()){
			//selectedTile.addStructure(s)
		}else{
			System.out.println("Cannot build here");
		}
	}

}
