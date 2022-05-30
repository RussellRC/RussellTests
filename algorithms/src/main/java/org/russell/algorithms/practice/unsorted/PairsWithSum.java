package org.russell.algorithms.practice.unsorted;

import java.util.HashSet;
import java.util.Set;

public class PairsWithSum {

    public static void main(String[] args) {
        int[] arr = {1, 4, 45, 6, 10, -8};
        
        pairsWithSum(arr, 16);
    }

    private static void pairsWithSum(int[] arr, int sum) {
        
        final Set<Integer> sums = new HashSet<>();
        
        for (int n : arr) {
            if (sums.contains(sum - n)) {
                System.out.println(String.format("pair: (%d, %d)", n, sum-n));
            }
            sums.add(n);
        }
        
        
    }
    
}
