package command;

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
public class AddObjectCommand extends Command<TDServer> {
	private static final long serialVersionUID = -1886523639931445319L;
	//Shapes shapes;

	/**
	 * Creates an ObjectCommand and takes in a Shape object.
	 * 
	 * @param receivedShapes
	 *            - the shape object received from the client.
	 */
	public AddObjectCommand() {
		//shapes = receivedShapes;
	}

	/**
	 * Executes the command to add the shape to the list.
	 */
	@Override
	public void execute(TDServer executeOn) {
		// TODO Auto-generated method stub
		//executeOn.addShapes(shapes);
	}

}
