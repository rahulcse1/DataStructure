package cracking.code.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LargestIsland {

	public static void main(String[] args) {
		int[][] m = { { 1, 1 }, { 1, 1 } };
		System.out.println(largestIsland(m));
	}

	static int R;
	static int C;
	static int ilandsize;
	static int[][] dirs = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static boolean isSafe(int[][] m, int r, int c, boolean[][] visited) {
		return (r >= 0 && r < R && c >= 0 && c < C && (m[r][c] == 1 && !visited[r][c]));
	}

	public static int largestIsland(int[][] m) {
		R = m.length;
		C = m[0].length;

		boolean[][] visited = new boolean[R][C];
		int max = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		int islandid = 2;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (m[i][j] == 1 && !visited[i][j]) {
					ilandsize = 1;
					m[i][j] = islandid;
					dfs(m, i, j, visited, islandid);
					map.put(islandid, ilandsize);
					islandid++;
					ilandsize = 0;
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (m[i][j] == 0) {
					Set<Integer> set = new HashSet<>();
					for (int k = 0; k < 4; k++) {
						int x = i + dirs[k][0];
						int y = j + dirs[k][1];
						if (x > -1 && x < R && y > -1 && y < C && m[x][y] != 0) {
							set.add(m[x][y]);
						}
					}

					int sum = 1;
					for (int num : set) {
						int value = map.get(num);
						sum += value;
					}
					max = Math.max(max, sum);
				}
			}
		}

		if(max == 0 && map.size() == 1) {
			max = map.get(map.keySet().iterator().next());
		}
		
		return max;
	}

	public static void dfs(int[][] m, int i, int j, boolean[][] visited, int islandid) {
		visited[i][j] = true;
		for (int k = 0; k < 4; k++) {
			int x = i + dirs[k][0];
			int y = j + dirs[k][1];
			if (isSafe(m, x, y, visited)) {
				ilandsize++;
				m[x][y] = islandid;
				dfs(m, x, y, visited, islandid);
			}
		}
	}

}
