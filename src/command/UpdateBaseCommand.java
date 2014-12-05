package command;

import Controller.TDClient;
import Controller.TDServer;

public class UpdateBaseCommand extends Command{
	
	private int newBaseHP;

	public UpdateBaseCommand(String username, int newBaseHP) {
		super(username);
		this.newBaseHP = newBaseHP;
	}

	@Override
	public void serverExecute(TDServer server) {
		
	}

	@Override
	public void clientExecute(TDClient client) {
		//client.updateBase(newBaseHP);
		
	}

}
