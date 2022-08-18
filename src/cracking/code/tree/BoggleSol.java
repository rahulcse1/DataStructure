package cracking.code.tree;

import java.util.List;

public class BoggleSol {

	public static void insert(TrieNode root, String key) {
		TrieNode current = root;
		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			if (current.children[c - 'A'] == null) {
				current.children[c - 'A'] = new TrieNode(c);
			}
			current = current.children[c - 'A'];
		}
		current.isWord = true;
	}

	public static boolean search(TrieNode root, String word) {
		TrieNode node = getNode(root, word);
		return node != null && node.isWord;
	}

	private static TrieNode getNode(TrieNode root, String key) {
		TrieNode current = root;
		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			if (current.children[c - 'A'] == null) {
				return null;
			}
			current = current.children[c - 'A'];
		}
		return current;
	}

	static void searchWord(TrieNode node, char boggle[][], int i, int j, boolean visited[][], String word) {
		if (node.isWord)
			System.out.println(word);
		if (isSafe(i, j, visited)) {
			visited[i][j] = true;
			for (int k = 0; k < 26; k++) {
				char c = (char) (k + 'A');
				TrieNode next = node.children[k];
				if (next != null) {
					if (isSafe(i, j + 1, visited) && boggle[i][j + 1] == c)
						searchWord(next, boggle, i, j + 1, visited, word + c);
					if (isSafe(i, j - 1, visited) && boggle[i][j - 1] == c)
						searchWord(next, boggle, i, j - 1, visited, word + c);

					if (isSafe(i + 1, j + 1, visited) && boggle[i + 1][j + 1] == c)
						searchWord(next, boggle, i + 1, j + 1, visited, word + c);
					if (isSafe(i + 1, j, visited) && boggle[i + 1][j] == c)
						searchWord(next, boggle, i + 1, j, visited, word + c);
					if (isSafe(i + 1, j - 1, visited) && boggle[i + 1][j - 1] == c)
						searchWord(next, boggle, i + 1, j - 1, visited, word + c);

					if (isSafe(i - 1, j + 1, visited) && boggle[i - 1][j + 1] == c)
						searchWord(next, boggle, i - 1, j + 1, visited, word + c);
					if (isSafe(i - 1, j, visited) && boggle[i - 1][j] == c)
						searchWord(next, boggle, i - 1, j, visited, word + c);
					if (isSafe(i - 1, j - 1, visited) && boggle[i - 1][j - 1] == c)
						searchWord(next, boggle, i - 1, j - 1, visited, word + c);
				}

			}
			visited[i][j] = false;
		}
	}

	static boolean isSafe(int i, int j, boolean visited[][]) {
		return (i >= 0 && i < 3 && j >= 0 && j < 3 && !visited[i][j]);
	}

	static void findWords(char boggle[][], TrieNode root) {
		int row = boggle.length;
		int col = boggle[0].length;
		boolean[][] visited = new boolean[row][col];
		TrieNode current = root;
		StringBuilder word = new StringBuilder("");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				char c = boggle[i][j];
				int index = c - 'A';
				if (current.children[index] != null) {
					word.append(c);
					searchWord(current.children[index], boggle, i, j, visited, word.toString());
					word = new StringBuilder("");
				}
			}
		}
	}

	public boolean wordBreak(String str, List<String> wordDict) {
		TrieNode trie = new TrieNode('\0');
		for (String word : wordDict) {
			insert(trie, word);
		}
		boolean[] dp = new boolean[str.length() + 1];
		char[] s = str.toCharArray();
		dp[0] = true;

		for (int i = 0; i < s.length; i++) {
			if (!dp[i])
				continue;
			int j = i;
			TrieNode curr = trie;
			while (j < s.length && curr.children[s[j] - 'a'] != null) {
				curr = curr.children[s[j++] - 'a'];
				if (curr.isWord)
					dp[j] = true;
			}
		}
		return dp[s.length];
	}

	public static void main(String[] args) {
		String dictionary[] = { "GEEKS", "FOR", "QUIZ", "GEE" };
		TrieNode root = new TrieNode('\0');
		int n = dictionary.length;
		for (int i = 0; i < n; i++)
			insert(root, dictionary[i]);

		char boggle[][] = { { 'G', 'I', 'Z' }, { 'U', 'E', 'K' }, { 'Q', 'S', 'E' } };
		findWords(boggle, root);

	}

}
