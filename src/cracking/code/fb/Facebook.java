package cracking.code.fb;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

import javax.xml.catalog.CatalogFeatures.Feature;

public class Facebook {

	public static void main(String[] args) {
		// Cheud-726?
		String input = "Zebra-493?";
		Facebook facebook = new Facebook();
		// System.out.println(facebook.rotationalCipher(input, 3));

		// facebook.minRemoveToMakeValid("))((");
		// System.out.println(facebook.subarraySum(new int[] { 1, 1, 1 }, 2));
		// System.out.println(facebook.areTheyEqual(new int[] { 1, 2, 3, 4 }, new int[]
		// { 1, 4, 3, 2 }));

		System.out.println(facebook.numberOfWays(new int[] { 1, 5, 3, 3, 3 }, 6));
		System.out.println(facebook.numberOfWays(new int[] { 1, 2, 3, 4, 3 }, 6));
		System.out.println(Math.pow(2, -2));
	}

	public double myPow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return x;
		}
		
		if (n % 2 == 0) {
			return myPow(x * x, n / 2);
		} else if (n % 2 == 1) {
			return x * myPow(x, n - 1);
		} else {
			return 1 / myPow(x, -n);
		}
	}

	int visibleNodes(Node root) {
		// Write your code here
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(visibleNodes(root.left), visibleNodes(root.right));

	}

	String rotationalCipher(String input, int rotationFactor) {
		// Write your code here
		// Z = 68 --- (90 + 3 - 65)%65 = 2
		// C = 67
		StringBuilder res = new StringBuilder("");
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (Character.isUpperCase(c)) {
				char ch = (char) (((int) c + rotationFactor - 65) % 26 + 65);
				res.append(ch);
			} else if (Character.isLowerCase(c)) {
				char ch = (char) (((int) c + rotationFactor - 97) % 26 + 97);
				res.append(ch);
			} else if (Character.isDigit(c)) {
				int num = Character.getNumericValue(c) + rotationFactor;
				if (num > 9) {
					// 12
					num = num % 10;
				}
				res.append(num);
			} else {
				res.append(c);
			}

		}
		return res.toString();
	}

	public int sumNumbers(TreeNode root, int sum) {

		sum = (sum * 10 + root.val);

		while (root.left == null && root.right == null) {
			return sum;
		}
		return sumNumbers(root.left, sum) + sumNumbers(root.right, sum);
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root.val == p.val || root.val == q.val) {
			return root;
		}
		TreeNode left_lca = lowestCommonAncestor(root.left, p, q);
		TreeNode right_lca = lowestCommonAncestor(root.right, p, q);
		if (left_lca != null && right_lca != null)
			return root;
		return (left_lca != null) ? left_lca : right_lca;
	}

	boolean areTheyEqual(int[] array_a, int[] array_b) {
		int len = array_a.length * array_b.length;
		int[] res = new int[len];
		for (int i = 0; i < array_a.length; i++) {
			res[array_a[i]]++;
			res[array_b[i]]--;
		}
		for (int i = 0; i < len; i++) {
			if (res[i] != 0) {
				return false;
			}
		}
		return true;
	}

	int numberOfWays(int[] arr, int k) {
		// Write your code here
		HashMap<Integer, Integer> m = new HashMap<>();
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			int p1 = arr[i];
			int p2 = k - p1;
			if (m.containsKey(p2)) {
				count += m.get(p2);
			}
			if (m.containsKey(p1)) {
				m.put(p1, m.get(p1) + 1);
			} else {
				m.put(p1, 1);
			}
		}
		return count;

	}

	int[] countSubarrays(int[] arr) {
		// Write your code here
		int len = arr.length;
		int[] res = new int[len];
		Arrays.fill(res, 1);
		int max = arr[0];
		int lastIndex = 0;
		for (int i = 1; i < len; i++) {

			if (max < arr[i]) {
				max = arr[i];
				lastIndex = i;
			} else {
				int count = i;
				while (count < len && max > arr[count]) {
					res[lastIndex] = res[lastIndex] + 1;
					count++;
				}
				max = arr[i];
				lastIndex = i;
			}
		}
		max = arr[len - 1];
		lastIndex = len - 1;
		for (int i = len - 2; i >= 0; i--) {

			if (max < arr[i]) {
				max = arr[i];
				lastIndex = i;
			} else {
				int count = i;
				while (count >= 0 && max > arr[count]) {
					res[lastIndex] = res[lastIndex] + 1;
					count--;
				}
				max = arr[i];
				lastIndex = i;
			}

		}
		return res;
	}

	public int subarraySum(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sum == k) {
				count++;
			}
			if (map.containsKey(sum - k)) {
				count += map.get(sum - k);
			}
			if (map.containsKey(sum)) {
				map.put(sum, map.get(sum) + 1);
			} else
				map.put(sum, 1);
		}
		return count;
	}

	public String minRemoveToMakeValid(String s) {

		Deque<Integer> stack = new ArrayDeque<Integer>();
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else if (s.charAt(i) == ')') {
				if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
					stack.pop();
				} else {
					stack.push(i);
				}
			}
		}
		while (!stack.isEmpty()) {
			sb.deleteCharAt(stack.pop());
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}