package org.russell.practice.lc;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 */
public class MaxSlidingWindow {

    @Test
    public void test1() {
        final int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        final int windowSize = 3;
        assertEquals(List.of(3, 3, 5, 5, 6, 7), maxSlidingWindow(nums, windowSize));
    }

    @Test
    public void test2() {
        final int[] nums = {8, 7, 6, 9};
        final int windowSize = 2;
        assertEquals(List.of(8, 7, 9), maxSlidingWindow(nums, windowSize));
    }

    @Test
    public void test3() {
        final int[] nums = {1, 1, 1, 1, 1, 4, 5};
        final int windowSize = 6;
        assertEquals(List.of(4, 5), maxSlidingWindow(nums, windowSize));
    }


    public static List<Integer> maxSlidingWindow(final int[] numbers, final int windowSize) {
        final List<Integer> result = new LinkedList<>();
        final Deque<Integer> indexesDeque = new LinkedList<>(); // Index
        int left = 0;
        int right = 0;

        while (right < numbers.length) {
            // remove from queue smaller values than numbers[right]
            while (!indexesDeque.isEmpty() && numbers[indexesDeque.peekLast()] < numbers[right]) {
                indexesDeque.removeLast();
            }
            indexesDeque.addLast(right);

            if (!indexesDeque.isEmpty() && left > indexesDeque.peekFirst()) {
                indexesDeque.removeFirst();
            }

            if (right+1 >= windowSize) {
                int maxOfWindow = numbers[indexesDeque.peekFirst()];
                result.add(maxOfWindow);
                left = left+1;
            }
            right = right+1;
        }

        return result;
    }
}
