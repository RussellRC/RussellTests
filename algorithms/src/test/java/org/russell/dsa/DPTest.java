package org.russell.dsa;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DPTest {

  private DP dp;

  @BeforeEach
  void beforeEach() {
    dp = new DP();
  }

  @Test
  void rob() {
    assertEquals(12, dp.rob(new int[] {3, 1, 4, 1, 5}));
  }

  @Test
  void countBits() {
    assertArrayEquals(new int[] {0, 1, 1, 2, 1, 2, 2}, dp.countBits(6));
  }

  @Test
  void decodeWays() {
    assertEquals(1, dp.numDecodings("101")); // "JA"
    assertEquals(2, dp.numDecodings("11106")); // "AAJF", "KJF"
  }

  @Test
  void maxSquare1() {
    assertEquals(0, dp.maximalSquare(new int[][] {{0}}));
    assertEquals(1, dp.maximalSquare(new int[][] {{1}}));
    assertEquals(1, dp.maximalSquare(new int[][] {{0, 1}, {1, 0}}));
    assertEquals(4, dp.maximalSquare(new int[][] {{1, 1}, {1, 1}}));

    var matrix =
        new int[][] {
          {0, 0, 1, 0, 0},
          {1, 1, 1, 0, 1},
          {0, 1, 1, 0, 0},
        };
    assertEquals(4, dp.maximalSquare(matrix));
  }

  @Test
  void uniquePaths() {
    assertEquals(3, dp.uniquePaths(3, 2));
  }

  @Test
  void longestIncreasingSubsequence() {
    assertEquals(4, dp.longestIncreasingSubsequence(new int[] {8, 2, 4, 3, 6, 12}));
  }

  @Test
  void wordBreak() {
    assertTrue(dp.wordBreak("cats", List.of("cats", "dog", "sand", "and", "cat")));
    assertFalse(dp.wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
    assertTrue(dp.wordBreak("catsanddog", List.of("cats", "dog", "sand", "and", "cat")));
  }

  @Test
  void jobScheduling1() {
    final var starts = new int[] {1, 3, 6, 8};
    final var ends = new int[] {4, 5, 10, 8};
    final var profits = new int[] {20, 20, 100, 70};
    assertEquals(120, dp.jobScheduling(starts, ends, profits));
  }

  @Test
  void jobScheduling2() {
    final var starts = new int[] {1, 2, 3, 3};
    final var ends = new int[] {3, 4, 5, 6};
    final var profits = new int[] {50, 10, 40, 70};
    assertEquals(120, dp.jobScheduling(starts, ends, profits));
  }

  @Test
  void jobScheduling3() {
    final var starts = new int[] {1, 1, 1};
    final var ends = new int[] {2, 3, 4};
    final var profits = new int[] {5, 6, 4};
    assertEquals(6, dp.jobScheduling(starts, ends, profits));
  }

  @Test
  void removeSubstring() {
    assertEquals("bxdxedede", dp.removeSubstring("bbdebxdxedede", "bde"));
  }
}
