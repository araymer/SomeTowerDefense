package Attackers;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.Structure;
import Model.Tile;

public class Marine extends Attacker {
	private static final int HITPOINTS = 50;
	private static final int DEFENSE = 10;
	private static final int ATTACK_RATING = 530;
	private static final int RANGE = 3;
	private static final int SPEED = 15;// The smaller, the faster
	int count = 0;
	double pixels = 0;

	public Marine(Tile startingLocation) {
		super(HITPOINTS, DEFENSE, ATTACK_RATING, RANGE, SPEED, startingLocation);
		name = "Marine";
		imageFileName = "topdownmarines40.png";
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
		if (xIncrement > 2) {
			xIncrement = 0;
		}
		
		
		//This calculates the amount of offset to animate between tiles (40px/SPEED = 2.6667px per tick)
		if(pixels < 40-(40/SPEED) && getLoc() != null)
			pixels += (double)40./SPEED;
		else
			pixels = 0;
	
		
		BufferedImage tempSubImage = bImage.getSubimage(xIncrement * WIDTH, yIncrement * HEIGHT, WIDTH, HEIGHT);
		//We need to slow down the animation frames so they aren't firing every tick! Use Count%5 so they're 1/5 as fast
		if(count%5 == 0)
			xIncrement++;
		
		count++;
		// variable "at" will help us manipulate sprites
		AffineTransform at = new AffineTransform();
		//calculate offset per tick
		at.translate(getLoc().getCoordinates().x * WIDTH + offset("x"), getLoc().getCoordinates().y * HEIGHT + offset("y"));
		//calculate direction they should be facing
		at.rotate(checkTransform(), tempSubImage.getWidth()/2, tempSubImage.getHeight()/2);

		
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
		if(getLoc().getCoordinates().x - getLoc().nextTile.getCoordinates().x > 0)
			return (Math.PI);
		else if(getLoc().getCoordinates().y - getLoc().nextTile.getCoordinates().y > 0)
			return (-Math.PI/2);
		else if(getLoc().getCoordinates().y - getLoc().nextTile.getCoordinates().y < 0)
			return (Math.PI/2);
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
