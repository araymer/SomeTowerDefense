package command;

import Controller.TDClient;
import Controller.TDServer;

public class CreateGameCommand extends Command{

	public CreateGameCommand(String username) {
		super(username);
	}

	@Override
	public void serverExecute(TDServer server) {
		
		
	}

	@Override
	public void clientExecute(TDClient client) {
		client.startMultiplayerGame();
	}

}
