package cracking.code.fb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SCCKosaraju {

	public static List<ArrayList<Integer>> kosaRaju(List<ArrayList<Integer>> graph) {

		int n = graph.size();
		boolean[] visited = new boolean[n];
		List<Integer> order = new ArrayList<>();

		for (int i = 0; i < n; i++)
			if (!visited[i])
				dfs(graph, visited, order, i);

		ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();

		for (int i = 0; i < n; i++)
			transpose.add(new ArrayList<>());

		for (int i = 0; i < n; i++) {
			visited[i] = false;
			for (Integer it : graph.get(i)) {
				transpose.get(it).add(i);
			}
		}

		List<ArrayList<Integer>> components = new ArrayList<>();
		Arrays.fill(visited, false);
		Collections.reverse(order);

		for (int u : order)
			if (!visited[u]) {
				ArrayList<Integer> component = new ArrayList<>();
				dfs(transpose, visited, component, u);
				components.add(component);
			}

		return components;
	}

	static void dfs(List<ArrayList<Integer>> graph, boolean[] visited, List<Integer> res, int node) {
		visited[node] = true;
		for (int v : graph.get(node))
			if (!visited[v])
				dfs(graph, visited, res, v);
		res.add(node);
	}

	public static int camelcase(String s) {
		// Write your code here
		if (s == null)
			return 0;
		int count = 1;
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i);
			if ( 65 <= c && c <= 90) {
				count++;
			}
		}
		return count;

	}

	public static void main(String[] args) {

		System.out.println(camelcase("saveChangesInTheEditor"));
		int n = 7;
		ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < n; i++)
			g.add(new ArrayList<Integer>());

		g.get(1).add(3);
		g.get(2).add(1);
		g.get(3).add(2);
		g.get(3).add(5);
		g.get(4).add(6);
		g.get(5).add(4);
		g.get(6).add(5);

		List<ArrayList<Integer>> components = kosaRaju(g);
		System.out.println(components);
	}
}
