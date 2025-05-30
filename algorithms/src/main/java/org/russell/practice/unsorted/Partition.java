package org.russell.practice.unsorted;

import java.util.ArrayList;
import java.util.List;

public class Partition {

    public static void partitionStdout(int n) {
        partition(n, n, "");
    }

    private static void partition(int n, int max, String prefix) {
        if (n == 0) {
            System.out.println(prefix);
            return;
        }

        for (int i = Math.min(max, n); i >= 1; i--) {
            partition(n-i, i, prefix + " " + i);
        }
    }

	/**
	 * All partitions of given number
	 */
	public static List<List<Integer>> allPartitions(final int n) {
		final List<List<Integer>> result = new ArrayList<>();
        final List<Integer> partial = new ArrayList<>();
        partition(n, partial, result);
        return result;
    }
	
    private static void partition(int n, List<Integer> partial, List<List<Integer>> partitions) {
        if (n == 0) {
            // Complete solution is held in 'partial' --> add it to list of solutions
            partitions.add(new ArrayList<>(partial));
        } else {
            // Iterate through all numbers i less than n.
            for (int i = n; i > 0; i--) {
                // Avoid duplicate solutions by ensuring that the partial array is always non-increasing
                if (partial.isEmpty() || partial.getLast() >= i) {
                    partial.add(i);
                    partition(n - i, partial, partitions);
                    partial.removeLast();
                }
            }
        }
    }
	
    /**
     * Partitions with given size for a number
     * @param n
     * @param size
     * @return
     */
	public static List<List<Integer>> partitionsWithSize(int n, int size) {
        final List<List<Integer>> result = new ArrayList<>();
        final List<Integer> partial = new ArrayList<>();
        partition(n, size, partial, result);
        return result;
    }
	
	private static void partition(int n, int partitionSize, List<Integer> partial, List<List<Integer>> result) {
        if (partial.size() > partitionSize) {
            return;
        }
        if (n == 0 && partial.size() == partitionSize) {
            // Complete solution is held in 'partial' --> add it to list of solutions
            result.add(new ArrayList<>(partial));
        } else {
            // Iterate through all numbers i less than n.
            // Avoid duplicate solutions by ensuring that the partial array is always non-increasing
            for (int i = n; i > 0; i--) {
                if (partial.isEmpty() || partial.get(partial.size() - 1) >= i) {
                    partial.add(i);
                    partition(n - i, partitionSize, partial, result);
                    partial.remove(partial.size() - 1);
                }
            }
        }
    }


}
