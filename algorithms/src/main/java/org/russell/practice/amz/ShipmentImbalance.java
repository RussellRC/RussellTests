package org.russell.practice.amz;

import org.russell.util.Pair;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://cybergeeksquad.co/2022/02/shipment-imbalance-amazon-oa.html
 * You are given an integer array.
 * The range of a subarray is the difference between the largest and smallest element in the subarray.
 * Find the sum of all subarray ranges of the array
 */
public class ShipmentImbalance {

    public static long subArrayRanges(final int[] nums) {

        // find number of smaller elements from left
        LinkedList<Pair<Long, Long>> linkedList = new LinkedList<>();
        final long[] lesserLeft = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            long count = 1;
            while (!linkedList.isEmpty() && linkedList.peekFirst().first <= nums[i]) {
                count = count + linkedList.removeFirst().second;
            }
            linkedList.addFirst(Pair.of((long) nums[i], count));
            lesserLeft[i] = count;
        }

        // find number of smaller elements from right
        linkedList = new LinkedList<>();
        final long[] lesserRight = new long[nums.length];
        for (int i = nums.length-1; i >= 0; i--) {
            long count = 1;
            while (!linkedList.isEmpty() && linkedList.peekFirst().first < nums[i]) {
                count = count + linkedList.removeFirst().second;
            }
            linkedList.addFirst(Pair.of((long) nums[i], count));
            lesserRight[i] = count;
        }

        // find number of greater elements from left
        linkedList = new LinkedList<>();
        final long[] greaterLeft = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            long count = 1;
            while (!linkedList.isEmpty() && linkedList.peekFirst().first >= nums[i]) {
                count = count + linkedList.removeFirst().second;
            }
            linkedList.addFirst(Pair.of((long) nums[i], count));
            greaterLeft[i] = count;
        }

        // find number of greater elements from right
        linkedList = new LinkedList<>();
        final long[] greaterRight = new long[nums.length];
        for (int i = nums.length-1; i >= 0; i--) {
            long count = 1;
            while (!linkedList.isEmpty() && linkedList.peekFirst().first > nums[i]) {
                count = count + linkedList.removeFirst().second;
            }
            linkedList.addFirst(Pair.of((long) nums[i], count));
            greaterRight[i] = count;
        }

        long result = 0;
        // (max occurrences - min occurrences) * value
        for (int i = 0; i < nums.length; i++) {
            final long occurrences = ((lesserLeft[i] * lesserRight[i]) - (greaterLeft[i] * greaterRight[i])) * nums[i];
            result = result + occurrences;
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(13L, subArrayRanges(new int[]{3, 1, 2, 4}));
    }

}
