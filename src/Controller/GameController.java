//TODO: implement the controller. This is where our main method will be.

package Controller;

import Attackers.Marine;
import Model.Ticker;
import View.GameGUI;

public class GameController {
	private int waveCount;
	private int[] spawnsPerWave = { 1, 2, 4, 6, 10 };
	private GameGUI gui;

	public static void main(String[] args) {
		new GameController();
	}

	public GameController() {
		waveCount = 5;
		gui = GameGUI.getInstance();
		new Thread(Ticker.getInstance()).start();
		startWaves();
		
//		gui.tilePanel.tileMap.getSpawnTile(1).addAttacker(
//				new Marine(gui.tilePanel.tileMap.getSpawnTile(1)));
//		
//		gui.tilePanel.tileMap.getSpawnTile(1).addAttacker(
//				new Marine(gui.tilePanel.tileMap.getSpawnTile(2)));
						
//gui.tilePanel.tileMap
//		.getGameBoard()
//		.get(11)
//		.get(14)
//		.addAttacker(
//				new Marine(gui.tilePanel.tileMap.getGameBoard().get(11)
//						.get(14)));
	}

	//TODO This needs to be dependent on ticker, so pausing will also pause the waves
	public void startWaves() {

		for (int i = 0; i < waveCount; i++) {
			System.out.println("\n\n\nStarting wave " + (i + 1) + " out of " + waveCount + "\n\n");
			for (int k = 0; k < spawnsPerWave[i]; k++) {
				// start spawning enemies on a delay (probably about 1/2 a
				// second)
				if(k%2 == 0){
					gui.tilePanel.tileMap.getSpawnTile(1).addAttacker(
							new Marine(gui.tilePanel.tileMap.getSpawnTile(1)));
				}else{
					gui.tilePanel.tileMap.getSpawnTile(1).addAttacker(
							new Marine(gui.tilePanel.tileMap.getSpawnTile(2)));
				}
				
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//TODO Wait for all enemies to die
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
