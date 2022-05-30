package org.russell.algorithms.practice.gfg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RotateMatrix {

    @Test
    void test1() {
        final int[][] matrix = new int[3][3];
        matrix[0] = new int[]{1, 2, 3};
        matrix[1] = new int[]{4, 5, 6};
        matrix[2] = new int[]{7, 8, 9};

        final int[][] expected = new int[3][3];
        expected[0] = new int[]{3, 6, 9};
        expected[1] = new int[]{2, 5, 8};
        expected[2] = new int[]{1, 4, 7};

        rotate90ToLeft(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void test2() {
        final int[][] matrix = new int[4][4];
        matrix[0] = new int[]{1, 2, 3, 4};
        matrix[1] = new int[]{5, 6, 7, 8};
        matrix[2] = new int[]{9, 10, 11, 12};
        matrix[3] = new int[]{13, 14, 15, 16};

        final int[][] expected = new int[4][4];
        expected[0] = new int[]{4, 8, 12, 16};
        expected[1] = new int[]{3, 7, 11, 15};
        expected[2] = new int[]{2, 6, 10, 14};
        expected[3] = new int[]{1, 5, 9, 13};

        rotate90ToLeft(matrix);
        assertArrayEquals(expected, matrix);
    }

    static void rotate90ToLeft(final int[][] matrix) {
        final int size = matrix.length;
        for (int y = 0; y < size/2; y++) {
            for (int x = y; x < size-1-y; x++) {
                final int temp = matrix[y][x];

                // from right to top
                matrix[y][x] = matrix[x][size-1-y];

                // from bottom to right
                matrix[x][size-1-y] = matrix[size-1-y][size-1-x];

                // from left to bottom
                matrix[size-1-y][size-1-x] = matrix[size-1-x][y];

                // from top to left
                matrix[size-1-x][y] = temp;
            }
        }
    }
}
