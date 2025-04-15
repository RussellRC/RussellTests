package org.russell.dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.russell.dsa.graphs.Graph;
import org.russell.dsa.graphs.Graph.Node;

public class GraphDFSTest {

  private GraphDFS gd;

  @BeforeEach
  void beforeEach() {
    gd = new GraphDFS();
  }

  @Test
  void buildAdjList() {
    var expected =
        Map.of(0, List.of(1, 3, 2), 1, List.of(0, 2), 2, List.of(1, 3, 0), 3, List.of(2, 0));
    assertEquals(
        expected,
        gd.buildAdjList(
            List.of(List.of(0, 1), List.of(1, 2), List.of(2, 3), List.of(3, 0), List.of(0, 2))));
  }

  @Test
  void copyGraph() {
    var expected = Map.of(1, List.of(2, 4), 2, List.of(1, 3), 3, List.of(2, 4), 4, List.of(1, 3));

    var n1 = new Node(1);
    var n2 = new Node(2);
    var n3 = new Node(3);
    var n4 = new Node(4);
    n1.neighbors = List.of(n2, n4);
    n2.neighbors = List.of(n1, n3);
    n3.neighbors = List.of(n2, n4);
    n4.neighbors = List.of(n1, n3);

    assertEquals(expected, gd.copyGraph(n1));
  }

  @Test
  public void validTree() {
    final List<List<Integer>> edges = List.of(List.of(0, 1), List.of(1, 2), List.of(2, 3), List.of(1, 3), List.of(1, 4));
    assertFalse(gd.validTree(edges));
  }
}
