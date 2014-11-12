package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * A clear panel that is used to display Structures and Attackers in the game
 * 
 * @author Marcos
 *
 */

public class TilePanel extends JPanel{
	private int WIDTH = 800;
	private int HEIGHT = 600;
	
	/**
	 * Constructs the TilePanel for use in the GameGUI
	 */
	public TilePanel(){
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setOpaque(false);
		this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//Test to make sure panel is clear and drawn elements are on top of map picture
		g2.setColor(Color.GREEN);
		g2.fillRect(100, 100, 10, 10);
		g2.fillRect(400, 400, 5, 5);
	}
}
