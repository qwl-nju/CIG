package chapter_3_binarytreeproblem;

import com.sun.corba.se.impl.oa.poa.AOMEntry;

public class Problem_16_SortedArrayToBalancedBST {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node generateTree(int[] sortArr) {
		if (sortArr == null) {
			return null;
		}
		return generate(sortArr, 0, sortArr.length - 1);
	}

	public static Node generate(int[] sortArr, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		Node head = new Node(sortArr[mid]);
		head.left = generate(sortArr, start, mid - 1);
		head.right = generate(sortArr, mid + 1, end);
		return head;
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
	
	//QWL
	public static Node generateTree2(int[] sortArr) {
		if(sortArr == null){
			return null;
		}
		return toBal(sortArr, 0, sortArr.length - 1);
	}
	
	public static Node toBal(int[] arr, int left, int right){
		if(left > right){
			return null;
		}
		int mid = (left + right) / 2;
		Node head = new Node(arr[mid]);
		head.left = toBal(arr, left, mid - 1);
		head.right = toBal(arr, mid + 1, right);
		return head;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		printTree(generateTree(arr));
		printTree(generateTree2(arr));

	}

}
