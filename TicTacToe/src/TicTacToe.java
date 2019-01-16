import java.util.Scanner;
public class TicTacToe {
	private char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
	private char player;
	private int count = 0;
	private boolean finish = false;
	private Scanner reader = new Scanner(System.in);
	
	public TicTacToe() {
		count = 0;
		player = 'O';
		this.getPrompt();
	}
	
	private void getPrompt() {
	  this.printBoard();
	  System.out.println("It's Player " + player + "'s turn.");
	  
	  System.out.print("row: ");
	  char row = this.reader.nextLine().charAt(0);
	  
	  System.out.print("column: ");
	  char column = this.reader.nextLine().charAt(0);
	  
	  this.play(row, column);
	  
	  if (this.finish) {
		  return;
	  }
	  
	  this.getPrompt();
	}
	
	private void printBoard() {
		System.out.println();
		System.out.println("   0  1  2");
		for (int i = 0; i < this.board.length; ++i) {
			System.out.print(i);
			for (int j = 0; j < this.board[i].length; ++j) {
				System.out.print(" " + board[i][j] + " ");
				if (j < this.board[i].length - 1) System.out.print("|");
			}
			System.out.println();
			if (i < this.board.length - 1) System.out.println("  ---------");
		}
		System.out.println();
	}
	
	private void play(char row, char column) {
		try {
			int i = Character.getNumericValue(row);
			int j = Character.getNumericValue(column);
			
			if (this.board[i][j] != ' ') {
				System.out.println();
				System.out.println("This cell is already taken, please try again");
				System.out.println();
				return;
			}

			board[i][j] = player;
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println();
			System.out.println("Invalid entry, please try again");
			System.out.println();
			return;
		}

		  ++this.count;
		  String message = this.checkForWin() ? "Player " + player + " Win!" : this.count > 8 ? "Draw!" : "";
		  if (message != "") this.finish = true;
		  if (finish) {
		    printBoard();
			System.out.println();
		    System.out.println(message);
			System.out.println();
		    return;
		  }

		  this.player = this.player == 'O' ? 'X' : 'O';
	}
	
	private boolean checkForWin() {
		return this.horizontalWin() || this.verticalWin() || this.diagonalWin();
	}
	
	private boolean horizontalWin() {
		for (int i = 0; i < this.board.length; ++i) {
			if (this.board[i][0] != ' ' && this.board[i][0] == this.board[i][1] && this.board[i][0] == this.board[i][2]) {
				return true;
			}
		}
		return false;
	}

	private boolean verticalWin() {
		for (int j = 0; j < this.board[0].length; ++j) {
			if (this.board[0][j] != ' ' && this.board[0][j] == this.board[1][j] && this.board[0][j] == this.board[2][j]) {
				return true;
			}
		}
		return false;
	}

	private boolean diagonalWin() {
		if ((this.board[0][0] != ' ' && this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2]) || 
				(this.board[0][2] != ' ' && this.board[0][2] == this.board[1][1] && this.board[0][2] == this.board[2][0])) {
			return true;
		}
		return false;
	}
}
