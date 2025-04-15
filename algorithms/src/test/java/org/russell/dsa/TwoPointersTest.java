package org.russell.dsa;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TwoPointersTest {

  private TwoPointers tp;

  @BeforeEach
  void beforeEach() {
    tp = new TwoPointers();
  }

  @Test
  void maxArea() {
    assertEquals(2, tp.maxArea(new int[] {1, 2, 1}));
    assertEquals(21, tp.maxArea(new int[] {3, 4, 1, 2, 2, 4, 1, 3, 2}));
  }

  @Test
  void threeSum() {
    final var expected = List.of(List.of(-1, -1, 2), List.of(-1, 0, 1));
    assertEquals(expected, tp.threeSum(new int[] {-1, 0, 1, 2, -1, -1}));
  }

  @Test
  void validTriangles() {
    assertEquals(10, tp.triangleNumbers(new int[] {11, 4, 9, 6, 15, 18}));
  }

  @Test
  void moveZeroes() {
    assertArrayEquals(new int[] {2, 4, 9, 0, 0}, tp.moveZeroes(new int[] {2, 0, 4, 0, 9}));
  }

  @Test
  void sortColors() {
    assertArrayEquals(
        new int[] {0, 0, 0, 1, 1, 1, 1, 2, 2},
        tp.sortColors(new int[] {2, 1, 2, 0, 1, 0, 1, 0, 1}));
  }

  @Test
  void trappedWater() {
    assertEquals(10, tp.trappingWatter(new int[] {3, 4, 1, 2, 2, 5, 1, 0, 2}));
    assertEquals(6, tp.trappingWatter(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
  }
}
