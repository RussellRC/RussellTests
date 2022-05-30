package org.russell.algorithms.practice.lc;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3845/
 * https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3846/
 */
public class NumberOfProvinces {

    @Test
    public void test1() {
        final int[][] m = new int[3][3];
        m[0] = new int[]{1, 1, 0};
        m[1] = new int[]{1, 1, 0};
        m[2] = new int[]{0, 0, 1};
        assertEquals(2, findProvinces(m));
    }

    @Test
    public void test2() {
        final int[][] m = new int[3][3];
        m[0] = new int[]{1, 0, 0};
        m[1] = new int[]{0, 1, 0};
        m[2] = new int[]{0, 0, 1};
        assertEquals(3, findProvinces(m));
    }

    public int findProvinces(int[][] isConnected) {
        final int size = isConnected.length;
        final UnionFind unionFind = new UnionFind(size);
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (isConnected[y][x] == 1) {
                    unionFind.union(y, x);
                }
            }
        }
        return unionFind.sets;
    }

    private static class UnionFind {
        final int[] root;
        final int[] rank;
        int sets;

        public UnionFind(int size) {
            sets = size;
            root = new int[size];
            rank = new int[size];
            IntStream.range(0, size).forEach(i -> {
                root[i] = i;
                rank[i] = 1;
            });
        }

        public int find(final int element) {
            if (element == root[element]) {
                return element;
            }
            root[element] = find(root[element]);
            return root[element];
        }

        public void union(final int a, final int b) {
            final int rootA = find(a);
            final int rootB = find(b);
            if (rootA != rootB) {
                if (rank[rootA] > rank[rootB]) {
                    root[rootB] = rootA;
                } else if (rank[rootB] > rank[rootA]) {
                    root[rootA] = rootB;
                } else {
                    root[rootB] = rootA;
                    rank[rootA] = rank[rootA] + 1;
                }
                sets--;
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }
}
