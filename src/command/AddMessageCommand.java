package command;

import Controller.TDClient;
import Controller.TDServer;

public class AddMessageCommand extends Command{
	
	private String message;
	public AddMessageCommand(String username, String message) {
		super(username);
		this.message = message;
	}

	@Override
	public void serverExecute(TDServer server) {
		server.addToChat(getSender() + ": "+ message);
	}

	@Override
	public void clientExecute(TDClient client) {
		
	}

}
