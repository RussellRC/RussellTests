package russell.tests.algorithms.hr;

public class EditDistance {

    public static void main(String[] args) {
        String str1 = "sunday";
        String str2 = "saturday";
        System.out.println( editDistance( str1 , str2 , str1.length(), str2.length()) );
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
        final int[][] dp = new int[m+1][n+1];
        
        for (int y = 0; y<=m; y++) {
            for (int x = 0; x<=n; x++) {
                if (y == 0) {
                    // If s1 is empty, insert all characters
                    dp[y][x] = x;
                } else if (x == 0) {
                    // If s2 is empty, remove all characters
                    dp[y][x] = y;
                } else if (s1.charAt(y-1) == s2.charAt(x-1)) {
                    // last characters are the same
                    dp[y][x] = dp[y-1][x-1];
                } else {
                    dp[y][x] = 1 + min(dp[y][x-1], dp[y-1][x-1], dp[y-1][x-1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    
    
}
