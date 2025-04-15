package org.russell.dsa.graphs;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Undirected graph
 *
 * @author russellrazo
 */
public class Graph {

  public static final Logger log = LoggerFactory.getLogger(Graph.class);

  public Node root;
  public List<Node> roots;

  public Graph(final Node root) {
    this.root = root;
  }

  public Graph(final List<Node> roots) {
    this.roots = roots;
  }

  /** Inner Node class */
  public static class Node {
    public Integer value;
    public List<Node> neighbors;

    public Node(final int value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.format("{value:%d}", value);
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      Node node = (Node) o;
      return Objects.equals(value, node.value) && Objects.equals(neighbors, node.neighbors);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, neighbors);
    }
  }

  /** Inner BFS Result class */
  static class BFSResult {
    final Node originNode;
    final Node destNode;
    final Map<Node, Node> path;

    BFSResult(final Node originNode, final Node destNode, final Map<Node, Node> path) {
      this.originNode = originNode;
      this.destNode = destNode;
      this.path = path;
    }
  }

  public static boolean areConnected(final Graph graph, final int a, final int b) {
    final BFSResult fromGraphToA = findNodeBFS(graph.root, a);

    if (fromGraphToA.destNode != null) {
      final BFSResult fromAToB = findNodeBFS(fromGraphToA.destNode, b);
      if (fromAToB.destNode != null) {

        printPath(fromAToB);

        return true;
      }
    }

    // No path from root to A, search B -> A
    final BFSResult fromGraphToB = findNodeBFS(graph.root, b);
    if (fromGraphToB.destNode != null) {
      final BFSResult fromBtoA = findNodeBFS(fromGraphToB.destNode, a);
      if (fromBtoA.destNode != null) {
        printPath(fromBtoA);
        return true;
      }
    }

    return false;
  }

  private static void printPath(final BFSResult bfsResult) {
    final LinkedList<Node> path = new LinkedList<>();

    Node node = null;
    for (node = bfsResult.destNode; node != bfsResult.originNode; node = bfsResult.path.get(node)) {
      path.addFirst(node);
    }
    path.addFirst(node);

    System.out.println(
        String.format(
            "Path from %s to %s: %s", bfsResult.originNode.value, bfsResult.destNode.value, path));
  }

  public static BFSResult findNodeBFS(final Node originNode, final int destValue) {
    final Queue<Node> pending = new LinkedList<>();
    pending.add(originNode);

    final Set<Node> visited = new HashSet<>();
    visited.add(originNode);

    // Map that holds the link from previous to node
    final Map<Node, Node> previous = new HashMap<Node, Node>();

    while (!pending.isEmpty()) {
      final Node node = pending.poll();

      log.debug("visiting: " + node);
      if (node.value == destValue) {
        log.debug("found it: " + destValue);
        return new BFSResult(originNode, node, previous);
      }

      if (node.neighbors != null) {
        for (final Node adjacent : node.neighbors) {
          if (!visited.contains(adjacent)) {
            pending.add(adjacent);
            visited.add(adjacent);
            previous.put(adjacent, node);
          }
        }
      }
    }
    return new BFSResult(originNode, null, null);
  }

  /**
   * Detph First Search
   *
   * @param originNode
   * @param value
   * @param visited
   * @return
   */
  public static Node findNodeDFS(final Node originNode, final int value, final Set<Node> visited) {

    if (originNode == null || visited.contains(originNode)) {
      return null;
    }

    log.debug("visiting: " + originNode);
    visited.add(originNode);
    if (originNode.value == value) {
      log.debug("found it: " + value);
      return originNode;
    }

    if (originNode.neighbors != null) {
      for (final Node node : originNode.neighbors) {
        final Node foundNode = findNodeDFS(node, value, visited);
        if (foundNode != null) {
          return foundNode;
        }
      }
    }

    return null;
  }

  /**
   * DFS iterative
   *
   * @param originNode
   * @param value
   * @return
   */
  public static Node dfsIterative(final Node originNode, final int value) {
    final Set<Node> visited = new HashSet<>();

    final Deque<Node> pending = new LinkedList<>();
    pending.push(originNode);

    while (!pending.isEmpty()) {
      final Node pop = pending.pop();

      if (!visited.contains(pop)) {
        visited.add(pop);
        System.out.println("Visiting: " + pop.value);
        if (pop.value == value) {
          return pop;
        }
      }

      if (pop.neighbors != null) {
        for (final Node adj : pop.neighbors) {
          pending.addFirst(adj);
        }
      }
    }
    return null;
  }
}
