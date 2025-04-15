package org.russell.practice.lc;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 */
public class ReorderRoutes {

    public int minReorder(int n, int[][] connections) {
        // create adjacency maps: undirected and directed
        final Map<Integer, Set<Integer>> adjecentsMap = new LinkedHashMap<>();
        final Map<Integer, Set<Integer>> directed = new LinkedHashMap<>();
        IntStream.range(0, n).forEach(city -> {
            adjecentsMap.put(city, new LinkedHashSet<>());
            directed.put(city, new LinkedHashSet<>());
        });
        for (final int[] edge : connections) {
            adjecentsMap.get(edge[0]).add(edge[1]);
            adjecentsMap.get(edge[1]).add(edge[0]);
            directed.get(edge[0]).add(edge[1]);
        }

        int changes = 0;

        // BFS starting from Node-0
        final Set<Integer> visited = new HashSet<>();
        final Queue<Integer> pending = new LinkedList<>();
        pending.add(0);

        while (!pending.isEmpty()) {
            final Integer current = pending.poll();
            if (!visited.contains(current)) {
                visited.add(current);

                // check if neighbors can reach Node-0
                for (final Integer adjacent : adjecentsMap.get(current)) {
                    if (!visited.contains(adjacent)) {
                        if (!directed.get(adjacent).contains(current)) {
                            // need to change direction: from adj to current current
                            changes = changes + 1;
                        }
                        pending.add(adjacent);
                    }
                }
            }
        }

        return changes;
    }

    @Test
    public void test1() {
        final int n = 6;
        final int[][] connections = new int[][]{{0,1}, {1,3}, {2,3}, {4,0}, {4,5}};
        assertEquals(3, minReorder(n, connections));
    }

    @Test
    public void test2() {
        final int n = 5;
        final int[][] connections = new int[][]{{1,0}, {1,2}, {3,2}, {3,4}};
        assertEquals(2, minReorder(n, connections));
    }

    @Test
    public void test3() {
        final int n = 3;
        final int[][] connections = new int[][]{{1,0}, {2,0}};
        assertEquals(0, minReorder(n, connections));
    }
}
