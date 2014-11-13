package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * A panel used to display player information such as resource numbers, wave number,
 * and structure and attacker selection
 * 
 * @author Marcos
 *
 */
public class ResourcePanel extends JPanel{
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	
	/**
	 * Constructs the ResourcePanel for use in the GameGUI
	 */
	public ResourcePanel(){
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setOpaque(false);
		this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//Test to make sure panel is clear and drawn elements are on top of map picture
		float alpha = 0.50f;
		Color color = new Color(1, 0, 0, alpha); //Red 
		g2.setPaint(color);
		g2.fillRect(0, 0, 800, 30);
	}
}
