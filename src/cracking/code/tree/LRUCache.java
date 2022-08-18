package cracking.code.tree;

import java.util.HashMap;

class LRUCache {

	int capacity;
	HashMap<Integer, Node> map;
	final Node head = new Node(-1, -1);
	final Node tail = new Node(-1, -1);

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.map = new HashMap<>(capacity);
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			Node curr = map.get(key);
			delete(curr);
			insert(curr);
			return curr.value;
		}
		return -1;
	}

	public void put(int key, int value) {
		Node curr = map.get(key);
		if (curr != null) {
			delete(curr);
			curr.value = value;
			insert(curr);
		} else {
			if (map.size() == capacity) {
				map.remove(tail.prev.key);
				delete(tail.prev);
			}
			Node n = new Node(key, value);
			map.put(key, n);
			insert(n);
		}
	}

    public void insert(Node node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    public void delete(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

	class Node {
		int key;
		int value;
		Node next;
		Node prev;

		public Node(int k, int v) {
			this.key = k;
			this.value = v;
		}
	}

	public static void main(String[] args) {
		LRUCache lRUCache = new LRUCache(2);
		lRUCache.put(1, 1); // cache is {1=1}
		lRUCache.put(2, 2); // cache is {1=1, 2=2}
		lRUCache.get(1); // return 1
		lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		lRUCache.get(2); // returns -1 (not found)
		lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		lRUCache.get(1); // return -1 (not found)
		lRUCache.get(3); // return 3
		lRUCache.get(4); // return 4
	}
}
