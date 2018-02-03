package chapter_3_binarytreeproblem;

import java.util.HashMap;
import java.util.Map;

public class Problem_22_PreAndInArrayToPosArray {

	public static int[] getPosArray(int[] pre, int[] in) {
		if (pre == null || in == null) {
			return null;
		}
		int len = pre.length;
		int[] pos = new int[len];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < len; i++) {
			map.put(in[i], i);
		}
		setPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
		return pos;
	}

	// 从右往左依次填好后序数组s
	// si为后序数组s该填的位置
	// 返回值为s该填的下一个位置
	public static int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj, int[] s, int si,
			HashMap<Integer, Integer> map) {
		if (pi > pj) {
			return si;
		}
		s[si--] = p[pi];
		int i = map.get(p[pi]);
		si = setPos(p, pj - nj + i + 1, pj, n, i + 1, nj, s, si, map);
		return setPos(p, pi + 1, pi + i - ni, n, ni, i - 1, s, si, map);
	}

	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static int[] getPosArray2(int[] pre, int[] in) {
		if (pre == null) {
			return null;
		}

		int[] res = new int[pre.length];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		int[] now = new int[1];
		now[0] = res.length - 1;
		isHelpGetPosArray2(pre, in, 0, pre.length - 1, 0, in.length - 1, map, res, now);
		return res;
	}

	public static void isHelpGetPosArray2(int[] pre, int[] in, int pl, int pr, int il, int ir,
			Map<Integer, Integer> map, int[] res, int[] now) {
		if (pl > pr) {
			return;
		}
		res[now[0]] = pre[pl];
		now[0]--;
		int index = map.get(pre[pl]);
		isHelpGetPosArray2(pre, in, pl + index - il + 1, pr, index + 1, ir, map, res, now);
		isHelpGetPosArray2(pre, in, pl + 1,pl + index - il, il, index - 1, map, res, now);
	}

	public static void main(String[] args) {
		int[] pre = { 1, 2, 4, 5, 8, 9, 3, 6, 7 };
		int[] in = { 4, 2, 8, 5, 9, 1, 6, 3, 7 };
		int[] pos = getPosArray(pre, in);
		printArray(pos);
		
		int[] pos2 = getPosArray2(pre, in);
		printArray(pos2);

	}
}
