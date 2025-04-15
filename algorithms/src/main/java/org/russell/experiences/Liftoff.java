package org.russell.experiences;

public class Liftoff {

    // Given an MxN matrix, write code which prints out the diagonals (from upper right to lower left) of the matrix. In this example where M = 4, N = 3:
// [[9, 3, 2],
//  [8, 6, 1],
//  [5, 5, 6],
//  [1, 2, 8]]


// Your code should print out:
// 9
// 3 8
// 2 6 5
// 1 5 1
// 6 2
// 8

    public static void main(String[] args) {
        int[][] array = new int[4][3];
        array[0] = new int[]{9, 3, 2};
        array[1] = new int[]{8, 6, 1};
        array[2] = new int[]{5, 5, 6};
        array[3] = new int[]{1, 2, 8};

        // (y, x)
        // (0, 0) = 9
        // (0, 1) (1, 0)
        // (0, 2) (1, 1) (2, 0)
        // (1, 2) (2, 1) (3, 0)
        // (2, 2) ...
        // (3, 2) ...

        int x = 0;
        int y = 0;
        final int xMax = array[0].length;
        while (x < xMax && y < array.length) {
            printDiagonal(array, y, x);
            System.out.println();
            if (x == xMax-1) {
                y++;
            } else {
                x++;
            }
        }

    }

    static void printDiagonal(int[][] array, int y0, int x0) {
        int y = y0;
        int x = x0;
        while (y < array.length && x >= 0) {
            System.out.print(array[y][x] + " ");
            x--;
            y++;
        }
    }

}
