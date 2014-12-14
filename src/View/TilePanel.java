package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Vector;

import javax.swing.JPanel;

import Attackers.Cannoneer;
import Attackers.Marine;
import Attackers.Scout;
import Maps.BeachBetrayal;
import Maps.BrokenPlainsPatrol;
import Maps.DesertUprising;
import Model.Attacker;
import Model.Map;
import Model.Player;
import Model.Tile;

/**
 * A clear panel that is used to display Structures and Attackers in the game
 * 
 * @author Team Something
 *
 */

@SuppressWarnings("serial")
public class TilePanel extends JPanel implements Serializable {
	private static TilePanel tilePanel;
	public Map tileMap;

	/**
	 * Constructs the TilePanel for use in the GameGUI
	 */
	private TilePanel() {
		this.setOpaque(false);
		this.setVisible(true);

		tileMap = DesertUprising.getInstance();

		
	}

	public void setMap(int selection) {
		switch (selection) {
		case 0:
			tileMap = DesertUprising.getInstance();
			setSpawn();
			break;
		case 1:
			tileMap = BrokenPlainsPatrol.getInstance();
			setSpawn();
			break;
		case 2:
			tileMap = BeachBetrayal.getInstance();
			setSpawn();
			break;
		}
		tileMap.getSpawnTile(1).addAttacker(
				new Cannoneer(tileMap.getSpawnTile(1)));
		
		 tileMap.getSpawnTile(1).addAttacker(
				new Scout(tileMap.getSpawnTile(1)));

		 tileMap.getSpawnTile(1)
		 .addAttacker(new Marine(tileMap.getSpawnTile(1)));
	}

	private void setSpawn() {
		tileMap.getSpawnTile(1).addAttacker(
				new Cannoneer(tileMap.getSpawnTile(1)));
		tileMap.getSpawnTile(1).addAttacker(
				new Cannoneer(tileMap.getSpawnTile(1)));
	}

	public TilePanel reset() {
		Player.getInstance().reset();
		tileMap = tileMap.reInit();
		return tilePanel;
	}

	public static TilePanel getInstance() {
		if (tilePanel == null) {
			tilePanel = new TilePanel();
		}
		return tilePanel;
	}

	public void setMap(Map m) {
		if(m.mapImageName.equals("desertuprising.jpg")){
			DesertUprising.setMap(m);
		}
		tileMap = m;
		tileMap.setGameBoard(m.getGameBoard());
		tilePanel = new TilePanel();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if( tileMap != null && tileMap.getGameBoard() != null){
			try {
				
				for (Vector<Tile> vec : tileMap.getGameBoard()) {
					for (Tile tile : vec) {
						if (tile.getStructure() != null) {
							tile.getStructure().draw(g2);
						}
						for (Attacker attacker : tile.getAttackers()) {
							attacker.draw(g2);
						}
					}
				}
			} catch (ConcurrentModificationException e) {
				System.out
						.println("TilePanel: repaint error, ConcurrentModificationException\n");
				// +
				// "this happens when too many objects to repaint. May be able to fix by having only\n"
				// +
				// "repaint scan the gameBoard and everything else uses repaint to scan and change anything");
				// Just have this catch do nothing if you can't really tell any
				// changes when repainting.
			}
		}
		

	}

	public Map getMap() {
		return tileMap;
	}

}
