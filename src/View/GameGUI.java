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
	TilePanel tilePanel;
	ResourcePanel resourcePanel;
	

	/**
	 * Constructs the Tower Defense GUI
	 */
	public GameGUI() {
		createFrame();

		MapPanel mapPanel = MapPanel.getInstance();
		mapPanel.setMap("desertuprising.jpg");
		tilePanel = TilePanel.getInstance();
		MouseListener placementListener = new PlacementListener();
		tilePanel.addMouseListener(placementListener);
		// Stacking panels on top of each other
		frame.add(mapPanel);
		mapPanel.add(tilePanel);
		resourcePanel = ResourcePanel.getInstance();
		tilePanel.add(resourcePanel);
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
	
	public void repaint(){
		tilePanel.repaint();
		resourcePanel.repaint();
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
			
			tilePanel.getMap().createStructure(resourcePanel.getSelectedStructure(), e.getPoint());
			
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			//This shouldn't be in final version
			repaint();
			
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
