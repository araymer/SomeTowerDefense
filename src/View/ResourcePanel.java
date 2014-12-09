package View;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class ResourcePanel extends JPanel implements ActionListener {
	private static ResourcePanel resourcePanel;
	ButtonGroup radioButtons;
	JRadioButton chronoTower;
	JRadioButton sentryGun;
	JRadioButton plasmaCannon;
	JRadioButton stasisTower;
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
		
		//stasisTower = new JRadioButton("Stasis Tower");
		//JButton stasisTowerInfo = new JButton("Info");

		radioButtons.add(chronoTower);
		radioButtons.add(sentryGun);
		radioButtons.add(plasmaCannon);
		//radioButtons.add(stasisTower);

		this.add(chronoTower);
		this.add(chronoTowerInfo);
		this.add(sentryGun);
		this.add(sentryGunInfo);
		this.add(plasmaCannon);
		this.add(plasmaCannonInfo);
		//this.add(stasisTower);
		//this.add(stasisTowerInfo);
		
		//add action listeners
		chronoTower.addActionListener(this);
		sentryGun.addActionListener(this);
		plasmaCannon.addActionListener(this);

		this.setOpaque(true);
		// this.setBackground(new Color(0, 0, 0, 0.5f));
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
		Graphics2D g2 = (Graphics2D) g;

		// float alpha = 0.50f;
		// Color color = new Color(0, 0, 1, alpha); // Blue
		// g2.setPaint(color);
		// g2.fillRect(0, 0, 800, 30);
	}

	/**
	 * Returns the structure that is selected in the structure selection menu
	 * 
	 * @return
	 * 
	 */
	public StructureType getSelectedStructure() {
		return selected;
		
		/*
		if (radioButtons.getSelection() == chronoTower)
			return StructureType.CHRONOTOWER;
		if (radioButtons.getSelection() == sentryGun)
			return StructureType.SENTRYGUN;
		if (radioButtons.getSelection() == plasmaCannon)
			return StructureType.PLASMACANNON;
		if (radioButtons.getSelection() == stasisTower)
			return StructureType.STASISTOWER;
		System.out.println("No selection recorded");
		return StructureType.SENTRYGUN;
		*/
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
			default:
				System.out.println("No selection");
				break;
				
		}
	}
}
