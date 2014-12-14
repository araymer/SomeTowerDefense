package Attackers;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.Player;
import Model.Structure;
import Model.Tile;
import View.TilePanel;

public class Scout extends Attacker {
	private static final int HITPOINTS = 150;
	private static final int DEFENSE = 10;
	private static final int ATTACK_RATING = 10;
	private static final int RANGE = 1;
	private static final int SPEED = 15;// The smaller, the faster
	double pixels = 0;
	int count = 0;
	BufferedImage tempSubImage;
	boolean firing;
	int frames;

	public Scout(Tile startingLocation) {
		super(HITPOINTS, DEFENSE, ATTACK_RATING, RANGE, SPEED, startingLocation);
		name = "Sergeant";
		imageFileName = "Attackers.png";
		value = 15;
	}

	@Override
	public void attack(Structure s) {
		s.takeDamage(this.getAttack());
	}

	@Override
	public void die() {
		// play dying animation and remove the attacker
		isDead = true;
		Player.getInstance().addMoney(value);
		System.out.println("SCOUT DEAD");

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
		if(firing)
			frames = 2;
		else
			frames = 3;

		if (xIncrement > frames) {
			xIncrement = 0;
		}
		
		
		//This calculates the amount of offset to animate between tiles (40px/SPEED = 0.8px per tick)
		if(pixels < 40-(40/SPEED) && getLoc() != null)
			pixels += (double)40./SPEED;

		else
			resetPixels();
	
		
		if(getLoc().nextTile != null) {
			firing = true;
			tempSubImage = bImage.getSubimage(xIncrement * WIDTH, yIncrement * HEIGHT + 120, WIDTH, HEIGHT);
		}
		else
			 tempSubImage = bImage.getSubimage(xIncrement * (WIDTH*3), yIncrement * HEIGHT + 80, WIDTH, HEIGHT*2);
		//We need to slow down the animation frames so they aren't firing every tick! Use Count%5 so they're 1/5 as fast
		if(count%4 == 0)

			xIncrement++;

		count++;
		
		// AffineTransform will help us manipulate sprites
		AffineTransform at = new AffineTransform();
		// calculate offset per tick
		at.translate(getLoc().getCoordinates().x * WIDTH + offset("x"),
				getLoc().getCoordinates().y * HEIGHT + offset("y"));
		// calculate direction they should be facing
		at.rotate(checkTransform(), tempSubImage.getWidth() / 2,
				tempSubImage.getHeight() / 2);

		g2.drawImage(tempSubImage, at, null);
		


	}

	private double offset(String s) {

		if(getLoc().nextTile != null){
			if(getLoc().getCoordinates().x - getLoc().nextTile.getCoordinates().x < 0 && s.equals("x"))

				return pixels;
			else if (getLoc().getCoordinates().x
					- getLoc().nextTile.getCoordinates().x > 0
					&& s.equals("x"))
				return -pixels;
			
			else if (getLoc().getCoordinates().y
					- getLoc().nextTile.getCoordinates().y < 0
					&& s.equals("y"))
				return pixels;
			
			else if (getLoc().getCoordinates().y
					- getLoc().nextTile.getCoordinates().y > 0
					&& s.equals("y"))
				return -pixels;
		}

		return 0;

	}

	private double checkTransform() {
		if(getLoc().nextTile != null) {

		if(getLoc().getCoordinates().x - getLoc().nextTile.getCoordinates().x > 0)
			return (-Math.PI/2);
		else if(getLoc().getCoordinates().y - getLoc().nextTile.getCoordinates().y < 0)
			return (Math.PI);
		else if(getLoc().getCoordinates().x - getLoc().nextTile.getCoordinates().x < 0)
			return (Math.PI/2);

		else
			return 0.;
		}
		else {
			
			if(getLoc().getCoordinates().x - TilePanel.getInstance().tileMap.getBaseX() > 0)
				return (-Math.PI/2);
			else if(getLoc().getCoordinates().y - TilePanel.getInstance().tileMap.getBaseY() < 0)
				return (Math.PI);
			else if(getLoc().getCoordinates().y - TilePanel.getInstance().tileMap.getBaseX() < 0)
				return (Math.PI/2);

			else
				return 0.;
			
		}
	}
	
	
	public void resetPixels() {
		pixels = 0;
	}

	@Override
	public void setLoc(Tile loc) {
		super.setLoc(loc);
		resetPixels();
	}

	

}
