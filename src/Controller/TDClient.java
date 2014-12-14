package Controller;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Vector;
import Structures.*;

import javax.swing.JOptionPane;

import Model.Player;
import Model.Ticker;
import Model.Tile;
import View.GameGUI;
import View.TilePanel;

import command.AddMessageCommand;
import command.BaseTakeDamageCommand;
import command.Command;
import command.DisconnectCommand;
import command.TransferResourcesCommand;
import command.UpdateBaseCommand;
import command.UpdateMiniMapCommand;

public class TDClient {

	@SuppressWarnings("unused")
	private GameController gameController;
	private GameGUI GUI;
	private Socket server;
	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	String username;
	private Player playerMoney;
	private int miniMapTick;


	/**
	 * Constructs a new TDClient and prompts the user to enter in the host
	 * address, host port, and a user name that the client keeps track of. It
	 * opens the connection to the server in a new thread.
	 */
	public TDClient() {

		// ask the user for a host, port, and user name
		// String host = JOptionPane.showInputDialog("Host address:");
		// String port = JOptionPane.showInputDialog("Host port:");
		String host = "localhost";
		String port = "4444";
		username = JOptionPane.showInputDialog("User name:");

		if (host == null || port == null || username == null)
			return;

		try {
			// Open a connection to the server
			server = new Socket(host, Integer.parseInt(port));
			toServer = new ObjectOutputStream(server.getOutputStream());
			fromServer = new ObjectInputStream(server.getInputStream());

			// write out the name of this client
			toServer.writeObject(username);

			gameController = GameController.getInstance();
			playerMoney = Player.getInstance();
			GUI = GameGUI.getInstance();
			GUI.setClient(this);

			// start a thread for handling server events
			new Thread(new ServerHandler()).start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles the commands from the server.
	 */
	private class ServerHandler implements Runnable {
		public void run() {
			try {
				while (true) {
					// read a command from server and execute it
					Command c = (Command) fromServer.readObject();
					c.clientExecute(TDClient.this);
				}
			} catch (SocketException e) {
				return; // "gracefully" terminate after disconnect
			} catch (EOFException e) {
				return; // "gracefully" terminate
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Updates the client canvas with any new shapes that were added.
	 * 
	 * @param list
	 *            - the list of shapes now present on the canvas.
	 */

	/**
	 * Closes the client, disconnects it from the server, and closes all the
	 * data streams involved.
	 */
	public void closeClient() {
		try {
			toServer.writeObject(new DisconnectCommand(username));
			toServer.close();
			fromServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateBaseHP(int newHP) {
		GUI.tilePanel.tileMap.getBase().setHP(newHP);
	}

	public void baseTakeDamage(int damageAmount) {
		BaseTakeDamageCommand dmgCommand = new BaseTakeDamageCommand(username,
				damageAmount);
		try {
			toServer.writeObject(dmgCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setStartingServerHP() {
		UpdateBaseCommand update = new UpdateBaseCommand(username,
				GUI.tilePanel.tileMap.getBase().getHP());
		try {
			toServer.writeObject(update);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startMultiplayerGame(){
		GUI.startMultiplayerGame();
	}
	
	public void addMessage(String message){
		try{
			toServer.writeObject(new AddMessageCommand(username, message));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the new chat in the ChatPanel
	 * 
	 * @param text  -the new updated text
	 */
	public void updateChat(List<String> text) {
		GUI.multiFrame.chatPanel.update(text);
	}
	
	/**
	 * Updates the minimap on this client
	 * 
	 * @param gameMap
	 */
	public void updateMiniMap(Vector<Vector<Tile>> gameMap, int totalResources, int enemiesKilled) {
		//System.out.println("updateMiniMap: received " + gameMap);
		GUI.multiFrame.miniPanel.updateMap(gameMap, totalResources, enemiesKilled);
	}
	
	
	/**
	 * Sends info for minimap to other player
	 */
	public void sendMiniMap(){
		//Only update 3 times a second
//		if(miniMapTick % 10 == 0){
//			
//			miniMapTick = 0;
			//TODO gameMap needs to be serializable first for this to work!!
			//Vector<Vector<Tile>> gameMap = new Vector<Vector<Tile>>(TilePanel.getInstance().tileMap.getGameBoard());
			int totalResources = playerMoney.getMoney();
			int enemiesKilled = Ticker.getInstance().numOfAttackersDead;
			//System.out.println("sendMiniMap: sending " + totalResources);
			try{
				toServer.writeObject(new UpdateMiniMapCommand(username, TilePanel.getInstance().tileMap.getGameBoard(), totalResources, enemiesKilled));
			}catch(Exception e){
				e.printStackTrace();
			}
//		}
//		miniMapTick ++;
	}
	
	/**
	 * Adds the resources given to this clients resources
	 * 
	 * @param resources -the amount of resources received
	 */
	public void receiveResources(int resources) {
		System.out.println(username + "receiveResources: total before: "+ playerMoney.getMoney() + " and now adding " + resources + " resources");
		playerMoney.addMoney(resources);
		System.out.println("new total = " + playerMoney.getMoney());
		
	}
	
	/**
	 * Sends your resources to the other player
	 * 
	 * @param amount
	 */
	public void sendCurrency(int amount) {
		
		if(amount > 0){
			System.out.println(username + " sendCurrency: total before subtracting:" + playerMoney.getMoney());
			if(playerMoney.subtractMoney(amount)){
				
				System.out.println("sending " + amount + " resources to the other player. Total is now " + playerMoney.getMoney());
				try{
					toServer.writeObject(new TransferResourcesCommand(username, amount));
				}catch(Exception e){
					e.printStackTrace();
				}
			}else{
				System.out.println("TDClient: you don't have that much to give");
			}
		}else{
			System.out.println("TDClient: error, can only send positive resources");
		}
		
	}
}
