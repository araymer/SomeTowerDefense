package Maps;

import java.util.ArrayList;

import Model.Base;
import Model.Map;
import Model.Tile;
import Structures.*;

/**
 * 
 * @author Team Something
 *
 */
@SuppressWarnings("serial")
public class BrokenPlainsPatrol extends Map {
	private static int guiHeight = 600;
	private static int guiWidth = 800;

	private static int height = guiHeight / getTileWidth();// 15
	private static int width = guiWidth / getTileWidth();// 20
	private Tile spawnTile1, spawnTile2, spawnTile3;
	private static final int BASE_X = 16;
	private static final int BASE_Y = 8;
	private static BrokenPlainsPatrol brokenPlains;

	private BrokenPlainsPatrol() {
		super(height, width);
		mapImageName = "BrokenPlainsPatrol.jpg";
		new ArrayList<Tile>();
		setPath();
		setBase();
		setSpawnPoints();
		setTiles();
	}

	public static BrokenPlainsPatrol getInstance() {
		if (brokenPlains == null)
			brokenPlains = new BrokenPlainsPatrol();
		return brokenPlains;
	}

	private void setTiles() {
		// top tree
		gameBoard.get(13).get(4).setBuild(false);
		gameBoard.get(13).get(3).setBuild(false);
		gameBoard.get(13).get(2).setBuild(false);
		gameBoard.get(12).get(4).setBuild(false);
		gameBoard.get(12).get(3).setBuild(false);
		gameBoard.get(12).get(2).setBuild(false);
		gameBoard.get(11).get(4).setBuild(false);
		gameBoard.get(11).get(3).setBuild(false);
		gameBoard.get(11).get(2).setBuild(false);

		// Upper chasm
		gameBoard.get(8).get(0).setBuild(false);
		gameBoard.get(8).get(1).setBuild(false);
		gameBoard.get(9).get(0).setBuild(false);
		gameBoard.get(9).get(1).setBuild(false);
		gameBoard.get(10).get(0).setBuild(false);
		gameBoard.get(10).get(1).setBuild(false);
		gameBoard.get(11).get(0).setBuild(false);
		gameBoard.get(11).get(1).setBuild(false);
		gameBoard.get(12).get(0).setBuild(false);
		gameBoard.get(12).get(1).setBuild(false);
		gameBoard.get(13).get(0).setBuild(false);
		gameBoard.get(13).get(1).setBuild(false);
		gameBoard.get(14).get(3).setBuild(false);
		gameBoard.get(14).get(2).setBuild(false);
		gameBoard.get(14).get(1).setBuild(false);
		gameBoard.get(14).get(0).setBuild(false);
		gameBoard.get(15).get(3).setBuild(false);
		gameBoard.get(15).get(2).setBuild(false);
		gameBoard.get(15).get(1).setBuild(false);
		gameBoard.get(15).get(0).setBuild(false);
		gameBoard.get(16).get(3).setBuild(false);
		gameBoard.get(16).get(2).setBuild(false);
		gameBoard.get(16).get(1).setBuild(false);
		gameBoard.get(16).get(0).setBuild(false);
		gameBoard.get(17).get(3).setBuild(false);
		gameBoard.get(17).get(2).setBuild(false);
		gameBoard.get(17).get(1).setBuild(false);
		gameBoard.get(17).get(0).setBuild(false);
		gameBoard.get(18).get(3).setBuild(false);
		gameBoard.get(18).get(2).setBuild(false);
		gameBoard.get(18).get(1).setBuild(false);
		gameBoard.get(18).get(0).setBuild(false);
		gameBoard.get(19).get(4).setBuild(false);
		gameBoard.get(19).get(3).setBuild(false);
		gameBoard.get(19).get(2).setBuild(false);
		gameBoard.get(19).get(1).setBuild(false);
		gameBoard.get(19).get(0).setBuild(false);

		// left tree
		gameBoard.get(4).get(8).setBuild(false);
		gameBoard.get(4).get(7).setBuild(false);
		gameBoard.get(4).get(6).setBuild(false);
		gameBoard.get(3).get(8).setBuild(false);
		gameBoard.get(3).get(7).setBuild(false);
		gameBoard.get(3).get(6).setBuild(false);
		gameBoard.get(2).get(8).setBuild(false);
		gameBoard.get(2).get(7).setBuild(false);
		gameBoard.get(2).get(6).setBuild(false);

		// right tree
		gameBoard.get(15).get(14).setBuild(false);
		gameBoard.get(15).get(13).setBuild(false);
		gameBoard.get(15).get(12).setBuild(false);
		gameBoard.get(14).get(14).setBuild(false);
		gameBoard.get(14).get(13).setBuild(false);
		gameBoard.get(14).get(12).setBuild(false);
		gameBoard.get(13).get(14).setBuild(false);
		gameBoard.get(13).get(13).setBuild(false);
		gameBoard.get(13).get(12).setBuild(false);

		// lower-left chasm
		gameBoard.get(3).get(10).setBuild(false);
		gameBoard.get(3).get(11).setBuild(false);
		gameBoard.get(3).get(12).setBuild(false);
		gameBoard.get(3).get(13).setBuild(false);
		gameBoard.get(2).get(10).setBuild(false);
		gameBoard.get(2).get(11).setBuild(false);
		gameBoard.get(2).get(12).setBuild(false);
		gameBoard.get(2).get(13).setBuild(false);
		gameBoard.get(1).get(10).setBuild(false);
		gameBoard.get(1).get(11).setBuild(false);
		gameBoard.get(1).get(12).setBuild(false);
		gameBoard.get(1).get(13).setBuild(false);
		gameBoard.get(1).get(14).setBuild(false);
		gameBoard.get(0).get(10).setBuild(false);
		gameBoard.get(0).get(11).setBuild(false);
		gameBoard.get(0).get(12).setBuild(false);
		gameBoard.get(0).get(13).setBuild(false);
		gameBoard.get(0).get(14).setBuild(false);
	}

	/**
	 * Inserts the base onto the map.
	 */
	@Override
	public void setBase() {
		gameBoard.get(BASE_X).get(BASE_Y)
				.addStructure(new BaseBrokenPlainsPatrol(BASE_X, BASE_Y));
	}

	/**
	 * Returns location of the base.
	 * 
	 * @return Base - the base for this map.
	 */
	@Override
	public Base getBase() {
		return (Base) gameBoard.get(BASE_X).get(BASE_Y).getStructure();
	}

	/**
	 * Supplies the spawn points.
	 */
	@Override
	public void setSpawnPoints() {
		gameBoard.get(19).get(10).setSpawn(true);
		gameBoard.get(0).get(1).setSpawn(true);
		gameBoard.get(9).get(14).setSpawn(true);
		spawnTile1 = gameBoard.get(19).get(10);
		spawnTile2 = gameBoard.get(0).get(1);
		spawnTile3 = gameBoard.get(9).get(14);

	}

	/**
	 * 
	 */
	@Override
	public void setPath() {
		// Spawn1 path to final approach intersection @ 14,10
		gameBoard.get(19).get(10).setMove(true, gameBoard.get(18).get(10)); // heading
																			// west
		gameBoard.get(19).get(10).setBuild(false);

		gameBoard.get(18).get(10).setMove(true, gameBoard.get(17).get(10));
		gameBoard.get(18).get(10).setBuild(false);

		gameBoard.get(17).get(10).setMove(true, gameBoard.get(16).get(10));
		gameBoard.get(17).get(10).setBuild(false);

		gameBoard.get(16).get(10).setMove(true, gameBoard.get(15).get(10));
		gameBoard.get(16).get(10).setBuild(false);

		// TODO: PATH BREAKS HERE FOR SOME REASON
		gameBoard.get(15).get(10).setMove(true, gameBoard.get(14).get(10));
		gameBoard.get(15).get(10).setBuild(false);

		// Spawn2 path to spawn2/3 intersection @ 9,6
		gameBoard.get(0).get(1).setMove(true, gameBoard.get(1).get(1)); // heading
																		// east
		gameBoard.get(1).get(1).setBuild(false);

		gameBoard.get(1).get(1).setMove(true, gameBoard.get(2).get(1));
		gameBoard.get(1).get(1).setBuild(false);

		gameBoard.get(2).get(1).setMove(true, gameBoard.get(3).get(1));
		gameBoard.get(2).get(1).setBuild(false);

		gameBoard.get(3).get(1).setMove(true, gameBoard.get(4).get(1));
		gameBoard.get(3).get(1).setBuild(false);

		gameBoard.get(4).get(1).setMove(true, gameBoard.get(4).get(2)); // turn
																		// south
		gameBoard.get(4).get(1).setBuild(false);

		gameBoard.get(4).get(2).setMove(true, gameBoard.get(4).get(3));
		gameBoard.get(4).get(2).setBuild(false);

		gameBoard.get(4).get(3).setMove(true, gameBoard.get(5).get(3)); // turn
																		// west
		gameBoard.get(4).get(3).setBuild(false);

		gameBoard.get(5).get(3).setMove(true, gameBoard.get(6).get(3));
		gameBoard.get(5).get(3).setBuild(false);

		gameBoard.get(6).get(3).setMove(true, gameBoard.get(7).get(3));
		gameBoard.get(6).get(3).setBuild(false);

		gameBoard.get(7).get(3).setMove(true, gameBoard.get(7).get(4)); // turn
																		// south
		gameBoard.get(7).get(3).setBuild(false);

		gameBoard.get(7).get(4).setMove(true, gameBoard.get(7).get(5));
		gameBoard.get(7).get(4).setBuild(false);

		gameBoard.get(7).get(5).setMove(true, gameBoard.get(7).get(6));
		gameBoard.get(7).get(5).setBuild(false);

		gameBoard.get(7).get(6).setMove(true, gameBoard.get(8).get(6)); // turn
																		// west
		gameBoard.get(7).get(6).setBuild(false);

		gameBoard.get(8).get(6).setMove(true, gameBoard.get(9).get(6));
		gameBoard.get(8).get(6).setBuild(false);

		// spawn3 to path spawn2/3 intersection @ 9,6
		gameBoard.get(8).get(14).setMove(true, gameBoard.get(8).get(13)); // head
																			// north
		gameBoard.get(8).get(14).setBuild(false);

		gameBoard.get(8).get(13).setMove(true, gameBoard.get(8).get(12));
		gameBoard.get(8).get(13).setBuild(false);

		gameBoard.get(8).get(12).setMove(true, gameBoard.get(8).get(11));
		gameBoard.get(8).get(12).setBuild(false);

		gameBoard.get(8).get(11).setMove(true, gameBoard.get(8).get(10));
		gameBoard.get(8).get(11).setBuild(false);

		gameBoard.get(8).get(10).setMove(true, gameBoard.get(9).get(10)); // turn
																			// west
		gameBoard.get(8).get(10).setBuild(false);

		gameBoard.get(9).get(10).setMove(true, gameBoard.get(10).get(10));
		gameBoard.get(9).get(10).setBuild(false);

		gameBoard.get(10).get(10).setMove(true, gameBoard.get(10).get(9)); // turn
																			// north
		gameBoard.get(10).get(10).setBuild(false);

		gameBoard.get(10).get(9).setMove(true, gameBoard.get(10).get(8));
		gameBoard.get(10).get(9).setBuild(false);

		gameBoard.get(10).get(8).setMove(true, gameBoard.get(9).get(7)); // turn
																			// east
		gameBoard.get(10).get(8).setBuild(false);

		gameBoard.get(9).get(7).setMove(true, gameBoard.get(9).get(6));
		gameBoard.get(9).get(7).setBuild(false);

		// gap between 9,6 and 14,10
		gameBoard.get(9).get(6).setMove(true, gameBoard.get(10).get(6)); // combine
																			// forces
																			// and
																			// move
																			// east
		gameBoard.get(9).get(6).setBuild(false);

		gameBoard.get(10).get(6).setMove(true, gameBoard.get(11).get(6));
		gameBoard.get(10).get(6).setBuild(false);

		gameBoard.get(11).get(6).setMove(true, gameBoard.get(12).get(6));
		gameBoard.get(11).get(6).setBuild(false);

		gameBoard.get(12).get(6).setMove(true, gameBoard.get(12).get(7)); // turn
																			// south
		gameBoard.get(12).get(6).setBuild(false);

		gameBoard.get(12).get(7).setMove(true, gameBoard.get(12).get(8));
		gameBoard.get(12).get(7).setBuild(false);

		gameBoard.get(12).get(8).setMove(true, gameBoard.get(12).get(9));
		gameBoard.get(12).get(8).setBuild(false);

		gameBoard.get(12).get(9).setMove(true, gameBoard.get(12).get(10));
		gameBoard.get(12).get(9).setBuild(false);

		gameBoard.get(12).get(10).setMove(true, gameBoard.get(13).get(10)); // turn
																			// east
		gameBoard.get(12).get(10).setBuild(false);

		gameBoard.get(12).get(10).setMove(true, gameBoard.get(13).get(10));
		gameBoard.get(12).get(10).setBuild(false);

		gameBoard.get(13).get(10).setMove(true, gameBoard.get(14).get(10));
		gameBoard.get(13).get(10).setBuild(false);

		// final approach
		gameBoard.get(14).get(10).setMove(true, gameBoard.get(14).get(9));
		gameBoard.get(14).get(10).setBuild(false);

		gameBoard.get(14).get(9).setMove(true, gameBoard.get(14).get(8));
		gameBoard.get(14).get(9).setBuild(false);

		gameBoard.get(14).get(8).setMove(true, gameBoard.get(14).get(7));
		gameBoard.get(14).get(8).setBuild(false);

		gameBoard.get(14).get(7).setMove(true, gameBoard.get(14).get(6));
		gameBoard.get(14).get(7).setBuild(false);

		gameBoard.get(14).get(6).setMove(true, gameBoard.get(14).get(5));
		gameBoard.get(14).get(6).setBuild(false);

		gameBoard.get(14).get(5).setMove(true, gameBoard.get(15).get(5)); // turn
																			// east
		gameBoard.get(14).get(5).setBuild(false);

		gameBoard.get(15).get(5).setMove(true, gameBoard.get(16).get(5));
		gameBoard.get(15).get(5).setBuild(false);

		gameBoard.get(16).get(5).setMove(true, gameBoard.get(16).get(6)); // turn
																			// south
		gameBoard.get(16).get(5).setBuild(false);

		gameBoard.get(16).get(6).setMove(true, gameBoard.get(16).get(7)); // turn
																			// south
		gameBoard.get(16).get(6).setBuild(false);

		// last tile to base
		gameBoard.get(16).get(7).setMove(true, null);
		gameBoard.get(16).get(7).setBuild(false);
	}

	@Override
	public Tile getSpawnTile(int n) {
		switch (n) {
		case 1:
			return spawnTile1;
		case 2:
			return spawnTile2;
		case 3:
			return spawnTile3;
		default:
			System.out.println("Error in getSpawnTile method");
			return null;
		}

	}
	
	@Override
	public int getBaseX() {
		return BASE_X;
	}

	@Override
	public int getBaseY() {
		return BASE_Y;

	}
	@Override
	public Map reInit() {
		brokenPlains = new BrokenPlainsPatrol();
		return brokenPlains;
	}
}
