package View;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JFrame;

/**
 * The class that organizes all the GUI elements for the tower defense game
 * 
 * @author Marcos
 *
 */
public class GameGUI implements Serializable{
	
	private int FRAME_WIDTH = 800;
	//desertuprising.jpg seems to have height 622
	//NOTE: I think the 22 px is coming from the bar at the top with the window buttons. I checked the image
	// and it's definitely 600px high. 
	private int FRAME_HEIGHT = 600;
	private JFrame frame;
	
	
	
	/**
	 * Constructs the Tower Defense GUI
	 */
	public GameGUI(){
		createFrame();
		
		MapPanel mapPanel = new MapPanel("desertuprising.jpg");
		TilePanel tilePanel = new TilePanel();
		//Stacking panels on top of each other
		frame.add(mapPanel);
		mapPanel.add(tilePanel);
		tilePanel.add(new ResourcePanel());
		frame.setVisible(true);
	}
	
	/**
	 * Creates and sets the JFrame
	 */
	private void createFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		//Color is for debug purposes
		frame.setBackground(Color.GREEN);
		frame.getContentPane().setBackground(Color.MAGENTA);
		frame.setTitle("Some Tower Defense");

	}

}
