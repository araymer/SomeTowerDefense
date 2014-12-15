package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A panel that contains a static image of the current map
 * 
 * @author Team Something
 *
 */
@SuppressWarnings("serial")
public class MapPanel extends JPanel {
	@SuppressWarnings("unused")
	private final int MAP_WIDTH = 800;
	private static Image image;
	private static MapPanel mapPanel;
	private String fileName;
	// private JMenuBar menu = new JMenuBar();

	private static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "imageFiles"
			+ System.getProperty("file.separator");

	/**
	 * Constructs a MapPanel for use in the GameGUI
	 * 
	 * @param filename
	 *            -the file name of the map image from the imageFiles folder
	 * 
	 */
	private MapPanel() {
		this.setSize(800, 622);

	}

	public MapPanel reset() {
		mapPanel = new MapPanel();
		return mapPanel;
	}

	public void setMap(String filename) {
		setFileName(fileName);
		File chosenMap = new File(baseDir + filename);
		try {
			image = ImageIO.read(chosenMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public static MapPanel getInstance() {
		if (mapPanel == null) {
			mapPanel = new MapPanel();
		}
		return mapPanel;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(image, 0, 0, 800, 600, null);
	}
}
