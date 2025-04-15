package org.russell.dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SlidingWindow {

  /** https://www.hellointerview.com/learn/code/sliding-window/variable-length#solution */
  int totalFruit(final int[] fruits) {

    int start = 0;
    int end = 0;
    int maxFruits = 0;
    final Map<Integer, Integer> fruitCounts = new HashMap<>();

    while (end < fruits.length) {
      fruitCounts.compute(fruits[end], (k, v) -> v == null ? 1 : v + 1);

      while (fruitCounts.size() >= 3) {
        fruitCounts.put(fruits[start], fruitCounts.get(fruits[start]) - 1);
        if (fruitCounts.get(fruits[start]) == 0) {
          fruitCounts.remove(fruits[start]);
        }
        start++;
      }

      maxFruits = Math.max(maxFruits, end - start + 1);
      end++;
    }

    return maxFruits;
  }

  /**
   * https://www.hellointerview.com/learn/code/sliding-window/longest-substring-without-repeating-characters
   */
  int longestSubstringWithoutRepeat(final String s) {
    int start = 0;
    int maxLength = 0;
    final Set<Character> uniques = new HashSet<>();

    for (int end = 0; end < s.length(); end++) {
      while (uniques.contains(s.charAt(end))) {
        uniques.remove(s.charAt(start));
        start++;
      }

      uniques.add(s.charAt(end));
      maxLength = Math.max(maxLength, uniques.size());
    }

    return maxLength;
  }

  /** https://www.hellointerview.com/learn/code/sliding-window/longest-repeating-character-replacement */
  int characterReplacement(final String s, final int k) {
    int start = 0;
    int maxLength = 0;
    int maxFrequency = 0;
    final Map<Character, Integer> charCounts = new HashMap<>();

    for (int end = 0; end < s.length(); end++) {
      final char charAtEnd = s.charAt(end);
      charCounts.compute(charAtEnd, (key, value) -> value == null ? 1 : value + 1);
      maxFrequency = Math.max(maxFrequency, charCounts.get(charAtEnd));

      if (k + maxFrequency < end - start + 1) {
        charCounts.computeIfPresent(s.charAt(start), (key, value) -> value - 1);
        start++;
      }

      maxLength = Math.max(maxLength, end - start + 1);
    }

    return maxLength;
  }

  /** https://www.hellointerview.com/learn/code/sliding-window/maximum-sum-of-subarrays-of-size-k */
  int maxSubarraySum(final int[] nums, final int k) {
    int start = 0;
    int end = 0;
    int maxSum = 0;
    int currentSum = 0;

    while (end < nums.length) {
      currentSum = currentSum + nums[end];

      if (end - start + 1 == k) {
        maxSum = Math.max(maxSum, currentSum);
        currentSum = currentSum - nums[start];
        start++;
      }

      end++;
    }

    return maxSum;
  }

  /** https://www.hellointerview.com/learn/code/sliding-window/maximum-sum-of-subarrays-of-size-k */
  int maxPointsFromCards(final int[] cards, final int k) {
    final int cardsSum = Arrays.stream(cards).sum();

    int maxPoints, start, end, currentSum;
    maxPoints = start = end = currentSum = 0;

    final int windowSize = cards.length - k;

    while (end < cards.length) {
      currentSum = currentSum + cards[end];

      if (end - start + 1 == windowSize) {
        maxPoints = Math.max(maxPoints, cardsSum - currentSum);
        currentSum = currentSum - cards[start];
        start++;
      }

      end++;
    }

    return maxPoints;
  }

  /** https://www.hellointerview.com/learn/code/sliding-window/maximum-sum-of-distinct-subarrays-with-length-k */
  int maxSumUnique(final int[] nums, final int k) {
    int maxSum, start, end, currentSum;
    maxSum = start = end = currentSum = 0;

    final Map<Integer, Integer> numCounts = new HashMap<>();

    while (end < nums.length) {
      currentSum = currentSum + nums[end];
      numCounts.compute(nums[end], (key, value) -> value == null ? 1 : value + 1);

      if (end - start + 1 == k) {
        if (numCounts.size() == k) {
          // if size of counts is same as k, it means all elements are unique
          maxSum = Math.max(maxSum, currentSum);
        }

        currentSum = currentSum - nums[start];
        numCounts.computeIfPresent(nums[start], (key, value) -> value - 1);
        if (numCounts.get(nums[start]) == 0) {
          numCounts.remove(nums[start]);
        }
        start++;
      }

      end++;
    }

    return maxSum;
  }
}
