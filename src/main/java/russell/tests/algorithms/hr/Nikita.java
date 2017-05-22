package russell.tests.algorithms.hr;

import java.util.HashSet;
import java.util.Set;

/**
 * Nikita just came up with a new array game. The rules are as follows:
 * 
 * Initially, there is an array, , containing integers.
 * 
 * In each move, Nikita must partition the array into non-empty parts such that the sum of the elements in the left partition is equal to
 * the sum of the elements in the right partition. If Nikita can make such a move, she gets point; otherwise, the game ends.
 * 
 * After each successful move, Nikita discards either the left partition or the right partition and continues playing by using the remaining
 * partition as array .
 * 
 * Nikita loves this game and wants your help getting the best score possible. Given , can you find and print the maximum number of points
 * she can score?
 * 
 * https://www.hackerrank.com/challenges/array-splitting
 * 
 */
public class Nikita {

    public static void main(String[] args) {

        final long[][] arrays = new long[4][];
        arrays[0] = new long[] { 3, 3, 3 };
        arrays[1] = new long[] { 2, 2, 2, 2 };
        arrays[2] = new long[] { 4, 1, 0, 1, 1, 0, 1 };
        arrays[3] = new long[] { 0, 0, 0, 0, 0, 0, 0 };

        // int y = 2;
        for (int y = 0; y < arrays.length; y++) {
            final long[] array = arrays[y];
            final Set<Long> sumSet = new HashSet<>();
            long sum = 0;
            boolean allZeroes = true;
            for (int i = 0; i < array.length; i++) {
                sum = sum + array[i];
                sumSet.add(sum);
                if (array[i] != 0) {
                    allZeroes = false;
                }
            }

            int points = array.length - 1;
            if (!allZeroes) {
                points = maxBinaryPartitions(sumSet, 0, sum);
            }
            System.out.println(points);
        }

    }

    /**
     * Max binary partitions where sum(left) == sum(right)
     * 
     * @param sumSet
     * @param min
     * @param max
     * @return
     */
    private static int maxBinaryPartitions(final Set<Long> sumSet, final long min, final long max) {
        if ((max + min) % 2 == 0 && sumSet.contains((max + min) / 2)) {
            int countLeft = maxBinaryPartitions(sumSet, min, (max + min) / 2);
            int countRight = maxBinaryPartitions(sumSet, (max + min) / 2, max);
            return 1 + Math.max(countLeft, countRight);
        } else {
            return 0;
        }
    }
}
