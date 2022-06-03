package sudoku;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SolveTest {

	SudokuGrid testGrid;
	int[][] solvedGrid = {
			{1, 2, 3, 4, 5, 6 ,7, 8, 9},
			{4, 5, 6, 7, 8, 9, 1, 2, 3},
			{7, 8, 9, 1, 2, 3, 4, 5, 6},
			{2, 1, 4, 3, 6, 5, 8, 9, 7},
			{3, 6, 5, 8, 9, 7, 2, 1, 4},
			{8, 9, 7, 2, 1, 4, 3, 6, 5},
			{5, 3, 1, 6, 4, 2, 9, 7, 8},
			{6, 4, 2, 9, 7, 8, 5, 3, 1},
			{9, 7, 8, 5, 3, 1, 6, 4, 2},
	};
	
	int[][] wrongExample = {
			{9, 2, 1, 6, 3, 7, 5, 8, 4},
			{6, 7, 4, 5, 1, 8, 9, 2, 3},
			{5, 8, 3, 4, 9, 2, 1, 6, 7},
			{2, 6, 9, 8, 5, 4, 3, 7, 1},
			{7, 4, 5, 3, 6, 1, 2, 9, 8},
			{1, 3, 8, 7, 2, 9, 6, 4, 5},
			{8, 5, 6, 2, 7, 3, 4, 1, 9},
			{4, 1, 2, 9, 8, 5, 7, 3, 6},
			{3, 9, 7, 1, 4, 6, 8, 5, 5},	/**Number 5 occurs twice**/
	};
	
	@Before
	public void setUp() {
		testGrid = new SudokuGrid();
	}

	@Test
	public void testInit() {
		boolean valid = false;
		int count = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(testGrid.grid[i][j] == 0) {
					count++;
				}
			}
		}
		if(count == 81) {
			valid = true;
		}
		Assert.assertTrue(valid);
	}
	
	@Test
	public void testSolve() {
		boolean valid = false;
		int count = 0;
		Algorithm.solve(testGrid.grid);
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(testGrid.grid[i][j] == solvedGrid[i][j]) {
					count++;
				}
			}
		}
		if(count == 81) {
			valid = true;
		}
		Assert.assertTrue(valid);
	}
	
	@Test
	public void testRowCheck() {
		Assert.assertTrue(Algorithm.rowCheck(solvedGrid, 9, 1));
		Assert.assertFalse(Algorithm.rowCheck(solvedGrid, 10, 1));
	}
	
	@Test
	public void testColumnCheck() {
		Assert.assertTrue(Algorithm.columnCheck(solvedGrid, 2, 2));
		Assert.assertFalse(Algorithm.columnCheck(solvedGrid, 10, 2));
	}
	
	@Test
	public void testBoxCheck() {
		Assert.assertTrue(Algorithm.boxCheck(solvedGrid, 3, 3, 4));
		Assert.assertFalse(Algorithm.boxCheck(solvedGrid, 20, 3, 4));
	}
	
	@Test
	public void testIsOk() {
		Assert.assertFalse(Algorithm.isOk(solvedGrid, 3, 5, 6));
	}
	
	@Test
	public void testCheckRowDuplicate() {
		Assert.assertFalse(Algorithm.checkRowDuplicate(solvedGrid));
	}
	
	@Test
	public void testCheckColumnDuplicate() {
		Assert.assertFalse(Algorithm.checkColumnDuplicate(solvedGrid));
	}
	
	@Test
	public void testBoxColumnDuplicate() {
		Assert.assertFalse(Algorithm.checkBoxDuplicate(solvedGrid));
	}
	
	@Test
	public void testSolvable() {
		Assert.assertFalse(Algorithm.solvable(wrongExample));
	}
	
}
