package org.russell.algorithms.practice.fb;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/discuss/interview-question/1641064/facebook-director-of-photography-puzzle-overflow
 */
public class Photography {

    /**
     * Second approach using prefix sum
     */
    public static int getArtisticPhotographCount(int N, String C, int X, int Y) {
        final int[] prefixSumP = new int[N + 1];
        final int[] prefixSumB = new int[N + 1];
        prefixSumP[0] = 0;
        prefixSumB[0] = 0;
        for (int i = 0; i < C.length(); i++) {
            prefixSumP[i+1] = C.charAt(i) == 'P' ? prefixSumP[i] + 1 : prefixSumP[i];
            prefixSumB[i+1] = C.charAt(i) == 'B' ? prefixSumB[i] + 1 : prefixSumB[i];
        }

        int result = 0;
        for (int i = 0; i < C.length(); i++) {
            if(C.charAt(i) == 'A') {
                final int rightStart = Math.min(i + X, N);
                final int rightEnd = Math.min(i + Y + 1, N);
                final int leftStart = Math.max(i - Y, 0);
                final int leftEnd = Math.max(i - X + 1, 0);

                final int leftPs = prefixSumP[leftEnd] - prefixSumP[leftStart];
                final int rightBs = prefixSumB[rightEnd] - prefixSumB[rightStart];
                result += leftPs * rightBs;

                final int rightPs = prefixSumP[rightEnd] - prefixSumP[rightStart];
                final int leftBs = prefixSumB[leftEnd] - prefixSumB[leftStart];
                result += leftBs * rightPs;
            }
        }

        return result;
    }

    /**
     * First approach, does not pass all cases
     */
    public static int getArtisticPhotographCountFirst(int N, String C, int X, int Y) {
        int result = 0;

        for (int i = 0; i < C.length() - 2; i++) {
            char first = C.charAt(i);
            if (first == 'P' || first == 'B') {
                int j = i + 1;
                int distanceToA = Math.abs(i - j);
                while (distanceToA >= X && distanceToA <= Y && j < C.length() - 1) {
                    if (C.charAt(j) == 'A') {
                        int k = j + 1;
                        int distanceFromA = Math.abs(k - j);
                        while (distanceFromA >= X && distanceFromA <= Y && k < C.length()) {
                            char third = C.charAt(k);
                            final boolean isArtistic = (first == 'P' && third == 'B') || (first == 'B' && third == 'P');
                            if (isArtistic) {
                                result++;
                            }
                            k++;
                            distanceFromA = Math.abs(k - j);
                        }
                    }
                    j++;
                    distanceToA = Math.abs(i - j);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(1, getArtisticPhotographCount(5, "APABA", 1, 2));
        assertEquals(0, getArtisticPhotographCount(5, "APABA", 2, 3));
        assertEquals(3, getArtisticPhotographCount(8, ".PBAAP.B", 1, 3));
    }

}
