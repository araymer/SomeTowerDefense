package command;

import Controller.TDClient;
import Controller.TDServer;

/*
 * This command is created when a user draws a shape on their client's canvas.
 * When executed on the server, this command will add the object to the
 * server's list of all object on the canvas.
 */

/**
 * Command for adding shapes to the server's list of shapes.
 *
 */
public class TransferResourcesCommand extends Command {
	private int resources;

	/**
	 * Creates an ObjectCommand and takes in a Shape object.
	 * 
	 * @param receivedShapes
	 *            - the shape object received from the client.
	 */
	public TransferResourcesCommand(String username, int resources) {
		super(username);
		this.resources = resources;
	}

	/**
	 * Executes the command to add the shape to the list.
	 */
	@Override
	public void serverExecute(TDServer server) {
		server.transferCommand(this);
	}
	
	@Override
	public void clientExecute(TDClient client) {
		client.receiveResources(resources);
	}

}
