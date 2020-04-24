import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	final int MENU = 0;
	final int GAME = 1;
	int currentState = MENU;

	Font titleFont;
	Font textFont;

	DropChecker dropper;
	ObjectManager objManager;

	Timer frameRate;

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		}
	}

	GamePanel() {

		titleFont = new Font("Arial", Font.PLAIN, 64);
		textFont = new Font("Arial", Font.PLAIN, 30);

		dropper = new DropChecker();
		objManager = new ObjectManager();

		frameRate = new Timer(1000 / 5, this);
		frameRate.start();

		if (needImage) {
			loadImage("ConnectFour.png");
		}
	}

	void drawMenuState(Graphics g) {

		g.setColor(Color.BLUE);
		g.fillRect(0, 0, ConnectFour.WIDTH, ConnectFour.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("CONNECT FOUR", 150, 100);

		g.setFont(textFont);
		g.drawString("Press ENTER to start", 260, 250);
		g.drawString("Press SPACE for instructions", 210, 350);
	}

	void drawGameState(Graphics g) {

		g.drawImage(image, 0, 0, null);
		dropper.draw(g);

		if (!objManager.checkWinner()) {
			if (objManager.whoseTurn.equals("red")) {
				g.setFont(textFont);
				g.setColor(Color.RED);
				g.drawString("RED player's turn", 280, 495);
			} else {
				g.setFont(textFont);
				g.setColor(new Color(255, 235, 55));
				g.drawString("YELLOW player's turn", 280, 495);
			}
		}
		
		if (objManager.checkWinner()) {
			if (objManager.winnerColor == Color.RED) {
				g.setFont(textFont);
				g.setColor(Color.RED);
				g.drawString("RED player wins!", 150, 495);
				g.setColor(Color.BLACK);
				g.drawString("- Press ENTER to continue", 385, 495);
			} else {
				g.setFont(textFont);
				g.setColor(new Color(255, 235, 55));
				g.drawString("YELLOW player wins!", 100, 495);
				g.setColor(Color.BLACK);
				g.drawString("- Press ENTER to continue", 400, 495);
			}
		}

		for (Checker checker : objManager.checkers) {
			checker.draw(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == GAME) {
				currentState = MENU;
				if (objManager.checkWinner()) {
					objManager = new ObjectManager();
					objManager.checkWinner.winner = false;
				}
			} else {
				currentState++;
			}
		}

		if (currentState == GAME) {
			if (!objManager.checkWinner()) {
				if (e.getKeyCode() == KeyEvent.VK_1) {
					objManager.dropChecker(1);
				} else if (e.getKeyCode() == KeyEvent.VK_2) {
					objManager.dropChecker(2);
				} else if (e.getKeyCode() == KeyEvent.VK_3) {
					objManager.dropChecker(3);
				} else if (e.getKeyCode() == KeyEvent.VK_4) {
					objManager.dropChecker(4);
				} else if (e.getKeyCode() == KeyEvent.VK_5) {
					objManager.dropChecker(5);
				} else if (e.getKeyCode() == KeyEvent.VK_6) {
					objManager.dropChecker(6);
				} else if (e.getKeyCode() == KeyEvent.VK_7) {
					objManager.dropChecker(7);
				}

				objManager.checkWinner();
			}
		}

		if (currentState == MENU) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				JOptionPane.showMessageDialog(null, "The object of the game is to create a line of four consecutive\n"
						+ "checkers of the same color in a row, whether it be horizontally, vertically, or diagonally.\n"
						+ "This game requires two players and their turns will alternate. On their turn, a player will\n"
						+ "drop a colored checker into a column, selected by pressing a number key on their keyboard.\n"
						+ "The number keys to press are marked by numbers over each column at the top of the board.\n"
						+ "The first player to connect four checkers in a row of the same color wins!");
			}
		}

		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		repaint();
	}
}