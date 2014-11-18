package Structures;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.Structure;

public class BaseDesertUprising extends Structure {
	
	
	
	public BaseDesertUprising(int x, int y) {
		super(500, 0, 0, 0, 0, 0, 0, x, y, null);
		imageFileName = "Base1.png";
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
	// TODO make the base image 2x2 tiles (40x40 pxl)
	/*
	@Override
	public void draw(Graphics2D g2){
		if(image == null){
			File imageFile = new File(baseDir + imageFileName);
			try {
				image = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int width = 40;
		int height = 40;
		
		g2.drawImage(image, x * width, y * height, width, height, null);
	}*/

}
