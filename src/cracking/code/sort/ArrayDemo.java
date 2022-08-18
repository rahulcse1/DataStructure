package cracking.code.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class ArrayDemo {
	public static List<Integer> rotLeft(List<Integer> a, int d) {
		// Write your code here
		int len = a.size();
		// 1,2,3,4,5
		List<Integer> result = new ArrayList<Integer>();
		result.addAll(a);
		for (int i = 0; i < len; i++) {
			// i = 0 , 1, 2, 3, 4
			// j = 1 , 2, 3, 4, 0
			int j = (i + (len - d)) % len;
			result.set(j, a.get(i));
		}
		return result;
	}

	public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
		// Write your code here
		Integer[] res = new Integer[2];
		Hashtable<Integer, Integer> price_index = new Hashtable<>();
		for (int i = 0; i < arr.size(); i++) {
			int p1 = arr.get(i);
			int p2 = m - p1;

			Integer j = price_index.get(p2);
			if (j != null) {
				res[0] = j + 1;
				res[1] = i + 1;
			}
			price_index.put(p1, i);
		}
		return Arrays.asList(res);
	}

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		System.out.println(rotLeft(a, 4));
	}
}
