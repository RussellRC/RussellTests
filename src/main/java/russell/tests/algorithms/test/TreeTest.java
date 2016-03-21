package russell.tests.algorithms.test;

import java.util.Arrays;

import russell.tests.algorithms.domain.Tree;
import russell.tests.algorithms.domain.Tree.Node;

public class TreeTest {

	public static void main(String[] args) {

		final Tree<String> letterTree = newLetterTree();
		final Tree<Integer> numberTree = newNumberTree();
		// testBFS(tree);
		// testPreOrderRecursive(tree);
		// testPreOrderIterative(tree);
		//testInOrderIterative(letterTree);
		//testPostOrderRecursive(letterTree);
		// testPostOrderIterative(tree);
		// testFlattenTree(letterTree);

		// testHasLeafPathSum(numberTree);
		// testAllLeafPathSum(numberTree);
		testBuildTree();
	}

	public static void testBuildTree() {
		System.out.println("\n##### Build Tree from InOrder+PostOrder #####");
		Node<Integer> root = Tree.buildTree(Arrays.asList(4, 2, 5, 1, 6, 7, 3, 8),
											Arrays.asList(4, 5, 2, 6, 7, 8, 3, 1));
		Tree.inOrderIterative(root);

		System.out.println();
		Node<String> root2 = Tree.buildTree(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I"),
											Arrays.asList("A", "C", "E", "D", "B", "H", "I", "G", "F"));
		Tree.inOrderIterative(root2);
	}

	public static void testAllLeafPathSum(final Tree tree) {
		System.out.println("\n##### All Root-Leaf Paths to Sum #####");
		System.out.println(Tree.allLeafPathSum(tree.root, 22));
	}

	public static void testHasLeafPathSum(final Tree tree) {
		System.out.println("\n##### Has Leaf Path to Sum #####");
		System.out.println(Tree.hasLeafPathSum(tree.root, 27));
		System.out.println(Tree.hasLeafPathSum(tree.root, 22));
		System.out.println(Tree.hasLeafPathSum(tree.root, 26));
		System.out.println(Tree.hasLeafPathSum(tree.root, 18));
		System.out.println(Tree.hasLeafPathSum(tree.root, 13));
	}

	public static void testFlattenTree(final Tree tree) {
		System.out.println("\n##### Flatten Tree #####");
		System.out.println(Tree.flattenTree(tree.root));
	}

	public static void testPostOrderIterative(final Tree tree) {
		System.out.println("\n##### Post-order iterative #####");
		Tree.postOrderIterative(tree.root);
	}

	public static void testPostOrderRecursive(final Tree tree) {
		System.out.println("\n##### Post-order rec #####");
		Tree.postOrderRecursive(tree.root);
	}

	public static void testPreOrderRecursive(Tree tree) {
		System.out.println("\n##### Pre-order rec #####");
		Tree.preOrderRecursive(tree.root);
	}

	public static void testPreOrderIterative(Tree tree) {
		System.out.println("\n##### Pre-order iterative #####");
		Tree.preOrderIterative(tree.root);
	}

	public static void testBFS(Tree tree) {
		System.out.println("\n##### BFS #####");
		Tree.bfs(tree.root);
	}

	public static void testTreeHeight(final Tree tree) {
		System.out.println("height of tree: " + Tree.getHeight(tree.root));
	}

	public static void testInOrderRecursive(final Tree tree) {
		System.out.println("\n##### in-order rec #####");
		Tree.inOrderRecursive(tree.root);
	}

	public static void testInOrderIterative(final Tree tree) {
		System.out.println("\n\n##### in-order iterative #####");
		Tree.inOrderIterative(tree.root);
	}

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
		tree.root.right.right.left = new Node<>(5);
		tree.root.right.right.right = new Node<>(1);

		return tree;
	}
}
