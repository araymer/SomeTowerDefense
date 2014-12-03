package command;

import java.util.ArrayList;

import Controller.TDClient;


/*
 * This command is created whenever the server's object list changes.
 * When executed on a client, it replaces the list of objects in
 * their GUI with the list sent by the server.
 */

/**
 * Command that updates the client canvas.
 *
 */
public class UpdateClientCommand extends Command<TDClient> {
	private static final long serialVersionUID = -6660866320115548448L;
	//private ArrayList<Shapes> list; // the message log from the server

	/**
	 * Creates a new UpdateClientCommand with the given list of shapes
	 * 
	 * @param list
	 *            - the list of shapes on the canvas
	 */
	public UpdateClientCommand() {
		//this.list = new ArrayList<Shapes>(list); // note: we are making a copy
													// of the given list
	}

	public void execute(TDClient executeOn) {
		// update the client
		//executeOn.update(list);
	}

}
