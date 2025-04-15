package org.russell.practice.lc;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/course-schedule
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }

        // build adjacency list
        final Map<Integer, Set<Integer>> prerequisitesMap = new HashMap<>();
        for (int[] edge : prerequisites) {
            prerequisitesMap.computeIfAbsent(edge[0], k -> new LinkedHashSet<>()).add(edge[1]);
            prerequisitesMap.computeIfAbsent(edge[1], k -> new LinkedHashSet<>());
        }
        if (prerequisitesMap.isEmpty()) {
            return true;
        }

        final Set<Integer> visited = new HashSet<>();
        final Set<Integer> canComplete = new HashSet<>();
        for (final Integer course : prerequisitesMap.keySet()) {
            if (!dfs(course, visited, canComplete, prerequisitesMap)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(final Integer current, final Set<Integer> visited, Set<Integer> canComplete, final Map<Integer, Set<Integer>> prerequisitesMap) {
        if (visited.contains(current)) {
            // found cycle
            return false;
        }
        if (prerequisitesMap.get(current).isEmpty()) {
            canComplete.add(current);
            return true;
        }

        visited.add(current);

        // call recursive dfs for each prerequisite
        for (final Integer prereq : prerequisitesMap.get(current)) {
            boolean canFinishPrereq = canComplete.contains(prereq) || dfs(prereq, visited, canComplete, prerequisitesMap);
            if (!canFinishPrereq) {
                return false;
            }
        }
        visited.remove(current);
        canComplete.add(current);
        return true;
    }


    @Test
    public void test1() {
        int[][] courses = new int[][]{{1,0}};
        assertTrue(canFinish(2, courses));
    }

    @Test
    public void test2() {
        int[][] courses = new int[][]{{0,1}, {0,2}, {1,3}, {1,4}, {3,4}};
        assertTrue(canFinish(5, courses));
    }

    @Test
    public void test3() {
        int[][] courses = new int[][]{{0,1}, {1,2}, {2,0}};
        assertFalse(canFinish(3, courses));
    }

    @Test
    public void test5() {
        int[][] courses = new int[][]{{5,5}};
        assertFalse(canFinish(3, courses));
    }

    @Test
    public void test6() {
        int[][] courses = new int[][]{{0,10}, {3,18}, {5,5}, {6,11}, {11,14}, {13,1}, {15,1}, {17,4}};
        assertFalse(canFinish(20, courses));
    }

}
