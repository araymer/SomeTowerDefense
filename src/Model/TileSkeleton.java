package Model;

import java.io.Serializable;

/**
 * Class created to easily pass tile information over the network to minimap
 * 
 * @author Team Something
 *
 */

@SuppressWarnings("serial")
public class TileSkeleton implements Serializable{
	public int attackerNum = 0;
	public boolean hasStructure = false;
	public boolean hasBase = false;
	public boolean hasPath = false;
	
	public TileSkeleton(Tile tile){
		if(tile != null){
			
			if(tile.getAttackers() != null){
				attackerNum = tile.getAttackers().size();
			}
			if(tile.getStructure() != null){
				hasStructure = true;
				if(tile.getStructure() instanceof Base){
					hasBase = true;
				}
			}
			hasPath = tile.getMove();
		}
	}
}
