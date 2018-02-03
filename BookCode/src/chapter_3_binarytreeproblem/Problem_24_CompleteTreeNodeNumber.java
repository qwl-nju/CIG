package chapter_3_binarytreeproblem;

public class Problem_24_CompleteTreeNodeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	public static int bs(Node node, int l, int h) {
		if (l == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, l + 1) == h) {
			return (1 << (h - l)) + bs(node.right, l + 1, h);
		} else {
			return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
		}
	}

	public static int mostLeftLevel(Node node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

	// QWL
	public static int nodeNum2(Node head) {
		if (head == null) {
			return 0;
		}
		int height = getHeight(head);

		return bs2(head, 1, height);
	}

	public static int getHeight(Node head) {
		int res = 0;
		if (head == null) {
			return res;
		}
		while (head != null) {
			res++;
			head = head.left;
		}
		return res;
	}

	public static int bs2(Node head, int le, int height) {
		if (head == null) {
			return 0;
		}
		int h = getHeight(head.right);
		if (le + h == height) {
			return (int) Math.pow(2, h) + bs(head.right, le + 1, height);
		} else {
			return (int) Math.pow(2, h) + bs(head.left, le + 1, height);
		}

	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		head.left.left.left = new Node(8);
		head.left.left.right = new Node(9);
		System.out.println(nodeNum(head));
		
		System.out.println(nodeNum2(head));

	}

}
