package command;

import Controller.TDClient;
import Controller.TDServer;

public class BaseTakeDamageCommand extends Command{
	private int amountDamaged;

	public BaseTakeDamageCommand(String username, int amtdmged) {
		super(username);
		amountDamaged = amtdmged;
	}

	@Override
	public void serverExecute(TDServer server) {
		server.masterHealthTakeDamage(amountDamaged);
	}

	@Override
	public void clientExecute(TDClient client) {
		
	}

}
