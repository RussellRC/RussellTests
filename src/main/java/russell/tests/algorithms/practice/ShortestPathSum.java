package russell.tests.algorithms.practice;

public class ShortestPathSum {

    public static void main(String[] args) {
        int[][] grid = { { 11, 4, 2, 5, 7 }, { 3, 8, 9, 6, 10 }, { 12, 14, 15, 20, 18 } };

        System.out.println(shortestPathSum(grid));
    }

    public static int shortestPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int[] temp = new int[grid[0].length];
        int minSum = Integer.MAX_VALUE;

        // init temp with min sum of first row
        for (int i = 0; i < grid[0].length; i++) {
            temp[i] = grid[0][i];
            minSum = Math.min(minSum, temp[i]);
        }

        if (grid.length == 1) {
            return minSum;
        }

        int min = minSum;
        for (int y = 1; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                temp[x] = minSum + grid[y][x];
                if (x == 0) {
                    min = temp[x];
                } else {
                    min = Math.min(temp[x], min);
                }
            }
            minSum = min;
        }

        return minSum;
    }

}
