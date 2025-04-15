package org.russell.dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.russell.dsa.tree.Tree.Node;

public class TreePractice {

  /**
   * @param root
   * @return
   */
  public static List<List<Integer>> levelOrder(final Node<Integer> root) {
    final var result = new LinkedList<List<Integer>>();

    final Deque<Node<Integer>> pending = new LinkedList<>();
    pending.add(root);

    while (!pending.isEmpty()) {
      final var level = pending.size();
      final var currentLevel = new LinkedList<Integer>();

      for (int i = 0; i < level; i++) {
        final var current = pending.removeFirst();
        currentLevel.add(current.data);
        if (current.left != null) {
          pending.add(current.left);
        }
        if (current.right != null) {
          pending.add(current.right);
        }
      }

      result.add(currentLevel);
    }

    return result;
  }

  /**
   * @param node
   * @return
   */
  public static int getHeight(final Node<?> node) {

    int leftHeight = 0;
    int rightHeight = 0;
    if (node.left != null) {
      leftHeight = getHeight(node.left);
    }
    if (node.right != null) {
      rightHeight = getHeight(node.right);
    }

    return Math.max(leftHeight, rightHeight) + 1;
  }

  /** https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description */
  public static <T> List<T> flattenTree(final Node<T> node) {
    if (node == null) {
      return Collections.emptyList();
    }

    final List<T> result = new LinkedList<>();
    final Deque<Node<T>> pending = new LinkedList<>();

    Node<T> current = node;
    while (current != null || !pending.isEmpty()) {
      if (current == null) {
        current = pending.pop();
      } else {
        if (current.right != null) {
          pending.push(current.right);
        }
        result.add(current.data);
        current = current.left;
      }
    }

    return result;
  }

  /**
   * @param node
   * @param path
   * @param result
   */
  public static <T> void rootToLeafPaths(
      final Node<T> node, final List<T> path, final List<List<T>> result) {
    if (node == null) {
      return;
    }

    if (node.left == null && node.right == null) {
      // node is leaf, add path to result
      final List<T> newPath = new ArrayList<>(path);
      newPath.add(node.data);
      result.add(newPath);
      return;
    }

    path.add(node.data);
    rootToLeafPaths(node.left, path, result);
    rootToLeafPaths(node.right, path, result);
    path.removeLast();
  }

  /**
   * Returns whether there is a path from root to a node with given value
   *
   * @param current
   * @param path
   * @param value
   * @return
   */
  public static <T> boolean existsPathToNode(
      final Node<T> current, List<Node<T>> path, final T value) {
    if (current == null) {
      return false;
    }
    if (path == null) {
      path = new ArrayList<>();
    }

    path.add(current);

    if (current.data == value) {
      return true;
    }

    boolean onLeft = false;
    boolean onRight = false;
    if (current.left != null) {
      onLeft = existsPathToNode(current.left, path, value);
    }
    if (current.right != null) {
      onRight = existsPathToNode(current.right, path, value);
    }
    if (onLeft || onRight) {
      return true;
    }

    // If not present in subtree rooted with current, remove it from path and return False
    path.removeLast();
    return false;
  }

  /**
   * Finds distance between 2 values http://www.geeksforgeeks.org/find-distance-two-given-nodes/
   *
   * @param root
   * @param data1
   * @param data2
   * @return
   */
  public static <T> int distance(Node<T> root, T data1, T data2) {
    if (root == null || data1 == null || data2 == null) {
      return 0;
    }

    //	path to data1
    final List<Node<T>> path1 = new ArrayList<>();
    final boolean pathData1 = existsPathToNode(root, path1, data1);
    if (!pathData1) {
      return 0;
    }

    // path to data2
    final List<Node<T>> path2 = new ArrayList<>();
    final boolean pathToData2 = existsPathToNode(root, path2, data2);
    if (!pathToData2) {
      return 0;
    }

    // find common path length
    int i = 0;
    while (i < path1.size() && i < path2.size()) {
      if (path1.get(i) != path2.get(i)) {
        break;
      }
      i = i + 1;
    }
    // Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2*Dist(root, lca)
    // lca = lowest common ancestor
    return path1.size() + path2.size() - 2 * i;
  }
}
