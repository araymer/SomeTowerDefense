/*
 * 
 * A desert marine outpost world. Conditions here are rough and supplies are sparse. The marines
 * want off this gods-forsaken hellhole and have taken up arms in mutiny!
 * 
 */

package Maps;

import Model.Map;
import Structures.BaseDesertUprising;

public class DesertUprising extends Map {

	private static int height = 30;
	private static int width = 40;

	public DesertUprising() {
		super(height, width);
		setPath();
		setBase();
		setSpawnPoints();
		setStartingResources();
		setTiles();
	}

	private void setTiles() {
		// TODO Auto-generated method stub. PLACE ANY OTHER TILE CHANGES HERE

	}

	@Override
	public void setBase() {
		// For now we'll set the base in the left-middle tile (0,15)
		gameBoard.get(0).get(15).build(new BaseDesertUprising());

	}

	@Override
	public void setSpawnPoints() {
		// One spawn point on opposite end of the board
		gameBoard.get(width - 1).get(15).setSpawn(true);

	}

	@Override
	public void setPath() {
		for (int i = 1; i < width; i++) {
			gameBoard.get(i).get(15).setMove(true);
		}

	}

	@Override
	public void setStartingResources() {
		// TODO Auto-generated method stub

	}

}
