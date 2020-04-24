import java.awt.*;

public class Checker {

	int x;
	int y;
	
	static final int WIDTH = 60;
	static final int HEIGHT = 60;
	
	int column = -1;
	int row = -1;
	
	int colorCounter = 0;
	
	Color color;
	Color color2;
	
	Checker(Color color, int row, int column, int x, int y) {
		
		this.x = x;
		this.y = y;
		this.column = column;
		this.row = row;
		this.color = color;
		this.color2 = color;
	}
	
	void draw(Graphics g) {
		
		if (colorCounter % 2 == 0) {
			g.setColor(color);
		} else {
			g.setColor(color2);
		}
		
		colorCounter++;
		g.fillOval(x, y, HEIGHT, WIDTH);
	}
}
