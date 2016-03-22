package russell.tests.algorithms.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DynamicProgrammingTest {

	@Test
	public void testCanJump() {
		Assert.assertTrue(DynamicProgramming.canJump(new int[] { 2, 3, 1, 1, 4 }));
		Assert.assertFalse(DynamicProgramming.canJump(new int[] { 3, 2, 1, 0, 4 }));
	}

	@Test
	public void testShortestPathSum() {

		int[][] grid = { { 11, 4, 2, 5, 7 }, 
				{ 3, 8, 9, 6, 10 }, 
				{ 12, 14, 15, 20, 18 } };
		
		System.out.println(DynamicProgramming.sortestPathSum(grid));

	}

}
