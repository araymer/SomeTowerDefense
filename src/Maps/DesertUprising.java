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
import Structures.MarineSentryGun;

public class DesertUprising extends Map {

	private static int tileWidth = 40;
	private static int guiHeight = 600;
	private static int guiWidth =800;
	
	private static int height = guiHeight/tileWidth;
	private static int width = guiWidth/tileWidth;
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
		gameBoard.get(2).get(6).addStructure(new BaseDesertUprising(2, 6));

	}

	@Override
	public void setSpawnPoints() {
		// One spawn point on opposite end of the board
		gameBoard.get(width - 1).get(height - 1).setSpawn(true);
		spawnTile = gameBoard.get(width-1).get(height - 1);

	}

	@Override
	public void setPath() {
		for (int i = 0; i < width; i++) {
			gameBoard.get(i).get(height - 1).setMove(true);
			gameBoard.get(i).get(height - 1).setDirection(Dir.LEFT);
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
		//TODO Calculate the tile that was clicked
		int selectedX = point.x/tileWidth;
		int selectedY = point.y/tileWidth;
		
		Tile selectedTile = gameBoard.get(selectedX).get(selectedY);
				
		switch (selectedStructure) {
		case BASE:	System.out.println("trying to creating base");
					selectedTile.addStructure(new BaseDesertUprising(selectedX, selectedY));
			break;
		case SENTRYGUN:	System.out.println("trying to creating machinegun");
						selectedTile.addStructure(new MarineSentryGun(selectedX, selectedY));
			break;
		//TODO more...
		default:
			break;
			
	
		}
	}

}
