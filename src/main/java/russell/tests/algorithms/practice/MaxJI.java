package russell.tests.algorithms.practice;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
 * Given an array arr[], find the maximum j – i such that arr[j] > arr[i].
 */
public class MaxJI {

    public static void main(String[] args) {
        
        System.out.println(maxJI(new int[]{34, 8, 10, 3, 2, 80, 30, 33, 1}));
        System.out.println(maxJI(new int[]{9, 2, 3, 4, 5, 6, 7, 8, 18, 0}));
        
    }

    private static int maxJI(int[] arr) {
        
        final int[] minLeft = new int[arr.length];
        minLeft[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            minLeft[i] = Math.min(arr[i], minLeft[i-1]);
        }
        System.out.println(Arrays.toString(minLeft));
        
        final int[] maxRight = new int[arr.length];
        maxRight[arr.length-1] = arr[arr.length-1];
        for (int j = arr.length-2; j >=0; j--) {
            maxRight[j] = Math.max(arr[j], maxRight[j+1]);
        }
        System.out.println(Arrays.toString(maxRight));
        
        int i = 0;
        int j = 0;
        int maxDiff = Integer.MIN_VALUE;
        while (i < arr.length && j < arr.length) {
            if (minLeft[i] < maxRight[j]) {
                maxDiff = Math.max(maxDiff, j - i);
                j++;
            } else {
                i++;
            }
//            System.out.println(i + "," + j);
        }
//        System.out.println(i + "," + j);
        
        return maxDiff;
    }
}
