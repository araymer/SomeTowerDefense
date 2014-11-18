package Attackers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Attacker;
import Model.Structure;

public class Marine extends Attacker {

	public Marine() {
		super(50, 10, 50, 2, 500);
		imageFileName = "topdownmarinessmall.png";
	}

	@Override
	public void attack(Structure s) {
		s.takeDamage(this.getAttack());
	}

	@Override
	public void die() {
		// play dying animation and remove the attacker

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
		
		if(xIncrement  > 6){
			xIncrement = 0;
		}
		
		BufferedImage tempSubImage = bImage.getSubimage(xIncrement * width, yIncrement * height, width, height);
		xIncrement ++;
		g2.drawImage(tempSubImage, getLoc().getCoordinates().x * width, getLoc().getCoordinates().y * height, width, height, null);
		
	}


}
