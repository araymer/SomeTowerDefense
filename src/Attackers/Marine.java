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
		imageFileName = "topdownmarines40.png";
	}

	@Override
	public void attack(Structure s) {
		//s.takeDamage(this.getAttack());
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

		//4 is the number of shooting frames
		if(xIncrement  > 4){
			xIncrement = 0;
		}
		
		BufferedImage tempSubImage = bImage.getSubimage(xIncrement * WIDTH, yIncrement * HEIGHT, WIDTH, HEIGHT);
		xIncrement ++;
		g2.drawImage(tempSubImage, getLoc().getCoordinates().x * WIDTH, getLoc().getCoordinates().y * HEIGHT, WIDTH, HEIGHT, null);
		
	}


}
