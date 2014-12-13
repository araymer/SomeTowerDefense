package command;

import java.util.Vector;

import Controller.TDClient;
import Controller.TDServer;
import Model.Tile;

public class UpdateMiniMapCommand extends Command{
	
	Vector<Vector<Tile>> gameMap;

	public UpdateMiniMapCommand(String username, Vector<Vector<Tile>> mapUpdate) {
		super(username);
		gameMap = new Vector<Vector<Tile>>(mapUpdate);
	}

	@Override
	public void serverExecute(TDServer server) {
		server.transferCommand(this);
	}

	@Override
	public void clientExecute(TDClient client) {
		client.updateMiniMap(gameMap);
	}

}
