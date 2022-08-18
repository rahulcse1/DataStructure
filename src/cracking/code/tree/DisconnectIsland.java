package cracking.code.tree;

import java.util.HashSet;
import java.util.Set;

public class DisconnectIsland {

	public static void main(String[] args) {
		int[][] m = { { 1, 1 } };
		System.out.println(minDays(m));
	}

	static int R;
	static int C;
	static int[][] dirs = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static int[][] diagonal = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	public static void dfs(int[][] m, int i, int j, boolean[][] visited, int islandid) {
		visited[i][j] = true;
		for (int k = 0; k < 4; k++) {
			int x = i + dirs[k][0];
			int y = j + dirs[k][1];
			if (isSafe(m, x, y, visited)) {
				m[x][y] = islandid;
				dfs(m, x, y, visited, islandid);
			}
		}
	}

	static boolean isSafe(int[][] m, int r, int c, boolean[][] visited) {
		return (r >= 0 && r < R && c >= 0 && c < C && (m[r][c] == 1 && !visited[r][c]));
	}

	public static int minDays(int[][] m) {

		R = m.length;
		C = m[0].length;
		boolean[][] visited = new boolean[R][C];
		Set<Integer> set = new HashSet();

		int islandid = 2;
		int min = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (m[i][j] == 1 && !visited[i][j]) {
					m[i][j] = islandid;
					dfs(m, i, j, visited, islandid);
					set.add(islandid);
					islandid++;
				}
			}
		}

		for (Integer key : set) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (m[i][j] == key) {
						for (int k = 0; k < 4; k++) {
							int x = i + diagonal[k][0];
							int y = j + diagonal[k][1];
							if (x > -1 && x < R && y > -1 && y < C && m[x][y] != 0) {
								min++;
								m[x][y] = 0;
							}
						}
						for (int k = 0; k < 4; k++) {
							int x = i + dirs[k][0];
							int y = j + dirs[k][1];
							if (x > -1 && x < R && y > -1 && y < C && m[x][y] != 0) {
								min++;
								m[x][y] = 0;
							}
						}
					}
				}
			}
		}

		return min;
	}
}
