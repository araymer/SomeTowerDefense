package View;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class MultiplayerFrame extends JFrame{
	
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 300;
	public ChatPanel chatPanel;
	public MiniMapPanel miniPanel;
	
	public MultiplayerFrame(){
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocation(1000, 0);
		this.setTitle("Multi-player Info");
		this.setLayout(new GridLayout(2,0));
		this.setBackground(Color.YELLOW);
		
		chatPanel = new ChatPanel();
		miniPanel = new MiniMapPanel();
		this.add(miniPanel);
		this.add(chatPanel);
		
		
		this.setVisible(true);
	}
	
}
