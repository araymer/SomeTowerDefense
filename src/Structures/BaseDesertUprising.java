package Structures;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Maps.DesertUprising;
import Model.Base;
import Model.Structure;
import Model.Ticker;
import TowerFSM.TowerStates;
import View.GameGUI;
import View.ResourcePanel;
import View.TilePanel;

@SuppressWarnings("serial")
public class BaseDesertUprising extends Base {

	protected static BufferedImage waitImage;
	protected static BufferedImage attackImage;
	protected static BufferedImage upgradeImage;
	protected static BufferedImage explodeImage;

	public BaseDesertUprising(int x, int y) {
		super(500, 0, 0, 0, 0, 0, 0, x, y, null);
		name = "Desert Uprising Base";
	}


	@Override
	protected void setImages() {
		if (waitImage == null) {
			File imageFile = new File(Structure.baseDir + "base40.png");
			try {
				waitImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (attackImage == null) {
			File imageFile = new File(Structure.baseDir + "error.png");
			try {
				attackImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (upgradeImage == null) {
			File imageFile = new File(Structure.baseDir + "error.png");
			try {
				upgradeImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (explodeImage == null) {
			File imageFile = new File(Structure.baseDir
					+ "explosion-sprite40.png");
			try {
				explodeImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	@Override
	protected BufferedImage getImage(TowerStates newState) {
		BufferedImage correctImage = null;
		switch (newState) {
		case ATTACK:
			correctImage = attackImage;
			break;
		case WAIT:
			correctImage = waitImage;
			break;
		case EXPLODE:
			correctImage = explodeImage;
			break;
		case UPGRADE:
			correctImage = upgradeImage;
			break;
		default:
			System.out.println("Problem Encountered in getImage()");
		}
		return correctImage;
	}

	@Override
	public void die() {
		JButton restart = new JButton("Restart Level");
		System.out.println("\n\n\n\nBASE WAS DESTROYED. GAME OVER");
		Ticker.getInstance().loopStop();
		JFrame gameOver = new JFrame();
		gameOver.setLocation(100,100);
		gameOver.setSize(400, 200);
		gameOver.add(new JLabel("BASE WAS DESTROYED. GAME OVER."));
		restart.addActionListener(new RestartListener());
		gameOver.add(restart);
		gameOver.setVisible(true);
		
		
		
	}
	
	private class RestartListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			
			TilePanel.getInstance().setMap(((DesertUprising) DesertUprising.getInstance()).reInit());
			ResourcePanel.getInstance().resourceFrame.dispose();
			ResourcePanel.getInstance().reinit();
			
			Ticker.getInstance().loopStart();
		
		}
		
	}

}
