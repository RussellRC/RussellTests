package org.russell.practice.unsorted;

import org.russell.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubArraysWithSum {

    public static List<Pair<Integer, Integer>> allSubArraysWithZeroSum(final int[] arr) {
        final List<Pair<Integer, Integer>> result = new ArrayList<>();
        final Map<Integer, List<Integer>> indexesBySum = new HashMap<>();
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];

            // if sum is 0, we found a subarray starting from index 0 and ending at index i
            if (sum == 0) {
                result.add(Pair.of(0, i));
            }

            final List<Integer> indexes = indexesBySum.compute(sum, (k, v) -> (v == null) ? new ArrayList<>() : v);
            if (!indexes.isEmpty()) {
                // If list is not empty we've reached the same sum twice,
                // which means that between each index and `i` the sum is 0
                for (final Integer index : indexes) {
                    result.add(Pair.of(index + 1, i));
                }
            }
            indexes.add(i);
        }

        return result;
    }

    public static void main(String[] args) {
        final int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};
        // 6 9 8 5 9
        System.out.println(allSubArraysWithZeroSum(arr));
    }


}
