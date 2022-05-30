package org.russell.algorithms.practice.gfg;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println(lisNLogN(arr));
        
        
    }

    /**
     * Last element is the LIS
     */
    private static List<List<Integer>> lisNLogN(int[] arr) {
        
        final List<List<Integer>> result = new ArrayList<>();
        
        for (final int e : arr) {
            //System.out.println(result);
            
            // Case 1:  No active lists, create new one
            if (result.isEmpty()) {
                final List<Integer> newList = new ArrayList<>();
                newList.add(e);
                result.add(newList);
                continue;
            }
            
            // Case 2:  Element is largest. Clone largest and extend
            final List<Integer> largest = result.get(result.size()-1);
            if (e > largest.get(largest.size()-1)) {
                final List<Integer> clone = new ArrayList<>(largest);
                clone.add(e);
                result.add(clone);
                continue;
            }
            
            // Case3:  Element is in between.
            // Clone and extend list with end element smaller than e
            int i = result.size() - 1;
            List<Integer> temp = result.get(i);
            while (temp.get(temp.size()-1) > e && i >= 0) {
                i--;
                temp = result.get(i);
            }
            final List<Integer> clone = new ArrayList<>(temp);
            clone.add(e);
            if (result.get(i+1).size() == clone.size()) {
                result.set(i+1, clone);
            }
        }
        
        
        return result;
    }
    
}
