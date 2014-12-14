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

	private final int FRAME_WIDTH = 1100;
	// Extra 22 for bar
	private final int FRAME_HEIGHT = 644;
	public JFrame frame;
	public TilePanel tilePanel;
	private JPanel gamePanel;
	private JPanel playPanel;
	private JLayeredPane mapOverlay;
	public MultiplayerFrame multiFrame;
	private static GameGUI thisGUI;
	private TDClient client;
	public boolean isMultiplayer = false;
	public int mapSelection;
	private CardLayout cards;
	Structure structure;
	public JFrame resourceFrame;
	public double interpolation;

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

	void createMap(int selection) {

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

		new Thread(Ticker.getInstance()).start();

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
	JMenuItem exit;
	JMenu help;
	JMenuItem options;
	JMenuItem instructions;

	void createMenuBar() {
		menuBar = new JMenuBar();
		game = new JMenu("Game");
		/*
		 * restart = new JMenuItem("Restart");
		 * restart.addActionListener(newMenuListener());
		 * restart.setActionCommand("restart"); game.add(restart); main = new
		 * JMenuItem("Return to Menu"); main.addActionListener(new
		 * MenuListener()); main.setActionCommand("main"); game.add(main);
		 */
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
		resetMenuBar();
	}

	public void resetMenuBar() {
		frame.setJMenuBar(menuBar);

	}

	public void newTilePanel() {
		tilePanel = TilePanel.getInstance();
		tilePanel.addMouseListener(new PlacementListener());
	}

	public void repaint(double inter) {
		interpolation = inter;
		tilePanel.repaint();
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
					ResourcePanel.updateInfo(info);
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
					ResourcePanel.updateInfo(info);

				}
			} catch (Exception exception) {

			}
		}
	}

	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "main":
				GameController.getInstance().returnToMain();
				break;
			case "restart":
				GameController.getInstance().restartMap();
				break;
			case "load":
				GameController.getInstance().loadData();
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

}
