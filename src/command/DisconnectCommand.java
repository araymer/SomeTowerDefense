package command;

import Controller.TDClient;
import Controller.TDServer;

/**
 * This command is sent by a client that is disconnecting
 * 
 * 
 *
 */
public class DisconnectCommand extends Command {

	/**
	 * Creates a disconnect command for the given client
	 * 
	 * @param name
	 *            username of client to disconnect
	 */
	public DisconnectCommand(String name) {
		super(name);
	}

	@Override
	public void serverExecute(TDServer server) {
		server.disconnect(getSender());
	}

	@Override
	public void clientExecute(TDClient client) {

	}

}
