package chapter_5_stringproblem;

import java.util.HashMap;
import java.util.Map;

public class Problem_01_IsDeformation {

	public static boolean isDeformation(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() != str2.length()) {
			return false;
		}
		char[] chas1 = str1.toCharArray();
		char[] chas2 = str2.toCharArray();
		int[] map = new int[256];
		for (int i = 0; i < chas1.length; i++) {
			map[chas1[i]]++;
		}
		for (int i = 0; i < chas2.length; i++) {
			if (map[chas2[i]]-- == 0) {
				return false;
			}
		}
		return true;
	}

	// QWL
	public static boolean isDeformation2(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() != str2.length()) {
			return false;
		}
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int[] r = new int[256];

		for (int i = 0; i < str1.length(); i++) {
			r[s1[i]]++;
		}

		for (int i = 0; i < str2.length(); i++) {
			r[s2[i]]--;
			if (r[s2[i]] < 0) {
				return false;
			}

		}
		return true;
	}

	public static boolean isDeformation3(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() != str2.length()) {
			return false;
		}
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		Map<Character, Integer> map = new HashMap<>();
		
		for (int i = 0; i < str1.length(); i++) {
			if (map.containsKey(s1[i])) {
				map.put(s1[i], map.get(s1[i]) + 1);
			} else {
				map.put(s1[i], 1);
			}
		}

		for (int i = 0; i < str2.length(); i++) {
			if (!map.containsKey(s2[i]) || map.get(s2[i]) == 0) {
				return false;
			} else {
				map.put(s2[i], map.get(s2[i]) - 1);
			}

		}
		return true;
	}

	public static void main(String[] args) {
		String A = "abcabcabcabcabcabc";
		String B = "bcacbaacbabcabcabc";
		System.out.println(isDeformation(A, B));
		System.out.println("======================");
		System.out.println(isDeformation2(A, B));
		System.out.println("======================");
		System.out.println(isDeformation3(A, B));

	}

}
