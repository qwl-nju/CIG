package chapter_1_stackandqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Problem_09_MaximalRectangle {

	public static int maxRecSize(int[][] map) {
		if (map == null || map.length == 0 || map[0].length == 0) {
			return 0;
		}
		int maxArea = 0;
		int[] height = new int[map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
			}
			maxArea = Math.max(maxRecFromBottom(height), maxArea);
		}
		return maxArea;
	}

	public static int maxRecFromBottom(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int maxArea = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
				int j = stack.pop();
				int k = stack.isEmpty() ? -1 : stack.peek();
				int curArea = (i - k - 1) * height[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length - k - 1) * height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}

	// QWL
	public static int maxRecSize2(int[][] map) {
		if (map == null || map.length == 0 || map[0].length == 0) {
			return 0;
		}

		int row = map.length;
		int cul = map[0].length;

		int max = 0;
		int[] culs = new int[cul];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < cul; j++) {
				culs[j] = map[i][j] == 1 ? culs[j] + 1 : 0;
			}
			max = Math.max(max, maxRecFromBottom2(culs));
		}
		return max;

	}

	// QWL
	public static int maxRecFromBottom2(int[] height) {
		List<Integer> lfirst = new ArrayList<>();
		List<Integer> rfirst = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < height.length; i++) {
			while (true) {
				if (stack.isEmpty()) {
					stack.push(i);
					lfirst.add(-1);
					break;
				} else if (height[stack.peek()] < height[i]) {
					lfirst.add(stack.peek());
					stack.push(i);
					break;
				} else {
					stack.pop();
				}
			}
		}

		stack = new Stack<>();
		for (int i = height.length - 1; i >= 0; i--) {
			while (true) {
				if (stack.isEmpty()) {
					stack.push(i);
					rfirst.add(height.length);
					break;
				} else if (height[stack.peek()] < height[i]) {
					rfirst.add(stack.peek());
					stack.push(i);
					break;
				} else {
					stack.pop();
				}
			}
		}
		Collections.reverse(rfirst);

		int max = 0;
		for (int i = 0; i < height.length; i++) {
			max = Math.max(max, height[i] * (rfirst.get(i) - lfirst.get(i) - 1));
		}
		return max;
	}

	public static void main(String[] args) {
		int[][] map = { { 1, 1, 1, 1 },{ 1, 0, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 0 },{ 1, 1, 1, 1 },{ 1, 1, 1, 1 },{ 1, 1, 1, 1 } };
		System.out.println(maxRecSize(map));
		System.out.println("======================");
		System.out.println(maxRecSize2(map));
		
	}

}
