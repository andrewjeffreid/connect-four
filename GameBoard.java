public class GameBoard {
    
    /** This is our game board */
    private char[][] board = new char[6][7];

    /** new game */
    public void initializeBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = '.';
            }
        }
    }

    /** display board */
    public void printBoard() {
        for (int i = 0; i < 7; i++) {
            System.out.print(i);
        }
        System.out.println();
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    /** move player one */
    public int move(int column, char peice) {
        int rowOfMove = -1;
            for (int row = 5; row >= 0; row--) {
                if (board[row][column] == '.') {
                    board[row][column] = peice;
                    rowOfMove = row;
                    break;
                }
            }
        return rowOfMove;
    }
    
    /**  */
    public boolean validateHorizontal(int x, int y, char piece){
		int counter = 0;
		for (int j=0; j<board[y].length; j++){
			if (board[y][j] != piece){
				counter = 0;
			}
			else{
				counter += 1;
			}
			if (counter == 4){
				break;
			}
		}
		if (counter == 4){
			return true;
		}
		else{
			return false;
		}
	}

    /**  */
	public boolean validateVertical(int x, int y, char piece){
		int counter = 0;
		for (int i=0; i<board.length; i++){
			if (board[i][x] != piece){
				counter = 0;
			}
			else{
				counter += 1;
			}
			if (counter == 4){
				break;
			}
		}
		if (counter == 4){
			return true;
		}
		else{
			return false;
		}
	}

    /**  */
	public boolean validateDiagonalLeft(int x, int y, char piece){
		int bottomXL = x - (5-y);
		int bottomYL = 5;

		if (bottomXL < 0){
			bottomYL = 5+bottomXL;
			bottomXL = 0;
		}

		int counter = 0;
		for (int i=bottomYL, j=bottomXL; i>=0 && j<board[y].length; i--, j++){

			if (board[i][j] != piece){
				counter = 0;
			}
			else{
				counter += 1;
			}
			if (counter == 4){
				break;
			}
		}
		if (counter == 4){
			return true;
		}
		else{
			return false;
		}
	}

    /**  */
	public boolean validateDiagonalRight(int x, int y, char piece){
		int bottomXR = x + (5-y);
		int bottomYR = 5;

		if (bottomXR > 6){
			bottomYR = 5-(bottomXR-6);
			bottomXR = 6;
		}

		int counter = 0;
		for (int i=bottomYR, j=bottomXR; i>=0 && j>=0; i--, j--){
			if (board[i][j] != piece){
				counter = 0;
			}
			else{
				counter += 1;
			}
			if (counter == 4){
				break;
			}
		}
		if (counter == 4){
			return true;
		}
		else {
			return false;
		}
	}

	public int[][] possibleMoves(){
		int[][] moves = new int[2][7];
		for (int j=0; j<board[0].length; j++){
			for (int i=board.length-1; i>=0; i--){
				if (board[i][j]=='.'){
					moves[0][j] = j;
					moves[1][j] = i;
					break;
				}
				else{
					moves[0][j] = -1;
					moves[1][j] = -1;
				}
			}
		}
		return moves;
	}
	public int check4(int[][] moves, char piece){
		int correctMove = -1;
		for (int j=0; j<board[0].length; j++){
			if (moves[0][j] == -1){
				continue;
			}
			else{
				int x = moves[0][j]; 
				int y = moves[1][j]; 
				board[y][x] = piece;
				if (validateVertical(x,y,piece) || validateHorizontal(x,y,piece)|| validateDiagonalLeft(x,y,piece)|| validateDiagonalRight(x,y,piece)){
					board[y][x] = '.';
					correctMove = x;
				}
				board[y][x] = '.';
			}
		}
		return correctMove;
	}
}