package Model;

import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * Determines is something is able to be drawn
 *
 * @author TeamSomething
 */
public abstract class Drawable implements Serializable {
	/**
	 * Draws the object
	 *
	 * @author TeamSomething
	 * @param g2
	 *            = Graphics
	 */
	public abstract void draw(Graphics2D g2);

	/**
	 * Updates the object
	 *
	 * @author TeamSomething
	 */
	public abstract void update();

	/**
	 * Boolean value detemining if the object is dead
	 *
	 * @author TeamSomething
	 * @return boolean = if the object is dead
	 */
	public abstract boolean isFinished();
}
