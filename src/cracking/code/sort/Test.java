package cracking.code.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Test {

	public static void main(String[] args) {
		// String[] strs = { "flower", "flow", "flight" };
		// longestCommonPrefix(strs);

		List<Integer> a = Arrays.asList(18, 49, 15, 30, 56, 20, 49, 67, 82, 69, 84, 63, 93, 87, 66, 17, 38, 32, 17, 32,
				94, 66, 67, 63, 48, 64, 84, 82, 87, 18, 79, 64, 79, 15, 71, 94, 59, 5, 22, 59, 4, 98, 81, 4, 98, 73, 69,
				88, 30, 81, 48, 56, 71, 20, 93, 22, 73, 5, 88);
		System.out.println(lonelyinteger(a));
		pangrams("We promptly judged antique ivory buckles for the next prize");
	}

	public static String pangrams(String s) {
		// Write your code here
		s = s.toUpperCase();
		List<Character> chs = new ArrayList<Character>();
		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			if (!Character.isSpaceChar(c)) {
				chs.add(c);
			}
		}
		return "false";
	}

	public static int diagonalDifference(List<List<Integer>> arr) {
		// Write your code here
		int l = arr.size();
		int d1 = 0;
		for (int i = 0; i < l; i++) {
			d1 += arr.get(i).get(i);
		}
		int d2 = 0;
		int i = l - 1;
		int j = 0;
		while (j <= i) {
			d2 += arr.get(i).get(j);
			i--;
			j++;
		}
		return Math.abs(d1 - d2);
	}

	public static int lonelyinteger(List<Integer> a) {
		// Write your code here
		return a.stream().filter(i -> Collections.frequency(a, i) == 1).iterator().next();

	}

	public int lengthOfLastWord(String s) {
		String[] strs = s.split(" ");
		return strs[strs.length - 1].length();
	}

	public static String longestCommonPrefix(String[] strs) {
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
			}
		}
		return prefix;
	}

	public static boolean isPalindrome(int x) {

		String nums = String.valueOf(x);
		char[] chs = nums.toCharArray();
		int i = 0;
		int j = nums.length() - 1;
		while (i < j) {
			if (chs[i] != chs[j]) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	public static int[] topKFrequent(int[] a, int k) {
		int[] res = new int[k];
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			hm.put(a[i], hm.getOrDefault(a[i], 0) + 1);
		}

		PriorityQueue<Integer> heap = new PriorityQueue<>((v1, v2) -> hm.get(v1) - hm.get(v2));

		for (int key : hm.keySet()) {
			heap.add(key);
		}
		for (int i = 0; i < k; i++) {
			res[i] = heap.poll();
		}
		return res;
	}

}
