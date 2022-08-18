package cracking.code.fb;

import java.util.LinkedList;

public class Program {

	public static void main(String[] args) {
		LinkedList o = new LinkedList(1);
		LinkedList t = new LinkedList(2);
		LinkedList th = new LinkedList(3);
		LinkedList f = new LinkedList(4);
		LinkedList five = new LinkedList(5);
		LinkedList six = new LinkedList(6);
		o.next = t;
		t.next = th;
		th.next = f;
		f.next = five;
		five.next = six;
		shiftLinkedList(o, 8);
	}

	static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			next = null;
		}
	}

	public static LinkedList shiftLinkedList(LinkedList head, int k) {
		// Write your code here.
		if (k == 0 || head == null)
			return head;
		LinkedList current = head;
		int len = 1;
		while (current.next != null) {
			current = current.next;
			len++;
		}
		current.next = head;
		k = k % len;
		for (int i = 0; i < len - k; i++) {
			current = current.next;
		}

		head = current.next;
		current.next = null;
		return head;
	}
}
