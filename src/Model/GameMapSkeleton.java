package Model;

import java.io.Serializable;
import java.util.Vector;

/**
 * Class made to be able to easily send gameMap information over the network to
 * the minimap
 * 
 * @author Team Something
 *
 */
public class GameMapSkeleton implements Serializable {
	/**
	 * Gets the map skeleton
	 *
	 * @author TeamSomething
	 * @return Vector<Vector<TileSkeleton>> = The map skeleton
	 */
	public Vector<Vector<TileSkeleton>> skeleton;

	/**
	 * Creates the map skeleton using the game map
	 *
	 * @author TeamSomething
	 * @param gameMap
	 *            = Our current game map
	 */
	public GameMapSkeleton(Vector<Vector<Tile>> gameMap) {

		int width = gameMap.size();
		int height = gameMap.get(0).size();
		skeleton = new Vector<Vector<TileSkeleton>>();

		for (int c = 0; c < width; c++) {
			skeleton.add(new Vector<TileSkeleton>());
			for (int r = 0; r < height; r++) {
				Tile curr = gameMap.get(c).get(r);
				skeleton.get(c).add(new TileSkeleton(curr));
			}
		}
	}

}
