package org.russell.practice.fb;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackStabilization {

    public static int getMinimumDeflatedDiscCount(int[] R) {
        int result = 0;
        for (int i = R.length-2; i >=0; i--) {
            if (R[i] >= R[i+1]) {
                R[i] = R[i+1] - 1;
                result++;
            }
            if (R[i] <=0 ) {
                return -1;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        assertEquals(3, getMinimumDeflatedDiscCount(new int[]{2, 5, 3, 6, 5}));
        assertEquals(2, getMinimumDeflatedDiscCount(new int[]{100, 100, 100}));
        assertEquals(-1, getMinimumDeflatedDiscCount(new int[]{6, 5, 4, 3}));
    }
}
