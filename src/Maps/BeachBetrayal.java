
package Maps;

import java.util.ArrayList;

import Model.Base;
import Model.Map;
import Model.Tile;
import Structures.BaseDesertUprising;

/**
 * The BeachBetrayal game map.
 * 
 * @author Team Something
 *
 */
@SuppressWarnings("serial")
public class BeachBetrayal extends Map {
	private static Map theMap;
	private static int guiHeight = 600;
	private static int guiWidth = 800;

	private static int height = guiHeight / getTileWidth();// 15
	private static int width = guiWidth / getTileWidth();// 20
	private Tile spawnTile1, spawnTile2;
	private static final int BASE_X = 18;
	private static final int BASE_Y = 3;

	private BeachBetrayal() {
		super(height, width);
		mapImageName = "BeachBetrayal.jpg";
		new ArrayList<Tile>();
		setPath();
		setBase();
		setSpawnPoints();
		setTiles();

	}

	public static BeachBetrayal getInstance() {
		if (theMap == null)
			theMap = new BeachBetrayal();
		return (BeachBetrayal) theMap;

	}

	public Map reInit() {
		theMap = new BeachBetrayal();
		return theMap;

	}

	private void setTiles() {
		// Set blocked (non-buildable) tiles
		
		// starting with the ocean to the north
		for(int y = 0; y > 3; y++)
			for(int x = 0; x > 20; x++)
				gameBoard.get(x).get(y).setBuild(false);
		
		// south-east corner tree
		gameBoard.get(0).get(13).setBuild(false);
		gameBoard.get(0).get(14).setBuild(false);
		
		// trees immediately west of SPAWN2
		gameBoard.get(3).get(13).setBuild(false);
		gameBoard.get(4).get(13).setBuild(false);
		for(int c = 3; c > 8; c++)
			gameBoard.get(c).get(14).setBuild(false);
		
		// tree south of the paths near where the diverge and combine
		gameBoard.get(11).get(12).setBuild(false);
		gameBoard.get(11).get(11).setBuild(false);
		gameBoard.get(12).get(12).setBuild(false);
		gameBoard.get(12).get(11).setBuild(false);
		
		// tree south of U-turn heading for final approach to base
		gameBoard.get(15).get(14).setBuild(false);
		gameBoard.get(13).get(14).setBuild(false);
	}

	@Override
	public void setBase() {
		gameBoard.get(BASE_X).get(BASE_Y)
				.addStructure(new BaseDesertUprising(BASE_X, BASE_Y));

	}

	@Override
	public Base getBase() {
		return (Base) gameBoard.get(BASE_X).get(BASE_Y).getStructure();
	}

	@Override
	public void setSpawnPoints() {
		gameBoard.get(0).get(4).setSpawn(true); // east side spawn point
		gameBoard.get(2).get(14).setSpawn(true); // south side spawn point
		spawnTile1 = gameBoard.get(0).get(4);
		spawnTile2 = gameBoard.get(2).get(14);

	}

	// This hard codes our path tiles
	@Override
	public void setPath() {
		// SPAWN 1 PATH
		gameBoard.get(0).get(4).setMove(true, gameBoard.get(1).get(4)); // start west
		gameBoard.get(1).get(4).setBuild(false);
		
		gameBoard.get(1).get(4).setMove(true, gameBoard.get(2).get(4));
		gameBoard.get(2).get(4).setBuild(false);
		
		gameBoard.get(2).get(4).setMove(true, gameBoard.get(3).get(4));
		gameBoard.get(3).get(4).setBuild(false);
		
		gameBoard.get(3).get(4).setMove(true, gameBoard.get(4).get(4));
		gameBoard.get(4).get(4).setBuild(false);
		
		gameBoard.get(4).get(4).setMove(true, gameBoard.get(4).get(5)); // turn south
		gameBoard.get(4).get(5).setBuild(false);
		
		gameBoard.get(4).get(5).setMove(true, gameBoard.get(4).get(6));
		gameBoard.get(4).get(6).setBuild(false);
		
		gameBoard.get(4).get(6).setMove(true, gameBoard.get(4).get(7));
		gameBoard.get(4).get(7).setBuild(false);
		
		gameBoard.get(4).get(7).setMove(true, gameBoard.get(4).get(8));
		gameBoard.get(4).get(8).setBuild(false);
		
		gameBoard.get(4).get(8).setMove(true, gameBoard.get(4).get(9));
		gameBoard.get(4).get(9).setBuild(false);
		
		gameBoard.get(4).get(9).setMove(true, gameBoard.get(5).get(9)); // turn west
		gameBoard.get(5).get(9).setBuild(false);
		
		gameBoard.get(5).get(9).setMove(true, gameBoard.get(5).get(9)); 
		gameBoard.get(6).get(9).setBuild(false);
		
		gameBoard.get(6).get(9).setMove(true, gameBoard.get(7).get(9)); 
		gameBoard.get(7).get(9).setBuild(false);
		
		gameBoard.get(7).get(9).setMove(true, gameBoard.get(8).get(9)); 
		gameBoard.get(8).get(9).setBuild(false);
		
		gameBoard.get(8).get(9).setMove(true, gameBoard.get(9).get(9)); 
		gameBoard.get(9).get(9).setBuild(false);
		
		gameBoard.get(9).get(9).setMove(true, gameBoard.get(10).get(9)); 
		gameBoard.get(10).get(9).setBuild(false);
		
		gameBoard.get(10).get(9).setMove(true, gameBoard.get(11).get(9)); 
		gameBoard.get(11).get(9).setBuild(false);
		
		gameBoard.get(11).get(9).setMove(true, gameBoard.get(12).get(9)); 
		gameBoard.get(12).get(9).setBuild(false);
		
		gameBoard.get(12).get(9).setMove(true, gameBoard.get(13).get(9)); 
		gameBoard.get(13).get(9).setBuild(false);
		
		gameBoard.get(13).get(9).setMove(true, gameBoard.get(13).get(8)); // turn north
		gameBoard.get(13).get(8).setBuild(false);
		
		gameBoard.get(13).get(8).setMove(true, gameBoard.get(13).get(7));
		gameBoard.get(13).get(7).setBuild(false);
		
		gameBoard.get(13).get(7).setMove(true, gameBoard.get(13).get(6));
		gameBoard.get(13).get(8).setBuild(false);
		
		gameBoard.get(13).get(6).setMove(true, gameBoard.get(14).get(6)); // turn west
		gameBoard.get(14).get(6).setBuild(false);
		
		gameBoard.get(14).get(6).setMove(true, gameBoard.get(15).get(6));
		gameBoard.get(15).get(6).setBuild(false);
		
		gameBoard.get(15).get(6).setMove(true, gameBoard.get(15).get(7)); // turn south
		gameBoard.get(15).get(7).setBuild(false);
		
		gameBoard.get(15).get(7).setMove(true, gameBoard.get(15).get(8));
		gameBoard.get(15).get(8).setBuild(false);
		
		gameBoard.get(15).get(8).setMove(true, gameBoard.get(15).get(9));
		gameBoard.get(15).get(9).setBuild(false);
		
		gameBoard.get(15).get(9).setMove(true, gameBoard.get(15).get(10));
		gameBoard.get(15).get(10).setBuild(false); // arrive @ 15,10 intersection
		
		// SPAWN2
		gameBoard.get(2).get(14).setMove(true, gameBoard.get(2).get(13)); // start north
		gameBoard.get(2).get(13).setBuild(false);
		
		gameBoard.get(2).get(13).setMove(true, gameBoard.get(2).get(12));
		gameBoard.get(2).get(12).setBuild(false);
		
		gameBoard.get(2).get(12).setMove(true, gameBoard.get(3).get(12)); // turn west
		gameBoard.get(3).get(12).setBuild(false);
		
		gameBoard.get(3).get(12).setMove(true, gameBoard.get(4).get(12)); 
		gameBoard.get(4).get(12).setBuild(false);
		
		gameBoard.get(4).get(12).setMove(true, gameBoard.get(5).get(12)); 
		gameBoard.get(5).get(12).setBuild(false);
		
		gameBoard.get(5).get(12).setMove(true, gameBoard.get(6).get(12)); 
		gameBoard.get(6).get(12).setBuild(false);
		
		gameBoard.get(6).get(12).setMove(true, gameBoard.get(6).get(11)); // turn north
		gameBoard.get(6).get(11).setBuild(false);
		
		gameBoard.get(6).get(11).setMove(true, gameBoard.get(6).get(10));
		gameBoard.get(6).get(10).setBuild(false);
		
		gameBoard.get(6).get(10).setMove(true, gameBoard.get(7).get(10)); // turn west
		gameBoard.get(7).get(10).setBuild(false);
		
		gameBoard.get(7).get(10).setMove(true, gameBoard.get(8).get(10));
		gameBoard.get(8).get(10).setBuild(false);
		
		gameBoard.get(8).get(10).setMove(true, gameBoard.get(9).get(10));
		gameBoard.get(9).get(10).setBuild(false);
		
		gameBoard.get(9).get(10).setMove(true, gameBoard.get(10).get(10));
		gameBoard.get(10).get(10).setBuild(false);
		
		gameBoard.get(10).get(10).setMove(true, gameBoard.get(11).get(10));
		gameBoard.get(11).get(10).setBuild(false);
		
		gameBoard.get(11).get(10).setMove(true, gameBoard.get(12).get(10));
		gameBoard.get(12).get(10).setBuild(false);
		
		gameBoard.get(12).get(10).setMove(true, gameBoard.get(13).get(10));
		gameBoard.get(13).get(10).setBuild(false);
		
		gameBoard.get(13).get(10).setMove(true, gameBoard.get(14).get(10));
		gameBoard.get(14).get(10).setBuild(false);
		
		gameBoard.get(14).get(10).setMove(true, gameBoard.get(15).get(10));
		gameBoard.get(15).get(10).setBuild(false); // arrive @ intersection 15,10
		
		// INTERSECTION 15,10
		gameBoard.get(15).get(10).setMove(true, gameBoard.get(15).get(11)); // head south from 15,10
		gameBoard.get(15).get(11).setBuild(false); 
		
		gameBoard.get(15).get(11).setMove(true, gameBoard.get(15).get(12));
		gameBoard.get(15).get(12).setBuild(false); 
		
		gameBoard.get(15).get(12).setMove(true, gameBoard.get(15).get(13));
		gameBoard.get(15).get(13).setBuild(false); 
		
		gameBoard.get(15).get(13).setMove(true, gameBoard.get(16).get(13)); // turn west
		gameBoard.get(16).get(13).setBuild(false); 
		
		gameBoard.get(16).get(13).setMove(true, gameBoard.get(17).get(13));
		gameBoard.get(17).get(13).setBuild(false); 
		
		gameBoard.get(17).get(13).setMove(true, gameBoard.get(18).get(13));
		gameBoard.get(18).get(13).setBuild(false); 
		
		gameBoard.get(18).get(13).setMove(true, gameBoard.get(18).get(12)); // turn north
		gameBoard.get(18).get(12).setBuild(false); 
		
		gameBoard.get(18).get(12).setMove(true, gameBoard.get(18).get(11));
		gameBoard.get(18).get(11).setBuild(false); 
		
		gameBoard.get(18).get(11).setMove(true, gameBoard.get(18).get(10));
		gameBoard.get(18).get(10).setBuild(false); 
		
		gameBoard.get(18).get(10).setMove(true, gameBoard.get(18).get(9));
		gameBoard.get(18).get(9).setBuild(false); 
		
		gameBoard.get(18).get(9).setMove(true, gameBoard.get(18).get(8)); // turn north
		gameBoard.get(18).get(8).setBuild(false); 
		
		gameBoard.get(18).get(8).setMove(true, gameBoard.get(17).get(8)); // turn east
		gameBoard.get(17).get(8).setBuild(false); 
		
		gameBoard.get(17).get(8).setMove(true, gameBoard.get(17).get(7)); // turn north
		gameBoard.get(17).get(7).setBuild(false); 
		
		gameBoard.get(17).get(7).setMove(true, gameBoard.get(17).get(6));
		gameBoard.get(17).get(6).setBuild(false); 
		
		gameBoard.get(17).get(6).setMove(true, gameBoard.get(17).get(5)); 
		gameBoard.get(17).get(5).setBuild(false); 
		
		gameBoard.get(17).get(5).setMove(true, gameBoard.get(18).get(5)); // turn west
		gameBoard.get(18).get(5).setBuild(false); 
		
		gameBoard.get(18).get(4).setMove(true, gameBoard.get(18).get(4)); // turn north
		gameBoard.get(18).get(4).setBuild(false); // and arrive at base
	}

	@Override
	public Tile getSpawnTile(int n) {
		if (n == 1)
			return spawnTile1;
		else if (n == 2)
			return spawnTile2;

		return null;
	}

}
