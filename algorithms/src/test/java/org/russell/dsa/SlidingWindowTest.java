package org.russell.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlidingWindowTest {

  private SlidingWindow sw;

  @BeforeEach
  void beforeEach() {
    sw = new SlidingWindow();
  }

  @Test
  void totalFruit() {
    assertEquals(4, sw.totalFruit(new int[]{3, 3, 2, 1, 2, 1, 0}));
  }

  @Test
  void longestSubstringWithoutRepeat() {
    assertEquals(3, sw.longestSubstringWithoutRepeat("eghghhgg"));
    assertEquals(3, sw.longestSubstringWithoutRepeat("abcabcbb"));
    assertEquals(1, sw.longestSubstringWithoutRepeat("bbbbb"));
    assertEquals(5, sw.longestSubstringWithoutRepeat("pwwxzkew"));
  }

  @Test
  void characterReplacement() {
    assertEquals(5, sw.characterReplacement("BBABCCDD", 2));
    assertEquals(6, sw.characterReplacement("BCBABCCCCA", 2));
  }

  @Test
  void maxSubarraySum() {
    assertEquals(9, sw.maxSubarraySum(new int[]{2, 1, 5, 1, 3, 2}, 3));
  }

  @Test
  void maxPointsFromCards() {
    assertEquals(17, sw.maxPointsFromCards(new int[]{2, 11, 4, 5, 3, 9, 2}, 3));
  }

  @Test
  void maxSumUnique() {
    assertEquals(20, sw.maxSumUnique(new int[]{3, 2, 2, 3, 4, 6, 7, 7, -1}, 4));
  }
}
