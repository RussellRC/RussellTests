package org.russell.dsa.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.russell.dsa.HITreeTests;
import org.russell.dsa.TreeDFS;
import org.russell.dsa.TreePractice;
import org.russell.dsa.tree.Tree.Node;

public class TreePracticeTest extends HITreeTests {

  private static Tree<String> letterTree;
  private static Tree<Integer> numberTree;

  @BeforeAll
  public static void beforeClass() {
    letterTree = newLetterTree();
    numberTree = newNumberTree();
  }

  @Test
  public void testPathToNode() {
    System.out.println(TreePractice.existsPathToNode(numberTree.root, null, 5));
    System.out.println(TreePractice.existsPathToNode(numberTree.root, null, 11));
    System.out.println(TreePractice.existsPathToNode(numberTree.root, null, 7));
    System.out.println(TreePractice.existsPathToNode(numberTree.root, null, 2));
    System.out.println(TreePractice.existsPathToNode(numberTree.root, null, 8));
    System.out.println(TreePractice.existsPathToNode(numberTree.root, null, 3));
    System.out.println(TreePractice.existsPathToNode(numberTree.root, null, 1));

    System.out.println(TreePractice.existsPathToNode(numberTree.root, null, 50));
  }

  @Test
  public void testDistance() {
    assertEquals(2, TreePractice.distance(letterTree.root, "F", "A"));
    assertEquals(3, TreePractice.distance(letterTree.root, "F", "H"));
    assertEquals(6, TreePractice.distance(letterTree.root, "E", "H"));
    assertEquals(2, TreePractice.distance(letterTree.root, "B", "C"));
  }

  @Test
  public void testBuildTree() {
    System.out.println("\n##### Build Tree from InOrder+PostOrder #####");
    Node<Integer> root =
        Tree.buildTree(
            Arrays.asList(4, 2, 5, 1, 6, 7, 3, 8), Arrays.asList(4, 5, 2, 6, 7, 8, 3, 1));
    Tree.inOrderIterative(root);

    System.out.println();
    Node<String> root2 =
        Tree.buildTree(
            Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I"),
            Arrays.asList("A", "C", "E", "D", "B", "H", "I", "G", "F"));
    Tree.inOrderIterative(root2);
  }

  @Test
  public void testFlattenTree() {
    System.out.println("\n##### Flatten Tree #####");
    System.out.println(TreePractice.flattenTree(letterTree.root));
  }

  @Test
  public void testPostOrderIterative() {
    System.out.println("\n##### Post-order iterative #####");
    Tree.postOrderIterative(letterTree.root);
  }

  @Test
  public void testPostOrderRecursive() {
    System.out.println("\n##### Post-order rec #####");
    Tree.postOrderRecursive(letterTree.root);
  }

  @Test
  public void testPreOrderRecursive() {
    System.out.println("\n##### Pre-order rec #####");
    Tree.preOrderRecursive(letterTree.root);
  }

  @Test
  public void testPreOrderIterative() {
    System.out.println("\n##### Pre-order iterative #####");
    Tree.preOrderIterative(letterTree.root);
  }

  @Test
  public void testBFSLetter() {
    System.out.println("\n##### BFS #####");
    Tree.bfs(letterTree.root);
  }

  @Test
  public void testBFSNumber() {
    System.out.println("\n##### BFS #####");
    Tree.bfs(numberTree.root);
  }

  @Test
  public void testTreeHeight() {
    System.out.println("height of tree: " + TreePractice.getHeight(letterTree.root));
  }

  @Test
  public void testInOrderRecursive() {
    System.out.println("\n##### in-order rec #####");
    Tree.inOrderRecursive(letterTree.root);
  }

  @Test
  public void testInOrderIterative() {
    System.out.println("\n\n##### in-order iterative #####");
    Tree.inOrderIterative(letterTree.root);
  }

  @Test
  public void rootToLeafPaths() {
    System.out.println("\n\n##### Root To Leaf Paths #####");

    final List<List<Integer>> result = new ArrayList<>();
    final List<Integer> path = new ArrayList<>();

    TreePractice.rootToLeafPaths(numberTree.root, path, result);

    System.out.println(result);
  }

  @Test
  public void testAllLeafPathSum() {
    System.out.println("\n##### All Root-Leaf Paths to Sum #####");
    System.out.println(TreeDFS.allLeafPathSum(numberTree.root, 22));
  }

  @Test
  public void testHasLeafPathSum() {
    System.out.println("\n##### Has Leaf Path to Sum #####");
    System.out.println(TreeDFS.hasLeafPathSum(numberTree.root, 27));
    System.out.println(TreeDFS.hasLeafPathSum(numberTree.root, 22));
    System.out.println(TreeDFS.hasLeafPathSum(numberTree.root, 26));
    System.out.println(TreeDFS.hasLeafPathSum(numberTree.root, 18));
    System.out.println(TreeDFS.hasLeafPathSum(numberTree.root, 13));
  }

  @Test
  public void levelOrder() {
    final var levelOrder1 = TreePractice.levelOrder(HI_TREE_1.root);
    System.out.println(levelOrder1);
    assertEquals(3, levelOrder1.size());
  }

  /**
   * F B G A D I C E H
   *
   * @return
   */
  private static Tree<String> newLetterTree() {
    final Tree<String> tree = new Tree<>();
    tree.root = new Node<>("F");
    tree.root.left = new Node<>("B");
    tree.root.right = new Node<>("G");

    // child of B
    tree.root.left.left = new Node<>("A");
    tree.root.left.right = new Node<>("D");

    // child of D
    tree.root.left.right.left = new Node<>("C");
    tree.root.left.right.right = new Node<>("E");

    // child of G
    tree.root.right.right = new Node<>("I");

    // child of I
    tree.root.right.right.left = new Node<>("H");
    return tree;
  }

  /**
   * 5 4 8 11 13 4 7 2 3 1
   *
   * @return
   */
  private static Tree<Integer> newNumberTree() {
    final Tree<Integer> tree = new Tree<>();
    tree.root = new Node<>(5);
    tree.root.left = new Node<>(4);
    tree.root.right = new Node<>(8);

    tree.root.left.left = new Node<>(11);
    tree.root.left.left.left = new Node<>(7);
    tree.root.left.left.right = new Node<>(2);

    tree.root.right.left = new Node<>(13);
    tree.root.right.right = new Node<>(4);
    tree.root.right.right.left = new Node<>(3);
    tree.root.right.right.right = new Node<>(1);

    return tree;
  }
}
