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
	private double timeBetweenFrames = 1000000000 / fps;
	private final int maxUpdatesBetweenRenders = 5;
	private double lastUpdateTime = System.nanoTime();
	private double lastRenderTime = System.nanoTime();
	private int updateCount = 0;
	private double now = System.nanoTime();
	private static Ticker ticker;
	private int tickCount;
	public int numOfAttackersDead = 0;
	public Wave waves;

	private Vector<Attacker> attackerRemoveList = new Vector<Attacker>();
	private Vector<Attacker> atkrMoveList = new Vector<Attacker>();
	private Vector<Structure> structureRemoveList = new Vector<Structure>();

	// many objects will need to access the same timer
	private Ticker() {
		waves = new Wave();
		isRunning = true;
		paused = false;
		tickCount = 0;
		// masterList = MasterList.getInstance();
	}

	// Only run this in another Thread!
	@Override
	public void run() {
		while (GameGUI.getInstance().isRunning) {
			while (GameGUI.getInstance().isRunning && isRunning) {
				now = System.nanoTime();
				updateCount = 0;

				if (!paused) {
					while (GameGUI.getInstance().isRunning
							&& now - lastUpdateTime > timeBetweenFrames
							&& updateCount < maxUpdatesBetweenRenders) {

						update();
						lastUpdateTime += timeBetweenFrames;
						updateCount++;

						if (now - lastUpdateTime > timeBetweenFrames)
							lastUpdateTime = now - timeBetweenFrames;

						// interpolation for visually smooth movement
						double interpolation = Math
								.min(1.0f,
										(double) ((now - lastUpdateTime) / timeBetweenFrames));
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
							tickCount++;
						}
					}
				}
			}
		}
		System.out.println("Ticker: this thread stopped");
	}

	boolean fast = false;
	/**
	 * Changes speed of game for "fast mode" play.
	 * 
	 */
	public void changeSpeed() {
		if (!fast) {
			timeBetweenFrames = timeBetweenFrames = 500000000 / fps;
			fast = true;
		} else {
			timeBetweenFrames = timeBetweenFrames = 1000000000 / fps;
			fast = false;
		}
		// 1/2th the time between frames
	}
	/**
	 * Calls GameGUI's repaint method to draw the game each tick.
	 * @param interpolation - deprecated
	 */
	private void drawGame(double interpolation) {
		// Iterate through all game objects and call their draw methods with
		// interpolation

		GameGUI.getInstance().repaint(interpolation); // This is temporary,
														// we'll want to
		// replace this with
		// some way to feed interpolation

	}
	/**
	 * @returns tickCount - number of ticks. Overflow imminent. Modulus arithmetic recommended
	 */
	public int getTicks() {
		return tickCount;
	}

	/**
	 * Updates each attacker and structure and removes or moves them if needed
	 */
	private void update() {
		

		// Had to add this to avoid ConcurrentModificationException
		// Checking if any attackers/structures need to be removed or moved
		attackerRemoveList.clear();
		atkrMoveList.clear();
		structureRemoveList.clear();
		int attackersAlive = 0;
		try {

			// Check for dead structures and attackers
			for (Vector<Tile> vec : TilePanel.getInstance().tileMap
					.getGameBoard()) {
				for (Tile tile : vec) {
					if (tile.getStructure() != null) {
						if (tile.getStructure().isFinished()) {
							structureRemoveList.add(tile.getStructure());
						}
					}
					for (Attacker attacker : tile.getAttackers()) {
						attackersAlive ++;
						if (attacker.isFinished()) {
							attackerRemoveList.add(attacker);
						} else if (attacker.needsToMove) {
							atkrMoveList.add(attacker);
						}
					}
				}
			}

			// Remove dead structures and attackers
			for (Vector<Tile> vec : TilePanel.getInstance().tileMap
					.getGameBoard()) {
				for (Tile tile : vec) {
					for (Attacker attacker : attackerRemoveList) {
						tile.getAttackers().remove(attacker);
					}
					for (Structure structure : structureRemoveList) {
						if (tile.getStructure() == structure) {
							tile.removeStructure();
						}
					}
				}
			}

			// Move attackers
			for (Attacker attacker : atkrMoveList) {
				// iterate through whole map and remove these
				for (Vector<Tile> vec : TilePanel.getInstance().tileMap
						.getGameBoard()) {
					for (Tile tile : vec) {
						tile.getAttackers().remove(attacker);
					}
				}

				attacker.location.getNextTile().getAttackers().add(attacker);
				attacker.setLoc(attacker.location.getNextTile());
				
				attacker.needsToMove = false;
			}

		} catch (Exception e) {
			System.out.println("removing an enemy threw exception");
			e.printStackTrace();
		}
		
		//Attempt to spawn attackers
		if(attackersAlive > 0){
			waves.setEnemiesAlive(true);
		}else{
			waves.setEnemiesAlive(false);
		}
		waves.attemptSpawn(tickCount);

		// Update all structures and enemies
		for (Vector<Tile> vec : TilePanel.getInstance().tileMap.getGameBoard()) {
			for (Tile tile : vec) {
				if (tile.getStructure() != null) {
					tile.getStructure().update();
				}
				for (Attacker attacker : tile.getAttackers()) {
					attacker.update();
				}
			}
		}
		
		

		numOfAttackersDead += attackerRemoveList.size();
		// Update other players minimap
		if (GameGUI.getInstance().isMultiplayer) {
			GameGUI.getInstance().getClient().sendMiniMap();
		}
	}
	/**
	 * sets isRunning to true so game logic starts within the game loop.
	 */
	public void loopStart() {
		System.out.println("Resuming loop");
		isRunning = true;
	}
	/**
	 * sets isRunning to false to stop game loop.
	 */
	public void loopStop() {
		System.out.println("Stopping loop");
		isRunning = false;
	}
	/**
	 * 
	 * @return isRunning
	 */
	public boolean running() {
		return isRunning;
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
	/**
	 * Re-instantiates Ticker
	 */
	public void reset() {
		ticker = new Ticker();
	}

}
