package Structures;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.Base;
import Model.Structure;
import Model.Ticker;
import TowerFSM.TowerStates;
import View.GameGUI;
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
		price = 0;
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

	JFrame gameOver;

	@Override
	public void die() {
		JButton restart = new JButton("Restart Level");
		System.out.println("\n\n\n\nBASE WAS DESTROYED. GAME OVER");
		Ticker.getInstance().loopStop();
		gameOver = new JFrame();
		gameOver.setLayout(new GridLayout(2, 1));
		gameOver.setLocation(100, 100);
		gameOver.setSize(400, 200);
		gameOver.add(new JLabel("BASE WAS DESTROYED. GAME OVER."));
		restart.addActionListener(new RestartListener());
		gameOver.add(restart);
		gameOver.setVisible(true);
		TilePanel.getInstance().getMap().getGameBoard().get(getX()).get(getY())
				.removeStructure();
		// TilePanel.getInstance().reset();
		// GameGUI.getInstance().frame.setContentPane(TilePanel.getInstance());

	}

	private class RestartListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {



			JFrame frame = GameGUI.getInstance().frame;
			TilePanel tilePanel = TilePanel.getInstance();

			frame.remove(tilePanel);
			tilePanel.reset();
			tilePanel = TilePanel.getInstance();

			frame.add(tilePanel);

			tilePanel.setSize(frame.getSize().width, frame.getSize().height);
			tilePanel.setLocation(0, 0);
			tilePanel.setLayout(new CardLayout());

			GameGUI.getInstance().newTilePanel();
			Ticker.getInstance().loopStart();
			// new Thread(Ticker.getInstance()).start();

			gameOver.dispose();

		}

	}

}
