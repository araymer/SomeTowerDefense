package Structures;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.Structure;

public class BaseDesertUprising extends Structure {
	
	
	
	public BaseDesertUprising(int x, int y) {
		super(500, 0, 0, 0, 0, 0, 0, x, y, null);
		imageFileName = "error.png";
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
