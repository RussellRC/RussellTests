package org.russell.dsa;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixPracticeTest {

    @Test
    void floodFill() {
        final int[][] img = new int[][] {
                new int[]{1, 0, 1},
                new int[]{1, 0, 0},
                new int[]{0, 0, 1}
        };

        MatrixDFS.floodFill(img, 1, 1, 2);
        System.out.println(Arrays.deepToString(img));
    }

    @Test
    void numberOfIslands() {
        final int[][] grid = new int[][] {
                new int[]{1, 1, 0, 1},
                new int[]{1, 1, 0, 1},
                new int[]{1, 1, 0, 1}
        };
        assertEquals(2, MatrixDFS.numberOfIslands(grid));
    }

    @Test
    void numberOfIslands2() {
        final int[][] grid = new int[][] {
                new int[]{0, 1, 0, 0, 0, 0},
                new int[]{0, 1, 1, 0, 1, 0},
                new int[]{0, 0, 0, 1, 1, 0},
                new int[]{1, 1, 0, 0, 0, 1},
                new int[]{1, 1, 0, 0, 0, 1},
        };
        assertEquals(4, MatrixDFS.numberOfIslands(grid));
    }
}
