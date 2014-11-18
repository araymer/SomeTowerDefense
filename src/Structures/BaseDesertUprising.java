package Structures;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.Structure;

public class BaseDesertUprising extends Structure {
	
	
	
	public BaseDesertUprising(int x, int y) {
		super(500, 0, 0, 0, 0, 0, 0, x, y, null);
		//imageFileName = "baseDesertUprising.png";
	}

	@Override
	public void shoot(Attacker a) {
		// TODO: shoot an enemy

	}

	@Override
	public void takeDamage(int dmg) {
		hitpoints -= dmg;

	}

	@Override
	public void explode() {
		// TODO This means game was lost

	}

	@Override
	public void draw(Graphics2D g2) {
		if(bImage == null){
			File imageFile = new File(baseDir + imageFileName);
			try {
				bImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		int width = 20;
		int height = 20;
		
		if((xIncrement * width) + width > bImage.getWidth()){
			yIncrement ++;
			xIncrement = 0;
		}
		if((yIncrement * height) + height > bImage.getHeight()){
			//Start from beginning again
			yIncrement = 0;
		}
		BufferedImage tempSubImage = bImage.getSubimage(xIncrement * width, yIncrement * height, width, height);
		xIncrement ++;
		g2.drawImage(tempSubImage, getX() * width, getY() * height, width, height, null);
		
	}

}
