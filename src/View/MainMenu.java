package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.GameController;
import Controller.TDClient;
import Controller.TDServer;

public class MainMenu extends JPanel {

	/**
	 * MainMenu that displays on startup
	 * 
	 * @author Team Something
	 *
	 */
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

	BufferedImage map;
	int mapSelected;

	/**
	 * Private constructor for MainMenu
	 * 
	 * @author Team Something
	 *
	 */
	private MainMenu() {

		this.setLayout(new BorderLayout());
		// desertMap = new JButton("Desert Uprising");
		singleplayer = new JButton("Single Player");
		multiplayer = new JButton("Multi-Player");
		load = new JButton("Load Last Game");
		beachButton = new JButton("Beach Betrayal");
		plainsButton = new JButton("Broken Plains Patrol");
		desertButton = new JButton("Desert Uprising");
		chooseLabel = new JLabel(
				"Choose A Map First Then Select Single/Multiplayer");
		ButtonListener buttonListener = new ButtonListener();
		// desertMap.addActionListener(buttonListener);
		singleplayer.addActionListener(buttonListener);
		multiplayer.addActionListener(buttonListener);
		load.addActionListener(buttonListener);
		beachButton.addActionListener(buttonListener);
		plainsButton.addActionListener(buttonListener);
		desertButton.addActionListener(buttonListener);
		// this.add(desertMap);
		JPanel startPanel = new JPanel();
		startPanel.add(singleplayer);
		startPanel.add(multiplayer);
		startPanel.add(load);
		startPanel.setBackground(new Color(0, 0, 0));
		this.add(startPanel, BorderLayout.NORTH);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 2));

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
		load.setVisible(false);
		singleplayer.setVisible(false);
		multiplayer.setVisible(false);
		load.setVisible(true);
		load.setFocusable(false);

	}

	/**
	 * Resets the game's logo.
	 * 
	 * @author Team Something
	 */
	public void resetLogo() {
		File logoImage = new File(baseDir + "logo.png");
		try {
			logo = ImageIO.read(logoImage);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		mainMenu.repaint();
	}

	/**
	 * Listens for the user to click on the game selection buttons
	 * 
	 * @author Team Something
	 *
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			singleplayer.setVisible(true);
			multiplayer.setVisible(true);
			if (e.getSource() == singleplayer)
				GameGUI.getInstance().createMap(mapSelected);

			if (e.getSource() == multiplayer) {
				System.out.println("Multiplayer button clicked");
				TDServer server = new TDServer();
				TDClient client = new TDClient();
				// TODO Have different map selection based on picked map
				GameGUI.getInstance().mapSelection = mapSelected;

				File logoImage = new File(baseDir + "waiting.png");
				try {
					logo = ImageIO.read(logoImage);
				} catch (IOException exc) {
					exc.printStackTrace();
				}
				// multiplayer.setVisible(false);
				mainMenu.repaint();
			}
			if (e.getSource() == load) {
				GameController.getInstance().loadData();
			}
			if (e.getSource() == beachButton) {

				try {
					map = ImageIO
							.read(new File("imageFiles/BeachBetrayal.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();
				mapSelected = 2;
			}
			if (e.getSource() == plainsButton) {

				try {
					map = ImageIO.read(new File(
							"imageFiles/BrokenPlainsPatrol.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();
				mapSelected = 1;
			}
			if (e.getSource() == desertButton) {
				try {
					map = ImageIO
							.read(new File("imageFiles/desertuprising.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();
				mapSelected = 0;
			}

		}
	}

	/**
	 * Returns Singleton instance of MainMenu
	 * 
	 * @author Team Something
	 *
	 * @return MainMenu = Singleton instance of MainMenu
	 */
	public static MainMenu getInstance() {
		if (mainMenu == null)
			mainMenu = new MainMenu();

		return mainMenu;
	}

	/**
	 * Reinstantiates MainMenu
	 * 
	 * @author Team Something
	 *
	 */
	public void reset() {
		mainMenu = new MainMenu();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		at.scale(0.5, 0.5);
		at.translate(getWidth() / 2 + 150, getHeight() / 2 + 200);

		g.drawImage(bgImage, 0, 0, null);
		g.drawImage(logo, 266, 100, null);
		g2.drawImage(map, at, null);
	}
}
