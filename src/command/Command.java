package command;

import java.io.Serializable;

import Controller.TDClient;
import Controller.TDServer;

/**
 * This abstract class defines a serializable command that can be sent and
 * executed on either a client or server.
 * 
 * 
 */

public abstract class Command implements Serializable {

	private String senderName;

	/**
	 * Creates a Command
	 *
	 * @author TeamSomething
	 * @param username
	 *            = the name of the sender
	 */
	public Command(String username) {
		senderName = username;
	}

	/**
	 * Executes the command on the given argument
	 * 
	 * @param executeOn
	 *            Object to execute command on
	 */
	public abstract void serverExecute(TDServer server);

	/**
	 * Executes the client-side command
	 *
	 * @author TeamSomething
	 * @param client
	 *            = the client
	 */
	public abstract void clientExecute(TDClient client);

	/**
	 * Gets the sender of the comman
	 *
	 * @author TeamSomething
	 * @return String = the sender's name
	 */
	public String getSender() {
		return senderName;
	}
}
