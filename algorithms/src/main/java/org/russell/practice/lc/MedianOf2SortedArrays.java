package org.russell.practice.lc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOf2SortedArrays {

    public static double findMedianSortedArrays(int[] numsA, int[] numsB) {
        final int[] merged = new int[numsA.length + numsB.length];
        int i = 0;
        int j = 0;
        int m = 0;
        while (i < numsA.length && j < numsB.length) {
            if (numsA[i] <= numsB[j]) {
                merged[m] = numsA[i];
                i++;
            } else {
                merged[m] = numsB[j];
                j++;
            }
            m++;
        }
        while (i < numsA.length) {
            merged[m] = numsA[i++];
            m++;
        }
        while (j < numsB.length) {
            merged[m] = numsB[j++];
            m++;
        }

        if (merged.length%2 == 0) {
            int midIndex = merged.length/2-1;
            return ((double) (merged[midIndex] + merged[midIndex+1]) / 2);
        } else {
            return merged[merged.length/2];
        }
    }

    @Test
    public void test1() {
        assertEquals(2.0, findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
    }

    @Test
    public void test2() {
        assertEquals(2.5, findMedianSortedArrays(new int[]{1,2}, new int[]{3, 4}));
    }
}
