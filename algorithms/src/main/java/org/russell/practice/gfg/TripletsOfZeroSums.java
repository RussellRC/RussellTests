package org.russell.practice.gfg;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of distinct elements. The task is to find triplets in array whose sum is zero.
 * http://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
 */
public class TripletsOfZeroSums {

    public static void main(String[] args) {
        int[] arr = {0, -1, 2, -3, 1};
        tzsHash(arr);
    }

    private static void tzsHash(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            
            final Set<Integer> set = new HashSet<>();
            
            for (int j = i+1; j < arr.length; j++) {
                int diff = -(arr[i] + arr[j]);
                
                if (set.contains(diff)) {
                    // If the set already contained compliment, print
                    System.out.println(String.format("%d, %d, %d", arr[i], arr[j], diff));
                } else {
                    set.add(arr[j]);
                }
            }
        }
    }
    
}
