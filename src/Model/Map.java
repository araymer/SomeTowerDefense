package Model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Vector;

import Structures.BaseDesertUprising;
import Structures.ChronoTower;
import Structures.HellfireCannon;
import Structures.MarineSentryGun;
import Structures.MarineSentryGunMkII;
import Structures.PlasmaCannon;
import Structures.StasisTower;

/**
 * Keeps track of the gameBoard and waves
 * 
 * @author TeamSomething
 *
 */
public abstract class Map implements Serializable {

	private static int tileWidth = 40;
	protected Vector<Vector<Tile>> gameBoard;
	private int height, width;
	public Player playerMoney = Player.getInstance();
	public String mapImageName;
	public int waveNumber;

	// randomizing wave spawn points easier
	/**
	 * Creates an instance of map
	 * 
	 * @author TeamSomething
	 * @param h
	 *            = height of map
	 * @param w
	 *            = width of map
	 */
	public Map(int h, int w) { // Default constructor sets empty board
		height = h;
		width = w;

		gameBoard = new Vector<Vector<Tile>>();

		for (int i = 0; i < width; i++) {
			gameBoard.add(new Vector<Tile>());
			for (int k = 0; k < height; k++) {
				gameBoard.get(i).add(new Tile(i, k));
			}
		}
		playerMoney.setMoney(300);
		waveNumber = 0;

	}

	/**
	 * Sets the wave number
	 * 
	 * @author TeamSomething
	 * @param wave
	 *            = wave number
	 */
	public void setWave(int wave) {
		waveNumber = wave;
	}

	/**
	 * Resets the map
	 * 
	 * @author TeamSomething
	 * @return Map = the reset map instance
	 */
	public abstract Map reInit();

	/**
	 * Calculates the correct tile to place structure given the mouse position
	 * on the GameGUI
	 * 
	 * @param selectedStructure
	 *            The type of structure to be created
	 * @param point
	 *            The position on the panel where the user clicked
	 */
	public void createStructure(StructureType selectedStructure, Point point) {
		// Calculate the tile that was clicked
		int selectedX = point.x / getTileWidth();
		int selectedY = point.y / getTileWidth();

		Tile selectedTile = gameBoard.get(selectedX).get(selectedY);

		switch (selectedStructure) {
		case BASE:
			System.out.println("trying to create base");
			selectedTile.addStructure(new BaseDesertUprising(selectedX,
					selectedY));
			break;
		case SENTRYGUN:
			System.out.println("trying to create machinegun");
			selectedTile
					.addStructure(new MarineSentryGun(selectedX, selectedY));
			break;
		case PLASMACANNON:
			System.out.println("trying to create plasmacannon");
			selectedTile.addStructure(new PlasmaCannon(selectedX, selectedY));
			break;
		case CHRONOTOWER:
			System.out.println("trying to create chronotower");
			selectedTile.addStructure(new ChronoTower(selectedX, selectedY));
			break;
		case STASISTOWER:
			System.out.println("trying to create stasistower");
			selectedTile.addStructure(new StasisTower(selectedX, selectedY));
			break;
		case UPGRADE:
			System.out.println("trying to upgrade");
			try {
				StructureType upgrade = selectedTile.getStructure()
						.getUpgradeTo();

				if (upgrade == null)
					upgrade = StructureType.NONE;
				switch (upgrade) {
				case SENTRYGUN2:
					MarineSentryGunMkII m = new MarineSentryGunMkII(selectedX,
							selectedY);
					if (Player.getInstance().getMoney() >= m.getPrice()) {
						selectedTile.removeStructure();
						selectedTile.addStructure(m);
					} else {
						System.out.println("Map: Insufficient funds");
					}

					break;
				case HELLFIRECANNON:
					HellfireCannon h = new HellfireCannon(selectedX, selectedY);
					if (Player.getInstance().getMoney() >= h.getPrice()) {
						selectedTile.removeStructure();
						selectedTile.addStructure(h);
					} else {
						System.out.println("Map: Insufficient funds");
					}
					break;
				case STASISTOWER:
					selectedTile.removeStructure();
					StasisTower s = new StasisTower(selectedX, selectedY);
					if (Player.getInstance().getMoney() >= s.getPrice()) {
						selectedTile.removeStructure();
						selectedTile.addStructure(s);
					} else {
						System.out.println("Map: Insufficient funds");
					}
					break;
				default:
					System.out.println("No upgrades available");

				}
			} catch (Exception e) {

			}

			break;
		default:
			System.out.println("Error in createStructure method");

		}
	}

	/**
	 * Places the base in the map
	 * 
	 * @author TeamSomething
	 */
	public abstract void setBase();

	/**
	 * Returns the Base
	 * 
	 * @author TeamSomething
	 * @return Base = our base
	 */
	public abstract Base getBase();

	/**
	 * Gets the X coordinate of the base
	 *
	 * @author TeamSomething
	 * @return int = base's X coordinate
	 */
	public abstract int getBaseX();

	/**
	 * Gets the Y coordinate of the base
	 *
	 * @author TeamSomething
	 * @return int = base's Y coordinate
	 */
	public abstract int getBaseY();

	/**
	 * Sets the spawn points of the map
	 *
	 * @author TeamSomething
	 */
	public abstract void setSpawnPoints();

	/**
	 * Sets the path for the enemies to travel
	 *
	 * @author TeamSomething
	 */
	public abstract void setPath();

	/**
	 * Gets the game board
	 *
	 * @author TeamSomething
	 * @return Vector<Vector<Tile>> = The Game board
	 */
	public Vector<Vector<Tile>> getGameBoard() {
		return gameBoard;
	}

	/**
	 * Sets the game board
	 *
	 * @author TeamSomething
	 * @param board
	 *            = The Game board
	 */
	public void setGameBoard(Vector<Vector<Tile>> board) {
		gameBoard = board;
	}

	/**
	 * Gets the spawn tile in accordance to the number given
	 *
	 * @author TeamSomething
	 * @return Tile = the spawn tile
	 */
	public abstract Tile getSpawnTile(int n);

	/**
	 * Gets the width of the tiles
	 *
	 * @author TeamSomething
	 * @return int = tile width
	 */
	public static int getTileWidth() {
		return tileWidth;
	}

	/**
	 * Sets the width of the tiles
	 *
	 * @author TeamSomething
	 * @param tileWidth
	 *            = tile width
	 */
	public static void setTileWidth(int tileWidth) {
		Map.tileWidth = tileWidth;
	}
}
