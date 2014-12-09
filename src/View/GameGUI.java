package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Controller.TDClient;
import Model.Ticker;

/**
 * The class that organizes all the GUI elements for the tower defense game
 * 
 * @author Team Something
 *
 */
public class GameGUI implements Serializable {

	private final int FRAME_WIDTH = 800;
	// Extra 22 for bar
	private final int FRAME_HEIGHT = 622;
	Container contentPane;
	JFrame frame;
	public TilePanel tilePanel;
	ResourcePanel resourcePanel;
	private static GameGUI thisGUI;
	private TDClient client;
	public boolean isMultiplayer = false;
	public int mapSelection;

	/**
	 * Constructs the Tower Defense GUI
	 */
	private GameGUI() {
		createFrame();
		createMenuBar();
		frame.setContentPane(MainMenu.getInstance());

		// createMap(0);

		frame.setVisible(true);

	}

	void createMap(int selection) {
		MapPanel mapPanel = MapPanel.getInstance();

		switch (selection) {
		case 0:
			mapPanel.setMap("desertuprising.jpg");

			break;
		}

		mapPanel.setSize(frame.getSize().width, frame.getSize().height);
		mapPanel.setLocation(0, 0);
		frame.setContentPane(mapPanel);
		// frame.add(mapPanel);
		// frame.add(ResourcePanel.getInstance(), null, -1);

		contentPane = frame.getContentPane();
		contentPane.setLayout(new CardLayout());

		tilePanel = TilePanel.getInstance();
		MouseListener placementListener = new PlacementListener();
		tilePanel.addMouseListener(placementListener);
		tilePanel.setSize(frame.getSize().width, frame.getSize().height);
		tilePanel.setLocation(0, 0);
		tilePanel.setLayout(new CardLayout());

		resourcePanel = ResourcePanel.getInstance();
		// resourcePanel.setSize(frame.getSize().width, frame.getSize().height);
		// resourcePanel.setSize(200, frame.getSize().height);
		// resourcePanel.setLocation(801, 0);
		// resourcePanel.setLayout(new FlowLayout());

		contentPane.add(tilePanel);
		// tilePanel.add(resourcePanel);

		frame.setJMenuBar(menuBar);

		frame.repaint();

		new Thread(Ticker.getInstance()).start();
		// GameController.getInstance().startWaves();

		if (isMultiplayer) {
			client.setStartingServerHP();
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

	}

	JMenuBar menuBar;
	JMenu game;
	JMenuItem load;
	JCheckBox pause;
	JMenuItem exit;
	JMenu help;
	JMenuItem options;
	JMenuItem instructions;

	void createMenuBar() {
		menuBar = new JMenuBar();
		// JMenu file = new JMenu("File");
		// JMenuItem
		// TODO: GOING TO HAVE TO ADD LISTENERS TO THESE LATER
		game = new JMenu("Game");
		load = new JMenuItem("Load");
		load.addActionListener(new MenuListener());
		game.add(load);
		pause = new JCheckBox("Pause");
		game.add(pause);
		exit = new JMenuItem("Exit");
		game.add(exit);
		help = new JMenu("Help");
		options = new JMenuItem("Options");
		help.add(options);
		instructions = new JMenuItem("How To Play");
		help.add(instructions);
		frame.setJMenuBar(menuBar);
		// menuBar.add(file);
		menuBar.add(game);
		menuBar.add(help);
		menuBar.setVisible(true);
	}

	public void repaint() {
		tilePanel.repaint();
		// resourcePanel.repaint();
	}

	public static GameGUI getInstance() {
		if (thisGUI == null)
			thisGUI = new GameGUI();

		return thisGUI;
	}

	public void returnMenu() {
		frame.setContentPane(MainMenu.getInstance());

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

	public void baseTakeDamage(int damageAmount) {
		client.baseTakeDamage(damageAmount);
	}
	
	public void startMultiplayerGame(){
		//TODO
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
		}

	}

	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == load)
				return;
			if (e.getSource() == pause)
				return;
			if (e.getSource() == exit)
				System.exit(0);
			if (e.getSource() == options)
				return;
			if (e.getSource() == instructions)
				return;

		}
	}

}
