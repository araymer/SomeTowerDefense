//TODO: implement the controller. This is where our main method will be.

package Controller;

import View.GameGUI;

public class GameController {
	private int waveCount;
	private int[] spawnsPerWave = {5, 10, 15, 20, 25};
	private GameGUI gui;
	
	public static void main(String[] args) {
		new GameController();
	}
	
	public GameController() {
		waveCount = 5;
		gui = new GameGUI();
		
	}
	
	public void startWaves() {
		
		for(int i = 0; i<waveCount; i++) {
			for(int k = 0; k<spawnsPerWave[i]; k++) {
			//start spawning enemies on a delay (probably about 1/2 a second)
			}
		}
		
	}


}
