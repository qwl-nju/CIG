package chapter_3_binarytreeproblem;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Problem_15_IsBSTAndCBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		boolean res = true;
		Node pre = null;
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			}
			if (pre != null && pre.value > cur1.value) {
				res = false;
			}
			pre = cur1;
			cur1 = cur1.right;
		}
		return res;
	}

	public static boolean isCBT(Node head) {
		if (head == null) {
			return true;
		}
		Queue<Node> queue = new LinkedList<Node>();
		boolean leaf = false;
		Node l = null;
		Node r = null;
		queue.offer(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
				return false;
			}
			if (l != null) {
				queue.offer(l);
			}
			if (r != null) {
				queue.offer(r);
			} else {
				leaf = true;
			}
		}
		return true;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	// QWL
	public static boolean isBST2(Node head) {
		if (head == null) {
			return true;
		}
		List<Node> list = new LinkedList<>();
		Stack<Node> stack = new Stack<>();
		while (!stack.isEmpty() || head != null) {
			while (head != null) {
				stack.push(head);
				head = head.left;
			}
			head = stack.pop();
			list.add(head);
			head = head.right;
		}
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).value < list.get(i - 1).value) {
				return false;
			}
		}
		return true;
	}
	
	
	public static boolean isCBT2(Node head) {
		if(head == null){
			return true;
		}
		LinkedList<Node> queue = new LinkedList<>();
		boolean only = false;
		queue.add(head);
		while(!queue.isEmpty()){
			Node h = queue.pollFirst();
			if(h.right != null && h.left == null){
				return false;
			}
			if(only){
				if(h.right != null || h.left != null){
					return false;
				}
			}
			if(h.left == null || h.right == null){
				only = true;
			}
			if(h.left != null){
				queue.add(h.left);
			}
			if(h.right != null){
				queue.add(h.right);
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Node head = new Node(14);
		head.left = new Node(22);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(15);
		head.right.left.left = new Node(15);

		printTree(head);
		System.out.println(isBST(head));
		System.out.println(isCBT(head));
		
		System.out.println(isBST2(head));
		System.out.println(isCBT2(head));

	}
}