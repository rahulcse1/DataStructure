package tree;

/**
 * A binary tree is one where each node has, at most, 2 children, creatively
 * named left and right. We define the level of a tree as the number of parent
 * nodes a tree node has.
 * 
 * @author techwalk
 *
 */
public class BinaryTree {

	Node root;
	static int MAX_LEVEL = 0;

	void printLeftView(Node node, int level) {
		if (node == null)
			return;
		if (MAX_LEVEL < level) {
			System.out.println(node.value);
			MAX_LEVEL = level;
		}
		printLeftView(node.left, level + 1);
		printLeftView(node.right, level + 1);
	}

	void printRightView(Node node, int level) {
		if (node == null)
			return;
		if (MAX_LEVEL < level) {
			System.out.println(node.value);
			MAX_LEVEL = level;
		}
		printRightView(node.right, level + 1);
		printRightView(node.left, level + 1);
	}

	static int lastValue = Integer.MIN_VALUE;

	boolean isValidBST(Node node) {
		if (node == null)
			return true;
		int value = node.value;
		Node left = node.left;
		Node right = node.right;
		if (!isValidBST(left))
			return false;
		if (value < lastValue)
			return false;
		lastValue = value;
		return (isValidBST(right));
	}

	boolean isNodeExist(Node node, int number) {
		if (node == null)
			return false;
		if (node.value == number) {
			return true;
		}
		return (isNodeExist(node.left, number) || isNodeExist(node.right, number));
	}
}
