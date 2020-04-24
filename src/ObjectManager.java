import java.awt.Color;
import java.util.*;

public class ObjectManager {

	ArrayList<Checker> checkers = new ArrayList<Checker>();

	boolean colorSwitched = false;
	int numSwitchedCheckers = 0;
	
	int checkerX = 0;

	String whoseTurn = "red";
	static Color winnerColor = null;

	CheckWinner checkWinner;

	ObjectManager() {

		checkWinner = new CheckWinner(checkers);
	}

	void dropChecker(int input) {

		int lowestY = ConnectFour.BOARD_HEIGHT;
		int row = 1;
		boolean makeChecker = true;

		for (Checker checker : checkers) {
			if (input == checker.column) {
				row++;
				if (checker.y < lowestY) {
					if (lowestY > 140) {
						lowestY = checker.y;
					} else {
						makeChecker = false;
						break;
					}
				}
			}
		}

		if (makeChecker) {
			checkerX = ConnectFour.BOARD_WIDTH + (input * (Checker.WIDTH + 10));
			Checker newChecker = new Checker(checkerColor(), row, input, checkerX, lowestY - Checker.HEIGHT - 10);
			checkers.add(newChecker);
			checkWinner.getWinner(newChecker);
		}
	}

	Color checkerColor() {
		if (checkers.size() % 2 == 0) {
			whoseTurn = "yellow";
			return Color.RED;
		} else {
			whoseTurn = "red";
			return Color.YELLOW;
		}
	}

	boolean checkWinner() {

		if (checkWinner.winner) {
			for (Checker checker : checkWinner.winningCheckers) {
				if (checker != null) {
					if (!colorSwitched) {
						checker.colorCounter = 0;
						numSwitchedCheckers++;
						winnerColor = checker.color;
						checker.color2 = Color.GREEN;
					}
				}
			}
			
			if (numSwitchedCheckers == 4) {
				colorSwitched = true;
			}
		}

		return checkWinner.winner;
	}
}
