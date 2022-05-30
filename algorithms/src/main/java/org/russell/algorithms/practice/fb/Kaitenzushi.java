package org.russell.algorithms.practice.fb;

import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Kaitenzushi {

    public static int getMaximumEatenDishCount(int N, int[] D, int K) {
        final LinkedHashSet<Integer> previous = new LinkedHashSet<>();
        int result = 0;

        for (int dish : D) {
            if (previous.contains(dish)) {
                continue;
            }
            result++;
            if (previous.size() == K) {
                final Integer oldestDish = previous.iterator().next();
                previous.remove(oldestDish);
            }
            previous.add(dish);
        }

        return result;
    }

    public static void main(String[] args) {
        assertEquals(5, getMaximumEatenDishCount(6, new int[]{1, 2, 3, 3, 2, 1}, 1));
        assertEquals(4, getMaximumEatenDishCount(6, new int[]{1, 2, 3, 3, 2, 1}, 2));
        assertEquals(2, getMaximumEatenDishCount(7, new int[]{1, 2, 1, 2, 1, 2, 1}, 2));
    }

}
