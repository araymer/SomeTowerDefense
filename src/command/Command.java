package command;

import java.io.Serializable;

import Controller.TDClient;
import Controller.TDServer;

/**
 *	This abstract class defines a serializable command that can be sent
 * 	and executed on either a client or server.
 *  
 *  
 */

public abstract class Command implements Serializable {

	private String senderName;
	
	public Command(String username){
		senderName = username;
	}

	/**
	 * Executes the command on the given argument
	 * 
	 * @param executeOn	Object to execute command on
	 */
	public abstract void serverExecute(TDServer server);
	
	public abstract void clientExecute(TDClient client);
	
	
	public String getSender(){
		return senderName;
	}
}
