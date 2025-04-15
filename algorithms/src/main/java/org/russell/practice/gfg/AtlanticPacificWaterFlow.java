package org.russell.practice.gfg;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import javax.annotation.Nullable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.geeksforgeeks.org/atlantic-pacific-water-flow/
 */
public class AtlanticPacificWaterFlow {

    @Test
    void test1() {
        final int[][] matrix = new int[5][5];
        matrix[0] = new int[]{1, 2, 2, 3, 5};
        matrix[1] = new int[]{3, 2, 3, 4, 4};
        matrix[2] = new int[]{2, 4, 5, 3, 1};
        matrix[3] = new int[]{6, 7, 1, 4, 5};
        matrix[4] = new int[]{5, 1, 1, 2, 4};

        final Set<Node> solution = findSolution(matrix);
        final Set<Node> expected = Set.of(Node.of(0,4), Node.of(1,3), Node.of(1,4), Node.of(2,2), Node.of(3,0), Node.of(3,1), Node.of(4,0));
        assertEquals(expected, solution);
    }

    @Test
    void test2() {
        final int[][] matrix = new int[2][2];
        matrix[0] = new int[]{2, 2};
        matrix[1] = new int[]{2, 2};

        final Set<Node> solution = findSolution(matrix);
        final Set<Node> expected = Set.of(Node.of(0,0), Node.of(0,1), Node.of(1,0), Node.of(1,1));
        assertEquals(expected, solution);
    }

    static Set<Node> findSolution(final int[][] matrix) {
        final Set<Node> originLeftTop = new LinkedHashSet<>();
        // Add left border
        for (int y = 0; y < matrix.length; y++) {
            originLeftTop.add(Node.of(y, 0, matrix[y][0]));
        }
        // Add top border
        for (int x = 0; x < matrix[0].length; x++) {
            originLeftTop.add(Node.of(0, x, matrix[0][x]));
        }

            final Set<Node> originRightBottom = new LinkedHashSet<>();
        // Add right border
        final int rightX = matrix[0].length-1;
        for (int y = 0; y < matrix.length; y++) {
            originRightBottom.add(Node.of(y, rightX, matrix[y][rightX]));
        }
        // Add bottom border
        final int bottomY = matrix.length-1;
        for (int x = 0; x < matrix[0].length; x++) {
            originRightBottom.add(Node.of(bottomY, x, matrix[bottomY][x]));
        }

        final Set<Node> flowingPacific = findFlowingCellsBFS(matrix, originLeftTop);
//        System.out.println(flowingPacific);
        final Set<Node> flowingAtlantic = findFlowingCellsBFS(matrix, originRightBottom);
//        System.out.println(flowingAtlantic);
        return Sets.intersection(flowingPacific, flowingAtlantic);
    }

    private static Set<Node> findFlowingCellsBFS(final int[][] matrix, final Set<Node> origins) {
        final Set<Node> visited = new LinkedHashSet<>();
        final Queue<Node> pending = new LinkedList<>(origins);

        while (!pending.isEmpty()) {
            final Node current = pending.poll();
            if (!visited.contains(current)) {
                visited.add(current);
                final Set<Node> adjacent = getFlowingAdjacents(matrix, current);
                pending.addAll(adjacent);
            }
        }
        return visited;
    }

    /**
     * Return the NSEW adjacents that are equal or higher than the current node
     */
    private static Set<Node> getFlowingAdjacents(final int[][] matrix, final Node node) {
        final Set<Node> adjacents = new LinkedHashSet<>();
        final int[] adjacentsX = {node.x-1, node.x+1};
        final int[] adjacentsY = {node.y-1, node.y+1};
        // NS
        for (int y : adjacentsY) {
            if (y >= 0 && y < matrix.length && node.x >= 0 && node.x < matrix[0].length) {
                if (matrix[y][node.x] >= node.value) {
                    adjacents.add(Node.of(y, node.x, matrix[y][node.x]));
                }
            }
        }
        // EW
        for (int x : adjacentsX) {
            if (x >= 0 && x < matrix[0].length && node.y >=0 && node.y < matrix.length) {
                if (matrix[node.y][x] >= node.value) {
                    adjacents.add(Node.of(node.y, x, matrix[node.y][x]));
                }
            }
        }

        return adjacents;
    }

    private final static class Node {
        final int y;
        final int x;
        final Integer value;

        private Node(final int y, final int x, @Nullable final Integer value) {
            this.y = y;
            this.x = x;
            this.value = value;
        }

        static Node of(final int y, final int x, final int value) {
            return new Node(y, x, value);
        }

        static Node of(final int y, final int x) {
            return new Node(y, x, null);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return String.format("(%d,%d,v=%d)", y, x, value);
        }
    }
}
