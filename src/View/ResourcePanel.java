package View;

import java.awt.Graphics;
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

	/**
	 * Constructs the ResourcePanel for use in the GameGUI
	 */
	private ResourcePanel() {
		selected = StructureType.SENTRYGUN;
		resourceFrame = new JFrame();
		resourceFrame.setSize(200, 200);
		resourceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resourceFrame.setResizable(false);
		resourceFrame.setTitle("Resources");
		resourceFrame.setLocation(811, 0);
		resourceFrame.setVisible(true);

		this.setLayout(new GridLayout(8, 1));
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
		// JButton upgradeInfo = new JButton("Info");
		upgrade.setActionCommand("Upgrade");

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
	

		// add action listeners
		chronoTower.addActionListener(this);
		sentryGun.addActionListener(this);
		plasmaCannon.addActionListener(this);
		upgrade.addActionListener(this);

		this.setOpaque(true);
		this.setVisible(true);

		resourceFrame.setContentPane(this);
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

	private class ButtonListener implements ActionListener {

		String structureName;
		String structureHP;
		String structureROF;
		String structureCost;
		String structureDamage;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO: get actual values, got lazy
			switch (e.getActionCommand()) {
			case "chrono":
				structureName = "Chrono-Tower";
				structureHP = "100";
				structureROF = "5";
				structureCost = "100";
				structureDamage = "5";
				break;
			case "sentry":
				break;
			case "plasma":
				break;
			}
			JFrame structureInfoFrame = new JFrame();
			structureInfoFrame.setResizable(false);
			structureInfoFrame.setSize(100, 200);
			structureInfoFrame.setLocation(811, 250);
			JPanel structureInfoPanel = new JPanel();
			structureInfoPanel.setLayout(new GridLayout(5, 1));
			structureInfoPanel.add(new JLabel("Name: " + structureName));
			structureInfoPanel.add(new JLabel("HP: " + structureHP));
			structureInfoPanel.add(new JLabel("Damage: " + structureDamage));
			structureInfoPanel.add(new JLabel("Rate of Fire: " + structureROF
					+ " shots per second"));
			structureInfoPanel.add(new JLabel("Cost: " + structureCost));
			structureInfoPanel.setVisible(true);
			structureInfoFrame.setContentPane(structureInfoPanel);
			structureInfoFrame.setVisible(true);
		}
	}

	public void reinit() {
		resourcePanel = new ResourcePanel();
		
	}
}
