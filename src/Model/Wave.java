package Model;

import View.TilePanel;
import Attackers.Cannoneer;
import Attackers.Marine;
import Attackers.Scout;

public class Wave {
	public int waveCount;
	
	private int innerCount = 1;
	public boolean enemiesAlive = true;
	
	public Wave(){
		
	}
	
	public void setWave(int num){
		waveCount = num;
	}
	
	public void startingAWave(){
		
	}
	
	public void finishedWaves(){
		
	}
	
	public void setEnemiesAlive(boolean bool){
		enemiesAlive = bool;
	}
	
	public void attemptSpawn(int tick){
		if(tick % 40 == 0){
			switch(waveCount){
			case 0:
				if(innerCount == 1){
					TilePanel.getInstance().tileMap.getSpawnTile(1).addAttacker(
							new Marine(TilePanel.getInstance().tileMap.getSpawnTile(1)));
				}else if(innerCount == 2){
					TilePanel.getInstance().tileMap.getSpawnTile(2).addAttacker(
							new Marine(TilePanel.getInstance().tileMap.getSpawnTile(2)));
					
				}else if(!enemiesAlive){
					waveCount++;
					startingAWave();
				}
				break;
			case 1:
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
					waveCount++;
					startingAWave();
				}
				break;
			case 2:
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
					waveCount++;
					startingAWave();
				}
				break;
			}
			innerCount ++;
		}
		
		
	}
}
