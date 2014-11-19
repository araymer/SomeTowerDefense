package Structures;

/*
 * The go-to weapon for the Corps Of Planetary Acquisition (COPA - space marines). This machine-gun
 * turret-style sentry is cheap. What it lacks in damage, it makes up for with deployability and speed.
 * 
 */

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.Structure;

public class MarineSentryGun extends Structure {

	public MarineSentryGun(int x, int y) {
		super(120, 0, 5, 11, 0, 200, 1500, x, y, null);
		imageFileName = "topdownturret40.png";
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
		
		if((xIncrement * WIDTH) + WIDTH > bImage.getWidth()){
			yIncrement ++;
			xIncrement = 0;
		}
		if((yIncrement * HEIGHT) + HEIGHT > bImage.getHeight()){
			//Start from beginning again
			yIncrement = 0;
		}
		BufferedImage tempSubImage = bImage.getSubimage(xIncrement * WIDTH, yIncrement * HEIGHT, WIDTH, HEIGHT);
		xIncrement ++;
		g2.drawImage(tempSubImage, getX() * WIDTH, getY() * HEIGHT, WIDTH, HEIGHT, null);
		
	}

}
