package command;

import java.util.Vector;

import Controller.TDClient;
import Controller.TDServer;
import Model.GameMapSkeleton;
import Model.Tile;

public class UpdateMiniMapCommand extends Command{
	
	//private final Vector<Vector<Tile>> gameMap;
	private GameMapSkeleton gameMap;
	private int totalResources;
	private int enemiesKilled;
	

	public UpdateMiniMapCommand(String username, Vector<Vector<Tile>> mapUpdate, int resources, int numKilled) {
		super(username);
		gameMap = new GameMapSkeleton(mapUpdate);
		
		totalResources = resources;
		enemiesKilled = numKilled;
		//System.out.println("Command: at construction gameMap = " + gameMap.get(gameMap.size() - 1).get(1).getAttackers().size());
	}

	@Override
	public void serverExecute(TDServer server) {
		//System.out.println("Command: at server gameMap = " + gameMap.get(gameMap.size() - 1).get(1).getAttackers().size());
		server.transferCommand(this);
	}

	@Override
	public void clientExecute(TDClient client) {
		//System.out.println("Command: at other client gameMap = " + gameMap.get(gameMap.size() - 1).get(1).getAttackers().size());
		client.updateMiniMap(gameMap, totalResources, enemiesKilled);
	}

}
