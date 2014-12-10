package command;

import Controller.TDClient;
import Controller.TDServer;

public class UpdateChatCommand extends Command{
	String text;

	public UpdateChatCommand(String username, String txt) {
		super(username);
		text = txt;
	}

	@Override
	public void serverExecute(TDServer server) {
		// TODO Auto-generated method stub
		//server.addToChat(text);
	}

	@Override
	public void clientExecute(TDClient client) {
		// TODO Auto-generated method stub
		//client.updateChat(text);
	}

}
