package command;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import Controller.TDClient;
import Controller.TDServer;

public class UpdateChatCommand extends Command{
	private List<String> text;

	public UpdateChatCommand(String username, List<String> txt) {
		super(username);
		text = new LinkedList<String>(txt); // note: we are making a copy of the given list
	}

	@Override
	public void serverExecute(TDServer server) {
		
	}

	@Override
	public void clientExecute(TDClient client) {
		client.updateChat(text);
	}

}
