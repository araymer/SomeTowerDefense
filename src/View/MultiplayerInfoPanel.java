package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Displays game information in the multiplayer game mode.
 * 
 * @author Team Something
 *
 */
public class MultiplayerInfoPanel extends JPanel {
	private JTextArea info;
	private JTextField textField;
	private JButton sendButton;

	/**
	 * Constructor for the MultiplayerInfoPanel
	 * 
	 * @author Team Something
	 *
	 */
	public MultiplayerInfoPanel() {
		info = new JTextArea("Other player's info:");
		textField = new JTextField("0");
		sendButton = new JButton("Send Resources");
		info.setEditable(false);

		this.setPreferredSize(new Dimension(200, 150));
		this.setLayout(new GridLayout(2, 1));
		this.add(info);
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));
		bottomPanel.add(sendButton);
		bottomPanel.add(textField);
		this.add(bottomPanel);

		// create a listener for writing messages to server
		ActionListener listener = new EnterListener();

		// attach listener to field & button
		textField.addActionListener(listener);
		sendButton.addActionListener(listener);
		this.setVisible(true);
	}

	/**
	 * 
	 * Sends the text in the textfield to the server to update the rest of
	 * clients
	 *
	 */
	private class EnterListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String s = textField.getText();
			try {
				int amount = Integer.parseInt(s);
				GameGUI.getInstance().getClient().sendCurrency(amount);
				textField.setText("");
			} catch (Exception e) {
				textField.setText("error: not a #");
			}

		}
	}

	/**
	 * Refreshes the information about other player
	 * 
	 * @param totalResources
	 * @param enemiesKilled
	 * @param enemyNum
	 */
	public void updateInfo(int totalResources, int enemiesKilled, int enemyNum) {
		info.setText("Other player's info:\nResources available: "
				+ totalResources + "\nEnemies killed: " + enemiesKilled
				+ "\nEnemies on map: " + enemyNum);
	}

}
