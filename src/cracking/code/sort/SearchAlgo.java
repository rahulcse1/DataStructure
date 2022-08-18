package cracking.code.sort;

public class SearchAlgo {

	public static boolean linearSearch(int[] a, int k) {
		if (a != null && a.length != 0) {
			for (int i : a) {
				if (a[i] == k) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * O(logn)
	 */
	public static boolean binarySearch(int[] a, int k) {

		int low = a[0];
		int high = a[a.length - 1];

		while (low < high) {
			int mid = (low + high) / 2;
			if (a[mid] < k) {
				low = mid + 1;
			} else if (a[mid] > k) {
				high = mid - 1;
			} else {
				return true;
			}
		}

		return false;
	}
}
