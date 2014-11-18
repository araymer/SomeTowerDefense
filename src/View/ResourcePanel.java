package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Model.StructureType;

/**
 * A panel used to display player information such as resource numbers, wave
 * number, and structure and attacker selection
 * 
 * @author Team Something
 *
 */
public class ResourcePanel extends JPanel {
	private static ResourcePanel resourcePanel;

	/**
	 * Constructs the ResourcePanel for use in the GameGUI
	 */
	private ResourcePanel() {
		this.setOpaque(false);
		this.setVisible(true);
	}

	public static ResourcePanel getInstance() {
		if (resourcePanel == null) {
			resourcePanel = new ResourcePanel();
		}
		return resourcePanel;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Test to make sure panel is clear and drawn elements are on top of map
		// picture
		float alpha = 0.50f;
		Color color = new Color(1, 0, 0, alpha); // Red
		g2.setPaint(color);
		g2.fillRect(0, 0, 800, 30);
	}
	/**
	 * Returns the structure that is selected in the structure selection menu
	 * 
	 * @return
	 * 
	 */
	public StructureType getSelectedStructure(){
		//TODO isn't complete
		return StructureType.SENTRYGUN;
	}
}
