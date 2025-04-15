package org.russell.dsa;

import java.util.Arrays;

public class Greedy {

    /** https://www.hellointerview.com/learn/code/greedy/overview */
    public int cookies(final int[] greeds, final int[] cookies) {
        Arrays.sort(greeds);
        Arrays.sort(cookies);

        int count = 0;
        int g = 0, c = 0;
        while (g < greeds.length && c < cookies.length) {
            if (greeds[g] <= cookies[c]) {
                c++;
                count++;
            }
            g++;
        }
        return count;
    }

    /** https://www.hellointerview.com/learn/code/greedy/best-time-to-buy-and-sell-stock */
    public int maxProfit(final int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    /** https://www.hellointerview.com/learn/code/greedy/gas-station */
    public int canCompleteCircuit(final int[] gas, final int[] cost) {
        final int totalGas = Arrays.stream(gas).sum();
        final int totalCost = Arrays.stream(cost).sum();
        if (totalGas < totalCost) {
            return -1;
        }

        int start = 0, tank = 0;
        for (int i = 0; i < gas.length; i++) {
            int estimate = tank + gas[i] - cost[i];
            if (estimate < 0) {
                start = i + 1;
                tank = 0;
            } else {
                tank = estimate;
            }
        }

        return start;
    }

    /** https://www.hellointerview.com/learn/code/greedy/jump-game */
    public boolean canJump(final int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }
}
