package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

import Maps.DesertUprising;
import Model.Attacker;
import Model.Drawable;
import Model.Map;
import Model.MasterList;
import Model.Tile;

/**
 * A clear panel that is used to display Structures and Attackers in the game
 * 
 * @author Team Something
 *
 */

public class TilePanel extends JPanel {
	private static TilePanel tilePanel;
	public Map tileMap;
	MasterList masterList;

	/**
	 * Constructs the TilePanel for use in the GameGUI
	 */
	private TilePanel() {
		//this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); already set
		this.setOpaque(false);
		this.setVisible(true);
		tileMap = new DesertUprising();
		masterList = MasterList.getInstance();
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
		
//		masterList.clear();
		//Draw each structure and attacker from every tile
//		Iterator<Vector<Tile>> vectorItr = tileMap.getGameBoard().iterator();
//		while(vectorItr.hasNext()){
//			Vector<Tile> currVector = vectorItr.next();
//			Iterator<Tile> tileItr = currVector.iterator();
//			while(tileItr.hasNext()){
//				Tile currTile = tileItr.next();
//				if(currTile.getStructure() != null){
//					currTile.getStructure().draw(g2);
//				}
//				
//				Iterator<Attacker> attackerItr = currTile.getAttackers().iterator();
//				while(attackerItr.hasNext()){
//					Attacker currAttacker = attackerItr.next();
//					currAttacker.draw(g2);
//				}
//			}
//		}
		try{
		for(Vector<Tile> vec: tileMap.getGameBoard()){
			for(Tile tile: vec){
				if(tile.getStructure() != null){
					tile.getStructure().draw(g2);
				}
				for(Attacker attacker: tile.getAttackers()){
					attacker.draw(g2);
				}
			}
		}
		}catch(Exception e){
			System.out.println("repaint error");
		}
//		synchronized(masterList){
//			Iterator<Drawable> itr = masterList.iterator();
//			while(itr.hasNext()){
//				System.out.println("drawing");
//				itr.next().draw(g2);
//			}
//		}
		
		
		
	}
	
	public Map getMap(){
		return tileMap;
	}
}
