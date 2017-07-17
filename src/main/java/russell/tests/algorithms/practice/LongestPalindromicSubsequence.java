package russell.tests.algorithms.practice;

import java.util.Arrays;

/**
 * Find length of longest palindromic subsequence
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        String s = "BBABCBCAB";
//        
//        System.out.println(lpsRec(s, 0, s.length()-1));
        System.out.println(lpsDP(s));
        

    }

    private static int lpsRec(final String s, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return 1;
        }
        
        if (leftIndex+1 == rightIndex && s.charAt(leftIndex) == s.charAt(rightIndex)) {
            return 2;
        }
        
        if (s.charAt(leftIndex) == s.charAt(rightIndex)) {
            return 2 + lpsRec(s, leftIndex + 1, rightIndex - 1);
        }
        
        int leftRemains = lpsRec(s, leftIndex, rightIndex - 1);
        int rightRemains = lpsRec(s, leftIndex+1, rightIndex);
        return Math.max(leftRemains, rightRemains);
    }
    
    private static int lpsDP(final String str) {
        
        final int[][] dp = new int[str.length()][str.length()];
        
        for (int i = 0; i < str.length(); i++) {
            dp[i][i] = 1;
        }
        
        for (int i = str.length()-1, right = 1; i >= 0; i--, right++) {
            int y = 0;
            int x = right;
            while (x <= str.length()-1) {
                
                if (y+1 == x && str.charAt(y) == str.charAt(x)) {
                    dp[y][x] = 2;
                }
                else if (str.charAt(y) == str.charAt(x)) {
                    dp[y][x] = dp[y+1][x-1] + 2;
                }
                else {
                    dp[y][x] = Math.max(dp[y][x-1], dp[y+1][x]);
                }
                
                y++;
                x++;
            }
        }
     
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[0][str.length()-1];
        
    }
    
}
