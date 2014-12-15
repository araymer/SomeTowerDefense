package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
@SuppressWarnings("serial")
public class ResourcePanel extends JPanel implements ActionListener {
	private static ResourcePanel resourcePanel;
	ButtonGroup radioButtons;
	JRadioButton chronoTower;
	JRadioButton sentryGun;
	JRadioButton plasmaCannon;
	JRadioButton upgrade;
	public JFrame resourceFrame;
	StructureType selected;
	JButton chronoTowerInfo;
	JButton sentryGunInfo;
	JButton plasmaCannonInfo;
	JLabel money;
	JLabel info;

	/**
	 * Constructs the ResourcePanel for use in the GameGUI
	 */
	private ResourcePanel() {
		selected = StructureType.SENTRYGUN;

		this.setLayout(new GridLayout(5, 2));
		this.setPreferredSize(new Dimension(200, 600));
		radioButtons = new ButtonGroup();

		chronoTower = new JRadioButton("Chrono-Tower");
		chronoTowerInfo = new JButton("Info");
		chronoTowerInfo.setActionCommand("chrono");
		chronoTowerInfo.addActionListener(new ButtonListener());
		chronoTower.setActionCommand("ChronoTower");

		sentryGun = new JRadioButton("Sentry Gun");
		sentryGunInfo = new JButton("Info");
		sentryGunInfo.setActionCommand("sentry");
		sentryGunInfo.addActionListener(new ButtonListener());
		sentryGun.setActionCommand("SentryGun");
		sentryGun.setSelected(true);

		plasmaCannon = new JRadioButton("Plasma Cannon");
		plasmaCannonInfo = new JButton("Info");
		plasmaCannonInfo.setActionCommand("plasma");
		plasmaCannonInfo.addActionListener(new ButtonListener());
		plasmaCannon.setActionCommand("PlasmaGun");

		upgrade = new JRadioButton("Upgrade");
		upgrade.setActionCommand("Upgrade");

		money = new JLabel("Funds: 500");

		info = new JLabel("");

		radioButtons.add(chronoTower);
		radioButtons.add(sentryGun);
		radioButtons.add(plasmaCannon);
		radioButtons.add(upgrade);

		this.add(chronoTower);
		this.add(chronoTowerInfo);
		this.add(sentryGun);
		this.add(sentryGunInfo);
		this.add(plasmaCannon);
		this.add(plasmaCannonInfo);
		this.add(upgrade);

		this.add(money);
		this.add(info);

		// add action listeners
		chronoTower.addActionListener(this);
		sentryGun.addActionListener(this);
		plasmaCannon.addActionListener(this);
		upgrade.addActionListener(this);

		this.setOpaque(true);
		this.setVisible(true);

		// resourceFrame.setContentPane(this);
	}

	/**
	 * Gets the Singleton instance of ResourcePanel
	 * 
	 * @author Team Something
	 *
	 * @return ResourcePanel = Singleton instance of ResourcePanel
	 */

	public static ResourcePanel getInstance() {
		if (resourcePanel == null) {
			resourcePanel = new ResourcePanel();
		}
		return resourcePanel;
	}

	/**
	 * Refreshes the player fund display in the ResourcePanel
	 * 
	 * @author Team Something
	 * 
	 * @param arg1
	 *            = integer value of the updated funds
	 */

	public void updateMoney(int arg1) {
		System.out.println("Receiving new money amount");
		money.setText("Funds: " + arg1);
	}

	/**
	 * Changes the information displayed in the info section of ResourcePanel.
	 * Can contain information about Attackers and Structures.
	 * 
	 * @author Team Something
	 *
	 * @param string
	 *            = The info that is to be displayed in the ResourcePanel
	 */
	public void updateInfo(String string) {
		info.setText(string);
	}

	// Inherits JavaDoc from Super
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

	}

	/**
	 * Returns the structure that is selected in the structure selection menu
	 * 
	 * @return StructureType - the new selection
	 * 
	 */
	public StructureType getSelectedStructure() {
		return selected;

	}

	/**
	 * Gets the selection the user makes from the RadioButtons
	 * 
	 * @author Team Something
	 *
	 * @param e
	 *            = ActionEvent from the selection of a radiobutton
	 */
	public void actionPerformed(ActionEvent e) {
		String newSelection = e.getActionCommand();

		switch (newSelection) {
		case "SentryGun":
			selected = StructureType.SENTRYGUN;
			break;
		case "ChronoTower":
			selected = StructureType.CHRONOTOWER;
			break;
		case "PlasmaGun":
			selected = StructureType.PLASMACANNON;
			break;
		case "Upgrade":
			selected = StructureType.UPGRADE;
			break;
		default:
			System.out.println("No selection recorded");
			break;

		}
	}

	/**
	 * Listener for info buttons for different types of Structures
	 * 
	 * @author Team Something
	 *
	 */
	private class ButtonListener implements ActionListener {

		String structureName;
		String structureHP;
		String structureROF;
		String structureCost;
		String structureDamage;

		/**
		 * Displays the according structure info for the structure the user
		 * selects
		 * 
		 * @author Team Something
		 *
		 * @param e
		 *            = The button the user selects
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO: Finish getting this info
			switch (e.getActionCommand()) {
			case "chrono":
				structureName = "Chrono-Tower";
				structureHP = "100";
				structureROF = "5";
				structureCost = "100";
				structureDamage = "5";
				infoSetter();
				break;
			case "sentry":
				structureName = "Sentry Gun";
				structureHP = "100";
				structureROF = "5";
				structureCost = "100";
				structureDamage = "5";
				infoSetter();
				break;
			case "plasma":
				structureName = "Plasma Cannon";
				structureHP = "100";
				structureROF = "5";
				structureCost = "100";
				structureDamage = "5";
				infoSetter();
				break;
			}

		}

		/**
		 * Sets the structure information to be displayed in the info section of
		 * ResourcePanel.
		 * 
		 * @author Team Something
		 *
		 */
		public void infoSetter() {
			String info = "<html>Name: " + structureName + "<br>HP: "
					+ structureHP + "<br>Damage: " + structureDamage
					+ "<br>Rate of Fire: " + structureROF + " shots per second"
					+ "<br>Cost: " + structureCost + "</html>";
			resourcePanel.updateInfo(info);
		}
	}

	/**
	 * Reinstantiates ResourcePanel and sets it as the new Singleton
	 * instantiation.
	 * 
	 * @author Team Something
	 *
	 */
	public void reinit() {
		resourcePanel = new ResourcePanel();

	}
}
