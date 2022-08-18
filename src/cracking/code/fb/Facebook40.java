package cracking.code.fb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Facebook40 {

	static class Pair {
		public Pair(int i, int j) {
			this.first = i;
			this.second = j;
		}

		int first;
		int second;
	}

	public static int[][] merge(int[][] a) {
		int len = a.length;
		if (len <= 1)
			return a;
		// Arrays.sort(a, (x, y) -> x[0] - y[0]);
		List<Pair> pairs = new ArrayList<Pair>();
		pairs.add(new Pair(a[0][0], a[0][1]));
		for (int k = 1; k < a.length; k++) {
			int[] start = a[k];
			int x1 = start[0];
			int y1 = start[1];

			Pair end = pairs.get(pairs.size() - 1);
			int x2 = end.first;
			int y2 = end.second;

			if (x2 >= x1) {
				end.first = Math.min(x1, x2);
			}
			if (y2 >= x1) {
				end.second = Math.max(y1, y2);
			} else {
				Pair pair = new Pair(x1, y1);
				pairs.add(pair);
			}
		}
		int[][] res = new int[pairs.size()][2];

		for (int i = 0; i < pairs.size(); i++) {
			Pair pair = pairs.get(i);
			res[i][0] = pair.first;
			res[i][1] = pair.second;
		}

		return res;
	}

	// {2, 3, 10, 6, 4, 8, 1}
	static int maxDiff(int[] a) {
		int l = a.length;
		int diff = -1;
		int maxRight = a[l - 1];
		for (int i = l - 2; i >= 0; i--) {
			if (a[i] > maxRight) {
				maxRight = a[i];
			} else {
				if (maxRight - a[i] > diff) {
					diff = maxRight - a[i];
				}
			}
		}
		return diff;
	}

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> dp = new ArrayList<>();
		helper(nums, 0, dp, res);
		return res;
	}

	public static void helper(int[] arr, int index, List<Integer> dp, List<List<Integer>> res) {
		res.add(new ArrayList<>(dp));

		for (int i = index; i < arr.length; ++i) {
			dp.add(arr[i]);
			helper(arr, i + 1, dp, res);
			dp.remove(dp.size() - 1);
		}
	}

	public static boolean balancedSplitExists(int[] arr) {
		// Write your code here
		int i = 0;
		int j = arr.length - 1;
		int lsum = 0, rsum = 0;
		Arrays.sort(arr);
		while (i <= j) {
			rsum += arr[j--];
			while (i <= j && lsum < rsum) {
				lsum += arr[i++];
			}
		}
		return lsum == rsum ? true : false;
	}

	class Sides {
		int a;
		int b;
		int c;

		Sides(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}

	int countDistinctTriangles(ArrayList<Sides> arr) {
		// Write your code here
		Set<String> set = new HashSet<>();
		for (int i = 0; i < arr.size(); i++) {
			Sides s = arr.get(i);
			set.add(Arrays.toString(IntStream.of(s.a, s.b, s.c).sorted().toArray()));
		}
		return set.size();
	}

	public static long maximumSum(List<Long> a, long m) {
		long max = Integer.MIN_VALUE;
		long prefix = 0;
		for (int i = 0; i < a.size(); i++) {
			long current = a.get(i) % m;
			prefix = (prefix + current) % m;
			if (current < prefix) {
				max = Math.max(max, prefix);
			} else {
				max = Math.max(max, current);
			}
		}
		return max;
	}

	public static void main(String[] args) {


		System.out.println(balancedSplitExists(new int[] { 1, 1, 5, 7 }));
		System.out.println(maxDiff(new int[] { 2, 3, 10, 6, 4, 8, 1 }));
		int[][] intervals = new int[][] { { 1, 4 }, { 0, 4 } };
		System.out.println(Arrays.toString(merge(intervals)));
		System.out.println(subsets(new int[] { 1, 2, 3 }));
	}

}
