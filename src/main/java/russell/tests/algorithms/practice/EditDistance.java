package russell.tests.algorithms.practice;


/**
 * 
 */
public class EditDistance {

    public static void main(String[] args) {
        String str1 = "sunday";
        String str2 = "saturday";
        System.out.println(editDistance(str1, str2, str1.length(), str2.length()));

        System.out.println(editDistanceRec(str1, str2, str1.length()-1, str2.length()-1));
    }

    public static int min(int x, int y, int z) {
        int min = x;
        if (y < min) {
            min = y;
        }
        if (z < min) {
            min = z;
        }
        return min;
    }

    public static int editDistance(String s1, String s2, int m, int n) {
        final int[][] dp = new int[m + 1][n + 1];

        for (int y = 0; y <= m; y++) {
            for (int x = 0; x <= n; x++) {
                if (y == 0) {
                    // If s1 is empty, insert all characters
                    dp[y][x] = x;
                } else if (x == 0) {
                    // If s2 is empty, remove all characters
                    dp[y][x] = y;
                } else if (s1.charAt(y - 1) == s2.charAt(x - 1)) {
                    // last characters are the same
                    dp[y][x] = dp[y - 1][x - 1];
                } else {
                    dp[y][x] = 1 + min(dp[y][x - 1], dp[y - 1][x], dp[y - 1][x - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static int editDistanceRec(String s1, String s2, int n, int m) {
        if (n < 0) {
            // m and n are indexes, so length is index+1
            return m+1;
        }
        if (m < 0) {
            return n+1;
        }

        if (s1.charAt(n) == s2.charAt(m)) {
            return editDistanceRec(s1, s2, n - 1, m - 1);
        }

        int d1 = editDistanceRec(s1, s2, n - 1, m);
        int d2 = editDistanceRec(s1, s2, n, m - 1);
        int d3 = editDistanceRec(s1, s2, n - 1, m - 1);
        return 1 + Math.min(d1, Math.min(d2, d3));
    }

}
