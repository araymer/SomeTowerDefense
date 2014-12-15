package Model;

import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import View.GameGUI;

public abstract class Base extends Structure implements Serializable {

	/**
	 * Abstract class for base
	 *
	 * @author TeamSomething
	 */
	public Base(int hp, int prod, int rng, int dmg, int splash, int rate,
			int cost, int x, int y, SpecialAttack sp) {
		super(hp, prod, rng, dmg, splash, rate, cost, x, y, sp);
		upgradeTo = StructureType.NONE;
		upgradeCost = 0;

	}

	/**
	 * Sets the base HP
	 *
	 * @author TeamSomething
	 * @param newHP
	 *            = hp of base
	 */
	public void setHP(int newHP) {
		hitpoints = newHP;
		System.out.println("New base hp is: " + hitpoints);
	}

	/**
	 * Special takeDamage that damages the hp on the server if on multiplayer.
	 */
	@Override
	public void takeDamage(int dmg) {
		if (GameGUI.getInstance().isMultiplayer) {
			GameGUI.getInstance().baseTakeDamage(dmg);
		} else {
			System.out.println("beep boop!");
			hitpoints -= dmg;
		}

	}

	JFrame gameOver;

	@Override
	public void die() {
		JButton restart = new JButton("Restart Level");
		System.out.println("\n\n\n\nBASE WAS DESTROYED. GAME OVER");
		Ticker.getInstance().loopStop();
		gameOver = new JFrame();
		gameOver.setLayout(new GridLayout(3, 1));
		gameOver.setLocation(100, 100);
		gameOver.setSize(400, 200);
		gameOver.add(new JLabel(
				"<html>BASE WAS DESTROYED. GAME OVER.<br>Use MenuBar to return or restart</html>"));
		/*
		 * restart.addActionListener(new RestartListener());
		 * gameOver.add(restart); JButton mainMenu = new
		 * JButton("Return to Main Menu"); mainMenu.addActionListener(new
		 * ReturnListener()); gameOver.add(mainMenu); gameOver.setVisible(true);
		 * TilePanel
		 * .getInstance().getMap().getGameBoard().get(getX()).get(getY())
		 * .removeStructure();
		 */
		gameOver.setVisible(true);
	}

	// JFrame frame;

	/*
	 * private class RestartListener implements ActionListener {
	 * 
	 * public void actionPerformed(ActionEvent e) {
	 * 
	 * frame = GameGUI.getInstance().frame; TilePanel tilePanel =
	 * TilePanel.getInstance();
	 * 
	 * frame.remove(tilePanel); tilePanel.reset(); tilePanel =
	 * TilePanel.getInstance();
	 * 
	 * frame.add(tilePanel);
	 * 
	 * tilePanel.setSize(frame.getSize().width, frame.getSize().height);
	 * tilePanel.setLocation(0, 0); tilePanel.setLayout(new CardLayout());
	 * 
	 * GameGUI.getInstance().newTilePanel(); Ticker.getInstance().loopStart();
	 * 
	 * gameOver.dispose();
	 * 
	 * }
	 * 
	 * }
	 * 
	 * private class ReturnListener implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent arg0) { frame =
	 * GameGUI.getInstance().frame;
	 * frame.setContentPane(MainMenu.getInstance());
	 * GameGUI.getInstance().resetMenuBar(); gameOver.dispose(); } }
	 */
}
