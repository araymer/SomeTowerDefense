package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.TDClient;
import Model.Attacker;
import Model.Structure;
import Model.Ticker;

/**
 * The class that organizes all the GUI elements for the tower defense game
 * 
 * @author Team Something
 *
 */
@SuppressWarnings("serial")
public class GameGUI implements Serializable {

	private final int FRAME_WIDTH = 800;
	// Extra 22 for bar
	private final int FRAME_HEIGHT = 622;
	Container contentPane;
	JFrame frame;
	public TilePanel tilePanel;
	public MapPanel mapPanel;
	ResourcePanel resourcePanel;
	public MultiplayerFrame multiFrame;
	private static GameGUI thisGUI;
	private TDClient client;
	public boolean isMultiplayer = false;
	public int mapSelection;
	Structure structure;

	/**
	 * Constructs the Tower Defense GUI
	 */
	private GameGUI() {
		createFrame();
		createMenuBar();
		frame.setContentPane(MainMenu.getInstance());
		frame.setVisible(true);

	}

	void createMap(int selection) {
		mapPanel = MapPanel.getInstance();

		switch (selection) {
		case 0:
			mapPanel.setMap("desertuprising.jpg");

			break;
		}

		mapPanel.setSize(frame.getSize().width, frame.getSize().height);
		mapPanel.setLocation(0, 0);
		frame.setContentPane(mapPanel);
		frame.setJMenuBar(menuBar);

		contentPane = frame.getContentPane();
		contentPane.setLayout(new CardLayout());

		tilePanel = TilePanel.getInstance();
		MouseListener placementListener = new PlacementListener();
		tilePanel.addMouseListener(placementListener);
		tilePanel.setSize(frame.getSize().width, frame.getSize().height);
		tilePanel.setLocation(0, 0);
		tilePanel.setLayout(new CardLayout());

		resourcePanel = ResourcePanel.getInstance();

		contentPane.add(tilePanel);
		// frame.setJMenuBar(menuBar);
		// frame.repaint();

		new Thread(Ticker.getInstance()).start();
		// GameController.getInstance().startWaves();

		if (isMultiplayer) {
			client.setStartingServerHP();
			multiFrame = new MultiplayerFrame();
		}
	}

	void loadMap(TilePanel tiles, MapPanel map) {
		mapPanel = map;

		mapPanel.setSize(frame.getSize().width, frame.getSize().height);
		mapPanel.setLocation(0, 0);
		frame.setContentPane(mapPanel);
		frame.setJMenuBar(menuBar);

		contentPane = frame.getContentPane();
		contentPane.setLayout(new CardLayout());

		tilePanel = tiles;
		MouseListener placementListener = new PlacementListener();
		tilePanel.addMouseListener(placementListener);
		tilePanel.setSize(frame.getSize().width, frame.getSize().height);
		tilePanel.setLocation(0, 0);
		tilePanel.setLayout(new CardLayout());

		resourcePanel = ResourcePanel.getInstance();

		contentPane.add(tilePanel);

		// frame.repaint();

		new Thread(Ticker.getInstance()).start();
		// GameController.getInstance().startWaves();

		if (isMultiplayer) {
			client.setStartingServerHP();
			multiFrame = new MultiplayerFrame();
		}
	}

	/**
	 * Creates and sets the JFrame
	 */
	private void createFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		// Color is for debug purposes
		frame.setBackground(Color.GREEN);
		frame.getContentPane().setBackground(Color.MAGENTA);
		frame.setTitle("Some Tower Defense");
		createMenuBar();
		frame.setJMenuBar(menuBar);

	}

	JMenuBar menuBar;
	JMenu game;
	JMenuItem load;
	JMenuItem save;
	JCheckBox pause;
	JMenuItem exit;
	JMenu help;
	JMenuItem options;
	JMenuItem instructions;

	void createMenuBar() {
		menuBar = new JMenuBar();
		game = new JMenu("Game");
		save = new JMenuItem("Save");
		save.addActionListener(new MenuListener());
		save.setActionCommand("save");
		game.add(save);
		load = new JMenuItem("Load");
		load.addActionListener(new MenuListener());
		load.setActionCommand("load");
		game.add(load);
		pause = new JCheckBox("Pause");
		pause.addActionListener(new MenuListener());
		pause.setActionCommand("pause");
		game.add(pause);
		exit = new JMenuItem("Exit");
		exit.addActionListener(new MenuListener());
		exit.setActionCommand("exit");
		game.add(exit);
		help = new JMenu("Help");
		options = new JMenuItem("Options");
		options.addActionListener(new MenuListener());
		options.setActionCommand("options");
		help.add(options);
		instructions = new JMenuItem("How To Play");
		instructions.addActionListener(new MenuListener());
		instructions.setActionCommand("instructions");
		help.add(instructions);
		// menuBar.add(file);
		menuBar.add(game);
		menuBar.add(help);
		menuBar.setVisible(true);
	}

	public void repaint() {
		// menuBar.repaint();
		tilePanel.repaint();
		// frame.setJMenuBar(menuBar);
		// resourcePanel.repaint();
	}

	public static GameGUI getInstance() {
		if (thisGUI == null)
			thisGUI = new GameGUI();

		return thisGUI;
	}

	public void returnMenu() {
		frame.setContentPane(MainMenu.getInstance());
		frame.setJMenuBar(menuBar);
	}

	public void setClient(TDClient cli) {
		client = cli;
		isMultiplayer = true;
		// add a listener that sends a disconnect command to when closing
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				client.closeClient();
			}
		});
	}

	public TDClient getClient() {
		return client;
	}

	public void baseTakeDamage(int damageAmount) {
		client.baseTakeDamage(damageAmount);
	}

	public void startMultiplayerGame() {
		createMap(mapSelection);
		MainMenu.getInstance().resetLogo();
	}

	private class PlacementListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			// Make a new structure on click
			if (e.getPoint().getX() <= 800 && e.getPoint().getY() <= 600) {
				System.out.println("Creating new structure at " + e.getPoint());

				tilePanel.getMap().createStructure(
						resourcePanel.getSelectedStructure(), e.getPoint());

			}

			// Aaaaaand allows to get attacker info
			Vector<Attacker> attackers = tilePanel.getMap().getGameBoard()
					.get((int) Math.round(e.getPoint().getX()) / 40)
					.get((int) Math.round(e.getPoint().getY()) / 40)
					.getAttackers();
			if (attackers.size() != 0) {
				Ticker.getInstance().loopStop();
				JFrame enemyInfoFrame = new JFrame();
				enemyInfoFrame.setResizable(false);
				enemyInfoFrame.addWindowListener(new ExitListener());
				enemyInfoFrame.setSize(100, 200);
				JPanel enemyInfoPanel = new JPanel();
				enemyInfoPanel.setLayout(new GridLayout(4, 1));
				enemyInfoPanel.add(new JLabel("Name: "
						+ attackers.get(0).getName()));
				enemyInfoPanel
						.add(new JLabel("HP: " + attackers.get(0).getHP()));
				enemyInfoPanel.add(new JLabel("Attack: "
						+ attackers.get(0).getAttack()));
				enemyInfoPanel.setVisible(true);
				enemyInfoFrame.setContentPane(enemyInfoPanel);
				enemyInfoFrame.setVisible(true);
			}

			// Aaaaaaand Structure info :P

			// JButton upgrade = new JButton("Upgrade");
			// upgrade.addActionListener(new ButtonListener());
			if (tilePanel.getMap().getGameBoard()
					.get((int) Math.round(e.getPoint().getX()) / 40)
					.get((int) Math.round(e.getPoint().getY()) / 40)
					.getStructure() != null) {
				structure = tilePanel.getMap().getGameBoard()
						.get((int) Math.round(e.getPoint().getX()) / 40)
						.get((int) Math.round(e.getPoint().getY()) / 40)
						.getStructure();

				// Ticker.getInstance().loopStop();
				JFrame structureInfoFrame = new JFrame();
				structureInfoFrame.setResizable(false);
				structureInfoFrame.addWindowListener(new ExitListener());
				structureInfoFrame.setSize(100, 200);
				structureInfoFrame.setLocation(811, 300);
				JPanel structureInfoPanel = new JPanel();
				structureInfoPanel.setLayout(new GridLayout(5, 1));
				structureInfoPanel.add(new JLabel("Name: "
						+ structure.getName()));
				structureInfoPanel.add(new JLabel("HP: " + structure.getHP()));
				structureInfoPanel.add(new JLabel("Damage: "
						+ structure.getDamage()));
				structureInfoPanel.add(new JLabel("Rate of Fire: "
						+ structure.getROF() + " shots per second"));
				// structureInfoPanel.add(upgrade);
				structureInfoPanel.setVisible(true);
				structureInfoFrame.setContentPane(structureInfoPanel);
				structureInfoFrame.setVisible(true);
			}
		}
	}

	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "load":
				loadData();
				break;
			case "save":
				saveData();
				break;
			case "pause":
				if (Ticker.getInstance().running())
					Ticker.getInstance().loopStop();
				else
					Ticker.getInstance().loopStart();
				break;
			case "exit":
				System.exit(0);
				break;
			case "options":
				// TODO: implement any game options
				break;
			case "instructions":
				JFrame instr = new JFrame();
				instr = new JFrame();
				instr.setSize(200, 600);
				instr.setResizable(false);
				instr.setTitle("Instructions");

				String howtoInstructions = "How to:\n\n"
						+ "This is a Tower Defense Game, where you will protect your home base by building"
						+ " towers that will defend against waves of enemies!\n\n"
						+ "Getting started:\n To play, simply select"
						+ " the single player or multiplayer game mode. Once in the game, you will be"
						+ " able to select the map you wish to play on.\n\n"
						+ "Gameplay:\nIn the game, you'll be able to select the type of tower you wish to"
						+ " build on the resource window on the right. Selecting a type of tower and clicking"
						+ " on an eligible spot will build a tower there. Click on any of your towers, your base"
						+ " or even enemies to view information about them, upgrade your towers, or heal your base.";

				JPanel instrPanel = new JPanel();
				instrPanel.setLayout(new FlowLayout());
				JTextArea howto = new JTextArea(howtoInstructions);
				howto.setSize(200, 600);
				howto.setEditable(false);
				howto.setBackground(Color.WHITE);
				howto.setLineWrap(true);
				instrPanel.add(howto);
				instrPanel.setVisible(true);
				instr.setContentPane(instrPanel);
				instr.setVisible(true);
				break;
			}

		}
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// empty
		}

	}

	private class ExitListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			if (!Ticker.getInstance().running())
				Ticker.getInstance().loopStart();
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// TODO: I don't know a good place to put this >.>.....
	/**
	 * This method attempts to load account data from "./accounts.dat"
	 * 
	 * @return true if successful, false otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean loadData() {
		FileInputStream inStream;
		ObjectInputStream inObject;

		try {
			// load map
			inStream = new FileInputStream(new File("map.dat"));
			inObject = new ObjectInputStream(inStream);
			mapPanel = (MapPanel) inObject.readObject();
			inObject.close();
			// load tiles
			inStream = new FileInputStream(new File("tiles.dat"));
			inObject = new ObjectInputStream(inStream);
			tilePanel = (TilePanel) inObject.readObject();
			inObject.close();
			loadMap(tilePanel, mapPanel);
			System.out.println("Load successful");
		} catch (Exception e) {
			JFrame cantLoad = new JFrame();
			JLabel loadError = new JLabel("Could not load");
			cantLoad.add(loadError);
			cantLoad.setSize(100, 100);
			cantLoad.setVisible(true);
			return false;
		}
		return true;
	}

	/**
	 * This method attempts to save the account map in "./accounts.dat"
	 */
	public void saveData() {
		FileOutputStream outStream;
		ObjectOutputStream outObject;
		try {
			// save map
			outStream = new FileOutputStream(new File("map.dat"));
			outObject = new ObjectOutputStream(outStream);
			outObject.writeObject(mapPanel);
			outObject.close();
			// save tiles
			outStream = new FileOutputStream(new File("tiles.dat"));
			outObject = new ObjectOutputStream(outStream);
			outObject.writeObject(tilePanel);
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
