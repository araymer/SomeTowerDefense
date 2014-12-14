package Attackers;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.Structure;
import Model.Tile;
import View.GameGUI;

public class Cannoneer extends Attacker {
	private static final int HITPOINTS = 500;
	private static final int DEFENSE = 10;
	private static final int ATTACK_RATING = 50;
	private static final int RANGE = 3;
	private static final int SPEED = 50;// The smaller, the faster
	double lastX, lastY, drawX, drawY;
	double pixels = 0;
	int count = 0;
	double interp;

	public Cannoneer(Tile startingLocation) {
		super(HITPOINTS, DEFENSE, ATTACK_RATING, RANGE, SPEED, startingLocation);
		name = "Cannoneer";
		imageFileName = "Attackers.png";
	}

	@Override
	public void attack(Structure s) {
		s.takeDamage(this.getAttack());
	}

	@Override
	public void die() {
		// play dying animation and remove the attacker
		isDead = true;

	}

	public void draw(Graphics2D g2) {
		interp = GameGUI.getInstance().interpolation;
		if (bImage == null) {
			File imageFile = new File(baseDir + imageFileName);
			try {
				bImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (xIncrement > 3) {
			xIncrement = 0;
		}
		
		
		//This calculates the amount of offset to animate between tiles (40px/SPEED = 0.8px per tick)
		if(pixels < 40-(40/SPEED) && getLoc() != null)
			pixels += (double)40./SPEED;
		else
			pixels = 0;
	
		
		BufferedImage tempSubImage = bImage.getSubimage(xIncrement * WIDTH, yIncrement * HEIGHT + 40, WIDTH, HEIGHT);
		//We need to slow down the animation frames so they aren't firing every tick! Use Count%5 so they're 1/5 as fast
		if(count%4 == 0)
			xIncrement++;
		
		count++;
		
		// variable "at" will help us manipulate sprites
		AffineTransform at = new AffineTransform();
		//calculate offset per tick
		at.translate(offset("x"), offset("y"));
		//calculate direction they should be facing
		at.rotate(checkTransform(), tempSubImage.getWidth()/2, tempSubImage.getHeight()/2);

		
		g2.drawImage(tempSubImage, at, null);
		
		lastX = drawX;
		lastY = drawY;

	}
	
	private double offset(String s) {
		
		if(s.equals("x")) {
			drawX = ((getLoc().getCoordinates().x+pixels - lastX) * interp + lastX - 20);
			return drawX;
		}
		
		else if(s.equals("y")) {
			drawY = ((getLoc().getCoordinates().y+pixels - lastY) * interp + lastY - 20);
			return drawY;
		}
		
		
		return 0;
	
	}
	
	private double checkTransform() {
		if(getLoc().getCoordinates().x - getLoc().nextTile.getCoordinates().x < 0)
			return (Math.PI/2);
		else if(getLoc().getCoordinates().x - getLoc().nextTile.getCoordinates().x > 0)
			return (-Math.PI/2);
		else if(getLoc().getCoordinates().y - getLoc().nextTile.getCoordinates().y < 0)
			return (Math.PI);
		else
			return 0.;
	}
	/*
	 * 
	 * //4 is the number of shooting frames if(xIncrement > 4){ xIncrement = 0;
	 * }
	 * 
	 * BufferedImage tempSubImage = bImage.getSubimage(xIncrement * WIDTH,
	 * yIncrement * HEIGHT, WIDTH, HEIGHT); xIncrement ++;
	 * g2.drawImage(tempSubImage, getLoc().getCoordinates().x * WIDTH,
	 * getLoc().getCoordinates().y * HEIGHT, WIDTH, HEIGHT, null);
	 * 
	 * }
	 */

}
