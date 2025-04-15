package org.russell.practice.lc;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinCostToConnectAllPoints {

    public int minCostConnectPoints(int[][] points) {
        // build adjacency list
        final Map<Point, Set<Edge>> adjacents = new LinkedHashMap<>();
        for (int i = 0; i < points.length; i++) {
            final Point source = new Point(points[i][0], points[i][1]);
            adjacents.computeIfAbsent(source, k -> new LinkedHashSet<>());

            for (int j = i+1; j < points.length; j++) {
                final Point target = new Point(points[j][0], points[j][1]);
                final int distance = Math.abs(source.x - target.x) + Math.abs(source.y - target.y);
                adjacents.get(source).add(new Edge(target, distance));
                adjacents.computeIfAbsent(target, k -> new LinkedHashSet<>()).add(new Edge(source, distance));
            }
        }

        // Prim's Algorithm
        int result = 0;
        final Set<Point> visited = new HashSet<>();
        final Point firstPoint = adjacents.keySet().iterator().next();
        final PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparing(edge -> edge.cost));
        minHeap.add(new Edge(firstPoint, 0));

        while (visited.size() < points.length) {
            final Edge edge = minHeap.poll();
            if (visited.contains(edge.target)) {
                continue;
            }
            visited.add(edge.target);
            result = result + edge.cost;
            final Set<Edge> pointAdjacents = adjacents.get(edge.target);
            for (final Edge adjacent : pointAdjacents) {
                if (!visited.contains(adjacent.target)) {
                    minHeap.add(adjacent);
                }
            }
        }

        return result;
    }

    /** Represents a Point (x,y) */
    private static final class Point {
        final int x;
        final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    /** Represents an outgoing Edge to a target */
    private static final class Edge {
        final Point target;
        final int cost;

        private Edge(final Point target, final int cost) {
            this.target = target;
            this.cost = cost;
        }
    }

    @Test
    public void test1() {
        final int[][] points = new int[][]{{0,0}, {2,2}, {3,10}, {5,2}, {7,0}};
        assertEquals(20, minCostConnectPoints(points));
    }

    @Test
    public void test2() {
        final int[][] points = new int[][]{{3,12}, {-2,5}, {-4,1}};
        assertEquals(18, minCostConnectPoints(points));
    }

    @Test
    public void test3() {
        final int[][] points = new int[][]{{0,0}, {1,1}, {1,0}, {-1,1}};
        assertEquals(4, minCostConnectPoints(points));
    }

    @Test
    public void test4() {
        final int[][] points = new int[][]{{0,0}};
        assertEquals(0, minCostConnectPoints(points));
    }
}
