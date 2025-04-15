package org.russell.practice.unsorted;

import java.util.HashMap;
import java.util.Map;

/**
 * Ways to cover distance with 1, 2, and 3 steps
 */
public class CoverDistance {

    
    final static Map<Integer, Integer> mem = new HashMap<>();
    
    public static void main(String[] args) {
        
        int distance = 4;
        
        // Ways to cover distance for 1, 2, and 3 steps
        mem.put(0, 1);
        mem.put(1, 1);
        mem.put(2, 2);
        
        System.out.println(waysToCoverDistRecMem(distance));
        
        System.out.println(mem);
    }
    
    /**
     * Recursive with memoization
     * @param d
     * @return
     */
    public static int waysToCoverDistRecMem(final int d) {
        if (mem.get(d) != null) {
            System.out.println("cache hit for " + d);
            return mem.get(d);
        }
        
        System.out.println("cache miss for " + d);
        int ways = waysToCoverDistRecMem(d-1) + waysToCoverDistRecMem(d-2) + waysToCoverDistRecMem(d-3);
        mem.put(d, ways);
        return ways;
    }
    
    /**
     * DP solution
     * @param dist
     * @return
     */
    public static int countDP(final int d) {
        final int[] count = new int[d+1];
        count[0] = 1;
        count[1] = 1;
        count[2] = 2;
     
        // Fill the solution array in bottom up manner
        for (int i=3; i<=d; i++) {
            count[i] = count[i-1] + count[i-2] + count[i-3];
        }
     
        return count[d];
    }
}
