package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.GameController;
import Controller.TDClient;
import Maps.BeachBetrayal;
import Maps.BrokenPlainsPatrol;
import Maps.DesertUprising;
import Model.Attacker;
import Model.Map;
import Model.Player;
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

	private final int FRAME_WIDTH = 1100;
	// Extra 22 for bar
	private final int FRAME_HEIGHT = 670;
	public JFrame frame;
	public TilePanel tilePanel;
	private JPanel gamePanel;
	private JPanel playPanel;
	private JLayeredPane mapOverlay;
	public MultiplayerFrame multiFrame;
	private static GameGUI thisGUI;
	private TDClient client;
	public boolean isMultiplayer = false;
	public volatile boolean isRunning = false;
	public int mapSelection;
	private CardLayout cards;
	Structure structure;
	public JFrame resourceFrame;
	private int currentMap;
	public double interpolation;
	private Thread tickerThread;

	/**
	 * Constructs the Tower Defense GUI
	 */
	private GameGUI() {

		cards = new CardLayout(0, 0);
		gamePanel = new JPanel();
		playPanel = new JPanel();
		mapOverlay = new JLayeredPane();

		gamePanel.setLayout(cards);
		gamePanel.setPreferredSize(new Dimension(1080, 600));
		gamePanel.setLocation(0, 0);
		gamePanel.add(MainMenu.getInstance(), "Main");

		createFrame();
		createMenuBar();

		frame.add(gamePanel);

		CardLayout c1 = (CardLayout) gamePanel.getLayout();
		c1.show(gamePanel, "Main");

		frame.setVisible(true);

	}

	/**
	 * Creates the background image for the map
	 * 
	 * @author Team Something
	 *
	 * @param selection
	 *            = integer representation of selected map
	 */
	void createMap(int selection) {
		//gamePanel = new JPanel();
		playPanel = new JPanel();
		mapOverlay = new JLayeredPane();

		currentMap = selection;
		switch (selection) {
		case 0:
			MapPanel.getInstance().setMap("desertuprising.jpg");
			break;
		case 1:
			MapPanel.getInstance().setMap("BrokenPlainsPatrol.jpg");
			break;
		case 2:
			MapPanel.getInstance().setMap("BeachBetrayal.jpg");
			break;
		}

		tilePanel = TilePanel.getInstance();
		tilePanel.setMap(selection);

		MouseListener placementListener = new PlacementListener();

		tilePanel.setSize(800, 600);

		tilePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		playPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		playPanel.setPreferredSize(new Dimension(1080, 600));

		MapPanel.getInstance().setPreferredSize(new Dimension(800, 600));

		mapOverlay.setPreferredSize(new Dimension(800, 600));
		mapOverlay.add(MapPanel.getInstance(), -1);
		mapOverlay.add(TilePanel.getInstance(), 0);

		playPanel.add(mapOverlay);
		tilePanel.addMouseListener(placementListener);
		playPanel.add(ResourcePanel.getInstance());

		gamePanel.add(playPanel, "Play");

		CardLayout c1 = (CardLayout) gamePanel.getLayout();

		c1.show(gamePanel, "Play");

		if (isMultiplayer) {
			client.setStartingServerHP();
			multiFrame = new MultiplayerFrame();
		}

		isRunning = true;
		if(tickerThread == null){
			tickerThread = new Thread(Ticker.getInstance());
		}
		Ticker.getInstance().loopStart();
		Ticker.getInstance().waves.setWave(1);
		
		tickerThread.start();

	}

	/**
	 * Creates the map from load data
	 * 
	 * @author Team Something
	 *
	 * @param map
	 *            = loaded map data
	 */
	public void createLoadedMap(Map map) {
		playPanel = new JPanel();
		mapOverlay = new JLayeredPane();
		
		MapPanel.getInstance().setMap(map.mapImageName);
		Player.getInstance().setMoney(map.playerMoney.getMoney());
		tilePanel = TilePanel.getInstance();

		MouseListener placementListener = new PlacementListener();

		tilePanel.setSize(800, 600);

		tilePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		playPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		playPanel.setPreferredSize(new Dimension(1080, 600));

		MapPanel.getInstance().setPreferredSize(new Dimension(800, 600));

		mapOverlay.setPreferredSize(new Dimension(800, 600));
		mapOverlay.add(MapPanel.getInstance(), -1);
		mapOverlay.add(TilePanel.getInstance(), 0);

		playPanel.add(mapOverlay);
		tilePanel.addMouseListener(placementListener);
		playPanel.add(ResourcePanel.getInstance());

		gamePanel.add(playPanel, "Play");

		CardLayout c1 = (CardLayout) gamePanel.getLayout();

		c1.show(gamePanel, "Play");

		if (isMultiplayer) {
			client.setStartingServerHP();
			multiFrame = new MultiplayerFrame();
		}
		
		TilePanel.getInstance().setMap(map);
		tilePanel.setMap(map);
		Player.getInstance().setMoney(map.playerMoney.getMoney());

		isRunning = true;
		if(tickerThread == null){
			tickerThread = new Thread(Ticker.getInstance());
		}
		
		Ticker.getInstance().waves.setWave(map.waveNumber);
		Ticker.getInstance().loopStart();
		try{
			tickerThread.start();
		}catch(Exception e){
			
		}
		
		System.out.println("GameGUI: finished loading");
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
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setTitle("Some Tower Defense");
		createMenuBar();
		frame.setLayout(new FlowLayout());

		frame.setJMenuBar(menuBar);

	}

	JMenuBar menuBar;
	JMenu game;
	JMenuItem load;
	JMenuItem save;
	JMenuItem restart;
	JMenuItem main;
	JCheckBox pause;
	JCheckBox fast;
	JMenuItem exit;
	JMenu help;
	JMenuItem options;
	JMenuItem instructions;

	/**
	 * Creates the JMenuBar
	 * 
	 * @author Team Something
	 *
	 */
	void createMenuBar() {
		menuBar = new JMenuBar();
		game = new JMenu("Game");
		restart = new JMenuItem("Restart");
		restart.addActionListener(new MenuListener());
		restart.setActionCommand("restart");
		game.add(restart);
		// save = new JMenuItem("Save");
		// save.addActionListener(new MenuListener());
		// save.setActionCommand("save");
		// game.add(save);
		// load = new JMenuItem("Load");
		// load.addActionListener(new MenuListener());
		// load.setActionCommand("load");
		// game.add(load);
		pause = new JCheckBox("Pause");
		pause.addActionListener(new MenuListener());
		pause.setActionCommand("pause");
		game.add(pause);
		fast = new JCheckBox("Fast Mode");
		fast.addActionListener(new MenuListener());
		fast.setActionCommand("fast");
		game.add(fast);
		main = new JMenuItem("Return to Menu");
		main.addActionListener(new MenuListener());
		main.setActionCommand("main");
		game.add(main);
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
		menuBar.add(game);
		menuBar.add(help);
		menuBar.setVisible(true);
	}

	/**
	 * Alerts the GUI that there is a new tile panel.
	 * 
	 * @author Team Something
	 *
	 */
	public void newTilePanel() {
		tilePanel = TilePanel.getInstance();
		tilePanel.addMouseListener(new PlacementListener());
	}

	/**
	 * Implements interpolated animations
	 * 
	 * @author Team Something
	 *
	 */
	public void repaint(double inter) {
		interpolation = inter;
		tilePanel.repaint();
	}

	/**
	 * Returns the instance of the GameGUI
	 * 
	 * @author Team Something
	 * @return GameGUI = instance of GameGUI
	 */
	public static GameGUI getInstance() {
		if (thisGUI == null)
			thisGUI = new GameGUI();

		return thisGUI;
	}

	/**
	 * Returns to the Main Menu
	 * 
	 * @author Team Something
	 *
	 */
	public void returnMenu() {
		Ticker.getInstance().loopStop();
		gamePanel.remove(playPanel);
		tilePanel = tilePanel.reset();
		// tilePanel = TilePanel.getInstance();
		tilePanel.setMap(currentMap);
		
		
	}

	/**
	 * Restarts the map
	 * 
	 * @author Team Something
	 *
	 */
	private void restartMap() {

		tilePanel = tilePanel.reset();
		// tilePanel = TilePanel.getInstance();
		tilePanel.setMap(currentMap);
		Ticker.getInstance().waves.setWave(1);
		Ticker.getInstance().loopStart();

	}

	/**
	 * Sets up the client for a multiplayer game
	 * 
	 * @author Team Something
	 *
	 */
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

	/**
	 * Returns the client
	 * 
	 * @author Team Something
	 * @return TDClient = the game client
	 */
	public TDClient getClient() {
		return client;
	}

	/**
	 * Delivers damage to the base.
	 * 
	 * @author Team Something
	 * @param damageAmount
	 *            = damage to be delivered to base
	 */
	public void baseTakeDamage(int damageAmount) {
		client.baseTakeDamage(damageAmount);
	}

	/**
	 * Starts a multiplayer game
	 * 
	 * @author Team Something
	 *
	 */
	public void startMultiplayerGame() {
		createMap(mapSelection);
		MainMenu.getInstance().resetLogo();
	}

	/**
	 * Listens to the mouse to carry out various operations in the game
	 * 
	 * @author Team Something
	 *
	 */
	private class PlacementListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			// Make a new structure on click
			if (e.getPoint().getX() <= 800 && e.getPoint().getY() <= 600) {
				System.out.println("Creating new structure at " + e.getPoint());

				tilePanel.getMap().createStructure(
						ResourcePanel.getInstance().getSelectedStructure(),
						e.getPoint());

			}

			// Aaaaaand allows to get attacker info
			try {
				Vector<Attacker> attackers = tilePanel.getMap().getGameBoard()
						.get((int) Math.round(e.getPoint().getX()) / 40)
						.get((int) Math.round(e.getPoint().getY()) / 40)
						.getAttackers();
				if (attackers.size() != 0) {
					String info = "<html>Name: " + attackers.get(0).getName()
							+ "<br>HP: " + attackers.get(0).getHP()
							+ "<br>Attack: " + attackers.get(0).getAttack()
							+ "</html>";
					ResourcePanel.getInstance().updateInfo(info);
				}
			} catch (Exception exception) {

			}

			// Aaaaaaand Structure info :P
			try {
				if (tilePanel.getMap().getGameBoard()
						.get((int) Math.round(e.getPoint().getX()) / 40)
						.get((int) Math.round(e.getPoint().getY()) / 40)
						.getStructure() != null) {
					structure = tilePanel.getMap().getGameBoard()
							.get((int) Math.round(e.getPoint().getX()) / 40)
							.get((int) Math.round(e.getPoint().getY()) / 40)
							.getStructure();
					String info = "<html>Name: " + structure.getName()
							+ "<br>HP: " + structure.getHP() + "<br>Damage: "
							+ structure.getDamage() + "<br>Rate of Fire: "
							+ structure.getROF() + " shots per second</html>";
					TilePanel.getInstance().setCirclePoints(
							(int) Math.round(e.getPoint().getX()) / 40,
							(int) Math.round(e.getPoint().getY()) / 40,
							structure.getRange());
					ResourcePanel.getInstance().updateInfo(info);
				}
			} catch (Exception exception) {

			}
		}
	}

	/**
	 * Listens to the JMenuBar to perform various actions
	 * 
	 * 
	 * @author Team Something
	 *
	 */
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "main":
				returnMenu();
				break;
			case "restart":
				restartMap();
				break;
			case "load":
				GameController.getInstance().loadData();
				break;
			case "fast":
				Ticker.getInstance().changeSpeed();
				break;
			case "save":
				GameController.getInstance().saveData();
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
				// implement any game options
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
						+ " the map you want to play on and then choose single player or multiplayer mode."
						+ " \n\n"
						+ "Gameplay:\nIn the game, you'll be able to select the type of tower you wish to"
						+ " build on the resource window on the right. Selecting a type of tower and clicking"
						+ " on an eligible spot will build a tower there. Click on any of your towers, your base"
						+ " or even enemies to view information about them or upgrade your towers."
						+ " You can win the game by having the base survive against all the waves.";

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

}
