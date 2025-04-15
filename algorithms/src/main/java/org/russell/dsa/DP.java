package org.russell.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DP {

  /** https://www.hellointerview.com/learn/code/dynamic-programming/solving-a-question-with-dp */
  public int rob(final int[] treasure) {
    if (treasure == null || treasure.length == 0) {
      return 0;
    }

    final var dp = new int[treasure.length + 1];
    dp[0] = 0;
    dp[1] = treasure[0];

    for (int i = 2; i < treasure.length + 1; i++) {
      final var sol2 = dp[i - 2] + treasure[i - 1];
      final var sol1 = dp[i - 1];
      dp[i] = Math.max(sol2, sol1);
    }
    return dp[treasure.length];
  }

  /** https://www.hellointerview.com/learn/code/dynamic-programming/counting-bits */
  public int[] countBits(final int num) {
    final int[] dp = new int[num + 1];
    dp[0] = 0;
    dp[1] = 1;

    for (int i = 2; i <= num; i++) {
      final int div = i / 2;
      if (i % 2 == 0) {
        dp[i] = dp[div];
      } else {
        dp[i] = dp[div] + 1;
      }
    }

    return dp;
  }

  /** https://www.hellointerview.com/learn/code/dynamic-programming/decode-ways */
  public int numDecodings(final String s) {
    if (s == null || s.startsWith("0")) {
      return 0;
    }

    final int[] dp = new int[s.length() + 1];
    dp[0] = 1; // one way to decode an empty string
    dp[1] = 1; // one way to decode the first char

    for (int i = 2; i < s.length() + 1; i++) {
      final var digit = s.charAt(i - 1);
      if (digit != '0') {
        dp[i] += dp[i - 1];
      }

      final var digits = Integer.parseInt(s.substring(i - 2, i));
      if (10 <= digits && digits <= 26) {
        dp[i] += dp[i - 2];
      }
    }

    return dp[s.length()];
  }

  /**
   * <a href="https://www.hellointerview.com/learn/code/dynamic-programming/maximal-square">HI:
   * Maximal Square</a>
   */
  public int maximalSquare(int[][] matrix) {
    final int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
    int maxSide = 0;

    for (int y = 1; y < matrix.length + 1; y++) {
      for (int x = 1; x < matrix[0].length + 1; x++) {
        if (matrix[y - 1][x - 1] == 1) {
          final var left = dp[y][x - 1];
          final var top = dp[y - 1][x];
          final var diag = dp[y - 1][x - 1];

          final var min = Math.min(left, Math.min(top, diag)) + 1;
          dp[y][x] = min;
          maxSide = Math.max(maxSide, dp[y][x]);
        }
      }
    }

    return maxSide * maxSide;
  }

  /** https://www.hellointerview.com/learn/code/dynamic-programming/unique-paths */
  public int uniquePaths(final int m, final int n) {
    if (m == 1 || n == 1) {
      return 1;
    }

    // Initialize: all top and left squares start with 1 unique path
    final int[][] dp = new int[m][n];
    for (int x = 0; x < n; x++) {
      dp[0][x] = 1;
    }
    for (int y = 0; y < m; y++) {
      dp[y][0] = 1;
    }

    for (int y = 1; y < m; y++) {
      for (int x = 1; x < n; x++) {
        dp[y][x] = dp[y - 1][x] + dp[y][x - 1];
      }
    }

    return dp[m - 1][n - 1];
  }

  /** https://www.hellointerview.com/learn/code/dynamic-programming/longest-increasing-subsequence */
  public int longestIncreasingSubsequence(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    int max = dp[0];

    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          int maxSubsequence = Math.max(dp[i], dp[j] + 1);
          dp[i] = maxSubsequence;
        }
        max = Math.max(max, dp[i]);
      }
    }

    return max;
  }

  /** https://www.hellointerview.com/learn/code/dynamic-programming/word-break */
  public boolean wordBreak(final String s, final List<String> wordDict) {
    final boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;

    for (int i = 1; i < s.length() + 1; i++) {
      for (final var word : wordDict) {
        if (i >= word.length() && dp[i - word.length()]) {
          final var substring = s.substring(i - word.length(), i);
          if (word.equals(substring)) {
            dp[i] = true;
            break; // a word from the dictionary is a substring in "s"
          }
        }
      }
    }

    return dp[dp.length - 1];
  }

  /**
   * https://www.hellointerview.com/learn/code/dynamic-programming/maximum-profit-in-job-scheduling
   */
  public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    final List<Job> jobs = new ArrayList<>();
    for (int i = 0; i < startTime.length; i++) {
      jobs.add(new Job(startTime[i], endTime[i], profit[i]));
    }

    final Comparator<Job> byEndComparator = Comparator.comparingInt(job -> job.end);
    jobs.sort(byEndComparator);

    // dp[i] represents the maximum profit that can be earned by scheduling the first i jobs (sorted
    // by end time).
    final int[] dp = new int[startTime.length + 1];
    dp[0] = 0; // 0 jobs => 0 profit

    for (int i = 1; i < jobs.size() + 1; i++) {
      final var job = jobs.get(i - 1);
      final var numJobs = findJobsEndingBefore(jobs, job.start);
      dp[i] = Math.max(dp[i - 1], dp[numJobs] + job.profit);
    }

    return dp[dp.length - 1];
  }

  /**
   * Helper function. Technically this is a bisectRight by end time
   */
  private static int findJobsEndingBefore(final List<Job> jobs, int time) {
    int low = 0;
    int high = jobs.size();
    while (low < high) {
      int mid = (low + high) / 2;
      if (time < jobs.get(mid).end) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  private static final class Job {
    private final int start;
    private final int end;
    private final int profit;

    private Job(int start, int end, int profit) {
      this.start = start;
      this.end = end;
      this.profit = profit;
    }
  }

  /**
   * Given two strings, ‘s’ and ‘t,’ where ‘t’ is a subsequence of ‘s', identify and remove the
   * longest substring from ‘s’ such that ‘s’ still contains ‘t’ as a subsequence.
   */
  public String removeSubstring(final String s, final String t) {
    //  bbdebxdxedede - bde
    // tttttf
    //      fttttttttt

    // Chars that can be removed from the left so that right substring still contains 't'
    final boolean[] dpLeft = new boolean[s.length() + 1];
    dpLeft[0] = true; // removing nothing from 's' contains 't'
    int maxLeft = 0;

    // Chars that can be removed from the right so that right substring still contains 't'
    final boolean[] dpRight = new boolean[s.length() + 1];
    dpRight[0] = true;
    int maxRight = 0;

    String subLeft = s, subRight = s;
    for (int i = 1; i < s.length() + 1; i++) {

      if (!dpLeft[i - 1] && !dpRight[i - 1]) {
        // Can not remove chars from either left or right so that 't' is still a subsequence
        break;
      }

      subLeft = s.substring(i);
      if (dpLeft[i - 1] && isSubsequence(subLeft, t)) {
        dpLeft[i] = true;
        maxLeft = i;
      }

      subRight = s.substring(0, s.length() - i);
      if (dpRight[i - 1] && isSubsequence(subRight, t)) {
        dpRight[i] = true;
        maxRight = i;
      }
    }

    return maxLeft > maxRight ? s.substring(0, maxLeft + 1) : s.substring(s.length() - maxRight);
  }

  /** helper */
  private boolean isSubsequence(final String str, final String sub) {
    int i = 0, j = 0;
    while (i < str.length() && j < sub.length()) {
      if (str.charAt(i) == sub.charAt(j)) {
        j++;
      }
      i++;
    }

    return j == sub.length();
  }


}
