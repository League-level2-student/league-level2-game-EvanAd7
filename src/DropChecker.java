import java.awt.*;

public class DropChecker {

	Font textFont;

	DropChecker() {

		textFont = new Font("Arial", Font.PLAIN, 30);
	}

	void draw(Graphics g) {

		g.setColor(Color.BLACK);
		g.setFont(textFont);
		
		int textX = 190;
		
		for (int i = 1; i <= 7; i++) {
			g.drawString(i+"", textX, 35);
			textX += 70;
		}
	}
}
