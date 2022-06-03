package sudoku;

public class SudokuGrid{

	public int[][] grid = new int[9][9];
	public String[] columns = {"", "", "", "", "", "", "", "", ""};
	
	public void init() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				this.grid[i][j] = 0;
			}
		}
	}
	
	public SudokuGrid() {
		init();
	}
	
}
