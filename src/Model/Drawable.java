package Model;

import java.awt.Graphics2D;

public abstract class Drawable {
	public abstract void draw(Graphics2D g2);
	public abstract void update();
	public abstract boolean isFinished();
}
