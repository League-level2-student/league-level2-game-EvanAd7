import javax.swing.*;

public class ConnectFour {
	
	JFrame frame;
	GamePanel gamePanel;
	
	public static final int WIDTH = 835;
	public static final int HEIGHT = 545;
	
	public static final int BOARD_HEIGHT = 466;
	public static final int BOARD_WIDTH = 100;
	
	public static void main(String[] args) {
		
		ConnectFour connectFour = new ConnectFour();
		connectFour.setup();
	}
	
	ConnectFour() {
		
		frame = new JFrame("Connect Four");
		gamePanel = new GamePanel();
	}
	
	void setup() {
		
		frame.add(gamePanel);
		frame.addKeyListener(gamePanel);
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
