package View;

import javax.swing.JFrame;

public class MultiplayerFrame extends JFrame{
	
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 300;
	public ChatPanel chatPanel;
	
	public MultiplayerFrame(){
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocation(1000, 0);
		this.setTitle("Multi-player Info");
		chatPanel = new ChatPanel();
		this.add(chatPanel);
		this.setVisible(true);
	}
	
}
