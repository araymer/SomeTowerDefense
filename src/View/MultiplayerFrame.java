package View;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;

/**
 * Frame for the multiplayer display
 * 
 * @author Team Something
 *
 */
public class MultiplayerFrame extends JFrame {

	private static final int FRAME_HEIGHT = 622;
	private static final int FRAME_WIDTH = 200;
	public ChatPanel chatPanel;
	public MiniMapPanel miniPanel;

	// public MultiplayerInfoPanel infoPanel;

	/**
	 * Constructor for Multiplayer frame
	 * 
	 * @author Team Something
	 *
	 */
	public MultiplayerFrame() {
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocation(1000, 0);
		this.setTitle("Multi-player Info");
		this.setLayout(new GridLayout(2, 0));
		this.setBackground(Color.YELLOW);
		this.setResizable(false);

		chatPanel = new ChatPanel();
		miniPanel = new MiniMapPanel();
		// infoPanel = new MultiplayerInfoPanel();
		this.add(miniPanel);
		this.add(chatPanel);
		// miniPanel.add(infoPanel, BorderLayout.SOUTH);

		this.setVisible(true);
	}

}
