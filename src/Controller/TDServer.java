package Controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

import Model.Tile;

import command.Command;
import command.DisconnectCommand;
import command.UpdateClientCommand;




public class TDServer {
	
	private ServerSocket socket;
	private int port;
	private Vector<Vector<Tile>> masterList;
	private HashMap<String, ObjectOutputStream> outputs; // map of all connected
															// users' output
															// streams

	/**
	 * Constructor for NPServer. Creates a new ServerSocket on port 4007 and
	 * then creates a new thread to accept clients.
	 */
	public TDServer() {
		port = 4007;
		masterList = new Vector<Vector<Tile>>();
		outputs = new HashMap<String, ObjectOutputStream>();

		try {
			socket = new ServerSocket(port);
			System.out.println("Server up");

			new Thread(new ClientAccepter()).start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This thread listens for and sets up connections to new clients.
	 */
	private class ClientAccepter implements Runnable {
		public void run() {
			try {
				while (true) {
					// accept a new client, get output & input streams
					Socket client = socket.accept();
					System.out.println("Client accepted");
					ObjectInputStream fromClient = new ObjectInputStream(
							client.getInputStream());
					ObjectOutputStream toClient = new ObjectOutputStream(
							client.getOutputStream());

					// read the client's name
					String clientUsername = (String) fromClient.readObject();

					// map client name to output stream
					outputs.put(clientUsername, toClient);

					// spawn a thread to handle communication with this client
					new Thread(new ClientHandler(fromClient)).start();

					System.out.println("Added new client: " + clientUsername);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This thread reads and executes commands sent by a client.
	 */
	private class ClientHandler implements Runnable {
		private ObjectInputStream input; // the input stream from the client

		public ClientHandler(ObjectInputStream input) {
			this.input = input;
		}

		public void run() {
			try {
				updateClients();
				while (true) {
					// read a command from the client, execute on the server
					Command<TDServer> command = (Command<TDServer>) input
							.readObject();
					command.execute(TDServer.this);

					// terminate if client is disconnecting
					if (command instanceof DisconnectCommand) {
						input.close();
						return;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

//	public void addShapes(Shapes shapes) {
//		masterList.add(shapes);
//		updateClients();
//	}

	private void updateClients() {
		// make an UpdateClientCommmand, write to all connected users
		UpdateClientCommand update = new UpdateClientCommand();
		try {
			for (ObjectOutputStream out : outputs.values())
				out.writeObject(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect(String clientName) {
		try {
			outputs.get(clientName).close(); // close output stream
			outputs.remove(clientName); // remove from map

			// add notification message
			System.out.println(clientName + " disconnected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TDServer();
	}

}
