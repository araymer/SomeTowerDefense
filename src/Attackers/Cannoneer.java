package Attackers;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.Structure;
import Model.Ticker;
import Model.Tile;

public class Cannoneer extends Attacker {
	private static final int HITPOINTS = 500;
	private static final int DEFENSE = 10;
	private static final int ATTACK_RATING = 50;
	private static final int RANGE = 3;
	private static final int SPEED = 50;// The smaller, the faster
	double pixels = 1000/SPEED/40;

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
		if (bImage == null) {
			File imageFile = new File(baseDir + imageFileName);
			try {
				bImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 4 is the number of shooting frames
		if (xIncrement > 3) {
			xIncrement = 0;
		}
		
		
		
		if(pixels < 40)
			pixels += pixels;
		else
			pixels = 1000/SPEED/40;
		
		
		System.out.println(pixels);
		
		BufferedImage tempSubImage = bImage.getSubimage(xIncrement * WIDTH,
				yIncrement * HEIGHT + 40, WIDTH, HEIGHT);
		xIncrement++;
		AffineTransform at = new AffineTransform();
		at.translate(getLoc().getCoordinates().x * WIDTH + offset("x"),
				getLoc().getCoordinates().y * HEIGHT + offset("y"));
		at.rotate(checkTransform());
		g2.drawImage(tempSubImage, at, null);


	}
	
	private double offset(String s) {
		if(getLoc().getCoordinates().x - getLoc().nextTile.getCoordinates().x < 0 && s.equals("x"))
			return pixels;
		else if(getLoc().getCoordinates().x - getLoc().nextTile.getCoordinates().x > 0 && s.equals("x"))
			return -pixels;
		else if(getLoc().getCoordinates().y - getLoc().nextTile.getCoordinates().y < 0 && s.equals("y"))
			return pixels;
		else if(getLoc().getCoordinates().y - getLoc().nextTile.getCoordinates().y > 0 && s.equals("y"))
			return -pixels;
		
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
