package View;

import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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

	public static ResourcePanel getInstance() {
		if (resourcePanel == null) {
			resourcePanel = new ResourcePanel();
		}
		return resourcePanel;
	}

	public void updateMoney(int arg1) {
		System.out.println("Receiving new money amount");
		money.setText("Funds: " + arg1);
	}

	public void updateInfo(String string) {
		info.setText(string);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (circleX != -1 && circleY != -1) {
			System.out.println("OVAL");
			g2.setColor(new Color(1, 1, 1, 0.5f));
			g2.fillOval(circleX - 50, circleY - 50, 50, 50);
		}
	}

	int circleX = -1;
	int circleY = -1;

	public void setCirclePoints(double x, double y) {
		circleX = (int) x;
		circleY = (int) y;
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

		public void infoSetter() {
			String info = "<html>Name: " + structureName + "<br>HP: "
					+ structureHP + "<br>Damage: " + structureDamage
					+ "<br>Rate of Fire: " + structureROF + " shots per second"
					+ "<br>Cost: " + structureCost + "</html>";
			resourcePanel.updateInfo(info);
		}
	}

	public void reinit() {
		resourcePanel = new ResourcePanel();

	}
}
