package org.russell.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.russell.dsa.graphs.Graph.Node;

public class GraphDFS {

  /**
   * https://www.hellointerview.com/learn/code/depth-first-search/adjacency-list#constructing-adjacency-lists
   */
  public Map<Integer, List<Integer>> buildAdjList(final List<List<Integer>> edges) {
    final Map<Integer, List<Integer>> adjList = new HashMap<>();
    for (final List<Integer> edge : edges) {
      adjList.computeIfAbsent(edge.get(0), ArrayList::new).add(edge.get(1));
      adjList.computeIfAbsent(edge.get(1), ArrayList::new).add(edge.get(0));
    }
    return adjList;
  }

  /** https://www.hellointerview.com/learn/code/depth-first-search/copy-graph */
  public Map<Integer, List<Integer>> copyGraph(final Node node) {
    final Map<Integer, List<Integer>> adjList = new HashMap<>();
    copyNode(node, adjList);
    return adjList;
  }

  /** Helper of {@link #copyGraph(Node)} */
  private void copyNode(final Node node, final Map<Integer, List<Integer>> adjList) {
    if (adjList.containsKey(node.value)) {
      return;
    }

    final List<Integer> neighbors = adjList.computeIfAbsent(node.value, ArrayList::new);
    for (final Node neighbor : node.neighbors) {
      neighbors.add(neighbor.value);
    }

    for (final Node neighbor : node.neighbors) {
      copyNode(neighbor, adjList);
    }
  }

  /** https://www.hellointerview.com/learn/code/depth-first-search/graph-valid-tree */
  public boolean validTree(final List<List<Integer>> edges) {
    final Map<Integer, List<Integer>> adjList = new HashMap<>();

    // Build adj list
    for (final var edge : edges) {
      adjList.computeIfAbsent(edge.getFirst(), ArrayList::new).add(edge.getLast());
      adjList.computeIfAbsent(edge.getLast(), ArrayList::new).add(edge.getFirst());
    }

    // There must be no cycles to be a valid tree
    final Set<Integer> visited = new HashSet<>();
    if (hasCycle(adjList, edges.getFirst().getFirst(), visited, null)) {
      return false;
    }

    // Must be a connected graph => all nodes must be in present as keys in the adj list
    return visited.size() == adjList.size();
  }

  /** helper for {@link #validTree(List)} */
  private static boolean hasCycle(
      final Map<Integer, List<Integer>> adjList,
      final Integer current,
      final Set<Integer> visited,
      final Integer parent) {

    visited.add(current);
    for (final Integer neighbor : adjList.get(current)) {
      if (!Objects.equals(parent, neighbor) && visited.contains(neighbor)) {
        return true;
      }
      if (!visited.contains(neighbor) && hasCycle(adjList, neighbor, visited, current)) {
        return true;
      }
    }
    return false;
  }
}
