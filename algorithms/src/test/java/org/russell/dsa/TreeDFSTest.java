package org.russell.dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TreeDFSTest extends HITreeTests {

  @Test
  public void testMaxDepth() {
    assertEquals(3, TreeDFS.maxDepth(HI_TREE_1.root));
  }

  @Test
  public void goodNodes() {
    assertEquals(3, TreeDFS.goodNodes(HI_TREE_1.root, HI_TREE_1.root.right.right));
  }

  @Test
  public void validBts_valid() {
    assertTrue(TreeDFS.isValidBts(HI_TREE_1.root));
  }

  @Test
  public void calculateTilt() {
    assertEquals(21, TreeDFS.findTilt(HI_TREE_1.root));
  }

  @Test
  public void longestUnivaluePath() {
    final var r1 = TreeDFS.longestUnivaluePath(HI_TREE_3.root);
    System.out.println(r1);
    assertEquals(3, r1.size());

    final var r2 = TreeDFS.longestUnivaluePath(HI_TREE_4.root);
    System.out.println(r2);
    assertEquals(5, r2.size());
  }

  @Test
  public void allLeafPathSum() {
    final var r1 = TreeDFS.allLeafPathSum(HI_TREE_2.root, 10);
    System.out.println(r1);
    assertEquals(2, r1.size());
  }
}
