package cracking.code.fb;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

	public static void main(String[] args) {
		System.out.println(findMinimumWindowSubstring("ADOBECODEBANC", "ABC"));
	}

	private static String findMinimumWindowSubstring(String s, String t) {
		if (s.isEmpty() && t.isEmpty())
			return "";
		if (t.length() > s.length())
			return "-1";

		Map<Character, Integer> tfreq = new HashMap<>();

		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			tfreq.put(c, tfreq.getOrDefault(c, 0) + 1);
		}

		int i = 0, j = 0, count = tfreq.size();
		int left = 0, right = s.length() - 1, min = s.length();
		boolean found = false;

		while (j < s.length()) {
			char c = s.charAt(j++);
			if (tfreq.containsKey(c)) {
				tfreq.put(c, tfreq.get(c) - 1);
				if (tfreq.get(c) == 0) {
					count--;
				}
			}

			if (count > 0)
				continue;

			while (count == 0) {
				char c1 = s.charAt(i++);
				if (tfreq.containsKey(c1)) {
					tfreq.put(c1, tfreq.get(c1) + 1);
					if (tfreq.get(c1) > 0) {
						count++;
					}
				}
			}

			if ((j - i) < min) {
				left = i;
				right = j;
				min = j - i;
				found = true;
			}

		}

		return !found ? "" : s.substring(left - 1, right);

	}

}
