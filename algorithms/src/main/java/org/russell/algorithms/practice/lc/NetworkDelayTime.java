package org.russell.algorithms.practice.lc;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/network-delay-time/
 */
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        // build adjacency list
        final Map<Integer, Set<Edge>> adjacents = new LinkedHashMap<>();
        IntStream.range(1, n+1).forEach(node -> adjacents.put(node, new LinkedHashSet<>()));
        for (final int[] edge : times) {
            if (edge != null && edge.length > 0) {
                adjacents.get(edge[0]).add(new Edge(edge[0], edge[1], edge[2]));
            }
        }

        // Dijkstra
        // time complexity = O(E * logV)
        final Set<Integer> visitedNodes = new LinkedHashSet<>();
        final PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparing(edge -> edge.weight));
        minHeap.add(new Edge(k, 0));
        int time = 0;
        while (!minHeap.isEmpty()) {
            final Edge edge = minHeap.poll();
            if (visitedNodes.contains(edge.source)) {
                continue;
            }
            visitedNodes.add(edge.source);
            time = Math.max(time, edge.weight);
            for (final Edge adjacent : adjacents.get(edge.source)) {
                if (!visitedNodes.contains(adjacent.target)) {
                    minHeap.add(new Edge(adjacent.target, edge.weight + adjacent.weight));
                }
            }
        }

        return visitedNodes.size() == n ? time : -1;
    }

    private static final class Edge {
        final int source;
        final Integer target;
        final int weight;

        // Represents an outgoing edge from a Node
        private Edge(final int source, final int weight) {
            this.source = source;
            this.target = null;
            this.weight = weight;
        }

        private Edge(final int source, final int target, final int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }

    @Test
    public void test1() {
        final int[][] edges = new int[][]{{2,1,1}, {2,3,1}, {3,4,1}};
        assertEquals(2, networkDelayTime(edges, 4, 2));
    }

    @Test
    public void test2() {
        final int[][] edges = new int[][]{{1,2,1}};
        assertEquals(1, networkDelayTime(edges, 2, 1));
    }

    @Test
    public void test3() {
        final int[][] edges = new int[][]{{1,2,1}};
        assertEquals(-1, networkDelayTime(edges, 2, 2));
    }
}
