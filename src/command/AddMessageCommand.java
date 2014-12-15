package command;

import Controller.TDClient;
import Controller.TDServer;

/**
 * Adds a message to the IM
 *
 * @author TeamSomething
 */
public class AddMessageCommand extends Command {

	private String message;

	/**
	 * Creates a message
	 *
	 * @author TeamSomething
	 * @param username
	 *            = the sender
	 * @param message
	 *            = the IM
	 */
	public AddMessageCommand(String username, String message) {
		super(username);
		this.message = message;
	}

	@Override
	public void serverExecute(TDServer server) {
		server.addToChat(getSender() + ": " + message);
	}

	@Override
	public void clientExecute(TDClient client) {

	}

}
