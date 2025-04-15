package org.russell.practice.lc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // build dependency list
        final Map<Integer, Set<Integer>> dependencies = new LinkedHashMap<>();
        IntStream.range(0, numCourses).forEach(course -> dependencies.put(course, new LinkedHashSet<>()));
        for (final int[] edge : prerequisites) {
            if (edge != null && edge.length > 0) {
                dependencies.get(edge[0]).add(edge[1]);
            }
        }

        if (dependencies.isEmpty()) {
            if (numCourses > 0) {
                return IntStream.range(0, numCourses).toArray();
            } else {
                return new int[]{};
            }
        }

        final List<Integer> result = new ArrayList<>();
        final Set<Integer> visited = new LinkedHashSet<>();
        final Set<Integer> completed = new LinkedHashSet<>();

        for (final Integer course : dependencies.keySet()) {
            if (!topologicalSort(course, visited, completed, dependencies, result)) {
                return new int[]{};
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean topologicalSort(final Integer course, final Set<Integer> visited, final Set<Integer> completed, final Map<Integer, Set<Integer>> dependencies, final List<Integer> result) {
        if (completed.contains(course)) {
            return true;
        }
        if (visited.contains(course)) {
            // found cycle
            return false;
        }

        visited.add(course);

        for (final Integer dependency : dependencies.get(course)) {
            boolean depCompleted = completed.contains(dependency) || topologicalSort(dependency, visited, completed, dependencies, result);
            if (!depCompleted) {
                return false;
            }
        }

        result.add(course);
        completed.add(course);
        visited.remove(course);
        return true;
    }

    @Test
    public void test1() {
        int[][] courses = new int[][]{{1,0}};
        assertArrayEquals(new int[]{0, 1}, findOrder(2, courses));
    }

    @Test
    public void test2() {
        int[][] courses = new int[][]{{1,0}, {2, 0}, {3,1}, {3,2}};
        assertArrayEquals(new int[]{0, 1, 2, 3}, findOrder(4, courses)); // Another correct ordering is [0,2,1,3]
    }

    @Test
    public void test3() {
        int[][] courses = new int[][]{};
        assertArrayEquals(new int[]{0}, findOrder(1, courses));
    }

    @Test
    public void test4() {
        int[][] courses = new int[][]{{1,0}};
        assertArrayEquals(new int[]{0, 1, 2}, findOrder(3, courses));
        // other correct orderings: [2, 0, 1], [0, 2, 1]
    }

}
