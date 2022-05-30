package org.russell.algorithms.practice.lc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOf2SortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int[] merged = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int m = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                merged[m] = nums1[i];
                i++;
            } else {
                merged[m] = nums2[j];
                j++;
            }
            m++;
        }
        while (i < nums1.length) {
            merged[m] = nums1[i++];
            m++;
        }
        while (j < nums2.length) {
            merged[m] = nums2[j++];
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
