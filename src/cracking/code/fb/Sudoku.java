package cracking.code.fb;

import java.util.StringTokenizer;

public class Sudoku {

	private static int GRID_SIZE = 9;

	public static void main(String[] args) {
		int[][] m = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
		printBoard(m);
		solveBoard(m);
		System.out.println("\n");
		printBoard(m);

		System.out.println(pigLatinize("pig"));
		System.out.println(pigLatinize("pig latin"));
		System.out.println(pigLatinize("Pig Latin"));
		}

	private static void printBoard(int[][] m) {
		for (int row = 0; row < GRID_SIZE; row++) {
			if (row % 3 == 0 && row != 0)
				System.out.println("---------------------------------");
			for (int col = 0; col < GRID_SIZE; col++) {
				if (col % 3 == 0 && col != 0)
					System.out.print(" | ");
				System.out.print(" " + m[row][col] + " ");
			}
			System.out.println();
		}
	}

	private static boolean solveBoard(int[][] m) {

		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				if (m[row][col] == 0) {
					for (int number = 1; number <= GRID_SIZE; number++) {
						if (isValidEntry(m, row, col, number)) {
							m[row][col] = number;
							if (solveBoard(m)) {
								return true;
							} else {
								m[row][col] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isValidEntry(int[][] m, int row, int col, int number) {
		return !checkRow(m, row, number) && !checkCol(m, col, number) && !checkBox(m, row, col, number);
	}

	private static boolean checkRow(int[][] m, int row, int number) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (m[row][i] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkCol(int[][] m, int col, int number) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (m[i][col] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkBox(int[][] m, int row, int col, int number) {

		int boxi = row - row % 3;
		int boxj = col - col % 3;

		for (int i = boxi; i < boxi + 3; i++) {
			for (int j = boxj; j < boxj + 3; j++) {
				if (m[i][j] == number) {
					return true;
				}
			}
		}

		return false;
	}

	public static String pigLatinize(String phrase) {

		StringTokenizer tokens = new StringTokenizer(phrase);
		StringBuilder res = new StringBuilder("");
		while (tokens.hasMoreTokens()) {
			String word = tokens.nextToken();
			char c = word.charAt(0);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				word = word.substring(1) + "-" + c + "way";
			} else {
				word = word.substring(1) + "-" + c + "ay";
			}
			res.append(word + " ");
		}
		return res.toString();
	}

}
