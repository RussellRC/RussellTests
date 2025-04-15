package org.russell.dsa;

import org.junit.jupiter.api.BeforeAll;
import org.russell.dsa.tree.Tree;

public class HITreeTests {

    protected static final Tree<Integer> HI_TREE_1 = new Tree<>();
    protected static final Tree<Integer> HI_TREE_2 = new Tree<>();
    protected static final Tree<Integer> HI_TREE_3 = new Tree<>();
    protected static final Tree<Integer> HI_TREE_4 = new Tree<>();
    protected static final Tree<Integer> HI_TREE_5 = new Tree<>();
    protected static final Tree<Integer> HI_TREE_6 = new Tree<>();
    protected static final Tree<Integer> HI_TREE_7 = new Tree<>();

    @BeforeAll
    static void beforeAll() {
        HI_TREE_1.root = new Tree.Node<>(4);
        HI_TREE_1.root.left = new Tree.Node<>(2);
        HI_TREE_1.root.right = new Tree.Node<>(7);
        HI_TREE_1.root.left.left = new Tree.Node<>(1);
        HI_TREE_1.root.left.right = new Tree.Node<>(3);
        HI_TREE_1.root.right.left = new Tree.Node<>(6);
        HI_TREE_1.root.right.right = new Tree.Node<>(9);

        HI_TREE_2.root = new Tree.Node<>(1);
        HI_TREE_2.root.left = new Tree.Node<>(2);
        HI_TREE_2.root.right = new Tree.Node<>(4);
        HI_TREE_2.root.left.left = new Tree.Node<>(4);
        HI_TREE_2.root.left.right = new Tree.Node<>(7);
        HI_TREE_2.root.right.left = new Tree.Node<>(5);
        HI_TREE_2.root.right.right = new Tree.Node<>(1);

        HI_TREE_3.root = new Tree.Node<>(1);
        HI_TREE_3.root.left = new Tree.Node<>(4);
        HI_TREE_3.root.right = new Tree.Node<>(5);
        HI_TREE_3.root.left.left = new Tree.Node<>(4);
        HI_TREE_3.root.left.right = new Tree.Node<>(4);
        HI_TREE_3.root.right.left = new Tree.Node<>(5);

        HI_TREE_4.root = new Tree.Node<>(1);
        HI_TREE_4.root.left = new Tree.Node<>(1);
        HI_TREE_4.root.right = new Tree.Node<>(1);
        HI_TREE_4.root.left.left = new Tree.Node<>(1);
        HI_TREE_4.root.left.right = new Tree.Node<>(1);
        HI_TREE_4.root.right.left = new Tree.Node<>(1);
        HI_TREE_4.root.right.right = new Tree.Node<>(1);

        HI_TREE_5.root = new Tree.Node<>(4);
        HI_TREE_5.root.left = new Tree.Node<>(2);
        HI_TREE_5.root.right = new Tree.Node<>(7);
        HI_TREE_5.root.left.left = new Tree.Node<>(1);
        HI_TREE_5.root.right.right = new Tree.Node<>(9);

        HI_TREE_6.root = new Tree.Node<>(4);
        HI_TREE_6.root.left = new Tree.Node<>(2);
        HI_TREE_6.root.right = new Tree.Node<>(7);
        HI_TREE_6.root.left.left = new Tree.Node<>(1);

        HI_TREE_7.root = new Tree.Node<>(4);
        HI_TREE_7.root.left = new Tree.Node<>(2);
        HI_TREE_7.root.right = new Tree.Node<>(7);
        HI_TREE_7.root.left.left = new Tree.Node<>(1);
        HI_TREE_7.root.right.left = new Tree.Node<>(6);
        HI_TREE_7.root.right.right = new Tree.Node<>(9);
        HI_TREE_7.root.left.left.left = new Tree.Node<>(7);
        HI_TREE_7.root.right.left.right = new Tree.Node<>(1);
        HI_TREE_7.root.right.right.left = new Tree.Node<>(1);
    }
}
