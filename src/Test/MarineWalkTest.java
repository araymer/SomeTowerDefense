package Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Attackers.Marine;
import Model.Ticker;
import View.GameGUI;

public class MarineWalkTest {
	private int waveCount;
	private int[] spawnsPerWave = { 5, 10, 15, 20, 25 };
	private GameGUI gui;
	Timer timer;

	public static void main(String[] args) {
		new MarineWalkTest();
	}

	public MarineWalkTest() {
		waveCount = 5;
		gui = GameGUI.getInstance();
		new Thread(Ticker.getInstance()).start();
		gui.tilePanel.tileMap
				.getGameBoard()
				.get(19)
				.get(1)
				.addAttacker(
						new Marine(gui.tilePanel.tileMap.getGameBoard().get(19)
								.get(1)));
		gui.tilePanel.tileMap.getGameBoard().get(19).get(1).getOccupied();
		gui.tilePanel.tileMap
				.getGameBoard()
				.get(11)
				.get(14)
				.addAttacker(
						new Marine(gui.tilePanel.tileMap.getGameBoard().get(11)
								.get(14)));
		gui.tilePanel.tileMap.getGameBoard().get(11).get(14).getOccupied();
	}

	public void startWaves() {

		for (int i = 0; i < waveCount; i++) {
			for (int k = 0; k < spawnsPerWave[i]; k++) {
				// start spawning enemies on a delay (probably about 1/2 a
				// second)
			}
		}

	}

	private class TimeListener implements ActionListener {

		GameGUI newGUI = GameGUI.getInstance();

		@Override
		public void actionPerformed(ActionEvent arg0) {
			newGUI.tilePanel.tileMap
					.getGameBoard()
					.get(19)
					.get(1)
					.addAttacker(
							new Marine(newGUI.tilePanel.tileMap.getGameBoard()
									.get(19).get(1)));

		}

	}

}