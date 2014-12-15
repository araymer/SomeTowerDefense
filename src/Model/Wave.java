package Model;

import View.TilePanel;
import Attackers.Cannoneer;
import Attackers.Marine;
import Attackers.Scout;

public class Wave {
	public int waveCount = 1;
	
	private int innerCount = 1;
	public boolean enemiesAlive = true;
	
	public Wave(){
		
	}
	
	public void setWave(int num){
		waveCount = num;
	}
	
	public void startingAWave(){
		innerCount = 0;
		waveCount++;
		System.out.println("Starting wave " + waveCount);
	}
	
	public void finishedWaves(){
		System.out.println("YOU WON");
	}
	
	public void setEnemiesAlive(boolean bool){
		if(!bool){
			//System.out.println("Wave: set to false!");
		}
		enemiesAlive = bool;
	}
	
	public void attemptSpawn(int tick){
		if(tick % 70 == 0){
			switch(waveCount){
			case 1:
				if(innerCount == 1){
					TilePanel.getInstance().tileMap.getSpawnTile(1).addAttacker(
							new Marine(TilePanel.getInstance().tileMap.getSpawnTile(1)));
				}else if(innerCount == 2){
					TilePanel.getInstance().tileMap.getSpawnTile(2).addAttacker(
							new Marine(TilePanel.getInstance().tileMap.getSpawnTile(2)));
					
				}else if(!enemiesAlive){
					startingAWave();
				}
				break;
			case 2:
				if(innerCount == 1){
					TilePanel.getInstance().tileMap.getSpawnTile(1).addAttacker(
							new Marine(TilePanel.getInstance().tileMap.getSpawnTile(1)));
				}else if(innerCount == 2){
					TilePanel.getInstance().tileMap.getSpawnTile(2).addAttacker(
							new Scout(TilePanel.getInstance().tileMap.getSpawnTile(2)));
					
				}else if(innerCount == 3){
				TilePanel.getInstance().tileMap.getSpawnTile(2).addAttacker(
						new Marine(TilePanel.getInstance().tileMap.getSpawnTile(2)));
				
				}else if(!enemiesAlive){
					startingAWave();
				}
				break;
			case 3:
				if(innerCount == 1){
					TilePanel.getInstance().tileMap.getSpawnTile(1).addAttacker(
							new Scout(TilePanel.getInstance().tileMap.getSpawnTile(1)));
				}else if(innerCount == 2){
					TilePanel.getInstance().tileMap.getSpawnTile(2).addAttacker(
							new Scout(TilePanel.getInstance().tileMap.getSpawnTile(2)));
					
				}else if(innerCount == 3){
				TilePanel.getInstance().tileMap.getSpawnTile(2).addAttacker(
						new Cannoneer(TilePanel.getInstance().tileMap.getSpawnTile(2)));
				
				}else if(!enemiesAlive){
					startingAWave();
					finishedWaves();
				}
				break;
			}
			innerCount ++;
		}
		
		
	}
}
