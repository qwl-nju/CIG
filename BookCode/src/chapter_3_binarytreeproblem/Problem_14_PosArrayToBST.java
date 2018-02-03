package chapter_3_binarytreeproblem;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_14_PosArrayToBST {

	public static boolean isPostArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		return isPost(arr, 0, arr.length - 1);
	}

	public static boolean isPost(int[] arr, int start, int end) {
		if (start == end) {
			return true;
		}
		int less = -1;
		int more = end;
		for (int i = start; i < end; i++) {
			if (arr[end] > arr[i]) {
				less = i;
			} else {
				more = more == end ? i : more;
			}
		}
		if (less == -1 || more == end) {
			return isPost(arr, start, end - 1);
		}
		if (less != more - 1) {
			return false;
		}
		return isPost(arr, start, less) && isPost(arr, more, end - 1);
	}

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int value) {
			this.value = value;
		}
	}

	public static Node posArrayToBST(int[] posArr) {
		if (posArr == null) {
			return null;
		}
		return posToBST(posArr, 0, posArr.length - 1);
	}

	public static Node posToBST(int[] posArr, int start, int end) {
		if (start > end) {
			return null;
		}
		Node head = new Node(posArr[end]);
		int less = -1;
		int more = end;
		for (int i = start; i < end; i++) {
			if (posArr[end] > posArr[i]) {
				less = i;
			} else {
				more = more == end ? i : more;
			}
		}
		head.left = posToBST(posArr, start, less);
		head.right = posToBST(posArr, more, end - 1);
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

	// QWL
	public static Node posArrayToBST2(int[] posArr) {
		if (posArr == null) {
			return null;
		}
		return recover(posArr, 0, posArr.length - 1);
	}

	public static Node recover(int[] arr, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = end - 1;
		;
		Node head = new Node(arr[end]);
		for (int i = start; i < end; i++) {
			if (arr[i] > arr[end]) {
				mid = i - 1;
				break;
			}
		}
		head.left = recover(arr, start, mid);
		head.right = recover(arr, mid + 1, end - 1);
		return head;
	}

	public static boolean isPost2(int[] arr, int start, int end) {
		if(start > end) return true;
		int left = start - 1;//я╟ур╫Гоч
		int right = end;
		for (int i = start; i < end; i++) {
			if (arr[i] < arr[end]) {
				left = i;
			} else {
				right = right == end ? i : right;
			}
		}
		if (left + 1 != right) {
			return false;
		}
		return isPost2(arr, start, left) && isPost2(arr, right, end - 1);
	}

	public static void main(String[] args) {
		int[] arr = { 2, 4, 3, 6, 8, 17, 9 };
		System.out.println(isPost(arr, 0, arr.length - 1));
		printTree(posArrayToBST(arr));

		System.out.println(isPost2(arr, 0, arr.length - 1));
		printTree(posArrayToBST2(arr));

	}

}
