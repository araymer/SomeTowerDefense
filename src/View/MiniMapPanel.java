package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Model.GameMapSkeleton;
import Model.TileSkeleton;

/**
 * This panel displays the other player's structure and enemy locations
 * 
 * @author Team Something
 *
 */
public class MiniMapPanel extends JPanel {

	// private JTextArea info;
	private GameMapSkeleton otherGameMap;
	private static final int MINI_MAP_HEIGHT = 150;
	private static final int MINI_MAP_WIDTH = 200;
	private MultiplayerInfoPanel infoPanel;
	private int enemyNum;

	/**
	 * Constructor for MiniMapPanel
	 * 
	 * @author Team Something
	 *
	 */
	public MiniMapPanel() {
		infoPanel = new MultiplayerInfoPanel();
		this.setBackground(Color.ORANGE);
		this.setLayout(new BorderLayout());
		this.add(infoPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	/**
	 * Updates the minimap display
	 * 
	 * @author Team Something
	 *
	 * @param gameMap
	 *            = Skeleton to build minimap out of
	 * @param totalResources
	 *            = interger of players resources
	 * @param enemieskilled
	 *            = total enemies killed between players
	 */
	public void updateMap(GameMapSkeleton gameMap, int totalResources,
			int enemiesKilled) {
		otherGameMap = gameMap;
		repaint();
		infoPanel.updateInfo(totalResources, enemiesKilled, enemyNum);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int amountOfEnemies = 0;
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 0, MINI_MAP_WIDTH, MINI_MAP_HEIGHT);

		if (otherGameMap != null) {
			enemyNum = 0;
			int width = otherGameMap.skeleton.size();
			int height = otherGameMap.skeleton.get(0).size();
			int tileHeight = MINI_MAP_HEIGHT / height;
			int tileWidth = MINI_MAP_WIDTH / width;

			for (int r = 0; r < height; r++) {
				for (int c = 0; c < width; c++) {
					TileSkeleton curr = otherGameMap.skeleton.get(c).get(r);
					if (curr.hasPath) {
						g2.setColor(Color.DARK_GRAY);
						g2.fillRect(tileWidth * c, tileHeight * r, tileWidth,
								tileHeight);
					}
					if (curr.hasStructure) {
						if (curr.hasBase) {
							// Draw base in green
							g2.setColor(Color.GREEN);
						} else {
							// Draw non-base structures in blue
							g2.setColor(Color.BLUE);
						}
						g2.fillRect(tileWidth * c, tileHeight * r, tileWidth,
								tileHeight);
					}
					if (curr.attackerNum > 0) {
						// Draw enemies in red
						g2.setColor(Color.RED);
						g2.fillOval(tileWidth * c, tileHeight * r, tileWidth,
								tileHeight);
						enemyNum += curr.attackerNum;
					}
				}
			}
		} else {
			System.out.println("MiniMapPanel: gameMap received was null");
		}

		// if(otherGameMap != null){
		// enemyNum = 0;
		// int width = otherGameMap.size();
		// int height = otherGameMap.get(0).size();
		// int tileHeight = MINI_MAP_HEIGHT/height;
		// int tileWidth = MINI_MAP_WIDTH/width;
		//
		// for(int r = 0; r < height; r++){
		// for(int c = 0; c < width; c++){
		// Tile curr = otherGameMap.get(c).get(r);
		// if(curr != null){
		// if(curr.getMove()){
		// g2.setColor(Color.DARK_GRAY);
		// g2.fillRect(tileWidth * c, tileHeight * r, tileWidth, tileHeight);
		// }
		// if(curr.getStructure() != null){
		// if(curr.getStructure() instanceof Base){
		// //Draw base in green
		// g2.setColor(Color.GREEN);
		// }else{
		// //Draw non-base structures in blue
		// g2.setColor(Color.BLUE);
		// }
		// g2.fillRect(tileWidth * c, tileHeight * r, tileWidth, tileHeight);
		// }
		// if(curr.getAttackers() != null && curr.getAttackers().size() > 0){
		// //Draw enemies in red
		// g2.setColor(Color.RED);
		// g2.fillOval(tileWidth * c, tileHeight * r, tileWidth, tileHeight);
		// enemyNum += curr.getAttackers().size();
		// }
		// }
		// }
		// }
		// }else{
		// System.out.println("MiniMapPanel: gameMap received was null");
		// }
		otherGameMap = null;
	}
}
