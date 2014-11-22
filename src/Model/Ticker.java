package Model;

import java.util.Iterator;
import java.util.Vector;

import View.GameGUI;
import View.TilePanel;

/**
 * This class controls all timing in the game, all other objects use the same
 * instance. Singleton Pattern.
 * 
 * @author Team Something
 */
public class Ticker implements Runnable {

	private boolean isRunning;
	private boolean paused;
	private final double fps = 20.0;
	private final double timeBetweenFrames = 1000000000 / fps;
	private final int maxUpdatesBetweenRenders = 5;
	private double lastUpdateTime = System.nanoTime();
	private double lastRenderTime = System.nanoTime();
	private int updateCount = 0;
	private double now = System.nanoTime();
	private static Ticker ticker;

	// many objects will need to access the same timer
	private Ticker() {
		isRunning = true;
		paused = false;
	}

	// Only run this in another Thread!
	@Override
	public void run() {
		while (isRunning) {
			now = System.nanoTime();
			updateCount = 0;

			if (!paused) {
				while (now - lastUpdateTime > timeBetweenFrames
						&& updateCount < maxUpdatesBetweenRenders) {
					
					//update();
					lastUpdateTime += timeBetweenFrames;
					updateCount++;

					if (now - lastUpdateTime > timeBetweenFrames)
						lastUpdateTime = now - timeBetweenFrames;

					// interpolation for visually smooth movement
					float interpolation = Math
							.min(1.0f,
									(float) ((now - lastUpdateTime) / timeBetweenFrames));
					drawGame(interpolation);
					lastRenderTime = now;

					// Can add thread.yield to prevent CPU hogging
					while (now - lastUpdateTime < timeBetweenFrames) {
						Thread.yield();

						try {
							Thread.sleep(1);
						} catch (Exception e) {
						}

						now = System.nanoTime();
					}
				}
			}
		}
	}

	private void drawGame(float interpolation) {
		// Iterate through all game objects and call their draw methods with
		// interpolation

		GameGUI.getInstance().repaint(); // This is temporary, we'll want to
											// replace this with
											// some way to feed interpolation

	}

	private void update() {
		//TODO FIX THIS
		// TODO calculate everything's new position
		// add in information for structures and towers for
		// position, direction and last drawn image (so it actually animates)
		// TODO: Possibly incorporate this method into drawGame and deprecate?

		
//		for (int i = 0; i < TilePanel.getInstance().tileMap.gameBoard.size(); i++) {
//			for (int p = 0; p < TilePanel.getInstance().tileMap.gameBoard
//					.get(i).size(); p++) {
//				for (int r = 0; r < TilePanel.getInstance().tileMap.gameBoard
//						.get(i).get(p).getAttackers().size(); r++)
//					TilePanel.getInstance().tileMap.gameBoard.get(i).get(p)
//							.getAttackers().get(r).move();
//			}
//		
//		}
//		for (int i = 0; i < TilePanel.getInstance().tileMap.gameBoard.size(); i++) {
//			for (int p = 0; p < TilePanel.getInstance().tileMap.gameBoard.get(i).size(); p++) {
//				for (int r = 0; r < TilePanel.getInstance().tileMap.gameBoard.get(i).get(p).getAttackers().size(); r++){
//					if(TilePanel.getInstance().tileMap.gameBoard.get(i).get(p).getStructure() != null)
//					TilePanel.getInstance().tileMap.gameBoard.get(i).get(p).getStructure().update();
//				}
//					
//			}
//		
//		}
		
		//Draw each structure and attacker from every tile
				Iterator<Vector<Tile>> vectorItr = TilePanel.getInstance().tileMap.getGameBoard().iterator();
				while(vectorItr.hasNext()){
					Vector<Tile> currVector = vectorItr.next();
					Iterator<Tile> tileItr = currVector.iterator();
					while(tileItr.hasNext()){
						Tile currTile = tileItr.next();
						if(currTile.getStructure() != null){
							currTile.getStructure().update();
						}
						
//						Iterator<Attacker> attackerItr = currTile.getAttackers().iterator();
//						while(attackerItr.hasNext()){
//							Attacker currAttacker = attackerItr.next();
//							currAttacker.move();
//						}
					}
				}
	}
	

	public void loopStart() {
		isRunning = true;
	}

	public void loopStop() {
		isRunning = false;
	}

	/**
	 * Returns instance of this class.
	 * 
	 * @return Ticker
	 */
	public static Ticker getInstance() {
		if (ticker == null)
			ticker = new Ticker();
		return ticker;
	}
}
