package org.russell.dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import org.russell.dsa.tree.Tree;

public class TreeDFS {

  /** https://www.hellointerview.com/learn/code/depth-first-search/maximum-depth-of-binary-tree */
  public static int maxDepth(final Tree.Node<?> node) {
    if (node == null) {
      return 0;
    }
    int leftDepth = maxDepth(node.left);
    int rightDepth = maxDepth(node.right);

    return Math.max(leftDepth, rightDepth) + 1;

    // Alternative solution
    //    return maxDepth(node, 0);
  }
  
  /** Alternative solution to {@link #maxDepth(Tree.Node)} */
  public static int maxDepth(final Tree.Node<?> node, int parentDepth) {
    if (node == null) {
      return parentDepth;
    }
    if (node.left == null && node.right == null) {
      return parentDepth + 1;
    }
    int leftDepth = maxDepth(node.left, parentDepth + 1);
    int rightDepth = maxDepth(node.right, parentDepth + 1);
    return Math.max(leftDepth, rightDepth);
  }

  /** https://www.hellointerview.com/learn/code/depth-first-search/path-sum */
  public static boolean pathSum(final Tree.Node<Integer> node, final int sum) {
    if (node == null) {
      return false;
    }

    if (node.right == null && node.left == null) {
      return node.data == sum;
    }
    boolean left = pathSum(node.left, sum - node.data);
    boolean right = pathSum(node.right, sum - node.data);
    return left || right;
  }

  /** https://www.hellointerview.com/learn/code/depth-first-search/path-sum */
  public static boolean hasLeafPathSum(final Tree.Node<Integer> node, final int sum) {
    if (node == null) {
      return false;
    }

    final Queue<Tree.Node<Integer>> nodes = new LinkedList<>();
    final Queue<Integer> sums = new LinkedList<>();

    nodes.add(node);
    sums.add(node.data);

    while (!nodes.isEmpty()) {
      final Tree.Node<Integer> current = nodes.poll();
      final int total = sums.poll();
      if (current.left == null && current.right == null) {
        if (total == sum) {
          return true;
        }
      }
      if (current.left != null) {
        nodes.add(current.left);
        sums.add(total + current.left.data);
      }
      if (current.right != null) {
        nodes.add(current.right);
        sums.add(total + current.right.data);
      }
    }
    return false;
  }

  /**
   * https://www.hellointerview.com/learn/code/depth-first-search/path-sum-2<br>
   * https://www.hellointerview.com/learn/code/backtracking/overview
   */
  public static List<List<Integer>> allLeafPathSum(final Tree.Node<Integer> node, final int sum) {
    final List<List<Integer>> result = new LinkedList<>();
    allLeafPathSumHelper(node, sum, result, new LinkedList<>());
    return result;
  }

  /** allLeafPathSumHelper */
  private static void allLeafPathSumHelper(
      final Tree.Node<Integer> node,
      final int sum,
      final List<List<Integer>> result,
      final List<Integer> currentPath) {

    if (node == null) {
      return;
    }

    currentPath.add(node.data);

    if (node.left == null && node.right == null) {
      if (sum == node.data) {
        result.add(new LinkedList<>(currentPath));
      }
    }

    allLeafPathSumHelper(node.left, sum - node.data, result, currentPath);
    allLeafPathSumHelper(node.right, sum - node.data, result, currentPath);
    currentPath.removeLast();
  }

  /** https://www.hellointerview.com/learn/code/depth-first-search/global-variables */
  public static <T> int goodNodes(Tree.Node<Integer> root, Tree.Node<Integer> dest) {
    return goodNodes(root, dest, Integer.MIN_VALUE);
  }

  /** goodNodes helper */
  private static int goodNodes(
      final Tree.Node<Integer> root, final Tree.Node<Integer> dest, final int max) {
    if (root == null) {
      return 0;
    }

    int count = 0;
    int newMax = max;
    if (root.data >= max) {
      count = count + 1;
      newMax = root.data;
    }

    final int goodLeft = goodNodes(root.left, dest, newMax);
    final int goodRight = goodNodes(root.right, dest, newMax);
    return goodLeft + goodRight + count;
  }

  /** https://www.hellointerview.com/learn/code/depth-first-search/validate-binary-search-tree */
  public static boolean isValidBts(final Tree.Node<Integer> root) {
    return isValidBts(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
  }

  /** isValidBts helper */
  private static boolean isValidBts(final Tree.Node<Integer> current, int max, int min) {
    if (current == null) {
      return true;
    }

    if (current.data <= min || current.data >= max) {
      return false;
    }

    final var btsLeft = isValidBts(current.left, current.data, min);
    final var btsRight = isValidBts(current.right, max, current.data);
    return btsLeft && btsRight;
  }

  /** https://www.hellointerview.com/learn/code/depth-first-search/calculate-tilt */
  public static int findTilt(final Tree.Node<Integer> root) {
    final var tilts = new ArrayList<Integer>();
    tilts.add(0);

    findTilt(root, tilts);
    return tilts.getLast();
  }

  /** find tilt helper */
  private static int findTilt(final Tree.Node<Integer> current, final List<Integer> tilts) {
    if (current == null) {
      return 0;
    }

    final var leftSum = findTilt(current.left, tilts);
    final var rightSum = findTilt(current.right, tilts);
    final var tilt = Math.abs(leftSum - rightSum);
    tilts.add(tilt + tilts.removeLast());
    return leftSum + rightSum + current.data;
  }

  /** https://www.hellointerview.com/learn/code/depth-first-search/diameter-of-a-binary-tree */
  public static int maxDiameter(final Tree.Node<Integer> root) {
    final AtomicInteger max = new AtomicInteger(0);
    maxDiameter(root, max);
    return max.get();
  }

  /** maxDiameter helper */
  private static int maxDiameter(
      final Tree.Node<Integer> current, final AtomicInteger maxDiameter) {
    if (current == null) {
      return 0;
    }

    final var maxLeft = maxDiameter(current.left, maxDiameter);
    final var maxRight = maxDiameter(current.right, maxDiameter);

    maxDiameter.set(Math.max(maxDiameter.get(), maxLeft + maxRight));

    // return the max depth of the current subtree
    return 1 + Math.max(maxLeft, maxRight);
  }

  /** https://www.hellointerview.com/learn/code/depth-first-search/longest-univalue-path */
  public static List<Integer> longestUnivaluePath(final Tree.Node<Integer> root) {
    final List<List<Integer>> paths = new LinkedList<>();
    longestUnivaluePath(root, paths);

    List<Integer> longest = paths.getFirst();
    for (final var e : paths) {
      if (e.size() > longest.size()) {
        longest = e;
      }
    }
    return longest;
  }

  /** longestUnivaluePath helper */
  private static List<Integer> longestUnivaluePath(
      final Tree.Node<Integer> current, final List<List<Integer>> paths) {

    if (current == null) {
      return Collections.emptyList();
    }

    final var leftPath = longestUnivaluePath(current.left, paths);
    final var rightPath = longestUnivaluePath(current.right, paths);
    final var path = new LinkedList<Integer>();
    paths.add(path);

    if (leftPath.isEmpty() && rightPath.isEmpty()) {
      path.add(current.data);
      return path;
    }

    if (leftPath.isEmpty()) {
      if (Objects.equals(current.data, rightPath.getLast())) {
        path.addAll(rightPath);
      }
      path.add(current.data);
      return path;
    }

    if (rightPath.isEmpty()) {
      if (Objects.equals(current.data, leftPath.getLast())) {
        path.addAll(leftPath);
      }
      path.add(current.data);
      return path;
    }

    if (Objects.equals(current.data, leftPath.getLast())
        && Objects.equals(current.data, rightPath.getLast())) {
      if (leftPath.size() == rightPath.size()) {
        path.addAll(leftPath);
        path.add(current.data);
        final var list = new LinkedList<>(path);
        list.addAll(rightPath);
        paths.add(list);
      }
    } else if (Objects.equals(current.data, leftPath.getLast())) {
      path.addAll(leftPath);
      path.add(current.data);
    } else if (Objects.equals(current.data, rightPath.getLast())) {
      path.add(current.data);
      path.addAll(rightPath);
    } else {
      path.add(current.data);
    }
    return path;
  }
}
