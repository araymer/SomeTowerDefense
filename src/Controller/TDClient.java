package Controller;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JOptionPane;

import View.GameGUI;
import command.BaseTakeDamageCommand;
import command.Command;
import command.DisconnectCommand;
import command.UpdateBaseCommand;


public class TDClient {
	
	private GameController gameController;
	private GameGUI GUI;
	private Socket server;
	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	String username;
	
	public static void main(String[] args) {
		new TDClient();
	}
	
	/**
	 * Constructs a new TDClient and prompts the user to enter in the host
	 * address, host port, and a user name that the client keeps track of. It
	 * opens the connection to the server in a new thread.
	 */
	public TDClient() {

		// ask the user for a host, port, and user name
//		String host = JOptionPane.showInputDialog("Host address:");
//		String port = JOptionPane.showInputDialog("Host port:");
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
					Command c = (Command) fromServer
							.readObject();
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
//	public void update(ArrayList<Shapes> list) {
//		GUI.update(list);
//	}

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
	
	public void updateBaseHP(int newHP){
		GUI.tilePanel.tileMap.getBase().setHP(newHP);
	}
	
	public void baseTakeDamage(int damageAmount){
		BaseTakeDamageCommand dmgCommand = new BaseTakeDamageCommand(username, damageAmount);
		try {
			toServer.writeObject(dmgCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setStartingServerHP(){
		UpdateBaseCommand update = new UpdateBaseCommand(username, GUI.tilePanel.tileMap.getBase().getHP());
		try {
			toServer.writeObject(update);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a command to the server when a new shape is drawn on the client.
	 * 
	 * @param shapes
	 *            - the shape that was drawn on the client that the server is
	 *            being told about.
	 */
//	public void addObject(Shapes shapes) {
//		AddObjectCommand addObj = new AddObjectCommand(shapes);
//		try {
//			toServer.writeObject(addObj);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
