package Structures;

/*
 * The go-to weapon for the Corps Of Planetary Acquisition (COPA - space marines). This machine-gun
 * turret-style sentry is cheap. What it lacks in damage, it makes up for with deployability and speed.
 * 
 */

import Model.Structure;
import TowerFSM.TowerWaiting;

public class MarineSentryGun extends Structure {

	public MarineSentryGun(int x, int y) {
		super(120, 0, 5, 11, 0, 200, 1500, x, y, null);
		setImageNames();
		tower = new TowerWaiting(this);
	}
	
	protected void setImageNames(){
		waitImage = "topdownturret40.png";
		attackImage = "turretFire.png";
		//Set rest when available
	}

	// @Override
	// public void draw(Graphics2D g2) {
	// if(bImage == null){
	// File imageFile = new File(baseDir + imageFileName);
	// try {
	// bImage = ImageIO.read(imageFile);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// if((xIncrement * WIDTH) + WIDTH > bImage.getWidth()){
	// yIncrement ++;
	// xIncrement = 0;
	// }
	// if((yIncrement * HEIGHT) + HEIGHT > bImage.getHeight()){
	// //Start from beginning again
	// yIncrement = 0;
	// }
	// BufferedImage tempSubImage = bImage.getSubimage(xIncrement * WIDTH,
	// yIncrement * HEIGHT, WIDTH, HEIGHT);
	// xIncrement ++;
	// g2.drawImage(tempSubImage, getX() * WIDTH, getY() * HEIGHT, WIDTH,
	// HEIGHT, null);
	//
	// }

}
