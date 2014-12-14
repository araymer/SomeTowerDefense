package command;

import java.util.Vector;

import Controller.TDClient;
import Controller.TDServer;
import Model.Tile;

public class UpdateMiniMapCommand extends Command{
	
	private Vector<Vector<Tile>> gameMap;
	private int totalResources;
	private int enemiesKilled;
	

	public UpdateMiniMapCommand(String username, Vector<Vector<Tile>> mapUpdate, int resources, int numKilled) {
		super(username);
		gameMap = new Vector<Vector<Tile>>(mapUpdate);
		totalResources = resources;
		enemiesKilled = numKilled;
	}

	@Override
	public void serverExecute(TDServer server) {
		server.transferCommand(this);
	}

	@Override
	public void clientExecute(TDClient client) {
		client.updateMiniMap(new Vector<Vector<Tile>>(gameMap), totalResources, enemiesKilled);
	}

}
