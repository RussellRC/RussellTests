package org.russell.dsa;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class StackPractice {

  /** https://www.hellointerview.com/learn/code/stack/valid-parentheses */
  boolean isBalanced(final String s) {
    final Map<Character, Character> brackets = Map.of(')', '(', ']', '[', '}', '{');
    final Deque<Character> stack = new LinkedList<>();

    for (int i = 0; i < s.length(); i++) {
      if (!brackets.containsKey(s.charAt(i))) {
        stack.push(s.charAt(i));
      } else {
        if (Objects.equals(stack.peek(), brackets.get(s.charAt(i)))) {
          stack.pop();
        } else {
          return false;
        }
      }
    }

    return stack.isEmpty();
  }

  /** https://www.hellointerview.com/learn/code/stack/decode-string */
  String decodeString(final String s) {
    final Deque<String> stack = new LinkedList<>();

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != ']') {
        stack.push(Character.toString(s.charAt(i)));
      } else {
        final StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty() && !stack.peek().matches("\\d")) {
          final String rep = stack.pop();
          if (!rep.equals("[")) {
            sb.insert(0, rep);
          }
        }
        final StringBuilder numStr = new StringBuilder();
        while (!stack.isEmpty() && stack.peek().matches("\\d")) {
          numStr.insert(0, stack.pop());
        }
        final var num = Integer.parseInt(numStr.toString());
        final StringBuilder res = new StringBuilder();
        IntStream.range(0, num).forEach(n -> res.insert(0, sb));
        stack.push(res.toString());
      }
    }

    final StringBuilder result = new StringBuilder();
    while (!stack.isEmpty()) {
      result.insert(0, stack.pop());
    }
    return result.toString();
  }

  /** https://www.hellointerview.com/learn/code/stack/longest-valid-parentheses */
  int longestValidParentheses(final String s) {
    final Deque<Integer> stack = new LinkedList<>();
    stack.push(-1);
    int maxLength = 0;

    for (int i = 0; i < s.length(); i++) {
      final char currentChar = s.charAt(i);
      if (currentChar == '(') {
        stack.push(i);
      } else {
        stack.pop();
        if (stack.isEmpty()) {
          // empty stack, start a new valid substring
          stack.push(i);
        } else {
          // stack not empty, update length of valid substring
          maxLength = Math.max(maxLength, i - stack.peek());
        }
      }
    }

    return maxLength;
  }

  /** https://www.hellointerview.com/learn/code/stack/monotonic-stack */
  int[] nextGreaterElement(final int[] nums) {
    final int[] result = new int[nums.length];
    Arrays.fill(result, -1);

    final Deque<Integer> stack = new LinkedList<>();

    for (int i = 0; i < nums.length; i++) {
      while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
        final int index = stack.pop();
        result[index] = nums[i];
      }
      stack.push(i);
    }

    return result;
  }

  /** https://www.hellointerview.com/learn/code/stack/daily-temperatures */
  int[] dailyTemperatures(final int[] temps) {
    final int[] result = new int[temps.length];
    final Deque<Integer> stack = new LinkedList<>();

    for (int i = 0; i < temps.length; i++) {
      while (!stack.isEmpty() && temps[i] > temps[stack.peek()]) {
        final int index = stack.pop();
        result[index] = i - index;
      }
      stack.push(i);
    }

    return result;
  }

  /** https://www.hellointerview.com/learn/code/stack/largest-rectangle-in-histogram */
  int largestRectangleArea(final int[] heights) {
    final Deque<Integer> stack = new LinkedList<>();
    int maxArea = 0;
    int i = 0;

    while (i < heights.length) {
      if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
        // current bar is taller than any previous
        stack.push(i);
        i++;
      } else {
        // bar at `i` is smaller than bar at heights[stack.peek()]
        // which means that we can compute the area of the bar at stack.peek,
        // because bar at `i` is the right boundary and therefore not part of the area
        final int height = heights[stack.pop()];
        final int rightBar = i - 1;
        final int leftBar = stack.isEmpty() ? -1 : stack.peek();
        final int width = rightBar - leftBar;
        final int area = width * height;
        maxArea = Math.max(maxArea, area);
      }
    }

    // Compute rectangle areas that have not been computed
    while (!stack.isEmpty()) {
      final int height = heights[stack.pop()];
      final int rightBar = i - 1;
      final int leftBar = stack.isEmpty() ? -1 : stack.peek();
      final int width = rightBar - leftBar;
      final int area = width * height;
      maxArea = Math.max(maxArea, area);
    }

    return maxArea;
  }
}
