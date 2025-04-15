package org.russell.dsa;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PrefixSumTest {

  @Test
  void vowelStrings() {
    final var queries1 = new int[][] {{0, 2}, {1, 4}, {3, 5}};
    assertArrayEquals(new int[] {1, 2, 1}, PrefixSum.vowelStrings("prefixsum", queries1));
  }

  @Test
  void subarraySum() {
    assertEquals(3, PrefixSum.subarraySum(new int[] {1, -1, 0}, 0));
    assertEquals(2, PrefixSum.subarraySum(new int[] {2, -1, -3, 4, 2, 3}, 5));
  }
}
