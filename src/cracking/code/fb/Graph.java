package cracking.code.fb;

import java.util.LinkedList;

public class Graph {

	public static void main(String args[]) {
		Graph graph = new Graph(7);

		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 1);
		graph.addEdge(2, 5);
		graph.addEdge(3, 6);
		System.out.println("BFS: ");
		graph.bfsTraversal(1);
		System.out.println();
		System.out.println("DFS: ");
		graph.dfsTraversal(1);
	}

	private int numberOfVertices;
	private LinkedList<Integer> adjacencyList[];

	Graph(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
		adjacencyList = new LinkedList[numberOfVertices];
		for (int i = 0; i < numberOfVertices; ++i)
			adjacencyList[i] = new LinkedList<>();
	}

	public void dfsTraversal(int node) {
		boolean[] visited = new boolean[numberOfVertices];
		dfs(node, visited);
	}

	private void dfs(int node, boolean[] visited) {
		visited[node] = true;
		System.out.print(node + "\t");
		for (int v : adjacencyList[node]) {
			if (!visited[v]) {
				dfs(v, visited);
			}
		}
	}

	public void bfsTraversal(int node) {

		boolean[] visited = new boolean[numberOfVertices];
		LinkedList<Integer> queue = new LinkedList<>();
		visited[node] = true;
		queue.add(node);

		while (!queue.isEmpty()) {
			node = queue.poll();
			System.out.print(node + "\t");
			for (int n : adjacencyList[node]) {
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}

	void addEdge(int from, int to) {
		adjacencyList[from].add(to);
		adjacencyList[to].add(from);
	}

}
