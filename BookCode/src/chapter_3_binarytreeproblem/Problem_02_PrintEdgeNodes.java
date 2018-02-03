package chapter_3_binarytreeproblem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Problem_02_PrintEdgeNodes {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void printEdge1(Node head) {
		if (head == null) {
			return;
		}
		int height = getHeight(head, 0);
		Node[][] edgeMap = new Node[height][2];
		setEdgeMap(head, 0, edgeMap);
		// print left edge
		for (int i = 0; i != edgeMap.length; i++) {
			System.out.print(edgeMap[i][0].value + " ");
		}
		// print leaf node but not in map
		printLeafNotInMap(head, 0, edgeMap);
		// print right edge but not left edge
		for (int i = edgeMap.length - 1; i != -1; i--) {
			if (edgeMap[i][0] != edgeMap[i][1]) {
				System.out.print(edgeMap[i][1].value + " ");
			}
		}
		System.out.println();
	}

	public static int getHeight(Node h, int l) {
		if (h == null) {
			return l;
		}
		return Math.max(getHeight(h.left, l + 1), getHeight(h.right, l + 1));
	}

	public static void setEdgeMap(Node h, int l, Node[][] edgeMap) {
		if (h == null) {
			return;
		}
		edgeMap[l][0] = edgeMap[l][0] == null ? h : edgeMap[l][0];
		edgeMap[l][1] = h;
		setEdgeMap(h.left, l + 1, edgeMap);
		setEdgeMap(h.right, l + 1, edgeMap);
	}

	public static void printLeafNotInMap(Node h, int l, Node[][] m) {
		if (h == null) {
			return;
		}
		if (h.left == null && h.right == null && h != m[l][0] && h != m[l][1]) {
			System.out.print(h.value + " ");
		}
		printLeafNotInMap(h.left, l + 1, m);
		printLeafNotInMap(h.right, l + 1, m);
	}

	public static void printEdge2(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		if (head.left != null && head.right != null) {
			printLeftEdge(head.left, true);
			printRightEdge(head.right, true);
		} else {
			printEdge2(head.left != null ? head.left : head.right);
		}
		System.out.println();
	}

	public static void printLeftEdge(Node h, boolean print) {
		if (h == null) {
			return;
		}
		if (print || (h.left == null && h.right == null)) {
			System.out.print(h.value + " ");
		}
		printLeftEdge(h.left, print);
		printLeftEdge(h.right, print && h.left == null ? true : false);
	}

	public static void printRightEdge(Node h, boolean print) {
		if (h == null) {
			return;
		}
		printRightEdge(h.left, print && h.right == null ? true : false);
		printRightEdge(h.right, print);
		if (print || (h.left == null && h.right == null)) {
			System.out.print(h.value + " ");
		}
	}

	// QWL
	public static void printEdgeQWL1(Node head) {
		if (head == null) {
			return;
		}
		int h = getHeight(head);
		Node[][] edge = new Node[h][2];
		setEdge(head, edge, 0);

		for (int i = 0; i < h; i++) {
			System.out.print(edge[i][0].value + " ");
		}

		getNoLeftRight(head, edge, 0);

		for (int i = h - 1; i >= 0; i--) {
			if (edge[i][0] != edge[i][1]) {
				System.out.print(edge[i][1].value + " ");
			}
		}
	}

	public static void getNoLeftRight(Node head, Node[][] edge, int level) {
		if (head == null) {
			return;
		}
		if (head != edge[level][0] && head != edge[level][1] && head.left == null && head.right == null) {
			System.out.print(head.value + " ");
		}
		getNoLeftRight(head.left, edge, level + 1);
		getNoLeftRight(head.right, edge, level + 1);
	}

	public static void setEdge(Node head, Node[][] edge, int level) {
		if (head == null) {
			return;
		}
		edge[level][0] = edge[level][0] == null ? head : edge[level][0];
		edge[level][1] = head;
		setEdge(head.left, edge, level + 1);
		setEdge(head.right, edge, level + 1);
	}

	public static int getHeight(Node h) {
		if (h == null) {
			return 0;
		}
		return Math.max(getHeight(h.left) + 1, getHeight(h.right) + 1);

	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.right = new Node(4);
		head.right.left = new Node(5);
		head.right.right = new Node(6);
		head.left.right.left = new Node(7);
		head.left.right.right = new Node(8);
		head.right.left.left = new Node(9);
		head.right.left.right = new Node(10);
		head.left.right.right.right = new Node(11);
		head.right.left.left.left = new Node(12);
		head.left.right.right.right.left = new Node(13);
		head.left.right.right.right.right = new Node(14);
		head.right.left.left.left.left = new Node(15);
		head.right.left.left.left.right = new Node(16);

		printEdge1(head);
		printEdge2(head);

		printEdgeQWL1(head);
	}

}
