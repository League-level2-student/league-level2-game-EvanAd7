import java.util.*;

public class CheckWinner {

	ArrayList<Checker> checkers;
	public Checker[] winningCheckers = new Checker[4];
	
	static boolean winner = false;
	
	CheckWinner(ArrayList<Checker> checkers) {

		for (int i=0; i<winningCheckers.length; i++) {
			winningCheckers[i] = null;
		}
		
		this.checkers = checkers;
	}

	void getWinner(Checker newChecker) {
		
		int winningCheckersH = 1;
		int winningCheckersV = 1;
		int winningCheckersD1 = 1;
		int winningCheckersD2 = 1;
		
		int incrementH = 1;
		
		int incRow = -1;
		int incCol = -1;

		int row = newChecker.row;
		int col = newChecker.column;

		winningCheckers[0] = newChecker;
		
		while (winningCheckersH < 4 && !arrayFilled()) {
			Checker currentChecker = findChecker(row, col + incrementH);

			if (currentChecker != null && currentChecker.color == newChecker.color) {
				winningCheckers[winningCheckersH] = currentChecker;
				winningCheckersH++;
				col = currentChecker.column;
			} else if (incrementH == 1) {
				row = newChecker.row;
				col = newChecker.column;
				incrementH = -1;
			} else {
				break;
			}
		}
		
		row = newChecker.row;
		col = newChecker.column;
		
		while (winningCheckersV < 4 && !arrayFilled()) {
			Checker currentChecker = findChecker(row - 1, col);

			if (currentChecker != null && currentChecker.color == newChecker.color) {
				winningCheckers[winningCheckersV] = currentChecker;
				winningCheckersV++;
				row = currentChecker.row;
			} else {
				break;
			}
		}
		
		row = newChecker.row;
		col = newChecker.column;
		
		while (winningCheckersD1 < 4 && !arrayFilled()) {
			Checker currentChecker = findChecker(row + incRow, col + incCol);

			if (currentChecker != null && currentChecker.color == newChecker.color) {
				winningCheckers[winningCheckersD1] = currentChecker;
				winningCheckersD1++;
				row = currentChecker.row;
				col = currentChecker.column;
			} else if (incRow == -1 && incCol == -1) {
				row = newChecker.row;
				col = newChecker.column;
				incRow = 1;
				incCol = 1;
			} else {
				break;
			}
		}
		
		incRow = -1;
		incCol = 1;
		
		row = newChecker.row;
		col = newChecker.column;
		
		while (winningCheckersD1 < 4 && !arrayFilled()) {
			
			Checker currentChecker = findChecker(row + incRow, col + incCol);

			if (currentChecker != null && currentChecker.color == newChecker.color) {
				winningCheckers[winningCheckersD2] = currentChecker;
				winningCheckersD2++;
				row = currentChecker.row;
				col = currentChecker.column;
			} else if (incRow == -1 && incCol == 1) {
				row = newChecker.row;
				col = newChecker.column;
				incRow = 1;
				incCol = -1;
			} else {
				break;
			}
		}
		
		if (winningCheckersH == 4 || winningCheckersV == 4 || winningCheckersD1 == 4 || winningCheckersD2 == 4) {
			winner = true;
		}
	}

	Checker findChecker(int row, int col) {

		Checker foundChecker = null;

		for (Checker checker : checkers) {
			if (checker.row == row && checker.column == col) {
				foundChecker = checker;
			}
		}

		return foundChecker;
	}
	
	boolean arrayFilled() {
		if (winningCheckers[3] != null) {
			return true;
		}
		return false;
	}
}
