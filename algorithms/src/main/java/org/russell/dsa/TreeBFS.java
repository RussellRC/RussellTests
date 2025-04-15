package org.russell.dsa;

import java.util.Deque;
import java.util.LinkedList;
import org.russell.dsa.tree.Tree;

public class TreeBFS {

  /** https://www.hellointerview.com/learn/code/breadth-first-search/maximum-width-of-binary-tree */
  public static int maxWidth(final Tree.Node<Integer> root) {
    if (root == null) {
      return 0;
    }

    final Deque<MaxWidthNode> pending = new LinkedList<>();
    pending.add(MaxWidthNode.of(root, 0));

    var maxWidth = 1;

    while (!pending.isEmpty()) {
      final var level = pending.size();
      var leftPosition = pending.getFirst().position;
      var rightPosition = -1;

      for (int i = 0; i < level; i++) {
        final var node = pending.removeFirst();
        if (i == level - 1) {
          rightPosition = node.position;
        }

        if (node.node.left != null) {
          pending.add(MaxWidthNode.of(node.node.left, node.position * 2));
        }
        if (node.node.right != null) {
          pending.add(MaxWidthNode.of(node.node.right, node.position * 2 + 1));
        }

        var levelWidth = rightPosition - leftPosition + 1;
        maxWidth = Math.max(maxWidth, levelWidth);
      }
    }

    return maxWidth;
  }

  private static class MaxWidthNode {
    Tree.Node<Integer> node;
    int position;

    private MaxWidthNode(final Tree.Node<Integer> node, final int position) {
      this.node = node;
      this.position = position;
    }

    private static MaxWidthNode of(final Tree.Node<Integer> node, final int position) {
      return new MaxWidthNode(node, position);
    }
  }
}
