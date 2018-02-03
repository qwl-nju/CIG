package chapter_3_binarytreeproblem;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_04_SerializeAndReconstructTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static String serialByPre(Node head) {
		if (head == null) {
			return "#!";
		}
		String res = head.value + "!";
		res += serialByPre(head.left);
		res += serialByPre(head.right);
		return res;
	}

	public static Node reconByPreString(String preStr) {
		String[] values = preStr.split("!");
		Queue<String> queue = new LinkedList<String>();
		for (int i = 0; i != values.length; i++) {
			queue.offer(values[i]);
		}
		return reconPreOrder(queue);
	}

	public static Node reconPreOrder(Queue<String> queue) {
		String value = queue.poll();
		if (value.equals("#")) {
			return null;
		}
		Node head = new Node(Integer.valueOf(value));
		head.left = reconPreOrder(queue);
		head.right = reconPreOrder(queue);
		return head;
	}

	public static String serialByLevel(Node head) {
		if (head == null) {
			return "#!";
		}
		String res = head.value + "!";
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			if (head.left != null) {
				res += head.left.value + "!";
				queue.offer(head.left);
			} else {
				res += "#!";
			}
			if (head.right != null) {
				res += head.right.value + "!";
				queue.offer(head.right);
			} else {
				res += "#!";
			}
		}
		return res;
	}

	public static Node reconByLevelString(String levelStr) {
		String[] values = levelStr.split("!");
		int index = 0;
		Node head = generateNodeByString(values[index++]);
		Queue<Node> queue = new LinkedList<Node>();
		if (head != null) {
			queue.offer(head);
		}
		Node node = null;
		while (!queue.isEmpty()) {
			node = queue.poll();
			node.left = generateNodeByString(values[index++]);
			node.right = generateNodeByString(values[index++]);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		return head;
	}

	public static Node generateNodeByString(String val) {
		if (val.equals("#")) {
			return null;
		}
		return new Node(Integer.valueOf(val));
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

	public static String serialByPreQWL(Node head) {
		StringBuilder res = new StringBuilder();
		if(head == null){
			res.append("#!");
			return res.toString();
		}
		res.append(head.value + "!");
		res.append(serialByPreQWL(head.left));
		res.append(serialByPreQWL(head.right));
		return res.toString();
	}


	public static Node reconByPreStringQWL(String preStr) {
		if (preStr == null) {
			return null;
		}
		String[] values = preStr.split("!");
		LinkedList<String> queue = new LinkedList<>();
		for (String value : values) {
			queue.add(value);
		}

		return isHelpreconByPreStringQWL(queue);
	}

	public static Node isHelpreconByPreStringQWL(LinkedList<String> queue) {
		if (queue == null || queue.isEmpty()) {
			return null;
		}
		String h = queue.pollFirst();
		if (h.equals("#")) {
			return null;
		}
		Node head = new Node(Integer.parseInt(h));
		head.left = isHelpreconByPreStringQWL(queue);
		head.right = isHelpreconByPreStringQWL(queue);
		return head;
	}
	
	public static String serialByLevelQWL(Node head) {
		StringBuilder res = new StringBuilder();
		if(head == null){
			return null;
		}
		LinkedList<Node> nodes = new LinkedList<>();
		nodes.add(head);
		while(!nodes.isEmpty()){
			Node t = nodes.pollFirst();
			if(t != null){
				res.append(t.value+"!");
				nodes.add(t.left);
				nodes.add(t.right);
			}else{
				res.append("#!");
			}
		}
		return res.toString();
	}
	
	public static Node reconByLevelStringQWL(String levelStr) {
		if(levelStr == null){
			return null;
		}
		String[] values = levelStr.split("!");
		Node[] nodes = new Node[values.length];
		for(int i = 0; i < values.length; i++){
			if(!values[i].equals("#")){
				nodes[i] = new Node(Integer.parseInt(values[i]));
			}else{
				nodes[i] = null;
			}
		}
		Node head = nodes[0];
		for(int i = 0; i < nodes.length; i++){
			if(nodes[i] != null){
				nodes[i].left = i * 2 + 1 < nodes.length ? nodes[i * 2 + 1] : null;
				nodes[i].right = i * 2 + 2 < nodes.length ? nodes[i * 2 + 2] : null;
			}
		}
		return head;
	}

	public static void main(String[] args) {
		Node head = null;
		printTree(head);

		String pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		String level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(1);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.right = new Node(5);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(100);
		head.left = new Node(21);
		head.left.left = new Node(37);
		head.right = new Node(-42);
		head.right.left = new Node(0);
		head.right.right = new Node(666);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		System.out.println("====================================");

		head = new Node(100);
		head.left = new Node(21);
		head.left.left = new Node(37);
		head.right = new Node(-42);
		head.right.left = new Node(0);
		head.right.right = new Node(666);
		printTree(head);

		pre = serialByPreQWL(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreStringQWL(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);
		
		level = serialByLevelQWL(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelStringQWL(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);
	}
}
