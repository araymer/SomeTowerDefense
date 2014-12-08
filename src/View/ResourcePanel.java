package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
	ButtonGroup radioButtons;
	JRadioButton chronoTower;
	JRadioButton sentryGun;
	JRadioButton plasmaCannon;
	JRadioButton stasisTower;

	/**
	 * Constructs the ResourcePanel for use in the GameGUI
	 */
	private ResourcePanel() {
		this.setLayout(new FlowLayout());
		radioButtons = new ButtonGroup();
		chronoTower = new JRadioButton("Chrono-Tower");
		JButton chronoTowerInfo = new JButton("Info");
		sentryGun = new JRadioButton("Sentry Gun");
		JButton sentryGunInfo = new JButton("Info");
		plasmaCannon = new JRadioButton("Plasma Cannon");
		JButton plasmaCannonInfo = new JButton("Info");
		stasisTower = new JRadioButton("Stasis Tower");
		JButton stasisTowerInfo = new JButton("Info");

		radioButtons.add(chronoTower);
		radioButtons.add(sentryGun);
		radioButtons.add(plasmaCannon);
		radioButtons.add(stasisTower);

		this.add(chronoTower);
		this.add(chronoTowerInfo);
		this.add(sentryGun);
		this.add(sentryGunInfo);
		this.add(plasmaCannon);
		this.add(plasmaCannonInfo);
		this.add(stasisTower);
		this.add(stasisTowerInfo);

		this.setOpaque(true);
		// this.setBackground(new Color(0, 0, 0, 0.5f));
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

		float alpha = 0.50f;
		Color color = new Color(0, 0, 1, alpha); // Blue
		g2.setPaint(color);
		g2.fillRect(0, 0, 800, 30);
	}

	/**
	 * Returns the structure that is selected in the structure selection menu
	 * 
	 * @return
	 * 
	 */
	public StructureType getSelectedStructure() {
		if (radioButtons.getSelection() == chronoTower)
			return StructureType.CHRONOTOWER;
		if (radioButtons.getSelection() == sentryGun)
			return StructureType.SENTRYGUN;
		if (radioButtons.getSelection() == plasmaCannon)
			return StructureType.PLASMACANNON;
		if (radioButtons.getSelection() == stasisTower)
			return StructureType.STASISTOWER;
		return StructureType.PLASMACANNON;
	}
}
