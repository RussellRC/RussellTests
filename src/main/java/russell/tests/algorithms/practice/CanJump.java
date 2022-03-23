package russell.tests.algorithms.practice;

import org.junit.Assert;

public class CanJump {
	public static void main(String[] args) {
		Assert.assertTrue(canJump(new int[] { 2, 3, 1, 1, 4 }));
		Assert.assertFalse(canJump(new int[] { 3, 2, 1, 0, 4 }));
	}

	public static boolean canJump(final int jumps[]) {

		int max = 0;
		for (int i = 0; i < jumps.length; i++) {
			if (max <= i && jumps[i] == 0) {
				return false;
			}

			max = Math.max(jumps[i] + i, max);

			if (max >= jumps.length - 1) {
				return true;
			}
		}

		return false;
	}
}
