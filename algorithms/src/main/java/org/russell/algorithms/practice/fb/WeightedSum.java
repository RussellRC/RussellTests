package org.russell.algorithms.practice.fb;

import java.util.ArrayList;
import java.util.List;

/*
 *
 Given a nested list of integers, return the sum of all integers in the list weighted by their depth. Each element is either an integer, or a list -- whose elements may also be integers or other lists.

For example:
Input: [8, 4, [5, [9], 3], 6]
Output: 8 + 4 + 2*(5 + 3*(9) + 3) + 6 ==> 88

Can you implement the method which takes in a nested list of integers, return the sum of all integers in the list weighted by their depth.

 */
public class WeightedSum {

    public static void main(String[] args) {
        final List<Object> list = new ArrayList<>();
        list.add(8);
        list.add(4);
        list.add(List.of(5, List.of(9), 3));
        list.add(6);
        System.out.println(weightedSum(list));
    }

    public static long weightedSum(final List<Object> list) {
        return weightedSum(list, 1);
    }

    // 12 + weightedSum(in, 2)
    //    5 + weightedSum(9, 3)
    //        9*3 = 27
    //    (5 + 27 + 3)*2 = 70
    // 12 + 70 + 6
    // 88
    public static long weightedSum(final List<Object> list, final int depth) {
        long sum = 0;
        for (final Object obj : list) {
            if (obj instanceof Integer) {
                sum += (Integer) obj;
            } else if (obj instanceof List) {
                sum += weightedSum((List<Object>) obj, depth+1);
            }
        }
        return sum*depth;
    }

}