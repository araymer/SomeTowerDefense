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
 * @author Marcos
 *
 */
public class MapPanel extends JPanel{
	private final int MAP_WIDTH = 800;
	private final int MAP_HEIGHT = 600;
	private static Image image;
	
	private static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "imageFiles"
			+ System.getProperty("file.separator");
	
	/**
	 * Constructs a MapPanel for use in the GameGUI
	 * 
	 * @param filename 
	 * 				-the file name of the map image from the imageFiles folder
	 * 
	 */
	public MapPanel(String filename){
		File chosenMap = new File(baseDir + filename);
		try {
			image = ImageIO.read(chosenMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setSize(MAP_WIDTH, MAP_HEIGHT);
		this.setVisible(true);
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(image, 0, 0, MAP_WIDTH, MAP_HEIGHT, null);
	}
}
