package org.russell.practice.unsorted;

import java.util.HashMap;
import java.util.Map;

public class KnapSack {

    public static void main(String[] args) {

        int vals[] = { 60, 100, 120 };
        int weights[] = { 10, 20, 30 };
        int maxWeight = 50;

        System.out.println(knapRec(maxWeight, vals, weights, vals.length - 1));
        System.out.println(knapDP(maxWeight, vals, weights));
        
        vals = new int[]{10, 40, 30, 50};
        weights = new int[]{5, 4, 6, 3};
        maxWeight = 10;
        System.out.println(knapRec(maxWeight, vals, weights, vals.length - 1));
        System.out.println(knapDP(maxWeight, vals, weights));
    }

    final static Map<Integer, Integer> mem = new HashMap<>();
    
    private static int knapRec(final int maxWeight, final int[] vals, final int[] weights, final int index) {

        if (maxWeight == 0 || index < 0) {
            return 0;
        }

        if (weights[index] > maxWeight) {
            return knapRec(maxWeight, vals, weights, index - 1);
        }
        
        Integer result = null;
        if ((result = mem.get(maxWeight)) != null) {
            return result;
        }

        int with = vals[index] + knapRec(maxWeight - weights[index], vals, weights, index - 1);
        int without = knapRec(maxWeight, vals, weights, index - 1);
        result = Math.max(with, without);
        mem.put(maxWeight, result);
        return result;
    }
    
    private static int knapDP(final int maxWeight, final int[] vals, final int[] weights) {
        
        final int[][] dp = new int[vals.length+1][maxWeight+1];
        
        for (int i = 0; i <= maxWeight; i++) {
            dp[0][i] = 0;
        }
        
        for (int v = 1; v <= vals.length; v++) {
            for (int w = 0; w <= maxWeight; w++) {
                if (weights[v-1] > w) {
                    dp[v][w] = dp[v-1][w];
                } else {
                    dp[v][w] = Math.max(vals[v-1] + dp[v-1][w - weights[v-1]], dp[v-1][w]);
                }
            }
        }
        
//        System.out.println(Arrays.deepToString(dp));
        return dp[vals.length][maxWeight];
    }

}
