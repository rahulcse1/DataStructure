package cracking.code.fb;

public class SurroundedRegions {

	static int[][] directions = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public static void solve(char[][] m) {
		int row = m.length;
		int col = m[0].length;

		for (int i = 0; i < row; i++) {
			markBoard(m, row, col, i, 0);
			markBoard(m, row, col, i, col - 1);
		}

		for (int i = 0; i < col; i++) {
			markBoard(m, row, col, 0, i);
			markBoard(m, row, col, row - 1, i);
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (m[i][j] == 'M') {
					m[i][j] = 'O';
				} else if (m[i][j] == 'O') {
					m[i][j] = 'X';
				}
			}
		}

	}

	private static void markBoard(char[][] m, int row, int col, int i, int j) {
		if (i < 0 || j < 0 || i >= row || j >= col || m[i][j] != 'O')
			return;
		m[i][j] = 'M';
		for (int[] direction : directions) {
			int x = direction[0] + i;
			int y = direction[1] + j;
			markBoard(m, row, col, x, y);
		}
	}

	public static void main(String[] args) {
		char[][] m = { { 'O', 'O', 'O' }, { 'O', 'O', 'O' }, { 'O', 'O', 'O' } };
		System.out.println(m);
		solve(m);
		System.out.println(m);
	}
}
