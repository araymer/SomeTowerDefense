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

	/**
	 * Resets the MapPanel
	 * 
	 * @author Team Something
	 *
	 */
	public MapPanel reset() {
		mapPanel = new MapPanel();
		return mapPanel;
	}

	/**
	 * Sets the image to be used in the game.
	 * 
	 * @author Team Something
	 *
	 * @param filename
	 *            = name of file to be used as map background
	 */
	public void setMap(String filename) {
		setFileName(fileName);
		File chosenMap = new File(baseDir + filename);
		try {
			image = ImageIO.read(chosenMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns filename of background image
	 * 
	 * @author Team Something
	 *
	 * @return String = name of background image
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets filename of background image
	 * 
	 * @author Team Something
	 *
	 * @param fileName
	 *            = file name of background
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Returns Singleton instance of MapPanel
	 * 
	 * @author Team Something
	 *
	 * @return MapPanel = Singleton instance of MapPanel
	 */
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
