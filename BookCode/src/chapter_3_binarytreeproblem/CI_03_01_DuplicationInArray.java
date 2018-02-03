package chapter_3_binarytreeproblem;

import java.util.HashMap;
import java.util.Map;

public class CI_03_01_DuplicationInArray {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node preInToTree(int[] pre, int[] in) {
		if (pre == null || in == null) {
			return null;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < in.length; i++) {// 避免重复遍历
			map.put(in[i], i);
		}
		return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
	}

	public static Node preIn(int[] p, int pi, int pj, int[] n, int ni, int nj, HashMap<Integer, Integer> map) {
		if (pi > pj) {
			return null;
		}
		Node head = new Node(p[pi]);
		int index = map.get(p[pi]);
		head.left = preIn(p, pi + 1, pi + index - ni, n, ni, index - 1, map);
		head.right = preIn(p, pi + index - ni + 1, pj, n, index + 1, nj, map);
		return head;
	}

	public static Node inPosToTree(int[] in, int[] pos) {
		if (in == null || pos == null) {
			return null;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		return inPos(in, 0, in.length - 1, pos, 0, pos.length - 1, map);
	}

	public static Node inPos(int[] n, int ni, int nj, int[] s, int si, int sj, HashMap<Integer, Integer> map) {
		if (si > sj) {
			return null;
		}
		Node head = new Node(s[sj]);
		int index = map.get(s[sj]);
		head.left = inPos(n, ni, index - 1, s, si, si + index - ni - 1, map);
		head.right = inPos(n, index + 1, nj, s, si + index - ni, sj - 1, map);
		return head;
	}

	// 每个节点的孩子数都为0或2的二叉树才能被先序与后序重构出来
	public static Node prePosToTree(int[] pre, int[] pos) {
		if (pre == null || pos == null) {
			return null;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < pos.length; i++) {
			map.put(pos[i], i);
		}
		return prePos(pre, 0, pre.length - 1, pos, 0, pos.length - 1, map);
	}

	public static Node prePos(int[] p, int pi, int pj, int[] s, int si, int sj, HashMap<Integer, Integer> map) {
		Node head = new Node(s[sj--]);
		if (pi == pj) {
			return head;
		}
		int index = map.get(p[++pi]);
		head.left = prePos(p, pi, pi + index - si, s, si, index, map);
		head.right = prePos(p, pi + index - si + 1, pj, s, index + 1, sj, map);
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
	public static Node preInToTree2(int[] pre, int[] in) {
		if (pre == null || pre.length == 0) {
			return null;
		}
		return isHelpPreInToTree2(pre, in, 0, pre.length - 1, 0, in.length - 1);

	}

	public static Node isHelpPreInToTree2(int[] pre, int[] in, int pl, int pr, int il, int ir) {
		if (pl > pr || il > ir) {
			return null;
		}
		// if (pl == pr) {
		// return new Node(pre[pl]);
		// }
		int h = pre[pl];
		Node head = new Node(h);
		int left = il;
		for (int i = il; i < ir; i++) {
			if (in[i] == h) {
				left = i;
				break;
			}
		}
		head.left = isHelpPreInToTree2(pre, in, pl + 1, pl + left - il, il, left - 1);
		head.right = isHelpPreInToTree2(pre, in, pl + left - il + 1, pr, left + 1, ir);
		return head;
	}

	public static Node inPosToTree2(int[] in, int[] pos) {
		if (in == null || pos == null) {
			return null;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		return isHelpInPosToTree2(in, pos, 0, in.length - 1, 0, pos.length - 1, map);
	}

	public static Node isHelpInPosToTree2(int[] in, int[] pos, int il, int ir, int pl, int pr,
			Map<Integer, Integer> map) {
		if (il > ir || pl > pr) {
			return null;
		}
		Node head = new Node(pos[pr]);
		int index = map.get(pos[pr]);
		head.right = isHelpInPosToTree2(in, pos, index + 1, ir, pr - (ir - index), pr - 1, map);
		head.left = isHelpInPosToTree2(in, pos, il, index - 1, pl, pr - (ir - index) - 1, map);
		return head;

	}

	public static Node prePosToTree2(int[] pre, int[] pos) {
		if (pre == null || pos == null) {
			return null;
		}
		Map<Integer, Integer> mapPre = new HashMap<>();
		Map<Integer, Integer> mapPos = new HashMap<>();
		for (int i = 0; i < pre.length; i++) {
			mapPre.put(pre[i], i);
			mapPos.put(pos[i], i);
		}
		return isHelpPrePosToTree2(pre, pos, 0, pre.length - 1, mapPre, mapPos);
	}

	public static Node isHelpPrePosToTree2(int[] pre, int[] pos, int el, int er, Map<Integer, Integer> mapPre,
			Map<Integer, Integer> mapPos) {
		if (el > er) {
			return null;
		}
		Node head = new Node(pre[el]);
		int index1 = mapPos.get(pre[el]);
		if (index1 == 0) {
			head.left = null;
			head.right = null;
			return head;
		}
		index1 = mapPre.get(pos[index1 - 1]);
		if (index1 - 1 > er || index1 < el) {
			head.left = null;
			head.right = null;
			return head;
		}
		head.left = isHelpPrePosToTree2(pre, pos, el + 1, index1 - 1, mapPre, mapPos);
		head.right = isHelpPrePosToTree2(pre, pos, index1, er, mapPre, mapPos);
		return head;
	}

	public static Node prePosToTree3(int[] pre, int[] pos) {
		if (pre == null || pos == null || pre.length != pos.length) {
			return null;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < pos.length; i++) {
			map.put(pos[i], i);
		}
		return isHelpPrePosToTree3(pre, pos, 0, pre.length - 1, 0, pos.length - 1, map);
	}

	public static Node isHelpPrePosToTree3(int[] pre, int[] pos, int el, int er, int sl, int sr,
			Map<Integer, Integer> map) {
		if (el > er) {
			return null;
		}
		Node head = new Node(pre[el]);
		int index = map.get(pre[el + 1]);
		head.left = isHelpPrePosToTree3(pre, pos, el + 1, el + index - sl, sl, index, map);
		head.right = isHelpPrePosToTree3(pre, pos, el + index - sl + 1, er, index + 1, sr, map);
		return head;

	}

	public static void main(String[] args) {
		int[] pre = { 1, 2, 4, 5, 8, 9, 3, 6, 7 };
		int[] in = { 4, 2, 8, 5, 9, 1, 6, 3, 7 };
		int[] pos = { 4, 8, 9, 5, 2, 6, 7, 3, 1 };
		printTree(preInToTree(pre, in));
		printTree(preInToTree2(pre, in));
		System.out.println("=============================================");

		printTree(inPosToTree(in, pos));
		printTree(inPosToTree2(in, pos));
		System.out.println("=============================================");

		printTree(prePosToTree(pre, pos));
		printTree(prePosToTree2(pre, pos));

	}

}
