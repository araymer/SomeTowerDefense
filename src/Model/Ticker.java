package Model;

import java.util.LinkedList;
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
	private final double fps = 30.0;
	private final double timeBetweenFrames = 1000000000 / fps;
	private final int maxUpdatesBetweenRenders = 5;
	private double lastUpdateTime = System.nanoTime();
	private double lastRenderTime = System.nanoTime();
	private int updateCount = 0;
	private double now = System.nanoTime();
	private static Ticker ticker;
	
	//private MasterList masterList;

	// many objects will need to access the same timer
	private Ticker() {
		isRunning = true;
		paused = false;
		//masterList = MasterList.getInstance();
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

					update();
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
		// TODO FIX THIS
		// TODO calculate everything's new position
		// add in information for structures and towers for
		// position, direction and last drawn image (so it actually animates)
		// TODO: Possibly incorporate this method into drawGame and deprecate?

		// for (int i = 0; i < TilePanel.getInstance().tileMap.gameBoard.size();
		// i++) {
		// for (int p = 0; p < TilePanel.getInstance().tileMap.gameBoard
		// .get(i).size(); p++) {
		// for (int r = 0; r < TilePanel.getInstance().tileMap.gameBoard
		// .get(i).get(p).getAttackers().size(); r++)
		// TilePanel.getInstance().tileMap.gameBoard.get(i).get(p)
		// .getAttackers().get(r).move();
		// }
		//
		// }
		// for (int i = 0; i < TilePanel.getInstance().tileMap.gameBoard.size();
		// i++) {
		// for (int p = 0; p <
		// TilePanel.getInstance().tileMap.gameBoard.get(i).size(); p++) {
		// for (int r = 0; r <
		// TilePanel.getInstance().tileMap.gameBoard.get(i).get(p).getAttackers().size();
		// r++){
		// if(TilePanel.getInstance().tileMap.gameBoard.get(i).get(p).getStructure()
		// != null)
		// TilePanel.getInstance().tileMap.gameBoard.get(i).get(p).getStructure().update();
		// }
		//
		// }
		//
		// }
		
		//Had to add this to avoid ConcurrentModificationException
		//Checking if any attackers/structures need to be removed or moved
		LinkedList<Attacker> attackerRemoveList = new LinkedList<Attacker>();
		LinkedList<Attacker> atkrMoveList = new LinkedList<Attacker>();
		LinkedList<Structure> structureRemoveList = new LinkedList<Structure>();
		try{
			
			//Check for dead structures and attackers
			for(Vector<Tile> vec: TilePanel.getInstance().tileMap.getGameBoard()){
				for(Tile tile: vec){
					if(tile.getStructure() != null){
						if(tile.getStructure().isFinished()){
							structureRemoveList.add(tile.getStructure());
						}
					}
					for(Attacker attacker: tile.getAttackers()){
						if(attacker.isFinished()){
							attackerRemoveList.add(attacker);
						}else if(attacker.needsToMove){
							atkrMoveList.add(attacker);
						}
					}
				}
			}
			
			
			//Remove dead structures and attackers
			for(Vector<Tile> vec: TilePanel.getInstance().tileMap.getGameBoard()){
				for(Tile tile: vec){
					for(Attacker attacker: attackerRemoveList){
						tile.getAttackers().remove(attacker);
					}
					for(Structure structure: structureRemoveList){
						if(tile.getStructure() == structure){
							tile.removeStructure();
						}
					}
				}
			}
			
			
			//Move attackers
			for(Attacker attacker: atkrMoveList){
				//iterate through whole map and remove these
				for(Vector<Tile> vec: TilePanel.getInstance().tileMap.getGameBoard()){
					for(Tile tile: vec){
						tile.getAttackers().remove(attacker);
					}
				}
				
//				nextTile.getAttackers().add(attacker);
//				attacker.setLoc(nextTile);
				
				attacker.location.getNextTile().getAttackers().add(attacker);
				attacker.setLoc(attacker.location.getNextTile());;
				attacker.needsToMove = false;
			}
			
			
		}catch(Exception e){
			System.out.println("removing an enemy threw exception");
			e.printStackTrace();
		}
		
		//Update all structures and enemies
		for(Vector<Tile> vec: TilePanel.getInstance().tileMap.getGameBoard()){
			for(Tile tile: vec){
				if(tile.getStructure() != null){
					tile.getStructure().update();
				}
				for(Attacker attacker: tile.getAttackers()){
					attacker.update();
				}
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
