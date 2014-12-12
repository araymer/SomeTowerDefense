package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * This panel shows the multiplayer chat box to send the other player messages
 * 
 * 
 *
 */
public class ChatPanel extends JPanel{
	
	private JTextArea textArea; // chat log displayed here
	private JTextField textField; // field where user enters text
	

	/**
	 * Constructs a new ChatPanel for given username, using the given OutputStream
	 * 
	 * 
	 */
	public ChatPanel(){
		
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		/* Setup the GUI */
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(300, 300));
		this.setLocation(0, 400);
		this.setPreferredSize(new Dimension(300, 300));
		this.setBackground(Color.BLACK);
		
		// create gui components
		textField = new JTextField();
		JButton enterButton = new JButton("Send");
		
		textField.setPreferredSize(new Dimension(225, 40));
		enterButton.setPreferredSize(new Dimension(50, 40));
		
		// add button and field to a lower panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(textField);
		bottomPanel.add(enterButton);
		
		// add text area and lower panel
		this.add(new JScrollPane(textArea), BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		// create a listener for writing messages to server
		ActionListener listener = new EnterListener();
		
		// attach listener to field & button
		textField.addActionListener(listener);
		enterButton.addActionListener(listener);
		this.setVisible(true);
		
	}
	
	/**
	 * 
	 * Sends the text in the textfield to the server to update the rest of clients
	 *
	 */
	private class EnterListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String s = textField.getText();
			GameGUI.getInstance().getClient().addMessage(s);
			textField.setText("");
		}
	}
	int i = 0;
	
	/**
	 * Updates the chat log. Called by UpdateClientCommands
	 * 
	 * @param messages	the current chat log
	 */
	public void update(List<String> messages) {
		String s = "";
		for (String message: messages)
			s = s + message + "\n";
		
		textArea.setText(s);
		textArea.setCaretPosition(s.length());
		repaint();
	}
}
