package russell.tests.algorithms.domain;

public class DynamicProgramming {

	public static boolean canJump(final int jumps[]) {
		
		int max = 0;
		for (int i = 0; i < jumps.length; i++) {
			if (max <= i && jumps[i] == 0) {
				return false;
			}
			
			max = Math.max(jumps[i] + i, max);
			
			if (max >= jumps.length-1) {
				return true;
			}
		}
		
		return false;
	}
	
	public static int sortestPathSum(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		
		int[] temp = new int[grid[0].length];
		int minSum = Integer.MAX_VALUE;
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
