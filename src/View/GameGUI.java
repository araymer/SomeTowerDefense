package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.JFrame;

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

	/**
	 * Constructs the Tower Defense GUI
	 */
	private GameGUI() {
		createFrame();
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

		contentPane = frame.getContentPane();
		contentPane.setLayout(new CardLayout());

		tilePanel = TilePanel.getInstance();
		MouseListener placementListener = new PlacementListener();
		tilePanel.addMouseListener(placementListener);
		tilePanel.setSize(frame.getSize().width, frame.getSize().height);
		tilePanel.setLocation(0, 0);
		tilePanel.setLayout(new CardLayout());

		resourcePanel = ResourcePanel.getInstance();
		resourcePanel.setSize(frame.getSize().width, frame.getSize().height);
		resourcePanel.setLocation(0, 0);

		contentPane.add(tilePanel);
		tilePanel.add(resourcePanel);

		frame.repaint();

		new Thread(Ticker.getInstance()).start();
		// GameController.getInstance().startWaves();
		
		
		if(isMultiplayer){
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

	public void repaint() {
		tilePanel.repaint();
		resourcePanel.repaint();
	}

	public static GameGUI getInstance() {
		if (thisGUI == null)
			thisGUI = new GameGUI();

		return thisGUI;
	}

	public void returnMenu() {
		frame.setContentPane(MainMenu.getInstance());

	}
	
	public void setClient(TDClient cli){
		client = cli;
		isMultiplayer = true;
		// add a listener that sends a disconnect command to when closing
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				client.closeClient();
			}
		});
	}
	
	public void baseTakeDamage(int damageAmount){
		client.baseTakeDamage(damageAmount);
	}

	private class PlacementListener extends MouseAdapter {


		@Override
		public void mousePressed(MouseEvent e) {
			// Make a new structure on click
			System.out.println("Creating new structure at " + e.getPoint());

			tilePanel.getMap().createStructure(
					resourcePanel.getSelectedStructure(), e.getPoint());

		}

	}
	

}
