package russell.tests.algorithms.test;

import russell.tests.algorithms.domain.Tree;
import russell.tests.algorithms.domain.Tree.Node;

public class TreeTest {

	public static void main(String[] args) {

		final Tree tree = new Tree();
		tree.root = new Node("F");
		tree.root.left = new Node("B");
		tree.root.right = new Node("G");
		
		// child of B
		tree.root.left.left = new Node("A");
		tree.root.left.right = new Node("D");
		
		// child of D
		tree.root.left.right.left = new Node("C");
		tree.root.left.right.right = new Node("E");
		
		// child of G
		tree.root.right.right = new Node("I");
		
		// child of I
		tree.root.right.right.left = new Node("H");

		//testBFS(tree);
		//testPreOrderRecursive(tree);
		//testPreOrderIterative(tree);
		testPostOrderRecursive(tree);
		testPostOrderIterative(tree);
	}
	
	private static void testPostOrderIterative(final Tree tree) {
        System.out.println("\n##### Post-order iterative #####");
        Tree.postOrderIterative(tree.root);
    }
	
	private static void testPostOrderRecursive(final Tree tree) {
	    System.out.println("\n##### Post-order rec #####");
	    Tree.postOrderRecursive(tree.root);
	}
	
	private static void testPreOrderRecursive(Tree tree) {
		System.out.println("\n##### Pre-order rec #####");
		Tree.preOrderRecursive(tree.root);
	}
	
	private static void testPreOrderIterative(Tree tree) {
		System.out.println("\n##### Pre-order iterative #####");
		Tree.preOrderIterative(tree.root);
	}

	private static void testBFS(Tree tree) {
		System.out.println("\n##### BFS #####");
		Tree.bfs(tree.root);
	}

	private static void testTreeHeight(final Tree tree) {
		System.out.println("height of tree: " + Tree.getHeight(tree.root));
	}
	
	private static void testInOrderRecursive(final Tree tree) {
		System.out.println("\n##### in-order rec #####");
		Tree.inOrderRecursive(tree.root);
	}
	
	private static void testInOrderIterative(final Tree tree) {
		System.out.println("\n\n##### in-order iterative #####");
		Tree.inOrderIterative(tree.root);
	}
}
