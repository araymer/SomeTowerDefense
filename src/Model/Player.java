package Model;

import java.io.Serializable;

import View.ResourcePanel;

/**
 * Keeps track of currency
 * 
 * @author TeamSomething
 */
public class Player implements Serializable {

	private static Player player;
	private int money;

	// private ResourcePanel resource = ResourcePanel.getInstance();

	/**
	 * Constructs a new player
	 * 
	 * @author TeamSomething
	 */
	private Player() {
		money = 500;

		updateCost();
	}

	/**
	 * Notifies the ResourcePanel at a new money amount
	 * 
	 * @author TeamSomething
	 */
	public void updateCost() {
		ResourcePanel.getInstance().updateMoney(getMoney());
	}

	/**
	 * Gets the Singleton instance of player
	 * 
	 * @author TeamSomething
	 * @return Player = Singleton instance of player
	 */
	public static Player getInstance() {
		if (player == null)
			player = new Player();
		return player;
	}

	/**
	 * Adds money to the player and notifies the resource panel.
	 * 
	 * @author TeamSomething
	 * @param add
	 *            = money to be added
	 */
	public void addMoney(int add) {
		money += add;
		updateCost();
	}

	/**
	 * Subtracts money to the player and notifies the resource panel.
	 * 
	 * @author TeamSomething
	 * @param subtract
	 *            = money to be subtracted
	 */
	public boolean subtractMoney(int subtract) {
		if (money - subtract < 0)
			return false;
		money -= subtract;

		updateCost();
		return true;
	}

	/**
	 * Gets the amount od money the player has.
	 * 
	 * @author TeamSomething
	 * @return int = Player's money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Sets the player's funds
	 * 
	 * @author TeamSomething
	 * @param money
	 *            = money value
	 */
	public void setMoney(int money) {
		this.money = money;
		updateCost();
	}

	/**
	 * Resets the instance of the Player
	 * 
	 * @author TeamSomething
	 */
	public void reset() {
		player = new Player();

	}

}
