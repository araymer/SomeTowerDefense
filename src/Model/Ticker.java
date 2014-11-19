package Model;

import View.*;

/**
 * This class controls all timing in the game, all other objects 
 * use the same instance. Singleton Pattern.
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
					update();
					lastUpdateTime += timeBetweenFrames;
					updateCount++;

					if (now - lastUpdateTime > timeBetweenFrames)
						lastUpdateTime = now - timeBetweenFrames;

					// interpolation for visually smooth movement
					float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / timeBetweenFrames));
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

	private void drawGame() {
		TilePanel.getInstance().repaint();
		ResourcePanel.getInstance().repaint();
		//BulletTravel.getInstance().repaint(); //uncomment after implementation
	}

	private void update(float interpolation) {
		// TODO calculate everything's new position
		// add in information for structures and towers for
		// position, direction and last drawn image (so it actually animates)
		// Interpolation will go into the draw methods that are called here.
		
		

	}

	public void loopStart() {
		isRunning = true;
	}

	public void loopStop() {
		isRunning = false;
	}
	
	/**
	 * Returns instance of this class.
	 * @return Ticker
	 */
	public static Ticker getInstance() {
		if(ticker == null)
			ticker = new Ticker();
		return ticker;
			}
}
