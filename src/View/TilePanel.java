package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

import Maps.DesertUprising;
import Model.Attacker;
import Model.Map;
import Model.Tile;

/**
 * A clear panel that is used to display Structures and Attackers in the game
 * 
 * @author Marcos
 *
 */

public class TilePanel extends JPanel {
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	private static TilePanel tilePanel;
	private Map tileMap;

	/**
	 * Constructs the TilePanel for use in the GameGUI
	 */
	private TilePanel() {
		//this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); already set
		this.setOpaque(false);
		this.setVisible(true);
		tileMap = new DesertUprising();
	}

	public static TilePanel getInstance() {
		if (tilePanel == null) {
			tilePanel = new TilePanel();
		}
		return tilePanel;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//Draw each structure and attacker from every tile
		Iterator<Vector<Tile>> vectorItr = tileMap.getGameBoard().iterator();
		while(vectorItr.hasNext()){
			Vector<Tile> currVector = vectorItr.next();
			Iterator<Tile> tileItr = currVector.iterator();
			while(tileItr.hasNext()){
				Tile currTile = tileItr.next();
				if(currTile.getStructure() != null){
					currTile.getStructure().draw(g2);
				}
				
				Iterator<Attacker> attackerItr = currTile.getAttackers().iterator();
				while(attackerItr.hasNext()){
					Attacker currAttacker = attackerItr.next();
					currAttacker.draw(g2);
				}
			}
		}
		
	}
	
	public Map getMap(){
		return tileMap;
	}
}
