package chapter_1_stackandqueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Problem_10_AllLessNumSubArray {

	public static int getNum(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		LinkedList<Integer> qmin = new LinkedList<Integer>();
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int i = 0;
		int j = 0;
		int res = 0;
		while (i < arr.length) {
			while (j < arr.length) {
				while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
					qmin.pollLast();
				}
				qmin.addLast(j);
				while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
					qmax.pollLast();
				}
				qmax.addLast(j);
				if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
					break;
				}
				j++;
			}
			if (qmin.peekFirst() == i) {
				qmin.pollFirst();
			}
			if (qmax.peekFirst() == i) {
				qmax.pollFirst();
			}
			res += j - i;
			i++;
		}
		return res;
	}

	// for test
	public static int[] getRandomArray(int len) {
		if (len < 0) {
			return null;
		}
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * 10);
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}

	// QWL
	public static int getNum2(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		LinkedList<Integer> maxarr = new LinkedList<>();
		LinkedList<Integer> minarr = new LinkedList<>();
		int res = 0;
		int len = arr.length;
		int right = 0;
		int left = 0;
		while (left < len) {
			while (true) {
				if (maxarr.isEmpty()) {
					maxarr.addLast(right);
					break;
				} else if (arr[maxarr.getLast()] > arr[right]) {
					maxarr.addLast(right);
					break;
				} else {
					maxarr.removeLast();
				}
			}

			if (maxarr.getFirst() < left) {

				maxarr.removeFirst();
			}
			System.out.println("1 " + maxarr.getFirst() + ":" + left);
			while (true) {
				if (minarr.isEmpty()) {
					minarr.addLast(right);
					break;
				} else if (arr[minarr.getLast()] < arr[right]) {
					minarr.addLast(right);
					break;
				} else {
					minarr.removeLast();
				}
			}

			System.out.println(minarr.getFirst() + ":" + left);
			if (minarr.getFirst() < left) {

				minarr.removeFirst();
			}
			System.out.println("2 " + minarr.getFirst() + ":" + left);
			// System.out.println(left+":"+maxarr.size());
			// System.out.println(minarr.size());

			if (arr[maxarr.getFirst()] - arr[minarr.getFirst()] <= num && right < len - 1) {
				right++;
			} else if (arr[maxarr.getFirst()] - arr[minarr.getFirst()] <= num) {
				res += len - left;// 此时right未改变，会重复添加此时的right到max和min中，造成单个索引多次加入max或min中
									// 所以此时要么在加入时就不允许相同大小的值存在，要么再删除时多次循环删除，直到满足条件
				left++;
			} else {
				res += right - left;
				left++;
			}

		}
		return res;

	}

	// QWL
	public static int getNum3(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		LinkedList<Integer> maxarr = new LinkedList<>();
		LinkedList<Integer> minarr = new LinkedList<>();
		int res = 0;
		int len = arr.length;
		int right = 0;
		int left = 0;
		while (left < len) {
			while (!maxarr.isEmpty() && arr[maxarr.getLast()] <= arr[right]) {
				maxarr.removeLast();
			}
			maxarr.addLast(right);
			if (maxarr.getFirst() < left) {
				maxarr.removeFirst();
			}

			while (!minarr.isEmpty() && arr[minarr.getLast()] >= arr[right]) {
				minarr.removeLast();
			}
			minarr.addLast(right);
			if (minarr.getFirst() < left) {
				minarr.removeFirst();
			}
			
			if (arr[maxarr.getFirst()] - arr[minarr.getFirst()] <= num && right < len - 1) {
				right++;
			} else if (arr[maxarr.getFirst()] - arr[minarr.getFirst()] <= num) {
				res += len - left;// 此时right未改变，会重复添加此时的right到max和min中，造成单个索引多次加入max或min中
				// 所以此时要么在加入时就不允许相同大小的值存在，要么再删除时多次循环删除，直到满足条件
				left++;
			} else {
				res += right - left;
				left++;
			}

		}
		return res;

	}

	public static void main(String[] args) {
		int[] arr = { 4, 1, 4, 3, 9, 4, 6, 2, 1, 9, 1, 5 };// getRandomArray(30);
		int num = 5;
		printArray(arr);

		System.out.println(getNum(arr, num));
		System.out.println("====================");
		System.out.println(getNum2(arr, num));
		System.out.println("====================");
		System.out.println(getNum3(arr, num));

	}

}
