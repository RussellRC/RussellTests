package org.russell.algorithms.practice.lc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/alien-dictionary/
 * https://youtu.be/6kTZYvNNyps
 */
public class AlienDictionary {

    public String alienOrder(List<String> words) {

        // build adjacency map
        final Map<Character, Set<Character>> adjacentsMap = new LinkedHashMap<>();
        for (int i = 0; i < words.size()-1; i++) {
            final String w1 = words.get(i);
            final String w2 = words.get(i+1);
            w1.chars().mapToObj(n -> (char) n).forEach(c -> adjacentsMap.computeIfAbsent(c, LinkedHashSet::new));
            w2.chars().mapToObj(n -> (char) n).forEach(c -> adjacentsMap.computeIfAbsent(c, LinkedHashSet::new));

            final int minLen = Math.min(w1.length(), w2.length());
            if (w1.length() > w2.length() && w1.substring(0, minLen).equals(w2.substring(0, minLen))) {
                // if w1 is longer than w2 but prefix is the same
                // then the lexicographical sorting is invalid
                return "";
            }

            // find most significant character and add outgoing edge
            for (int j = 0; j < minLen; j++) {
                final char c1 = w1.charAt(j);
                final char c2 = w2.charAt(j);
                if (c1 != c2) {
                    adjacentsMap.get(c1).add(c2);
                    break;
                }
            }
        }

        final Set<Character> cycle = new HashSet<>();
        final Set<Character> result = new LinkedHashSet<>();
        for (final Character ch : adjacentsMap.keySet()) {
            if (!topologicalSort(ch, adjacentsMap, cycle, result)) {
                return "";
            }
        }

        final StringBuilder sb = new StringBuilder();
        for (final Character ch : result) {
            sb.insert(0, ch);
        }
        return sb.toString();
    }

    private boolean topologicalSort(final Character current, final Map<Character, Set<Character>> adjacentsMap, final Set<Character> cycle, final Set<Character> result) {
        if (result.contains(current)) {
            return true;
        }
        if (cycle.contains(current)) {
            return false;
        }

        cycle.add(current);
        for (final Character adj : adjacentsMap.get(current)) {
            if (!result.contains(adj)) {
                boolean adjResult = topologicalSort(adj, adjacentsMap, cycle, result);
                if (!adjResult) {
                    return false;
                }
            }
        }

        result.add(current);
        cycle.remove(current);
        return true;
    }

    @Test
    public void test1() {
        final List<String> words = List.of("wrt", "wrf", "er", "ett", "rftt");
        assertEquals("wertf", alienOrder(words));
    }

    @Test
    public void test2() {
        final List<String> words = List.of("wrt", "wrf", "er", "ett", "rftt", "rwtt");
        assertEquals("", alienOrder(words));
    }

    @Test
    public void test3() {
        final List<String> words = List.of("a", "ba", "bc", "c");
        assertEquals("abc", alienOrder(words));
    }


}
