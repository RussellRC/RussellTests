package org.russell.practice.unsorted;

public class PotsOfGold {

    public static void main(String[] args) {
        
        int[] pots = {8, 15, 3, 7};
        System.out.println(potsOfGold(pots, 0, pots.length-1));
        
        System.out.println(potsDP(pots));
    }

    private static int potsOfGold(int[] pots, int start, int end) {
        
        if (start == end) {
            return pots[start];
        }
        if (start == end-1) {
            return Math.max(pots[start], pots[end]);
        }
        
        // X picks first, then it gets the minimum of Y picking either first or last
        int pickFirst = pots[start] + Math.min(potsOfGold(pots, start+2, end), potsOfGold(pots, start+1, end-1));
        
        // X picks last, then it gets the minimum of Y picking either first or last
        int pickLast = pots[end] + Math.min(potsOfGold(pots, start+1, end-1), potsOfGold(pots, start, end-2));
        return Math.max(pickFirst, pickLast);        
    }
    
    static class Pair {
        
        Pair(){}
        Pair(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
        
        int p1;
        int p2;
        @Override
        public String toString() {
            return String.format("(%d, %d)", p1, p2);
        }
    }
    
    private static int potsDP(int[] pots) {
        
        final Pair[][] dp = new Pair[pots.length][pots.length];
        
        // initialize main diagonal with (v,0),
        // since there is only 1 choice and Player 1 gets it
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = new Pair(pots[i], 0);
        }
        
        for (int i = 1; i < dp.length; i++) {
            for (int y = 0, x = i; x < dp[y].length; y++, x++) {
                
                dp[y][x] = new Pair(y, x);
                
                int pickFirst = pots[y] + dp[y+1][x].p2;
                int pickLast = pots[x] + dp[y][x-1].p2;
                dp[y][x].p1 = Math.max(pickFirst, pickLast);
                
                // if Player X picks first element, Player Y gets first pick of next value
                // else Player Y gets first pick of previous value
                dp[y][x].p2 = dp[y+1][x].p1;
                if (dp[y][x].p1 == pickLast) {
                    dp[y][x].p2 = dp[y][x-1].p1;
                }
            }
        }
        
//        System.out.println(Arrays.deepToString(dp));
        
        return dp[0][dp.length-1].p1;
    }
}
