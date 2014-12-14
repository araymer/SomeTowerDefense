package Controller;

import java.awt.CardLayout;
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
import View.MainMenu;
import View.MapPanel;
import View.TilePanel;

public class GameController {
	private int waveCount;
	private int[] spawnsPerWave = { 1, 2, 4, 6, 10 };
	private GameGUI gui;
	private static GameController theController;

	public static void main(String[] args) {

		GameController.getInstance();
	}

	public static GameController getInstance() {
		if (theController == null)
			theController = new GameController();
		return theController;
	}

	private GameController() {
		waveCount = 5;
		gui = GameGUI.getInstance();
	}

	// TODO This needs to be dependent on ticker, so pausing will also pause the
	// waves
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
			// TODO Wait for all enemies to die
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

	FileInputStream inStream;
	ObjectInputStream inObject;

	@SuppressWarnings("unchecked")
	public boolean loadData() {

		try {
			// load map
//			inStream = new FileInputStream(new File("map.dat"));
//			inObject = new ObjectInputStream(inStream);
//			MapPanel.getInstance().setMap((String) inObject.readObject());
//			inObject.close();
			// load tiles
			inStream = new FileInputStream(new File("map.dat"));
			inObject = new ObjectInputStream(inStream);
			Map loadedMap = (Map) inObject.readObject();
			inObject.close();
//			for (int i = 0; i < TilePanel.getInstance().getMap().getGameBoard()
//					.size(); i++) {
//				for (int p = 0; p < TilePanel.getInstance().getMap()
//						.getGameBoard().get(i).size(); p++) {
//					if (TilePanel.getInstance().getMap().getGameBoard().get(i)
//							.get(p) != null) {
//						TilePanel.getInstance().getMap().getGameBoard().get(i)
//								.get(p).getStructure().setImages();
//					}
//				}
//			}
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

	public void saveData() {
		FileOutputStream outStream;
		ObjectOutputStream outObject;
		try {
			// save map
//			outStream = new FileOutputStream(new File("map.dat"));
//			outObject = new ObjectOutputStream(outStream);
//			outObject.writeObject(MapPanel.getInstance().getFileName());
//			outObject.close();
			// save tiles
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

	JFrame frame;

	public void restartMap() {
		frame = GameGUI.getInstance().frame;
		TilePanel tilePanel = TilePanel.getInstance();

		frame.remove(tilePanel);
		tilePanel.reset();
		tilePanel = TilePanel.getInstance();

		frame.add(tilePanel);

		tilePanel.setSize(frame.getSize().width, frame.getSize().height);
		tilePanel.setLocation(0, 0);
		tilePanel.setLayout(new CardLayout());

		GameGUI.getInstance().newTilePanel();
		Ticker.getInstance().loopStart();

	}

	public void returnToMain() {
		frame = GameGUI.getInstance().frame;
		frame.setContentPane(MainMenu.getInstance());
		GameGUI.getInstance().resetMenuBar();
	}
}
