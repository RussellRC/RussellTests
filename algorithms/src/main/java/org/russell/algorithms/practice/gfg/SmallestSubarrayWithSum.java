package org.russell.algorithms.practice.gfg;

/**
 * http://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/
 * 
 * Given an array of integers and a number x,
 * find the smallest subarray with sum greater than the given value.
 */
public class SmallestSubarrayWithSum {

    public static void main(String[] args) {

        // Minimum length subarray is {4, 45, 6}
        System.out.println(ssws(new int[] { 1, 4, 45, 6, 0, 19 }, 51));
        
        // Minimum length subarray is {100, 1, 0, 200}
        System.out.println(ssws(new int[] {1, 11, 100, 1, 0, 200, 3, 2, 1, 250}, 280));
        
        // Not possible
        System.out.println(ssws(new int[] {1, 2, 4}, 8));

    }

    private static int ssws(final int[] arr, final int targetSum) {
        int start = 0, end = 0;
        int minLen = arr.length;
        int sum = 0;

        while (end < arr.length) {
            while (sum <= targetSum && end < arr.length) {
                sum += arr[end++];
            }

            while (sum > targetSum && start < arr.length) {
                if ((end - start) < minLen)
                    minLen = end - start;

                sum -= arr[start++];
            }
        }
        
        if (minLen == arr.length && sum <= targetSum)
            return -1; // we could throw exception
        
        return minLen;
    }

}
