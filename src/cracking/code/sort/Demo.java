package cracking.code.sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Demo {
	public static int getMinimumDays(List<Integer> parcels) {
		// Write your code here
		Set<Integer> set = new HashSet<>(parcels);
		set.remove(0);
		return set.size();
	}

	public static int findTotalPower(List<Integer> power) {
		 int n = power.size(), k = 1, cur = 0, ans = 0;
	        int[][] mindp = new int[n][n];

	        int[] sum = new int[n + 1];
	        for(int num : power) {
	            cur += num;
	            sum[k++] = cur;
	        }

	       // create a matrix to find out the minimum integer between index i and j
	        for(int i = 0; i < power.size(); i++) {
	            LinkedList<Integer> stack = new LinkedList<>();
	            for(int j = i; j < power.size(); j++) {
	                while(!stack.isEmpty() && (power.get(stack.peekFirst()) > power.get(j))) {
	                    stack.removeFirst();
	                }
	                stack.addLast(j);
	                mindp[i][j] = power.get(stack.peekFirst());
	            }
	        }
	       // sum up all the possible subarrays using index i and j with the matrix and array
	        for(int i = 0; i < power.size(); i++) {
	            for(int j = i; j < power.size(); j++) {
	                ans += mindp[i][j] * (sum[j + 1] - sum[i]);
	            }
	        }
	        return ans;
	}

	public static void main(String[] args) {
		List<Integer> parcels = new ArrayList<Integer>();
		parcels.add(2);
		parcels.add(1);
		parcels.add(3);
		System.out.println(findTotalPower(parcels));
	}
}
