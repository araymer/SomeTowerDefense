package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.TDClient;
import Controller.TDServer;

public class MainMenu extends JPanel {

	private static MainMenu mainMenu;
	Image bgImage;
	Image logo;
	private static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "imageFiles"
			+ System.getProperty("file.separator");
	// JButton desertMap;
	JButton singleplayer;
	JButton multiplayer;
	JButton load;
	JButton beachButton;
	JButton plainsButton;
	JButton desertButton;
	JLabel chooseLabel;

	private MainMenu() {
		
		this.setLayout(new BorderLayout());
		// desertMap = new JButton("Desert Uprising");
		singleplayer = new JButton("Single Player");
		multiplayer = new JButton("Multi-Player");
		load = new JButton("Load Last Game");
		beachButton  = new JButton("Beach Betrayal");
		plainsButton = new JButton("Broken Plains Patrol");
		desertButton = new JButton("Desert Uprising");
		chooseLabel = new JLabel("Choose A Map First");
		ButtonListener buttonListener = new ButtonListener();
		// desertMap.addActionListener(buttonListener);
		singleplayer.addActionListener(buttonListener);
		multiplayer.addActionListener(buttonListener);
		// this.add(desertMap);
		JPanel startPanel = new JPanel();
		startPanel.add(singleplayer);
		startPanel.add(multiplayer);
		startPanel.add(load);
		startPanel.setBackground(Color.BLACK);
		this.add(startPanel, BorderLayout.NORTH);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2));
		
		JPanel mapChoosePanel = new JPanel();
		mapChoosePanel.add(beachButton);
		mapChoosePanel.add(plainsButton);
		mapChoosePanel.add(desertButton);
		bottomPanel.add(chooseLabel);
		bottomPanel.add(mapChoosePanel);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.setBackground(Color.BLACK);

		// set Background
		File chosenMap = new File(baseDir + "starsedit.png");
		try {
			bgImage = ImageIO.read(chosenMap);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// add a lame logo
		File logoImage = new File(baseDir + "logo.png");
		try {
			logo = ImageIO.read(logoImage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		repaint();
		this.setVisible(true);

	}

	public void resetLogo() {
		File logoImage = new File(baseDir + "logo.png");
		try {
			logo = ImageIO.read(logoImage);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		mainMenu.repaint();
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == singleplayer)
				GameGUI.getInstance().createMap(1);
			if (e.getSource() == multiplayer) {

				System.out.println("Multiplayer button clicked");
				TDServer server = new TDServer();
				TDClient client = new TDClient();
				// TODO Have different map selection based on picked map
				GameGUI.getInstance().mapSelection = 0;

				File logoImage = new File(baseDir + "waiting.png");
				try {
					logo = ImageIO.read(logoImage);
				} catch (IOException exc) {
					exc.printStackTrace();
				}
				// multiplayer.setVisible(false);
				mainMenu.repaint();

			}
		}
	}

	public static MainMenu getInstance() {
		if (mainMenu == null)
			mainMenu = new MainMenu();

		return mainMenu;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(bgImage, 0, 0, null);
		g.drawImage(logo, 260, 216, null);
	}
}
