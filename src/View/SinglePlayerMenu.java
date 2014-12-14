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

public class SinglePlayerMenu extends JPanel {

	private static SinglePlayerMenu singleMenu;
	Image bgImage;
	Image logo;
	private static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "imageFiles"
			+ System.getProperty("file.separator");
	JButton desertMap;
	JButton plainsMap;
	JButton beachMap;

	private SinglePlayerMenu() {

		desertMap = new JButton("Desert Uprising");
		plainsMap = new JButton("Single Player");
		beachMap = new JButton("Multi-Player");
		ButtonListener buttonListener = new ButtonListener();
		desertMap.addActionListener(buttonListener);
		plainsMap.addActionListener(buttonListener);
		beachMap.addActionListener(buttonListener);
		this.add(desertMap);
		this.add(plainsMap);
		this.add(beachMap);
		this.setBackground(Color.BLACK);

		// set Background
		File chosenMap = new File(baseDir + "starsedit.png");
		try {
			bgImage = ImageIO.read(chosenMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
		this.setVisible(true);

	}

	public static SinglePlayerMenu getInstance() {
		if (singleMenu == null)
			singleMenu = new SinglePlayerMenu();

		return singleMenu;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(bgImage, 0, 0, null);
		g.drawImage(logo, 260, 216, null);
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == desertMap)
				GameGUI.getInstance().createMap(0);
			if (e.getSource() == plainsMap)
				GameGUI.getInstance().createMap(1);
			if (e.getSource() == beachMap) {
				GameGUI.getInstance().createMap(2);
			}
			singleMenu.repaint();
		}

	}

}
