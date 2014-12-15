package command;

import Controller.TDClient;
import Controller.TDServer;

/**
 * Creates a game command
 *
 * @author TeamSomething
 */
public class CreateGameCommand extends Command {

	/**
	 * Creates a new Command for the user
	 *
	 * @author TeamSomething
	 * @param username
	 *            = the username of the player
	 */
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
