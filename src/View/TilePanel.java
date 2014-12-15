package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import Attackers.Cannoneer;
import Attackers.Marine;
import Attackers.Scout;
import Maps.BeachBetrayal;
import Maps.BrokenPlainsPatrol;
import Maps.DesertUprising;
import Model.Attacker;
import Model.Map;
import Model.Ticker;
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
	public boolean display, display1;
//	private int wave = 0;
//	Timer img;
//	Timer waveTime;

	/**
	 * Constructs the TilePanel for use in the GameGUI
	 */
	private TilePanel() {
		this.setOpaque(false);
		this.setVisible(true);

		tileMap = DesertUprising.getInstance();
		display = false;
		display1 = false;
	}

	public void setMap(int selection) {
		switch (selection) {
		case 0:
			tileMap = DesertUprising.getInstance();
		
			break;
		case 1:
			tileMap = BrokenPlainsPatrol.getInstance();
		
			break;
		case 2:
			tileMap = BeachBetrayal.getInstance();
			
			break;
		default:
			tileMap = DesertUprising.getInstance();
			break;
		}
//		display = true;
//		repaint();
//		ActionListener imgDisplay = new ActionListener() {
//		      public void actionPerformed(ActionEvent evt) {
//		    	  display = false;
//		    	  repaint(); 
//		    	  for(int i = 0; i<15; i++) {
//		  			for(int k = 1; k<=2; k++) {
//		  				tileMap.getSpawnTile(k).addAttacker(
//		  				new Marine(tileMap.getSpawnTile(k)));
//		  				
//		  			}
//		  		}
//		    	  img.stop();
//		      }
//		  };
//		img =  new Timer(5000, imgDisplay);
//		img.start();
//		
//		
//		ActionListener waveStart = new ActionListener() {
//		      public void actionPerformed(ActionEvent evt) {
//		    	  setSpawn(wave);
//		      }
//		  };
//		waveTime =  new Timer(30000, waveStart);
//		waveTime.start();
		
		
	}

//	private void setSpawn(int w) {
//
//		wave++;
//		System.out.println("Spawn");
//
//		if(w<3)
//			for(int i = 0; i<15; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Marine(tileMap.getSpawnTile(k)));
//				}
//			}
//		else if(w==4) {
//			for(int i = 0; i<15; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Marine(tileMap.getSpawnTile(k)));
//				}
//			}
//			for(int i = 0; i<6; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Scout(tileMap.getSpawnTile(k)));
//				}
//			}
//		}
//		else if(w>4 && w<8) {
//
//			for(int i = 0; i<15+(w*2); i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Marine(tileMap.getSpawnTile(k)));
//				}
//			}
//			for(int i = 0; i<6+w; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Scout(tileMap.getSpawnTile(k)));
//				}
//			}
//
//		}
//
//		else if(w==8) {
//
//			for(int i = 0; i<15+w; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Marine(tileMap.getSpawnTile(k)));
//				}
//			}
//			for(int i = 0; i<6+w; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Scout(tileMap.getSpawnTile(k)));
//				}
//			}
//			for(int i = 0; i<2+w; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Cannoneer(tileMap.getSpawnTile(k)));
//				}
//			}
//
//		}
//
//
//
//		else if(w==9) {
//
//			for(int i = 0; i<15*w; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Marine(tileMap.getSpawnTile(k)));
//				}
//			}
//			for(int i = 0; i<6+w; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Scout(tileMap.getSpawnTile(k)));
//				}
//			}
//			for(int i = 0; i<2+w; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Cannoneer(tileMap.getSpawnTile(k)));
//				}
//			}
//
//		}
//
//		else if(w==10) {
//
//			for(int i = 0; i<15*w; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Marine(tileMap.getSpawnTile(k)));
//				}
//			}
//			for(int i = 0; i<6*w; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Scout(tileMap.getSpawnTile(k)));
//				}
//			}
//			for(int i = 0; i<2*w; i++) {
//				for(int k = 1; k<=2; k++) {
//					tileMap.getSpawnTile(k).addAttacker(
//							new Cannoneer(tileMap.getSpawnTile(k)));
//				}
//			}
//			waveTime.stop();
//		}
//		display1 = true;
//		repaint();
//		Ticker.getInstance().loopStop();
//	}
	
	

	public TilePanel reset() {
		// Player.getInstance().reset();
		tileMap = tileMap.reInit();
		return tilePanel;
	}

	public TilePanel reallyReset() {
		tilePanel = new TilePanel();
		return reset();
	}

	public static TilePanel getInstance() {
		if (tilePanel == null) {
			tilePanel = new TilePanel();
		}
		return tilePanel;
	}

	public void setMap(Map m) {
		if (m.mapImageName.equals("desertuprising.jpg")) {
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
		
		
		if (tileMap != null && tileMap.getGameBoard() != null) {
			
			if(display) {
				try {
					g2.drawImage(ImageIO.read(new File("imageFiles/GetReady.png")), getWidth()/2-235, getHeight()/2-41, null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(display1) {
				try {
					g2.drawImage(ImageIO.read(new File("imageFiles/Congrats.png")), getWidth()/2-235, getHeight()/2-41, null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
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

		if (circleX != -1 && circleY != -1) {
			g2.setColor(new Color(1, 1, 1, 0.2f));
			g2.fillOval(circleX - range / 3, circleY - range / 3, range, range);
		}
	}

	int circleX = -1;
	int circleY = -1;
	int range;

	public void setCirclePoints(int x, int y, int range) {
		circleX = x * 40 - 20;
		circleY = y * 40 - 20;
		this.range = range * 60; // 60 to account for both ways + half a tile
	}

	public Map getMap() {
		return tileMap;
	}

}
