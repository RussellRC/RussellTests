package org.russell.practice.gfg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/find-pair-with-greatest-product-in-array/
 *
 * Given an array of n elements, 
 * find the greatest number such that it is product of two elements of given array. 
 * If no such element exists, print -1.
 * Elements are within the range of 1 to 10^5.
 */
public class PairsWithGreatestProduct {

    public static void main(String[] args) {
        
        int[] arr = {10, 3, 5, 30, 35};
        System.out.println(pgp(arr));
        
        arr = new int[]{2, 5, 7, 8};
        System.out.println(pgp(arr));
        
        arr = new int[]{10, 2, 4, 30, 35};
        System.out.println(pgp(arr));
        
        arr = new int[]{10, 2, 2, 4, 30, 35};
        System.out.println(pgp(arr));
        
        arr = new int[]{17, 2, 1, 35, 30};
        System.out.println(pgp(arr));
    }

    private static int pgp(int[] arr) {
        final Map<Integer, Integer> map = new HashMap<>();
        for (int e : arr) {
            Integer v = map.get(e);
            if (v == null) {
                map.put(e, 1);
            } else {
                map.put(e, 2);
            }
        }
        
        Arrays.sort(arr);
        
        for (int i = arr.length-1; i >=0; i--)  {
            for (int j = 0; j < i && arr[j] <= Math.sqrt(arr[i]); j++) {
                if (arr[i] % arr[j] == 0) {
                    int result = arr[i]/arr[j];
                    Integer v = map.get(result);
                    if (result != arr[j] && v != null) {
                        return arr[i];
                    }
                    
                    if (result == arr[j] && v != null && v == 2) {
                        return arr[i];
                    }
                }
            }
        }
        return -1;
    }
    
}
