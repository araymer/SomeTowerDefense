package Model;

import java.io.Serializable;

import View.ResourcePanel;

public class Player implements Serializable {

	private static Player player;
	private int money;

	// private ResourcePanel resource = ResourcePanel.getInstance();

	private Player() {
		money = 500;
		updateCost();
	}

	public void updateCost() {
		ResourcePanel.getInstance().updateMoney(getMoney());
	}

	public static Player getInstance() {
		if (player == null)
			player = new Player();
		return player;
	}

	public void addMoney(int add) {
		money += add;
		updateCost();
	}

	public boolean subtractMoney(int subtract) {
		if (money - subtract < 0)
			return false;
		money -= subtract;
		updateCost();
		return true;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
		updateCost();
	}

	public void reset() {
		player = new Player();

	}

}
