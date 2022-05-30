package org.russell.algorithms.practice.lc;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/redundant-connection/
 */
public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        final UnionFind uf = new UnionFind(edges);
        for (final int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge;
            }
        }
        // should never get here since a cycle is guaranteed
        return null;
    }

    private static final class UnionFind {
        // Map from element to its root
        final Map<Integer, Integer> roots;
        // Map from element to rank
        final Map<Integer, Integer> ranks;

        private UnionFind(final int[][] edges) {
            roots = new HashMap<>(edges.length);
            ranks = new HashMap<>(edges.length);
            IntStream.range(1, edges.length+1)
                    .boxed()
                    .forEach(node -> {
                        roots.put(node, node);
                        ranks.put(node, 1);
                    });
        }

        private int find(int node) {
            int root = roots.get(node);
            while (!Objects.equals(root, roots.get(root))) {
                // path compression
                roots.put(root, roots.get(roots.get(root)));
                root = roots.get(root);
            }
            return root;
        }

        /** returns false if can not do union, i.e. cycle found */
        private boolean union(int node1, int node2) {
            final int root1 = find(node1);
            final int root2 = find(node2);

            if (Objects.equals(root1, root2)) {
                // cycle found, this is the redundant connection
                return false;
            }

            final int newRank = ranks.get(root1) + ranks.get(root2);
            if (ranks.get(root1) > ranks.get(root2)) {
                roots.put(root2, root1);
                ranks.put(root1, newRank);
            } else {
                roots.put(root1, root2);
                ranks.put(root2, newRank);
            }
            return true;
        }
    }

    @Test
    public void test1() {
        final int[][] edges = new int[][]{{1,2}, {1,3}, {2,3}};
        assertArrayEquals(new int[]{2, 3}, findRedundantConnection(edges));
    }

    @Test
    public void test2() {
        final int[][] edges = new int[][]{{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        assertArrayEquals(new int[]{1, 4}, findRedundantConnection(edges));
    }
}
