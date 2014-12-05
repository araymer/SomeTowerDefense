package command;

import java.util.ArrayList;

import Controller.TDClient;
import Controller.TDServer;


/*
 * This command is created whenever the server's object list changes.
 * When executed on a client, it replaces the list of objects in
 * their GUI with the list sent by the server.
 */

/**
 * Command that updates the client canvas.
 *
 */
public class UpdateClientCommand extends Command {
	//private ArrayList<Shapes> list; // the message log from the server

	/**
	 * Creates a new UpdateClientCommand with the given list of shapes
	 * 
	 * @param list
	 *            - the list of shapes on the canvas
	 */
	public UpdateClientCommand(String username) {
		super(username);
		//this.list = new ArrayList<Shapes>(list); // note: we are making a copy
													// of the given list
	}

	@Override
	public void serverExecute(TDServer server) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientExecute(TDClient client) {
		// TODO Auto-generated method stub
		
	}

}
