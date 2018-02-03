package chapter_1_stackandqueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem_07_SlidingWindowMaxArray {

	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
				qmax.pollLast();
			}
			qmax.addLast(i);
			if (qmax.peekFirst() == i - w) {
				qmax.pollFirst();
			}
			if (i >= w - 1) {
				res[index++] = arr[qmax.peekFirst()];
			}
		}
		return res;
	}

	// QWL
	public static int[] getMaxWindow2(int[] arr, int w) {
		if (arr == null || arr.length == 0) {
			return arr;
		}
		int len = arr.length;
		List<Integer> res = new ArrayList<>();
		LinkedList<Integer> link = new LinkedList<>();
		for (int i = 0; i < len; i++) {
			while (true) {
				if (link.size() == 0) {
					link.addLast(i);
					break;
				} else if (arr[link.getLast()] > arr[i]) {
					link.addLast(i);
					break;
				} else {
					link.removeLast();
				}
			}
			if (i >= w - 1) {
				if (link.getFirst() > i - w) {
					res.add(arr[link.getFirst()]);
				} else {
					link.removeFirst();
					res.add(arr[link.getFirst()]);
				}
			}
		}
		if (res.isEmpty()) {
			res.add(arr[link.getFirst()]);
		}
		int[] r = new int[res.size()];
		for (int i = 0; i < r.length; i++) {
			r[i] = res.get(i);
		}
		//System.out.println(r.length);
		return r;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7,6,0,2,1,5,6,7,3,-3,1,-7,-9,3,3,1,8,9 };
		int w = 3;
		printArray(getMaxWindow(arr, w));
		System.out.println("============================");
		printArray(getMaxWindow2(arr, w));

	}

}
