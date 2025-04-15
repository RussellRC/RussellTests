package org.russell.dsa;

import java.util.*;
import org.russell.dsa.graphs.Graph;

public class GraphPractice {

  /** */
  public static Deque<Graph.Node> topologicalSort(final Graph g) {
    final Deque<Graph.Node> stack = new LinkedList<>();
    final Set<Graph.Node> visited = new LinkedHashSet<>();

    for (Graph.Node root : g.roots) {
      if (!visited.contains(root)) {
        visitTopologicalSort(root, visited, stack);
      }
    }
    return stack;
  }

  private static void visitTopologicalSort(
      final Graph.Node node, final Set<Graph.Node> visited, final Deque<Graph.Node> stack) {
    visited.add(node);
    for (final Graph.Node adj : node.neighbors) {
      if (!visited.contains(adj)) {
        visitTopologicalSort(adj, visited, stack);
      }
    }

    stack.push(node);
  }

  public static Graph cloneGraph(final Graph.Node node) {
    // Map of visited nodes, where k=node v=clone, so that there is only 1 clone per node
    final Map<Graph.Node, Graph.Node> visited = new HashMap<>();
    final Queue<Graph.Node> pending = new LinkedList<>();

    Graph.Node root = new Graph.Node(node.value);

    visited.put(node, root);
    pending.add(node);
    Graph.Node current, clone;

    while (!pending.isEmpty()) {
      current = pending.poll();
      clone = visited.get(current);
      clone.neighbors = new ArrayList<>();

      for (Graph.Node adjacent : current.neighbors) {
        Graph.Node adjacentClone = visited.get(adjacent);
        if (adjacentClone == null) {
          // Create new clone when it doesn't already exist
          adjacentClone = new Graph.Node(adjacent.value);
        }
        clone.neighbors.add(adjacentClone);

        if (!visited.containsKey(adjacent)) {
          pending.add(adjacent);
          visited.put(adjacent, adjacentClone);
        }
      }
    }

    return new Graph(root);
  }
}
