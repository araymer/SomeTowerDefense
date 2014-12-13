package View;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiplayerInfoPanel extends JPanel{
	private JTextArea info;
	private JTextField textField;
	private JButton sendButton;
	
	public MultiplayerInfoPanel(){
		info = new JTextArea("Other player's info:\nResources available: 700\nEnemies killed: 5\nEnemies on map 3");
		textField = new JTextField("0");
		sendButton = new JButton("Send Resources");
		info.setEditable(false);
		
		this.setPreferredSize(new Dimension(200, 150));
		this.setLayout(new GridLayout(2, 1));
		this.add(info);
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2,1));
		bottomPanel.add(sendButton);
		bottomPanel.add(textField);
		this.add(bottomPanel);
		this.setVisible(true);
	}

}
