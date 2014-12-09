package View;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
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
	JFrame resourceFrame;
	StructureType selected;

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

		this.setLayout(new FlowLayout());
		radioButtons = new ButtonGroup();
		
		chronoTower = new JRadioButton("Chrono-Tower");
		JButton chronoTowerInfo = new JButton("Info");
		chronoTower.setActionCommand("ChronoTower");
		
		sentryGun = new JRadioButton("Sentry Gun");
		JButton sentryGunInfo = new JButton("Info");
		sentryGun.setActionCommand("SentryGun");
		sentryGun.setSelected(true);
		
		plasmaCannon = new JRadioButton("Plasma Cannon");
		JButton plasmaCannonInfo = new JButton("Info");
		plasmaCannon.setActionCommand("PlasmaGun");
		
		upgrade = new JRadioButton("Upgrade");
		JButton upgradeInfo = new JButton("Info");
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
		this.add(upgradeInfo);
		
		//add action listeners
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
		
		switch(newSelection) {
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
				System.out.println("upgrade mode");
				break;
			default:
				System.out.println("No selection recorded");
				break;
				
		}
	}
}
