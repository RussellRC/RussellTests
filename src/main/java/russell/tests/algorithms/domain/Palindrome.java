package russell.tests.algorithms.domain;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {

	public static void main(String[] args) {
		String s = "abcde";
		String sub1 = s.substring(0, 4);
		String sub2 = s.substring(sub1.length());
		String sub3 = s.substring(s.length());
		
		System.out.println(s);
		System.out.println(sub1);
		System.out.println(sub2);
		System.out.println("sub3:" + sub3);
	}
	
	/**
	 * Is plaindrome
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome(final String s) {
		if (s == null || s.isEmpty()) {
			return true;
		}

		final String word = s.toLowerCase();
		int start = word.length() % 2 == 0 ? (word.length() / 2) - 1 : word.length() / 2;
		int end = word.length() % 2 == 0 ? start + 1 : start;

		while (start >= 0 && end <= word.length() - 1 && word.charAt(start) == word.charAt(end)) {
			start--;
			end++;
		}

		return start < 0;
	}

	/**
	 * Longest Palindrome
	 */
	public static String longestPalindrome(final String string) {
		if (string == null || string.isEmpty()) {
			return string;
		}

		String temp;
		String longestPalindrome = string.substring(0, 1);

		for (int i = 0; i < string.length() - 1; i++) {
			temp = longestPalindrome(string, i, i);
			if (temp.length() > longestPalindrome.length()) {
				longestPalindrome = temp;
			}

			temp = longestPalindrome(string, i, i + 1);
			if (temp.length() > longestPalindrome.length()) {
				longestPalindrome = temp;
			}
		}

		return longestPalindrome;
	}

	private static String longestPalindrome(String string, int left, int right) {

		while (left >= 0 && right <= string.length() - 1 && string.charAt(left) == string.charAt(right)) {
			left--;
			right++;
		}

		// left can be -1, right will always be the +1 character we want
		return string.substring(left + 1, right);
	}

	public static List<List<String>> partitions(String s) {
		List<String> partition = new ArrayList<>();
		List<List<String>> result = new ArrayList<>();
		partitions(s, s.length(), partition, result);
		return result;
	}

	private static void partitions(final String s, int end, List<String> partition, List<List<String>> result) {
		// stop condition
		if (end == 0) {
			ArrayList<String> temp = new ArrayList<String>(partition);
			result.add(temp);
			return;
		}

		for (int i = end; i >= 0; i--) {
			if (partition.isEmpty() || partition.get(partition.size() - 1).length() >= i) {
				String str = s.substring(0, i); 
				if (str.isEmpty()) {
					return;
				}
				if (isPalindrome(str)) {
					partition.add(str);
					str = s.substring(str.length());
					partitions(str, str.length(), partition, result);
					partition.remove(partition.size() - 1);
				}
			}
		}
	}
}
