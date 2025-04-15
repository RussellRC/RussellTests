package org.russell.practice.unsorted;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * You are given an integer array nums.
 * ou are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 */
public class CanJump {
	public static void main(String[] args) {
		assertTrue(canJump(new int[] { 2, 3, 1, 1, 4 }));
		assertFalse(canJump(new int[] { 3, 2, 1, 0, 4 }));
	}

	public static boolean canJump(final int[] jumps) {

		int max = 0;
		for (int i = 0; i < jumps.length; i++) {
			if (max <= i && jumps[i] == 0) {
				System.out.printf("I can not reach. Max=%d, jumps[i]=0%n", max);
				return false;
			}

			max = Math.max(jumps[i] + i, max);
			System.out.println("Max: " + max);

			if (max >= jumps.length - 1) {
				System.out.printf("I can reach last index %d with max jump %d%n", jumps.length-1, max);
				return true;
			}
		}

		return false;
	}
}
