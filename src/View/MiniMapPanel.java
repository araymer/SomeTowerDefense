package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import Model.Base;
import Model.Tile;

/**
 * This panel displays the other player's structure and enemy locations
 * 
 * @author Team Something
 *
 */
public class MiniMapPanel extends JPanel{
	
	private JTextArea info;
	private Vector<Vector<Tile>> otherGameMap;
	private static final int MINI_MAP_HEIGHT = 225;
	private static final int MINI_MAP_WIDTH = 300;
	
	public MiniMapPanel(){
		this.setBackground(Color.ORANGE);
		this.setLayout(new BorderLayout());
		info = new JTextArea();
		info.setSize(300, 65);
		info.setEditable(false);
		this.add(info, BorderLayout.SOUTH);
		//info.setLocation(87, 225);
		info.setPreferredSize(new Dimension(300, 65));
		info.setText("Username's info:\nStructures placed: 23\nEnemies on map: 5\nResources available: 700");
		this.setVisible(true);
	}
	
	public void updateMap(Vector<Vector<Tile>> gameMap){
		otherGameMap = gameMap;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(0, 0, MINI_MAP_WIDTH, MINI_MAP_HEIGHT);
		if(otherGameMap != null){
			int width = otherGameMap.size();
			int height = otherGameMap.get(0).size();
			int tileHeight = MINI_MAP_HEIGHT/height;
			int tileWidth = MINI_MAP_WIDTH/width;
			
			System.out.println("height: " + height);
			System.out.println("width: " + width);
			for(int r = 0; r < height; r++){
				for(int c = 0; c < width; c++){
					Tile curr = otherGameMap.get(c).get(r);
					if(curr != null){
						if(curr.getStructure() != null){
							if(curr.getStructure() instanceof Base){
								//Draw base in green
								g2.setColor(Color.GREEN);
							}else{
								//Draw non-base structures in blue
								g2.setColor(Color.BLUE);
							}
							g2.fillRect(tileWidth * c, tileHeight * r, tileWidth, tileHeight);
						}
						if(curr.getAttackers() != null && curr.getAttackers().size() > 0){
							//Draw enemies in red
							g2.setColor(Color.RED);
							g2.fillOval(tileWidth * c, tileHeight * r, tileWidth, tileHeight);
						}
					}
				}
			}
		}
//		//Draw enemies in red
//		g2.setColor(Color.RED);
//		g2.fillOval(260,210,10,10);
//		g2.fillOval(124, 32, 10, 10);
//		
//		//Draw non-base structures in blue
//		g2.setColor(Color.BLUE);
//		g2.fillRect(22, 130, 10, 10);
//		g2.fillRect(212, 100, 10, 10);
//		
//		//Draw base in green
//		g2.setColor(Color.GREEN);
//		g2.fillRect(18, 100, 10, 10);
	}
}
