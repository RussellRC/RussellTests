package org.russell.algorithms.practice.fb;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotaryLock {

    /**
     * 17 out of 35 correct
     */
    public static long getMinCodeEntryTime(int N, int[] C) {
        int current = 1;
        int seconds = 0;

        for (int nextNum : C) {
            if (nextNum != current) {
                int directionA = Math.abs(nextNum - current);
                int directionB = N - directionA;
                seconds = seconds + Math.min(directionA, directionB);
                current = nextNum;
            }
        }
        return seconds;
    }

    public static void main(String[] args) {
        assertEquals(2, getMinCodeEntryTime(3, new int[]{1, 2, 3}));
        assertEquals(11, getMinCodeEntryTime(10, new int[]{9, 4, 4, 8}));
    }
}
