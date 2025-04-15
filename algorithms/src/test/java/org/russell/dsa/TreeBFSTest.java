package org.russell.dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TreeBFSTest extends HITreeTests {

  @Test
  public void maxWidth() {
    assertEquals(4, TreeBFS.maxWidth(HI_TREE_5.root));
    assertEquals(2, TreeBFS.maxWidth(HI_TREE_6.root));
    assertEquals(7, TreeBFS.maxWidth(HI_TREE_7.root));
  }
}
