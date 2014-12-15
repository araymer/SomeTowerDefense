package command;

import Controller.TDClient;
import Controller.TDServer;

/**
 * Command for the Base taking damage
 *
 * @author TeamSomething
 */
public class BaseTakeDamageCommand extends Command {
	private int amountDamaged;

	/**
	 * Constructs a new Command for the base taking damage
	 *
	 * @author TeamSomething
	 * @param username
	 *            = the name of the client
	 * @param amtdmgd
	 *            = the amount of damage done to the base
	 */
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
