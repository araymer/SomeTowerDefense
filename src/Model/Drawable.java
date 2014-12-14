package Model;

import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Drawable implements Serializable{
	public abstract void draw(Graphics2D g2);
	public abstract void update();
	public abstract boolean isFinished();
}
