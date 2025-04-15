package org.russell.practice.fb;

import java.util.Arrays;

/**
 * https://leetcode.com/discuss/interview-question/1376859/facebook-puzzle
 */
public class Cafeteria {

    // My solution
    public static long getMaxAdditionalDinersCountRussell(long N, long K, int M, long[] S) {
        Arrays.sort(S);
        long result = 0;
        if (S[0] != 1) {
            result = result + countAvailable(1, S[0], K, false, true);
        }
        for (int i = 1; i < S.length; i++) {
            result = result + countAvailable(S[i-1], S[i], K, true, true);
        }
        if (S[S.length-1] < N) {
            result = result + countAvailable(S[S.length-1], N, K, true, false);
        }

        return result;
    }

    // My solution
    private static long countAvailable(long s1, long s2, long skip, boolean s1taken, boolean s2taken) {
        long available = 0;
        final long upperBound = s2taken ? (s2 - skip - 1) : s2;
        long i = s1taken ? (s1 + skip + 1) : s1;
        while (i <= upperBound) {
            available++;
            i = i + skip + 1;
        }
        return available;
    }

    // Better solution
    public static long getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
        // Write your code here
        Arrays.sort(S);
        long result = 0;
        long start = 1, end;

        for(int i=0; i<M; ++i) {
            end = S[i] - K - 1;
            result += getCount(start, end, K);
            start = S[i] + K + 1;
        }
        result += getCount(start, N, K);
        return result;
    }

    // Better solution
    private static long getCount(long start, long end, long K) {
        return start > end ? 0 : (end - start)/(K + 1) + 1;
    }


    public static void main(String[] args) {
//        System.out.println(countAvailable(1, 2, 1, false, true));
//        System.out.println(countAvailable(2, 6, 1, true, true));
//        System.out.println(countAvailable(6, 10, 1, true, false));
//
//        System.out.println(countAvailable(1, 2, 2, false, true));
//        System.out.println(countAvailable(2, 6, 2, true, true));
//        System.out.println(countAvailable(6, 10, 2, true, false));

        // 1 2 3 4 5 6 7 8 9 10
        //   x       x
        System.out.println(getMaxAdditionalDinersCount(10L, 1L, 2, new long[]{2L, 6L}));

        // 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
        //           x          x        x
        System.out.println(getMaxAdditionalDinersCount(15L, 2L, 3, new long[]{11L, 6L, 14L}));
    }

}
