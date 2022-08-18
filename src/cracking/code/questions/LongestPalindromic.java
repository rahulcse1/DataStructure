package cracking.code.questions;

import java.util.HashMap;

public class LongestPalindromic {

	static String longestPalSubstr(String s) {
		int l = s.length();
		// max length
		int maxl = 1;
		int start = 0;
		for (int i = 0; i < l; i++) {
			for (int j = i; j < l; j++) {
				int flag = 1;
				for (int k = 0; k < (j - i + 1) / 2; k++)
					if (s.charAt(i + k) != s.charAt(j - k))
						flag = 0;

				if (flag != 0 && (j - i + 1) > maxl) {
					start = i;
					maxl = j - i + 1;
				}
			}
		}
		String res = "";
		for (int i = start; i <= start + maxl - 1; ++i)
			res += s.charAt(i);

		return res;
	}

	static String longestPalSubstr1(String s) {

		if (s == null || s.length() < 1) {
			return s;
		}
		int start = 0;
		int end = 0;
		for (int i = 0; i < s.length(); i++) {
			int l1 = maxl(s, i, i);
			int l2 = maxl(s, i, i + 1);
			int len = Math.max(l1, l2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}

		return s.substring(start, end + 1);
	}

	static int maxl(String s, int left, int right) {
		if (s == null || left > right) {
			return 0;
		}
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}

	static int minOperations(int[] a) {
		// Write your code here

		return 0;
	}

	static int longestUniqueSubsttr(String s) {

		HashMap<Character, Integer> seen = new HashMap<>();
		int maxl = 0;
		int start = 0;
		for (int end = 0; end < s.length(); end++) {
			if (seen.containsKey(s.charAt(end))) {
				start = Math.max(start, seen.get(s.charAt(end)) + 1);
			}
			seen.put(s.charAt(end), end);
			maxl = Math.max(maxl, end - start + 1);
		}
		return maxl;
	}

	public static void main(String[] args) {
		System.out.println(longestPalSubstr1("AMARJEET"));
		System.out.println(longestUniqueSubsttr("AMARJEET"));
	}
}
