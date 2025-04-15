package org.russell.dsa;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PrefixSum {

  /** https://www.hellointerview.com/learn/code/prefix-sum/count-vowels */
  static int[] vowelStrings(final String word, final int[][] queries) {
    final var vowels = Set.of('a', 'e', 'i', 'o', 'u');
    final var prefixSum = new int[word.length() + 1];

    // create prefix sum array
    for (int i = 1; i < word.length() + 1; i++) {
      prefixSum[i] = prefixSum[i - 1];
      if (vowels.contains(word.charAt(i - 1))) {
        prefixSum[i] = prefixSum[i] + 1;
      }
    }

    // calculate vowels in each query
    final var result = new int[queries.length];
    var i = 0;
    for (final var query : queries) {
      final var numVowels = prefixSum[query[1] + 1] - prefixSum[query[0]];
      result[i] = numVowels;
      i++;
    }

    return result;
  }

  /** https://www.hellointerview.com/learn/code/prefix-sum/subarray-sum-equals-k */
  static int subarraySum(final int[] nums, final int k) {
    final Map<Integer, Integer> prefixCounts = new HashMap<>();
    prefixCounts.put(0, 1);
    int result = 0;
    int sum = 0;

    for (final int num : nums) {
      sum += num;
      final var diff = sum - k;
      if (prefixCounts.containsKey(diff)) {
        result += prefixCounts.get(diff);
      }

      prefixCounts.compute(sum, (key, value) -> value == null ? 1 : value + 1);
    }

    return result;
  }
}
