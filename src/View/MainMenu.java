package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

	private static MainMenu mainMenu;
	Image bgImage;
	Image logo;
	private static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "imageFiles"
			+ System.getProperty("file.separator");

	private MainMenu() {

		JButton desertMap = new JButton("Desert Uprising");
		desertMap.addActionListener(new ButtonListener());
		this.add(desertMap);
		this.setBackground(Color.BLACK);

		// set Background
		File chosenMap = new File(baseDir + "stars.png");
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

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GameGUI.getInstance().createMap(0);

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
		g.drawImage(logo, 119, 216, null);
	}
}
