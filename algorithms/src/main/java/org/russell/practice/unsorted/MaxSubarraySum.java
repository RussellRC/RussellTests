package org.russell.practice.unsorted;

/**
 * https://www.hackerrank.com/challenges/maxsubarray
 */
public class MaxSubarraySum {

    
    public static void main(String[] args) {
        
        int[] arr = {1, 56, 58, 57, 90, 92, 94, 93, 91, 45};
        System.out.println(maxContiguousSubArraySum(arr));
        System.out.println(maxNonCont(arr));
        
    }
    
    public static String maxSubarray(final int[] array) {
        final int maxNonCont = maxNonCont(array);
        final int maxCont = maxContiguousSubArraySum(array);
        return String.format("%d %d", maxCont, maxNonCont);
    }

    /**
     * Kadane's algorithm
     */
    public static int maxContiguousSubArraySum(final int[] array) {
        int maxSum = array[0];
        int currentSum = array[0];
        for (int i = 1; i < array.length; i++) {
            currentSum = Math.max(array[i], currentSum + array[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
    
    private static int maxNonCont(final int[] array) {
        int maxNonCont = Integer.MIN_VALUE;
        int greaterNeg = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                greaterNeg = Math.max(array[i], greaterNeg);
            } else {
                if (maxNonCont == Integer.MIN_VALUE) {
                    maxNonCont = array[i];
                } else {
                    maxNonCont = maxNonCont + array[i];
                }
            }
        }
        maxNonCont = Math.max(maxNonCont, greaterNeg);
        return maxNonCont;
    }
    
}