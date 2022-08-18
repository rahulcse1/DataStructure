package cracking.code.tree;

import java.util.HashMap;

public class MatrixIland {
	static int count;

	public static void main(String args[]) {
		int M[][] ={{ 0, 0, 1, 1, 0 }, 
					{ 1, 0, 1, 1, 0 }, 
					{ 0, 1, 0, 0, 0 },
					{ 0, 0, 0, 0, 1 } };
		System.out.println(largestRegion(M));
	}

	static boolean isSafe(int[][] m, int r, int c, boolean[][] visited) {
		return (r >= 0 && r < m.length && c >= 0 && c < m[0].length
				&& (m[r][c] == 1 && !visited[r][c]));
	}

	/*
	 * works fine if diagonal condition satisfied 1 node can be connected
	 * horizontal, vertical and diagonal.. specific to this example if diagonal
	 * condition is not there then ans should be 4.
	 */
	private static int largestRegion(int[][] m) {

		boolean[][] visited = new boolean[m.length][m[0].length];
		int result = 0;
		
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (m[i][j] == 1 && !visited[i][j]) {
					count = 1;
					dfs1(m, i, j, visited);
					result = Math.max(result, count);
				}
			}
		}
		return result;
	}

	static int[][] dirs = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	public static void dfs1(int[][] m, int i, int j, boolean[][] visited) {
		visited[i][j] = true;
		for (int k = 0; k < 4; k++) {
			if (isSafe(m, i + dirs[k][0], j + dirs[k][1], visited)) {
				dfs(m, i + dirs[k][0], j + dirs[k][1], visited);
			}
		}
	}

	private static void dfs(int[][] m, int i, int j, boolean[][] visited) {

		int[] rowNbr = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] colNbr = { -1, 0, 1, -1, 1, -1, 0, 1 };
		visited[i][j] = true;

		for (int k = 0; k < 8; k++) {
			if (isSafe(m, i + rowNbr[k], j + colNbr[k], visited)) {
				count++;
				dfs(m, i + rowNbr[k], j + colNbr[k], visited);
			}
		}
	}
}
