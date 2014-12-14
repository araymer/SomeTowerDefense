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

public abstract class Map implements Serializable {

	private static int tileWidth = 40;
	protected Vector<Vector<Tile>> gameBoard;
	private int height, width;
	public Player playerMoney = Player.getInstance();
	public String mapImageName;

	// randomizing wave spawn points easier

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
	}

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
					selectedTile.removeStructure();
					selectedTile.addStructure(new MarineSentryGunMkII(
							selectedX, selectedY));
					break;
				case HELLFIRECANNON:
					selectedTile.removeStructure();
					selectedTile.addStructure(new HellfireCannon(selectedX,
							selectedY));
					break;
				case STASISTOWER:
					selectedTile.removeStructure();
					selectedTile.addStructure(new StasisTower(selectedX,
							selectedY));
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

	public abstract void setBase();

	public abstract Base getBase();

	public abstract void setSpawnPoints();

	public abstract void setPath();

	public Vector<Vector<Tile>> getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Vector<Vector<Tile>> board) {
		gameBoard = board;
	}

	public abstract Tile getSpawnTile(int n);

	public static int getTileWidth() {
		return tileWidth;
	}

	public static void setTileWidth(int tileWidth) {
		Map.tileWidth = tileWidth;
	}
}
