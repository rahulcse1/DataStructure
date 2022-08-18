package cracking.code.tree;

import java.util.HashMap;

public class NoofIsland {
	static int row;
	static int col;
	static int ilandsize;
	static int[][] dirs = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public static int numIslands(char[][] m) {

		row = m.length;
		col = m[0].length;

		boolean[][] visited = new boolean[row][col];

		HashMap<Integer, Integer> map = new HashMap<>();
		int islandid = 1;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (m[i][j] == '1' && !visited[i][j]) {
					ilandsize = 1;
					dfs(m, i, j, visited);
					map.put(islandid, ilandsize);
					islandid++;
					ilandsize=0;
				}
			}
		}

		return map.size();
	}

	public static void dfs(char[][] m, int i, int j, boolean[][] visited) {
		visited[i][j] = true;
		for (int k = 0; k < 4; k++) {
			int x = i + dirs[k][0];
			int y = j + dirs[k][1];
			if (isSafe(m, x, y, visited)) {
				ilandsize++;
				dfs(m, x, y, visited);
			}
		}
	}

	static boolean isSafe(char[][] m, int i, int j, boolean[][] visited) {
		return (i >= 0 && i < row && j >= 0 && j < col && m[i][j] == '1' && !visited[i][j]);
	}

	public static void main(String[] args) {
		char[][] m = { 
				{ '1', '1', '0', '0', '0' }, 
				{ '1', '1', '0', '0', '0' }, 
				{ '0', '0', '1', '0', '0' },
				{ '0', '0', '0', '1', '1' } };
		System.out.println(numIslands(m));
	}

}
