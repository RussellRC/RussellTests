package org.russell.algorithms.practice.lc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Leetcode 152
 */
public class MaxProductOfSubarrays {

    @Test
    public void test1() {
        assertEquals(6, maxProduct(new int[]{2, 3, -2, 4}));
    }

    @Test
    public void test2() {
        assertEquals(8, maxProduct(new int[]{2, 0, -2, 4, 2, 0}));
    }

    public long maxProduct(final int[] numbers) {
        long result = Arrays.stream(numbers).max().orElseThrow(); // base case: max product is the max number
        long currentMax = 1;
        long currentMin = 1;
        for (int num : numbers) {
            long tempMax = currentMax;
            currentMax = Math.max(Math.max(num * currentMax, num * currentMin), num);
            currentMin = Math.min(Math.min(num * tempMax, num * currentMin), num);
            result = Math.max(result, currentMax);
        }

        return result;
    }
}
