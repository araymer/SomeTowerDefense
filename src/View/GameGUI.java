package View;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JFrame;

/**
 * The class that organizes all the GUI elements for the tower defense game
 * 
 * @author Marcos
 *
 */
public class GameGUI implements Serializable {

	private final int FRAME_WIDTH = 800;
	// Extra 22 for bar
	private final int FRAME_HEIGHT = 622;
	JFrame frame;

	/**
	 * Constructs the Tower Defense GUI
	 */
	public GameGUI() {
		createFrame();

		MapPanel mapPanel = MapPanel.getInstance();
		mapPanel.setMap("desertuprising.jpg");
		TilePanel tilePanel = TilePanel.getInstance();
		MouseListener placementListener = new PlacementListener();
		tilePanel.addMouseListener(placementListener);
		// Stacking panels on top of each other
		frame.add(mapPanel);
		mapPanel.add(tilePanel);
		tilePanel.add(ResourcePanel.getInstance());
		frame.setVisible(true);
	}

	/**
	 * Creates and sets the JFrame
	 */
	private void createFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		// Color is for debug purposes
		frame.setBackground(Color.GREEN);
		frame.getContentPane().setBackground(Color.MAGENTA);
		frame.setTitle("Some Tower Defense");

	}
	
	private class PlacementListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Creating new structure at " + e.getPoint());
			
			// map.createStructure(ResourcePanel.getInstance().getSelectedStructure, e.getPoint());
			
			// Replace map with whatever will be used to set buildings
			// 1st variable will be enum for the structure types
			// The value of the enum will be dependent on which is selected on the menu
			// createStructure will have to find which tile to place the structure depending on mouse location
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
