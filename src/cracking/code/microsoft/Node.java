package cracking.code.microsoft;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int value;
	private List<Integer> neighbors;

	public Node(int value) {
		this.value = value;
		this.neighbors = new ArrayList<>();
	}

	public void addNeighbor(int e) {
		this.neighbors.add(e);
	}

	public int getValue() {
		return value;
	}

	public List<Integer> getNeighbors() {
		return neighbors;
	}

}
