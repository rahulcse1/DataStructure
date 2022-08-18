package cracking.code.fb;

import java.util.Arrays;

public class Dummy {
	public static void main(String[] args) {
		System.out.printf("Checking %s", "Amar");
		System.out.println(getTotalTime(new int[] { 4, 2, 1, 3 }));
		findMaxProduct(new int[] { 2, 4, 7, 1, 5, 3 });
	}

	static int[] findMaxProduct(int[] a) {
		// Write your code here
		if (a == null || a.length == 0)
			return a;
		int[] res = new int[a.length];
		int x = a[0];
		int y = a[1];
		int max = 0;
		for (int i = 2; i < a.length; i++) {
			if (a[i] * x * y > max) {
				res[i] = a[i] * x * y;
				max = res[i];
			} else {
				res[i] = max;
			}
			if (x < a[i] && x < y) {
				x = a[i];
			} else if ((y < a[i] && y < x)) {
				y = a[i];
			}
		}
		res[0] = -1;
		res[1] = -1;
		return res;
	}

	static int getTotalTime(int[] a) {
		// Write your code here
		Arrays.sort(a);
		int p = 0;
		for (int i = a.length - 1; i >= 1; i--) {
			p = p + a[i] + a[i - 1];
			a[i - 1] = a[i] + a[i - 1];
		}
		return p;
	}
}
