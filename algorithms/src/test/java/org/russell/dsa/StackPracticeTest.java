package org.russell.dsa;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackPracticeTest {

  private StackPractice sp;

  @BeforeEach
  void beforeEach() {
    sp = new StackPractice();
  }

  @Test
  void isBalanced() {
    assertTrue(sp.isBalanced("(){({})}"));
    assertFalse(sp.isBalanced("(){({}})"));
  }

  @Test
  void decodeString() {
    assertEquals("aaabcbc", sp.decodeString("3[a]2[bc]"));
    assertEquals("accaccacc", sp.decodeString("3[a2[c]]"));
    assertEquals("abcabccdcdcdef", sp.decodeString("2[abc]3[cd]ef"));
  }

  @Test
  void longestValidParentheses() {
    assertEquals(2, sp.longestValidParentheses("())))"));
    assertEquals(8, sp.longestValidParentheses("((()()())"));
  }

  @Test
  void nextGreaterElement() {
    int[] nums, expected;

    nums = new int[] {2, 1, 3, 2, 4, 3};
    expected = new int[] {3, 3, 4, 4, -1, -1};
    assertArrayEquals(expected, sp.nextGreaterElement(nums));
  }

  @Test
  void dailyTemperatures() {
    int[] temps, expected;

    temps = new int[] {65, 70, 68, 60, 55, 75, 80, 74};
    expected = new int[] {1, 4, 3, 2, 1, 1, 0, 0};
    assertArrayEquals(expected, sp.dailyTemperatures(temps));

    temps = new int[] {30, 40, 50, 60};
    expected = new int[] {1, 1, 1, 0};
    assertArrayEquals(expected, sp.dailyTemperatures(temps));
  }

  @Test
  void largestRectangleArea() {
    assertEquals(15, sp.largestRectangleArea(new int[] {2, 8, 5, 6, 2, 3}));
    assertEquals(10, sp.largestRectangleArea(new int[] {2, 1, 5, 6, 2, 3}));
    assertEquals(4, sp.largestRectangleArea(new int[] {2, 4}));
  }


}
