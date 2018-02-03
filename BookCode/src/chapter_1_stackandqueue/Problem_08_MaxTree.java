package chapter_1_stackandqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Problem_08_MaxTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getMaxTree(int[] arr) {
		Node[] nArr = new Node[arr.length];
		for (int i = 0; i != arr.length; i++) {
			nArr[i] = new Node(arr[i]);
		}
		Stack<Node> stack = new Stack<Node>();
		HashMap<Node, Node> lBigMap = new HashMap<Node, Node>();
		HashMap<Node, Node> rBigMap = new HashMap<Node, Node>();
		for (int i = 0; i != nArr.length; i++) {
			Node curNode = nArr[i];
			while ((!stack.isEmpty()) && stack.peek().value < curNode.value) {
				popStackSetMap(stack, lBigMap);
			}
			stack.push(curNode);
		}
		while (!stack.isEmpty()) {
			popStackSetMap(stack, lBigMap);
		}
		for (int i = nArr.length - 1; i != -1; i--) {
			Node curNode = nArr[i];
			while ((!stack.isEmpty()) && stack.peek().value < curNode.value) {
				popStackSetMap(stack, rBigMap);
			}
			stack.push(curNode);
		}
		while (!stack.isEmpty()) {
			popStackSetMap(stack, rBigMap);
		}
		Node head = null;
		for (int i = 0; i != nArr.length; i++) {
			Node curNode = nArr[i];
			Node left = lBigMap.get(curNode);
			Node right = rBigMap.get(curNode);
			if (left == null && right == null) {
				head = curNode;
			} else if (left == null) {
				if (right.left == null) {
					right.left = curNode;
				} else {
					right.right = curNode;
				}
			} else if (right == null) {
				if (left.left == null) {
					left.left = curNode;
				} else {
					left.right = curNode;
				}
			} else {
				Node parent = left.value < right.value ? left : right;
				if (parent.left == null) {
					parent.left = curNode;
				} else {
					parent.right = curNode;
				}
			}
		}
		return head;
	}

	public static void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map) {
		Node popNode = stack.pop();
		if (stack.isEmpty()) {
			map.put(popNode, null);
		} else {
			map.put(popNode, stack.peek());
		}
	}

	public static void printPreOrder(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		printPreOrder(head.left);
		printPreOrder(head.right);
	}

	public static void printInOrder(Node head) {
		if (head == null) {
			return;
		}
		printPreOrder(head.left);
		System.out.print(head.value + " ");
		printPreOrder(head.right);
	}

	// QWL
	public static Node getMaxTree2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}

		int len = arr.length;
		Node[] nodes = new Node[len];
		for (int i = 0; i < len; i++) {
			nodes[i] = new Node(arr[i]);
		}

		List<Integer> lfirst = new ArrayList<>();
		List<Integer> rfirst = new ArrayList<>();

		Stack<Integer> index = new Stack<>();
		for (int i = 0; i < len; i++) {
			while (true) {
				if (index.isEmpty()) {
					index.push(i);
					lfirst.add(null);
					break;
				} else if (arr[index.peek()] > arr[i]) {
					lfirst.add(index.peek());
					index.push(i);
					break;
				} else {
					index.pop();
				}
			}
		}
		//System.out.println(lfirst);
		
		index = new Stack<>();
		for (int i = len - 1; i >= 0; i--) {
			while (true) {
				if (index.isEmpty()) {
					index.push(i);
					rfirst.add(null);
					break;
				} else if (arr[index.peek()] > arr[i]) {
					rfirst.add(index.peek());
					index.push(i);
					break;
				} else {
					index.pop();
				}
			}
		}
		Collections.reverse(rfirst);
		//System.out.println(rfirst);
		
		Node head = null;
		for(int i = 0; i < len; i++){
			Integer lf = lfirst.get(i);
			Integer rf = rfirst.get(i);
			if(lf == null && rf == null){
				head = nodes[i];
			}else if(lf == null){
				if(nodes[rf].left == null){
					nodes[rf].left = nodes[i];
				}else{
					nodes[rf].right = nodes[i];
				}
			}else{
				if(rf == null){
					if(nodes[lf].left == null){
						nodes[lf].left = nodes[i];
					}else{
						nodes[lf].right = nodes[i];
					}
				}else{
					Node f = arr[rf] > arr[lf] ? nodes[lf] : nodes[rf];
					if(f.left == null){
						f.left = nodes[i];
					}else{
						f.right = nodes[i];
					}
				}
			}
		}
		return head;
	}

	public static void main(String[] args) {
		int[] uniqueArr = { 3, 4, 5, 1, 2 ,7,9,8,0,12,54};
		Node head = getMaxTree(uniqueArr);
		printPreOrder(head);
		System.out.println();
		printInOrder(head);
		System.out.println();
		System.out.println("==================");
		
		head = getMaxTree2(uniqueArr);
		printPreOrder(head);
		System.out.println();
		printInOrder(head);
	}

}
