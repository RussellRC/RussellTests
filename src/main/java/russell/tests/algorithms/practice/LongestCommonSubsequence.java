package russell.tests.algorithms.practice;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {

        String s1 = "ABCDGH";
        String s2 = "AEDFHR";
        StringBuilder result = new StringBuilder();
        lcsRec(s1, s2, s1.length(), s2.length(), result);
        System.out.println(result);
        
        s1 = "AGGTAB";
        s2 = "GXTXAYB";
        result = new StringBuilder();
        lcsRec(s1, s2, s1.length(), s2.length(), result);
        System.out.println(result);
        
        lcs(s1, s2, s1.length(), s2.length());
    }
    
    private static void lcs(final String s1, final String s2, final int m, final int n) {
        final int[][] mem = new int[m+1][n+1];
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    mem[i][j] = 0;
                } else if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    mem[i][j] = mem[i-1][j-1] + 1;
                } else {
                    mem[i][j] = Math.max(mem[i-1][j], mem[i][j-1]);
                }
            }
        }
        
        System.out.println(Arrays.deepToString(mem));
        System.out.println(mem[m][n]);
    }

    private static void lcsRec(final String s1, final String s2, final int length1, final int length2, final StringBuilder result) {
        
        if (length1 == 0 || length2 == 0) {
            return;
        }
        
        if (s1.charAt(length1-1) == s2.charAt(length2-1)) {
            result.insert(0, s1.charAt(length1-1));
            lcsRec(s1, s2, length1-1, length2-1, result);
        } else {
            final StringBuilder sb1 = new StringBuilder(result);
            final StringBuilder sb2 = new StringBuilder(result);
            
            if (length2 > 0) {
                lcsRec(s1, s2, length1, length2-1, sb1);
            }
            if (length1 > 0) {
                lcsRec(s1, s2, length1-1, length2, sb2);
            }
            
            if (sb1.length() > sb2.length()) {
                result.replace(0, result.length(), sb1.toString());
            } else {
                result.replace(0, result.length(), sb2.toString());
            }
        }
    }

}
