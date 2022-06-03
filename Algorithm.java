package sudoku;

public class Algorithm {
	/**@return true, ha a sorban van mar ez a szam. False, ha nincs**/
	public static boolean rowCheck(int[][] grid, int number, int row) {
		for(int i = 0; i < 9; i++) {
				if(grid[row][i] == number) {
					return true;
				}
		}
		return false;
	}
	
	public static boolean columnCheck(int[][] grid, int number, int column) {
		for(int i = 0; i < 9; i++) {
				if(grid[i][column] == number) {
					return true;
				}
		}
		return false;
	}
	
	public static boolean boxCheck(int[][] grid, int number, int row, int column) {
		int boxRow = row-row%3;
		int boxColumn = column-column%3;
		
		for(int i = boxRow; i < boxRow+3; i++) {
			for(int j = boxColumn; j < boxColumn+3; j++) {
				if(grid[i][j] == number) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isOk(int[][] grid, int number, int row, int column) {
		return !rowCheck(grid, number, row) && !columnCheck(grid, number, column) && !boxCheck(grid, number, row, column);
	}
	
	/**van-e a sorban ket ugyanolyan szam**/
	public static boolean checkRowDuplicate(int[][] grid) {
		for (int row = 0; row < 9; row++) {
			for (int j = 0; j < 8; j++) {
				if (grid[row][j] == 0) {
					continue;
				}
				for (int k = j + 1; k < 9; k++) {
					if (grid[row][j] == grid[row][k]) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean checkColumnDuplicate(int[][] grid) {
		for (int column = 0; column < 9; column++) {
			for (int i = 0; i < 8; i++) {
				if (grid[i][column] == 0) {
					continue;
				}
				for (int k = i + 1; k < 9; k++) {
					if (grid[i][column] == grid[k][column]) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean checkBoxDuplicate(int[][] grid) {
		for(int column = 0; column <= 6; column+=3) {
			for(int row = 0; row <= 6; row += 3) {
				for (int i = 0; i < 8; i++) {
					if (grid[row + i / 3][column + i % 3] == 0) {
						continue;
					}
					for (int m = i + 1; m < 9; m++) {
						if (grid[row + i / 3][column + i % 3] == grid[row + m / 3][column + m % 3]) {
							return true;
						}
					}
				}
				
			}
		}
		
		return false;
	}
	
	public static boolean solvable(int[][] grid) {
		return !checkRowDuplicate(grid) && !checkColumnDuplicate(grid) && !checkBoxDuplicate(grid);
	}
	
	public static boolean solve(int[][] grid) {
		if (solvable(grid)) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (grid[i][j] == 0) {
						for (int number = 1; number <= 9; number++) {
							if (isOk(grid, number, i, j)) {
								grid[i][j] = number;
								if (solve(grid)) {
									return true;
								} else {
									grid[i][j] = 0;
								}
							}
						}
						return false;
					}
				}
			}

			return true;
		}
		return false;
	}
}