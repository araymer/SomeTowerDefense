package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Attackers.Marine;
import Model.Map;
import Model.Ticker;
import Model.Tile;
import View.GameGUI;
import View.MapPanel;
import View.TilePanel;

/**
 * The basic game controller, handles waves, saving, loading and input/output streams.
 * This uses the Singleton pattern.
 * @author Team Something
 *
 */
public class GameController {
	private int waveCount;
	private GameGUI gui;
	private int spawnsPerWave[] = {1,2,4,8,16};
	private static GameController theController;

	/**
	 * The method that makes this executable.
	 * @param args - not used
	 */
	public static void main(String[] args) {

		GameController.getInstance();
	}

	/**
	 * The only instance of this class can be called from anywhere.
	 * @return GameController
	 */
	public static GameController getInstance() {
		if (theController == null)
			theController = new GameController();
		return theController;
	}

	/**
	 * Private constructor so that there is only one instance at a time.
	 */
	private GameController() {
		waveCount = 5;
		gui = GameGUI.getInstance();
	}

	/**
	 * Sends the waves to attack the player.
	 */
	public void startWaves() {

		for (int i = 0; i < waveCount; i++) {
			System.out.println("\n\n\nStarting wave " + (i + 1) + " out of "
					+ waveCount + "\n\n");
			for (int k = 0; k < spawnsPerWave[i]; k++) {
				// start spawning enemies on a delay (probably about 1/2 a
				// second)
				if (k % 2 == 0) {
					gui.tilePanel.tileMap.getSpawnTile(1).addAttacker(
							new Marine(gui.tilePanel.tileMap.getSpawnTile(1)));
				} else {
					gui.tilePanel.tileMap.getSpawnTile(1).addAttacker(
							new Marine(gui.tilePanel.tileMap.getSpawnTile(2)));
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

	// for multiplayer
	FileInputStream inStream;
	ObjectInputStream inObject;

	/**
	 * Loads a previously saved game.
	 * @return boolean - if there is a game saved.
	 */
	@SuppressWarnings("unchecked")
	public boolean loadData() {

		try {
			// load tiles
			inStream = new FileInputStream(new File("map.dat"));
			inObject = new ObjectInputStream(inStream);
			Map loadedMap = (Map) inObject.readObject();
			inObject.close();
			System.out.println("Map info:");
			System.out.println("Money was " + loadedMap.playerMoney.getMoney());
			System.out.println("Map was " + loadedMap.mapImageName);
			gui.createLoadedMap(loadedMap);
			System.out.println("Load successful");
		} catch (Exception e) {
			JFrame cantLoad = new JFrame();
			JLabel loadError = new JLabel("Could not load");
			cantLoad.add(loadError);
			cantLoad.setSize(100, 100);
			cantLoad.setVisible(true);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Saves the game.
	 */
	public void saveData() {
		FileOutputStream outStream;
		ObjectOutputStream outObject;
		try {
			//save map
			outStream = new FileOutputStream(new File("map.dat"));
			outObject = new ObjectOutputStream(outStream);
			outObject.writeObject(TilePanel.getInstance().getMap());
			outObject.close();
			System.out.println("Save successful");
		} catch (Exception e) {
			JFrame cantSave = new JFrame();
			JLabel saveError = new JLabel("Error saving game");
			cantSave.add(saveError);
			cantSave.setSize(100, 100);
			cantSave.setVisible(true);
			e.printStackTrace();
		}
	}
}
